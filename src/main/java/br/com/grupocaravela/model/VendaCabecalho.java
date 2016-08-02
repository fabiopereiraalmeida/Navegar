package br.com.grupocaravela.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "venda_cabecalho")
public class VendaCabecalho implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long codVenda;
	private Cliente cliente;
	private Double valorParcial;
	private Double valorDesconto;
	private Double valorTotal;
	private Date dataVenda;
	private Usuario usuario;
	private FormaPagamento formaPagamento;
	
	private List<VendaDetalhe> vendaCabecalhoList = new ArrayList<>();
	private List<ContaReceber> contaReceberList = new ArrayList<>();
	
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
	@JoinColumn(name = "cod_venda", nullable = false)
	public Long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(Long codVenda) {
		this.codVenda = codVenda;
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

	@NotNull
	@Min(0)
	@Column(name = "valor_parcial", precision = 11, scale = 2, nullable = false)
	public Double getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(Double valorParcial) {
		this.valorParcial = valorParcial;
	}

	@NotNull
	@Min(0)
	@Column(name = "valor_desconto", precision = 11, scale = 2, nullable = false)
	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	@NotNull
	@Min(0)
	@Column(name = "valor_total", precision = 11, scale = 2, nullable = false)
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_venda", nullable = false)
	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id", nullable = false)
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	@OneToMany(mappedBy = "vendaCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<VendaDetalhe> getVendaCabecalhoList() {
		return vendaCabecalhoList;
	}

	public void setVendaCabecalhoList(List<VendaDetalhe> vendaCabecalhoList) {
		this.vendaCabecalhoList = vendaCabecalhoList;
	}
	
	@OneToMany(mappedBy = "vendaCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ContaReceber> getContaReceberList() {
		return contaReceberList;
	}

	public void setContaReceberList(List<ContaReceber> contaReceberList) {
		this.contaReceberList = contaReceberList;
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
		VendaCabecalho other = (VendaCabecalho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
