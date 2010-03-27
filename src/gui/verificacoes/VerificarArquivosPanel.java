package gui.verificacoes;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.Util;

public class VerificarArquivosPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea logTextArea = null;
	private JPanel botoesPanel = null;
	private JButton verificarArquivosButton = null;
	private JLabel musicaLabel = null;
	private JTextField musicaTextField = null;
	private JLabel problemasEncontradosLabel = null;
	private JTextField problemasEncontradosTextField = null;
	private JLabel diretorioBaseLabel = null;
	private JTextField diretorioBaseTextField = null;
	private JScrollPane logScrollPane = null;
	private JLabel verificadasLabel = null;
	private JTextField verificadasTextField = null;
	private JLabel totalLabel = null;
	private JTextField totalTextField = null;
	/**
	 * This is the default constructor
	 */
	public VerificarArquivosPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.fill = GridBagConstraints.BOTH;
		gridBagConstraints7.gridy = 1;
		gridBagConstraints7.weightx = 1.0;
		gridBagConstraints7.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints7.insets = new Insets(0, 8, 0, 0);
		gridBagConstraints7.gridx = 2;
		GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
		gridBagConstraints61.gridx = 2;
		gridBagConstraints61.anchor = GridBagConstraints.SOUTHWEST;
		gridBagConstraints61.insets = new Insets(0, 8, 0, 0);
		gridBagConstraints61.gridy = 0;
		totalLabel = new JLabel();
		totalLabel.setText("Total");
		GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
		gridBagConstraints51.fill = GridBagConstraints.BOTH;
		gridBagConstraints51.gridy = 1;
		gridBagConstraints51.weightx = 1.0;
		gridBagConstraints51.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints51.insets = new Insets(0, 8, 0, 0);
		gridBagConstraints51.gridx = 1;
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.gridx = 1;
		gridBagConstraints41.anchor = GridBagConstraints.SOUTHWEST;
		gridBagConstraints41.insets = new Insets(0, 8, 0, 0);
		gridBagConstraints41.gridy = 0;
		verificadasLabel = new JLabel();
		verificadasLabel.setText("Verificados");
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.BOTH;
		gridBagConstraints31.gridy = 6;
		gridBagConstraints31.weightx = 1.0;
		gridBagConstraints31.weighty = 1.0;
		gridBagConstraints31.gridwidth = 3;
		gridBagConstraints31.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints31.gridx = 0;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.BOTH;
		gridBagConstraints21.gridy = 3;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.gridwidth = 2;
		gridBagConstraints21.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints21.gridx = 0;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.anchor = GridBagConstraints.SOUTHWEST;
		gridBagConstraints11.gridy = 2;
		diretorioBaseLabel = new JLabel();
		diretorioBaseLabel.setText("Diretório Base");
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.BOTH;
		gridBagConstraints6.gridy = 3;
		gridBagConstraints6.weightx = 1.0;
		gridBagConstraints6.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints6.insets = new Insets(0, 8, 0, 0);
		gridBagConstraints6.gridwidth = 1;
		gridBagConstraints6.gridx = 2;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.gridx = 2;
		gridBagConstraints5.anchor = GridBagConstraints.SOUTHWEST;
		gridBagConstraints5.insets = new Insets(0, 8, 0, 0);
		gridBagConstraints5.gridy = 2;
		problemasEncontradosLabel = new JLabel();
		problemasEncontradosLabel.setText("Erros");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.fill = GridBagConstraints.BOTH;
		gridBagConstraints4.gridy = 1;
		gridBagConstraints4.weightx = 1.0;
		gridBagConstraints4.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints4.gridwidth = 1;
		gridBagConstraints4.gridx = 0;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.anchor = GridBagConstraints.SOUTHWEST;
		gridBagConstraints3.gridy = 0;
		musicaLabel = new JLabel();
		musicaLabel.setText("Música Atual");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.gridwidth = 3;
		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.gridy = 7;
		this.setSize(447, 291);
		this.setLayout(new GridBagLayout());
		this.add(getBotoesPanel(), gridBagConstraints1);
		this.add(musicaLabel, gridBagConstraints3);
		this.add(getMusicaTextField(), gridBagConstraints4);
		this.add(problemasEncontradosLabel, gridBagConstraints5);
		this.add(getProblemasEncontradosTextField(), gridBagConstraints6);
		this.add(diretorioBaseLabel, gridBagConstraints11);
		this.add(getDiretorioBaseTextField(), gridBagConstraints21);
		this.add(getLogScrollPane(), gridBagConstraints31);
		this.add(verificadasLabel, gridBagConstraints41);
		this.add(getVerificadasTextField(), gridBagConstraints51);
		this.add(totalLabel, gridBagConstraints61);
		this.add(getTotalTextField(), gridBagConstraints7);
	}

	/**
	 * This method initializes logTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	protected JTextArea getLogTextArea() {
		if (logTextArea == null) {
			logTextArea = new JTextArea();
			logTextArea.setLineWrap(true);
			logTextArea.setFont(new Font("Dialog", Font.PLAIN, 10));
			logTextArea.setEditable(false);
			logTextArea.setRows(8);
		}
		return logTextArea;
	}

	/**
	 * This method initializes botoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotoesPanel() {
		if (botoesPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.insets = new Insets(8, 0, 0, 0);
			gridBagConstraints2.gridy = 0;
			botoesPanel = new JPanel();
			botoesPanel.setLayout(new GridBagLayout());
			botoesPanel.add(getVerificarArquivosButton(), gridBagConstraints2);
		}
		return botoesPanel;
	}

	/**
	 * This method initializes verificarArquivosButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVerificarArquivosButton() {
		if (verificarArquivosButton == null) {
			verificarArquivosButton = new JButton();
			verificarArquivosButton.setFocusable(false);
			verificarArquivosButton.setText("Verificar Arquivos");
			verificarArquivosButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					verificarArquivos();
				}
			});
		}
		return verificarArquivosButton;
	}

	/**
	 * This method initializes musicaTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getMusicaTextField() {
		if (musicaTextField == null) {
			musicaTextField = new JTextField();
			musicaTextField.setColumns(22);
			musicaTextField.setEditable(false);
		}
		return musicaTextField;
	}

	/**
	 * This method initializes problemasEncontradosTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getProblemasEncontradosTextField() {
		if (problemasEncontradosTextField == null) {
			problemasEncontradosTextField = new JTextField();
			problemasEncontradosTextField.setColumns(8);
			problemasEncontradosTextField.setEditable(false);
		}
		return problemasEncontradosTextField;
	}
	
	public void verificarArquivos() {
		VerificarArquivosThread thread = new VerificarArquivosThread(this);
		thread.start();
	}

	/**
	 * This method initializes diretorioBaseTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDiretorioBaseTextField() {
		if (diretorioBaseTextField == null) {
			diretorioBaseTextField = new JTextField();
			diretorioBaseTextField.setColumns(36);
			diretorioBaseTextField.setEditable(false);
			diretorioBaseTextField.setText(Util.getDiretorioBase());
		}
		return diretorioBaseTextField;
	}

	/**
	 * This method initializes logScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getLogScrollPane() {
		if (logScrollPane == null) {
			logScrollPane = new JScrollPane();
			logScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			logScrollPane.setViewportView(getLogTextArea());
		}
		return logScrollPane;
	}

	/**
	 * This method initializes verificadasTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getVerificadasTextField() {
		if (verificadasTextField == null) {
			verificadasTextField = new JTextField();
			verificadasTextField.setColumns(8);
			verificadasTextField.setEditable(false);
		}
		return verificadasTextField;
	}

	/**
	 * This method initializes totalTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getTotalTextField() {
		if (totalTextField == null) {
			totalTextField = new JTextField();
			totalTextField.setColumns(8);
			totalTextField.setEditable(false);
		}
		return totalTextField;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
