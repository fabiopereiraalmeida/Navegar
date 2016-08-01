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

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "compra_detalhe")
public class CompraDetalhe implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double valorTotal;
	private CompraCabecalho compraCabecalho;
	private ProdutoLote produtoLote;
	
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
	@Min(0)
	@Column(name = "valor_total", nullable = false, precision = 11, scale = 2)
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "compra_cabecalho_id", nullable = false)
	public CompraCabecalho getCompraCabecalho() {
		return compraCabecalho;
	}

	public void setCompraCabecalho(CompraCabecalho compraCabecalho) {
		this.compraCabecalho = compraCabecalho;
	}

	@ManyToOne
	@JoinColumn(name = "produto_lote_id")
	public ProdutoLote getProdutoLote() {
		return produtoLote;
	}

	public void setProdutoLote(ProdutoLote produtoLote) {
		this.produtoLote = produtoLote;
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
		CompraDetalhe other = (CompraDetalhe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
