package br.com.grupocaravela.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.grupocaravela.model.Fornecedor;
import br.com.grupocaravela.repository.Fornecedores;
import br.com.grupocaravela.util.jpa.Transactional;

public class CadastroFornecedorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Fornecedores fornecedores;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public Fornecedor salvar(Fornecedor fornecedor) throws NegocioException{
		Fornecedor fornecedorExistente = fornecedores.porCnpj(fornecedor.getCnpj());

		if (fornecedorExistente != null && !fornecedorExistente.equals(fornecedor)) {
			throw new NegocioException("Já existe um fornecedor com o nome informado.");
		}

		return fornecedores.guardar(fornecedor);

	}

}
