package fachada;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import util.Util;
import bd.BDUtil;
import classesbasicas.Assunto;
import classesbasicas.Cantor;
import classesbasicas.Colecao;
import classesbasicas.Constantes;
import classesbasicas.Log;
import classesbasicas.Musica;
import classesbasicas.MusicaNaColecao;
import classesbasicas.Playlist;
import classesbasicas.Qualidade;
import classesbasicas.Tipo;
import dao.AssuntoDAO;
import dao.CantorDAO;
import dao.ColecaoDAO;
import dao.LogDAO;
import dao.MusicaDAO;
import dao.PlaylistDAO;
import dao.QualidadeDAO;
import dao.TipoDAO;
import dao.impl.AssuntoDAOMySQL;
import dao.impl.CantorDAOMySQL;
import dao.impl.ColecaoDAOMySQL;
import dao.impl.LogDAOMySQL;
import dao.impl.MusicaDAOMySQL;
import dao.impl.PlaylistDAOMySQL;
import dao.impl.QualidadeDAOMySQL;
import dao.impl.TipoDAOMySQL;
import exceptions.DataException;



public class Fachada {
	
	// M�todos de listagem	
	public static List<Musica> listarMusicasPorDiversos(String nome, String nomeCantor, String ritmo, String assunto, String observacao,
			String qualidade, String letra, Colecao colecao, int ano, int tipoArquivo, Hashtable<String, List<String>> filtros) throws DataException {
		boolean naoListarPorNome = false;
		boolean naoListarPorNomeCantor = false;
		boolean naoListarPorRitmo = false;
		boolean naoListarPorAssunto = false;
		boolean naoListarPorObservacao = false;
		boolean naoListarPorQualidade = false;
		boolean naoListarPorLetra = false;
		boolean naoListarPorAno = false;
		boolean naoListarPorTipoArquivo = false;
		
		if (nome == null || nome.trim().equals("")) {
			naoListarPorNome = true;
		} else {
			nome = nome.trim();
		}

		if (nomeCantor == null || nomeCantor.trim().equals("")) {
			naoListarPorNomeCantor = true;
		} else {
			nomeCantor = nomeCantor.trim();
		}
		
		if (observacao == null || observacao.trim().equals("")) {
			naoListarPorObservacao = true;
		} else {
			observacao = observacao.trim();
		}
		
		if (letra == null || letra.trim().equals("")) {
			naoListarPorLetra = true;
		} else {
			letra = letra.trim();
		}
		
		if (ano <= 0) {
			naoListarPorAno = true;
		}
		
		if (tipoArquivo == Constantes.TIPO_ARQUIVO_TODOS || tipoArquivo == Constantes.TIPO_ARQUIVO_NAO_LISTAR) {
			naoListarPorTipoArquivo = true;
		}
		
		if (filtros == null || !filtros.containsKey(Constantes.CAMPO_PROCURA_QUALIDADE)) {
			naoListarPorQualidade = true;
		}
		
		if (filtros == null || !filtros.containsKey(Constantes.CAMPO_PROCURA_RITMO)) {
			naoListarPorRitmo = true;
		}

		if (filtros == null || !filtros.containsKey(Constantes.CAMPO_PROCURA_ASSUNTO)) {
			naoListarPorAssunto = true;
		}
		
		if (naoListarPorNome && naoListarPorNomeCantor && naoListarPorRitmo && naoListarPorAssunto && naoListarPorObservacao
				&& naoListarPorQualidade && naoListarPorLetra && naoListarPorTipoArquivo) {
			return new ArrayList<Musica>();
		} else {
			MusicaDAO musicaDAO = new MusicaDAOMySQL();

			if (colecao != null) {
				return musicaDAO.listarMusicasDaColecaoPorDiversos(
						nome, naoListarPorNome,
						nomeCantor, naoListarPorNomeCantor,
						ritmo, naoListarPorRitmo,
						assunto, naoListarPorAssunto,
						observacao, naoListarPorObservacao,
						qualidade, naoListarPorQualidade,
						letra, naoListarPorLetra, ano, naoListarPorAno, tipoArquivo, naoListarPorTipoArquivo, colecao);	
			} else {
				return musicaDAO.listarMusicasPorDiversos(
						nome, naoListarPorNome,
						nomeCantor, naoListarPorNomeCantor,
						ritmo, naoListarPorRitmo,
						assunto, naoListarPorAssunto,
						observacao, naoListarPorObservacao,
						qualidade, naoListarPorQualidade,
						letra, naoListarPorLetra, ano, naoListarPorAno, tipoArquivo, naoListarPorTipoArquivo, filtros);	
			}
		}
	}
	
