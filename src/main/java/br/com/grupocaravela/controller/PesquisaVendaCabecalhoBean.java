package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.StatusVenda;
import br.com.grupocaravela.model.VendaCabecalho;
import br.com.grupocaravela.repository.VendasCabecalho;
import br.com.grupocaravela.repository.filter.VendaCabecalhoFilter;

@Named
@ViewScoped
public class PesquisaVendaCabecalhoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private VendasCabecalho vendasCabecalho;
	
				
	private VendaCabecalhoFilter filtro;
	private List<VendaCabecalho> vendaCabecalhoFiltrados;
	
	
	public PesquisaVendaCabecalhoBean() {
		filtro = new VendaCabecalhoFilter();
		vendaCabecalhoFiltrados = new ArrayList<>();
				
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

	public StatusVenda[] getStatuses(){
		 return StatusVenda.values();
	}
	
}
