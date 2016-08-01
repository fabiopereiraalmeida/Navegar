package br.com.grupocaravela.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.grupocaravela.model.Empresa;
import br.com.grupocaravela.model.Fornecedor;
import br.com.grupocaravela.repository.filter.FornecedorFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jpa.Transactional;

public class Fornecedores implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Fornecedor guardar(Fornecedor fornecedor) {	
		/*
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		fornecedores.setEmpresa(empresa);
		
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();
		
		fornecedores = manager.merge(fornecedores);
		
		trx.commit();
		
		return fornecedores;
		*/
		
		//System.out.println("Passei no merge");
		//System.out.println("Fornecedores: " + fornecedores.getNome());
		
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		fornecedor.setEmpresa(empresa);
		//System.out.println("empresa: " + fornecedores.getEmpresa().getRazaoSocial());
		
		return manager.merge(fornecedor);
		
	}
	
	@Transactional	//Qunado vai remover a partir do Controlador ".controller"
	public void remover(Fornecedor fornecedor) throws NegocioException{
		try {
			//System.out.println("Passei no try de excluir");
			fornecedor = porId(fornecedor.getId());
			manager.remove(fornecedor);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O fornecedor não pode ser excluido!");
		}
	}
	
	public Fornecedor porCnpj(String cnpj) {
		try {
			return manager.createQuery("from Fornecedores where upper(cnpj) = :cnpj", Fornecedor.class)
				.setParameter("cnpj", cnpj.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> filtrados(FornecedorFilter filtro) {		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Fornecedor.class);
		
		if (StringUtils.isNotBlank(filtro.getCnpj())) {
			criteria.add(Restrictions.eq("cnpj", filtro.getCnpj()));			
		}
		
		//MatchMode = % - From nome WHERE nome LIKE '%fabio%'
		if (StringUtils.isNotBlank(filtro.getRazaoSocial())) {
			criteria.add(Restrictions.ilike("razaoSocial", filtro.getRazaoSocial(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("razaoSocial")).list();
	}
	
	public Fornecedor porId(Long id) {
		return manager.find(Fornecedor.class, id);
	}
	
	public List<Fornecedor> porRazaoSocial(String razaoSocial) {
		return this.manager.createQuery("from Fornecedor where upper(razaoSocial) like :razaoSocial", Fornecedor.class)
				.setParameter("razaoSocial", razaoSocial.toUpperCase() + "%").getResultList();
	}
	
	public List<Fornecedor> porFantasia(String fantasia) {
		return this.manager.createQuery("from Fornecedor where upper(fantasia) like :fantasia", Fornecedor.class)
				.setParameter("fantasia", fantasia.toUpperCase() + "%").getResultList();
	}
	
}
