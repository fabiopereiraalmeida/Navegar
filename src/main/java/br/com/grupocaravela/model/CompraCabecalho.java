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
import javax.validation.constraints.Size;

@Entity
@Table(name = "compra_cabecalho")
public class CompraCabecalho implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numeroNota;
	private Date dataCompra;
	private Date dataRecebimento;
	private Boolean finalizada;
	private Boolean entregue;	
	private Double valorCompra;
	private Usuario usuario;
	
	private List<CompraDetalhe> compraDetalhes = new ArrayList<>();
	private List<Caixa> caixaList = new ArrayList<>();
	
	private Empresa empresa;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Size(max = 20)
	@Column(name = "numero_nota", length = 20)
	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_compra", nullable = false)	
	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_recebimento")	
	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	@Column(name = "finalizada")
	public Boolean getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}

	@Column(name = "entregue")
	public Boolean getEntregue() {
		return entregue;
	}

	public void setEntregue(Boolean entregue) {
		this.entregue = entregue;
	}

	@NotNull
	@Min(0)
	@Column(name = "valor_compra", nullable = false, precision = 11, scale = 2)
	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
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

	@OneToMany(mappedBy = "compraCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<CompraDetalhe> getCompraDetalhes() {
		return compraDetalhes;
	}

	public void setCompraDetalhes(List<CompraDetalhe> compraDetalhes) {
		this.compraDetalhes = compraDetalhes;
	}
	
	@OneToMany(mappedBy = "compraCabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Caixa> getCaixaList() {
		return caixaList;
	}

	public void setCaixaList(List<Caixa> caixaList) {
		this.caixaList = caixaList;
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
		CompraCabecalho other = (CompraCabecalho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
