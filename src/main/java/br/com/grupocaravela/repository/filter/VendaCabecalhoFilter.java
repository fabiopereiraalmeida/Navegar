 package br.com.grupocaravela.repository.filter;

import java.io.Serializable;
import java.util.Date;

import br.com.grupocaravela.model.EstadoVenda;

public class VendaCabecalhoFilter implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private Long codVendaDe;
	private Long codVendaAte;
	private Date dataVendaDe;
	private Date dataVendaAte;
	private String nomeVendedor;
	private String nomeCliente;
	private EstadoVenda[] estadosVendas;
	
	public Long getCodVendaDe() {
		return codVendaDe;
	}
	
	public void setCodVendaDe(Long codVendaDe) {
		this.codVendaDe = codVendaDe;
	}
	
	public Long getCodVendaAte() {
		return codVendaAte;
	}
	
	public void setCodVendaAte(Long codVendaAte) {
		this.codVendaAte = codVendaAte;
	}
	
	public Date getDataVendaDe() {
		return dataVendaDe;
	}
	
	public void setDataVendaDe(Date dataVendaDe) {
		this.dataVendaDe = dataVendaDe;
	}
	
	public Date getDataVendaAte() {
		return dataVendaAte;
	}
	
	public void setDataVendaAte(Date dataVendaAte) {
		this.dataVendaAte = dataVendaAte;
	}
	
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public EstadoVenda[] getEstadosVendas() {
		return estadosVendas;
	}
	
		public void setEstadosVendas(EstadoVenda[] estadosVendas) {
		this.estadosVendas = estadosVendas;
	}
		
}
