package dao;

import classesbasicas.Usuario;
import exceptions.DataException;

public interface UsuarioDAO {
	public void cadastrarUsuario(Usuario u) throws DataException;
	public void removerUsuario(Usuario u) throws DataException;
	public void alterarUsuario(Usuario u) throws DataException;
	public Usuario login(String login, String senha) throws DataException;
}
