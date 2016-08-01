package br.com.grupocaravela.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.grupocaravela.model.FormaPagamento;
import br.com.grupocaravela.repository.FormaPagamentos;
import br.com.grupocaravela.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = FormaPagamento.class)
public class FormaPagamentoConverter implements Converter{

	//@Inject
	private FormaPagamentos formaPagamentos;
	
	public FormaPagamentoConverter() {
		formaPagamentos = CDIServiceLocator.getBean(FormaPagamentos.class); //Alternativa para o @Inject
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		FormaPagamento retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = formaPagamentos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			FormaPagamento formaPagamento = (FormaPagamento) value;
			return formaPagamento.getId() == null ? null : formaPagamento.getId().toString();
			//return ((FormaPagamento) value).getId().toString();
		}
		
		return "";
	}

}
