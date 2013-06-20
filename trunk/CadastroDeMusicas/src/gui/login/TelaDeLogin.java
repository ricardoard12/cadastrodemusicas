package gui.login;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import usuarios.LoginDeUsuario;
import classesbasicas.Usuario;

public class TelaDeLogin {
	
	private static String login = "";
	private static String senha = "";
	
	private static boolean usuarioCancelou = false;
	
	public static void mostrarTela() {
		usuarioCancelou = false;
		
		LoginPanel panel = new LoginPanel();
		
		JOptionPane option = new JOptionPane();
		JPanel painel = new JPanel();
		
		Color cor1 = option.getBackground();
		Color cor2 = painel.getBackground();
		
		UIManager.put("OptionPane.background", panel.getBackground());
		UIManager.put("Panel.background", panel.getBackground());
		
		int opcao = JOptionPane.showOptionDialog(null, panel, "Login",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, null, null);
		if ((opcao == JOptionPane.CLOSED_OPTION)
				|| (opcao == JOptionPane.CANCEL_OPTION)) {
			usuarioCancelou = true;
			return;
		}
		
		UIManager.put("OptionPane.background", cor1);
		UIManager.put("Panel.background", cor2);
		
		login = panel.getLoginTextField().getText();
		senha = new String(panel.getSenhaPasswordField().getPassword());
	}
	
	public static void logar() {
		Usuario u = null;
		
		do {
			u = null;
			
			mostrarTela();
			
			if (usuarioCancelou) {
				return;
			} else if (login == null || login.equals("")) {
				JOptionPane.showMessageDialog(null, "Login inválido!\nPor favor, forneça um login válido.", "Login inválido", JOptionPane.ERROR_MESSAGE);
			} else if (senha == null || senha.equals("")) {
				JOptionPane.showMessageDialog(null, "Senha inválida!\nPor favor, forneça uma senha válida.", "Senha inválida", JOptionPane.ERROR_MESSAGE);
			} else {
				u = LoginDeUsuario.logar(login, senha);
				
				if (u == null) {
					JOptionPane.showMessageDialog(null, "Usuário inexistente ou senha incorreta", "Não foi possível logar", JOptionPane.ERROR_MESSAGE);
				}
			}			
		} while (u == null);
	}
}
