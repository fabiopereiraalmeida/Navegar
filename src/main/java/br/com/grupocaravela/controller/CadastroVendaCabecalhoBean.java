package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.grupocaravela.model.Cliente;
import br.com.grupocaravela.model.Empresa;
import br.com.grupocaravela.model.EnderecoEntrega;
import br.com.grupocaravela.model.FormaPagamento;
import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.model.Usuario;
import br.com.grupocaravela.model.VendaCabecalho;
import br.com.grupocaravela.model.VendaDetalhe;
import br.com.grupocaravela.repository.Clientes;
import br.com.grupocaravela.repository.FormaPagamentos;
import br.com.grupocaravela.repository.Produtos;
import br.com.grupocaravela.repository.Usuarios;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.service.VendaCabecalhoService;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroVendaCabecalhoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private FormaPagamentos formaPagamentos;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Produtos produtos;
	
	@Inject
	private VendaCabecalhoService vendaCabecalhoService;
	
	private VendaCabecalho vendaCabecalho;
	
	private Produto produtoLinhaEditavel;
	
	private String codigo;
	
	private List<Usuario> usuarioList;
	private List<FormaPagamento> formaPagamentoList;
	
	public CadastroVendaCabecalhoBean(){
		limpar();	
	}
	
		
	private void limpar(){
		vendaCabecalho = new VendaCabecalho();
		vendaCabecalho.setEnderecoEntrega(new EnderecoEntrega());
		
		usuarioList = new ArrayList<>();
		formaPagamentoList = new ArrayList<>();		
				 
	}
	
	public void inicializar(){
				
		if (this.vendaCabecalho == null) {
			limpar();
		}
		
		if (FacesUtil.isNotPostback()) {
			this.usuarioList = this.usuarios.buscarUsuarios();
			this.formaPagamentoList = this.formaPagamentos.buscarTodasFormasPagamento();
			
			this.vendaCabecalho.adicionarItemVazio();
			
			this.recalcularVenda();			
		}
		
	}
	
	public List<Cliente> completarCliente(String nome){
		return clientes.porNome(nome);
	}
	
	public void salvar(){
		
		this.vendaCabecalho.removerItemVazio();
		
		try {			
			this.vendaCabecalho = this.vendaCabecalhoService.salvar(this.vendaCabecalho);
			FacesUtil.addInfoMessage("Venda salva com sucesso!");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}finally{
			this.vendaCabecalho.adicionarItemVazio();
		}
		
	}
	
	public void recalcularVenda(){
		if (this.vendaCabecalho != null) {
			this.vendaCabecalho.recalcularValorTotal();
		}
	}
	
	public void carregarProdutoPorCodigo(){
		
		if (StringUtils.isNotEmpty(this.codigo)) {
			this.produtoLinhaEditavel = produtos.porCodigo(this.codigo);
			this.carregarProdutoLinhaEditavel();
		}
	}
	
	public void carregarProdutoLinhaEditavel(){
		VendaDetalhe vDetalhe = this.vendaCabecalho.getVendaDetalheList().get(0);
		
		if (produtoLinhaEditavel != null) {
			
			if (this.existeItemDuplicado(this.produtoLinhaEditavel)) {
				FacesUtil.addErrorMessage("O item informado ja consta na lista de vendas!");
			}else{
			vDetalhe.setProduto(this.getProdutoLinhaEditavel());
			//Empresa empresa = manager.createQuery("from Empresa where id = :id", Empresa.class).setParameter("id", new Long(1)).getSingleResult();
			
			vDetalhe.setValorParcial(produtoLinhaEditavel.getValorDesejavelVenda() * vDetalhe.getQuantidade());			
			vDetalhe.setValorTotal((produtoLinhaEditavel.getValorDesejavelVenda() * vDetalhe.getQuantidade()) - (vDetalhe.getValorDesconto()));
			
			this.vendaCabecalho.adicionarItemVazio();
			this.produtoLinhaEditavel = null;
			this.codigo = null;
			
			this.recalcularVenda();
			}
		}
	}
	
	private boolean existeItemDuplicado(Produto produto) {
		
		boolean existeItem = false;
		
		for (VendaDetalhe vDetalhe : this.getVendaCabecalho().getVendaDetalheList()) {
			if (produto.equals(vDetalhe.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}
	
	public void atualizarQuantidade(VendaDetalhe detalhe, int linha){
		
		if (detalhe.getQuantidade() < 0.01) {
			
			if (linha == 0) {
				detalhe.setQuantidade(1.0);
			}else{
				this.getVendaCabecalho().getVendaDetalheList().remove(linha);
			}
		}
		
		detalhe.calcularTotalItem();
						
		this.vendaCabecalho.recalcularValorTotal();
	}
	
public void atualizarDesconto(VendaDetalhe detalhe, int linha){
				
		detalhe.calcularTotalItem();
		this.vendaCabecalho.recalcularValorTotal();
	}


	public List<Produto> completarProduto(String nome){
		return produtos.porNome(nome);
	}

	public VendaCabecalho getVendaCabecalho() {
		return vendaCabecalho;
	}
	
	public void setVendaCabecalho(VendaCabecalho vendaCabecalho) {
		this.vendaCabecalho = vendaCabecalho;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public List<FormaPagamento> getFormaPagamentoList() {
		return formaPagamentoList;
	}
	
	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}


	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public boolean isEditando(){
		return this.vendaCabecalho.getId() != null;
	}
	
}
