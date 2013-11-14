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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import util.Util;
import bd.BDUtil;
import classesbasicas.Assunto;
import classesbasicas.Cantor;
import classesbasicas.Colecao;
import classesbasicas.Constantes;
import classesbasicas.Musica;
import dao.AssuntoDAO;
import dao.CantorDAO;
import dao.MusicaDAO;
import dao.QualidadeDAO;
import dao.TipoDAO;
import exceptions.DataException;

public class MusicaDAOMySQL implements MusicaDAO {
	
	private static Map<Integer, Integer> musicaCantor = null;
	private static Map<Integer, List<Integer>> musicaAssunto = null;
	
	private static QualidadeDAO qualidadeDAO = new QualidadeDAOMySQL();
	private static TipoDAO tipoDAO = new TipoDAOMySQL();
	private static CantorDAO cantorDAO = new CantorDAOMySQL();
	private static AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
	
	private void limparListas()
	{
		if (musicaCantor != null)
		{
			musicaCantor.clear();
			musicaCantor = null;
		}

		if (musicaAssunto != null)
		{
			musicaAssunto.clear();
			musicaAssunto = null;			
		}
	}
	
	public void alterarMusica(Musica m) throws DataException {	
		limparListas();
		
		try {
			// Removendo os cantores antigos da música
			String sql = "DELETE FROM musicaCantor WHERE idMusica = " + m.getIdMusica();
			PreparedStatement stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Cantores antigos removidos da música");
			stat.close();

			// Removendo os assuntos antigos da música
			sql = "DELETE FROM musicaAssunto WHERE idMusica = "
					+ m.getIdMusica();
			stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Assuntos antigos removidos da música");
			stat.close();

			sql = "UPDATE Musica SET nome=?, letra=?, duracao=?, observacao=?, "
					+ "idarquivomusica=?, diretorio=?, idTipo=?, idQualidade=?, chaveUnica=?, ano = ?, modified = ? "
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
			ps.setInt(5, m.getIdArquivoMusica());
			ps.setString(6, "");
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
			ps.close();
			
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
				ps.close();
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
					ps.close();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int cadastrarMusica(Musica m) throws DataException {
		String sql = "INSERT INTO Musica (nome, letra, duracao, observacao, idarquivomusica, diretorio, idTipo, idQualidade, chaveUnica, ano, created, modified, tipoarquivo) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		limparListas();
		
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
			ps.setInt(5, m.getIdArquivoMusica());
			ps.setString(6, "");
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
			
			rs.close();
			ps.close();
			
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
				ps.close();
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
					ps.close();
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
				m.setIdArquivoMusica(r.getInt("idarquivomusica"));
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
					m.setQualidade(qualidadeDAO.getQualidade(qualidade));
				}
				tipo = r.getInt("idTipo");
				if (r.wasNull()) {
					m.setTipo(null);
				} else {
					m.setTipo(tipoDAO.getTipo(tipo));
				}
					
				// pegar mais os cantores e os assuntos
				m.setCantores(new ArrayList<Cantor>());
				m.setAssuntos(new ArrayList<Assunto>());
				
				if (musicaCantor == null)
				{
					sql = "SELECT * FROM musicacantor";
					Statement st = BDUtil.getConexao().createStatement();
					ResultSet rs = st.executeQuery(sql);
					
					musicaCantor = new HashMap<Integer, Integer>();
					while (rs.next()) {
						int idCantor = rs.getInt("idCantor");
						int idMusica = rs.getInt("idMusica");
						
						musicaCantor.put(idMusica, idCantor);
					}	
					
					rs.close();
					st.close();
				}

				if (musicaCantor.containsKey(m.getIdMusica()))
				{
					int idCantor = musicaCantor.get(m.getIdMusica());
					
					m.getCantores().add(cantorDAO.getCantor(idCantor));
				}
				
				if (musicaAssunto == null)
				{
					sql = "SELECT * FROM musicaassunto";
					Statement st = BDUtil.getConexao().createStatement();
					ResultSet rs = st.executeQuery(sql);
					
					musicaAssunto = new HashMap<Integer, List<Integer>>();
					while (rs.next()) {
						int idAssunto = rs.getInt("idAssunto");
						int idMusica = rs.getInt("idMusica");

						List<Integer> assuntos = null;
						if (musicaAssunto.containsKey(idMusica))
						{
							 assuntos = musicaAssunto.get(idMusica);
						} else {
							assuntos = new ArrayList<Integer>();
							musicaAssunto.put(idMusica, assuntos);
						}
						assuntos.add(idAssunto);
					}
					
					rs.close();
					st.close();
				}

				if (musicaAssunto.containsKey(m.getIdMusica()))
				{
					for (int idAssunto: musicaAssunto.get(m.getIdMusica()))
					{
						m.getAssuntos().add(assuntoDAO.getAssunto(idAssunto));	
					}
				}				
				
				lista.add(m);				
			}
			
			r.close();
			s.close();
			
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
		limparListas();
		
		try {
			// Removendo os cantores antigos da música
			String sql = "DELETE FROM musicaCantor WHERE idMusica = " + m.getIdMusica();
			PreparedStatement stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Cantores antigos removidos da música");
			stat.close();

			// Removendo os assuntos antigos da música
			sql = "DELETE FROM musicaAssunto WHERE idMusica = "
					+ m.getIdMusica();
			stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Assuntos antigos removidos da música");
			stat.close();
			
			sql = "DELETE FROM musica WHERE idMusica = " + m.getIdMusica();
			stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("Música removida do Banco de Dados");
			stat.close();
			
			// Removendo o arquivo da musica do banco de dados
			sql = "DELETE FROM arquivomusica WHERE id = " + m.getIdArquivoMusica();
			stat = BDUtil.getConexao().prepareStatement(sql);
			stat.execute();
			System.out.println("arquivo da Música removida do Banco de Dados");
			stat.close();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível remover a música do BD");
		}
		
	}

