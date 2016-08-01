package br.com.grupocaravela.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.grupocaravela.model.Cliente;
import br.com.grupocaravela.repository.Clientes;
import br.com.grupocaravela.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public Cliente salvar(Cliente cliente) throws NegocioException{
		Cliente clienteExistente = clientes.porCpf(cliente.getCpf());

		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente com o nome informado.");
		}

		return clientes.guardar(cliente);

	}

}
