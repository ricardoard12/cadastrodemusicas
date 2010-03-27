package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.BDUtil;

import classesbasicas.Qualidade;
import dao.QualidadeDAO;
import exceptions.DataException;

public class QualidadeDAOMySQL implements QualidadeDAO {

	public void alterarQualidade(Qualidade q) throws DataException {
		// TODO Auto-generated method stub
		
	}

	public int cadastrarQualidade(Qualidade q) throws DataException {
		String sql = "INSERT INTO qualidade (qualidade) "
			+ "VALUES (?)";
	
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);

			ps.setString(1, q.getQualidade());
			
			ps.execute();

			System.out.println("Qualidade cadastrada");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			q.setIdQualidade(codigo);			
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível cadastrar a qualidade.");
		}
	}

	public Qualidade getQualidade(int idQualidade) throws DataException {
		String sql = "SELECT * FROM Qualidade WHERE idQualidade = " + idQualidade;
		
		List<Qualidade> lista = listarQualidadesPorConsulta(sql);
		
		if (lista.size() <= 0) {
			return null;
		} else {
			return lista.get(0);
		}
	}
	
	private List<Qualidade> listarQualidadesPorConsulta(String sql) throws DataException {
		List<Qualidade> lista = new ArrayList<Qualidade>();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()) {
				Qualidade q = new Qualidade();
				
				q.setIdQualidade(r.getInt("idQualidade"));
				q.setQualidade(r.getString("qualidade"));
								
				lista.add(q);
			}
			
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível realizar a operação");
		}
	}

	public List<Qualidade> listarQualidades() throws DataException {
		String sql = "SELECT * FROM Qualidade";
		
		List<Qualidade> lista = listarQualidadesPorConsulta(sql);
		
		return lista;
	}

	public void removerQualidade(Qualidade q) throws DataException {
		// TODO Auto-generated method stub
		
	}

}
