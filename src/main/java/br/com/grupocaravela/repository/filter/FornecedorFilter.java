package br.com.grupocaravela.repository.filter;

import java.io.Serializable;

public class FornecedorFilter implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private String razaoSocial;
	private String fatasia;
	private String cnpj;
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getFatasia() {
		return fatasia;
	}
	public void setFatasia(String fatasia) {
		this.fatasia = fatasia;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
