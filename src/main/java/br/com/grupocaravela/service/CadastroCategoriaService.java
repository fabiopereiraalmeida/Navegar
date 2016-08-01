package br.com.grupocaravela.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.grupocaravela.model.Categoria;
import br.com.grupocaravela.repository.Categorias;
import br.com.grupocaravela.util.jpa.Transactional;

public class CadastroCategoriaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public Categoria salvar(Categoria categoria) throws NegocioException{
		
		Categoria categoriaExistente = categorias.porNome(categoria.getNome());

		if (categoriaExistente != null && !categoriaExistente.equals(categoria)) {
			throw new NegocioException("Já existe um categoria com o nome informado.");
		}
		
		return categorias.guardar(categoria);

	}

}
