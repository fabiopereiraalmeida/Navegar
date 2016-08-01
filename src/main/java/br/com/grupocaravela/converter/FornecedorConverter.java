package br.com.grupocaravela.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.grupocaravela.model.Fornecedor;
import br.com.grupocaravela.repository.Fornecedores;
import br.com.grupocaravela.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter implements Converter{

	//@Inject
	private Fornecedores fornecedors;
	
	public FornecedorConverter() {
		fornecedors = CDIServiceLocator.getBean(Fornecedores.class); //Alternativa para o @Inject
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Fornecedor retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			//Long id = new Long(1);
			retorno = fornecedors.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Fornecedor fornecedor = (Fornecedor) value;
			return fornecedor.getId() == null ? null : fornecedor.getId().toString();
			//return ((Fornecedor) value).getId().toString();
		}
		
		return "";
	}

}
