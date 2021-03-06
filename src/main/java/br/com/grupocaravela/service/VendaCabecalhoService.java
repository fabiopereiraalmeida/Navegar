package br.com.grupocaravela.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.grupocaravela.model.VendaCabecalho;
import br.com.grupocaravela.repository.VendasCabecalho;
import br.com.grupocaravela.util.jpa.Transactional;

public class VendaCabecalhoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VendasCabecalho vendasCabecalho;

	@Transactional // Informa que tudo apartir daqui é uma tranzação com o banco
	public VendaCabecalho salvar(VendaCabecalho vendaCabecalho) throws NegocioException{
		
		if (vendaCabecalho.isNovo()) {
			vendaCabecalho.setDataVenda(new Date());					
			vendaCabecalho.setCodVenda(new Long(vendasCabecalho.ultimoCodVenda()) + new Long(1));
		}
		
		vendaCabecalho.recalcularValorTotal();
		
		if (vendaCabecalho.getVendaDetalheList().isEmpty()) {
			throw new NegocioException("A venda deve possuir no minimo um item!");
		}
		
		if (vendaCabecalho.isValorTotalNegativo()) {
			throw new NegocioException("O valor total da venda não pode ser negativo!");
		}
		
		vendaCabecalho = this.vendasCabecalho.guardar(vendaCabecalho);
		
		return vendaCabecalho;

	}

}
