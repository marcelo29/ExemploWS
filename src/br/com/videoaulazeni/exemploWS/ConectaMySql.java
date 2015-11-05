package br.com.videoaulazeni.exemploWS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySql {

	private static final String URL = "jdbc:mysql://localhost/exemploWS";
	private static final String USER = "root";
	private static final String PASSWORD = "123";

	public static Connection obtemConexao() throws SQLException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
