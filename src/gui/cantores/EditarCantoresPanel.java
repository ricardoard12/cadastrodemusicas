package gui.cantores;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classesbasicas.Cantor;
import exceptions.DataException;
import fachada.Fachada;

public class EditarCantoresPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel dadosPanel = null;
	private JLabel nomeLabel = null;
	private JTextField nomeTextField = null;
	private JLabel nomeSemEspacosLabel = null;
	private JTextField nomeSemEspacosTextField = null;
	private JPanel botoesPanel = null;
	private JButton cancelarButton = null;
	private JButton voltarCamposButton = null;
	private JButton okButton = null;

	private Cantor cantor = null;  //  @jve:decl-index=0:
	
	/**
	 * This is the default constructor
	 */
	public EditarCantoresPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 0;
		gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints5.gridy = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		this.setSize(526, 200);
		this.setLayout(new GridBagLayout());
		this.add(getDadosPanel(), gridBagConstraints);
		this.add(getBotoesPanel(), gridBagConstraints5);
	}

	/**
	 * This method initializes dadosPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosPanel() {
		if (dadosPanel == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.anchor = GridBagConstraints.SOUTHWEST;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints3.insets = new Insets(0, 8, 0, 0);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints2.insets = new Insets(0, 8, 0, 0);
			gridBagConstraints2.gridy = 0;
			nomeSemEspacosLabel = new JLabel();
			nomeSemEspacosLabel.setText("Nome Sem Espaços");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints1.gridx = 0;
			nomeLabel = new JLabel();
			nomeLabel.setText("Nome");
			dadosPanel = new JPanel();
			dadosPanel.setLayout(new GridBagLayout());
			dadosPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Dados do Cantor", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			dadosPanel.add(nomeLabel, gridBagConstraints4);
			dadosPanel.add(getNomeTextField(), gridBagConstraints1);
			dadosPanel.add(nomeSemEspacosLabel, gridBagConstraints2);
			dadosPanel.add(getNomeSemEspacosTextField(), gridBagConstraints3);
		}
		return dadosPanel;
	}

	/**
	 * This method initializes nomeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomeTextField() {
		if (nomeTextField == null) {
			nomeTextField = new JTextField();
			nomeTextField.setEditable(false);
			nomeTextField.setColumns(16);
		}
		return nomeTextField;
	}

	/**
	 * This method initializes nomeSemEspacosTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomeSemEspacosTextField() {
		if (nomeSemEspacosTextField == null) {
			nomeSemEspacosTextField = new JTextField();
			nomeSemEspacosTextField.setEditable(false);
			nomeSemEspacosTextField.setColumns(16);
		}
		return nomeSemEspacosTextField;
	}

	/**
	 * This method initializes botoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotoesPanel() {
		if (botoesPanel == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.insets = new Insets(0, 8, 0, 0);
			gridBagConstraints7.gridy = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.insets = new Insets(0, 8, 0, 0);
			gridBagConstraints6.gridy = 0;
			botoesPanel = new JPanel();
			botoesPanel.setLayout(new GridBagLayout());
			botoesPanel.add(getCancelarButton(), new GridBagConstraints());
			botoesPanel.add(getVoltarCamposButton(), gridBagConstraints6);
			botoesPanel.add(getOkButton(), gridBagConstraints7);
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
			cancelarButton.setEnabled(false);
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
	 * This method initializes voltarCamposButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVoltarCamposButton() {
		if (voltarCamposButton == null) {
			voltarCamposButton = new JButton();
			voltarCamposButton.setEnabled(false);
			voltarCamposButton.setFocusable(false);
			voltarCamposButton.setText("Voltar Campos");
			voltarCamposButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					editarCantor(cantor);
				}
			});
		}
		return voltarCamposButton;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setEnabled(false);
			okButton.setFocusable(false);
			okButton.setText("OK");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cantor.setNome(getNomeTextField().getText());
					cantor.setNomeSemEspacos(getNomeSemEspacosTextField().getText());
					
					try {
						Fachada.alterarCantor(cantor);
						JOptionPane.showMessageDialog(EditarCantoresPanel.this, "Cantor alterado com sucesso.",
								"Sucesso!", JOptionPane.INFORMATION_MESSAGE);
						cancelarOperacao();
					} catch (DataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return okButton;
	}
	
	// métodos criados pelo programador
	public void editarCantor(Cantor c) {
		if (c == null) return;
		
		this.cantor = c;
		
		getNomeTextField().setText(cantor.getNome());
		getNomeTextField().setEditable(true);
		
		getNomeSemEspacosTextField().setText(cantor.getNomeSemEspacos());
		getNomeSemEspacosTextField().setEditable(true);
		
		getCancelarButton().setEnabled(true);
		getVoltarCamposButton().setEnabled(true);
		getOkButton().setEnabled(true);
	}
	
	private void cancelarOperacao() {
		cantor = null;
		
		getNomeTextField().setText("");
		getNomeTextField().setEditable(false);
		
		getNomeSemEspacosTextField().setText("");
		getNomeSemEspacosTextField().setEditable(false);
		
		getCancelarButton().setEnabled(false);
		getVoltarCamposButton().setEnabled(false);
		getOkButton().setEnabled(false);
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
