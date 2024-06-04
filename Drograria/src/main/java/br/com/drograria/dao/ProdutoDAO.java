package br.com.drograria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.drograria.domain.Fabricante;
import br.com.drograria.domain.Produto;
import br.com.drograria.factory.ConexaoFactory;

public class ProdutoDAO {

	public void salvar(Produto produto) throws SQLException {
		StringBuilder statement = new StringBuilder();
		statement.append("INSERT INTO PRODUTO(");
		statement.append(" DESCRICAO,");
		statement.append(" QUANTIDADE,");
		statement.append(" PRECO,");
		statement.append(" FABRICANTE");
		statement.append(")VALUES(?,?,?,?)");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setString(1, produto.getDescricao());
		comando.setLong(2, produto.getQuantidade());
		comando.setBigDecimal(3, produto.getPreco());
		comando.setLong(4, produto.getFabricante().getCodigo());

		comando.executeUpdate();
	}
	
	
	public void deletar(Produto produto) throws SQLException{
		StringBuilder statement = new StringBuilder();
		statement.append("DELETE FROM PRODUTO ");
		statement.append("WHERE CODIGO = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setLong(1, produto.getCodigo());
		
		comando.executeUpdate();
	}
	
	public void atualizar(Produto produto) throws SQLException{
		StringBuilder statement = new StringBuilder();
		statement.append("UPDATE PRODUTO ");
		statement.append("SET DESCRICAO = ?, ");
		statement.append(" 	  PRECO = ?, ");
		statement.append(" 	  QUANTIDADE = ?, ");
		statement.append(" 	  FABRICANTE = ? ");
		statement.append("WHERE CODIGO = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setString(1, produto.getDescricao());
		comando.setBigDecimal(2, produto.getPreco());
		comando.setLong(3, produto.getQuantidade());
		comando.setLong(4, produto.getFabricante().getCodigo());
		comando.setLong(5, produto.getCodigo());
		
		comando.executeUpdate();
	}
	
	public Produto pesquisarPorCodigo(Long codigo) throws SQLException {
		StringBuilder statement = new StringBuilder();
		statement.append("SELECT p.CODIGO, p.DESCRICAO, p.PRECO, p.QUANTIDADE, p.FABRICANTE, f.CODIGO, F.DESCRICAO ");
		statement.append("FROM PRODUTO p ");
		statement.append("INNER FABRICANTE f ON f.CODIGO = p.FABRICANTE ");
		statement.append("WHERE CODIGO = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setLong(1, codigo);

		ResultSet resultado = comando.executeQuery();

		Produto produto = null;

		if (resultado.next()) {
		    produto = new Produto();
			produto.setCodigo(resultado.getLong("p.CODIGO"));
			produto.setDescricao(resultado.getString("p.DESCRICAO"));
			produto.setPreco(resultado.getBigDecimal("p.PRECO"));
			produto.setQuantidade(resultado.getLong("p.QUANTIDADE"));
			
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("f.CODIGO"));
			fabricante.setDescricao(resultado.getString("f.DESCRICAO"));
			produto.setFabricante(fabricante);
		}

		return produto;
	}
	
	
	public List<Produto> pesquisarPorDescricao(String Descricao) throws SQLException {
		StringBuilder statement = new StringBuilder();
		statement.append("SELECT p.CODIGO, p.DESCRICAO, p.PRECO, p.QUANTIDADE, p.FABRICANTE, f.CODIGO, F.DESCRICAO ");
		statement.append("FROM PRODUTO p ");
		statement.append("INNER FABRICANTE f ON f.CODIGO = p.FABRICANTE ");
		statement.append("WHERE DESCRICAO LIKE ? ");
		statement.append("ORDER BY DESCRICAO DESC");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setString(1, "%" + Descricao + "%");
		
		ResultSet resultado = comando.executeQuery();

		List<Produto> listaProdutos = new ArrayList<Produto>();

		while (resultado.next()) {
			Produto produto = new Produto();
			produto.setCodigo(resultado.getLong("p.CODIGO"));
			produto.setDescricao(resultado.getString("p.DESCRICAO"));
			produto.setPreco(resultado.getBigDecimal("p.PRECO"));
			produto.setQuantidade(resultado.getLong("p.QUANTIDADE"));
			
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("f.CODIGO"));
			fabricante.setDescricao(resultado.getString("f.DESCRICAO"));
			produto.setFabricante(fabricante);
			
			listaProdutos.add(produto);
		}

		return listaProdutos;
	}
	
	public List<Produto> listar() throws SQLException {
		StringBuilder statement = new StringBuilder();
		statement.append("SELECT p.CODIGO, p.DESCRICAO, p.PRECO, p.QUANTIDADE, p.FABRICANTE, f.CODIGO, f.DESCRICAO ");
		statement.append("FROM PRODUTO p ");
		statement.append("INNER JOIN FABRICANTE f ON f.CODIGO = p.FABRICANTE ");
		statement.append("ORDER BY p.DESCRICAO DESC");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());

		ResultSet resultado = comando.executeQuery();

		List<Produto> listaProdutos = new ArrayList<Produto>();

		while (resultado.next()) {
			Produto produto = new Produto();
			produto.setCodigo(resultado.getLong("p.CODIGO"));
			produto.setDescricao(resultado.getString("p.DESCRICAO"));
			produto.setPreco(resultado.getBigDecimal("p.PRECO"));
			produto.setQuantidade(resultado.getLong("p.QUANTIDADE"));
			
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("f.CODIGO"));
			fabricante.setDescricao(resultado.getString("f.DESCRICAO"));
			produto.setFabricante(fabricante);
			
			listaProdutos.add(produto);
		}

		return listaProdutos;
	}
	
	
}
