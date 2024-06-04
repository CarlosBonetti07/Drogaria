package br.com.drograria.mb;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drograria.dao.FabricanteDAO;
import br.com.drograria.domain.Fabricante;
import br.com.drograria.util.JSFUtil;

@ManagedBean(name = "FabricanteMb")
@ViewScoped
public class FabricanteMb {

	private Fabricante fabricante;
//	private ListDataModel<Fabricante> itens; // PF 4.0
	private ArrayList<Fabricante> itens; // PF 5.0 +
	private ArrayList<Fabricante> itensFiltrados;// PF 5.0 +

	// @PostConstruct --> Chama o método antes da página ser desenhada
	@PostConstruct
	public void prepararPesquisa() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			itens = (ArrayList<Fabricante>) fabricanteDAO.listar();

//			itens = new ListDataModel<Fabricante>(fabricantes); // PF 4.0

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void prepararNovo() {
		fabricante = new Fabricante();
	}

	public void novo() {
		FabricanteDAO dao = new FabricanteDAO();
		try {
			dao.salvar(fabricante);

			itens = (ArrayList<Fabricante>) dao.listar();

			JSFUtil.mensagemSucesso("Fabricante salvo com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.mensagemErro(e.getMessage());
		}
	}

//	public void prepararExcluir() {
//		fabricante = itens.getRowData(); // PF 4.0 ---> Foi corrigido com o "f:setPropertyActionListener" no JSF
//	}

	public void excluir() {

		FabricanteDAO dao = new FabricanteDAO();
		try {
			dao.deletar(fabricante);

			itens = (ArrayList<Fabricante>) dao.listar();

			JSFUtil.mensagemSucesso("Fabricante excluído com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.mensagemErro(e.getMessage());
		}

	}

	public void editar() {

		FabricanteDAO dao = new FabricanteDAO();
		try {
			dao.atualizar(fabricante);

			itens = (ArrayList<Fabricante>) dao.listar();

			JSFUtil.mensagemSucesso("Fabricante atualizado com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.mensagemErro(e.getMessage());
		}

	}

	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
