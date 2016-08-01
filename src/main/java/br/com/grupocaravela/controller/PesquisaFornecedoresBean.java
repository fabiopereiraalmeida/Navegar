package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Fornecedor;
import br.com.grupocaravela.repository.Fornecedores;
import br.com.grupocaravela.repository.filter.FornecedorFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFornecedoresBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Fornecedores fornecedors;
	
	private FornecedorFilter filtro;
	private List<Fornecedor> fornecedorsFiltrados;
	
	private Fornecedor fornecedorSelecionado;
	
	public PesquisaFornecedoresBean() {
		filtro = new FornecedorFilter();
	}
	
	public void excluir() {
		try {
			
			System.out.println("Passei no Excluir");
			
			fornecedors.remover(fornecedorSelecionado);
			fornecedorsFiltrados.remove(fornecedorSelecionado);
			
			FacesUtil.addInfoMessage("Fornecedor " + fornecedorSelecionado.getRazaoSocial() 
					+ " excluído com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar() {
		fornecedorsFiltrados = fornecedors.filtrados(filtro);
	}
	
	public List<Fornecedor> getFornecedorsFiltrados() {
		return fornecedorsFiltrados;
	}

	public FornecedorFilter getFiltro() {
		return filtro;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}
	
}