package dao;

import java.util.List;

import classesbasicas.Qualidade;
import exceptions.DataException;

public interface QualidadeDAO {
	
	public int cadastrarQualidade(Qualidade q) throws DataException;
	public void alterarQualidade(Qualidade q) throws DataException;
	public void removerQualidade(Qualidade q) throws DataException;
	public Qualidade getQualidade(int idQualidade) throws DataException;
	public List<Qualidade> listarQualidades() throws DataException;
	public int getQualidadeId(String qualidade) throws DataException;
	
}
