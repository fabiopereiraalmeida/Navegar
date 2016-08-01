package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Categoria;
import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.model.Unidade;
import br.com.grupocaravela.repository.Categorias;
import br.com.grupocaravela.repository.Unidades;
import br.com.grupocaravela.service.CadastroProdutoService;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;
	
	@Inject
	private Unidades unidades;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;
	
	private List<Categoria> categoriaList;
	private List<Unidade> unidadeList;

	public CadastroProdutoBean(){
		limpar();	
	}
	
	//@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando a conexao...");
		
		if (this.produto == null) {
			limpar();
		}
				
		if (FacesUtil.isNotPostback()) { //Verifica se esta na mesma pagina para evitar uma nova consulta			
			categoriaList = categorias.buscarCategorias();
			unidadeList = unidades.buscarUnidades();						
		}
		
	}
	
	private void limpar(){
		produto = new Produto();
		categoriaList = new ArrayList<>();
		unidadeList = new ArrayList<>();
		 
	}
	
	public void salvar(){
		
		try {
			this.produto = cadastroProdutoService.salvar(this.produto);
			limpar();
			
			FacesUtil.addInfoMessage("Produto salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategoriaList() {
						
		return categoriaList;
		
	}

	public List<Unidade> getUnidadeList() {
		
		return unidadeList;
	}
	
	public boolean isEditando() {
		return this.produto.getId() != null;
	}
	
}
