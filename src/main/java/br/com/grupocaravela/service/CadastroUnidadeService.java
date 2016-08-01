package br.com.grupocaravela.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.grupocaravela.model.Unidade;
import br.com.grupocaravela.repository.Unidades;
import br.com.grupocaravela.util.jpa.Transactional;

public class CadastroUnidadeService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Unidades unidades;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public Unidade salvar(Unidade unidade) throws NegocioException{
		
		Unidade unidadeExistente = unidades.porNome(unidade.getNome());

		if (unidadeExistente != null && !unidadeExistente.equals(unidade)) {
			throw new NegocioException("Já existe um unidade com o nome informado.");
		}
		
		return unidades.guardar(unidade);

	}

}
