package br.com.grupocaravela.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.grupocaravela.model.VendaCabecalho;
import br.com.grupocaravela.repository.VendasCabecalho;
import br.com.grupocaravela.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = VendaCabecalho.class)
public class VendaCabecalhoConverter implements Converter{

	//@Inject
	private VendasCabecalho vendasCabecalho;
	
	public VendaCabecalhoConverter() {
		vendasCabecalho = CDIServiceLocator.getBean(VendasCabecalho.class); //Alternativa para o @Inject
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		VendaCabecalho retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = vendasCabecalho.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			VendaCabecalho vendaCabecalho = (VendaCabecalho) value;
			return vendaCabecalho.getId() == null ? null : vendaCabecalho.getId().toString();
			//return ((VendaCabecalho) value).getId().toString();
		}
		
		return "";
	}

}
