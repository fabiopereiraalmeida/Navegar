 package br.com.grupocaravela.repository.filter;

import java.io.Serializable;
import java.util.Date;

import br.com.grupocaravela.model.StatusVenda;

public class VendaCabecalhoFilter implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private Long codVendaDe;
	private Long codVendaAte;
	private Date dataVendaDe;
	private Date dataVendaAte;
	private String nomeUsuario;
	private String nomeCliente;
	private StatusVenda[] statuses;
	
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
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public StatusVenda[] getStatuses() {
		return statuses;
	}

	public void setStatuses(StatusVenda[] statuses) {
		this.statuses = statuses;
	}
	
	
}
