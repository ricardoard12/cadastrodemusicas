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
import classesbasicas.Assunto;
import classesbasicas.Constantes;
import dao.AssuntoDAO;
import exceptions.DataException;

public class AssuntoDAOMySQL implements AssuntoDAO {
	
	private static List<Assunto> lista = null;

	private void limparLista()
	{
		lista.clear();
		lista = null;
	}
	
	public void alterarAssunto(Assunto a) throws DataException {
		PreparedStatement ps;
		
		String sql = "UPDATE assunto SET chaveUnica=?, assunto=?, modified=? WHERE idAssunto=?";
		
		limparLista();
		
		try {
		
			
			ps = BDUtil.getConexao().prepareStatement(sql);
			
			ps.setString(1, a.getChaveUnica());
			ps.setString(2, a.getAssunto());
			Date modified = new Date();
			ps.setTimestamp(3, new Timestamp((modified.getTime())));
			ps.setInt(4, a.getIdAssunto());
			
			ps.execute();
			a.setModified(modified);
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível alterar o cantor");
		}
	}

	public int cadastrarAssunto(Assunto a) throws DataException {
		String sql = "INSERT INTO assunto (assunto, chaveUnica, created, modified, tipoarquivo) "
			+ "VALUES (?, ?, ?, ?, ?)";
	
		limparLista();
		
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, a.getAssunto());
			ps.setString(2, a.getChaveUnica());
			Date data = new Date();
			ps.setTimestamp(3, new Timestamp(data.getTime()));
			ps.setTimestamp(4, new Timestamp(data.getTime()));
			ps.setInt(5, a.getTipoArquivo());
			
			ps.execute();

			System.out.println("Assunto cadastrado");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			a.setIdAssunto(codigo);
			a.setCreated(data);
			a.setModified(data);
			
			rs.close();
			ps.close();
			
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
				a.setCreated(r.getDate("created"));
				a.setModified(r.getDate("modified"));
				a.setTipoArquivo(r.getInt("tipoarquivo"));
								
				lista.add(a);
			}
			
			r.close();
			s.close();
			
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível realizar a operação");
		}
	}

	public Assunto getAssunto(int idAssunto) throws DataException {
		Assunto a = null;
		
		if (lista == null)
		{
			String sql = "SELECT * FROM Assunto";
			lista = listarAssuntosPorConsulta(sql);
		}
		
		for (Assunto assunto: lista)
		{
			if (assunto.getIdAssunto() == idAssunto)
			{
				a = assunto;
				break;
			}
		}
		
		return a;
	}

	public List<Assunto> listarAssuntos() throws DataException {
		String sql = "SELECT * FROM Assunto ORDER BY assunto";
		
		List<Assunto> lista = listarAssuntosPorConsulta(sql);
		
		return lista;
	}

	public void removerAssunto(Assunto a) throws DataException {
		limparLista();
		
		try {
			String sql = "DELETE FROM assunto WHERE idAssunto = " + a.getIdAssunto();
			PreparedStatement stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			
			System.out.println("Tipo removido do sistema.");
			
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException();
		}
	}

	public List<Assunto> listarAssuntosSemChaveUnica() throws DataException {
		String sql = "SELECT * FROM assunto WHERE chaveUnica IS NULL";
		return listarAssuntosPorConsulta(sql);		
	}

	public List<Assunto> listarAssuntos(String assunto) throws DataException {
		String sql = "SELECT * FROM assunto WHERE assunto LIKE '" + assunto + "'";
		return listarAssuntosPorConsulta(sql);
	}

	public Assunto getAssuntoPorChaveUnica(String chaveUnica)
			throws DataException {
		String sql = "SELECT * FROM Assunto WHERE chaveUnica LIKE '" + chaveUnica + "'";
		
		List<Assunto> lista = listarAssuntosPorConsulta(sql);
		
		if (lista.size() == 0) return null;
		else return lista.get(lista.size() - 1);
	}

	public List<Assunto> listarAssuntos(int tipoArquivo) throws DataException {
		String sql = "SELECT * FROM assunto WHERE tipoarquivo = " + tipoArquivo + " OR " + tipoArquivo + " = " + Constantes.TIPO_ARQUIVO_TODOS + " ORDER BY assunto";
		return listarAssuntosPorConsulta(sql);
	}
	
	public List<Assunto> listarAssuntos(String assunto, int tipoArquivo) throws DataException {
		String sql = "SELECT * FROM assunto WHERE assunto LIKE '" + assunto + "' AND tipoarquivo = " + tipoArquivo;
		return listarAssuntosPorConsulta(sql);
	}

}
