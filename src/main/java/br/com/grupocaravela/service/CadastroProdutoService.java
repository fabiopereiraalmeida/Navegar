package br.com.grupocaravela.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.repository.Produtos;
import br.com.grupocaravela.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public Produto salvar(Produto produto) throws NegocioException{
		Produto produtoExistente = produtos.porCodigo(produto.getCodigo());

		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}

		return produtos.guardar(produto);

	}

}
