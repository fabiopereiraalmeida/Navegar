package br.com.grupocaravela.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.EstadoVenda;
import br.com.grupocaravela.service.CadastroEstadoVendaService;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEstadoVendaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroEstadoVendaService cadastroEstadoVendaService;
	
	private EstadoVenda estadoVenda;
	
	public CadastroEstadoVendaBean(){
		limpar();	
	}
	
	//@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando a conexao...");
		
		if (this.estadoVenda == null) {
			limpar();
		}
				
		if (FacesUtil.isNotPostback()) { //Verifica se esta na mesma pagina para evitar uma nova consulta			
			//estadoVendaList = estadoVendas.buscarEstadoVendas();
			//unidadeList = unidades.buscarUnidades();						
		}
		
	}
	
	private void limpar(){
		estadoVenda = new EstadoVenda();
				 
	}
	
	public void salvar(){
		
		//this.estadoVenda.setDataCadastro(dataAtual());
			
		try {
			this.estadoVenda = cadastroEstadoVendaService.salvar(this.estadoVenda);
			limpar();
			
			FacesUtil.addInfoMessage("EstadoVenda salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public EstadoVenda getEstadoVenda() {
		return estadoVenda;
	}

	public void setEstadoVenda(EstadoVenda estadoVenda) {
		this.estadoVenda = estadoVenda;
	}
	
	public boolean isEditando() {
		return this.estadoVenda.getId() != null;
	}
	
}
