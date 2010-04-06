package dao;

import java.util.List;

import classesbasicas.Colecao;
import classesbasicas.Musica;
import exceptions.DataException;

public interface ColecaoDAO {
	public int cadastrarColecao(Colecao c) throws DataException;
	public void alterarColecao(Colecao c) throws DataException;
	public void removerColecao(Colecao c) throws DataException;	
	public List<Colecao> listarColecoes() throws DataException;
	
	public void adicionarMusicas(Colecao c, List<Musica> musicas) throws DataException;
	public void removerMusicas(Colecao c, List<Musica> musicas) throws DataException;
	public Colecao getColecao(String nome) throws DataException;
}
