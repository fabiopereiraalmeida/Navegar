package br.com.grupocaravela.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Cargo;
import br.com.grupocaravela.service.CadastroCargoService;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCargoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroCargoService cadastroCargoService;
	
	private Cargo cargo;
	
	public CadastroCargoBean(){
		limpar();	
	}
	
	//@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando a conexao...");
		
		if (this.cargo == null) {
			limpar();
		}
				
		if (FacesUtil.isNotPostback()) { //Verifica se esta na mesma pagina para evitar uma nova consulta			
			//cargoList = cargos.buscarCargos();
			//unidadeList = unidades.buscarUnidades();						
		}
		
	}
	
	private void limpar(){
		cargo = new Cargo();
				 
	}
	
	public void salvar(){
		
		//this.cargo.setDataCadastro(dataAtual());
			
		try {
			this.cargo = cadastroCargoService.salvar(this.cargo);
			limpar();
			
			FacesUtil.addInfoMessage("Cargo salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public boolean isEditando() {
		return this.cargo.getId() != null;
	}
	
}
