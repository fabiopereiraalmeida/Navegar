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
import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.repository.filter.ProdutoFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jpa.Transactional;

public class Produtos implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto guardar(Produto produto) {	
		/*
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		produto.setEmpresa(empresa);
		
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();
		
		produto = manager.merge(produto);
		
		trx.commit();
		
		return produto;
		*/
		
		//System.out.println("Passei no merge");
		//System.out.println("Produto: " + produto.getNome());
		
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		produto.setEmpresa(empresa);
		//System.out.println("empresa: " + produto.getEmpresa().getRazaoSocial());
		
		return manager.merge(produto);
		
	}
	
	@Transactional	//Qunado vai remover a partir do Controlador ".controller"
	public void remover(Produto produto) throws NegocioException{
		try {
			//System.out.println("Passei no try de excluir");
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O produto não pode ser excluido!");
		}
	}
	
	public Produto porCodigo(String codigo) {
		try {
			return manager.createQuery("from Produto where upper(codigo) = :codigo", Produto.class)
				.setParameter("codigo", codigo.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filtro) {		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		if (StringUtils.isNotBlank(filtro.getCodigo())) {
			criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));			
		}
		
		//MatchMode = % - From nome WHERE nome LIKE '%fabio%'
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}
	
	public List<Produto> porNome(String nome) {
		return this.manager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
}
