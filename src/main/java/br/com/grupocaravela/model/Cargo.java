package br.com.grupocaravela.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "cargo")
public class Cargo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	
	private Boolean clientes;
	private Boolean categorias;
	private Boolean contasPagar;
	private Boolean contasReceber;
	private Boolean compras;
	private Boolean caixa;
	private Boolean fornecedores;
	private Boolean formasPagamento;	
	private Boolean produtos;
	private Boolean unidades;
	private Boolean usuarios;
	private Boolean vendas;
	
	private Empresa empresa;

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

	@NotNull
	@ManyToOne
	@JoinColumn(name = "empresa_id", nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Boolean getVendas() {
		return vendas;
	}

	public void setVendas(Boolean vendas) {
		this.vendas = vendas;
	}

	public Boolean getClientes() {
		return clientes;
	}

	public void setClientes(Boolean clientes) {
		this.clientes = clientes;
	}

	public Boolean getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Boolean fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Boolean getContasPagar() {
		return contasPagar;
	}

	public void setContasPagar(Boolean contasPagar) {
		this.contasPagar = contasPagar;
	}

	public Boolean getContasReceber() {
		return contasReceber;
	}

	public void setContasReceber(Boolean contasReceber) {
		this.contasReceber = contasReceber;
	}

	public Boolean getCompras() {
		return compras;
	}

	public void setCompras(Boolean compras) {
		this.compras = compras;
	}

	public Boolean getCaixa() {
		return caixa;
	}

	public void setCaixa(Boolean caixa) {
		this.caixa = caixa;
	}

	public Boolean getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(Boolean formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

	public Boolean getCategorias() {
		return categorias;
	}

	public void setCategorias(Boolean categorias) {
		this.categorias = categorias;
	}

	public Boolean getProdutos() {
		return produtos;
	}

	public void setProdutos(Boolean produtos) {
		this.produtos = produtos;
	}

	public Boolean getUnidades() {
		return unidades;
	}

	public void setUnidades(Boolean unidades) {
		this.unidades = unidades;
	}

	public Boolean getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Boolean usuarios) {
		this.usuarios = usuarios;
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
		Cargo other = (Cargo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
