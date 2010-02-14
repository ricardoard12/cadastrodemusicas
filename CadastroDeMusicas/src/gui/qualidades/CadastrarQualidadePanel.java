package gui.qualidades;

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

import classesbasicas.Qualidade;
import exceptions.DataException;
import fachada.Fachada;

public class CadastrarQualidadePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel dadosPanel = null;
	private JLabel qualidadeLabel = null;
	private JTextField qualidadeTextField = null;
	private JPanel botoesPanel = null;
	private JButton cancelarButton = null;
	private JButton cadastrarButton = null;

	/**
	 * This is the default constructor
	 */
	public CadastrarQualidadePanel() {
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
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.add(getDadosPanel(), gridBagConstraints2);
		this.add(getBotoesPanel(), gridBagConstraints3);
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
			qualidadeLabel = new JLabel();
			qualidadeLabel.setText("Qualidade");
			dadosPanel = new JPanel();
			dadosPanel.setLayout(new GridBagLayout());
			dadosPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Qualidade", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			dadosPanel.add(qualidadeLabel, gridBagConstraints1);
			dadosPanel.add(getQualidadeTextField(), gridBagConstraints);
		}
		return dadosPanel;
	}

	/**
	 * This method initializes qualidadeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getQualidadeTextField() {
		if (qualidadeTextField == null) {
			qualidadeTextField = new JTextField();
			qualidadeTextField.setColumns(12);
		}
		return qualidadeTextField;
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
			cancelarButton.setText("Cancelar");
			cancelarButton.setFocusable(false);
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
		getQualidadeTextField().setText("");
	}

	/**
	 * This method initializes cadastrarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCadastrarButton() {
		if (cadastrarButton == null) {
			cadastrarButton = new JButton();
			cadastrarButton.setText("Cadastrar");
			cadastrarButton.setFocusable(false);
			cadastrarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cadastrarQualidade();
				}
			});
		}
		return cadastrarButton;
	}
	
	private void cadastrarQualidade() {
		if (getQualidadeTextField().getText() == null || getQualidadeTextField().getText().trim().equals("")) {
			cancelarOperacao();
			return;
		}
		
		Qualidade q = new Qualidade();
		
		q.setQualidade(getQualidadeTextField().getText());
		
		try {
			Fachada.cadastrarQualidade(q);
			
			JOptionPane.showMessageDialog(CadastrarQualidadePanel.this, "Qualidade Cadastrada com sucesso!", 
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			
			cancelarOperacao();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
