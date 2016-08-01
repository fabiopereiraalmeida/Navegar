package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Unidade;
import br.com.grupocaravela.repository.Unidades;
import br.com.grupocaravela.repository.filter.UnidadeFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUnidadesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Unidades unidades;
	
	private UnidadeFilter filtro;
	private List<Unidade> unidadesFiltrados;
	
	private Unidade unidadeSelecionado;
	
	public PesquisaUnidadesBean() {
		filtro = new UnidadeFilter();
	}
	
	public void excluir() {
		try {
			
			System.out.println("Passei no Excluir");
			
			unidades.remover(unidadeSelecionado);
			unidadesFiltrados.remove(unidadeSelecionado);
			
			FacesUtil.addInfoMessage("Unidade " + unidadeSelecionado.getNome() 
					+ " excluído com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar() {
		unidadesFiltrados = unidades.filtrados(filtro);
	}
	
	public List<Unidade> getUnidadesFiltrados() {
		return unidadesFiltrados;
	}

	public UnidadeFilter getFiltro() {
		return filtro;
	}

	public Unidade getUnidadeSelecionado() {
		return unidadeSelecionado;
	}

	public void setUnidadeSelecionado(Unidade unidadeSelecionado) {
		this.unidadeSelecionado = unidadeSelecionado;
	}
	
}