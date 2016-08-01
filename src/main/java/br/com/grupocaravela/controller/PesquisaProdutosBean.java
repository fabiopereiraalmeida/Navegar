package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.repository.Produtos;
import br.com.grupocaravela.repository.filter.ProdutoFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	
	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;
	
	private Produto produtoSelecionado;
	
	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}
	
	public void excluir() {
		try {
			
			System.out.println("Passei no Excluir");
			
			produtos.remover(produtoSelecionado);
			produtosFiltrados.remove(produtoSelecionado);
			
			FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getCodigo() 
					+ " excluído com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
	}
	
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
}