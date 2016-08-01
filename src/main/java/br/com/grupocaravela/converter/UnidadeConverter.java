package br.com.grupocaravela.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.grupocaravela.model.Unidade;
import br.com.grupocaravela.model.Unidade;
import br.com.grupocaravela.repository.Unidades;
import br.com.grupocaravela.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Unidade.class)
public class UnidadeConverter implements Converter{

	//@Inject
	private Unidades unidades;
	
	public UnidadeConverter() {
		unidades = CDIServiceLocator.getBean(Unidades.class); //Alternativa para o @Inject
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Unidade retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = unidades.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Unidade unidade = (Unidade) value;
			return unidade.getId() == null ? null : unidade.getId().toString();
			//return ((Unidade) value).getId().toString();
		}
		
		return "";
	}

}
