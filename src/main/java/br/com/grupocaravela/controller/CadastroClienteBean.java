package br.com.grupocaravela.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Cliente;
import br.com.grupocaravela.service.CadastroClienteService;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	private Cliente cliente;
	
	public CadastroClienteBean(){
		limpar();	
	}
	
	//@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando a conexao...");
		
		if (this.cliente == null) {
			limpar();
		}
				
		if (FacesUtil.isNotPostback()) { //Verifica se esta na mesma pagina para evitar uma nova consulta			
			//categoriaList = categorias.buscarCategorias();
			//unidadeList = unidades.buscarUnidades();						
		}
		
	}
	
	private void limpar(){
		cliente = new Cliente();
		cliente.setDataCadastro(dataAtual());
		 
	}
	
	public void salvar(){
		
		//this.cliente.setDataCadastro(dataAtual());
			
		try {
			this.cliente = cadastroClienteService.salvar(this.cliente);
			limpar();
			
			FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean isEditando() {
		return this.cliente.getId() != null;
	}
	
	private java.util.Date dataAtual() {

		java.util.Date hoje = new java.util.Date();
		// java.util.Date hoje = Calendar.getInstance().getTime();
		return hoje;
	}
}
