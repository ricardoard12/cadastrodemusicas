package classesbasicas;

import java.awt.Color;

public class Constantes {
	public static final String MIDIA = "Midia";
	public static final String MIDIA_CD = "CD";
	public static final String MIDIA_CD_AUDIO = "CD_AUDIO";
	public static final String MIDIA_DVD = "DVD";
	
	public static final String LIMITAR_MUSICAS = "Limitar Musicas";
	public static final String LIMITE_DE_MUSICAS = "Limite de Musicas";
	
	public static final String RELATORIO_ORDEM_ALFABETICA = "Relatorio Por Ordem Alfabetica";
	public static final String RELATORIO_POR_CANTOR = "Relatorio Por Cantor";
	public static final String RELATORIO_POR_ASSUNTO = "Relatorio Por Assunto";
	
	public static final String RELATORIO_PDF = "Relatorio em PDF";
	public static final String RELATORIO_HTML = "Relatorio em HTML";
	
	public static final String INSERIR_LETRAS_INICIAIS = "Inserir Letras Iniciais";
	
	public static final String NOME_COLECAO = "Nome da Coleção";
	public static final String NUMERO_MUSICAS = "Número de Músicas";
	
	public static final String LOCAL_DE_DESTINO = "Local de Destino";
	
	public static final String NOME_ARQUIVO_LOG = "logs.bin";
	
	// tamanho do cd equivalente a 660MB, e do dvd a 4.2GB
	public static final long TAMANHO_MAXIMO_CD = 692060160L;
	public static final long TAMANHO_MAXIMO_DVD = 4509715661L;
	public static final long TAMANHO_MAXIMO_CD_AUDIO = 4650L; // equivalente a 75 minutos
	
	public static final String nomeDiretorioTemp = "colec_temp";
	
	public static final String CONFIGURACAO_INTERVALO_ENTRE_MUSICAS = "intervalo_musicas";
	public static final String CONFIGURACAO_DATA_RELEASE = "data_release";
	
	public static final String STRING_CAPA_DISCO = "CAPADISCO";

	public static final String QUALIDADE_RUIM = "Ruim";
	public static final String QUALIDADE_NORMAL = "Normal";
	public static final String QUALIDADE_BOA = "Boa";
	public static final String QUALIDADE_OTIMA = "Ótima";
	
	public static final int TIPO_ARQUIVO_MUSICA_CANTADA = 1;
	public static final int TIPO_ARQUIVO_MUSICA_INSTRUMENTAL = 2;
	public static final int TIPO_ARQUIVO_MENSAGEM = 3;
	public static final int TIPO_ARQUIVO_TODOS = -1;
	public static final int TIPO_ARQUIVO_NAO_LISTAR = -2;
	
	public static final String TIPO_ARQUIVO_CANTADA_STRING = "Cantada";
	public static final String TIPO_ARQUIVO_INSTRUMENTAL_STRING = "Instrumental";
	public static final String TIPO_ARQUIVO_MENSAGEM_STRING = "Mensagem";
	public static final String[] TIPO_ARQUIVO_NOMES_TIPOS = {
		"",
		TIPO_ARQUIVO_CANTADA_STRING,
		TIPO_ARQUIVO_INSTRUMENTAL_STRING,
		TIPO_ARQUIVO_MENSAGEM_STRING
	};
	
	public static final Color COR_QUALIDADE_RUIM = new Color(255, 182, 182);
	public static final Color COR_QUALIDADE_NORMAL = new Color(244, 244, 244);
	public static final Color COR_QUALIDADE_BOA = new Color(189, 189, 255);
	public static final Color COR_QUALIDADE_OTIMA = new Color(120, 120, 255);
	public static final Color COR_QUALIDADE_FALTANDO = new Color(160, 255, 160);
}

