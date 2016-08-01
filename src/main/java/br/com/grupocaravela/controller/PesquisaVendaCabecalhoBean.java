package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.EstadoVenda;
import br.com.grupocaravela.model.VendaCabecalho;
import br.com.grupocaravela.repository.Categorias;
import br.com.grupocaravela.repository.EstadoVendas;
import br.com.grupocaravela.repository.VendasCabecalho;
import br.com.grupocaravela.repository.filter.EstadoVendaFilter;
import br.com.grupocaravela.repository.filter.VendaCabecalhoFilter;
import br.com.grupocaravela.util.cdi.CDIServiceLocator;
import br.com.grupocaravela.util.jpa.Transactional;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaVendaCabecalhoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private VendasCabecalho vendasCabecalho;
	
	@Inject
	private EstadoVendas estadoVendas;
			
	private VendaCabecalhoFilter filtro;
	private List<VendaCabecalho> vendaCabecalhoFiltrados;
	
	private List<EstadoVenda> estadoVendaList;	
	
	public PesquisaVendaCabecalhoBean() {
		filtro = new VendaCabecalhoFilter();
		vendaCabecalhoFiltrados = new ArrayList<>();
		
		//########################		
		estadoVendas = CDIServiceLocator.getBean(EstadoVendas.class); //Alternativa para o @Inject
		estadoVendaList = new ArrayList<>();
		estadoVendaList = estadoVendas.buscarEstadoVendas();
	}
	
	public void pesquisar(){
		vendaCabecalhoFiltrados = vendasCabecalho.filtrados(filtro);
	}
	
	public List<VendaCabecalho> getVendaCabecalhoFiltrados() {
		return vendaCabecalhoFiltrados;
	}

	public VendaCabecalhoFilter getFiltro() {
		return filtro;
	}

	public List<EstadoVenda> getEstadoVendaList() {
		return estadoVendaList;
	}
	
}
