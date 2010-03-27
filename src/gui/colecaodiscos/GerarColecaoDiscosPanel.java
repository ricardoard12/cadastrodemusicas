package gui.colecaodiscos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classesbasicas.Constantes;
import classesbasicas.Musica;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class GerarColecaoDiscosPanel extends JPanel {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private static final long serialVersionUID = 1L;
	private JPanel tipoDeMidiaPanel = null;
	private JRadioButton cdAudioRadioButton;
	private JTextField nomeColecaoTextField;
	private JLabel nomeColecaoLabel;
	private JRadioButton cdRadioButton = null;
	private JRadioButton dvdRadioButton = null;
	private JPanel localPanel = null;
	private JLabel localLabel = null;
	private JTextField localTextField = null;
	private JButton escolherLocalButton = null;
	private ButtonGroup midiaButtonGroup = null;  //  @jve:decl-index=0:visual-constraint="626,21"
	private JCheckBox caracteresIniciaisCheckBox = null;
	private JPanel limiteDeMusicasPanel = null;
	private JCheckBox limitarNumeroDeMusicasCheckBox = null;
	private JLabel numeroMaximoDeMusicasLabel = null;
	private JTextField numeroMaximoDeMusicasTextField = null;
	private JPanel relatoriosPanel = null;
	private JCheckBox relatorioPorOrdemAlfabeticaCheckBox = null;
	private JCheckBox relatorioPorCantorCheckBox = null;
	private JCheckBox relatorioPorAssuntosCheckBox = null;
	private JLabel tiposDeArquivoLabel = null;
	private JCheckBox pdfCheckBox = null;
	private JCheckBox htmlCheckBox = null;
	private JPanel botoesPanel = null;
	private JButton cancelarButton = null;
	private JButton gerarColecaoButton = null;
	
	private GerarColecaoDiscosDialog pai = null;
	private JPanel gerarColecaoPanel = null;
	private JLabel statusLabel = null;
	private JTextField statusTextField = null;
	private JTextField totalDeMusicasTextField = null;
	private JLabel totalDeMusicasLabel = null;
	private JLabel musicaAtualLabel = null;
	private JTextField musicaAtualTextField = null;
	private JProgressBar progressBar = null;
	private JScrollPane logScrollPane = null;
	private JTextArea logTextArea = null;
	
	private List<Musica> colecao = null;
	/**
	 * This is the default constructor
	 */
	public GerarColecaoDiscosPanel(GerarColecaoDiscosDialog pai) {
		super();
		this.pai = pai;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
		gridBagConstraints20.gridx = 0;
		gridBagConstraints20.gridwidth = 3;
		gridBagConstraints20.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints20.insets = new Insets(12, 0, 0, 0);
		gridBagConstraints20.gridy = 3;
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.gridx = 0;
		gridBagConstraints18.gridwidth = 3;
		gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints18.insets = new Insets(12, 0, 0, 0);
		gridBagConstraints18.gridy = 2;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 2;
		gridBagConstraints11.gridheight = 2;
		gridBagConstraints11.fill = GridBagConstraints.BOTH;
		gridBagConstraints11.insets = new Insets(0, 10, 0, 0);
		gridBagConstraints11.gridy = 0;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 1;
		gridBagConstraints7.insets = new Insets(0, 10, 0, 0);
		gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints7.gridy = 0;
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.fill = GridBagConstraints.BOTH;
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.gridy = 1;
		gridBagConstraints4.gridwidth = 2;
		gridBagConstraints4.insets = new Insets(10, 0, 0, 0);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new java.awt.Dimension(426, 396));
		this.add(getTipoDeMidiaPanel(), new GridBagConstraints(-1, -1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		this.add(getLocalPanel(), gridBagConstraints4);
		this.add(getLimiteDeMusicasPanel(), gridBagConstraints7);
		this.add(getRelatoriosPanel(), new GridBagConstraints(2, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 24, 0), 0, 0));
		this.add(getBotoesPanel(), gridBagConstraints18);
		this.add(getGerarColecaoPanel(), gridBagConstraints20);
	}

	/**
	 * This method initializes tipoDeMidiaPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTipoDeMidiaPanel() {
		if (tipoDeMidiaPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.anchor = GridBagConstraints.SOUTHWEST;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints.gridy = 1;
			tipoDeMidiaPanel = new JPanel();
			GridBagLayout tipoDeMidiaPanelLayout = new GridBagLayout();
			tipoDeMidiaPanel.setLayout(tipoDeMidiaPanelLayout);
			tipoDeMidiaPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1};
			tipoDeMidiaPanelLayout.rowHeights = new int[] {7, 7, 7};
			tipoDeMidiaPanelLayout.columnWeights = new double[] {0.1};
			tipoDeMidiaPanelLayout.columnWidths = new int[] {7};
			tipoDeMidiaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Mídia", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			tipoDeMidiaPanel.add(getCdRadioButton(), new GridBagConstraints(-1, -1, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			tipoDeMidiaPanel.add(getDvdRadioButton(), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			tipoDeMidiaPanel.add(getCdAudioRadioButton(), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		return tipoDeMidiaPanel;
	}

	/**
	 * This method initializes cdRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getCdRadioButton() {
		if (cdRadioButton == null) {
			cdRadioButton = new JRadioButton();
			cdRadioButton.setFocusable(false);
			cdRadioButton.setText("CD");
			getMidiaButtonGroup().add(cdRadioButton);
		}
		return cdRadioButton;
	}

	/**
	 * This method initializes dvdRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getDvdRadioButton() {
		if (dvdRadioButton == null) {
			dvdRadioButton = new JRadioButton();
			dvdRadioButton.setSelected(true);
			dvdRadioButton.setFocusable(false);
			dvdRadioButton.setText("DVD");
			getMidiaButtonGroup().add(dvdRadioButton);
		}
		return dvdRadioButton;
	}

	/**
	 * This method initializes localPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLocalPanel() {
		if (localPanel == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.anchor = GridBagConstraints.SOUTHWEST;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridwidth = 2;
			gridBagConstraints5.gridy = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridx = 0;
			localLabel = new JLabel();
			localLabel.setText("Local de Destino");
			localPanel = new JPanel();
			GridBagLayout localPanelLayout = new GridBagLayout();
			localPanel.setLayout(localPanelLayout);
			localPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1};
			localPanelLayout.rowHeights = new int[] {7, 7, 7, 7, 7};
			localPanelLayout.columnWeights = new double[] {0.1};
			localPanelLayout.columnWidths = new int[] {7};
			localPanel.add(localLabel, gridBagConstraints6);
			localPanel.add(getLocalTextField(), gridBagConstraints2);
			localPanel.add(getEscolherLocalButton(), gridBagConstraints3);
			localPanel.add(getCaracteresIniciaisCheckBox(), gridBagConstraints5);
			localPanel.add(getNomeColecaoLabel(), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			localPanel.add(getNomeColecaoTextField(), new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		}
		return localPanel;
	}

	/**
	 * This method initializes localTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getLocalTextField() {
		if (localTextField == null) {
			localTextField = new JTextField();
			localTextField.setColumns(20);
			localTextField.setEditable(false);
		}
		return localTextField;
	}

	/**
	 * This method initializes escolherLocalButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEscolherLocalButton() {
		if (escolherLocalButton == null) {
			escolherLocalButton = new JButton();
			escolherLocalButton.setFocusable(false);
			escolherLocalButton.setText("Escolher");
			escolherLocalButton.setPreferredSize(new Dimension(84, 18));
			escolherLocalButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					escolherLocalDeDestino();
				}
			});
		}
		return escolherLocalButton;
	}

	/**
	 * This method initializes midiaButtonGroup	
	 * 	
	 * @return javax.swing.ButtonGroup	
	 */
	private ButtonGroup getMidiaButtonGroup() {
		if (midiaButtonGroup == null) {
			midiaButtonGroup = new ButtonGroup();
		}
		return midiaButtonGroup;
	}

	/**
	 * This method initializes caracteresIniciaisCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getCaracteresIniciaisCheckBox() {
		if (caracteresIniciaisCheckBox == null) {
			caracteresIniciaisCheckBox = new JCheckBox();
			caracteresIniciaisCheckBox.setFocusable(false);
			caracteresIniciaisCheckBox.setSelected(true);
			caracteresIniciaisCheckBox.setText("Inserir Caracteres Iniciais");
		}
		return caracteresIniciaisCheckBox;
	}

	/**
	 * This method initializes limiteDeMusicasPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLimiteDeMusicasPanel() {
		if (limiteDeMusicasPanel == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = GridBagConstraints.SOUTHWEST;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.gridy = 2;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints9.gridx = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints8.insets = new Insets(4, 0, 0, 0);
			gridBagConstraints8.gridy = 1;
			numeroMaximoDeMusicasLabel = new JLabel();
			numeroMaximoDeMusicasLabel.setText("Número Máximo de Músicas");
			limiteDeMusicasPanel = new JPanel();
			limiteDeMusicasPanel.setLayout(new GridBagLayout());
			limiteDeMusicasPanel.add(getLimitarNumeroDeMusicasCheckBox(), gridBagConstraints10);
			limiteDeMusicasPanel.add(numeroMaximoDeMusicasLabel, gridBagConstraints8);
			limiteDeMusicasPanel.add(getNumeroMaximoDeMusicasTextField(), gridBagConstraints9);
		}
		return limiteDeMusicasPanel;
	}

	/**
	 * This method initializes limitarNumeroDeMusicasCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getLimitarNumeroDeMusicasCheckBox() {
		if (limitarNumeroDeMusicasCheckBox == null) {
			limitarNumeroDeMusicasCheckBox = new JCheckBox();
			limitarNumeroDeMusicasCheckBox.setFocusable(false);
			limitarNumeroDeMusicasCheckBox.setText("Limitar Número de Músicas");
			limitarNumeroDeMusicasCheckBox
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (getLimitarNumeroDeMusicasCheckBox().isSelected()) {
								getNumeroMaximoDeMusicasTextField().setEditable(true);
							} else {
								getNumeroMaximoDeMusicasTextField().setEditable(false);
							}
						}
					});
		}
		return limitarNumeroDeMusicasCheckBox;
	}

	/**
	 * This method initializes numeroMaximoDeMusicasTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNumeroMaximoDeMusicasTextField() {
		if (numeroMaximoDeMusicasTextField == null) {
			numeroMaximoDeMusicasTextField = new JTextField();
			numeroMaximoDeMusicasTextField.setColumns(10);
			numeroMaximoDeMusicasTextField.setEditable(false);
			numeroMaximoDeMusicasTextField.setEnabled(true);
		}
		return numeroMaximoDeMusicasTextField;
	}

	/**
	 * This method initializes relatoriosPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRelatoriosPanel() {
		if (relatoriosPanel == null) {
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 1;
			gridBagConstraints17.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints17.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints17.gridy = 4;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 0;
			gridBagConstraints16.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints16.gridy = 4;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 0;
			gridBagConstraints15.insets = new Insets(12, 0, 0, 0);
			gridBagConstraints15.gridwidth = 2;
			gridBagConstraints15.gridy = 3;
			tiposDeArquivoLabel = new JLabel();
			tiposDeArquivoLabel.setText("Tipos de Arquivo");
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.anchor = GridBagConstraints.WEST;
			gridBagConstraints14.gridwidth = 2;
			gridBagConstraints14.gridy = 2;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 0;
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.gridwidth = 2;
			gridBagConstraints13.gridy = 1;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.anchor = GridBagConstraints.WEST;
			gridBagConstraints12.gridwidth = 2;
			gridBagConstraints12.gridy = 0;
			relatoriosPanel = new JPanel();
			relatoriosPanel.setLayout(new GridBagLayout());
			relatoriosPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Relatórios", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			relatoriosPanel.add(getRelatorioPorOrdemAlfabeticaCheckBox(), gridBagConstraints12);
			relatoriosPanel.add(getRelatorioPorCantorCheckBox(), gridBagConstraints13);
			relatoriosPanel.add(getRelatorioPorAssuntosCheckBox(), gridBagConstraints14);
			relatoriosPanel.add(tiposDeArquivoLabel, gridBagConstraints15);
			relatoriosPanel.add(getPdfCheckBox(), gridBagConstraints16);
			relatoriosPanel.add(getHtmlCheckBox(), gridBagConstraints17);
		}
		return relatoriosPanel;
	}

	/**
	 * This method initializes relatorioPorOrdemAlfabeticaCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getRelatorioPorOrdemAlfabeticaCheckBox() {
		if (relatorioPorOrdemAlfabeticaCheckBox == null) {
			relatorioPorOrdemAlfabeticaCheckBox = new JCheckBox();
			relatorioPorOrdemAlfabeticaCheckBox.setFocusable(false);
			relatorioPorOrdemAlfabeticaCheckBox.setText("Por Ordem Alfabética");
			relatorioPorOrdemAlfabeticaCheckBox.setSelected(true);
		}
		return relatorioPorOrdemAlfabeticaCheckBox;
	}

	/**
	 * This method initializes relatorioPorCantorCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getRelatorioPorCantorCheckBox() {
		if (relatorioPorCantorCheckBox == null) {
			relatorioPorCantorCheckBox = new JCheckBox();
			relatorioPorCantorCheckBox.setFocusable(false);
			relatorioPorCantorCheckBox.setText("Por Cantor");
			relatorioPorCantorCheckBox.setSelected(true);
		}
		return relatorioPorCantorCheckBox;
	}

	/**
	 * This method initializes relatorioPorAssuntosCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getRelatorioPorAssuntosCheckBox() {
		if (relatorioPorAssuntosCheckBox == null) {
			relatorioPorAssuntosCheckBox = new JCheckBox();
			relatorioPorAssuntosCheckBox.setFocusable(false);
			relatorioPorAssuntosCheckBox.setText("Por Assuntos");
			relatorioPorAssuntosCheckBox.setSelected(true);
		}
		return relatorioPorAssuntosCheckBox;
	}

	/**
	 * This method initializes pdfCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getPdfCheckBox() {
		if (pdfCheckBox == null) {
			pdfCheckBox = new JCheckBox();
			pdfCheckBox.setFocusable(false);
			pdfCheckBox.setText("PDF");
			pdfCheckBox.setSelected(true);
		}
		return pdfCheckBox;
	}

	/**
	 * This method initializes htmlCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getHtmlCheckBox() {
		if (htmlCheckBox == null) {
			htmlCheckBox = new JCheckBox();
			htmlCheckBox.setFocusable(false);
			htmlCheckBox.setText("HTML");
		}
		return htmlCheckBox;
	}

	/**
	 * This method initializes botoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotoesPanel() {
		if (botoesPanel == null) {
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.gridx = 1;
			gridBagConstraints19.insets = new Insets(0, 34, 0, 0);
			gridBagConstraints19.gridy = 0;
			botoesPanel = new JPanel();
			botoesPanel.setLayout(new GridBagLayout());
			botoesPanel.add(getCancelarButton(), new GridBagConstraints());
			botoesPanel.add(getGerarColecaoButton(), new GridBagConstraints(1, -1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 34, 0, 0), 0, 0));
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
					//$hide>>$
					pai.dispose();
					//$hide<<$
				}
			});
		}
		return cancelarButton;
	}

	/**
	 * This method initializes gerarColecaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGerarColecaoButton() {
		if (gerarColecaoButton == null) {
			gerarColecaoButton = new JButton();
			gerarColecaoButton.setFocusable(false);
			gerarColecaoButton.setText("Gerar Coleção");
			gerarColecaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gerarColecao();
				}
			});
		}
		return gerarColecaoButton;
	}
	
	private void escolherLocalDeDestino() {
		//$hide>>$
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);					
		
		int opcao = chooser.showOpenDialog(this);
		
		if (opcao == JFileChooser.APPROVE_OPTION) { 
			getLocalTextField().setText(chooser.getSelectedFile().getPath());
		}
		//$hide<<$
	}

	/**
	 * This method initializes gerarColecaoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getGerarColecaoPanel() {
		if (gerarColecaoPanel == null) {
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.fill = GridBagConstraints.BOTH;
			gridBagConstraints28.gridy = 0;
			gridBagConstraints28.weightx = 1.0;
			gridBagConstraints28.weighty = 1.0;
			gridBagConstraints28.gridheight = 7;
			gridBagConstraints28.insets = new Insets(0, 6, 0, 0);
			gridBagConstraints28.gridx = 1;
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.gridx = 0;
			gridBagConstraints27.anchor = GridBagConstraints.WEST;
			gridBagConstraints27.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints27.insets = new Insets(6, 0, 0, 0);
			gridBagConstraints27.gridy = 6;
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints26.gridy = 5;
			gridBagConstraints26.weightx = 1.0;
			gridBagConstraints26.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints26.gridx = 0;
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			gridBagConstraints25.gridx = 0;
			gridBagConstraints25.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints25.gridy = 4;
			musicaAtualLabel = new JLabel();
			musicaAtualLabel.setText("Música Atual");
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.gridx = 0;
			gridBagConstraints24.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints24.gridy = 2;
			totalDeMusicasLabel = new JLabel();
			totalDeMusicasLabel.setText("Total de Músicas");
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints23.gridy = 3;
			gridBagConstraints23.weightx = 1.0;
			gridBagConstraints23.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints23.gridx = 0;
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.anchor = GridBagConstraints.SOUTHWEST;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridy = 1;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints21.gridx = 0;
			statusLabel = new JLabel();
			statusLabel.setText("Status");
			gerarColecaoPanel = new JPanel();
			gerarColecaoPanel.setLayout(new GridBagLayout());
			gerarColecaoPanel.add(statusLabel, gridBagConstraints22);
			gerarColecaoPanel.add(getStatusTextField(), gridBagConstraints21);
			gerarColecaoPanel.add(getTotalDeMusicasTextField(), gridBagConstraints23);
			gerarColecaoPanel.add(totalDeMusicasLabel, gridBagConstraints24);
			gerarColecaoPanel.add(musicaAtualLabel, gridBagConstraints25);
			gerarColecaoPanel.add(getMusicaAtualTextField(), gridBagConstraints26);
			gerarColecaoPanel.add(getProgressBar(), gridBagConstraints27);
			gerarColecaoPanel.add(getLogScrollPane(), gridBagConstraints28);
		}
		return gerarColecaoPanel;
	}

	/**
	 * This method initializes statusTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getStatusTextField() {
		if (statusTextField == null) {
			statusTextField = new JTextField();
			statusTextField.setColumns(12);
			statusTextField.setPreferredSize(new Dimension(112, 20));
			statusTextField.setFont(new Font("Dialog", Font.PLAIN, 10));
		}
		return statusTextField;
	}

	/**
	 * This method initializes totalDeMusicasTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getTotalDeMusicasTextField() {
		if (totalDeMusicasTextField == null) {
			totalDeMusicasTextField = new JTextField();
			totalDeMusicasTextField.setColumns(12);
		}
		return totalDeMusicasTextField;
	}

	/**
	 * This method initializes musicaAtualTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getMusicaAtualTextField() {
		if (musicaAtualTextField == null) {
			musicaAtualTextField = new JTextField();
			musicaAtualTextField.setColumns(12);
		}
		return musicaAtualTextField;
	}

	/**
	 * This method initializes progressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	protected JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
		}
		return progressBar;
	}

	/**
	 * This method initializes logScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	protected JScrollPane getLogScrollPane() {
		if (logScrollPane == null) {
			logScrollPane = new JScrollPane();
			logScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			logScrollPane.setViewportView(getLogTextArea());
		}
		return logScrollPane;
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
			logTextArea.setColumns(24);
		}
		return logTextArea;
	}

	private void gerarColecao() {
		//$hide>>$
		Map<String, String> map = new HashMap<String, String>();		
		
		map.put(Constantes.MIDIA, getCdRadioButton().isSelected() ? Constantes.MIDIA_CD : (getCdAudioRadioButton().isSelected() ? Constantes.MIDIA_CD_AUDIO : Constantes.MIDIA_DVD));
		
		map.put(Constantes.LIMITAR_MUSICAS, "" + getLimitarNumeroDeMusicasCheckBox().isSelected());
		if (map.get(Constantes.LIMITAR_MUSICAS).equals("true")) {
			String numString = getNumeroMaximoDeMusicasTextField().getText();
			try {
				Integer.parseInt(numString);
				map.put(Constantes.LIMITE_DE_MUSICAS, numString);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			}			
		}
		
		String localDeDestino = getLocalTextField().getText();
		// testando se o local é válido
		if (localDeDestino == null || localDeDestino.equals("")) {
			return;
		}
		map.put(Constantes.LOCAL_DE_DESTINO, localDeDestino);
		
		map.put(Constantes.RELATORIO_ORDEM_ALFABETICA, "" + getRelatorioPorOrdemAlfabeticaCheckBox().isSelected());
		map.put(Constantes.RELATORIO_POR_CANTOR, "" + getRelatorioPorCantorCheckBox().isSelected());
		map.put(Constantes.RELATORIO_POR_ASSUNTO, "" + getRelatorioPorAssuntosCheckBox().isSelected());
		map.put(Constantes.RELATORIO_PDF, "" + getPdfCheckBox().isSelected());
		map.put(Constantes.RELATORIO_HTML, "" + getHtmlCheckBox().isSelected());
		
		map.put(Constantes.INSERIR_LETRAS_INICIAIS, "" + getCaracteresIniciaisCheckBox().isSelected());
		String nomeColecao = getNomeColecaoTextField().getText();
		if (nomeColecao == null || nomeColecao.length() <= 0) {
			nomeColecao = "Coleção de Músicas";
		}
		map.put(Constantes.NOME_COLECAO, nomeColecao);
		
		if (colecao == null) {
			GerarColecaoDiscosThread thread = new GerarColecaoDiscosThread(this, map);
			thread.start();	
		} else {
			GerarColecaoDiscosThread thread = new GerarColecaoDiscosThread(colecao, this, map);
			thread.start();
		}
		//$hide<<$
	}
	
	public void setColecao(List<Musica> colecao) {
		this.colecao = colecao;
	}
	
	private JLabel getNomeColecaoLabel() {
		if(nomeColecaoLabel == null) {
			nomeColecaoLabel = new JLabel();
			nomeColecaoLabel.setText("Nome da Coleção");
		}
		return nomeColecaoLabel;
	}
	
	private JTextField getNomeColecaoTextField() {
		if(nomeColecaoTextField == null) {
			nomeColecaoTextField = new JTextField();
		}
		return nomeColecaoTextField;
	}
	
	private JRadioButton getCdAudioRadioButton() {
		if(cdAudioRadioButton == null) {
			cdAudioRadioButton = new JRadioButton();
			cdAudioRadioButton.setText("CD Áudio");
			cdAudioRadioButton.setFocusable(false);
			getMidiaButtonGroup().add(cdAudioRadioButton);
		}
		return cdAudioRadioButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
