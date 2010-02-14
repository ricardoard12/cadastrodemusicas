package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.BDUtil;
import classesbasicas.Assunto;
import dao.AssuntoDAO;
import exceptions.DataException;

public class AssuntoDAOMySQL implements AssuntoDAO {

	public void alterarAssunto(Assunto a) throws DataException {
		String sql = "UPDATE assunto SET chaveUnica=?, assunto=? WHERE idAssunto=?";

		PreparedStatement ps;
		try {
			ps = BDUtil.getConexao().prepareStatement(sql);
			
			ps.setString(1, a.getChaveUnica());
			ps.setString(2, a.getAssunto());
			ps.setInt(3, a.getIdAssunto());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível alterar o cantor");
		}
	}

	public int cadastrarAssunto(Assunto a) throws DataException {
		String sql = "INSERT INTO assunto (assunto, chaveUnica) "
			+ "VALUES (?, ?)";
	
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);

			ps.setString(1, a.getAssunto());
			ps.setString(2, a.getChaveUnica());
			
			ps.execute();

			System.out.println("Assunto cadastrado");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			a.setIdAssunto(codigo);			
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível cadastrar o assunto.");
		}
	}
	
	private List<Assunto> listarAssuntosPorConsulta(String sql) throws DataException {
		List<Assunto> lista = new ArrayList<Assunto>();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()) {
				Assunto a = new Assunto();
				
				a.setIdAssunto(r.getInt("idAssunto"));
				a.setAssunto(r.getString("assunto"));
				a.setChaveUnica(r.getString("chaveUnica"));
								
				lista.add(a);
			}
			
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível realizar a operação");
		}
	}

	public Assunto getAssunto(int idAssunto) throws DataException {
		String sql = "SELECT * FROM Assunto WHERE idAssunto = " + idAssunto;
		
		List<Assunto> lista = listarAssuntosPorConsulta(sql);
		
		if (lista.size() == 0) return null;
		else return lista.get(lista.size() - 1);
	}

	public List<Assunto> listarAssuntos() throws DataException {
		String sql = "SELECT * FROM Assunto ORDER BY assunto";
		
		List<Assunto> lista = listarAssuntosPorConsulta(sql);
		
		return lista;
	}

	public void removerAssunto(Assunto a) throws DataException {
		// TODO Auto-generated method stub
		
	}

	public List<Assunto> listarAssuntosSemChaveUnica() throws DataException {
		String sql = "SELECT * FROM assunto WHERE chaveUnica IS NULL";
		return listarAssuntosPorConsulta(sql);		
	}

}
