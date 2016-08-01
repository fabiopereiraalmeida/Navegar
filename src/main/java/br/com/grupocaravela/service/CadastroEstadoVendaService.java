package br.com.grupocaravela.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.grupocaravela.model.EstadoVenda;
import br.com.grupocaravela.repository.EstadoVendas;
import br.com.grupocaravela.util.jpa.Transactional;

public class CadastroEstadoVendaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoVendas estadoVendas;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public EstadoVenda salvar(EstadoVenda estadoVenda) throws NegocioException{
		
		EstadoVenda estadoVendaExistente = estadoVendas.porNome(estadoVenda.getNome());

		if (estadoVendaExistente != null && !estadoVendaExistente.equals(estadoVenda)) {
			throw new NegocioException("Já existe um estadoVenda com o nome informado.");
		}
		
		return estadoVendas.guardar(estadoVenda);

	}

}
