package br.com.grupocaravela.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.grupocaravela.model.Usuario;
import br.com.grupocaravela.repository.Usuarios;
import br.com.grupocaravela.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public Usuario salvar(Usuario usuario) throws NegocioException{
		
		Usuario usuarioExistente = usuarios.porNome(usuario.getNome());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um usuario com o nome informado.");
		}
		
		return usuarios.guardar(usuario);

	}

}
