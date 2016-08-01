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
@Table(name = "caixa")
public class Caixa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Double valor;
	private Date data;
	private Usuario usuario;
	private CompraCabecalho compraCabecalho;

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
	@Column(name = "valor", nullable = false, precision = 11, scale = 2)
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
	
	@ManyToOne
	@JoinColumn(name = "compra_cabecalho_id")
	public CompraCabecalho getCompraCabecalho() {
		return compraCabecalho;
	}

	public void setCompraCabecalho(CompraCabecalho compraCabecalho) {
		this.compraCabecalho = compraCabecalho;
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
		Caixa other = (Caixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
