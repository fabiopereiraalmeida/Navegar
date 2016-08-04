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
import br.com.grupocaravela.model.FormaPagamento;
import br.com.grupocaravela.model.Usuario;
import br.com.grupocaravela.repository.filter.FormaPagamentoFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jpa.Transactional;

public class FormaPagamentos implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<FormaPagamento> buscarTodasFormasPagamento() {
		//System.out.println("Passei aki 02");
		return manager.createQuery("FROM FormaPagamento", FormaPagamento.class).getResultList(); //(JPQL) Seleciona o objeto
	}
	
	public FormaPagamento guardar(FormaPagamento formaPagamento) {	
		
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		formaPagamento.setEmpresa(empresa);
		//System.out.println("empresa: " + formaPagamento.getEmpresa().getRazaoSocial());
		
		return manager.merge(formaPagamento);
		
	}
	
	@Transactional	//Qunado vai remover a partir do Controlador ".controller"
	public void remover(FormaPagamento formaPagamento) throws NegocioException{
		try {
			//System.out.println("Passei no try de excluir");
			formaPagamento = porId(formaPagamento.getId());
			manager.remove(formaPagamento);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O formaPagamento não pode ser excluido!");
		}
	}
	
	public FormaPagamento IgualdadeNome(String nome) {
		try {
			return manager.createQuery("from FormaPagamento where upper(nome) = :nome", FormaPagamento.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FormaPagamento> filtrados(FormaPagamentoFilter filtro) {		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FormaPagamento.class);
		
		//MatchMode = % - From nome WHERE nome LIKE '%fabio%'
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public FormaPagamento porId(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	public List<FormaPagamento> porNome(String nome) {
		return this.manager.createQuery("from FormaPagamento where upper(nome) like :nome", FormaPagamento.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
		
}
