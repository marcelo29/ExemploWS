package br.com.videoaulazeni.exemploWS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class UsuarioDao {

	public boolean inserirUsuario(Usuario usuario) {
		try {
			Connection con = ConectaMySql.obtemConexao();

			String queryInserir = "insert into usuario values (null, ?, ?)";

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(queryInserir);

			ppStm.setString(1, usuario.getNome());
			ppStm.setInt(2, usuario.getIdade());

			ppStm.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean atualizarUsuario(Usuario usuario) {
		try {
			Connection con = ConectaMySql.obtemConexao();

			String query = "update usuario set nome = ?, idade = ? where id = ?";

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(query);

			ppStm.setString(1, usuario.getNome());
			ppStm.setInt(2, usuario.getIdade());
			ppStm.setInt(3, usuario.getId());

			ppStm.execute();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean excluirUsuario(int id) {
		return excluirUsuario(new Usuario(id, "", 0));
	}

	public boolean excluirUsuario(Usuario usuario) {
		try {
			Connection con = ConectaMySql.obtemConexao();

			String queryExcluir = "delete from usuario where id = ?";

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(queryExcluir);

			ppStm.setInt(1, usuario.getId());

			ppStm.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public ArrayList<Usuario> buscarTodosUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		Usuario user = null;

		try {
			Connection con = ConectaMySql.obtemConexao();

			String query = "select * from usuario";

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(query);

			ResultSet rs = ppStm.executeQuery();

			while (rs.next()) {
				user = new Usuario();
				user.setId(rs.getInt(1));
				user.setNome(rs.getString(2));
				user.setIdade(rs.getInt(3));

				lista.add(user);
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public Usuario buscarUsuarioPorId(int id) {
		Usuario user = null;

		try {
			Connection con = ConectaMySql.obtemConexao();

			String queryPesquisa = "select * from usuario where id = ?";

			PreparedStatement ppStm = (PreparedStatement) con.prepareStatement(queryPesquisa);

			ppStm.setInt(1, id);

			ResultSet rs = ppStm.executeQuery();

			if (rs.next()) {
				user = new Usuario();
				user.setId(rs.getInt(1));
				user.setNome(rs.getString(2));
				user.setIdade(rs.getInt(3));
			} else {
				return user;
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}