package br.com.grupocaravela.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.grupocaravela.model.Cargo;
import br.com.grupocaravela.repository.Cargos;
import br.com.grupocaravela.repository.filter.CargoFilter;
import br.com.grupocaravela.service.NegocioException;
import br.com.grupocaravela.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCargosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cargos cargos;
	
	private CargoFilter filtro;
	private List<Cargo> cargosFiltrados;
	
	private Cargo cargoSelecionado;
	
	public PesquisaCargosBean() {
		filtro = new CargoFilter();
	}
	
	public void excluir() {
		try {
			
			System.out.println("Passei no Excluir");
			
			cargos.remover(cargoSelecionado);
			cargosFiltrados.remove(cargoSelecionado);
			
			FacesUtil.addInfoMessage("Cargo " + cargoSelecionado.getNome() 
					+ " excluído com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar() {
		cargosFiltrados = cargos.filtrados(filtro);
	}
	
	public List<Cargo> getCargosFiltrados() {
		return cargosFiltrados;
	}

	public CargoFilter getFiltro() {
		return filtro;
	}

	public Cargo getCargoSelecionado() {
		return cargoSelecionado;
	}

	public void setCargoSelecionado(Cargo cargoSelecionado) {
		this.cargoSelecionado = cargoSelecionado;
	}
	
}