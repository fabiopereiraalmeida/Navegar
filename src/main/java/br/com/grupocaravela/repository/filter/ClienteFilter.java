package br.com.grupocaravela.repository.filter;

import java.io.Serializable;

public class ClienteFilter implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private String nome;
	private String apelido;
	private String cpf;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
		
}
