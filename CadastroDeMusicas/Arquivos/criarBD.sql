CREATE DATABASE CadastroDeMusicas;
USE CadastroDeMusicas;

CREATE TABLE Tipo (
  idTipo INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  tipo VARCHAR(100) NOT NULL,
  PRIMARY KEY(idTipo)
);

CREATE TABLE Qualidade (
  idQualidade INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  qualidade VARCHAR(30) NOT NULL,
  PRIMARY KEY(idQualidade)
);

CREATE TABLE DiretorioBase (
  diretorio VARCHAR(500) NOT NULL,
  PRIMARY KEY(diretorio)
);

CREATE TABLE Assunto (
  idAssunto INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  assunto VARCHAR(100) NULL,
  PRIMARY KEY(idAssunto)
);

CREATE TABLE Cantor (
  idCantor INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nomesemespacos VARCHAR(150) NULL,
  nome VARCHAR(150) NULL,
  PRIMARY KEY(idCantor)
);

CREATE TABLE Musica (
  idMusica INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  idTipo INTEGER UNSIGNED NULL,
  idQualidade INTEGER UNSIGNED NULL,
  nome VARCHAR(200) NOT NULL,
  letra TEXT NULL,
  duracao INTEGER UNSIGNED NULL,
  observacao TEXT NULL,
  nomearquivo VARCHAR(200) NOT NULL,
  diretorio VARCHAR(100) NULL,
  PRIMARY KEY(idMusica),
  FOREIGN KEY(idQualidade)
    REFERENCES Qualidade(idQualidade)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idTipo)
    REFERENCES Tipo(idTipo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MusicaAssunto (
  idAssunto INTEGER UNSIGNED NOT NULL,
  idMusica INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idAssunto, idMusica),
  FOREIGN KEY(idMusica)
    REFERENCES Musica(idMusica)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idAssunto)
    REFERENCES Assunto(idAssunto)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MusicaCantor (
  idCantor INTEGER UNSIGNED NOT NULL,
  idMusica INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idCantor, idMusica),
  FOREIGN KEY(idMusica)
    REFERENCES Musica(idMusica)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idCantor)
    REFERENCES Cantor(idCantor)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


