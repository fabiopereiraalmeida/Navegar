package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Categoria;
import br.com.grupocaravela.repository.Categorias;
import br.com.grupocaravela.repository.filter.CategoriaFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCategoriasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;
	
	private CategoriaFilter filtro;
	private List<Categoria> categoriasFiltrados;
	
	private Categoria categoriaSelecionado;
	
	public PesquisaCategoriasBean() {
		filtro = new CategoriaFilter();
	}
	
	public void excluir() {
		try {
			
			System.out.println("Passei no Excluir");
			
			categorias.remover(categoriaSelecionado);
			categoriasFiltrados.remove(categoriaSelecionado);
			
			FacesUtil.addInfoMessage("Categoria " + categoriaSelecionado.getNome() 
					+ " excluído com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar() {
		categoriasFiltrados = categorias.filtrados(filtro);
	}
	
	public List<Categoria> getCategoriasFiltrados() {
		return categoriasFiltrados;
	}

	public CategoriaFilter getFiltro() {
		return filtro;
	}

	public Categoria getCategoriaSelecionado() {
		return categoriaSelecionado;
	}

	public void setCategoriaSelecionado(Categoria categoriaSelecionado) {
		this.categoriaSelecionado = categoriaSelecionado;
	}
	
}