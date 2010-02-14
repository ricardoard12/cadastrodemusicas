-- adicao de campo de chave unica para a tabela de musica
ALTER TABLE `musica` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL AFTER `idMusica` ;
ALTER TABLE `musica` ADD INDEX ( `chaveUnica` ) ;
-- adicao de �ndice para o nome da musica
 ALTER TABLE `musica` ADD INDEX `nome_musica` ( `nome` );

-- adicao de chave unica para a tabela de cantor
ALTER TABLE `cantor` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL AFTER `idCantor` ;
ALTER TABLE `cantor` ADD INDEX ( `chaveUnica` ) ;

-- adicionando tabela de configura��es
 CREATE TABLE `cadastrodemusicas`.`configuracoes` (
`configuracao` VARCHAR( 100 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'nome da configura��o',
`valor` VARCHAR( 1000 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT 'valor da configura��o',
PRIMARY KEY ( `configuracao` )
) ENGINE = MYISAM CHARACTER SET latin1 COLLATE latin1_swedish_ci;
INSERT INTO `cadastrodemusicas`.`configuracoes` (`configuracao` ,`valor`) VALUES ('diretorio_base', 'Cole��o Checada');

-- eliminando a tabela diretoriobase (essa configura��o vai pra a tabela de configura��es)
DROP TABLE `diretoriobase`  ;
  
INSERT INTO `cadastrodemusicas`.`configuracoes` (`configuracao` ,`valor`)VALUES ('sincronizacao', 'inativa');
  
-- cria��o da tabela de log, para inser��es das opera��es realizadas  
CREATE TABLE `cadastrodemusicas`.`log` (
`idLog` BIGINT NOT NULL ,
`tipoOperacao` INT NOT NULL ,
`classeObjeto` VARCHAR( 100 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`objeto` BLOB NOT NULL ,
`data` DATETIME NOT NULL ,
PRIMARY KEY ( `idLog` )
) ENGINE = MYISAM;

ALTER TABLE `log` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL AFTER `idLog` ;
ALTER TABLE `log` ADD `chaveUnicaObjeto` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL COMMENT 'Chave �nica do Objeto Cadastrado' AFTER `chaveUnica` ;

ALTER TABLE `assunto` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL AFTER `idAssunto` ;
ALTER TABLE `tipo` ADD `chaveUnica` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL AFTER `idTipo` ;


 CREATE TABLE `cadastrodemusicas`.`colecao` (
`idColecao` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`nome` VARCHAR( 20 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`descricao` VARCHAR( 255 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET latin1 COLLATE latin1_swedish_ci; 

CREATE TABLE `cadastrodemusicas`.`musicacolecao` (
`idMusicaColecao` BIGINT NOT NULL ,
`idMusica` INT NOT NULL ,
`idColecao` BIGINT NOT NULL ,
`ordem` INT NULL DEFAULT '-1',
PRIMARY KEY ( `idMusicaColecao` )
) ENGINE = InnoDB; 

ALTER TABLE `musicacolecao` CHANGE `idMusicaColecao` `idMusicaColecao` BIGINT( 20 ) NOT NULL AUTO_INCREMENT;  
 
ALTER TABLE `assunto`  ENGINE = InnoDB;
ALTER TABLE `cantor`  ENGINE = InnoDB;
ALTER TABLE `configuracoes`  ENGINE = InnoDB;
ALTER TABLE `log`  ENGINE = InnoDB;
ALTER TABLE `musica`  ENGINE = InnoDB;
ALTER TABLE `musicaassunto`  ENGINE = InnoDB;
ALTER TABLE `musicacantor`  ENGINE = InnoDB;
ALTER TABLE `qualidade`  ENGINE = InnoDB; 
ALTER TABLE `tipo`  ENGINE = InnoDB;
    
ALTER TABLE `musicacolecao` ADD INDEX ( `idMusica` );
ALTER TABLE `musicacolecao` ADD INDEX ( `idColecao` );
 
 ALTER TABLE `musicacolecao` ADD FOREIGN KEY ( `idMusica` ) REFERENCES `cadastrodemusicas`.`musica` (
`idMusica`
) ON DELETE CASCADE ON UPDATE CASCADE ;

ALTER TABLE `musicacolecao` ADD FOREIGN KEY ( `idColecao` ) REFERENCES `cadastrodemusicas`.`colecao` (
`idColecao`
) ON DELETE CASCADE ON UPDATE CASCADE ;  
















INSERT INTO `cadastrodemusicas`.`configuracoes` (`configuracao` ,`valor`) VALUES ('playlistIndiceAtual', '0'), ('versao', '1.0');
ALTER TABLE `musica` ADD `arquivoCapa` LONGBLOB NULL DEFAULT NULL , ADD `nomeArquivoCapa` VARCHAR( 1000 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ;
ALTER TABLE `musica` ADD `ano` INT NULL DEFAULT NULL ;
CREATE TABLE `cadastrodemusicas`.`playlist` (`idMusica` BIGINT UNSIGNED NOT NULL ,`ordem` INT UNSIGNED NOT NULL ,UNIQUE (`ordem`)) ENGINE = InnoDB; 
ALTER TABLE `playlist` ADD INDEX ( `idMusica` );
ALTER TABLE `playlist` ADD FOREIGN KEY ( `idMusica` ) REFERENCES `cadastrodemusicas`.`musica` (`idMusica`) ON DELETE CASCADE ON UPDATE CASCADE ;
ALTER TABLE `log` ADD `excluido` TINYINT NULL DEFAULT '0';














ALTER TABLE `musica` CHANGE `nome` `nome` VARCHAR( 500 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;
ALTER TABLE `musica` CHANGE `nomearquivo` `nomearquivo` VARCHAR( 600 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;
ALTER TABLE `cantor` CHANGE `nomesemespacos` `nomesemespacos` VARCHAR( 250 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL;
ALTER TABLE `cantor` CHANGE `nome` `nome` VARCHAR( 250 ) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL;

