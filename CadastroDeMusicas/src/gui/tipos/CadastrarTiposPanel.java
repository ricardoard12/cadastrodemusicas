package gui.tipos;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JButton;

import classesbasicas.Tipo;
import exceptions.DataException;
import fachada.Fachada;

public class CadastrarTiposPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel dadosPanel = null;
	private JLabel tipoLabel = null;
	private JTextField tipoTextField = null;
	private JPanel botoesPanel = null;
	private JButton cancelarButton = null;
	private JButton cadastrarButton = null;

	/**
	 * This is the default constructor
	 */
	public CadastrarTiposPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.gridy = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridy = 0;
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.add(getDadosPanel(), gridBagConstraints);
		this.add(getBotoesPanel(), gridBagConstraints3);
	}

	/**
	 * This method initializes dadosPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosPanel() {
		if (dadosPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.anchor = GridBagConstraints.SOUTHWEST;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints1.gridx = 0;
			tipoLabel = new JLabel();
			tipoLabel.setText("Ritmo");
			dadosPanel = new JPanel();
			dadosPanel.setLayout(new GridBagLayout());
			dadosPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Ritmo", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			dadosPanel.add(tipoLabel, gridBagConstraints2);
			dadosPanel.add(getTipoTextField(), gridBagConstraints1);
		}
		return dadosPanel;
	}

	/**
	 * This method initializes tipoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTipoTextField() {
		if (tipoTextField == null) {
			tipoTextField = new JTextField();
			tipoTextField.setColumns(12);
		}
		return tipoTextField;
	}

	/**
	 * This method initializes botoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotoesPanel() {
		if (botoesPanel == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 0;
			botoesPanel = new JPanel();
			botoesPanel.setLayout(new GridBagLayout());
			botoesPanel.add(getCancelarButton(), new GridBagConstraints());
			botoesPanel.add(getCadastrarButton(), gridBagConstraints4);
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
	
	private void cancelarOperacao() {
		limparCampos();
	}
	
	private void limparCampos() {
		getTipoTextField().setText("");
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
					cadastrarTipo();
				}
			});
		}
		return cadastrarButton;
	}
	
	private void cadastrarTipo() {
		if (getTipoTextField().getText() == null || getTipoTextField().getText().trim().equals("")) {
			cancelarOperacao();
			return;
		}
		
		Tipo t = new Tipo();
		
		t.setTipo(getTipoTextField().getText());
		try {
			Fachada.cadastrarTipo(t);
			
			JOptionPane.showMessageDialog(CadastrarTiposPanel.this, "Ritmo Cadastrado com sucesso!", 
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			
			cancelarOperacao();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
