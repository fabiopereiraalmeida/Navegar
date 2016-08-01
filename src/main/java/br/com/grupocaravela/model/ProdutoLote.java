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
@Table(name = "produto_lote")
public class ProdutoLote implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String lote;
	private Double valorCompra;
	private Double quantidade;
	private Date fabricacao;
	private Date validade;
	private Produto produto;
	
	private Empresa empresa;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "lote", length = 20)
	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	@Column(name = "valor_compra", precision = 11, scale = 2, nullable = false)
	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	@Column(name = "quantidade", precision = 11, scale = 2, nullable = false)
	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fabricacao")
	public Date getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(Date fabricacao) {
		this.fabricacao = fabricacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "validade")
	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	@ManyToOne
	@JoinColumn(name = "produto_id")
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		ProdutoLote other = (ProdutoLote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
