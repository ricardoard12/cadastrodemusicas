package gui.assuntos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classesbasicas.Assunto;
import exceptions.DataException;
import fachada.Fachada;

public class CadastrarAssuntosPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel dadosPanel = null;
	private JLabel assuntoLabel = null;
	private JTextField assuntoTextField = null;
	private JPanel botoesPanel = null;
	private JButton cancelarButton = null;
	private JButton cadastrarButton = null;

	/**
	 * This is the default constructor
	 */
	public CadastrarAssuntosPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.gridy = 1;
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.add(getDadosPanel(), gridBagConstraints4);
		this.add(getBotoesPanel(), gridBagConstraints2);
	}

	/**
	 * This method initializes dadosPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosPanel() {
		if (dadosPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.anchor = GridBagConstraints.SOUTHWEST;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridx = 0;
			assuntoLabel = new JLabel();
			assuntoLabel.setText("Assunto");
			dadosPanel = new JPanel();
			dadosPanel.setLayout(new GridBagLayout());
			dadosPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Assunto", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			dadosPanel.add(assuntoLabel, gridBagConstraints1);
			dadosPanel.add(getAssuntoTextField(), gridBagConstraints);
		}
		return dadosPanel;
	}

	/**
	 * This method initializes assuntoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAssuntoTextField() {
		if (assuntoTextField == null) {
			assuntoTextField = new JTextField();
			assuntoTextField.setColumns(14);
		}
		return assuntoTextField;
	}

	/**
	 * This method initializes botoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotoesPanel() {
		if (botoesPanel == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 0;
			botoesPanel = new JPanel();
			botoesPanel.setLayout(new GridBagLayout());
			botoesPanel.add(getCancelarButton(), new GridBagConstraints());
			botoesPanel.add(getCadastrarButton(), gridBagConstraints3);
		}
		return botoesPanel;
	}

	/**
	 * This method initializes cancelarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelarButton() {
		if (cancelarButton == null) {
			cancelarButton = new JButton();
			cancelarButton.setFocusable(false);
			cancelarButton.setText("Cancelar");
			cancelarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cancelarOperacao();
				}
			});
		}
		return cancelarButton;
	}

	/**
	 * This method initializes cadastrarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCadastrarButton() {
		if (cadastrarButton == null) {
			cadastrarButton = new JButton();
			cadastrarButton.setFocusable(false);
			cadastrarButton.setText("Cadastrar");
			cadastrarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cadastrarAssunto();
				}
			});
		}
		return cadastrarButton;
	}
	
	// métodos criados	
	private void cadastrarAssunto() {
		if (getAssuntoTextField().getText() == null || getAssuntoTextField().getText().trim().equals("")) {
			cancelarOperacao();
			return;
		}
		
		Assunto a = new Assunto();
		
		a.setAssunto(getAssuntoTextField().getText());
		
		try {
			Fachada.cadastrarAssunto(a);
			
			JOptionPane.showMessageDialog(CadastrarAssuntosPanel.this, "Assunto Cadastrado com sucesso!", 
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			
			cancelarOperacao();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cancelarOperacao() {
		limparCampos();
	}
	
	private void limparCampos() {
		getAssuntoTextField().setText("");
	}

}
