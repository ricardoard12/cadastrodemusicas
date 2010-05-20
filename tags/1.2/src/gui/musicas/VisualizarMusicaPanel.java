package gui.musicas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import util.GlobalPlayer;
import util.Util;
import classesbasicas.Assunto;
import classesbasicas.Musica;
import exceptions.DataException;
import fachada.Fachada;


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
public class VisualizarMusicaPanel extends JPanel {

	private Musica musicaAtual = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel dadosPrincipaisPanel = null;
	private JTextField nomeTextField = null;
	private JTextField cantorTextField = null;
	private JLabel duracaoLabel = null;
	private JTextField anoTextField;
	private JLabel anoLabel;
	private JLabel capaLabel;
	private JLabel capaLabelLabel;
	private JTextField duracaoTextField = null;
	private JLabel spacer01Label = null;
	private JLabel letraLabel = null;
	private JScrollPane letraScrollPane = null;
	private JTextArea letraTextArea = null;
	private JLabel spacer8Label = null;
	private JLabel spacer10Label = null;
	private JPanel ritmoPanel = null;
	private JLabel tipoLabel = null;
	private JTextField ritmoTextField = null;
	private JLabel nomeDaMusicaLabel = null;
	private JLabel cantorLabel = null;
	private JPanel dadosDoCadastroPanel = null;
	private JLabel nomeArquivoLabel = null;
	private JTextField nomeArquivoTextField = null;
	private JLabel observacaoLabel = null;
	private JScrollPane observacaoScrollPane = null;
	private JTextArea observacaoTextArea = null;
	private JScrollPane assuntosScrollPane = null;
	private JList assuntosList = null;
	private JLabel spacer6Label = null;
	private JLabel spacer7Label = null;
	private JLabel spacer11Label = null;
	private JLabel spacer12Label = null;
	private JLabel spacer13Label = null;
	private JLabel diretorioLabel = null;
	private JTextField diretorioTextField = null;
	private JTextField qualidadeTextField = null;
	private JLabel qualidadeLabel1 = null;
	private JLabel assuntosLabel1 = null;
	private JPanel botoesPanel = null;
	private JButton playButton = null;
	private JButton stopButton = null;
	private JPanel controlePanel = null;

	/**
	 * This is the default constructor
	 */
	public VisualizarMusicaPanel() {
		super();
		initialize();
		getCapaLabel().setMinimumSize(getCapaLabel().getSize());
		System.out.println(getCapaLabel().getSize());
		getCapaLabel().setMaximumSize(getCapaLabel().getSize());
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.fill = GridBagConstraints.BOTH;
		gridBagConstraints31.insets = new Insets(0, 2, 0, 0);
		gridBagConstraints31.gridy = 1;
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.gridx = 1;
		gridBagConstraints23.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints23.gridwidth = 1;
		gridBagConstraints23.gridy = 1;
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.gridx = 1;
		gridBagConstraints14.gridy = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.gridy = 0;
		this.setSize(754, 269);
		this.setLayout(new GridBagLayout());
		this.add(getDadosPrincipaisPanel(), gridBagConstraints);
		this.add(getDadosDoCadastroPanel(), gridBagConstraints14);
		this.add(getBotoesPanel(), gridBagConstraints23);
		this.add(getControlePanel(), gridBagConstraints31);
	}

