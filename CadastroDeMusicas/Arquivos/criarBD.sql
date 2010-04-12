/*
SQLyog - Free MySQL GUI v5.02
Host - 5.0.41-community-nt : Database - cadastrodemusicas
*********************************************************************
Server version : 5.0.41-community-nt
*/


create database if not exists `cadastrodemusicas`;

USE `cadastrodemusicas`;

SET FOREIGN_KEY_CHECKS=0;

/*Table structure for table `assunto` */

CREATE TABLE `assunto` (
  `idAssunto` int(10) unsigned NOT NULL auto_increment,
  `chaveUnica` varchar(255) default NULL,
  `assunto` varchar(100) default NULL,
  `created` datetime default NULL,
  `modified` datetime default NULL,
  PRIMARY KEY  (`idAssunto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `assunto` */

/*Table structure for table `cantor` */

CREATE TABLE `cantor` (
  `idCantor` int(10) unsigned NOT NULL auto_increment,
  `chaveUnica` varchar(255) default NULL,
  `nomesemespacos` varchar(250) default NULL,
  `nome` varchar(250) default NULL,
  `created` datetime default NULL,
  `modified` datetime default NULL,
  PRIMARY KEY  (`idCantor`),
  KEY `chaveUnica` (`chaveUnica`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cantor` */

/*Table structure for table `colecao` */

CREATE TABLE `colecao` (
  `idColecao` bigint(20) NOT NULL auto_increment,
  `nome` varchar(20) NOT NULL,
  `descricao` varchar(255) default NULL,
  `created` datetime default NULL,
  `modified` datetime default NULL,
  PRIMARY KEY  (`idColecao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `colecao` */

/*Table structure for table `configuracoes` */

CREATE TABLE `configuracoes` (
  `configuracao` varchar(100) NOT NULL COMMENT 'nome da configuração',
  `valor` varchar(1000) NOT NULL COMMENT 'valor da configuração',
  PRIMARY KEY  (`configuracao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `configuracoes` */

insert into `configuracoes` values 

('diretorio_base','Coleção Checada'),

('intervalo_musicas','5'),

('playlist_atual','default'),

('playlist_indice_atual','0'),

('sincronizacao','inativa'),

('versao','1.1');

/*Table structure for table `log` */

CREATE TABLE `log` (
  `idLog` bigint(20) NOT NULL auto_increment,
  `chaveUnica` varchar(255) default NULL,
  `chaveUnicaObjeto` varchar(255) default NULL COMMENT 'Chave Única do Objeto Cadastrado',
  `tipoOperacao` int(11) NOT NULL,
  `classeObjeto` varchar(100) NOT NULL,
  `objeto` blob NOT NULL,
  `data` datetime NOT NULL,
  `excluido` tinyint(4) default '0',
  PRIMARY KEY  (`idLog`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `log` */

/*Table structure for table `musica` */

CREATE TABLE `musica` (
  `idMusica` bigint(20) unsigned NOT NULL auto_increment,
  `chaveUnica` varchar(255) default NULL,
  `idTipo` int(10) unsigned default NULL,
  `idQualidade` int(10) unsigned default NULL,
  `nome` varchar(500) NOT NULL,
  `letra` text,
  `duracao` int(10) unsigned default NULL,
  `observacao` text,
  `nomearquivo` varchar(600) NOT NULL,
  `diretorio` varchar(100) default NULL,
  `arquivoCapa` longblob,
  `nomeArquivoCapa` varchar(1000) default NULL,
  `ano` int(11) default NULL,
  `created` datetime default NULL,
  `modified` datetime default NULL,
  PRIMARY KEY  (`idMusica`),
  KEY `idQualidade` (`idQualidade`),
  KEY `idTipo` (`idTipo`),
  KEY `chaveUnica` (`chaveUnica`),
  KEY `nome_musica` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `musica` */

/*Table structure for table `musicaassunto` */

CREATE TABLE `musicaassunto` (
  `idAssunto` int(10) unsigned NOT NULL,
  `idMusica` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idAssunto`,`idMusica`),
  KEY `idMusica` (`idMusica`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `musicaassunto` */

/*Table structure for table `musicacantor` */

CREATE TABLE `musicacantor` (
  `idCantor` int(10) unsigned NOT NULL,
  `idMusica` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`idCantor`,`idMusica`),
  KEY `idMusica` (`idMusica`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `musicacantor` */

/*Table structure for table `musicacolecao` */

CREATE TABLE `musicacolecao` (
  `idMusicaColecao` bigint(20) NOT NULL auto_increment,
  `idMusica` bigint(20) unsigned NOT NULL,
  `idColecao` bigint(20) NOT NULL,
  `ordem` int(11) default '2147483647',
  PRIMARY KEY  (`idMusicaColecao`),
  KEY `idMusica` (`idMusica`),
  KEY `idColecao` (`idColecao`),
  CONSTRAINT `musicacolecao_ibfk_1` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `musicacolecao_ibfk_2` FOREIGN KEY (`idColecao`) REFERENCES `colecao` (`idColecao`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `musicacolecao` */

/*Table structure for table `playlist` */

CREATE TABLE `playlist` (
  `idPlaylist` bigint(20) unsigned NOT NULL auto_increment,
  `nome` varchar(160) NOT NULL,
  `created` datetime default NULL,
  `modified` datetime default NULL,
  PRIMARY KEY  (`idPlaylist`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `playlist` */

insert into `playlist` values 

(1,'default','2010-02-22 23:26:37','2010-02-22 23:26:37'),

(2,'default','2010-02-22 23:26:37','2010-02-22 23:26:37'),

(3,'default','2010-02-22 23:26:37','2010-02-22 23:26:37');

/*Table structure for table `playlistitem` */

CREATE TABLE `playlistitem` (
  `idPlaylist` bigint(20) unsigned NOT NULL,
  `idMusica` bigint(20) unsigned NOT NULL,
  `ordem` int(11) default '2147483647',
  KEY `idPlaylist` (`idPlaylist`),
  KEY `idMusica` (`idMusica`),
  KEY `idPlaylist_2` (`idPlaylist`),
  KEY `idMusica_2` (`idMusica`),
  KEY `idPlaylist_3` (`idPlaylist`),
  KEY `idMusica_3` (`idMusica`),
  CONSTRAINT `playlistitem_ibfk_2` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `playlistitem_ibfk_3` FOREIGN KEY (`idPlaylist`) REFERENCES `playlist` (`idPlaylist`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `playlistitem_ibfk_4` FOREIGN KEY (`idPlaylist`) REFERENCES `playlist` (`idPlaylist`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `playlistitem_ibfk_5` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `playlistitem_ibfk_6` FOREIGN KEY (`idPlaylist`) REFERENCES `playlist` (`idPlaylist`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `playlistitem_ibfk_7` FOREIGN KEY (`idMusica`) REFERENCES `musica` (`idMusica`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `playlistitem` */

/*Table structure for table `qualidade` */

CREATE TABLE `qualidade` (
  `idQualidade` int(10) unsigned NOT NULL auto_increment,
  `qualidade` varchar(30) NOT NULL,
  PRIMARY KEY  (`idQualidade`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `qualidade` */

insert into `qualidade` values 

(1,'Ruim'),

(2,'Normal'),

(3,'Boa'),

(4,'Ótima');

/*Table structure for table `tipo` */

CREATE TABLE `tipo` (
  `idTipo` int(10) unsigned NOT NULL auto_increment,
  `chaveUnica` varchar(255) default NULL,
  `tipo` varchar(100) NOT NULL,
  `created` datetime default NULL,
  `modified` datetime default NULL,
  PRIMARY KEY  (`idTipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tipo` */

SET FOREIGN_KEY_CHECKS=1;
