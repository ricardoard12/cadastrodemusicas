package dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.Util;

import bd.BDUtil;
import classesbasicas.Assunto;
import classesbasicas.Cantor;
import classesbasicas.Colecao;
import classesbasicas.Musica;
import dao.AssuntoDAO;
import dao.CantorDAO;
import dao.MusicaDAO;
import dao.QualidadeDAO;
import dao.TipoDAO;
import exceptions.DataException;

public class MusicaDAOMySQL implements MusicaDAO {

	public void alterarMusica(Musica m) throws DataException {		
		try {
			// Removendo os cantores antigos da música
			String sql = "DELETE FROM musicaCantor WHERE idMusica = " + m.getIdMusica();
			PreparedStatement stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Cantores antigos removidos da música");

			// Removendo os assuntos antigos da música
			sql = "DELETE FROM musicaAssunto WHERE idMusica = "
					+ m.getIdMusica();
			stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Assuntos antigos removidos da música");

			sql = "UPDATE Musica SET nome=?, letra=?, duracao=?, observacao=?, "
					+ "nomearquivo=?, diretorio=?, idTipo=?, idQualidade=?, chaveUnica=?, ano = ?, modified = ? "
					+ "WHERE idMusica=?";

			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);

			ps.setString(1, m.getNome());
			ps.setString(2, m.getLetra());
			if (m.getDuracao() > 0) {
				ps.setInt(3, m.getDuracao());
			} else {
				ps.setNull(3, java.sql.Types.INTEGER);
			}
			ps.setString(4, m.getObservacao());
			ps.setString(5, m.getNomeArquivo());
			ps.setString(6, m.getDiretorio());
			if (m.getTipo() != null && m.getTipo().getIdTipo() > 0) {
				ps.setInt(7, m.getTipo().getIdTipo());
			} else {
				ps.setNull(7, java.sql.Types.INTEGER);
			}
			if (m.getQualidade() != null
					&& m.getQualidade().getIdQualidade() > 0) {
				ps.setInt(8, m.getQualidade().getIdQualidade());
			} else {
				ps.setNull(8, java.sql.Types.INTEGER);
			}
			if (m.getChaveUnica() == null) {
				ps.setNull(9, java.sql.Types.VARCHAR);
			} else {
				ps.setString(9, m.getChaveUnica());
			}
			if (m.getAno() > 1900 && m.getAno() < 3000) {
				ps.setInt(10, m.getAno());
			} else {
				ps.setNull(10, java.sql.Types.INTEGER);
			}
			
			Date modified = new Date();
			ps.setTimestamp(11, new Timestamp((modified.getTime())));	
			
			ps.setInt(12, m.getIdMusica());

			ps.execute();
			m.setModified(modified);

			if (m.getCantores() != null && m.getCantores().size() > 0) {
				sql = "INSERT INTO MusicaCantor (idCantor, idMusica) "
						+ "VALUES (?, ?)";
				ps = BDUtil.getConexao().prepareStatement(sql);
				ps.setInt(2, m.getIdMusica());

				for (int i = 0; i < m.getCantores().size(); i++) {
					ps.setInt(1, m.getCantores().get(i).getIdCantor());

					System.out.println("idMusica = " + m.getIdMusica()
							+ ", idCantor = "
							+ m.getCantores().get(i).getIdCantor());

					ps.execute();
				}
			}

			// cadastrando os assuntos
			if (m.getAssuntos() != null && m.getAssuntos().size() > 0) {
				for (Assunto a : m.getAssuntos()) {
					sql = "INSERT INTO MusicaAssunto (idAssunto, idMusica) "
							+ "VALUES (?, ?)";

					ps = BDUtil.getConexao().prepareStatement(sql);

					ps.setInt(1, a.getIdAssunto());
					ps.setInt(2, m.getIdMusica());

					ps.execute();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int cadastrarMusica(Musica m) throws DataException {
		String sql = "INSERT INTO Musica (nome, letra, duracao, observacao, nomearquivo, diretorio, idTipo, idQualidade, chaveUnica, ano, created, modified, tipoarquivo) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, m.getNome());
			ps.setString(2, m.getLetra());
			if (m.getDuracao() > 0) {
				ps.setInt(3, m.getDuracao());
			} else {
				ps.setNull(3, java.sql.Types.INTEGER);
			}
			ps.setString(4, m.getObservacao());
			ps.setString(5, m.getNomeArquivo());
			ps.setString(6, m.getDiretorio());
			if (m.getTipo() != null && m.getTipo().getIdTipo() > 0) {
				ps.setInt(7, m.getTipo().getIdTipo());
			} else {
				ps.setNull(7, java.sql.Types.INTEGER);
			}
			if (m.getQualidade() != null && m.getQualidade().getIdQualidade() > 0) {
				ps.setInt(8, m.getQualidade().getIdQualidade());
			} else {
				ps.setNull(8, java.sql.Types.INTEGER);
			}
			if (m.getChaveUnica() == null) {
				ps.setNull(9, java.sql.Types.VARCHAR);
			} else {
				ps.setString(9, m.getChaveUnica());
			}			
			if (m.getAno() > 1900 && m.getAno() < 3000) {
				ps.setInt(10, m.getAno());
			} else {
				ps.setNull(10, java.sql.Types.INTEGER);
			}
			
			Date data = new Date();
			ps.setTimestamp(11, new Timestamp(data.getTime()));
			ps.setTimestamp(12, new Timestamp(data.getTime()));
			
			ps.setInt(13, m.getTipoArquivo());
			
			ps.execute();

			System.out.println("Musica cadastrada");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int codigo = rs.getInt("GENERATED_KEY");			
			m.setIdMusica(codigo);
			m.setCreated(data);
			m.setModified(data);
			
			// cadastrando os cantores
			if (m.getCantores() != null && m.getCantores().size() > 0) {
				sql = "INSERT INTO MusicaCantor (idCantor, idMusica) "
					+ "VALUES (?, ?)";
				ps = BDUtil.getConexao().prepareStatement(sql);
				ps.setInt(2, m.getIdMusica());
				
				for (int i = 0; i < m.getCantores().size(); i++) {					
					ps.setInt(1, m.getCantores().get(i).getIdCantor());					
					
					System.out.println("idMusica = " + m.getIdMusica() + ", idCantor = " + m.getCantores().get(i).getIdCantor());
					
					ps.execute();
				}
			}
			
			// cadastrando os assuntos
			if (m.getAssuntos() != null && m.getAssuntos().size() > 0) {
				for (Assunto a: m.getAssuntos()) {
					sql = "INSERT INTO MusicaAssunto (idAssunto, idMusica) "
						+ "VALUES (?, ?)";
					
					ps = BDUtil.getConexao().prepareStatement(sql);
					
					ps.setInt(1, a.getIdAssunto());
					ps.setInt(2, m.getIdMusica());
					
					ps.execute();
				}
			}
			
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível cadastrar a musica.");
		}
	}
	
	private List<Musica> listarMusicasPorConsulta(String sql) throws DataException {
		List<Musica> lista = new ArrayList<Musica>();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()) {
				Musica m = new Musica();
				
				m.setIdMusica(r.getInt("idMusica"));
				m.setNome(r.getString("nome"));
				m.setLetra(r.getString("letra"));
				m.setDuracao(r.getInt("duracao"));
				m.setObservacao(r.getString("observacao"));
				m.setDiretorio(r.getString("diretorio"));
				m.setNomeArquivo(r.getString("nomearquivo"));
				m.setChaveUnica(r.getString("chaveUnica"));
				m.setAno(r.getInt("ano"));
				m.setNomeArquivoCapa(r.getString("nomeArquivoCapa"));
				m.setCreated(r.getDate("created"));
				m.setModified(r.getDate("modified"));
				m.setTipoArquivo(r.getInt("tipoarquivo"));
				
				m.setCantores(new ArrayList<Cantor>());
				
				// setando a qualidade e o tipo
				int qualidade = 0;
				int tipo = 0;
				
				qualidade = r.getInt("idQualidade");
				if (r.wasNull()) {
					m.setQualidade(null);
				} else {
					QualidadeDAO qualidadeDAO = new QualidadeDAOMySQL();
					m.setQualidade(qualidadeDAO.getQualidade(qualidade));
				}
				tipo = r.getInt("idTipo");
				if (r.wasNull()) {
					m.setTipo(null);
				} else {
					TipoDAO tipoDAO = new TipoDAOMySQL();
					m.setTipo(tipoDAO.getTipo(tipo));
				}
					
				// pegar mais os cantores e os assuntos
				m.setCantores(new ArrayList<Cantor>());
				m.setAssuntos(new ArrayList<Assunto>());
				
				sql = "SELECT * FROM musicacantor WHERE idMusica = " + m.getIdMusica();
				Statement st = BDUtil.getConexao().createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				while (rs.next()) {
					int idCantor = rs.getInt("idCantor");
					
					CantorDAO cantorDAO = new CantorDAOMySQL();
					m.getCantores().add(cantorDAO.getCantor(idCantor));
				}
				
				sql = "SELECT * FROM musicaassunto inner join assunto on musicaAssunto.idAssunto = assunto.idAssunto " +
						"WHERE idMusica = " + m.getIdMusica() + " order by assunto.assunto";
				rs = st.executeQuery(sql);
				
				while(rs.next()) {
					int idAssunto = rs.getInt("idAssunto");
					
					AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
					m.getAssuntos().add(assuntoDAO.getAssunto(idAssunto));
				}				
				
				lista.add(m);				
			}
			
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível realizar a operação");
		}
	}

