package br.com.grupocaravela.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.grupocaravela.model.Produto;
import br.com.grupocaravela.repository.Produtos;
import br.com.grupocaravela.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter{

	//@Inject
	private Produtos produtos;
	
	public ProdutoConverter() {
		produtos = CDIServiceLocator.getBean(Produtos.class); //Alternativa para o @Inject
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = produtos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Produto produto = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
			//return ((Produto) value).getId().toString();
		}
		
		return "";
	}

}
