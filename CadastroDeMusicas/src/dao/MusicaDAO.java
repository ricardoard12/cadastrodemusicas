package dao;

import java.io.InputStream;
import java.util.List;

import classesbasicas.Cantor;
import classesbasicas.Colecao;
import classesbasicas.Musica;
import exceptions.DataException;

public interface MusicaDAO {
	
	public static final int ARQUIVO_CAPA_INEXISTENTE = 1;
	public static final int ARQUIVO_CAPA_COPIADO_OK = 2;
	public static final int ARQUIVO_CAPA_ERRO_COPIA = 3;
	
	public int cadastrarMusica(Musica m) throws DataException;
	public void alterarMusica(Musica m) throws DataException;
	public void removerMusica(Musica m) throws DataException;
	public Musica getMusica(int idMusica) throws DataException;
	public List<Musica> listarMusicasEmOrdemAlfabetica() throws DataException;
	public List<Musica> listarMusicasOrdenarPorCantor() throws DataException;
	public List<Musica> listarMusicasOrdenarPorAssunto() throws DataException;
	public void adicionarCantor(Musica m, Cantor c) throws DataException;
	public List<Musica> listarMusicasPorDiversos(
			String nome, boolean naoListarPorNome,
			String nomeCantor, boolean naoListarPorNomeCantor,
			String ritmo, boolean naoListarPorRitmo,
			String assunto, boolean naoListarPorAssunto,
			String observacao, boolean naoListarPorObservacao,
			String qualidade, boolean naoListarPorQualidade,
			String letra, boolean naoListarPorLetra,
			int ano, boolean naoListarPorAno,
			int tipoArquivo, boolean naoListarPorTipoArquivo) throws DataException;
	public List<Musica> listarMusicasDaColecaoPorDiversos(
			String nome, boolean naoListarPorNome,
			String nomeCantor, boolean naoListarPorNomeCantor,
			String ritmo, boolean naoListarPorRitmo,
			String assunto, boolean naoListarPorAssunto,
			String observacao, boolean naoListarPorObservacao,
			String qualidade, boolean naoListarPorQualidade,
			String letra, boolean naoListarPorLetra,
			int ano, boolean naoListarPorAno,
			int tipoArquivo, boolean naoListarPorTipoArquivo,
			Colecao colecao) throws DataException;
	public List<Musica> listarMusicasSemChaveUnica() throws DataException;
	public void alterarCapaDiscoMusica(Musica m, String nomeArquivo, String caminhoArquivoImagem) throws DataException;
	public InputStream getCapaDiscoMusica(Musica m) throws DataException;
	public Musica listarMusicasPorChaveUnica(String chaveUnica) throws DataException;
	public int exportarArquivoCapa(Musica musica, String caminhoArquivo) throws DataException;
}