	/**
	 * This method initializes dadosPrincipaisPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosPrincipaisPanel() {
		if (dadosPrincipaisPanel == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints6.gridy = 4;
			cantorLabel = new JLabel();
			cantorLabel.setText("Cantor");
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints8.gridy = 1;
			nomeDaMusicaLabel = new JLabel();
			nomeDaMusicaLabel.setText("Nome");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridx = 2;
			GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
			gridBagConstraints42.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints42.gridx = 2;
			gridBagConstraints42.gridy = 1;
			gridBagConstraints42.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints42.insets = new Insets(0, 0, 0, 0);
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 2;
			gridBagConstraints30.gridy = 9;
			spacer10Label = new JLabel();
			spacer10Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura150.PNG")));
			spacer10Label.setText("");
			GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
			gridBagConstraints34.gridwidth = 2;
			gridBagConstraints34.gridy = 8;
			gridBagConstraints34.gridx = 0;
			spacer8Label = new JLabel();
			spacer8Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura260.PNG")));
			spacer8Label.setText("");
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.gridy = 7;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.weighty = 1.0;
			gridBagConstraints12.gridwidth = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridwidth = 3;
			gridBagConstraints4.gridy = 6;
			gridBagConstraints4.gridx = 0;
			letraLabel = new JLabel();
			letraLabel.setText("Letra");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			spacer01Label = new JLabel();
			spacer01Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura10.png")));
			spacer01Label.setText("");
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints11.gridwidth = 1;
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridy = 5;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints10.gridy = 4;
			gridBagConstraints10.gridx = 2;
			duracaoLabel = new JLabel();
			duracaoLabel.setText("Duração");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 5;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			dadosPrincipaisPanel = new JPanel();
			dadosPrincipaisPanel.setLayout(new GridBagLayout());
			dadosPrincipaisPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Dados Principais", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			dadosPrincipaisPanel.add(getNomeTextField(), gridBagConstraints2);
			dadosPrincipaisPanel.add(getCantorTextField(), gridBagConstraints7);
			dadosPrincipaisPanel.add(duracaoLabel, gridBagConstraints10);
			dadosPrincipaisPanel.add(getDuracaoTextField(), new GridBagConstraints(2, 5, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 80), 0, 0));
			dadosPrincipaisPanel.add(spacer01Label, gridBagConstraints3);
			dadosPrincipaisPanel.add(letraLabel, new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			dadosPrincipaisPanel.add(getLetraScrollPane(), new GridBagConstraints(0, 7, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			dadosPrincipaisPanel.add(spacer8Label, gridBagConstraints34);
			dadosPrincipaisPanel.add(spacer10Label, gridBagConstraints30);
			dadosPrincipaisPanel.add(getRitmoPanel(), gridBagConstraints42);
			dadosPrincipaisPanel.add(getRitmoTextField(), gridBagConstraints1);
			dadosPrincipaisPanel.add(nomeDaMusicaLabel, gridBagConstraints8);
			dadosPrincipaisPanel.add(cantorLabel, gridBagConstraints6);
			dadosPrincipaisPanel.add(getCapaLabelLabel(), new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			dadosPrincipaisPanel.add(getCapaLabel(), new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 15, 0, 15), 0, 0));
			dadosPrincipaisPanel.add(getAnoLabel(), new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 81, 0, 0), 0, 0));
			dadosPrincipaisPanel.add(getAnoTextField(), new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 80, 0, 0), 0, 0));
		}
		return dadosPrincipaisPanel;
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
			nomeTextField.setColumns(22);
		}
		return nomeTextField;
	}

	/**
	 * This method initializes cantorTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCantorTextField() {
		if (cantorTextField == null) {
			cantorTextField = new JTextField();
			cantorTextField.setEditable(false);
			cantorTextField.setColumns(22);
		}
		return cantorTextField;
	}

	/**
	 * This method initializes duracaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDuracaoTextField() {
		if (duracaoTextField == null) {
			duracaoTextField = new JTextField();
			duracaoTextField.setEditable(false);
			duracaoTextField.setColumns(10);
		}
		return duracaoTextField;
	}

	/**
	 * This method initializes letraScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getLetraScrollPane() {
		if (letraScrollPane == null) {
			letraScrollPane = new JScrollPane();
			letraScrollPane.setPreferredSize(new Dimension(223, 118));
			letraScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			letraScrollPane.setViewportView(getLetraTextArea());
		}
		return letraScrollPane;
	}

	/**
	 * This method initializes letraTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getLetraTextArea() {
		if (letraTextArea == null) {
			letraTextArea = new JTextArea();
			letraTextArea.setColumns(20);
			letraTextArea.setRows(6);
			letraTextArea.setTabSize(6);
			// letraTextArea.setLineWrap(true);
			letraTextArea.setEditable(false);
		}
		return letraTextArea;
	}

	/**
	 * This method initializes ritmoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRitmoPanel() {
		if (ritmoPanel == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.fill = GridBagConstraints.NONE;
			tipoLabel = new JLabel();
			tipoLabel.setText("Ritmo");
			ritmoPanel = new JPanel();
			ritmoPanel.setLayout(new GridBagLayout());
			ritmoPanel.add(tipoLabel, gridBagConstraints5);
		}
		return ritmoPanel;
	}

	/**
	 * This method initializes ritmoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRitmoTextField() {
		if (ritmoTextField == null) {
			ritmoTextField = new JTextField();
			ritmoTextField.setEditable(false);
		}
		return ritmoTextField;
	}

	/**
	 * This method initializes dadosDoCadastroPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosDoCadastroPanel() {
		if (dadosDoCadastroPanel == null) {
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridx = 4;
			gridBagConstraints22.gridy = 4;
			assuntosLabel1 = new JLabel();
			assuntosLabel1.setText("Assuntos");
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 4;
			gridBagConstraints21.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints21.gridy = 0;
			qualidadeLabel1 = new JLabel();
			qualidadeLabel1.setText("Qualidade");
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.anchor = GridBagConstraints.CENTER;
			gridBagConstraints20.gridx = 4;
			gridBagConstraints20.gridy = 4;
			gridBagConstraints20.insets = new Insets(0, 0, 0, 0);
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.fill = GridBagConstraints.BOTH;
			gridBagConstraints15.gridy = 1;
			gridBagConstraints15.weightx = 1.0;
			gridBagConstraints15.gridx = 4;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints9.insets = new Insets(8, 0, 0, 0);
			gridBagConstraints9.gridx = 4;
			gridBagConstraints9.gridy = 0;
			gridBagConstraints9.fill = GridBagConstraints.NONE;
			GridBagConstraints gridBagConstraints131 = new GridBagConstraints();
			gridBagConstraints131.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints131.gridx = 0;
			gridBagConstraints131.gridy = 1;
			gridBagConstraints131.weightx = 1.0;
			gridBagConstraints131.gridwidth = 3;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints13.gridy = 0;
			gridBagConstraints13.gridx = 0;
			diretorioLabel = new JLabel();
			diretorioLabel.setText("Diretório");
			GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
			gridBagConstraints37.gridx = 3;
			gridBagConstraints37.gridy = 0;
			spacer13Label = new JLabel();
			spacer13Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura10.png")));
			spacer13Label.setText("");
			GridBagConstraints gridBagConstraints36 = new GridBagConstraints();
			gridBagConstraints36.gridx = 1;
			gridBagConstraints36.gridy = 0;
			spacer12Label = new JLabel();
			spacer12Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura10.png")));
			spacer12Label.setText("");
			GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
			gridBagConstraints35.gridx = 4;
			gridBagConstraints35.gridy = 6;
			spacer11Label = new JLabel();
			spacer11Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura100.PNG")));
			spacer11Label.setText("");
			GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
			gridBagConstraints33.gridwidth = 3;
			gridBagConstraints33.gridy = 6;
			gridBagConstraints33.gridx = 0;
			spacer7Label = new JLabel();
			spacer7Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura200.PNG")));
			spacer7Label.setText("");
			GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
			gridBagConstraints29.gridheight = 6;
			gridBagConstraints29.gridy = 0;
			gridBagConstraints29.gridx = 5;
			spacer6Label = new JLabel();
			spacer6Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerAltura200.PNG")));
			spacer6Label.setText("");
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.fill = GridBagConstraints.BOTH;
			gridBagConstraints24.gridy = 5;
			gridBagConstraints24.weightx = 1.0;
			gridBagConstraints24.weighty = 1.0;
			gridBagConstraints24.insets = new Insets(0, 2, 0, 0);
			gridBagConstraints24.gridx = 4;
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.fill = GridBagConstraints.BOTH;
			gridBagConstraints19.gridwidth = 4;
			gridBagConstraints19.gridx = 0;
			gridBagConstraints19.gridy = 5;
			gridBagConstraints19.weightx = 1.0;
			gridBagConstraints19.weighty = 1.0;
			gridBagConstraints19.gridheight = 3;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints18.gridx = 0;
			gridBagConstraints18.gridy = 4;
			gridBagConstraints18.gridwidth = 4;
			observacaoLabel = new JLabel();
			observacaoLabel.setText("Observação");
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints17.gridwidth = 5;
			gridBagConstraints17.gridx = 0;
			gridBagConstraints17.gridy = 3;
			gridBagConstraints17.weightx = 1.0;
			gridBagConstraints17.fill = GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints16.gridx = 0;
			gridBagConstraints16.gridy = 2;
			gridBagConstraints16.gridwidth = 3;
			nomeArquivoLabel = new JLabel();
			nomeArquivoLabel.setText("Nome do Arquivo");
			dadosDoCadastroPanel = new JPanel();
			dadosDoCadastroPanel.setLayout(new GridBagLayout());
			dadosDoCadastroPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Dados da Coleção", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			dadosDoCadastroPanel.add(nomeArquivoLabel, gridBagConstraints16);
			dadosDoCadastroPanel.add(getNomeArquivoTextField(), gridBagConstraints17);
			dadosDoCadastroPanel.add(observacaoLabel, gridBagConstraints18);
			dadosDoCadastroPanel.add(getObservacaoScrollPane(), gridBagConstraints19);
			dadosDoCadastroPanel.add(getAssuntosScrollPane(), gridBagConstraints24);
			dadosDoCadastroPanel.add(spacer6Label, gridBagConstraints29);
			dadosDoCadastroPanel.add(spacer7Label, gridBagConstraints33);
			dadosDoCadastroPanel.add(spacer11Label, gridBagConstraints35);
			dadosDoCadastroPanel.add(spacer12Label, gridBagConstraints36);
			dadosDoCadastroPanel.add(spacer13Label, gridBagConstraints37);
			dadosDoCadastroPanel.add(diretorioLabel, gridBagConstraints13);
			dadosDoCadastroPanel.add(getDiretorioTextField(), gridBagConstraints131);
			dadosDoCadastroPanel.add(getQualidadeTextField(), gridBagConstraints15);
			dadosDoCadastroPanel.add(qualidadeLabel1, gridBagConstraints21);
			dadosDoCadastroPanel.add(assuntosLabel1, gridBagConstraints22);
		}
		return dadosDoCadastroPanel;
	}

	/**
	 * This method initializes nomeArquivoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomeArquivoTextField() {
		if (nomeArquivoTextField == null) {
			nomeArquivoTextField = new JTextField();
			nomeArquivoTextField.setEditable(false);
			nomeArquivoTextField.setColumns(20);
		}
		return nomeArquivoTextField;
	}

	/**
	 * This method initializes observacaoScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getObservacaoScrollPane() {
		if (observacaoScrollPane == null) {
			observacaoScrollPane = new JScrollPane();
			observacaoScrollPane.setViewportView(getObservacaoTextArea());
		}
		return observacaoScrollPane;
	}

	/**
	 * This method initializes observacaoTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getObservacaoTextArea() {
		if (observacaoTextArea == null) {
			observacaoTextArea = new JTextArea();
			observacaoTextArea.setColumns(24);
			observacaoTextArea.setRows(6);
			observacaoTextArea.setLineWrap(true);
			observacaoTextArea.setEditable(false);
		}
		return observacaoTextArea;
	}

	/**
	 * This method initializes assuntosScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getAssuntosScrollPane() {
		if (assuntosScrollPane == null) {
			assuntosScrollPane = new JScrollPane();
			assuntosScrollPane.setViewportView(getAssuntosList());
		}
		return assuntosScrollPane;
	}


	/**
	 * This method initializes assuntosList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getAssuntosList() {
		if (assuntosList == null) {
			assuntosList = new JList();
			assuntosList.setVisibleRowCount(6);
		}
		return assuntosList;
	}



	/**
	 * This method initializes diretorioTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDiretorioTextField() {
		if (diretorioTextField == null) {
			diretorioTextField = new JTextField();
			diretorioTextField.setEditable(false);
			diretorioTextField.setColumns(20);
		}
		return diretorioTextField;
	}

	/**
	 * This method initializes qualidadeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getQualidadeTextField() {
		if (qualidadeTextField == null) {
			qualidadeTextField = new JTextField();
			qualidadeTextField.setEditable(false);
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
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.insets = new Insets(0, 0, 0, 0);
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			gridBagConstraints25.gridx = 1;
			gridBagConstraints25.insets = new Insets(0, 45, 0, 0);
			gridBagConstraints25.gridy = 0;
			botoesPanel = new JPanel();
			botoesPanel.setLayout(new GridBagLayout());
			botoesPanel.add(getPlayButton(), gridBagConstraints26);
			botoesPanel.add(getStopButton(), gridBagConstraints25);
		}
		return botoesPanel;
	}

	/**
	 * This method initializes playButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPlayButton() {
		if (playButton == null) {
			playButton = new JButton();
			playButton.setText("Play");
			playButton.setFocusable(false);
			playButton.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/media_play_green.png")));
			playButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (musicaAtual != null) {
						//$hide>>$
						String nomeArquivo = Util.getDiretorioBase() + File.separator + musicaAtual.getDiretorio() +
						File.separator + musicaAtual.getNomeArquivo();
						GlobalPlayer.play(nomeArquivo);
						
						getControlePanel().add(GlobalPlayer.getControle(), BorderLayout.NORTH);				
						getControlePanel().validate();
						GlobalPlayer.getControle().repaint();
						//$hide<<$
					}
					
				}
			});
		}
		return playButton;
	}

	/**
	 * This method initializes stopButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStopButton() {
		if (stopButton == null) {
			stopButton = new JButton();
			stopButton.setText("Stop");
			stopButton.setFocusable(false);
			stopButton.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/media_stop_red.png")));
			stopButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					GlobalPlayer.stop();
					//$hide<<$
				}
			});
		}
		return stopButton;
	}

	/**
	 * This method initializes controlePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getControlePanel() {
		if (controlePanel == null) {
			controlePanel = new JPanel();
			controlePanel.setLayout(new BorderLayout());
		}
		return controlePanel;
	}
	
	public void visualizarMusica(Musica musica) {
		musicaAtual = musica;
		
		// dados principais
		getNomeTextField().setText(musica.getNome());
		if (musica.getTipo() != null) {
			getRitmoTextField().setText(musica.getTipo().getTipo());
		}
		if (musica.getCantores() != null && musica.getCantores().size() >= 1) {
			getCantorTextField().setText(musica.getCantores().get(0).getNome());
		}
		if (musica.getDuracao() > 0) {
			getDuracaoTextField().setText(Util.formataDuracao(musica.getDuracao()));
		}
		if (musica.getLetra() != null) {
			getLetraTextArea().setText(musica.getLetra());
		}
		
		if (musica.getAno() > 1900 && musica.getAno() < 3000) {
			getAnoTextField().setText("" + musica.getAno());
		}
		
		// mostrar a capa
		if (musica.getNomeArquivoCapa() != null) {
			try {
				BufferedImage bi = ImageIO.read(Fachada.getCapaDiscoMusica(musica));
				int side = getCapaLabel().getWidth() < getCapaLabel().getHeight() ? getCapaLabel().getWidth() : getCapaLabel().getHeight();
				Image scaledImage = bi.getScaledInstance(side, side, Image.SCALE_SMOOTH);
				capaLabel.setText("");
				capaLabel.setIcon(new ImageIcon(scaledImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// dados da coleção
		getDiretorioTextField().setText(musica.getDiretorio());
		if (musica.getQualidade() != null) {
			getQualidadeTextField().setText(musica.getQualidade().getQualidade());
		}
		getNomeArquivoTextField().setText(musica.getNomeArquivo());
		if (musica.getObservacao() != null) {
			getObservacaoTextArea().setText(musica.getObservacao());
		}
		if (musica.getAssuntos() != null && musica.getAssuntos().size() > 0) {
			Vector<String> assuntos = new Vector<String>();
			
			for (Assunto a: musica.getAssuntos()) {
				assuntos.add(a.getAssunto());
			}
			
			getAssuntosList().setListData(assuntos);
		}	
		
		repaint();
	}
	
	private JLabel getCapaLabelLabel() {
		if(capaLabelLabel == null) {
			capaLabelLabel = new JLabel();
			capaLabelLabel.setText("Capa do Disco");
		}
		return capaLabelLabel;
	}
	
	private JLabel getCapaLabel() {
		if(capaLabel == null) {
			capaLabel = new JLabel();
			capaLabel.setHorizontalAlignment(SwingConstants.CENTER);
			capaLabel.setIcon(null);
			capaLabel.setIconTextGap(0);
			capaLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			capaLabel.setMaximumSize(new java.awt.Dimension(150, 115));
			capaLabel.setMinimumSize(new java.awt.Dimension(150, 115));
			capaLabel.setPreferredSize(new java.awt.Dimension(150, 115));
			capaLabel.setText("N/A");
			capaLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					//$hide>>$
					if (!capaLabel.isEnabled() || musicaAtual.getNomeArquivoCapa() == null) return;

					try {
						BufferedImage bi;
						try {
							bi = ImageIO.read(Fachada.getCapaDiscoMusica(musicaAtual));
						} catch (DataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return;
						}
						
						int largura = bi.getWidth();
						int altura = bi.getHeight();
						double fator = 1.0;
						
						if (largura > 700 || altura > 700) {
							if (largura > altura) {
								fator = 700.0 / largura;
							} else {
								fator = 700.0 / altura;
							}
						}
						
						Image imagem = bi.getScaledInstance((int) (largura * fator), (int) (altura * fator), Image.SCALE_SMOOTH);
						System.out.println("Largura: " + imagem.getWidth(null) + " Altura: " + imagem.getHeight(null));
						JOptionPane.showMessageDialog(VisualizarMusicaPanel.this, null, 
							"Capa Selecionada", JOptionPane.PLAIN_MESSAGE, new ImageIcon(imagem));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//$hide<<$
				}
			});
		}
		return capaLabel;
	}
	
	private JLabel getAnoLabel() {
		if(anoLabel == null) {
			anoLabel = new JLabel();
			anoLabel.setText("Ano");
		}
		return anoLabel;
	}
	
	private JTextField getAnoTextField() {
		if(anoTextField == null) {
			anoTextField = new JTextField();
			anoTextField.setEditable(false);
		}
		return anoTextField;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
