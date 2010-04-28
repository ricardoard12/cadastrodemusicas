package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bd.BDUtil;

import classesbasicas.Tipo;
import dao.TipoDAO;
import exceptions.DataException;

public class TipoDAOMySQL implements TipoDAO {

	public void alterarTipo(Tipo t) throws DataException {
		String sql = "UPDATE tipo SET chaveUnica=?, tipo=?, modified=? WHERE idTipo=?";

		PreparedStatement ps;
		try {
			ps = BDUtil.getConexao().prepareStatement(sql);
			
			ps.setString(1, t.getChaveUnica());
			ps.setString(2, t.getTipo());
			Date modified = new Date();
			ps.setTimestamp(3, new Timestamp((modified.getTime())));
			ps.setInt(4, t.getIdTipo());
			
			ps.execute();
			t.setModified(modified);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("N�o foi poss�vel alterar o cantor");
		}
	}

	public int cadastrarTipo(Tipo t) throws DataException {
		String sql = "INSERT INTO tipo (tipo, chaveUnica, created, modified) "
			+ "VALUES (?, ?, ?, ?)";
	
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, t.getTipo());
			ps.setString(2, t.getChaveUnica());
			Date data = new Date();
			ps.setTimestamp(3, new Timestamp(data.getTime()));
			ps.setTimestamp(4, new Timestamp(data.getTime()));
			
			ps.execute();

			System.out.println("Tipo cadastrado");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			t.setIdTipo(codigo);
			t.setCreated(data);
			t.setModified(data);
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("N�o foi poss�vel cadastrar o assunto.");
		}
	}

	public Tipo getTipo(int idTipo) throws DataException {
		String sql = "SELECT * FROM Tipo WHERE idTipo = " + idTipo;
		
		List<Tipo> lista = listarTiposPorConsulta(sql);
		
		if (lista.size() <= 0) {
			return null;
		} else {
			return lista.get(0);
		}
	}
	
	private List<Tipo> listarTiposPorConsulta(String sql) throws DataException {
		List<Tipo> lista = new ArrayList<Tipo>();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()) {
				Tipo t = new Tipo();
				
				t.setIdTipo(r.getInt("idTipo"));
				t.setTipo(r.getString("tipo"));
				t.setChaveUnica(r.getString("chaveUnica"));
				t.setCreated(r.getDate("created"));
				t.setModified(r.getDate("modified"));
								
				lista.add(t);
			}
			
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("N�o foi poss�vel realizar a opera��o");
		}
	}

	public List<Tipo> listarTipos() throws DataException {
		String sql = "SELECT * FROM Tipo ORDER BY tipo";
		
		List<Tipo> lista = listarTiposPorConsulta(sql);
		
		return lista;
	}

	public void removerTipo(Tipo t) throws DataException {
		// TODO Auto-generated method stub
		
	}

	public List<Tipo> listarTiposSemChaveUnica() throws DataException {
		String sql = "SELECT * FROM tipo WHERE chaveUnica IS NULL";
		return listarTiposPorConsulta(sql);
	}

	public List<Tipo> listarTipos(String tipo) throws DataException {
		String sql = "SELECT * FROM tipo WHERE tipo LIKE '" + tipo + "'";
		return listarTiposPorConsulta(sql);
	}

}
