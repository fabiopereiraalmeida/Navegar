package br.com.grupocaravela.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.grupocaravela.model.Empresa;
import br.com.grupocaravela.model.Cliente;
import br.com.grupocaravela.repository.filter.ClienteFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jpa.Transactional;

public class Clientes implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cliente guardar(Cliente cliente) {	
		/*
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		cliente.setEmpresa(empresa);
		
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();
		
		cliente = manager.merge(cliente);
		
		trx.commit();
		
		return cliente;
		*/
		
		//System.out.println("Passei no merge");
		//System.out.println("Cliente: " + cliente.getNome());
		
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		cliente.setEmpresa(empresa);
		//System.out.println("empresa: " + cliente.getEmpresa().getRazaoSocial());
		
		return manager.merge(cliente);
		
	}
	
	@Transactional	//Qunado vai remover a partir do Controlador ".controller"
	public void remover(Cliente cliente) throws NegocioException{
		try {
			//System.out.println("Passei no try de excluir");
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O cliente não pode ser excluido!");
		}
	}
	
	public Cliente porCpf(String cpf) {
		try {
			return manager.createQuery("from Cliente where upper(cpf) = :cpf", Cliente.class)
				.setParameter("cpf", cpf.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filtro) {		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);
		
		if (StringUtils.isNotBlank(filtro.getCpf())) {
			criteria.add(Restrictions.eq("codigo", filtro.getCpf()));			
		}
		
		//MatchMode = % - From nome WHERE nome LIKE '%fabio%'
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}
	
	public List<Cliente> porNome(String nome) {
		return this.manager.createQuery("from Cliente where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
	public List<Cliente> porApelido(String apelido) {
		return this.manager.createQuery("from Cliente where upper(apelido) like :apelido", Cliente.class)
				.setParameter("apelido", apelido.toUpperCase() + "%").getResultList();
	}
	
}
