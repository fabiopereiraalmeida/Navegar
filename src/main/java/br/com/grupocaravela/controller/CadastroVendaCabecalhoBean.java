package br.com.grupocaravela.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.grupocaravela.model.VendaCabecalho;

@Named
@ViewScoped
public class CadastroVendaCabecalhoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	private VendaCabecalho vendaCabecalho;
	
	public CadastroVendaCabecalhoBean(){
		limpar();	
	}
	
		
	private void limpar(){
		vendaCabecalho = new VendaCabecalho();
				 
	}
	
	public void salvar(){
		
	}

	public VendaCabecalho getVendaCabecalho() {
		return vendaCabecalho;
	}
}
