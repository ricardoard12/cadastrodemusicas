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
import classesbasicas.Colecao;
import classesbasicas.Musica;
import dao.ColecaoDAO;
import exceptions.DataException;

public class ColecaoDAOMySQL implements ColecaoDAO {

	public void alterarColecao(Colecao c) throws DataException {
		try {
			// Removendo as músicas atuais da coleção
			String sql = "DELETE FROM musicacolecao WHERE idColecao = " + c.getIdColecao();
			PreparedStatement stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Músicas antigas removidas da coleção");
			stat.close();

			sql = "UPDATE colecao SET nome=?, descricao=?, modified = ? WHERE idColecao=?";

			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);

			ps.setString(1, c.getNome());
			ps.setString(2, c.getDescricao());
			Date modified = new Date();
			ps.setTimestamp(3, new Timestamp((modified.getTime())));	
			ps.setInt(4, c.getIdColecao());

			ps.execute();
			c.setModified(modified);

			// cadastrando as novas músicas
			if (c.getMusicas() != null && c.getMusicas().size() > 0) {
				String sqls[] = new String[c.getMusicas().size()];
				for (int i = 1; i <= c.getMusicas().size(); i++) {
					sqls[i - 1] = "INSERT INTO musicacolecao (idMusica, idColecao, ordem) VALUES (" + c.getMusicas().get(i -  1).getIdMusica() + ", " 
									+ c.getIdColecao() + ", " + i + ")";
				}
				
				BDUtil.getConexao().setAutoCommit(false);
				Statement pstat;			
				pstat = BDUtil.getConexao().createStatement();
				for (String s : sqls) {
					System.out.println(s);
					pstat.addBatch(s);
				}				
				pstat.executeBatch();
				BDUtil.getConexao().commit();
				BDUtil.getConexao().setAutoCommit(true);
				pstat.close();
			}
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// insere uma nova coleção
	public int cadastrarColecao(Colecao c) throws DataException {
		String sql = "INSERT INTO colecao (nome, descricao, created, modified) "
			+ "VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, c.getNome());
			if (c.getDescricao() == null || c.getDescricao().length() <= 0) {
				ps.setNull(2, java.sql.Types.VARCHAR);
			} else {
				ps.setString(2, c.getDescricao());	
			}
			Date data = new Date();
			ps.setTimestamp(3, new Timestamp(data.getTime()));
			ps.setTimestamp(4, new Timestamp(data.getTime()));
			
			ps.execute();

			System.out.println("Coleção cadastrada");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			c.setIdColecao(codigo);	
			c.setCreated(data);
			c.setModified(data);
			
			// adicionar as músicas na ordem encontrada na coleção
			if (c.getMusicas() != null && c.getMusicas().size() > 0) {
				String sqls[] = new String[c.getMusicas().size()];
				for (int i = 1; i <= c.getMusicas().size(); i++) {
					sqls[i - 1] = "INSERT INTO musicacolecao (idMusica, idColecao, ordem) VALUES (" + c.getMusicas().get(i -  1).getIdMusica() + ", " 
									+ c.getIdColecao() + ", " + i + ")";
				}
				
				BDUtil.getConexao().setAutoCommit(false);
				Statement stat;			
				stat = BDUtil.getConexao().createStatement();
				for (String s : sqls) {
					System.out.println(s);
					stat.addBatch(s);
				}				
				stat.executeBatch();
				BDUtil.getConexao().commit();
				BDUtil.getConexao().setAutoCommit(true);
				stat.close();
			}
			
			rs.close();
			ps.close();
			
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível cadastrar a Coleção.");
		}
	}

	private List<Colecao> listarColecoesPorConsulta(String sql) throws DataException {
		List<Colecao> lista = new ArrayList<Colecao>();

		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()) {
				Colecao c = new Colecao();
				
				c.setIdColecao(r.getInt("idColecao"));
				c.setNome(r.getString("nome"));
				c.setDescricao(r.getString("descricao"));
				c.setCreated(r.getDate("created"));
				c.setModified(r.getDate("modified"));
								
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
	
	public List<Colecao> listarColecoes() throws DataException {
		String sql = "SELECT * FROM colecao ORDER BY nome";
		return listarColecoesPorConsulta(sql);
	}

	public void adicionarMusicas(Colecao c, List<Musica> musicas) throws DataException {
		if (musicas == null || c == null) return;
		
		try {
			BDUtil.getConexao().setAutoCommit(false);
			Statement s = BDUtil.getConexao().createStatement();
			for (Musica m: musicas) {
				// checar se a música já está na coleção
				String sql = "SELECT * FROM musicacolecao WHERE idMusica = " + m.getIdMusica() + " AND idColecao = " + c.getIdColecao();
				ResultSet r = s.executeQuery(sql);
				if (r.next()) {
					r.close();
					continue;
				} else {
					sql = "INSERT INTO musicacolecao (idMusica, idColecao) VALUES (?, ?)";
					PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);
					ps.setInt(1, m.getIdMusica());
					ps.setInt(2, c.getIdColecao());
					ps.execute();
					ps.close();
					r.close();
				}
			}
			BDUtil.getConexao().commit();
			BDUtil.getConexao().setAutoCommit(true);
			
			s.close();
		} catch (SQLException e) {
			try {
				BDUtil.getConexao().setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DataException("Houve um erro ao adicionar as Músicas.");
			}
			e.printStackTrace();
			throw new DataException("Houve um erro ao adicionar as Músicas.");
		}
		
	}

	public void removerColecao(Colecao c) throws DataException {
		if (c == null) return;
		Statement stat;
		try {
			stat = BDUtil.getConexao().createStatement();
			String sql = "DELETE FROM colecao WHERE idColecao = " + c.getIdColecao();
			stat.execute(sql);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Erro ao Excluir a Coleção");
		}
	}

	public void removerMusicas(Colecao c, List<Musica> musicas)	throws DataException {
		if (musicas == null || c == null) return;
		
		try {
			BDUtil.getConexao().setAutoCommit(false);
			Statement stat = BDUtil.getConexao().createStatement();
			for (Musica m: musicas) {
				String sql = "DELETE FROM musicacolecao WHERE idColecao = " + c.getIdColecao() + " AND idMusica = " + m.getIdMusica();
				stat.execute(sql);
			}
			stat.close();
			BDUtil.getConexao().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BDUtil.getConexao().setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DataException("Erro ao remover as Músicas");
			}
			throw new DataException("Erro ao remover as Músicas");
		}
		
	}

	public Colecao getColecao(String nome) throws DataException {
		String sql = "SELECT * FROM colecao WHERE nome LIKE '" + nome + "'";
		List<Colecao> colecoes = listarColecoesPorConsulta(sql);
		if (colecoes != null && colecoes.size() > 0) return colecoes.get(0);
		return null; 
	}

}
