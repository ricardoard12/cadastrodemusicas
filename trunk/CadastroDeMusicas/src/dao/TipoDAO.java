package dao;

import java.util.List;

import classesbasicas.Tipo;
import exceptions.DataException;

public interface TipoDAO {
	
	public int cadastrarTipo(Tipo t) throws DataException;
	public void alterarTipo(Tipo t) throws DataException;
	public void removerTipo(Tipo t) throws DataException;
	public Tipo getTipo(int idTipo) throws DataException;
	public List<Tipo> listarTipos() throws DataException;
	public List<Tipo> listarTiposSemChaveUnica() throws DataException;
	
}
