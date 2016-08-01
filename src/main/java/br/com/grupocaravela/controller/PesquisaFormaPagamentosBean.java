package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.FormaPagamento;
import br.com.grupocaravela.repository.FormaPagamentos;
import br.com.grupocaravela.repository.filter.FormaPagamentoFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFormaPagamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FormaPagamentos formaPagamentos;
	
	private FormaPagamentoFilter filtro;
	private List<FormaPagamento> formaPagamentosFiltrados;
	
	private FormaPagamento formaPagamentoSelecionado;
	
	public PesquisaFormaPagamentosBean() {
		filtro = new FormaPagamentoFilter();
	}
	
	public void excluir() {
		try {
			
			System.out.println("Passei no Excluir");
			
			formaPagamentos.remover(formaPagamentoSelecionado);
			formaPagamentosFiltrados.remove(formaPagamentoSelecionado);
			
			FacesUtil.addInfoMessage("FormaPagamento " + formaPagamentoSelecionado.getNome() 
					+ " excluído com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar() {
		formaPagamentosFiltrados = formaPagamentos.filtrados(filtro);
	}
	
	public List<FormaPagamento> getFormaPagamentosFiltrados() {
		return formaPagamentosFiltrados;
	}

	public FormaPagamentoFilter getFiltro() {
		return filtro;
	}

	public FormaPagamento getFormaPagamentoSelecionado() {
		return formaPagamentoSelecionado;
	}

	public void setFormaPagamentoSelecionado(FormaPagamento formaPagamentoSelecionado) {
		this.formaPagamentoSelecionado = formaPagamentoSelecionado;
	}
	
}