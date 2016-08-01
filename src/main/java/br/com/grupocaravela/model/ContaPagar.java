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

@Entity
@Table(name = "conta_pagar")
public class ContaPagar implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private CompraCabecalho compraCabecalho;
	private Date dataVencimento;
	private Date dataCriacao;
	private Date dataPagamento;
	private FormaPagamento formaPagamento;
	private Double valor;
	private String observacao;
	private Boolean pago;
	
	private Empresa empresa;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false, length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne
	@JoinColumn(name = "compra_cabecalho_id")
	public CompraCabecalho getCompraCabecalho() {
		return compraCabecalho;
	}

	public void setCompraCabecalho(CompraCabecalho compraCabecalho) {
		this.compraCabecalho = compraCabecalho;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento")
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id")
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Column(name = "valor", precision = 11, scale = 2)
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Column(name = "observacao", columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "pago")
	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	@ManyToOne
	@JoinColumn(name = "empresa_id")
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
		ContaPagar other = (ContaPagar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
