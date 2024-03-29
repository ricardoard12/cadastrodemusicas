package bd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import classesbasicas.Assunto;
import classesbasicas.Cantor;
import classesbasicas.Constantes;
import classesbasicas.Musica;
import classesbasicas.Tipo;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import com.mysql.management.MysqldResource;
import com.mysql.management.MysqldResourceI;

import exceptions.ConfiguracaoInexistenteException;
import exceptions.DataException;
import fachada.Fachada;

public class BDUtil {

	private static Connection conexao = null;
	public final static String USUARIO = "root";
	public final static String SENHA = "";
	public static int porta = 4455; 
	public final static String DATABASE_NAME = "cadastrodemusicas";
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	public final static String DIRETORIO_BD = "." + File.separator + "BD";
	public final static String NOME_ARQUIVOS_CONFIGURACAO = "scm.cfg";
	
	private static boolean sincronizacaoAtiva = false;
	
	public static Connection getConexao() {		
		while (conexao == null) {
			// verificando se existe um arquivo de configura��o com a porta certa para iniciar o banco
			File configFile = new File(DIRETORIO_BD + File.separator + NOME_ARQUIVOS_CONFIGURACAO);
			if (configFile.exists()) {
				try {
					FileReader configReader = new FileReader(configFile);
					BufferedReader configBR = new BufferedReader(configReader);
					String line = null;
					do {
						line = configBR.readLine();
						if (line != null && line.startsWith("[PORTA_BD]")) {
							line = line.substring(line.indexOf("=") + 1);
							int p = Integer.parseInt(line);
							System.out.println("Porta a Iniciar o Banco de Dados: " + p);
							if (p >= 2000 && p <= 9000) {
								porta = p;
							}
						}
					} while(line != null);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}	
			
			try {
				Class.forName(DRIVER);
				conexao = DriverManager.getConnection(getUrl(),
						USUARIO, SENHA);
				
				// verificar a vers�o do banco de dados, pra o caso de precisar de atualiza��o
				// testando a vers�o anterior � 1.0
				String sql = "SELECT * FROM configuracoes";
				Statement stat;			
				stat = getConexao().createStatement();
				ResultSet rs;
				try {
					rs = stat.executeQuery(sql);
				} catch (MySQLSyntaxErrorException e) {
					// atualizar o Banco de Dados
					sql = "SELECT * FROM diretoriobase";
					rs = stat.executeQuery(sql);					
					rs.next();					
					String config = rs.getString("diretorio");
					if (config == null || config.length() <= 0) {
						config = "Cole��o Checada";
					}
					System.out.println("Atualizando o Banco de dados da Vers�o anterior � 1.0 para a Vers�o 1.3");
					String[] sqls = {"ALTER TABLE `musica` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL AFTER `idMusica` ;",
									 "ALTER TABLE `musica` ADD INDEX ( `chaveUnica` ) ;",
									 "ALTER TABLE `musica` ADD INDEX `nome_musica` ( `nome` );",
									 "ALTER TABLE `cantor` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL AFTER `idCantor` ;",
									 "ALTER TABLE `cantor` ADD INDEX ( `chaveUnica` ) ;",
									 "CREATE TABLE `cadastrodemusicas`.`configuracoes` ("
									 	+ "`configuracao` VARCHAR( 100 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'nome da configura��o',"
									 	+ "`valor` VARCHAR( 1000 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'valor da configura��o',"
									 	+ "PRIMARY KEY ( `configuracao` )"
									 	+ ") ENGINE = MYISAM CHARACTER SET latin1 COLLATE latin1_swedish_ci;",
									 "INSERT INTO `cadastrodemusicas`.`configuracoes` (`configuracao` ,`valor`) VALUES ('diretorio_base', '" + config + "');",
									 "DROP TABLE `diretoriobase`  ;",
									 "INSERT INTO `cadastrodemusicas`.`configuracoes` (`configuracao` ,`valor`)VALUES ('sincronizacao', 'inativa');",
									 "CREATE TABLE `cadastrodemusicas`.`log` ("
									 	+ "`idLog` BIGINT NOT NULL ,"
									 	+ "`tipoOperacao` INT NOT NULL ,"
									 	+ "`classeObjeto` VARCHAR( 100 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,"
									 	+ "`objeto` BLOB NOT NULL ,"
									 	+ "`data` DATETIME NOT NULL ,"
									 	+ "PRIMARY KEY ( `idLog` )"
									 	+ ") ENGINE = MYISAM;",
									 "ALTER TABLE `log` CHANGE `idLog` `idLog` BIGINT( 20 ) NOT NULL AUTO_INCREMENT;",  
									 "ALTER TABLE `log` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL AFTER `idLog` ;",
									 "ALTER TABLE `log` ADD `chaveUnicaObjeto` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL COMMENT 'Chave �nica do Objeto Cadastrado' AFTER `chaveUnica` ;",
									 "ALTER TABLE `assunto` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL AFTER `idAssunto` ;",
									 "ALTER TABLE `tipo` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL AFTER `idTipo` ;",
									 "CREATE TABLE `cadastrodemusicas`.`colecao` ("
									 	+ "`idColecao` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,"
										+ "`nome` VARCHAR( 20 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,"
										+ "`descricao` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL"
										+ ") ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_swedish_ci;",
									 "ALTER TABLE `colecao` DROP PRIMARY KEY , ADD PRIMARY KEY ( `idColecao` );", 
									 "CREATE TABLE `cadastrodemusicas`.`musicacolecao` (`idMusicaColecao` BIGINT NOT NULL ,"
									 	+ "`idMusica` INT NOT NULL ,"
									 	+ "`idColecao` BIGINT NOT NULL ,`ordem` INT NULL DEFAULT '-1',"
										+ "PRIMARY KEY ( `idMusicaColecao` )) ENGINE = InnoDB;",
									 "ALTER TABLE `musicacolecao` CHANGE `idMusica` `idMusica` BIGINT( 20 ) UNSIGNED NOT NULL;",  
									 "ALTER TABLE `musicacolecao` CHANGE `idMusicaColecao` `idMusicaColecao` BIGINT( 20 ) NOT NULL AUTO_INCREMENT;",
									 "ALTER TABLE `assunto`  ENGINE = InnoDB;",
									 "ALTER TABLE `cantor`  ENGINE = InnoDB;",
									 "ALTER TABLE `configuracoes`  ENGINE = InnoDB;",
									 "ALTER TABLE `log`  ENGINE = InnoDB;",
									 "ALTER TABLE `musica`  ENGINE = InnoDB;",
									 "ALTER TABLE `musicaassunto`  ENGINE = InnoDB;",
									 "ALTER TABLE `musicacantor`  ENGINE = InnoDB;",
									 "ALTER TABLE `qualidade`  ENGINE = InnoDB;",
									 "ALTER TABLE `tipo`  ENGINE = InnoDB;",
									 "ALTER TABLE `musicacolecao` ADD INDEX ( `idMusica` );",
									 "ALTER TABLE `musicacolecao` ADD INDEX ( `idColecao` );",
									 "ALTER TABLE `musica` CHANGE `idMusica` `idMusica` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT;",  
									 "ALTER TABLE `musicacolecao` ADD FOREIGN KEY ( `idMusica` ) REFERENCES `cadastrodemusicas`.`musica` (`idMusica`) ON DELETE CASCADE ON UPDATE CASCADE;",
									 "ALTER TABLE `musicacolecao` ADD FOREIGN KEY ( `idColecao` ) REFERENCES `cadastrodemusicas`.`colecao` (`idColecao`) ON DELETE CASCADE ON UPDATE CASCADE;",
									 "INSERT INTO `cadastrodemusicas`.`configuracoes` (`configuracao` ,`valor`) VALUES ('playlistIndiceAtual', '0'), ('versao', '1.0');",
									 "ALTER TABLE `musica` ADD `arquivoCapa` LONGBLOB NULL DEFAULT NULL , ADD `nomeArquivoCapa` VARCHAR( 1000 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ;",
									 "ALTER TABLE `musica` ADD `ano` INT NULL DEFAULT NULL ;",
									 "CREATE TABLE `cadastrodemusicas`.`playlist` (`idMusica` BIGINT UNSIGNED NOT NULL ,`ordem` INT UNSIGNED NOT NULL ,UNIQUE (`ordem`)) ENGINE = InnoDB;", 
									 "ALTER TABLE `playlist` ADD INDEX ( `idMusica` );",
									 "ALTER TABLE `playlist` ADD FOREIGN KEY ( `idMusica` ) REFERENCES `cadastrodemusicas`.`musica` (`idMusica`) ON DELETE CASCADE ON UPDATE CASCADE ;",
									 "ALTER TABLE `log` ADD `excluido` TINYINT NULL DEFAULT '0';",
									 "ALTER TABLE `musica` CHANGE `nome` `nome` VARCHAR( 500 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;",
									 "ALTER TABLE `musica` CHANGE `nomearquivo` `nomearquivo` VARCHAR( 600 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;",
									 "ALTER TABLE `cantor` CHANGE `nomesemespacos` `nomesemespacos` VARCHAR( 250 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL;",
									 "ALTER TABLE `cantor` CHANGE `nome` `nome` VARCHAR( 250 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL;",
									 "DROP TABLE `playlist` ;",
									"CREATE TABLE `cadastrodemusicas`.`playlist` (`idPlaylist` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,`nome` VARCHAR( 160 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,`created` DATETIME NULL ,`modified` DATETIME NULL) ENGINE = InnoDB;",
									"ALTER TABLE `playlist` CHANGE `idPlaylist` `idPlaylist` BIGINT( 20 ) UNSIGNED NOT NULL AUTO_INCREMENT ;",
									"CREATE TABLE `cadastrodemusicas`.`playlistitem` (`idPlaylist` BIGINT UNSIGNED NOT NULL ,`idMusica` BIGINT UNSIGNED NOT NULL ,`ordem` INT NULL DEFAULT '2147483647') ENGINE = InnoDB;",
									"ALTER TABLE `playlistitem` ADD INDEX ( `idPlaylist` ) ;",
									"ALTER TABLE `playlistitem` ADD INDEX ( `idMusica` ) ;",
									"ALTER TABLE `playlistitem` ADD FOREIGN KEY ( `idPlaylist` ) REFERENCES `cadastrodemusicas`.`playlist` (`idPlaylist`) ON DELETE CASCADE ON UPDATE CASCADE ;",
									"ALTER TABLE `playlistitem` ADD FOREIGN KEY ( `idMusica` ) REFERENCES `cadastrodemusicas`.`musica` (`idMusica`) ON DELETE CASCADE ON UPDATE CASCADE ;",
									"ALTER TABLE `musicacolecao` CHANGE `ordem` `ordem` INT NULL DEFAULT '2147483647';",
									"INSERT INTO `cadastrodemusicas`.`playlist` (`idPlaylist` ,`nome` ,`created` ,`modified`) VALUES (NULL , 'default', '2010-02-22 23:26:37', '2010-02-22 23:26:37');",
									"UPDATE `cadastrodemusicas`.`configuracoes` SET `configuracao` = 'playlist_indice_atual' WHERE `configuracoes`.`configuracao` = 'playlistIndiceAtual';",
									"INSERT INTO `cadastrodemusicas`.`configuracoes` (`configuracao` ,`valor`) VALUES ('playlist_atual', 'default');",
									"ALTER TABLE `assunto` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
									"UPDATE assunto SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
									"ALTER TABLE `cantor` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
									"UPDATE cantor SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
									"ALTER TABLE `colecao` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
									"UPDATE colecao SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
									"ALTER TABLE `musica` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
									"UPDATE musica SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
									"ALTER TABLE `tipo` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
									"UPDATE tipo SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
									"UPDATE `cadastrodemusicas`.`configuracoes` SET `valor` = '1.3' WHERE `configuracoes`.`configuracao` =  'versao';",
									"INSERT INTO configuracoes VALUES('data_release', '05/07/2010');",
									"ALTER TABLE `assunto` ADD `tipoarquivo` INT( 10 );",
									"ALTER TABLE `cantor` ADD `tipoarquivo` INT( 10 );",
									"ALTER TABLE `musica` ADD `tipoarquivo` INT( 10 );",
									"ALTER TABLE `tipo` ADD `tipoarquivo` INT( 10 );",
									"UPDATE assunto SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
									"UPDATE cantor SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
									"UPDATE musica SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
									"UPDATE tipo SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";"
									};
					
					
					/*DROP VIEW IF EXISTS `cadastrodemusicas`.`musicas_view`;

					CREATE VIEW `cadastrodemusicas`.`musicas_view` 
						AS 
							( 
					select 
						musica.*, 
						cantor.idCantor, cantor.nomesemespacos,cantor.nome as nome_cantor,
						tipo.tipo, 
						assunto.assunto, 
						qualidade.qualidade 
					from ((((((
						musica left join musicacantor on musica.idMusica = musicacantor.idMusica) 
						left join cantor on musicacantor.idcantor = cantor.idcantor) 
						left join tipo on musica.idTipo = tipo.idTipo) 
						left join musicaassunto on musica.idMusica = musicaassunto.idMusica) 
						left join assunto on musicaassunto.idAssunto = assunto.idAssunto)
						left join qualidade on musica.idQualidade = qualidade.idQualidade) 
					group by musica.idMusica)*/
					
					
					
					
					
					
					
					
					
					
					
					
					conexao.setAutoCommit(false);
					for (String s : sqls) {
						System.out.println(s);
						stat.addBatch(s);
						// stat.execute(s);
					}				
					stat.executeBatch();
					conexao.commit();
					conexao.setAutoCommit(true);
				}

				// atualizando o banco de dados caso a vers�o do banco seja a 1.0 (menos altera��es no banco) 
				sql = "SELECT valor FROM configuracoes WHERE configuracao LIKE 'versao'";
				rs = stat.executeQuery(sql);				
				rs.next();
				if (rs.getString("valor").equals("1.0")) {
					System.out.println("Atualizando o Banco de Dados da Vers�o 1.0 para a 1.3");
					String[] sqls = {"DROP TABLE `playlist` ;",
							"CREATE TABLE `cadastrodemusicas`.`playlist` (`idPlaylist` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,`nome` VARCHAR( 160 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,`created` DATETIME NULL ,`modified` DATETIME NULL) ENGINE = InnoDB;",
							"ALTER TABLE `playlist` CHANGE `idPlaylist` `idPlaylist` BIGINT( 20 ) UNSIGNED NOT NULL AUTO_INCREMENT ;",
							"CREATE TABLE `cadastrodemusicas`.`playlistitem` (`idPlaylist` BIGINT UNSIGNED NOT NULL ,`idMusica` BIGINT UNSIGNED NOT NULL ,`ordem` INT NULL DEFAULT '2147483647') ENGINE = InnoDB;",
							"ALTER TABLE `playlistitem` ADD INDEX ( `idPlaylist` ) ;",
							"ALTER TABLE `playlistitem` ADD INDEX ( `idMusica` ) ;",
							"ALTER TABLE `playlistitem` ADD FOREIGN KEY ( `idPlaylist` ) REFERENCES `cadastrodemusicas`.`playlist` (`idPlaylist`) ON DELETE CASCADE ON UPDATE CASCADE ;",
							"ALTER TABLE `playlistitem` ADD FOREIGN KEY ( `idMusica` ) REFERENCES `cadastrodemusicas`.`musica` (`idMusica`) ON DELETE CASCADE ON UPDATE CASCADE ;",
							"ALTER TABLE `musicacolecao` CHANGE `ordem` `ordem` INT NULL DEFAULT '2147483647';",
							"INSERT INTO `cadastrodemusicas`.`playlist` (`idPlaylist` ,`nome` ,`created` ,`modified`) VALUES (NULL , 'default', '2010-02-22 23:26:37', '2010-02-22 23:26:37');",
							"UPDATE `cadastrodemusicas`.`configuracoes` SET `configuracao` = 'playlist_indice_atual' WHERE `configuracoes`.`configuracao` = 'playlistIndiceAtual';",
							"INSERT INTO `cadastrodemusicas`.`configuracoes` (`configuracao` ,`valor`) VALUES ('playlist_atual', 'default');",
							"ALTER TABLE `assunto` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
							"UPDATE assunto SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
							"ALTER TABLE `cantor` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
							"UPDATE cantor SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
							"ALTER TABLE `colecao` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
							"UPDATE colecao SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
							"ALTER TABLE `musica` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
							"UPDATE musica SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
							"ALTER TABLE `tipo` ADD `created` DATETIME NULL DEFAULT NULL ,ADD `modified` DATETIME NULL DEFAULT NULL ;",
							"UPDATE tipo SET created = NOW(), modified = NOW() WHERE created IS NULL OR modified IS NULL;",
							"UPDATE `cadastrodemusicas`.`configuracoes` SET `valor` = '1.3' WHERE `configuracoes`.`configuracao` =  'versao';",
							"INSERT INTO configuracoes VALUES('data_release', '05/07/2010');",
							"ALTER TABLE `assunto` ADD `tipoarquivo` INT( 10 );",
							"ALTER TABLE `cantor` ADD `tipoarquivo` INT( 10 );",
							"ALTER TABLE `musica` ADD `tipoarquivo` INT( 10 );",
							"ALTER TABLE `tipo` ADD `tipoarquivo` INT( 10 );",
							"UPDATE assunto SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
							"UPDATE cantor SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
							"UPDATE musica SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
							"UPDATE tipo SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";"
							};
					conexao.setAutoCommit(false);
					for (String s : sqls) {
						System.out.println(s);
						stat.addBatch(s);
						// stat.execute(s);
					}				
					stat.executeBatch();
					conexao.commit();
					conexao.setAutoCommit(true);
				}
				
				if (rs.getString("valor").equals("1.1")) {
					System.out.println("Atualizando o Banco de Dados da Vers�o 1.1 para a 1.3");
					String[] sqls = {
								"UPDATE `cadastrodemusicas`.`configuracoes` SET `valor` = '1.3' WHERE `configuracoes`.`configuracao` =  'versao';",
								"INSERT INTO configuracoes VALUES('data_release', '05/07/2010');",
								"ALTER TABLE `assunto` ADD `tipoarquivo` INT( 10 );",
								"ALTER TABLE `cantor` ADD `tipoarquivo` INT( 10 );",
								"ALTER TABLE `musica` ADD `tipoarquivo` INT( 10 );",
								"ALTER TABLE `tipo` ADD `tipoarquivo` INT( 10 );",
								"UPDATE assunto SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
								"UPDATE cantor SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
								"UPDATE musica SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
								"UPDATE tipo SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";"
							};
					conexao.setAutoCommit(false);
					for (String s : sqls) {
						System.out.println(s);
						stat.addBatch(s);
						// stat.execute(s);
					}				
					stat.executeBatch();
					conexao.commit();
					conexao.setAutoCommit(true);
				} else if (rs.getString("valor").equals("1.2")) {
					System.out.println("Atualizando o Banco de Dados da Vers�o 1.2 para a 1.3");
					String[] sqls = {
								"UPDATE `cadastrodemusicas`.`configuracoes` SET `valor` = '1.3' WHERE `configuracoes`.`configuracao` =  'versao';",
								"INSERT INTO configuracoes VALUES('data_release', '05/07/2010');",
								"ALTER TABLE `assunto` ADD `tipoarquivo` INT( 10 );",
								"ALTER TABLE `cantor` ADD `tipoarquivo` INT( 10 );",
								"ALTER TABLE `musica` ADD `tipoarquivo` INT( 10 );",
								"ALTER TABLE `tipo` ADD `tipoarquivo` INT( 10 );",
								"UPDATE assunto SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
								"UPDATE cantor SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
								"UPDATE musica SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
								"UPDATE tipo SET tipoarquivo = " + Constantes.TIPO_ARQUIVO_MUSICA_CANTADA + ";",
							};
					conexao.setAutoCommit(false);
					for (String s : sqls) {
						System.out.println(s);
						stat.addBatch(s);
						// stat.execute(s);
					}				
					stat.executeBatch();
					conexao.commit();
					conexao.setAutoCommit(true);
				} else  if (rs.getString("valor").equals("1.3")) {
					System.out.println("Atualizando o Banco de Dados da Vers�o 1.3 para a 1.4");
					String[] sqls = {
								"UPDATE `cadastrodemusicas`.`configuracoes` SET `valor` = '1.4' WHERE `configuracoes`.`configuracao` =  'versao';",
								"ALTER TABLE `musica` ADD `arquivoMusica` longblob NULL DEFAULT NULL;",
							};
					conexao.setAutoCommit(false);
					for (String s : sqls) {
						System.out.println(s);
						stat.addBatch(s);
						// stat.execute(s);
					}				
					stat.executeBatch();
					conexao.commit();
					conexao.setAutoCommit(true);

				} else  if (rs.getString("valor").equals("1.4")) {
					System.out.println("Atualizando o Banco de Dados da Vers�o 1.4 para a 1.5");
					
					/*String[] sqls = {
								"CREATE TABLE arquivomusica (`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, `arquivomusica` longblob NULL DEFAULT NULL);",
								"ALTER TABLE `musica` ADD `idarquivomusica` BIGINT NOT NULL ;"
							};
					conexao.setAutoCommit(false);
					for (String s : sqls) {
						System.out.println(s);
						stat.addBatch(s);
						// stat.execute(s);
					}				
					stat.executeBatch();
					conexao.commit();
					conexao.setAutoCommit(true);
					
					for (String s: sqls)
					{
						System.out.println(s);
					}*/
					
					/*try {
						System.out.println("Alterando todas as musicas para colocar o arquivo no BD");
						
						List<Musica> musicas = Fachada.listarTodasAsMusicasEmOrdemAlfabetica();
						// List<Musica> musicas = Fachada.listarMusicasPorDiversos("A M�e d%menina", null, null, null, null, null, null, null, 0, Constantes.TIPO_ARQUIVO_TODOS, null);
						
						System.out.println("Quantidaded e musicas: " + musicas.size());
						
						for (Musica m: musicas)
						{
							System.out.println("Musica: " + m.getNome());
							
							// String current = new java.io.File( "." ).getCanonicalPath();
							String path = getDiretorioBase() + File.pathSeparator + m.getDiretorio() + File.pathSeparator + m.getNomeArquivo();
							File f = new File(path);
							String fPath = f.getAbsolutePath().replaceAll("(\\\\|" + File.pathSeparator + ")", "/").replaceAll("(\')", "\\\\'");
							
							sql = "INSERT INTO arquivomusica (arquivomusica) VALUES(LOAD_FILE(\'" + fPath + "\'));";
							stat.execute(sql, Statement.RETURN_GENERATED_KEYS);
							ResultSet id_arquivo = stat.getGeneratedKeys();
							id_arquivo.next();
							int id = id_arquivo.getInt(1);
							
							sql = "UPDATE musica SET idarquivomusica = " + id + " WHERE idMusica = " + m.getIdMusica();
							stat.execute(sql);
						}
						
						System.out.println("Removendo o campo nomearquivo da tabela de musicas");
						String[] sqls2 = {
								"ALTER TABLE `musica` DROP `nomearquivo`;",
								"UPDATE `cadastrodemusicas`.`configuracoes` SET `valor` = '1.5' WHERE `configuracoes`.`configuracao` =  'versao';"
							};
						conexao.setAutoCommit(false);
						for (String s : sqls2) {
							System.out.println(s);
							stat.addBatch(s);
							// stat.execute(s);
						}				
						stat.executeBatch();
						conexao.commit();
						conexao.setAutoCommit(true);

						System.out.println("OK! Banco de dados alterado.");
					} catch (DataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DiretorioBaseInvalidoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}
				
				// atualizar a variavel sincronizacaoAtiva
				sql = "SELECT valor FROM configuracoes WHERE configuracao LIKE 'sincronizacao'";
				rs = stat.executeQuery(sql);				
				rs.next();
				
				if (rs.getString("valor").equals("ativa")) {
					sincronizacaoAtiva = true;
				} else {
					sincronizacaoAtiva = false;
				}
				
				System.out.println("Sincronizacao: " + sincronizacaoAtiva);
				
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					Thread.sleep(500);
					porta++;
					desativarBD();					
					return getConexao();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Error e) {
				e.printStackTrace();
				try {
					Thread.sleep(500);
					porta++;
					desativarBD();
					return getConexao();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		// testar a conex�o

		return conexao;
	}
	
	private static String getUrl() {
		return "jdbc:mysql:mxj://localhost:" + porta + "/" + DATABASE_NAME + "?"
	    + "server.basedir=" + DIRETORIO_BD + "&server.max_allowed_packet=32000000";
	}
		
	public static void backupBD(String nomeArquivo) {

		try {
			FileOutputStream arquivoSaida = new FileOutputStream(nomeArquivo);

			String criaBD = "CREATE DATABASE " + DATABASE_NAME
					+ ";\n";
			criaBD += "USE " + DATABASE_NAME + ";\n\n";

			arquivoSaida.write(criaBD.getBytes());

			Runtime rt = Runtime.getRuntime();

			String comando = "mysqldump --opt --default-character-set=latin1 -u " + USUARIO + " -p"
					+ SENHA;
			comando += " " + DATABASE_NAME;

			Process p = rt.exec(comando);

			LeDumpDeSaida erro = new LeDumpDeSaida(p.getErrorStream(),
					System.out);
			LeDumpDeSaida saida = new LeDumpDeSaida(p.getInputStream(),
					arquivoSaida);

			erro.start();
			saida.start();

			p.waitFor();

			arquivoSaida.flush();
			arquivoSaida.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void desativarBD() {
		try {
			getConexao().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conexao = null;
		
		MysqldResourceI m = new MysqldResource(new File(DIRETORIO_BD));
		
		m.shutdown();	
	}

	public static void atualizarChavesUnicas() {
		boolean sincTemp = sincronizacaoAtiva;
		getConexao();
		sincronizacaoAtiva = false;
		
		try {
			List<Musica> musicas = Fachada.listarMusicasSemChaveUnica();
			for (Musica m: musicas) {
				System.out.println(m.getNome());				
				m.gerarChaveUnica();				
				Fachada.alterarMusica(m);
			}
			
			List<Cantor> cantores = Fachada.listarCantoresSemChaveUnica();
			for (Cantor c : cantores) {
				Fachada.alterarCantor(c);
			}
			
			List<Assunto> assuntos = Fachada.listarAssuntosSemChaveUnica();
			for (Assunto a : assuntos) {
				Fachada.alterarAssunto(a);
			}
			
			List<Tipo> tipos = Fachada.listarTiposSemChaveUnica();
			for (Tipo t : tipos) {
				Fachada.alterarTipo(t);
			}
			
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sincronizacaoAtiva = sincTemp;
	}
	
	public static boolean isSincronizacaoAtiva() {
		getConexao();
		return sincronizacaoAtiva;
	}

	public static void excluirDadosSincronizacao() throws SQLException {
		String sql = "UPDATE log SET excluido = 1";		
		
		Statement stat = getConexao().createStatement();
		stat.execute(sql);
	}
	
	public static void salvarConfiguracao(String nomeConfig, String valorConfig) throws DataException {
		// verificando se j� existe uma configura��o com o mesmo nome
		String sql = "SELECT valor FROM configuracoes WHERE configuracao = '" + nomeConfig + "'";
		try {
			Statement stat = getConexao().createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				sql = "UPDATE configuracoes SET valor = " + valorConfig + " WHERE configuracao = '" + nomeConfig + "'"; 
			} else {
				sql = "INSERT INTO configuracoes (configuracao, valor) VALUES ('" + nomeConfig + "', '" + valorConfig + "')";
			}
			stat.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException();
		}
	}
	
	public static String getConfiguracao(String nomeConfig) throws ConfiguracaoInexistenteException, DataException {
		String sql = "SELECT valor FROM configuracoes WHERE configuracao = '" + nomeConfig + "'";
		try {
			Statement stat = getConexao().createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				return rs.getString("valor");
			} else {
				throw new ConfiguracaoInexistenteException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException();
		}
	}
}
