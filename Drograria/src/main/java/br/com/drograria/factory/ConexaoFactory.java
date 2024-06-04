package br.com.drograria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class ConexaoFactory {

	private static final String USUARIO = "root";
	private static final String SENHA = "a123456";
	private static final String URL = "jdbc:mysql://localhost:3306/drogaria";

//	public static Connection conectar() {
//		Connection conexao = null;
//		try {
//			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
//			System.out.println("conectou");
//			
//		} catch (SQLException e) {
//			System.out.println("erro");
//			e.printStackTrace();
//		}
//
//		return conexao;
//	}

	
	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new Driver()); // Registando o Driver para corrigir o bug do JSF/TomCat
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	public static void main(String[] args) throws SQLException {
		Connection conexao = ConexaoFactory.conectar();
	}

}
