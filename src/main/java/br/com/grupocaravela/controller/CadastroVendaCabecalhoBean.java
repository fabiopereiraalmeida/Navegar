package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Cliente;
import br.com.grupocaravela.model.EnderecoEntrega;
import br.com.grupocaravela.model.FormaPagamento;
import br.com.grupocaravela.model.Usuario;
import br.com.grupocaravela.model.VendaCabecalho;
import br.com.grupocaravela.repository.Clientes;
import br.com.grupocaravela.repository.FormaPagamentos;
import br.com.grupocaravela.repository.Usuarios;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.service.VendaCabecalhoService;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroVendaCabecalhoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private FormaPagamentos formaPagamentos;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private VendaCabecalhoService vendaCabecalhoService;
	
	private VendaCabecalho vendaCabecalho;
	
	private List<Usuario> usuarioList;
	private List<FormaPagamento> formaPagamentoList;
	
	public CadastroVendaCabecalhoBean(){
		limpar();	
	}
	
		
	private void limpar(){
		vendaCabecalho = new VendaCabecalho();
		vendaCabecalho.setEnderecoEntrega(new EnderecoEntrega());
		
		usuarioList = new ArrayList<>();
		formaPagamentoList = new ArrayList<>();		
				 
	}
	
	public void inicializar(){
				
		if (this.vendaCabecalho == null) {
			limpar();
		}
		
		if (FacesUtil.isNotPostback()) {
			this.usuarioList = this.usuarios.buscarUsuarios();
			this.formaPagamentoList = this.formaPagamentos.buscarTodasFormasPagamento();
		}
		
	}
	
	public List<Cliente> completarCliente(String nome){
		return clientes.porNome(nome);
	}
	
	public void salvar(){
		
		try {
			this.vendaCabecalho = this.vendaCabecalhoService.salvar(this.vendaCabecalho);
			FacesUtil.addInfoMessage("Venda salva com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public VendaCabecalho getVendaCabecalho() {
		return vendaCabecalho;
	}
	
	public void setVendaCabecalho(VendaCabecalho vendaCabecalho) {
		this.vendaCabecalho = vendaCabecalho;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public List<FormaPagamento> getFormaPagamentoList() {
		return formaPagamentoList;
	}
		
	public boolean isEditando(){
		return this.vendaCabecalho.getId() != null;
	}
	
}
