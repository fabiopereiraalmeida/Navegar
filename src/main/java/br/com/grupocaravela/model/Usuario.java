package br.com.grupocaravela.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private String telefone;
	
	private Empresa empresa;
	
	private List<Caixa> caixaList = new ArrayList<>();
	private List<VendaCabecalho> vendaCabecalhoList = new ArrayList<>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank
	@Size(max = 45)
	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "login", nullable = false, length = 20)
	public String getLogin() {
		return login;
	}
		
	public void setLogin(String login) {
		this.login = login;
	}
	
	@NotBlank
	@Size(max = 16)
	@Column(name = "senha", nullable = false, length = 16)
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Size(max = 70)
	@Column(name = "email", nullable = false, length = 70)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Size(max = 16)
	@Column(name = "telefone", nullable = false, length = 16) //(77)_9.8100-0781 
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "empresa_id", nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)	
	public List<Caixa> getCaixaList() {
		return caixaList;
	}

	public void setCaixaList(List<Caixa> caixaList) {
		this.caixaList = caixaList;
	}

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<VendaCabecalho> getVendaCabecalhoList() {
		return vendaCabecalhoList;
	}

	public void setVendaCabecalhoList(List<VendaCabecalho> vendaCabecalhoList) {
		this.vendaCabecalhoList = vendaCabecalhoList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
