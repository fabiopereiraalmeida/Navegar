package br.com.grupocaravela.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Categoria;
import br.com.grupocaravela.service.CadastroCategoriaService;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroCategoriaService cadastroCategoriaService;
	
	private Categoria categoria;
	
	public CadastroCategoriaBean(){
		limpar();	
	}
	
	//@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando a conexao...");
		
		if (this.categoria == null) {
			limpar();
		}
				
		if (FacesUtil.isNotPostback()) { //Verifica se esta na mesma pagina para evitar uma nova consulta			
			//categoriaList = categorias.buscarCategorias();
			//unidadeList = unidades.buscarUnidades();						
		}
		
	}
	
	private void limpar(){
		categoria = new Categoria();
				 
	}
	
	public void salvar(){
		
		//this.categoria.setDataCadastro(dataAtual());
			
		try {
			this.categoria = cadastroCategoriaService.salvar(this.categoria);
			limpar();
			
			FacesUtil.addInfoMessage("Categoria salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public boolean isEditando() {
		return this.categoria.getId() != null;
	}
	
}
