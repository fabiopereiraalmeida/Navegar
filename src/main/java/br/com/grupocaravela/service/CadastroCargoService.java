package br.com.grupocaravela.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.grupocaravela.model.Cargo;
import br.com.grupocaravela.repository.Cargos;
import br.com.grupocaravela.util.jpa.Transactional;

public class CadastroCargoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cargos cargos;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public Cargo salvar(Cargo cargo) throws NegocioException{
		
		Cargo cargoExistente = cargos.porNome(cargo.getNome());

		if (cargoExistente != null && !cargoExistente.equals(cargo)) {
			throw new NegocioException("Já existe um cargo com o nome informado.");
		}
		
		return cargos.guardar(cargo);

	}

}
