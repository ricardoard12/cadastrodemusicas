package fachada;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
	
	// Métodos de listagem	
	public static List<Musica> listarMusicasPorDiversos(String nome, String nomeCantor, String ritmo, String assunto, String observacao,
			String qualidade, String letra, Colecao colecao, int ano) throws DataException {
		boolean naoListarPorNome = false;
		boolean naoListarPorNomeCantor = false;
		boolean naoListarPorRitmo = false;
		boolean naoListarPorAssunto = false;
		boolean naoListarPorObservacao = false;
		boolean naoListarPorQualidade = false;
		boolean naoListarPorLetra = false;
		boolean naoListarPorAno = false;
		
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
		
		if (ritmo == null || ritmo.trim().equals("")) {
			naoListarPorRitmo = true;
		} else {
			ritmo = ritmo.trim();
		}
		
		if (assunto == null || assunto.trim().equals("")) {
			naoListarPorAssunto = true;
		} else {
			assunto = assunto.trim();
		}
		
		if (observacao == null || observacao.trim().equals("")) {
			naoListarPorObservacao = true;
		} else {
			observacao = observacao.trim();
		}
		
		if (qualidade == null || qualidade.trim().equals("")) {
			naoListarPorQualidade = true;
		} else {
			qualidade = qualidade.trim();
		}
		
		if (letra == null || letra.trim().equals("")) {
			naoListarPorLetra = true;
		} else {
			letra = letra.trim();
		}
		
		if (ano <= 0) {
			naoListarPorAno = true;
		}

		if (naoListarPorNome && naoListarPorNomeCantor && naoListarPorRitmo && naoListarPorAssunto && naoListarPorObservacao
				&& naoListarPorQualidade && naoListarPorLetra) {
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
						letra, naoListarPorLetra, ano, naoListarPorAno, colecao);	
			} else {
				return musicaDAO.listarMusicasPorDiversos(
						nome, naoListarPorNome,
						nomeCantor, naoListarPorNomeCantor,
						ritmo, naoListarPorRitmo,
						assunto, naoListarPorAssunto,
						observacao, naoListarPorObservacao,
						qualidade, naoListarPorQualidade,
						letra, naoListarPorLetra, ano, naoListarPorAno);	
			}
		}
	}
	
	public static List<Log> listarLogs() throws DataException {
		LogDAO dao = new LogDAOMySQL();
		return dao.listarLogs();
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
	
	public static List<Qualidade> listarQualidades() throws DataException {
		QualidadeDAO qualidadeDAO = new QualidadeDAOMySQL();
		
		return qualidadeDAO.listarQualidades();
	}
	
	public static List<Assunto> listarAssuntos() throws DataException {
		AssuntoDAO assuntoDAO = new AssuntoDAOMySQL();
		
		return assuntoDAO.listarAssuntos();
	}
	
	public static List<Cantor> listarCantoresPorDiversos(String nome, String nomeSemEspacos) throws DataException {
		boolean naoListarPorNome = false;
		boolean naoListarPorNomeSemEspacos = false;
		
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
			
			
		if (naoListarPorNome && naoListarPorNomeSemEspacos) {	
			return new ArrayList<Cantor>();
		} else {
			CantorDAO cantorDAO = new CantorDAOMySQL();
			return cantorDAO.listarCantoresPorDiversos(nome, naoListarPorNome, nomeSemEspacos, naoListarPorNomeSemEspacos);
		}
	}
	
	public static Musica getMusica(int idMusica) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		return musicaDAO.getMusica(idMusica);
	}
	
	public static InputStream getCapaDiscoMusica(Musica m) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		return musicaDAO.getCapaDiscoMusica(m);
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
	
	public static List<Colecao> listarColecoes() throws DataException {
		ColecaoDAO cDAO = new ColecaoDAOMySQL();
		return cDAO.listarColecoes();
	}
	
	// Métodos de cadastro
	public static void cadastrarMusica(Musica m) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();		
		m.gerarChaveUnica();		
		musicaDAO.cadastrarMusica(m);
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
	
	// Métodos de exclusão
	public static void excluirMusica(Musica m) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		musicaDAO.removerMusica(m);
	}
	
	public static void excluirColecao(Colecao c) throws DataException {
		ColecaoDAO cDAO = new ColecaoDAOMySQL();
		cDAO.removerColecao(c);
	}
	
	// Relatórios
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
			JasperReport jasperReport = JasperCompileManager.compileReport("relatorios" + File.separator + "ColecaoPorOrdemAlfabetica.jrxml");
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(colecao);
			// jasperPrint = JasperFillManager.fillReport("relatorios" + File.separator + "ColecaoPorOrdemAlfabetica.jasper", parameters, ds);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
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
			fw.write("Nome\tCantor\tRitmo\tDisco\tFaixa\tDuração\tAssuntos\n\n");
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
	
	// Métodos de alteração
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
	
	public static void dispararAlteracaoArquivoMusica(Musica m) {
		return;
	}
	
	public static void alterarCapaDiscoMusica(Musica m, String nomeArquivo, String caminhoArquivoImagem) throws DataException {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		musicaDAO.alterarCapaDiscoMusica(m, nomeArquivo, caminhoArquivoImagem);
	}
	
	// métodos de Playlist
	public static Playlist getDefaultPlaylist() throws DataException {
		return getPlaylist("default");
	}
	
	public static Playlist getPlaylist(String nome) throws DataException {
		PlaylistDAO pDAO = new PlaylistDAOMySQL();
		return pDAO.getPlaylist(nome);
	}
	
	public static void alterarPlaylist(Playlist p) throws DataException {
		PlaylistDAO pDAO = new PlaylistDAOMySQL();
		pDAO.alterarPlaylist(p);
	}
	
	// métodos de teste	
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

	// para testar alguns métodos...
	public static void main(String[] args) {
		criaRelatorioDeTeste();
	}	
}
