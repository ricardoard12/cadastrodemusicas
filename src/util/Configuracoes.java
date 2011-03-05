package util;

import java.util.Hashtable;

import exceptions.ConfiguracaoInexistenteException;
import exceptions.DataException;

import bd.BDUtil;

public class Configuracoes {
	// valores das configuracoes
	public static final String VALOR_CONFIG_TIPO_SISTEMA_MUSICAS = "MUSICAS";
	public static final String VALOR_CONFIG_TIPO_SISTEMA_DMD = "DMD";
		
	// Configurações armazenadas no banco de dados
	public static final String CONFIGURACAO_INTERVALO_ENTRE_MUSICAS = "intervalo_musicas";
	public static final String CONFIGURACAO_DATA_RELEASE = "data_release";
	public static final String CONFIGURACAO_TIPO_SISTEMA = "tipo_sistema";
	
	// chaves das configuracoes
	public static final String CONFIGURACAO_TITULO = "titulo";
	public static final String CONFIGURACAO_ITEM_MENU_MUSICAS = "item_menu_musicas";
	public static final String CONFIGURACAO_ITEM_MENU_CANTORES = "item_menu_cantores";
	public static final String CONFIGURACAO_TAB_CADASTRAR_MUSICAS_TITULO = "tab_cadastrar_musicas_titulo";
	public static final String CONFIGURACAO_TAB_PROCURAR_MUSICAS_TITULO = "tab_procurar_musicas_titulo";
	public static final String CONFIGURACAO_TAB_EDITAR_MUSICAS_TITULO = "tab_editar_musicas_titulo";
	public static final String CONFIGURACAO_NOME_MUSICA = "nome_musica";
	public static final String CONFIGURACAO_NOME_CANTOR = "nome_cantor";
	public static final String CONFIGURACAO_NOME_RITMO = "nome_ritmo";
	public static final String CONFIGURACAO_CAPA_DISCO = "capa_disco";
	public static final String CONFIGURACAO_LETRA = "letra";
	public static final String CONFIGURACAO_TABELA_MUSICAS_EXISTENTES = "musicas_existentes";
	public static final String CONFIGURACAO_TABELA_NOME_CAMPO_ORADOR = "nome_campo_orador";
	public static final String CONFIGURACAO_TABELA_TITULO_CANTORES = "tabela_titulo_cantores";
	public static final String CONFIGURACAO_CADASTRAR_MUSICA_BUTTON = "cadastrar_musica_button";
	public static final String CONFIGURACAO_CANTOR_INTERPRETE_LABEL = "cantor_interprete_label";
	public static final String CONFIGURACAO_RITMO_ESTILO_LABEL = "ritmo_estilo_label";
	public static final String CONFIGURACAO_ASSUNTO_INSTRUMENTOS_LABEL = "assunto_instrumentos_label";
	
	
	private static Hashtable<String, String> configuracoes;
	
	static {
		String tipoSistema = VALOR_CONFIG_TIPO_SISTEMA_MUSICAS;
		configuracoes = new Hashtable<String, String>();
		
		try {
			tipoSistema = BDUtil.getConfiguracao(CONFIGURACAO_TIPO_SISTEMA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD)) {
			configuracoes.put(CONFIGURACAO_TIPO_SISTEMA, VALOR_CONFIG_TIPO_SISTEMA_DMD);
		} else {
			configuracoes.put(CONFIGURACAO_TIPO_SISTEMA, VALOR_CONFIG_TIPO_SISTEMA_MUSICAS);
		}
		
		configuracoes.put(CONFIGURACAO_TITULO, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ? 
				"DMD - Sistema de Cadastro de Arquivos de Áudio (MP3)" : "Sistema de Cadastro de Músicas");
		configuracoes.put(CONFIGURACAO_ITEM_MENU_MUSICAS, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Arquivos de Áudio" : "Músicas");
		configuracoes.put(CONFIGURACAO_ITEM_MENU_CANTORES, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Oradores" : "Cantores");
		configuracoes.put(CONFIGURACAO_TAB_CADASTRAR_MUSICAS_TITULO, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Cadastrar Arquivos" : "Cadastrar Músicas");
		configuracoes.put(CONFIGURACAO_TAB_PROCURAR_MUSICAS_TITULO, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Procurar Arquivos" : "Procurar Músicas");
		configuracoes.put(CONFIGURACAO_TAB_EDITAR_MUSICAS_TITULO, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Editar Arquivo" : "Editar Músicas");
		configuracoes.put(CONFIGURACAO_NOME_MUSICA, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Nome (Breve Descrição)" : "Nome");
		configuracoes.put(CONFIGURACAO_NOME_CANTOR, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Orador (Nome sem Espaços)" : "Cantor (Nome sem Espaços)");
		configuracoes.put(CONFIGURACAO_NOME_RITMO, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Tipo Áudio" : "Ritmo");
		configuracoes.put(CONFIGURACAO_CAPA_DISCO, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Foto" : "Capa Disco");
		configuracoes.put(CONFIGURACAO_LETRA, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Texto" : "Letra");
		configuracoes.put(CONFIGURACAO_TABELA_MUSICAS_EXISTENTES, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Arquivos Existentes" : "Músicas Existentes");
		configuracoes.put(CONFIGURACAO_TABELA_NOME_CAMPO_ORADOR, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Orador" : "Cantor");
		configuracoes.put(CONFIGURACAO_TABELA_TITULO_CANTORES, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Oradores Existentes" : "Cantores Existentes");
		configuracoes.put(CONFIGURACAO_CADASTRAR_MUSICA_BUTTON, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Cadastrar Arquivo" : "Cadastrar Música");
		configuracoes.put(CONFIGURACAO_CANTOR_INTERPRETE_LABEL, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Orador" : "Cantor / Intérprete");
		configuracoes.put(CONFIGURACAO_RITMO_ESTILO_LABEL, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Tipo Arquivo" : "Ritmo / Estilo");
		configuracoes.put(CONFIGURACAO_ASSUNTO_INSTRUMENTOS_LABEL, tipoSistema.equals(VALOR_CONFIG_TIPO_SISTEMA_DMD) ?
				"Assunto" : "Assunto / Instrumentos");
	}
	
	
	public static String getConfiguracao(String chave) {
		// procura primeiro na tabela Hash
		String config = configuracoes.get(chave);
		if (config != null) return config;
		
		try {
			config = BDUtil.getConfiguracao(chave);
			return config;
		} catch (ConfiguracaoInexistenteException e) {
			e.printStackTrace();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
