package br.com.drogaria.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Test;

import br.com.drograria.dao.ProdutoDAO;
import br.com.drograria.domain.Fabricante;
import br.com.drograria.domain.Produto;

public class ProdutoDAOTeste {

	@Test
	public void salvar() throws SQLException {
		Produto p = new Produto();
		p.setDescricao("NOVALGINA COM 10 COMPRIMIDOS");
		p.setQuantidade(13L);
		p.setPreco(new BigDecimal("2.45"));

		Fabricante f = new Fabricante();
		f.setCodigo(9L);
		p.setFabricante(f);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(p);
	}
}
