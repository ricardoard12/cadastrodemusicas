package gui.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel loginLabel = null;
	private JTextField loginTextField = null;
	private JLabel senhaLabel = null;
	private JPasswordField senhaPasswordField = null;
	private JLabel imagemLabel = null;
	/**
	 * This is the default constructor
	 */
	public LoginPanel() {
		super();
		initialize();
		setPreferredSize(new Dimension(313, 176));
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		imagemLabel = new JLabel();
		imagemLabel.setBounds(new Rectangle(2, 1, 173, 157));
		imagemLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
		imagemLabel.setIcon(new ImageIcon(getClass().getResource("/figuras/Marca Interdance MUITO Menor so a figura.PNG")));
		imagemLabel.setText("");
		senhaLabel = new JLabel();
		senhaLabel.setBounds(new Rectangle(120, 86, 46, 16));
		senhaLabel.setText("Senha:");
		loginLabel = new JLabel();
		loginLabel.setBounds(new Rectangle(120, 60, 38, 16));
		loginLabel.setText("Login:");
		this.setSize(313, 158);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.add(loginLabel, null);
		this.add(getLoginTextField(), null);
		this.add(senhaLabel, null);
		this.add(getSenhaPasswordField(), null);
		this.add(imagemLabel, null);
	}

	/**
	 * This method initializes loginTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getLoginTextField() {
		if (loginTextField == null) {
			loginTextField = new JTextField();
			loginTextField.setBounds(new Rectangle(170, 60, 131, 20));
			loginTextField.setColumns(10);
		}
		return loginTextField;
	}

	/**
	 * This method initializes senhaPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	public JPasswordField getSenhaPasswordField() {
		if (senhaPasswordField == null) {
			senhaPasswordField = new JPasswordField();
			senhaPasswordField.setBounds(new Rectangle(170, 86, 131, 20));
			senhaPasswordField.setColumns(10);
		}
		return senhaPasswordField;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
