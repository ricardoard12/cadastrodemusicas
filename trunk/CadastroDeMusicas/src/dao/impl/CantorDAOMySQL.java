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

import classesbasicas.Cantor;
import dao.CantorDAO;
import exceptions.DataException;

public class CantorDAOMySQL implements CantorDAO {

	private static List<Cantor> lista = null;
	
	private void limparLista()
	{
		if (lista != null)
		{
			lista.clear();
			lista = null;
		}
	}
	
	public void alterarCantor(Cantor c) throws DataException {
		PreparedStatement ps;
		
		String sql = "UPDATE Cantor SET nome=?, nomeSemEspacos=?, chaveUnica=?, modified=? WHERE idCantor=?";

		limparLista();
		
		try {
			ps = BDUtil.getConexao().prepareStatement(sql);
			
			ps.setString(1, c.getNome());
			ps.setString(2, c.getNomeSemEspacos());
			ps.setString(3, c.getChaveUnica());
			Date modified = new Date();
			ps.setTimestamp(4, new Timestamp((modified.getTime())));			
			ps.setInt(5, c.getIdCantor());
			
			ps.execute();
			
			c.setModified(modified);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível alterar o cantor");
		}
	}

	public int cadastrarCantor(Cantor c) throws DataException {
		String sql = "INSERT INTO Cantor (nomesemespacos, nome, chaveUnica, created, modified, tipoarquivo) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		limparLista();
		
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, c.getNomeSemEspacos());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getChaveUnica());
			Date data = new Date();
			ps.setTimestamp(4, new Timestamp(data.getTime()));
			ps.setTimestamp(5, new Timestamp(data.getTime()));
			ps.setInt(6, c.getTipoArquivo());
			
			ps.execute();

			System.out.println("Cantor cadastrado");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			c.setIdCantor(codigo);
			c.setCreated(data);
			c.setModified(data);
			
			rs.close();
			ps.close();
			
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível cadastrar o cantor.");
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
				c.setCreated(r.getDate("created"));
				c.setModified(r.getDate("modified"));
				c.setTipoArquivo(r.getInt("tipoarquivo"));
				
				lista.add(c);
			}
			
			r.close();
			s.close();
			
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível realizar a operação");
		}
	}

	public Cantor getCantor(int idCantor) throws DataException {
		Cantor c = null;
		
		if (lista == null)
		{
			String sql = "SELECT * FROM Cantor";
			lista = listarCantoresPorConsulta(sql);
		}

		for (Cantor cantor: lista)
		{
			if (cantor.getIdCantor() == idCantor)
			{
				c = cantor;
				break;
			}
		}
		
		return c;
	}
	
	public List<Cantor> listarCantores() throws DataException {
		String sql = "SELECT * FROM Cantor ORDER BY nome";
		
		List<Cantor> lista = listarCantoresPorConsulta(sql);
		
		return lista;
	}

	public void removerCantor(Cantor c) throws DataException {
		limparLista();
		
		try {
			String sql = "DELETE FROM cantor WHERE idCantor = " + c.getIdCantor();
			PreparedStatement stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Tipo removido do sistema.");
			
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException();
		}
	}

	public List<Cantor> listarCantoresPorDiversos(String nome, boolean naoListarPorNome,
			String nomeSemEspacos, boolean naoListarPorNomeSemEspacos,
			int tipoArquivo, boolean naoListarPorTipoArquivo) throws DataException {
		String sql = "select * from cantor where (nome like '%" + nome + "%' or '" + naoListarPorNome + "' like 'true')" +
				" and (nomeSemEspacos like '%" + nomeSemEspacos + "%' or '" + naoListarPorNomeSemEspacos + "' like 'true') " +
				" and (tipoarquivo = " + tipoArquivo + " or '" + naoListarPorTipoArquivo + "' like 'true') " +
						"order by cantor.nome";
		
		return listarCantoresPorConsulta(sql);
	}

	public List<Cantor> listarCantoresSemChaveUnica() throws DataException {
		String sql = "SELECT * FROM cantor WHERE chaveUnica IS NULL";
		return listarCantoresPorConsulta(sql);
	}

	public List<Cantor> listarCantoresPorNome(String nome) throws DataException {
		String sql = "SELECT * FROM cantor WHERE nome LIKE '" + nome + "'";
		return listarCantoresPorConsulta(sql);
	}

	public Cantor getCantorPorChaveUnica(String chaveUnica)
			throws DataException {
		String sql = "SELECT * FROM Cantor WHERE chaveUnica LIKE '" + chaveUnica + "'";
		
		List<Cantor> lista = listarCantoresPorConsulta(sql);
		
		if (lista.size() == 0) return null;
		else return lista.get(lista.size() - 1);
	}
	
	public List<Cantor> listarCantoresPorNome(String nome, int tipoArquivo) throws DataException {
		String sql = "SELECT * FROM cantor WHERE nome LIKE '" + nome + "' AND tipoarquivo = " + tipoArquivo;
		return listarCantoresPorConsulta(sql);
	}
	
	

}