	public Musica getMusica(int idMusica) throws DataException {
		String sql = "SELECT * FROM musica where idMusica = " + idMusica + " ORDER BY nome";
		
		List<Musica> lista = listarMusicasPorConsulta(sql);
		
		if (lista.size() > 0) return lista.get(0);
		else return null;
	}
	
	public List<Musica> listarMusicasEmOrdemAlfabetica() throws DataException {
		String sql = "SELECT * FROM musica ORDER BY nome";
		
		return listarMusicasPorConsulta(sql);
	}

	public void removerMusica(Musica m) throws DataException {
		
		try {
			// Removendo os cantores antigos da música
			String sql = "DELETE FROM musicaCantor WHERE idMusica = " + m.getIdMusica();
			PreparedStatement stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Cantores antigos removidos da música");

			// Removendo os assuntos antigos da música
			sql = "DELETE FROM musicaAssunto WHERE idMusica = "
					+ m.getIdMusica();
			stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Assuntos antigos removidos da música");
			
			sql = "DELETE FROM musica WHERE idMusica = " + m.getIdMusica();
			stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Música removida do Banco de Dados");
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível remover a música do BD");
		}
		
	}

	public void adicionarCantor(Musica m, Cantor c) throws DataException {
		String sql = "INSERT INTO MusicaCantor (idCantor, idMusica) "
			+ "VALUES (?, ?)";
		PreparedStatement ps;
		
		try {
			ps = BDUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, c.getIdCantor());
			ps.setInt(2, m.getIdMusica());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public List<Musica> listarMusicasPorDiversos(
			String nome, boolean naoListarPorNome,
			String nomeCantor, boolean naoListarPorNomeCantor,
			String ritmo, boolean naoListarPorRitmo,
			String assunto, boolean naoListarPorAssunto,
			String observacao, boolean naoListarPorObservacao,
			String qualidade, boolean naoListarPorQualidade,
			String letra, boolean naoListarPorLetra,
			int ano, boolean naoListarPorAno) throws DataException {
		
		String sql = "select * from	((((((musica left join musicacantor on musica.idMusica = musicacantor.idMusica) " + 
			"left join cantor on musicacantor.idcantor = cantor.idcantor) " +
			"left join tipo on musica.idTipo = tipo.idTipo) " +
			"left join musicaassunto on musica.idMusica = musicaassunto.idMusica) " +
			"left join assunto on musicaassunto.idAssunto = assunto.idAssunto) " +
			"left join qualidade on musica.idQualidade = qualidade.idQualidade) " +
			"where (musica.nome like '%" + nome + "%' or '" + naoListarPorNome + "' like 'true') and " +
					"(musica.observacao like '%" + observacao + "%' or '" + naoListarPorObservacao + "' like 'true') and " +
					"(musica.letra like '%" + letra + "%' or '" + naoListarPorLetra + "' like 'true') and " +
					"(musica.ano = " + ano + " or '" + naoListarPorAno + "' like 'true') ";
						
		if (!naoListarPorNomeCantor) {
			sql += "and (cantor.nome like '%" + nomeCantor + "%') ";
		}
		
		if (!naoListarPorRitmo) {
			sql += "and (tipo.tipo like '%" + ritmo + "%') ";
		}
		
		if (!naoListarPorAssunto) {
			sql += "and (assunto.assunto like '" + assunto + "') ";
		}	
		
		if (!naoListarPorQualidade) {
			sql += "and (qualidade.qualidade like '%" + qualidade + "%') ";
		}
				
		sql +=  "group by musica.idMusica order by musica.nome";
						
		System.out.println(sql);
		
		return listarMusicasPorConsulta(sql);
	}

	public List<Musica> listarMusicasOrdenarPorCantor() throws DataException {
		String sql = "SELECT * FROM (musica inner join musicacantor on musica.idMusica = musicacantor.idMusica) " +
				"inner join cantor on musicacantor.idCantor = cantor.idCantor " +
				"ORDER BY cantor.nome, musica.nome";
		
		return listarMusicasPorConsulta(sql);
	}

	public List<Musica> listarMusicasOrdenarPorAssunto() throws DataException {
		String sql = "SELECT * FROM (musica right join musicaAssunto on musica.idMusica = musicaassunto.idMusica) " +
		"inner join assunto on musicaassunto.idAssunto = assunto.idAssunto " +
		"ORDER BY assunto.assunto, musica.nome";

		return listarMusicasPorConsulta(sql);
	}

	public List<Musica> listarMusicasSemChaveUnica() throws DataException {
		String sql = "SELECT * FROM musica WHERE chaveUnica IS NULL";
		return listarMusicasPorConsulta(sql);
	}

	public List<Musica> listarMusicasDaColecaoPorDiversos(String nome,
			boolean naoListarPorNome, String nomeCantor,
			boolean naoListarPorNomeCantor, String ritmo,
			boolean naoListarPorRitmo, String assunto,
			boolean naoListarPorAssunto, String observacao,
			boolean naoListarPorObservacao, String qualidade,
			boolean naoListarPorQualidade, String letra,
			boolean naoListarPorLetra, int ano, boolean naoListarPorAno, Colecao colecao) throws DataException {
		if (colecao == null) return null;
		else {
			String sql = "select * from	(((((((musica left join musicacantor on musica.idMusica = musicacantor.idMusica) " +
			"inner join musicacolecao on musicacolecao.idMusica = musica.idMusica AND musicacolecao.idColecao = " + colecao.getIdColecao() + ") " +
			"left join cantor on musicacantor.idcantor = cantor.idcantor) " +
			"left join tipo on musica.idTipo = tipo.idTipo) " +
			"left join musicaassunto on musica.idMusica = musicaassunto.idMusica) " +
			"left join assunto on musicaassunto.idAssunto = assunto.idAssunto) " +
			"left join qualidade on musica.idQualidade = qualidade.idQualidade) " +
			"where (musica.nome like '%" + nome + "%' or '" + naoListarPorNome + "' like 'true') and " +
					"(musica.observacao like '%" + observacao + "%' or '" + naoListarPorObservacao + "' like 'true') and " +
					"(musica.letra like '%" + letra + "%' or '" + naoListarPorLetra + "' like 'true') and " +
					"(musica.ano = " + ano + " or '" + naoListarPorAno + "' like 'true') ";
						
		if (!naoListarPorNomeCantor) {
			sql += "and (cantor.nome like '%" + nomeCantor + "%') ";
		}
		
		if (!naoListarPorRitmo) {
			sql += "and (tipo.tipo like '%" + ritmo + "%') ";
		}
		
		if (!naoListarPorAssunto) {
			sql += "and (assunto.assunto like '" + assunto + "') ";
		}	
		
		if (!naoListarPorQualidade) {
			sql += "and (qualidade.qualidade like '%" + qualidade + "%') ";
		}
				
		sql +=  "group by musica.idMusica order by ordem, musica.nome";
						
		System.out.println(sql);
		
		return listarMusicasPorConsulta(sql);
		}
	}

	public void alterarCapaDiscoMusica(Musica m, String nomeArquivo, String caminhoArquivoImagem) throws DataException {
		String sql = "UPDATE musica set nomeArquivoCapa = ?, arquivoCapa = ? WHERE idMusica = " + m.getIdMusica();
		FileInputStream fis = null;
		
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);
			
			if (nomeArquivo != null) {
				ps.setString(1, nomeArquivo);
				File file = new File(caminhoArquivoImagem);
				fis = new FileInputStream(file);
				ps.setBinaryStream(2, fis, (int) file.length());
			} else {
				ps.setNull(1, java.sql.Types.VARCHAR);
				ps.setNull(2, java.sql.Types.BLOB);
			}

			ps.execute();
			if (fis != null) {
				fis.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possivel alterar a imagem da capa");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new DataException("Não foi possivel alterar a imagem da capa");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getCapaDiscoMusica(Musica m) throws DataException {
		String sql = "SELECT arquivoCapa FROM musica WHERE idMusica = " + m.getIdMusica();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();			
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				return rs.getBinaryStream("arquivoCapa");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possivel ler a imagem da capa");
		}
	}

	public Musica listarMusicasPorChaveUnica(String chaveUnica)
			throws DataException {
		String sql = "SELECT * FROM musica where chaveUnica LIKE '" + chaveUnica + "' ORDER BY nome";
		
		List<Musica> lista = listarMusicasPorConsulta(sql);
		
		if (lista.size() > 0) return lista.get(0);
		else return null;
	}
	
	public int exportarArquivoCapa(Musica musica, String caminhoArquivo) throws DataException {
		InputStream is = getCapaDiscoMusica(musica);
		
		if (is != null) {
			if (Util.copyFile(is, caminhoArquivo)) {
				return ARQUIVO_CAPA_COPIADO_OK;
			} else {
				return ARQUIVO_CAPA_ERRO_COPIA;
			}
		} else {
			return ARQUIVO_CAPA_INEXISTENTE;
		}
	}
}
