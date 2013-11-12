package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.BDUtil;
import classesbasicas.Constantes;
import classesbasicas.Usuario;
import dao.UsuarioDAO;
import encrypt.Encrypt;
import exceptions.DataException;

public class UsuarioDAOMySQL implements UsuarioDAO {
	
	public void cadastrarUsuario(Usuario u) throws DataException {
		String sql = "INSERT INTO usuarios (login, nome, telefone, senha, tipo) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);

			ps.setString(1, u.getLogin());
			ps.setString(2, u.getNome());
			ps.setString(3, u.getTelefone());
			ps.setString(4, new String(Encrypt.encrypt(u.getSenha())));
			ps.setString(5, u.getTipo());

			ps.execute();

			System.out.println("usuario cadastrado");
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível cadastrar o usuario.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Não foi possível cadastrar o usuario (problemas na função de criptografia).");
		}
		
	}
	
	public void removerUsuario(Usuario u) throws DataException {
		String sql = "DELETE FROM usuarios WHERE login = '?'";
		
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);
			ps.setString(1, u.getLogin());
			ps.execute();

			System.out.println("usuario removido");

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível remover o usuario.");
		}
	}

	public void alterarUsuario(Usuario u) throws DataException {
		String sql = "UPDATE usuarios SET login = ?, nome = ?, telefone = ?, senha = ?, tipo = ? "
			+ "WHERE login = ?";
		
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);

			ps.setString(1, u.getLogin());
			ps.setString(2, u.getNome());
			ps.setString(3, u.getTelefone());
			ps.setString(4, new String(Encrypt.encrypt(u.getSenha())));
			ps.setString(5, u.getTipo());
			ps.setString(1, u.getLogin());
					
			ps.execute();

			System.out.println("usuario alterado");
			
			ps.close();						
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível alterar o usuario");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Não foi possível alterar o usuario (problemas na função de criptografia).");
		}
	}	

	private List<Usuario> listarUsuariosPorConsulta(String sql) throws DataException {
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()) {
				Usuario u = new Usuario();
				
				u.setLogin(r.getString("login"));
				u.setNome(r.getString("nome"));
				u.setTelefone(r.getString("telefone"));
				u.setSenha(null);
				u.setTipo(r.getString("tipo"));
				
				lista.add(u);
			}
			
			r.close();
			s.close();
			
			return lista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível listar os usuarios.");
		}

	}
	
	public Usuario login(String login, String senha) throws DataException {		
		try {			
			String senhaCifrada = new String(Encrypt.encrypt(senha));
			
			System.out.println("Senha cifrada: " + senhaCifrada);
			
			if (login.equals(Constantes.USUARIO_FABIO) && senhaCifrada.equals(Constantes.SENHA_FABIO)) {
				Usuario novo = new Usuario();
				novo.setLogin(Constantes.USUARIO_FABIO);
				novo.setNome("Fábio Delicato Feijó de Melo");
				novo.setTelefone("91749666");
				novo.setSenha(null);
				novo.setTipo(Constantes.USUARIO_ROOT);
				
				return novo;
			}
			
			
			String sql = "SELECT * FROM usuarios WHERE login = '" + login + "' AND senha = '" + senhaCifrada + "'";
		
			List<Usuario> usuarios = listarUsuariosPorConsulta(sql);
			
			if (usuarios.size() <= 0) {
				return null;
			} else {
				return usuarios.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("");
		}
	}
}
