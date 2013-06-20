package usuarios;

import classesbasicas.Usuario;
import dao.UsuarioDAO;
import dao.impl.UsuarioDAOMySQL;
import exceptions.DataException;

public class LoginDeUsuario {
	
	private static Usuario usuario = null;
	
	public static Usuario logar(String login, String senha) {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAOMySQL();
			usuario = usuarioDAO.login(login, senha);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public static void logoff() {
		usuario = null;
	}
	
	public static Usuario getUsuario() {
		return usuario;
	}
}
