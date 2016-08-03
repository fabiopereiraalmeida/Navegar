package br.com.grupocaravela.model;

public enum StatusVenda {

	ABERTO("Aberto"), 
	FINALIZADO("Finalizado"), 
	ENTREGUE("Entregue"), 
	CANCELADO("Cancelado");
	
	private String descricao;
	
	private StatusVenda(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
		
}

