package dao;

import java.util.List;

import classesbasicas.Cantor;
import exceptions.DataException;

public interface CantorDAO {
	
	public int cadastrarCantor(Cantor c) throws DataException;
	public void alterarCantor(Cantor c) throws DataException;
	public void removerCantor(Cantor c) throws DataException;
	public Cantor getCantor(int idCantor) throws DataException;
	public List<Cantor> listarCantores() throws DataException;
	public List<Cantor> listarCantoresPorDiversos(String nome, boolean naoListarPorNome, String nomeSemEspacos, boolean naoListarPorNomeSemEspacos) throws DataException;
	public List<Cantor> listarCantoresSemChaveUnica() throws DataException;
	public List<Cantor> listarCantoresPorNome(String nome) throws DataException;
	public Cantor getCantorPorChaveUnica(String chaveUnica) throws DataException;
}
