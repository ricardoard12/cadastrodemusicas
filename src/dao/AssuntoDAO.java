package dao;

import java.util.List;

import classesbasicas.Assunto;
import exceptions.DataException;

public interface AssuntoDAO {
	
	public int cadastrarAssunto(Assunto a) throws DataException;
	public void alterarAssunto(Assunto a) throws DataException;
	public void removerAssunto(Assunto a) throws DataException;
	public Assunto getAssunto(int idAssunto) throws DataException;
	public List<Assunto> listarAssuntos() throws DataException;
	public List<Assunto> listarAssuntosSemChaveUnica() throws DataException;
	public List<Assunto> listarAssuntos(String assunto) throws DataException;
	public List<Assunto> listarAssuntos(int tipoArquivo) throws DataException;
	public Assunto getAssuntoPorChaveUnica(String chaveUnica) throws DataException;
	public List<Assunto> listarAssuntos(String assunto, int tipoArquivo) throws DataException;
	
}
