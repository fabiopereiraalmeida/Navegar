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

import br.com.grupocaravela.model.Categoria;
import br.com.grupocaravela.model.Categoria;
import br.com.grupocaravela.model.Categoria;
import br.com.grupocaravela.model.Empresa;
import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.repository.filter.CategoriaFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jpa.Transactional;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}
	
	public List<Categoria> buscarCategorias() {
		//System.out.println("Passei aki 02");
		return manager.createQuery("FROM Categoria", Categoria.class).getResultList(); //(JPQL) Seleciona o objeto
	}
	
	public Categoria porNome(String nome) {
		try {
			return manager.createQuery("from Categoria where upper(nome) = :nome", Categoria.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Categoria guardar(Categoria categoria) {	
				
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		categoria.setEmpresa(empresa);
		
		return manager.merge(categoria);
		
	}
	
	@Transactional	//Qunado vai remover a partir do Controlador ".controller"
	public void remover(Categoria categoria) throws NegocioException{
		try {
			//System.out.println("Passei no try de excluir");
			categoria = porId(categoria.getId());
			manager.remove(categoria);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("A categoria não pode ser excluida!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> filtrados(CategoriaFilter filtro) {		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Categoria.class);
		
		//MatchMode = % - From nome WHERE nome LIKE '%fabio%'
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
}
