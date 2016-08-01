package br.com.grupocaravela.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Unidade;
import br.com.grupocaravela.service.CadastroUnidadeService;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUnidadeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroUnidadeService cadastroUnidadeService;
	
	private Unidade unidade;
	
	public CadastroUnidadeBean(){
		limpar();	
	}
	
	//@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando a conexao...");
		
		if (this.unidade == null) {
			limpar();
		}
				
		if (FacesUtil.isNotPostback()) { //Verifica se esta na mesma pagina para evitar uma nova consulta			
			//unidadeList = unidades.buscarUnidades();
			//unidadeList = unidades.buscarUnidades();						
		}
		
	}
	
	private void limpar(){
		unidade = new Unidade();
				 
	}
	
	public void salvar(){
		
		//this.unidade.setDataCadastro(dataAtual());
			
		try {
			this.unidade = cadastroUnidadeService.salvar(this.unidade);
			limpar();
			
			FacesUtil.addInfoMessage("Unidade salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public boolean isEditando() {
		return this.unidade.getId() != null;
	}
	
}
