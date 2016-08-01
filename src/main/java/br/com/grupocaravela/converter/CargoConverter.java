package br.com.grupocaravela.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.grupocaravela.model.Cargo;
import br.com.grupocaravela.repository.Cargos;
import br.com.grupocaravela.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cargo.class)
public class CargoConverter implements Converter{

	//@Inject
	private Cargos cargos;
	
	public CargoConverter() {
		cargos = CDIServiceLocator.getBean(Cargos.class); //Alternativa para o @Inject
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cargo retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = cargos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cargo cargo = (Cargo) value;
			return cargo.getId() == null ? null : cargo.getId().toString();
			//return ((Cargo) value).getId().toString();
		}
		
		return "";
	}

}
