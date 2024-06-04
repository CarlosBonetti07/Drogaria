package br.com.drograria.mb;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drograria.dao.FabricanteDAO;
import br.com.drograria.dao.ProdutoDAO;
import br.com.drograria.domain.Fabricante;
import br.com.drograria.domain.Produto;
import br.com.drograria.util.JSFUtil;

@ManagedBean(name = "ProdutoMb")
@ViewScoped
public class ProdutoMb {

	private Produto produto;
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;

	private ArrayList<Fabricante> comboFabricantes;

	@PostConstruct
	public void carregarListagem() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			itens = (ArrayList<Produto>) dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.mensagemErro(e.getMessage());
		}
	}

	public void prepararNovo() {
		produto = new Produto();

		FabricanteDAO dao = new FabricanteDAO();
		try {
			comboFabricantes = (ArrayList<Fabricante>) dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.mensagemErro(e.getMessage());
		}
	}

	public void novo() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.salvar(produto);

			itens = (ArrayList<Produto>) dao.listar();

			JSFUtil.mensagemSucesso("Produto salvo com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.mensagemErro(e.getMessage());
		}
	}

	public void excluir() {

		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.deletar(produto);

			itens = (ArrayList<Produto>) dao.listar();

			JSFUtil.mensagemSucesso("Produto excluído com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.mensagemErro(e.getMessage());
		}

	}

	public void prepararEditar() {
		FabricanteDAO dao = new FabricanteDAO();
		try {
			comboFabricantes = (ArrayList<Fabricante>) dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.mensagemErro(e.getMessage());
		}
	}
	
	public void editar() {

		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.atualizar(produto);

			itens = (ArrayList<Produto>) dao.listar();

			JSFUtil.mensagemSucesso("Produto atualizado com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.mensagemErro(e.getMessage());
		}

	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public ArrayList<Fabricante> getComboFabricantes() {
		return comboFabricantes;
	}

	public void setComboFabricantes(ArrayList<Fabricante> comboFabricantes) {
		this.comboFabricantes = comboFabricantes;
	}

}
