package br.com.grupocaravela.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "venda_cabecalho")
public class VendaCabecalho implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long codVenda;
	private String observacao;
	private Cliente cliente;
	private Double frete = 0.0;
	private Double valorParcial = 0.0;
	private Double valorDesconto = 0.0;
	private Double valorTotal = 0.0;
	private Date dataVenda;
	private Date dataEntrega;
	private Usuario usuario;
	private FormaPagamento formaPagamento;
	private StatusVenda status = StatusVenda.ABERTO;
	private EnderecoEntrega enderecoEntrega;
	
	private List<VendaDetalhe> vendaDetalheList = new ArrayList<>();
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
	
	@JoinColumn(name = "cod_venda")
	public Long getCodVenda() {
		return codVenda;
	}

	public void setCodVenda(Long codVenda) {
		this.codVenda = codVenda;
	}
	
	@Column(columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
	@Column(name = "frete", precision = 11, scale = 2, nullable = false)
	public Double getFrete() {
		return frete;
	}

	public void setFrete(Double frete) {
		this.frete = frete;
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
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega")
	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
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
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}

	@Embedded
	public EnderecoEntrega getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	
	@OneToMany(mappedBy = "vendaCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<VendaDetalhe> getVendaDetalheList() {
		return vendaDetalheList;
	}

	public void setVendaDetalheList(List<VendaDetalhe> vendaDetalheList) {
		this.vendaDetalheList = vendaDetalheList;
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

	@Transient
	public Double getCalculoValorParcial(){
		
		this.valorParcial = ((this.getValorTotal() - this.getFrete()) + (this.getValorDesconto()));
		return valorParcial;
	}
	
	public void recalcularValorTotal() {
		
		Double total = 0.0;
		
		total = ((total + this.frete) - this.valorDesconto);
		
		for (VendaDetalhe vDetalhe : this.getVendaDetalheList()) {
			
			if (vDetalhe.getProduto() != null && vDetalhe.getProduto().getId() != null) {
				//vDetalhe.calcularTotalItem();
				total = total + vDetalhe.getValorTotal();
			}			
		}
		this.setValorParcial((total - this.getFrete()) + this.getValorDesconto());
		this.setValorTotal(total);
		
	}

	public void adicionarItemVazio() {
		
		if (this.isAberto()) {
			
		}
		
		Produto produto = new Produto();
		
		VendaDetalhe vDetalhe = new VendaDetalhe();
		vDetalhe.setProduto(produto);
		vDetalhe.setQuantidade(1.0);
		vDetalhe.setVendaCabecalho(this);
		
		this.getVendaDetalheList().add(0, vDetalhe);
	}

	@Transient
	public boolean isAberto() {
		return StatusVenda.ABERTO.equals(this.getStatus());
	}
	
	@Transient
	public boolean isNovo(){
		return this.getId() == null;
	}
	
	@Transient
	public boolean isExistente(){ //é o contrario de novo
		return !isNovo();
	}

	public void removerItemVazio() {
		VendaDetalhe vDetalhePrimeiro = this.getVendaDetalheList().get(0);
		
		if (vDetalhePrimeiro != null && vDetalhePrimeiro.getProduto().getId() == null) {
			this.getVendaDetalheList().remove(0);
		}
		
	}

	@Transient
	public boolean isValorTotalNegativo() {
		
		if (this.getValorTotal() < 0) {
			return true;
		}else{
			return false;
		}		
	}
		
}
