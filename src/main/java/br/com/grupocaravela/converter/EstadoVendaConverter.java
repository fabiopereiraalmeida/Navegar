package br.com.grupocaravela.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.grupocaravela.model.EstadoVenda;
import br.com.grupocaravela.repository.EstadoVendas;
import br.com.grupocaravela.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = EstadoVenda.class)
public class EstadoVendaConverter implements Converter{

	//@Inject
	private EstadoVendas estadoVendas;
	
	public EstadoVendaConverter() {
		estadoVendas = CDIServiceLocator.getBean(EstadoVendas.class); //Alternativa para o @Inject
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		EstadoVenda retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = estadoVendas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			EstadoVenda estadoVenda = (EstadoVenda) value;
			return estadoVenda.getId() == null ? null : estadoVenda.getId().toString();
			//return ((EstadoVenda) value).getId().toString();
		}
		
		return "";
	}

}
