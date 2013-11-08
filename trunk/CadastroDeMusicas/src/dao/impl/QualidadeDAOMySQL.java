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
	
	private static List<Qualidade> lista = null;

	public void alterarQualidade(Qualidade q) throws DataException {
		// TODO Auto-generated method stub
	}

	public int cadastrarQualidade(Qualidade q) throws DataException {
		String sql = "INSERT INTO qualidade (qualidade) "
			+ "VALUES (?)";
	
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, q.getQualidade());
			
			ps.execute();

			System.out.println("Qualidade cadastrada");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			q.setIdQualidade(codigo);
			
			lista = null;
			
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("N�o foi poss�vel cadastrar a qualidade.");
		}
	}

	public Qualidade getQualidade(int idQualidade) throws DataException {
		Qualidade q = null;
		
		if (lista == null)
		{
			lista = listarQualidades();
		}

		for (Qualidade qual: lista)
		{
			if (qual.getIdQualidade() == idQualidade)
			{
				q = qual;
				break;
			}
		}
		
		return q;
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
			throw new DataException("N�o foi poss�vel realizar a opera��o");
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

	public int getQualidadeId(String qualidade) throws DataException {
		String sql = "SELECT * FROM Qualidade WHERE qualidade = '" + qualidade + "'";
		
		List<Qualidade> lista = listarQualidadesPorConsulta(sql);
		
		return lista.get(0).getIdQualidade();
	}

}
