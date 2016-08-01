package br.com.grupocaravela.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.grupocaravela.model.Categoria;
import br.com.grupocaravela.repository.Categorias;
import br.com.grupocaravela.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter{

	//@Inject
	private Categorias categorias;
	
	public CategoriaConverter() {
		categorias = CDIServiceLocator.getBean(Categorias.class); //Alternativa para o @Inject
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = categorias.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Categoria categoria = (Categoria) value;
			return categoria.getId() == null ? null : categoria.getId().toString();
			//return ((Categoria) value).getId().toString();
		}
		
		return "";
	}

}
