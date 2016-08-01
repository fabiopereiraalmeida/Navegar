package br.com.grupocaravela.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Fornecedor;
import br.com.grupocaravela.service.CadastroFornecedorService;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroFornecedorService cadastroFornecedorService;
	
	private Fornecedor fornecedor;
	
	public CadastroFornecedorBean(){
		limpar();	
	}
	
	//@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando a conexao...");
		
		if (this.fornecedor == null) {
			limpar();
		}
				
		if (FacesUtil.isNotPostback()) { //Verifica se esta na mesma pagina para evitar uma nova consulta			
			//categoriaList = categorias.buscarCategorias();
			//unidadeList = unidades.buscarUnidades();						
		}
		
	}
	
	private void limpar(){
		fornecedor = new Fornecedor();
		 
	}
	
	public void salvar(){
		
		//this.fornecedor.setDataCadastro(dataAtual());
			
		try {
			this.fornecedor = cadastroFornecedorService.salvar(this.fornecedor);
			limpar();
			
			FacesUtil.addInfoMessage("Fornecedor salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public boolean isEditando() {
		return this.fornecedor.getId() != null;
	}
	
	private java.util.Date dataAtual() {

		java.util.Date hoje = new java.util.Date();
		// java.util.Date hoje = Calendar.getInstance().getTime();
		return hoje;
	}
}
