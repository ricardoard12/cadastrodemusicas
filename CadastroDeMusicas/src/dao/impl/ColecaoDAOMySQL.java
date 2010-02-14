package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.BDUtil;

import classesbasicas.Assunto;
import classesbasicas.Colecao;
import classesbasicas.Musica;
import dao.ColecaoDAO;
import exceptions.DataException;

public class ColecaoDAOMySQL implements ColecaoDAO {

	public void alterarColecao(Colecao c) throws DataException {
		
	}

	// insere uma nova coleção (sem músicas)
	public int cadastrarColecao(Colecao c) throws DataException {
		String sql = "INSERT INTO colecao (nome, descricao) "
			+ "VALUES (?, ?)";

		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);

			ps.setString(1, c.getNome());
			if (c.getDescricao() == null || c.getDescricao().length() <= 0) {
				ps.setNull(2, java.sql.Types.VARCHAR);
			} else {
				ps.setString(2, c.getDescricao());	
			}			
			
			ps.execute();

			System.out.println("Coleção cadastrada");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			c.setIdColecao(codigo);			
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
								
				lista.add(c);
			}
			
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
					continue;
				} else {
					sql = "INSERT INTO musicacolecao (idMusica, idColecao) VALUES (?, ?)";
					PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);
					ps.setInt(1, m.getIdMusica());
					ps.setInt(2, c.getIdColecao());
					ps.execute();
				}
			}
			BDUtil.getConexao().commit();
			BDUtil.getConexao().setAutoCommit(true);
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

}
