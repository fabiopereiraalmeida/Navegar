package br.com.grupocaravela.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.grupocaravela.model.FormaPagamento;
import br.com.grupocaravela.repository.FormaPagamentos;
import br.com.grupocaravela.util.jpa.Transactional;

public class CadastroFormaPagamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FormaPagamentos formaPagamentos;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public FormaPagamento salvar(FormaPagamento formaPagamento) throws NegocioException{
		FormaPagamento formaPagamentoExistente = formaPagamentos.IgualdadeNome(formaPagamento.getNome());

		if (formaPagamentoExistente != null && !formaPagamentoExistente.equals(formaPagamento)) {
			throw new NegocioException("Já existe uma forma de pagamento com o nome informado.");
		}

		return formaPagamentos.guardar(formaPagamento);

	}

}
