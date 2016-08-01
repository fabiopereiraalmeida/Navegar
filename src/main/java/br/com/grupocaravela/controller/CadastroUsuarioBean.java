package br.com.grupocaravela.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Usuario;
import br.com.grupocaravela.service.CadastroUsuarioService;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	private Usuario usuario;
	
	public CadastroUsuarioBean(){
		limpar();	
	}
	
	//@PostConstruct
	public void inicializar(){
		System.out.println("Inicializando a conexao...");
		
		if (this.usuario == null) {
			limpar();
		}
				
		if (FacesUtil.isNotPostback()) { //Verifica se esta na mesma pagina para evitar uma nova consulta			
			//usuarioList = usuarios.buscarUsuarios();
			//unidadeList = unidades.buscarUnidades();						
		}
		
	}
	
	private void limpar(){
		usuario = new Usuario();
				 
	}
	
	public void salvar(){
		
		//this.usuario.setDataCadastro(dataAtual());
			
		try {
			this.usuario = cadastroUsuarioService.salvar(this.usuario);
			limpar();
			
			FacesUtil.addInfoMessage("Usuario salvo com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isEditando() {
		return this.usuario.getId() != null;
	}
	
}
