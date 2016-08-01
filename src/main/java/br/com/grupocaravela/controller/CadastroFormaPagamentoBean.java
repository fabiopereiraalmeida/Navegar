package br.com.grupocaravela.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.FormaPagamento;
import br.com.grupocaravela.service.CadastroFormaPagamentoService;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFormaPagamentoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	private FormaPagamento formaPagamento;
	
	public CadastroFormaPagamentoBean(){
		limpar();	
	}
	
	//@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando a conexao...");
		
		if (this.formaPagamento == null) {
			limpar();
		}
				
		if (FacesUtil.isNotPostback()) { //Verifica se esta na mesma pagina para evitar uma nova consulta			
			//categoriaList = categorias.buscarCategorias();
			//unidadeList = unidades.buscarUnidades();						
		}
		
	}
	
	private void limpar(){
		formaPagamento = new FormaPagamento();
				 
	}
	
	public void salvar(){
		
		try {
			this.formaPagamento = cadastroFormaPagamentoService.salvar(this.formaPagamento);
			limpar();
			
			FacesUtil.addInfoMessage("FormaPagamento salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public boolean isEditando() {
		return this.formaPagamento.getId() != null;
	}
	
}
