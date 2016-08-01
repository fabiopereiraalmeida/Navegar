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

import br.com.grupocaravela.model.Cargo;
import br.com.grupocaravela.model.Cargo;
import br.com.grupocaravela.model.Cargo;
import br.com.grupocaravela.model.Empresa;
import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.repository.filter.CargoFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jpa.Transactional;

public class Cargos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Cargo porId(Long id) {
		return manager.find(Cargo.class, id);
	}
	
	public List<Cargo> buscarCargos() {
		//System.out.println("Passei aki 02");
		return manager.createQuery("FROM Cargo", Cargo.class).getResultList(); //(JPQL) Seleciona o objeto
	}
	
	public Cargo porNome(String nome) {
		try {
			return manager.createQuery("from Cargo where upper(nome) = :nome", Cargo.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Cargo guardar(Cargo cargo) {	
				
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		cargo.setEmpresa(empresa);
		
		return manager.merge(cargo);
		
	}
	
	@Transactional	//Qunado vai remover a partir do Controlador ".controller"
	public void remover(Cargo cargo) throws NegocioException{
		try {
			//System.out.println("Passei no try de excluir");
			cargo = porId(cargo.getId());
			manager.remove(cargo);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O cargo não pode ser excluido!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargo> filtrados(CargoFilter filtro) {		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cargo.class);
		
		//MatchMode = % - From nome WHERE nome LIKE '%fabio%'
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
}
