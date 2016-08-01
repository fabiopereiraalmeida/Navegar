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

import br.com.grupocaravela.model.EstadoVenda;
import br.com.grupocaravela.model.EstadoVenda;
import br.com.grupocaravela.model.EstadoVenda;
import br.com.grupocaravela.model.Empresa;
import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.repository.filter.EstadoVendaFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jpa.Transactional;

public class EstadoVendas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public EstadoVenda porId(Long id) {
		return manager.find(EstadoVenda.class, id);
	}
	
	public List<EstadoVenda> buscarEstadoVendas() {
		//System.out.println("Passei aki 02");
		return manager.createQuery("FROM EstadoVenda", EstadoVenda.class).getResultList(); //(JPQL) Seleciona o objeto
	}
	
	public EstadoVenda porNome(String nome) {
		try {
			return manager.createQuery("from EstadoVenda where upper(nome) = :nome", EstadoVenda.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public EstadoVenda guardar(EstadoVenda estadoVenda) {	
				
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		estadoVenda.setEmpresa(empresa);
		
		return manager.merge(estadoVenda);
		
	}
	
	@Transactional	//Qunado vai remover a partir do Controlador ".controller"
	public void remover(EstadoVenda estadoVenda) throws NegocioException{
		try {
			//System.out.println("Passei no try de excluir");
			estadoVenda = porId(estadoVenda.getId());
			manager.remove(estadoVenda);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("A estadoVenda não pode ser excluida!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EstadoVenda> filtrados(EstadoVendaFilter filtro) {		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(EstadoVenda.class);
		
		//MatchMode = % - From nome WHERE nome LIKE '%fabio%'
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
}