	public void adicionarCantor(Musica m, Cantor c) throws DataException {
		PreparedStatement ps;
		
		String sql = "INSERT INTO MusicaCantor (idCantor, idMusica) "
			+ "VALUES (?, ?)";

		limparListas();
		
		try {
			ps = BDUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, c.getIdCantor());
			ps.setInt(2, m.getIdMusica());
			
			ps.execute();
			
			ps.close();
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
			int ano, boolean naoListarPorAno,
			int tipoArquivo, boolean naoListarPorTipoArquivo,
			Hashtable<String, List<String>> filtros) throws DataException {
		
		String sql = "select * from	((((((musica left join musicacantor on musica.idMusica = musicacantor.idMusica) " + 
			"left join cantor on musicacantor.idcantor = cantor.idcantor) " +
			"left join tipo on musica.idTipo = tipo.idTipo) " +
			"left join musicaassunto on musica.idMusica = musicaassunto.idMusica) " +
			"left join assunto on musicaassunto.idAssunto = assunto.idAssunto) " +
			"left join qualidade on musica.idQualidade = qualidade.idQualidade) " +
			"where (musica.nome like '%" + nome + "%' or '" + naoListarPorNome + "' like 'true') and " +
					"(musica.observacao like '%" + observacao + "%' or '" + naoListarPorObservacao + "' like 'true') and " +
					"(musica.letra like '%" + letra + "%' or '" + naoListarPorLetra + "' like 'true') and " +
					"(musica.ano = " + ano + " or '" + naoListarPorAno + "' like 'true') and " +
					"(musica.tipoarquivo = " + tipoArquivo + " or '" + naoListarPorTipoArquivo + "' like 'true') ";
						
		if (!naoListarPorNomeCantor) {
			sql += "and (cantor.nome like '%" + nomeCantor + "%') ";
		}
		
		/*if (!naoListarPorRitmo) {
			sql += "and (tipo.tipo like '%" + ritmo + "%') ";
		}
		
		if (!naoListarPorAssunto) {
			sql += "and (assunto.assunto like '" + assunto + "') ";
		}*/	
		
		if (filtros != null)
		{
			// verificando as qualidades selecionadas
			List<String> qualidades = filtros.get(Constantes.CAMPO_PROCURA_QUALIDADE);
			if (qualidades != null && qualidades.size() > 0)
			{
				sql += "and (0";
				for (String q: qualidades)
				{
					int qualidadeId = qualidadeDAO.getQualidadeId(q);
					sql += " or qualidade.idQualidade = " + qualidadeId;
				}	
				sql += ") ";
			}
			
			// verificando os assuntos selecionados
			List<String> assuntos = filtros.get(Constantes.CAMPO_PROCURA_ASSUNTO);
			if (assuntos != null && assuntos.size() > 0)
			{
				sql += "and (0";
				for (String a: assuntos)
				{
					sql += " or assunto.assunto like '" + a + "'";
				}	
				sql += ") ";
			}
			
			// verificando os ritmos selecionados
			List<String> ritmos = filtros.get(Constantes.CAMPO_PROCURA_RITMO);
			if (ritmos != null && ritmos.size() > 0)
			{
				sql += "and (0";
				for (String r: ritmos)
				{
					sql += " or tipo.tipo like '" + r + "'";
				}	
				sql += ") ";
			}
			
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
			boolean naoListarPorLetra, int ano, boolean naoListarPorAno, 
			int tipoArquivo, boolean naoListarPorTipoArquivo, Colecao colecao) throws DataException {
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
					"(musica.ano = " + ano + " or '" + naoListarPorAno + "' like 'true') and " + 
					"(musica.tipoarquivo = " + tipoArquivo + " or '" + naoListarPorTipoArquivo + "' like 'true') ";
						
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
			ps.close();
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

	public int getCapaDiscoMusica(Musica m, String caminhoArquivo) throws DataException {
		String sql = "SELECT arquivoCapa FROM musica WHERE idMusica = " + m.getIdMusica();

		try {
			Statement s = BDUtil.getConexao().createStatement();			
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				InputStream is = rs.getBinaryStream("arquivoCapa");
				if (is != null)
				{
					if (Util.copyFile(is, caminhoArquivo)) {
						is.close();
						return ARQUIVO_CAPA_COPIADO_OK;
					} else {
						is.close();
						return ARQUIVO_CAPA_ERRO_COPIA;
					}
				} else {
					return ARQUIVO_CAPA_INEXISTENTE;
				}
			} else {
				return ARQUIVO_CAPA_INEXISTENTE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Não foi possivel ler a imagem da capa (3)");
		}
	}

	public Musica listarMusicasPorChaveUnica(String chaveUnica)
			throws DataException {
		String sql = "SELECT * FROM musica where chaveUnica LIKE '" + chaveUnica + "' ORDER BY nome";
		
		List<Musica> lista = listarMusicasPorConsulta(sql);
		
		if (lista.size() > 0) return lista.get(0);
		else return null;
	}

	public int cadastrarArquivoMusica(File arquivo) throws DataException {
		Statement stat;
		int id = 0;
		
		try {
			stat = BDUtil.getConexao().createStatement();
			String fPath = arquivo.getAbsolutePath().replaceAll("(\\\\|" + File.pathSeparator + ")", "/").replaceAll("(\')", "\\\\'");
			
			String sql = "INSERT INTO arquivomusica (arquivomusica) VALUES(LOAD_FILE(\'" + fPath + "\'));";
			
			stat.execute(sql, Statement.RETURN_GENERATED_KEYS);

			ResultSet id_arquivo = stat.getGeneratedKeys();
			id_arquivo.next();
			
			id = id_arquivo.getInt(1);
			
			id_arquivo.close();
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataException();
		}
		
		return id;
	}

	public int getArquivoMusica(Musica m, String caminhoArquivo) throws DataException {
		String sql = "SELECT arquivomusica FROM arquivomusica WHERE id = " + m.getIdArquivoMusica();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();			
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				InputStream is = rs.getBinaryStream("arquivomusica");
				if (is != null)
				{
					if (Util.copyFile(is, caminhoArquivo)) {
						is.close();
						return ARQUIVO_MUSICA_COPIADO_OK;
					} else {
						is.close();
						return ARQUIVO_MUSICA_ERRO_COPIA;
					}
				} else {
					return ARQUIVO_MUSICA_INEXISTENTE;
				}
			} else {
				return ARQUIVO_MUSICA_INEXISTENTE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Não foi possivel ler o arquivo da musica");
		}
	}
}
