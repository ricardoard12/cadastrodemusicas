package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.BDUtil;

import classesbasicas.Cantor;
import dao.CantorDAO;
import exceptions.DataException;

public class CantorDAOMySQL implements CantorDAO {

	public void alterarCantor(Cantor c) throws DataException {
		String sql = "UPDATE Cantor SET nome=?, nomeSemEspacos=?, chaveUnica=? WHERE idCantor=?";

		PreparedStatement ps;
		try {
			ps = BDUtil.getConexao().prepareStatement(sql);
			
			ps.setString(1, c.getNome());
			ps.setString(2, c.getNomeSemEspacos());
			ps.setString(3, c.getChaveUnica());
			ps.setInt(4, c.getIdCantor());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("N�o foi poss�vel alterar o cantor");
		}
	}

	public int cadastrarCantor(Cantor c) throws DataException {
		String sql = "INSERT INTO Cantor (nomesemespacos, nome, chaveUnica) "
			+ "VALUES (?, ?, ?)";
		
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);

			ps.setString(1, c.getNomeSemEspacos());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getChaveUnica());
			
			ps.execute();

			System.out.println("Cantor cadastrado");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			c.setIdCantor(codigo);
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("N�o foi poss�vel cadastrar o cantor.");
		}
	}
	
	private List<Cantor> listarCantoresPorConsulta(String sql) throws DataException {
		List<Cantor> lista = new ArrayList<Cantor>();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()) {
				Cantor c = new Cantor();
				
				c.setIdCantor(r.getInt("idCantor"));
				c.setNomeSemEspacos(r.getString("nomesemespacos"));
				c.setNome(r.getString("nome"));
				c.setChaveUnica(r.getString("chaveUnica"));
				
				lista.add(c);
			}
			
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("N�o foi poss�vel realizar a opera��o");
		}
	}

	public Cantor getCantor(int idCantor) throws DataException {		
		String sql = "SELECT * FROM Cantor WHERE idCantor = " + idCantor;
		
		List<Cantor> lista = listarCantoresPorConsulta(sql);
		
		if (lista.size() == 0) return null;
		else return lista.get(lista.size() - 1);
	}

	
	
	public List<Cantor> listarCantores() throws DataException {
		String sql = "SELECT * FROM Cantor ORDER BY nome";
		
		List<Cantor> lista = listarCantoresPorConsulta(sql);
		
		return lista;
	}

	public void removerCantor(Cantor c) throws DataException {
		// TODO Auto-generated method stub
		
	}

	public List<Cantor> listarCantoresPorDiversos(String nome, boolean naoListarPorNome,
			String nomeSemEspacos, boolean naoListarPorNomeSemEspacos) throws DataException {
		String sql = "select * from cantor where (nome like '%" + nome + "%' or '" + naoListarPorNome + "' like 'true')" +
				" and (nomeSemEspacos like '%" + nomeSemEspacos + "%' or '" + naoListarPorNomeSemEspacos + "' like 'true') " +
						"order by cantor.nome";
		
		return listarCantoresPorConsulta(sql);
	}

	public List<Cantor> listarCantoresSemChaveUnica() throws DataException {
		String sql = "SELECT * FROM cantor WHERE chaveUnica IS NULL";
		return listarCantoresPorConsulta(sql);
	}

}
