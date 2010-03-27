package dao;

import java.util.List;

import classesbasicas.Log;
import exceptions.DataException;

public interface LogDAO {
	public int cadastrarLog(Log log) throws DataException;
	public List<Log> listarLogs() throws DataException;
}
