package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.EstadoVenda;
import br.com.grupocaravela.repository.EstadoVendas;
import br.com.grupocaravela.repository.filter.EstadoVendaFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEstadoVendasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstadoVendas estadoVendas;
	
	private EstadoVendaFilter filtro;
	private List<EstadoVenda> estadoVendasFiltrados;
	
	private EstadoVenda estadoVendaSelecionado;
	
	public PesquisaEstadoVendasBean() {
		filtro = new EstadoVendaFilter();
	}
	
	public void excluir() {
		try {
			
			System.out.println("Passei no Excluir");
			
			estadoVendas.remover(estadoVendaSelecionado);
			estadoVendasFiltrados.remove(estadoVendaSelecionado);
			
			FacesUtil.addInfoMessage("EstadoVenda " + estadoVendaSelecionado.getNome() 
					+ " excluído com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar() {
		estadoVendasFiltrados = estadoVendas.filtrados(filtro);
	}
	
	public List<EstadoVenda> getEstadoVendasFiltrados() {
		return estadoVendasFiltrados;
	}

	public EstadoVendaFilter getFiltro() {
		return filtro;
	}

	public EstadoVenda getEstadoVendaSelecionado() {
		return estadoVendaSelecionado;
	}

	public void setEstadoVendaSelecionado(EstadoVenda estadoVendaSelecionado) {
		this.estadoVendaSelecionado = estadoVendaSelecionado;
	}
	
	
}