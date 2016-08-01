package br.com.grupocaravela.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.mysql.fabric.FabricCommunicationException;

@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Integer numeroParcelas;
	private Integer numeroDias;	
	private Double juros;
	private Double valorMinimo;
	private String observacao;
	
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
	@Min(1)
	@Column(name = "numero_parcelas", precision = 3, nullable = false)
	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	@NotNull
	@Min(0)
	@Column(name = "numero_dias", precision = 3, nullable = false)
	public Integer getNumeroDias() {
		return numeroDias;
	}

	public void setNumeroDias(Integer numeroDias) {
		this.numeroDias = numeroDias;
	}

	@NotNull
	@Min(0)
	@Column(name = "juros", nullable = false, precision = 3, scale = 2)
	public Double getJuros() {
		return juros;
	}

	public void setJuros(Double juros) {
		this.juros = juros;
	}
	
	@NotNull
	@Min(0)
	@Column(name = "valor_minimo", nullable = false, precision = 3, scale = 2)
	public Double getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(Double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	@Column(name = "observacao", columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		FormaPagamento other = (FormaPagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
