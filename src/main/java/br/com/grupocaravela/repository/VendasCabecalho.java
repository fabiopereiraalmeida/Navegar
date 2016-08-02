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
import br.com.grupocaravela.model.VendaCabecalho;
import br.com.grupocaravela.repository.filter.CategoriaFilter;
import br.com.grupocaravela.repository.filter.VendaCabecalhoFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jpa.Transactional;

public class VendasCabecalho implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public VendaCabecalho porId(Long id) {
		return manager.find(VendaCabecalho.class, id);
	}
	
	public List<VendaCabecalho> buscarVendasCabecalho() {
		//System.out.println("Passei aki 02");
		return manager.createQuery("FROM VendaCabecalho", VendaCabecalho.class).getResultList(); //(JPQL) Seleciona o objeto
	}
	/*
	public Categoria porNome(String nome) {
		try {
			return manager.createQuery("from Categoria where upper(nome) = :nome", Categoria.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	*/
	public VendaCabecalho guardar(VendaCabecalho vendaCabecalho) {	
				
		Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
		vendaCabecalho.setEmpresa(empresa);
		
		return manager.merge(vendaCabecalho);
		
	}
	
	@Transactional	//Qunado vai remover a partir do Controlador ".controller"
	public void remover(VendaCabecalho vendaCabecalho) throws NegocioException{
		try {
			//System.out.println("Passei no try de excluir");
			vendaCabecalho = porId(vendaCabecalho.getId());
			manager.remove(vendaCabecalho);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("A VendaCabecalho não pode ser excluida!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<VendaCabecalho> filtrados(VendaCabecalhoFilter filtro) {		
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(VendaCabecalho.class)
				//fazemos uma associação (join) com cliente e nomeamos de "c"
				.createAlias("cliente", "c")
				//fazemos uma associação (join) com vendedor (usuario) e nomeamos de "v"
				.createAlias("usuario", "u");
		
		if (filtro.getCodVendaDe() != null) {
			//cod deve ser maior ou igual (ge = greate or equals) a filtro.codVendaDe
			criteria.add(Restrictions.ge("codVenda", filtro.getCodVendaDe()));
		}
		
		if (filtro.getCodVendaAte() != null) {
			//cod deve ser menor ou igual (le = lower or equals) a filtro.codVendaAte
			criteria.add(Restrictions.le("codVenda", filtro.getCodVendaAte()));
		}
		
		if (filtro.getDataVendaDe() != null) {
			criteria.add(Restrictions.ge("dataVenda", filtro.getDataVendaDe()));
		}
		
		if (filtro.getDataVendaAte() != null) {
			criteria.add(Restrictions.le("dataVenda", filtro.getDataVendaAte()));
		}
		
		if (StringUtils.isNotBlank(filtro.getNomeCliente())) {
			//acessamos o nome do cliente associado ao pedido pelo alias "c", criado anteriormente
			criteria.add(Restrictions.ilike("c.nome", filtro.getNomeCliente(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filtro.getNomeUsuario())) {
			//acessamos o nome do vendedor associado ao pedido pelo alias "v", criado anteriormente
			criteria.add(Restrictions.ilike("u.nome", filtro.getNomeUsuario(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getStatuses()!= null && filtro.getStatuses().length > 0) {
			//adicionamos uma restrição "in", passando um array de constantes da enum StatusPedido
			criteria.add(Restrictions.in("status", filtro.getStatuses()));
		}
		
		return criteria.addOrder(Order.asc("id")).list();
	}
	
}
