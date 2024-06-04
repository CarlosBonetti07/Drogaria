package br.com.drograria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.drograria.domain.Fabricante;
import br.com.drograria.factory.ConexaoFactory;

public class FabricanteDAO {

	public void salvar(Fabricante fabricante) throws SQLException {
		StringBuilder statement = new StringBuilder();
		statement.append("INSERT INTO FABRICANTE ");
		statement.append("(DESCRICAO) ");
		statement.append("VALUES(?)");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setString(1, fabricante.getDescricao());
		
		comando.executeUpdate();
	}
	
	
	public void deletar(Fabricante fabricante) throws SQLException{
		StringBuilder statement = new StringBuilder();
		statement.append("DELETE FROM FABRICANTE ");
		statement.append("WHERE CODIGO = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setLong(1, fabricante.getCodigo());
		
		comando.executeUpdate();
	}
	
	public void atualizar(Fabricante fabricante) throws SQLException{
		StringBuilder statement = new StringBuilder();
		statement.append("UPDATE FABRICANTE ");
		statement.append("SET DESCRICAO = ? ");
		statement.append("WHERE CODIGO = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setString(1, fabricante.getDescricao());
		comando.setLong(2, fabricante.getCodigo());
		
		comando.executeUpdate();
	}
	
	public Fabricante pesquisarPorCodigo(Long codigo) throws SQLException {
		StringBuilder statement = new StringBuilder();
		statement.append("SELECT CODIGO, DESCRICAO ");
		statement.append("FROM FABRICANTE ");
		statement.append("WHERE CODIGO = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setLong(1, codigo);

		ResultSet resultado = comando.executeQuery();

		Fabricante fabricante = null;

		if (resultado.next()) {
			fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("CODIGO"));
			fabricante.setDescricao(resultado.getString("DESCRICAO"));
		}

		return fabricante;
	}
	
	
	public List<Fabricante> pesquisarPorDescricao(String Descricao) throws SQLException {
		StringBuilder statement = new StringBuilder();
		statement.append("SELECT CODIGO, DESCRICAO ");
		statement.append("FROM FABRICANTE ");
		statement.append("WHERE DESCRICAO LIKE ? ");
		statement.append("ORDER BY DESCRICAO DESC");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());
		comando.setString(1, "%" + Descricao + "%");
		
		ResultSet resultado = comando.executeQuery();

		List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("CODIGO"));
			fabricante.setDescricao(resultado.getString("DESCRICAO"));
			
			listaFabricantes.add(fabricante);
		}

		return listaFabricantes;
	}
	
	public List<Fabricante> listar() throws SQLException {
		StringBuilder statement = new StringBuilder();
		statement.append("SELECT CODIGO, DESCRICAO ");
		statement.append("FROM FABRICANTE ");
		statement.append("ORDER BY DESCRICAO DESC");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(statement.toString());

		ResultSet resultado = comando.executeQuery();

		List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultado.getLong("CODIGO"));
			fabricante.setDescricao(resultado.getString("DESCRICAO"));
			
			listaFabricantes.add(fabricante);
		}

		return listaFabricantes;
	}
	
	public static void main(String[] args) {

		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Fabricante teste");

		Fabricante fabricante2 = new Fabricante();
		fabricante2.setDescricao("Fabricante teste2");
		
//		Fabricante fabricante3 = new Fabricante();
//		fabricante3.setCodigo(1L);
//		fabricante3.setDescricao("Fabricante 3");

		
//		Fabricante fabricante2 = new Fabricante();
//		fabricante2.setCodigo(2L);
//		fabricante2.setDescricao("Fabricante 2 Atualizado");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		try {
			fabricanteDAO.salvar(fabricante);
			fabricanteDAO.salvar(fabricante2);
			System.out.println("FABRICANTES SALVOS");
			
//			fabricanteDAO.deletar(fabricante3);
//			System.out.println("FABRICANTES EXCLUIDOS");
			
//			fabricanteDAO.atualizar(fabricante2);
//			System.out.println("FABRICANTES ATUALIZADOS");
			
//			Fabricante fabricante = fabricanteDAO.pesquisarPorCodigo(2L);
//			System.out.println("Código:" + fabricante.getCodigo() + " Decrição:" + fabricante.getDescricao());
			
//			List<Fabricante> fabricantes = fabricanteDAO.listar();
//			for (Fabricante fabricante : fabricantes) {
//				System.out.println("Código:" + fabricante.getCodigo() + " Decrição:" + fabricante.getDescricao());
//			}
//			List<Fabricante> fabricantes = fabricanteDAO.pesquisarPorDescricao("2");
//			for (Fabricante fabricante : fabricantes) {
//				System.out.println("Código:" + fabricante.getCodigo() + " Decrição:" + fabricante.getDescricao());
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
