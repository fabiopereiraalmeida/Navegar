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

import br.com.grupocaravela.model.Unidade;
import br.com.grupocaravela.model.Unidade;
import br.com.grupocaravela.model.Unidade;
import br.com.grupocaravela.model.Empresa;
import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.repository.filter.UnidadeFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jpa.Transactional;

public class Unidades implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Unidade porId(Long id) {
		return manager.find(Unidade.class, id);
	}
	
	public List<Unidade> buscarUnidades() {
		//System.out.println("Passei aki 02");
		return manager.createQuery("FROM Unidade", Unidade.class).getResultList(); //(JPQL) Seleciona o objeto
	}
	
	public Unidade porNome(String nome) {
		try {
			return manager.createQuery("from Unidade where upper(nome) = :nome", Unidade.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Unidade guardar(Unidade unidade) {	
				
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		unidade.setEmpresa(empresa);
		
		return manager.merge(unidade);
		
	}
	
	@Transactional	//Qunado vai remover a partir do Controlador ".controller"
	public void remover(Unidade unidade) throws NegocioException{
		try {
			//System.out.println("Passei no try de excluir");
			unidade = porId(unidade.getId());
			manager.remove(unidade);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("A unidade não pode ser excluida!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidade> filtrados(UnidadeFilter filtro) {		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Unidade.class);
		
		//MatchMode = % - From nome WHERE nome LIKE '%fabio%'
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
}
