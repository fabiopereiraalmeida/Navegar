package br.com.grupocaravela.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class EnderecoEntrega implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pessoa;
	private String endereco;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	private String cep;
	private String telefone;
	
	@Size(max = 50)
	@Column(name = "entrega_pessoa", length = 50)
	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}

	@Size(max = 150)
	@Column(name = "entrega_endereco", length = 150)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Size(max = 6)
	@Column(name = "entrega_numero", length = 6)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Size(max = 50)
	@Column(name = "entrega_complemento", length = 50)
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Size(max = 60)
	@Column(name = "entrega_cidade", length = 60)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Size(max = 2)
	@Column(name = "entrega_uf", length = 2)
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Size(max = 12)
	@Column(name = "entrega_cep", length = 12)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Size(max = 12)
	@Column(name = "entrega_telefone", length = 12)
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
