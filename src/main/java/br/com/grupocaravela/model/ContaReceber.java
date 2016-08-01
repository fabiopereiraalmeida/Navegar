package br.com.grupocaravela.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "conta_receber")
public class ContaReceber implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Cliente cliente;
	private VendaCabecalho vendaCabecalho;
	private Double valorDevido;
	private Date vencimento;
	private Date dataPagamento;
	private Boolean quitada;
	
	private Empresa empresa;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@ManyToOne
	@JoinColumn(name = "venda_cabecalho_id")
	public VendaCabecalho getVendaCabecalho() {
		return vendaCabecalho;
	}

	public void setVendaCabecalho(VendaCabecalho vendaCabecalho) {
		this.vendaCabecalho = vendaCabecalho;
	}

	@NotNull
	@Min(0)
	@Column(name = "valor_devido", nullable = false, precision = 11, scale = 2)
	public Double getValorDevido() {
		return valorDevido;
	}

	public void setValorDevido(Double valorDevido) {
		this.valorDevido = valorDevido;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "vencimento", nullable = false)
	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "quitada")
	public Boolean getQuitada() {
		return quitada;
	}

	public void setQuitada(Boolean quitada) {
		this.quitada = quitada;
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
		ContaReceber other = (ContaReceber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