	public static List<Log> listarLogs() throws DataException {
		LogDAO dao = new LogDAOMySQL();
		return dao.listarLogs();
	}
	
	public static List<Log> listarLogs(String chaveUnica) throws DataException {
		LogDAO dao = new LogDAOMySQL();
		return dao.listarLogs(chaveUnica);
	}
	
	public static List<Musica> listarMusicasSemChaveUnica() throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		return musicaDAO.listarMusicasSemChaveUnica();
	}
	
	public static List<Cantor> listarCantoresSemChaveUnica() throws DataException {
		CantorDAO cantorDAO = new CantorDAOMySQL();
		return cantorDAO.listarCantoresSemChaveUnica();
	}
	
	public static List<Assunto> listarAssuntosSemChaveUnica() throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		return assuntoDAO.listarAssuntosSemChaveUnica();
	}
	
	public static List<Tipo> listarTiposSemChaveUnica() throws DataException {
		TipoDAO tipoDAO = new TipoDAOMySQL();
		return tipoDAO.listarTiposSemChaveUnica();
	}
	
	public static List<Tipo> listarTipos() throws DataException {
		TipoDAO tipoDAO = new TipoDAOMySQL();
		
		return tipoDAO.listarTipos();
	}
	
	public static List<Tipo> listarTipos(int tipoArquivo) throws DataException {
		TipoDAO tipoDAO = new TipoDAOMySQL();
		return tipoDAO.listarTipos(tipoArquivo);
	}
	
	public static List<Tipo> listarTipos(String tipo) throws DataException {
		TipoDAO tipoDAO = new TipoDAOMySQL();
		return tipoDAO.listarTipos(tipo);
	}
	
	public static List<Tipo> listarTipos(String tipo, int tipoArquivo) throws DataException {
		TipoDAO tipoDAO = new TipoDAOMySQL();
		return tipoDAO.listarTipos(tipo, tipoArquivo);
	}
	
	public static List<Qualidade> listarQualidades() throws DataException {
		QualidadeDAO qualidadeDAO = new QualidadeDAOMySQL();
		
		return qualidadeDAO.listarQualidades();
	}
	
	public static List<Assunto> listarAssuntos() throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		return assuntoDAO.listarAssuntos();
	}
	
	public static List<Assunto> listarAssuntos(int tipoArquivo) throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		return assuntoDAO.listarAssuntos(tipoArquivo);
	}
	
	public static List<Assunto> listarAssuntos(String assunto) throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		return assuntoDAO.listarAssuntos(assunto);
	}
	
	public static List<Assunto> listarAssuntos(String assunto, int tipoArquivo) throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		return assuntoDAO.listarAssuntos(assunto, tipoArquivo);
	}	
	
	public static List<Cantor> listarCantoresPorDiversos(String nome, String nomeSemEspacos, int tipoArquivo) throws DataException {
		boolean naoListarPorNome = false;
		boolean naoListarPorNomeSemEspacos = false;
		boolean naoListarPorTipoArquivo = false;
		
		if (nome == null || nome.trim().equals("")) {
			naoListarPorNome = true;
		} else {
			nome = nome.trim();
		}
		
		if (nomeSemEspacos == null || nomeSemEspacos.trim().equals("")) {
			naoListarPorNomeSemEspacos = true;
		} else {
			nomeSemEspacos = nomeSemEspacos.trim();
		}
		
		if (tipoArquivo == Constantes.TIPO_ARQUIVO_TODOS || tipoArquivo == Constantes.TIPO_ARQUIVO_NAO_LISTAR) {
			naoListarPorTipoArquivo = true;
		}
			
		if (naoListarPorNome && naoListarPorNomeSemEspacos && naoListarPorTipoArquivo) {	
			return new ArrayList<Cantor>();
		} else {
			CantorDAO cantorDAO = new CantorDAOMySQL();
			return cantorDAO.listarCantoresPorDiversos(nome, naoListarPorNome, nomeSemEspacos, naoListarPorNomeSemEspacos, tipoArquivo, naoListarPorTipoArquivo);
		}
	}
	
	public static List<Cantor> listarCantoresPorNome(String nome) throws DataException {
		CantorDAO cantorDAO = new CantorDAOMySQL();
		return cantorDAO.listarCantoresPorNome(nome);
	}
	
	public static List<Cantor> listarCantoresPorNome(String nome, int tipoArquivo) throws DataException {
		CantorDAO cantorDAO = new CantorDAOMySQL();
		return cantorDAO.listarCantoresPorNome(nome, tipoArquivo);
	}
	
	public static Musica getMusica(int idMusica) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		return musicaDAO.getMusica(idMusica);
	}
	
	public static Cantor getCantor(int idCantor) throws DataException {
		CantorDAO cantorDAO = new CantorDAOMySQL();
		return cantorDAO.getCantor(idCantor);
	}
	
	public static Tipo getTipo(int idTipo) throws DataException {
		TipoDAO tipoDAO = new TipoDAOMySQL();
		return tipoDAO.getTipo(idTipo);
	}
	
	public static Assunto getAssunto(int idAssunto) throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		return assuntoDAO.getAssunto(idAssunto);
	}
	
	public static InputStream getCapaDiscoMusica(Musica m, String caminhoArquivo) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		
		if (musicaDAO.getCapaDiscoMusica(m, caminhoArquivo) == MusicaDAO.ARQUIVO_CAPA_COPIADO_OK)
		{
			try
			{
				return new FileInputStream(caminhoArquivo);	
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File NOT FOUND (capa disco musica)");
				return null;
			}
		} else {
			return null;
		}
	}

	public static int exportarArquivoMusica(Musica m, String caminhoArquivo) throws DataException
	{
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		return musicaDAO.getArquivoMusica(m, caminhoArquivo);
	}
	
	public static List<Musica> listarTodasAsMusicasEmOrdemAlfabetica() throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		return musicaDAO.listarMusicasEmOrdemAlfabetica();
	}
	
	public static List<Musica> listarTodasAsMusicasOrdenarPorCantor() throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		return musicaDAO.listarMusicasOrdenarPorCantor();
	}
	
	public static List<Musica> listarTodasAsMusicasOrdenarPorAssunto() throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		return musicaDAO.listarMusicasOrdenarPorAssunto();
	}
	
	public static Musica getMusicaPorChaveUnica(String chaveUnica) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		return musicaDAO.listarMusicasPorChaveUnica(chaveUnica);
	}
	
	public static Cantor getCantorPorChaveUnica(String chaveUnica) throws DataException {
		CantorDAO cantorDAO = new CantorDAOMySQL();
		return cantorDAO.getCantorPorChaveUnica(chaveUnica);
	}
	
	public static Assunto getAssuntoPorChaveUnica(String chaveUnica) throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		return assuntoDAO.getAssuntoPorChaveUnica(chaveUnica);
	}
	
	public static Tipo getTipoPorChaveUnica(String chaveUnica) throws DataException {
		TipoDAO tipoDAO = new TipoDAOMySQL();
		return tipoDAO.getTipoPorChaveUnica(chaveUnica);
	}
	
	public static List<Colecao> listarColecoes() throws DataException {
		ColecaoDAO cDAO = new ColecaoDAOMySQL();
		return cDAO.listarColecoes();
	}
	

	public static Colecao getColecao(String nome) throws DataException {
		ColecaoDAO cDAO = new ColecaoDAOMySQL();
		return cDAO.getColecao(nome);
	}	
	
	// M�todos de cadastro
	public static void cadastrarMusica(Musica m, File f) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		
		if (m.getChaveUnica() == null || m.getChaveUnica().equals("")) m.gerarChaveUnica();
		
		int id = musicaDAO.cadastrarArquivoMusica(f);
		if (id == 0)
		{
			throw new DataException();
		} else {
			m.setIdArquivoMusica(id);
		}
		
		musicaDAO.cadastrarMusica(m);
	}
	
	public static void substituirArquivoMusica(Musica m, File f) throws DataException
	{
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		
		if (m.getChaveUnica() == null || m.getChaveUnica().equals("")) m.gerarChaveUnica();
		
		int id = musicaDAO.cadastrarArquivoMusica(f);
		if (id == 0)
		{
			throw new DataException();
		} else {
			m.setIdArquivoMusica(id);
		}
		
		musicaDAO.alterarMusica(m);
	}


	
	public static void cadastrarCantor(Cantor c) throws DataException {
		CantorDAO cantorDAO = new CantorDAOMySQL();
		cantorDAO.cadastrarCantor(c);
	}	
	
	public static void cadastrarTipo(Tipo t) throws DataException {
		TipoDAO tipoDAO = new TipoDAOMySQL();
		tipoDAO.cadastrarTipo(t);
	}
	
	public static void cadastrarAssunto(Assunto a) throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		assuntoDAO.cadastrarAssunto(a);
	}

	public static void cadastrarQualidade(Qualidade q) throws DataException {
		QualidadeDAO qualidadeDAO = new QualidadeDAOMySQL();
		qualidadeDAO.cadastrarQualidade(q);
		
	}
	
	public static void cadastrarColecao(Colecao c) throws DataException {
		ColecaoDAO cDAO = new ColecaoDAOMySQL();
		cDAO.cadastrarColecao(c);
	}
	
	/*public static void salvarListaDeReproducao(List<Musica> musicas, int indiceAtual) {
		BDUtil.salvarListaReproducao(musicas, indiceAtual);
	}
	
	public static Map<String, Object> getListaReproducao() {
		return BDUtil.getListaReproducao();
	}*/
	
	// M�todos de exclus�o
	public static void excluirMusica(Musica m) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		musicaDAO.removerMusica(m);
	}
	
	public static void excluirAssunto(Assunto a) throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		assuntoDAO.removerAssunto(a);
	}

	public static void excluirTipo(Tipo t) throws DataException {
		TipoDAO tipoDAO = new TipoDAOMySQL();
		tipoDAO.removerTipo(t);
	}

	public static void excluirCantor(Cantor c) throws DataException {
		CantorDAO cantorDAO = new CantorDAOMySQL();
		cantorDAO.removerCantor(c);
	}
	
	public static void excluirColecao(Colecao c) throws DataException {
		ColecaoDAO cDAO = new ColecaoDAOMySQL();
		cDAO.removerColecao(c);
	}
	
	// Relat�rios
	static {
		// System.setProperty("jasper.reports.compiler.class", "net.sf.jasperreports.engine.design.JRJavacCompiler");
		System.setProperty("jasper.reports.compiler.class", "net.sf.jasperreports.engine.design.JRJdtCompiler");
	    // net.sf.jasperreports.engine.util.JRProperties.setProperty(net.sf.jasperreports.engine.util.JRProperties.COMPILER_CLASS, "net.sf.jasperreports.engine.design.JRJavacCompiler");
		// net.sf.jasperreports.engine.util.JRProperties.setProperty(net.sf.jasperreports.engine.util.JRProperties.COMPILER_CLASS, "net.sf.jasperreports.engine.design.JRJdtCompiler");
	}	
	
	public static void criaRelatorioMusicasOrdemAlfabetica() {
		JasperPrint jasperPrint = new JasperPrint();
		Connection conn = null;
		Map inputParameters = new HashMap();
		// inputParameters.put("sometitle","first report");
		conn = BDUtil.getConexao();
		try {			
			
			/*File dir = new File("relatorios");
			File arquivos[] = dir.listFiles();
			for (File arquivo: arquivos) {
				if (!arquivo.isDirectory()) {
					// System.out.println(arquivo.getPath().substring(0, arquivo.getPath().indexOf(".")) + ".jasper");
					JasperCompileManager.compileReportToFile(arquivo.getPath(), arquivo.getPath().substring(0, arquivo.getPath().indexOf(".")) + ".jasper");
				}
				
			}*/
			
			// JasperReport jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "MusicasOrdemAlfabetica.jrxml");
			// JasperCompileManager.compileReportToFile("relatorios" + File.separator + "MusicasOrdemAlfabetica.jrxml", "relatorios" + File.separator + "MusicasOrdemAlfabetica.jasper");
			// Object o = new Object();
			
			// System.out.println(o.getClass().getResource("/relatorios/MusicasOrdemAlfabetica.jasper"));
			jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "MusicasOrdemAlfabetica.jasper", inputParameters, conn);
			JasperViewer.viewReport(jasperPrint, false);
			// JasperExportManager.exportReportToPdfFile(jasperPrint, "teste.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void criaRelatorioMusicasPorCantor() {
		JasperPrint jasperPrint = new JasperPrint();
		Connection conn = null;
		Map inputParameters = new HashMap();
		// inputParameters.put("sometitle","first report");
		conn = BDUtil.getConexao();
		try {
			// JasperReport jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "MusicasPorCantor.jrxml");
			jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "MusicasPorCantor.jasper", inputParameters, conn);
			// for a pdf
			JasperViewer.viewReport(jasperPrint, false);
			// JasperExportManager.exportReportToPdfFile(jasperPrint, "teste.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void criaRelatorioCantoresComNumeroDeMusicas() {
		JasperPrint jasperPrint = new JasperPrint();
		Connection conn = null;
		Map inputParameters = new HashMap();
		// inputParameters.put("sometitle","first report");
		conn = BDUtil.getConexao();
		try {
			// JasperReport jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "CantoresComNumeroDeMusicas.jrxml");
			jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "CantoresComNumeroDeMusicas.jasper", inputParameters, conn);
			// for a pdf
			JasperViewer.viewReport(jasperPrint, false);
			// JasperExportManager.exportReportToPdfFile(jasperPrint, "teste.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void criaRelatorioColecaoPorOrdemAlfabetica(List<MusicaNaColecao> colecao,
			Map<String, String> parameters, boolean pdf, boolean html, String caminho) {
		JasperPrint jasperPrint = new JasperPrint();
		// inputParameters.put("sometitle","first report");
		parameters.put(Constantes.NUMERO_MUSICAS, "" + colecao.size());
		try {
			// JasperReport jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "ColecaoPorOrdemAlfabetica.jrxml");
			
			// TODO comentar essas linhas
			// JasperCompileManager.compileReportToFile("relatorios" + File.separator + "ColecaoPorOrdemAlfabetica.jrxml", "relatorios" + File.separator + "ColecaoPorOrdemAlfabetica.jasper");
			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(colecao);
			jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "ColecaoPorOrdemAlfabetica.jasper", parameters, ds);
			// jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
			// for a pdf
			// JasperViewer.viewReport(jasperPrint, false);
			
			if (pdf) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, caminho + File.separator + "MusicasPorOrdemAlfabetica.pdf");
			}
			
			if (html) {
				// jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "ColecaoPorOrdemAlfabeticaHTML.jrxml");
				ds = new JRBeanCollectionDataSource(colecao);
				jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "ColecaoPorOrdemAlfabetica.jasper", parameters, ds);
				JasperExportManager.exportReportToHtmlFile(jasperPrint, caminho + File.separator + "MusicasPorOrdemAlfabetica.html");
			}
			
			// gerando o arquivo xls
			FileWriter fw = new FileWriter(caminho + File.separator + "MusicasPorOrdemAlfabetica.xls");
			fw.write("Nome\tCantor\tRitmo\tDisco\tFaixa\tDura��o\tAssuntos\n\n");
			for (MusicaNaColecao mus: colecao) {
				fw.write((mus.getNomeMusica() == null ? "" : mus.getNomeMusica()) + "\t");
				fw.write((mus.getCantor() == null ? "" : mus.getCantor()) + "\t");
				fw.write((mus.getTipo() == null ? "" : mus.getTipo()) + "\t");
				fw.write(mus.getNumeroDVD() + "\t");
				fw.write(mus.getNumeroFaixa() + "\t");
				fw.write(Util.formataDuracao(mus.getDuracaoMusica()) + "\t");
				fw.write((mus.getAssunto() == null ? "" : mus.getAssunto()) + "\n");
			}
			fw.close();
			
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public static void criaRelatorioColecaoPorCantor(List<MusicaNaColecao> colecao,
			Map<String, String> parameters, boolean pdf, boolean html, String caminho) {
		JasperPrint jasperPrint = new JasperPrint();
		// inputParameters.put("sometitle","first report");
		try {
			// JasperReport jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "ColecaoPorCantores.jrxml");
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(colecao);
			jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "ColecaoPorCantores.jasper", parameters, ds);
			// for a pdf
			// JasperViewer.viewReport(jasperPrint, false);
			if (pdf) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, caminho + File.separator + "MusicasPorCantor.pdf");
			}
			
			if (html) {
				// jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "ColecaoPorCantoresHTML.jrxml");
				ds = new JRBeanCollectionDataSource(colecao);
				jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "ColecaoPorCantores.jasper", parameters, ds);
				JasperExportManager.exportReportToHtmlFile(jasperPrint, caminho + File.separator + "MusicasPorCantor.html");
			}
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void criaRelatorioColecaoPorAssunto(List<MusicaNaColecao> colecao,
			Map<String, String> parameters, boolean pdf, boolean html, String caminho) {
		JasperPrint jasperPrint = new JasperPrint();
		// inputParameters.put("sometitle","first report");
		try {
			// JasperReport jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "ColecaoPorAssuntos.jrxml");
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(colecao);
			jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "ColecaoPorAssuntos.jasper", parameters, ds);
			// for a pdf
			// JasperViewer.viewReport(jasperPrint, false);
			if (pdf) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, caminho + File.separator + "MusicasPorAssuntos.pdf");
			}
			
			if (html) {
				// jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "ColecaoPorAssuntosHTML.jrxml");
				ds = new JRBeanCollectionDataSource(colecao);
				jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "ColecaoPorAssuntos.jasper", parameters, ds);
				JasperExportManager.exportReportToHtmlFile(jasperPrint, caminho + File.separator + "MusicasPorAssuntos.html");
			}
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// M�todos de altera��o
	public static void alterarCantor(Cantor c) throws DataException {
		if (c == null) return;
		
		CantorDAO cantorDAO = new CantorDAOMySQL();
		cantorDAO.alterarCantor(c);	
	}
	
	public static void alterarAssunto(Assunto a) throws DataException {
		if (a == null) return;
		
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		assuntoDAO.alterarAssunto(a);	
	}
	
	public static void alterarTipo(Tipo t) throws DataException {
		if (t == null) return;
		
		TipoDAO tipoDAO = new TipoDAOMySQL();
		tipoDAO.alterarTipo(t);	
	}
	
	public static void adicionarCantorAMusica(Musica m, Cantor c) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		
		musicaDAO.adicionarCantor(m, c);
	}
	
	public static void adicionarMusicasAColecao(Colecao c, List<Musica> musicas) throws DataException {
		ColecaoDAO cDAO = new ColecaoDAOMySQL();
		cDAO.adicionarMusicas(c, musicas);
	}
	
	public static void removerMusicasDaColecao(Colecao c, List<Musica> musicas) throws DataException {
		ColecaoDAO cDAO = new ColecaoDAOMySQL();
		cDAO.removerMusicas(c, musicas);
	}
	
	public static void alterarMusica(Musica m) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		musicaDAO.alterarMusica(m);
	}
	
	public static void alterarColecao(Colecao c) throws DataException {
		ColecaoDAO colecaoDAO = new ColecaoDAOMySQL();
		colecaoDAO.alterarColecao(c);
	} 
	
	public static void dispararAlteracaoArquivoMusica(Musica m) {
		return;
	}
	
	public static void alterarCapaDiscoMusica(Musica m, String nomeArquivo, String caminhoArquivoImagem) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		musicaDAO.alterarCapaDiscoMusica(m, nomeArquivo, caminhoArquivoImagem);
	}
	
	// m�todos de Playlist
	public static Playlist getDefaultPlaylist() throws DataException {
		return getPlaylist(Playlist.NOME_PLAYLIST_PADRAO);
	}
	
	public static Playlist getPlaylist(String nome) throws DataException {
		PlaylistDAO pDAO = new PlaylistDAOMySQL();
		return pDAO.getPlaylist(nome);
	}
	
	public static void alterarPlaylist(Playlist p) throws DataException {
		PlaylistDAO pDAO = new PlaylistDAOMySQL();
		pDAO.alterarPlaylist(p);
	}
	
	public static List<Playlist> listarPlaylists() throws DataException {
		PlaylistDAO pDAO = new PlaylistDAOMySQL();
		return pDAO.listarPlaylists();
	}
	
	public static int adicionarPlaylist(Playlist p) throws DataException {
		PlaylistDAO pDAO = new PlaylistDAOMySQL();
		return pDAO.cadastrarPlaylist(p);
	}
	
	public static void removerPlaylist(Playlist p) throws DataException {
		PlaylistDAO pDAO = new PlaylistDAOMySQL();
		pDAO.removerPlaylist(p);
	}
	
	// m�todos de teste	
	public static void criaRelatorioDeTeste() {
		List<MusicaNaColecao> set = new ArrayList<MusicaNaColecao>();
		
		MusicaNaColecao m1 = new MusicaNaColecao();
		m1.setNomeMusica("Teste1");
		m1.setDuracaoMusica(30);
		
		MusicaNaColecao m2 = new MusicaNaColecao();
		m2.setNomeMusica("Teste2");
		m2.setDuracaoMusica(60);
		
		set.add(m1);
		set.add(m2);		
		
		JasperPrint jasperPrint = new JasperPrint();
		Map inputParameters = new HashMap();
		// inputParameters.put("sometitle","first report");
		try {
			// JasperReport jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "ColecaoTeste.jrxml");
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(set);
			jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "ColecaoTeste.jasper", inputParameters, ds);
			// for a pdf
			JasperViewer.viewReport(jasperPrint, false);
			// JasperExportManager.exportReportToPdfFile(jasperPrint, "teste.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// para testar alguns m�todos...
	public static void main(String[] args) {
		criaRelatorioDeTeste();
	}

}
