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

@Entity
@Table(name = "produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String codigo;
	private String nome;
	private Double valorMinimoVenda;
	private Double valorDesejavelVenda;
	private Double margemLucroMinimo;
	private Double margemLucroDesejavel;
	private Double estoque;
	private Double estoqueMinimo;
	private Double estoqueDesejavel;
	private Double peso;
	private Unidade unidade;
	private Categoria categoria;
	
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
	@Size(max = 20)
	@Column(name = "codigo", length = 20, nullable = false)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@NotBlank
	@Size(max = 45)
	@Column(name = "nome", length = 45, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull(message = "é obrigatório")
	@Min(0)
	@Column(name = "valor_minimo_venda", precision = 11, scale = 2, nullable = false)
	public Double getValorMinimoVenda() {
		return valorMinimoVenda;
	}

	public void setValorMinimoVenda(Double valorMinimoVenda) {
		this.valorMinimoVenda = valorMinimoVenda;
	}

	@NotNull 
	@Min(0)
	@Column(name = "valor_desejavel_venda", precision = 11, scale = 2, nullable = false)
	public Double getValorDesejavelVenda() {
		return valorDesejavelVenda;
	}

	public void setValorDesejavelVenda(Double valorDesejavelVenda) {
		this.valorDesejavelVenda = valorDesejavelVenda;
	}

	@NotNull 
	@Min(0)
	@Column(name = "margem_lucro_minimo", precision = 11, scale = 3, nullable = false)
	public Double getMargemLucroMinimo() {
		return margemLucroMinimo;
	}

	public void setMargemLucroMinimo(Double margemLucroMinimo) {
		this.margemLucroMinimo = margemLucroMinimo;
	}

	@NotNull 
	@Min(0)
	@Column(name = "margem_lucro_desejavel", precision = 11, scale = 3, nullable = false)
	public Double getMargemLucroDesejavel() {
		return margemLucroDesejavel;
	}

	public void setMargemLucroDesejavel(Double margemLucroDesejavel) {
		this.margemLucroDesejavel = margemLucroDesejavel;
	}

	@NotNull 
	@Min(0)
	@Column(name = "estoque", precision = 11, scale = 3, nullable = false)
	public Double getEstoque() {
		return estoque;
	}

	public void setEstoque(Double estoque) {
		this.estoque = estoque;
	}

	@NotNull 
	@Min(0)
	@Column(name = "estoque_minimo", precision = 11, scale = 3, nullable = false)
	public Double getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Double estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	@NotNull 
	@Min(0)
	@Column(name = "estoque_desejavel", precision = 11, scale = 3, nullable = false)
	public Double getEstoqueDesejavel() {
		return estoqueDesejavel;
	}

	public void setEstoqueDesejavel(Double estoqueDesejavel) {
		this.estoqueDesejavel = estoqueDesejavel;
	}

	@Column(name = "peso", precision = 11, scale = 3)
	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "unidade_id", nullable = false)
	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
		
	@NotNull
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
