package gui.musicas;

import exceptions.DataException;
import fachada.Fachada;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import util.GlobalPlayer;
import util.Util;
import classesbasicas.Assunto;
import classesbasicas.Cantor;
import classesbasicas.Constantes;
import classesbasicas.Musica;
import classesbasicas.Qualidade;
import classesbasicas.Tipo;


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
public class EditarMusicasPanel extends JPanel {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	private MusicasInternalFrame pai = null;
	
	// Música que está sendo editada atualmente
	Musica musica = null;  //  @jve:decl-index=0:
	
	private List<Tipo> tipos = null;  //  @jve:decl-index=0:
	private List<Qualidade> qualidades = null;  //  @jve:decl-index=0:
	private List<Assunto> assuntos = null;  //  @jve:decl-index=0:
	
	private Vector<String> tiposString = null;
	private Vector<String> qualidadesString = null;  //  @jve:decl-index=0:
	private Vector<String> assuntosString = null;  //  @jve:decl-index=0:
	
	private Vector<String> cantoresNomesCampos = null;  //  @jve:decl-index=0:
	private Vector<Vector<String>> cantoresDados = null;  //  @jve:decl-index=0:
	private List<Cantor> cantores = null;  //  @jve:decl-index=0:
	private DefaultTableModel cantoresTableModel = null;
	
	private Vector<String> musicasNomesCampos = null;  //  @jve:decl-index=0:
	private Vector<Vector<String>> musicasDados = null;  //  @jve:decl-index=0:
	private List<Musica> musicas = null;  //  @jve:decl-index=0:
	private DefaultTableModel musicasTableModel = null;
	
	private MonitoraNomeTextFieldThread monitoraNomeTextFieldThread = null;  //  @jve:decl-index=0:
	private MonitoraCantorTextFieldThread monitoraCantorTextFieldThread = null;  //  @jve:decl-index=0:
	
	private static final long serialVersionUID = 1L;
	private JPanel dadosPrincipaisPanel = null;
	private JLabel nomeDaMusicaLabel = null;
	private JTextField nomeTextField = null;
	private JLabel cantorLabel = null;
	private JTextField cantorTextField = null;
	private JLabel tipoLabel = null;
	private JComboBox tipoComboBox = null;
	private JLabel duracaoLabel = null;
	private JTextField duracaoTextField = null;
	private JLabel spacer01Label = null;
	private JLabel letraLabel = null;
	private JScrollPane letraScrollPane = null;
	private JTextArea letraTextArea = null;
	private JPanel dadosDoCadastroPanel = null;
	private JLabel nomeArquivoLabel = null;
	private JTextField nomeArquivoTextField = null;
	private JLabel observacaoLabel = null;
	private JScrollPane observacaoScrollPane = null;
	private JTextArea observacaoTextArea = null;
	private JLabel qualidadeLabel = null;
	private JComboBox qualidadeComboBox = null;
	private JLabel assuntosLabel = null;
	private JScrollPane assuntosScrollPane = null;
	private JList assuntosList = null;
	private JPanel botoesPanel = null;
	private JButton okButton = null;
	private JButton cancelarButton = null;
	private JButton voltarCamposButton = null;
	private JPanel cantoresPanel = null;
	private JPanel musicasPanel = null;
	private JLabel spacer6Label = null;
	private JLabel spacer7Label = null;
	private JLabel spacer8Label = null;
	private JLabel spacer10Label = null;
	private JLabel spacer11Label = null;
	private JLabel spacer12Label = null;
	private JLabel spacer13Label = null;
	private JScrollPane cantoresScrollPane = null;
	private JTable cantoresTable = null;
	private JLabel spacer14Label = null;
	private JScrollPane musicasScrollPane = null;
	private JTable musicasTable = null;
	private JLabel spacer15Label = null;
	private JPanel ritmoPanel = null;
	private JButton novoRitmoButton = null;
	private JPanel nomeDaMusicaPanel = null;
	private JPanel qualidadePanel = null;
	private JPanel assuntosPanel = null;
	private JButton novoAssuntoButton = null;
	private JLabel anoLabel;
	private JTextField anoTextField;
	private JButton alterarCapaButton;
	private JLabel capaLabelLabel;
	private JPanel capaControlePanel;
	private JLabel capaLabel;
	private JButton limparSelecaoAssuntosButton = null;	
	private JLabel diretorioLabel = null;
	private JTextField diretorioTextField = null;
	private JButton playButton = null;
	private JButton stopButton = null;
	private JPanel controle1Panel = null;
	private JLabel spacerLabel = null;
	private JPanel botoes1Panel = null;
	private JButton play1Button = null;
	private JButton stop1Button = null;
	private JPanel controles1Panel = null;
	private JLabel spacer2Label = null;
	private JPanel controlePanel = null;

	private JButton substituirArquivoButton = null;

	private JPanel duracaoPanel = null;

	private JButton alterarDuracaButton = null;
	
	private String caminhoImagemCapaSelecionada = null;
	private String nomeArquivoCapaSelecionada = null;
	
	private int tipoArquivo;
	
	/**
	 * This is the default constructor
	 */
	public EditarMusicasPanel() {
		super();
		initialize();
		desativarCampos();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
		gridBagConstraints51.gridx = 1;
		gridBagConstraints51.fill = GridBagConstraints.BOTH;
		gridBagConstraints51.gridy = 1;
		GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
		gridBagConstraints32.gridx = 2;
		gridBagConstraints32.fill = GridBagConstraints.BOTH;
		gridBagConstraints32.gridy = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 1;
		gridBagConstraints31.gridwidth = 2;
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridy = 2;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.gridx = 2;
		gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints21.gridy = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.gridy = 0;
		this.setLayout(new GridBagLayout());
		this.setBounds(new Rectangle(0, 0, 800, 497));
		this.add(getDadosPrincipaisPanel(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		this.add(getDadosDoCadastroPanel(), gridBagConstraints21);
		this.add(getBotoesPanel(), gridBagConstraints31);
		this.add(getCantoresPanel(), gridBagConstraints32);
		this.add(getMusicasPanel(), gridBagConstraints51);
	}

	/**
	 * This method initializes dadosPrincipaisPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getDadosPrincipaisPanel() {
		if (dadosPrincipaisPanel == null) {
			GridBagConstraints gridBagConstraints55 = new GridBagConstraints();
			gridBagConstraints55.gridx = 2;
			gridBagConstraints55.anchor = GridBagConstraints.CENTER;
			gridBagConstraints55.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints55.gridy = 4;
			GridBagConstraints gridBagConstraints47 = new GridBagConstraints();
			gridBagConstraints47.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints47.gridx = 0;
			gridBagConstraints47.gridy = 4;
			gridBagConstraints47.insets = new Insets(8, 0, 0, 0);
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.gridx = 3;
			gridBagConstraints28.gridy = 10;
			spacerLabel = new JLabel();
			spacerLabel.setText("");
			spacerLabel.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerAltura20.png")));
			GridBagConstraints gridBagConstraints45 = new GridBagConstraints();
			gridBagConstraints45.gridx = 0;
			gridBagConstraints45.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints45.ipady = 0;
			gridBagConstraints45.fill = GridBagConstraints.BOTH;
			gridBagConstraints45.gridwidth = 3;
			gridBagConstraints45.gridy = 10;
			GridBagConstraints gridBagConstraints44 = new GridBagConstraints();
			gridBagConstraints44.gridx = 0;
			gridBagConstraints44.fill = GridBagConstraints.BOTH;
			gridBagConstraints44.anchor = GridBagConstraints.CENTER;
			gridBagConstraints44.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints44.gridy = 1;
			GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
			gridBagConstraints42.gridx = 2;
			gridBagConstraints42.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints42.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints42.gridy = 1;
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 2;
			gridBagConstraints30.gridy = 9;
			spacer10Label = new JLabel();
			spacer10Label.setText("");
			spacer10Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura150.PNG")));
			GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
			gridBagConstraints34.gridx = 0;
			gridBagConstraints34.gridwidth = 2;
			gridBagConstraints34.gridy = 8;
			spacer8Label = new JLabel();
			spacer8Label.setText("");
			spacer8Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura260.PNG")));
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.BOTH;
			gridBagConstraints12.gridy = 7;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.weighty = 1.0;
			gridBagConstraints12.gridwidth = 3;
			gridBagConstraints12.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridwidth = 3;
			gridBagConstraints4.gridy = 6;
			letraLabel = new JLabel();
			letraLabel.setText("Letra");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			spacer01Label = new JLabel();
			spacer01Label.setText("");
			spacer01Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura10.png")));
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 5;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints11.gridwidth = 1;
			gridBagConstraints11.gridx = 2;
			duracaoLabel = new JLabel();
			duracaoLabel.setText("Duração");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridy = 2;
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.ipady = 0;
			gridBagConstraints9.gridx = 2;
			tipoLabel = new JLabel();
			tipoLabel.setText("Ritmo");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 5;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints7.gridx = 0;
			cantorLabel = new JLabel();
			cantorLabel.setText("Cantor (Nome Sem Espaços)");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints2.gridx = 0;
			nomeDaMusicaLabel = new JLabel();
			nomeDaMusicaLabel.setText("Nome");			
			nomeDaMusicaLabel.setBounds(new Rectangle(0, 9, 33, 16));
			dadosPrincipaisPanel = new JPanel();
			dadosPrincipaisPanel.setLayout(new GridBagLayout());
			dadosPrincipaisPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Dados Principais", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			dadosPrincipaisPanel.setPreferredSize(new java.awt.Dimension(535, 276));
			dadosPrincipaisPanel.add(getNomeTextField(), gridBagConstraints2);
			dadosPrincipaisPanel.add(getCantorTextField(), new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(1, 0, 0, 0), 0, 0));
			dadosPrincipaisPanel.add(getTipoComboBox(), gridBagConstraints9);
			dadosPrincipaisPanel.add(getDuracaoTextField(), new GridBagConstraints(2, 5, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(1, 0, 4, 50), 0, 0));
			dadosPrincipaisPanel.add(spacer01Label, gridBagConstraints3);
			dadosPrincipaisPanel.add(letraLabel, new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			dadosPrincipaisPanel.add(getLetraScrollPane(), new GridBagConstraints(0, 7, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			dadosPrincipaisPanel.add(spacer8Label, gridBagConstraints34);
			dadosPrincipaisPanel.add(spacer10Label, gridBagConstraints30);
			dadosPrincipaisPanel.add(getRitmoPanel(), gridBagConstraints42);
			dadosPrincipaisPanel.add(getNomeDaMusicaPanel(), gridBagConstraints44);
			dadosPrincipaisPanel.add(getControle1Panel(), new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			dadosPrincipaisPanel.add(spacerLabel, gridBagConstraints28);
			dadosPrincipaisPanel.add(cantorLabel, gridBagConstraints47);
			dadosPrincipaisPanel.add(getDuracaoPanel(), new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 50), 0, 0));
			dadosPrincipaisPanel.add(getCapaLabel(), new GridBagConstraints(2, 7, 1, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 16, 0, 12), 0, 0));
			dadosPrincipaisPanel.add(getCapaControlePanel(), new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 14, 0, 10), 0, 0));
			dadosPrincipaisPanel.add(getAnoTextField(), new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 110, 4, 0), 0, 0));
			dadosPrincipaisPanel.add(getAnoLabel(), new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(8, 111, 0, 0), 0, 0));
			getCapaLabel().setMinimumSize(getCapaLabel().getSize());
			getCapaLabel().setMaximumSize(getCapaLabel().getSize());
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
			nomeTextField.addKeyListener(new KeyListener() {

				public void keyPressed(KeyEvent arg0) { }

				public void keyReleased(KeyEvent arg0) { }

				public void keyTyped(KeyEvent arg0) {
					//$hide>>$
					mudouNomeMusica();
					//$hide<<$
				}
				
			});
			nomeTextField.setColumns(22);
			nomeTextField.setEditable(false);
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
			cantorTextField.addKeyListener(new KeyListener() {

				public void keyPressed(KeyEvent arg0) { }

				public void keyReleased(KeyEvent arg0) { }

				public void keyTyped(KeyEvent arg0) {
					//$hide>>$
					mudouNomeCantor();
					//$hide<<$
				}
				
			});

			cantorTextField.setColumns(22);
			cantorTextField.setEditable(false);
		}
		return cantorTextField;
	}
	
	/**
	 * This method initializes tipoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTipoComboBox() {
		if (tipoComboBox == null) {
			tipoComboBox = new JComboBox(getTiposString());
			tipoComboBox.setMinimumSize(new Dimension(1, 1));
			tipoComboBox.setPreferredSize(new Dimension(150, 25));
		}
		return tipoComboBox;
	}

	/**
	 * This method initializes duracaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDuracaoTextField() {
		if (duracaoTextField == null) {
			duracaoTextField = new JTextField();
			duracaoTextField.setColumns(10);
			duracaoTextField.setEditable(false);
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
			// letraTextArea.setLineWrap(true);
			letraTextArea.setColumns(20);
			letraTextArea.setTabSize(6);
			letraTextArea.setRows(6);
			letraTextArea.repaint();
		}
		return letraTextArea;
	}

	/**
	 * This method initializes dadosDoCadastroPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosDoCadastroPanel() {
		if (dadosDoCadastroPanel == null) {
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.gridy = 1;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.gridwidth = 3;
			gridBagConstraints13.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints1.gridy = 0;
			diretorioLabel = new JLabel();
			diretorioLabel.setText("Diretório");
			GridBagConstraints gridBagConstraints53 = new GridBagConstraints();
			gridBagConstraints53.gridx = 4;
			gridBagConstraints53.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints53.gridy = 7;
			GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
			gridBagConstraints50.gridx = 4;
			gridBagConstraints50.fill = GridBagConstraints.BOTH;
			gridBagConstraints50.gridy = 4;
			GridBagConstraints gridBagConstraints48 = new GridBagConstraints();
			gridBagConstraints48.gridx = 4;
			gridBagConstraints48.fill = GridBagConstraints.NONE;
			gridBagConstraints48.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints48.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints48.gridy = 0;
			GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
			gridBagConstraints37.gridx = 3;
			gridBagConstraints37.gridy = 0;
			spacer13Label = new JLabel();
			spacer13Label.setText("");
			spacer13Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura10.png")));
			GridBagConstraints gridBagConstraints36 = new GridBagConstraints();
			gridBagConstraints36.gridx = 1;
			gridBagConstraints36.gridy = 0;
			spacer12Label = new JLabel();
			spacer12Label.setText("");
			spacer12Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura10.png")));
			GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
			gridBagConstraints35.gridx = 4;
			gridBagConstraints35.gridy = 6;
			spacer11Label = new JLabel();
			spacer11Label.setText("");
			spacer11Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura100.PNG")));
			GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
			gridBagConstraints33.gridx = 0;
			gridBagConstraints33.gridwidth = 3;
			gridBagConstraints33.gridy = 6;
			spacer7Label = new JLabel();
			spacer7Label.setText("");
			spacer7Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerLargura200.PNG")));
			GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
			gridBagConstraints29.gridx = 5;
			gridBagConstraints29.gridheight = 6;
			gridBagConstraints29.gridy = 0;
			spacer6Label = new JLabel();
			spacer6Label.setText("");
			spacer6Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerAltura200.PNG")));
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.fill = GridBagConstraints.BOTH;
			gridBagConstraints24.gridy = 5;
			gridBagConstraints24.weightx = 1.0;
			gridBagConstraints24.weighty = 1.0;
			gridBagConstraints24.gridx = 4;
			assuntosLabel = new JLabel();
			assuntosLabel.setText("Assuntos");
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridy = 1;
			gridBagConstraints22.fill = GridBagConstraints.BOTH;
			gridBagConstraints22.gridx = 4;
			qualidadeLabel = new JLabel();
			qualidadeLabel.setText("Qualidade");
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.fill = GridBagConstraints.BOTH;
			gridBagConstraints19.gridy = 5;
			gridBagConstraints19.weightx = 1.0;
			gridBagConstraints19.weighty = 1.0;
			gridBagConstraints19.gridwidth = 4;
			gridBagConstraints19.gridheight = 3;
			gridBagConstraints19.gridx = 0;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 0;
			gridBagConstraints18.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints18.gridwidth = 4;
			gridBagConstraints18.gridy = 4;
			observacaoLabel = new JLabel();
			observacaoLabel.setText("Observação");
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.fill = GridBagConstraints.BOTH;
			gridBagConstraints17.gridy = 3;
			gridBagConstraints17.weightx = 1.0;
			gridBagConstraints17.gridwidth = 5;
			gridBagConstraints17.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints17.gridx = 0;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 0;
			gridBagConstraints16.gridwidth = 3;
			gridBagConstraints16.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints16.gridy = 2;
			nomeArquivoLabel = new JLabel();
			nomeArquivoLabel.setText("Nome do Arquivo");
			dadosDoCadastroPanel = new JPanel();
			dadosDoCadastroPanel.setLayout(new GridBagLayout());
			dadosDoCadastroPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Dados da Coleção", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			dadosDoCadastroPanel.add(nomeArquivoLabel, gridBagConstraints16);
			dadosDoCadastroPanel.add(getNomeArquivoTextField(), gridBagConstraints17);
			dadosDoCadastroPanel.add(observacaoLabel, gridBagConstraints18);
			dadosDoCadastroPanel.add(getObservacaoScrollPane(), gridBagConstraints19);
			dadosDoCadastroPanel.add(getQualidadeComboBox(), gridBagConstraints22);
			dadosDoCadastroPanel.add(getAssuntosScrollPane(), gridBagConstraints24);
			dadosDoCadastroPanel.add(spacer6Label, gridBagConstraints29);
			dadosDoCadastroPanel.add(spacer7Label, gridBagConstraints33);
			dadosDoCadastroPanel.add(spacer11Label, gridBagConstraints35);
			dadosDoCadastroPanel.add(spacer12Label, gridBagConstraints36);
			dadosDoCadastroPanel.add(spacer13Label, gridBagConstraints37);
			dadosDoCadastroPanel.add(getQualidadePanel(), gridBagConstraints48);
			dadosDoCadastroPanel.add(getAssuntosPanel(), gridBagConstraints50);
			dadosDoCadastroPanel.add(getLimparSelecaoAssuntosButton(), gridBagConstraints53);
			dadosDoCadastroPanel.add(diretorioLabel, gridBagConstraints1);
			dadosDoCadastroPanel.add(getDiretorioTextField(), gridBagConstraints13);
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
			nomeArquivoTextField.setColumns(20);
			nomeArquivoTextField.setEditable(false);
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
		}
		return observacaoTextArea;
	}

	/**
	 * This method initializes qualidadeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getQualidadeComboBox() {
		if (qualidadeComboBox == null) {
			qualidadeComboBox = new JComboBox(getQualidadesString());
			qualidadeComboBox.setMinimumSize(new Dimension(1, 1));
			qualidadeComboBox.setPreferredSize(new Dimension(120, 25));
		}
		return qualidadeComboBox;
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
	 * @throws DataException 
	 */
	private JList getAssuntosList() {
		if (assuntosList == null) {
			assuntosList = new JList(getAssuntosString());
			assuntosList.setVisibleRowCount(6);
		}
		return assuntosList;
	}

	/**
	 * This method initializes botoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotoesPanel() {
		if (botoesPanel == null) {
			GridBagConstraints gridBagConstraints46 = new GridBagConstraints();
			gridBagConstraints46.gridx = 0;
			gridBagConstraints46.gridy = 0;
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.gridx = 2;
			gridBagConstraints27.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints27.gridy = 0;
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.gridx = 4;
			gridBagConstraints26.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints26.gridy = 0;
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			gridBagConstraints25.gridx = 1;
			gridBagConstraints25.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints25.gridy = 0;
			botoesPanel = new JPanel();
			botoesPanel.setLayout(new GridBagLayout());
			botoesPanel.add(getOkButton(), gridBagConstraints26);
			botoesPanel.add(getCancelarButton(), gridBagConstraints25);
			botoesPanel.add(getVoltarCamposButton(), gridBagConstraints27);
			botoesPanel.add(getSubstituirArquivoButton(), gridBagConstraints46);
		}
		return botoesPanel;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("OK");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					alterarMusica();
					//$hide<<$
				}
			});
			okButton.setFocusable(false);
		}
		return okButton;
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
					//$hide>>$
					cancelarOperacao();
					//$hide<<$
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
	public JButton getVoltarCamposButton() {
		if (voltarCamposButton == null) {
			voltarCamposButton = new JButton();
			voltarCamposButton.setText("Voltar Campos");
			voltarCamposButton.setFocusable(false);
			voltarCamposButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						//$hide>>$
						editarMusica(Fachada.getMusica(musica.getIdMusica()));
						//$hide<<$
					} catch (DataException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return voltarCamposButton;
	}
		/**
	 * This method initializes cantoresPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCantoresPanel() {
		if (cantoresPanel == null) {
			GridBagConstraints gridBagConstraints39 = new GridBagConstraints();
			gridBagConstraints39.gridx = 1;
			gridBagConstraints39.gridy = 0;
			spacer14Label = new JLabel();
			spacer14Label.setText("");
			spacer14Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerAltura99.PNG")));
			GridBagConstraints gridBagConstraints38 = new GridBagConstraints();
			gridBagConstraints38.fill = GridBagConstraints.BOTH;
			gridBagConstraints38.gridy = 0;
			gridBagConstraints38.weightx = 1.0;
			gridBagConstraints38.weighty = 1.0;
			gridBagConstraints38.gridx = 0;
			cantoresPanel = new JPanel();
			cantoresPanel.setLayout(new GridBagLayout());
			cantoresPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Cantores", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			cantoresPanel.add(getCantoresScrollPane(), gridBagConstraints38);
			cantoresPanel.add(spacer14Label, gridBagConstraints39);
		}
		return cantoresPanel;
	}

	/**
	 * This method initializes musicasPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMusicasPanel() {
		if (musicasPanel == null) {
			GridBagConstraints gridBagConstraints58 = new GridBagConstraints();
			gridBagConstraints58.gridx = 0;
			gridBagConstraints58.gridy = 0;
			spacer2Label = new JLabel();
			spacer2Label.setText("");
			spacer2Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerAltura22.png")));
			GridBagConstraints gridBagConstraints54 = new GridBagConstraints();
			gridBagConstraints54.gridx = 1;
			gridBagConstraints54.fill = GridBagConstraints.BOTH;
			gridBagConstraints54.gridy = 0;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 0;
			gridBagConstraints41.gridy = 1;
			spacer15Label = new JLabel();
			spacer15Label.setText("");
			spacer15Label.setIcon(new ImageIcon(getClass().getResource("/figuras/spacerAltura99.PNG")));
			GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.fill = GridBagConstraints.BOTH;
			gridBagConstraints40.gridy = 1;
			gridBagConstraints40.weightx = 1.0;
			gridBagConstraints40.weighty = 1.0;
			gridBagConstraints40.gridx = 1;
			musicasPanel = new JPanel();
			musicasPanel.setLayout(new GridBagLayout());
			musicasPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Músicas", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			musicasPanel.setPreferredSize(new java.awt.Dimension(600, 452));
			musicasPanel.add(getMusicasScrollPane(), gridBagConstraints40);
			musicasPanel.add(spacer15Label, gridBagConstraints41);
			musicasPanel.add(getBotoes1Panel(), gridBagConstraints54);
			musicasPanel.add(spacer2Label, gridBagConstraints58);
		}
		return musicasPanel;
	}

	/**
	 * This method initializes cantoresScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getCantoresScrollPane() {
		if (cantoresScrollPane == null) {
			cantoresScrollPane = new JScrollPane();
			cantoresScrollPane.setViewportView(getCantoresTable());
		}
		return cantoresScrollPane;
	}

	/**
	 * This method initializes cantoresTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getCantoresTable() {
		if (cantoresTable == null) {
			cantoresTable = new JTable();
			cantoresTable.setModel(getCantoresTableModel());
			cantoresTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						//$hide>>$
						showDialog(e);
						//$hide<<$
					}
				}

				private void showDialog(MouseEvent e) {
					if (cantores != null) {
						int row = cantoresTable.rowAtPoint(e.getPoint());
						if (row >= 0 && row < cantores.size() && cantores.get(row) != null) {
							getCantorTextField().setText(cantores.get(row).getNomeSemEspacos());
							//$hide>>$
							mudouNomeCantor();
							//$hide<<$
						}
					}
				}
			});
		}
		return cantoresTable;
	}

	/**
	 * This method initializes musicasScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getMusicasScrollPane() {
		if (musicasScrollPane == null) {
			musicasScrollPane = new JScrollPane();
			musicasScrollPane.setViewportView(getMusicasTable());
		}
		return musicasScrollPane;
	}

	/**
	 * This method initializes musicasTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getMusicasTable() {
		if (musicasTable == null) {
			musicasTable = new JTable();
			musicasTable.setModel(getMusicasTableModel());
		}
		
		musicasTable.getColumn(musicasTable.getColumnName(0)).setPreferredWidth(110);
		musicasTable.getColumn(musicasTable.getColumnName(1)).setPreferredWidth(15);
		
		return musicasTable;
	}
	
	/**
	 * This method initializes ritmoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRitmoPanel() {
		if (ritmoPanel == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints20.gridy = 0;
			gridBagConstraints20.gridx = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints8.gridy = 0;
			gridBagConstraints8.gridx = 0;
			GridBagConstraints gridBagConstraints43 = new GridBagConstraints();
			gridBagConstraints43.gridx = 1;
			gridBagConstraints43.fill = GridBagConstraints.NONE;
			gridBagConstraints43.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints43.insets = new Insets(0, 57, 0, 0);
			gridBagConstraints43.gridy = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.fill = GridBagConstraints.NONE;
			gridBagConstraints5.insets = new Insets(8, 0, 0, 0);
			gridBagConstraints5.gridx = 0;
			ritmoPanel = new JPanel();
			ritmoPanel.setLayout(new GridBagLayout());
			ritmoPanel.add(tipoLabel, gridBagConstraints5);
			ritmoPanel.add(getNovoRitmoButton(), gridBagConstraints43);
		}
		return ritmoPanel;
	}

	/**
	 * This method initializes novoRitmoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovoRitmoButton() {
		if (novoRitmoButton == null) {
			novoRitmoButton = new JButton();
			novoRitmoButton.setFocusable(false);
			novoRitmoButton.setText("Novo");
			novoRitmoButton.setPreferredSize(new Dimension(60, 16));
			novoRitmoButton.setFont(new Font("Dialog", Font.BOLD, 10));
			novoRitmoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					cadastrarTipo();
					//$hide<<$
				}
			});
		}
		return novoRitmoButton;
	}
	
	/**
	 * This method initializes nomeDaMusicaPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getNomeDaMusicaPanel() {
		if (nomeDaMusicaPanel == null) {
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints15.gridy = 0;
			gridBagConstraints15.gridx = 3;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints14.gridy = 0;
			gridBagConstraints14.ipadx = 0;
			gridBagConstraints14.gridx = 2;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.gridx = 0;
			nomeDaMusicaPanel = new JPanel();
			nomeDaMusicaPanel.setLayout(null);
			nomeDaMusicaPanel.add(nomeDaMusicaLabel, null);
			nomeDaMusicaPanel.add(getPlayButton(), null);
			nomeDaMusicaPanel.add(getStopButton(), null);
		}
		return nomeDaMusicaPanel;
	}

	/**
	 * This method initializes qualidadePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getQualidadePanel() {
		if (qualidadePanel == null) {
			GridBagConstraints gridBagConstraints49 = new GridBagConstraints();
			gridBagConstraints49.gridx = 1;
			gridBagConstraints49.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints49.fill = GridBagConstraints.NONE;
			gridBagConstraints49.insets = new Insets(0, 6, 0, 0);
			gridBagConstraints49.gridy = 0;
			qualidadePanel = new JPanel();
			qualidadePanel.setLayout(new GridBagLayout());
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints20.insets = new Insets(8, 0, 0, 0);
			gridBagConstraints20.fill = GridBagConstraints.NONE;
			qualidadePanel.add(qualidadeLabel, gridBagConstraints20);
		}
		return qualidadePanel;
	}

	/**
	 * This method initializes assuntosPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAssuntosPanel() {
		if (assuntosPanel == null) {
			GridBagConstraints gridBagConstraints52 = new GridBagConstraints();
			gridBagConstraints52.gridx = 1;
			gridBagConstraints52.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints52.insets = new Insets(0, 9, 0, 0);
			gridBagConstraints52.gridy = 0;
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints23.gridy = 0;
			gridBagConstraints23.insets = new Insets(8, 0, 0, 0);
			gridBagConstraints23.gridx = 0;
			assuntosPanel = new JPanel();
			assuntosPanel.setLayout(new GridBagLayout());
			assuntosPanel.add(assuntosLabel, gridBagConstraints23);
			assuntosPanel.add(getNovoAssuntoButton(), gridBagConstraints52);
		}
		return assuntosPanel;
	}

	/**
	 * This method initializes novoAssuntoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovoAssuntoButton() {
		if (novoAssuntoButton == null) {
			novoAssuntoButton = new JButton();
			novoAssuntoButton.setFocusable(false);
			novoAssuntoButton.setText("Novo");
			novoAssuntoButton.setPreferredSize(new Dimension(60, 18));
			novoAssuntoButton.setFont(new Font("Dialog", Font.BOLD, 10));
			novoAssuntoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					cadastrarAssunto();
					//$hide<<$
				}
			});
		}
		return novoAssuntoButton;
	}

	/**
	 * This method initializes limparSelecaoAssuntosButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLimparSelecaoAssuntosButton() {
		if (limparSelecaoAssuntosButton == null) {
			limparSelecaoAssuntosButton = new JButton();
			limparSelecaoAssuntosButton.setFocusable(false);
			limparSelecaoAssuntosButton.setText("Limpar Seleção");
			limparSelecaoAssuntosButton.setFont(new Font("Dialog", Font.BOLD, 10));
			limparSelecaoAssuntosButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							getAssuntosList().clearSelection();
						}
					});
		}
		return limparSelecaoAssuntosButton;
	}

	/**
	 * This method initializes diretorioTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDiretorioTextField() {
		if (diretorioTextField == null) {
			diretorioTextField = new JTextField();
			diretorioTextField.setColumns(20);
			diretorioTextField.setEditable(false);
		}
		return diretorioTextField;
	}

	/**
	 * This method initializes playButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPlayButton() {
		if (playButton == null) {
			playButton = new JButton();
			playButton.setFocusable(false);
			playButton.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/media_play_green.png")));
			playButton.setBounds(new Rectangle(198, 2, 26, 22));
			playButton.setMnemonic(KeyEvent.VK_UNDEFINED);
			playButton.setToolTipText("Toca a música que está selecionada para ser salva");
			playButton.setPreferredSize(new Dimension(26, 16));
			playButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					tocarArquivoEscolhido();
					//$hide<<$
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
			stopButton.setFocusable(false);
			stopButton.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/media_stop_red.png")));
			stopButton.setBounds(new Rectangle(224, 2, 26, 22));
			stopButton.setPreferredSize(new Dimension(26, 16));
			stopButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					stop();
					//$hide<<$
				}
			});
		}
		return stopButton;
	}

	/**
	 * This method initializes controle1Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getControle1Panel() {
		if (controle1Panel == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setVgap(0);
			controle1Panel = new JPanel();
			controle1Panel.setLayout(borderLayout);
			controle1Panel.setBackground(Color.lightGray);
		}
		return controle1Panel;
	}

	/**
	 * This method initializes botoes1Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotoes1Panel() {
		if (botoes1Panel == null) {
			botoes1Panel = new JPanel();
			botoes1Panel.setLayout(null);
			botoes1Panel.setBackground(new Color(238, 238, 238));
			botoes1Panel.add(getPlay1Button(), null);
			botoes1Panel.add(getStop1Button(), null);
			botoes1Panel.add(getControles1Panel(), null);
			botoes1Panel.add(getControlePanel(), null);
		}
		return botoes1Panel;
	}

	/**
	 * This method initializes play1Button	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPlay1Button() {
		if (play1Button == null) {
			play1Button = new JButton();
			play1Button.setFocusable(false);
			play1Button.setText("");
			play1Button.setPreferredSize(new Dimension(22, 22));
			play1Button.setBounds(new Rectangle(358, 0, 26, 22));
			play1Button.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/media_play_green.png")));
			play1Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					tocarArquivoDoCadastro();
					//$hide<<$
				}
			});
		}
		return play1Button;
	}
	
	/**
	 * This method initializes stop1Button	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStop1Button() {
		if (stop1Button == null) {
			stop1Button = new JButton();
			stop1Button.setFocusable(false);
			stop1Button.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/media_stop_red.png")));
			stop1Button.setBounds(new Rectangle(383, 0, 26, 22));
			stop1Button.setPreferredSize(new Dimension(26, 22));
			stop1Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					stop();
					//$hide<<$
				}
			});
		}
		return stop1Button;
	}

	/**
	 * This method initializes controles1Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getControles1Panel() {
		if (controles1Panel == null) {
			controles1Panel = new JPanel();
			controles1Panel.setLayout(new GridBagLayout());
			controles1Panel.setBounds(new Rectangle(0, 0, 0, 0));
		}
		return controles1Panel;
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
			controlePanel.setBounds(new Rectangle(0, 0, 359, 21));
			controlePanel.setBackground(Color.lightGray);
		}
		return controlePanel;
	}

	private void ativarCampos() {
		getNomeTextField().setEditable(true);
		getPlayButton().setEnabled(true);
		getStopButton().setEnabled(true);
		
		getNovoRitmoButton().setEnabled(true);
		getTipoComboBox().setEnabled(true);
		
		getCantorTextField().setEditable(true);
		getAlterarDuracaButton().setEnabled(true);
		getLetraTextArea().setEditable(true);
		
		getQualidadeComboBox().setEnabled(true);
		
		getObservacaoTextArea().setEditable(true);
		getNovoAssuntoButton().setEnabled(true);
		getAssuntosList().setEnabled(true);
		getLimparSelecaoAssuntosButton().setEnabled(true);
		
		getAnoTextField().setEnabled(true);
		
		getCapaLabel().setEnabled(true);
		getAlterarCapaButton().setEnabled(true);
		
		getPlay1Button().setEnabled(true);
		getStop1Button().setEnabled(true);
		getMusicasTable().setEnabled(true);
		
		getCantoresTable().setEnabled(true);
		
		getSubstituirArquivoButton().setEnabled(true);
		getCancelarButton().setEnabled(true);
		getVoltarCamposButton().setEnabled(true);
		getOkButton().setEnabled(true);
	}
	
	private void desativarCampos() {
		getNomeTextField().setEditable(false);
		getPlayButton().setEnabled(false);
		getStopButton().setEnabled(false);
		
		getNovoRitmoButton().setEnabled(false);
		getTipoComboBox().setEnabled(false);
		
		getCantorTextField().setEditable(false);
		getAlterarDuracaButton().setEnabled(false);
		getLetraTextArea().setEditable(false);
		
		getQualidadeComboBox().setEnabled(false);
		
		getObservacaoTextArea().setEditable(false);
		getNovoAssuntoButton().setEnabled(false);
		getAssuntosList().setEnabled(false);
		getLimparSelecaoAssuntosButton().setEnabled(false);
		
		getAnoTextField().setEnabled(false);
		
		getCapaLabel().setEnabled(false);
		getAlterarCapaButton().setEnabled(false);
		
		getPlay1Button().setEnabled(false);
		getStop1Button().setEnabled(false);
		getMusicasTable().setEnabled(false);
		
		getCantoresTable().setEnabled(false);
		
		getSubstituirArquivoButton().setEnabled(false);
		getCancelarButton().setEnabled(false);
		getVoltarCamposButton().setEnabled(false);
		getOkButton().setEnabled(false);
	}

	private void mudouNomeMusica() {
		//$hide>>$
		if (getNomeTextField().isEditable()) {
			if (monitoraNomeTextFieldThread == null) {
				this.monitoraNomeTextFieldThread = new MonitoraNomeTextFieldThread(this);
				this.monitoraNomeTextFieldThread.start();
			} else {
				monitoraNomeTextFieldThread.naoFinalizarAinda();
			}
		}			
		//$hide<<$
	}

	public void monitoraNomeTextFieldThreadFinalizou() {
		//$hide>>$
		monitoraNomeTextFieldThread = null;
		System.out.println("Mudou o nome da música!!!");
		if (musica != null) {
			musica.setNome(getNomeTextField().getText());
			atualizaNomeArquivoDaMusica();
			atualizarTabelaDeMusicas();
			
		}
		//$hide<<$
	}

	private void mudouNomeCantor() {
		//$hide>>$
		if (getCantorTextField().isEditable()) {
			if (monitoraCantorTextFieldThread == null) {
				this.monitoraCantorTextFieldThread = new MonitoraCantorTextFieldThread(this);
				this.monitoraCantorTextFieldThread.start();
			} else {
				monitoraCantorTextFieldThread.naoFinalizarAinda();
			}
		}			
		//$hide<<$
	}

	public void monitoraCantorTextFieldThreadFinalizou() {
		//$hide>>$
		monitoraCantorTextFieldThread = null;
		System.out.println("Mudou o nome do Cantor!!!");
		if (musica != null) {
			musica.setCantores(null);
			atualizarTabelaDeCantores();
			atualizaNomeArquivoDaMusica();
		}
		//$hide<<$
	}

	private void alterarMusica() {
		//$hide>>$
		if (musica == null) {
			return;
		}
		
		musica.setNome(getNomeTextField().getText());
		if (musica.getNome() == null || musica.getNome().equals("")) {
			JOptionPane.showMessageDialog(EditarMusicasPanel.this, "Por favor, indique um nome válido para a música.",
					"Nome da música inválido", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// verificando se o ano é válido
		if (getAnoTextField().getText() != null && !getAnoTextField().getText().trim().equals("") && !getAnoTextField().getText().matches("[0-9][0-9][0-9][0-9]")) {
			JOptionPane.showMessageDialog(this, "O Campo Ano está inválido. Por favor, preencha o Campo com 4 dígitos numéricos ou deixe-o em Branco.", "Campo Ano inválido", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Musica musicaAntiga = null;
		try {
			musicaAntiga = Fachada.getMusica(musica.getIdMusica());
		} catch (DataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (!musica.getNomeArquivo().equals(musicaAntiga.getNomeArquivo())) {
			String nomeArquivoAntigo = Util.getDiretorioBase() + File.separator + musicaAntiga.getDiretorio() +
				File.separator + musicaAntiga.getNomeArquivo();
			File arquivoAntigo = new File(nomeArquivoAntigo);
			File novoArquivo = new File(Util.getDiretorioBase() + File.separator + musica.getDiretorio() +
					File.separator + musica.getNomeArquivo());
			
			if (!arquivoAntigo.renameTo(novoArquivo)) {
				JOptionPane.showMessageDialog(this, "Não foi possível modificar o nome do arquivo. A operação será cancelada.",
						"Erro ao renomear arquivo.", JOptionPane.ERROR_MESSAGE);
				return;
			}
			/*// copiando para um arquivo temporário
			int i = 0;
			String temp = "temp" + i;
			String nomeArquivoAntigo = Util.getDiretorioBase() + File.separator + musicaAntiga.getDiretorio() +
				File.separator + musicaAntiga.getNomeArquivo();
			String nomeArquivoTemp = Util.getDiretorioBase() + File.separator + temp;
			
			while (!Util.copyFile(nomeArquivoAntigo, nomeArquivoTemp)) {
				File arquivoTemp = new File(nomeArquivoTemp);
				
				if (arquivoTemp.exists()) {
					i++;
					temp = "temp" + i;
					nomeArquivoTemp = Util.getDiretorioBase() + File.separator + temp;
				} else {
					File arquivoAntigo = new File(nomeArquivoAntigo);
					if (!arquivoAntigo.exists()) {
						JOptionPane.showMessageDialog(this, "Problemas com o arquivo da música, por favor, faça uma verificação dos arquivos (menu Sistema).",
								"Problemas com arquivos", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						JOptionPane.showMessageDialog(this, "Problema inesperado ao copiar arquivo da música.", "Problema inesperado", JOptionPane.ERROR_MESSAGE);
					}
				}				
			}
			
			// apagando o arquivo original
			File arquivoAntigo = new File(nomeArquivoAntigo);
			
			File novoArquivo = new File(Util.getDiretorioBase() + File.separator + musica.getDiretorio() +
					File.separator + musica.getNomeArquivo());
			File arquivoTemporario = new File(nomeArquivoTemp);

			if (musica.getNomeArquivo().equalsIgnoreCase(musicaAntiga.getNomeArquivo())) {
				if (!arquivoAntigo.delete()) {
					JOptionPane.showMessageDialog(this, "Não foi possível apagar o arquivo antigo da música. Cancelando a operação", "Impossível apagar arquivo", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// TODO arrumar um jeito de fazer um flush aqui
				while (arquivoAntigo.exists()) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if (!arquivoTemporario.renameTo(novoArquivo)) {
				JOptionPane.showMessageDialog(this, "Não foi possível copiar o novo arquivo da música. Cancelando operação.", "Erro ao copiar arquivo.", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (!musica.getNomeArquivo().equalsIgnoreCase(musicaAntiga.getNomeArquivo())) {
				if (!arquivoAntigo.delete()) {
					JOptionPane.showMessageDialog(this, "Não foi possível apagar o arquivo antigo da música. Por favor, apague manualmente.", "Impossível apagar arquivo", JOptionPane.ERROR_MESSAGE);
					// return;
				}
			}*/
			
		}
		
		// alterar a música
		try {
			// setando o tipo da música
			if (getTipoComboBox().getSelectedIndex() > 0) {
				musica.setTipo(tipos
						.get(getTipoComboBox().getSelectedIndex() - 1));
			} else {
				musica.setTipo(null);
			}

			// setando a letra da música
			if (getLetraTextArea().getText() != null
					&& !getLetraTextArea().getText().equals("")) {
				musica.setLetra(getLetraTextArea().getText());
			} else {
				musica.setLetra(null);
			}

			// setando a qualidade
			if (getQualidadeComboBox().getSelectedIndex() > 0) {
				musica.setQualidade(qualidades.get(getQualidadeComboBox()
						.getSelectedIndex() - 1));
			} else {
				musica.setQualidade(null);
			}

			// setando a observação
			if (getObservacaoTextArea().getText() != null
					&& !getObservacaoTextArea().getText().equals("")) {
				musica.setObservacao(getObservacaoTextArea().getText());
			} else {
				musica.setObservacao(null);
			}
			
			// setando o ano
			if (getAnoTextField().getText() != null && getAnoTextField().getText().trim().length() == 4) {
				musica.setAno(Integer.parseInt(getAnoTextField().getText()));
			}

			// setando os assuntos
			int indices[] = getAssuntosList().getSelectedIndices();
			if (indices.length > 0) {
				List<Assunto> lista = new ArrayList<Assunto>();
				for (int i : indices) {
					lista.add(assuntos.get(i));
					System.out.println("adicionado o assunto "
							+ assuntos.get(i).getAssunto());
				}
				musica.setAssuntos(lista);
			} else {
				musica.setAssuntos(null);
			}
			
			System.out.println(getCapaLabel().getIcon());
			
			Fachada.alterarMusica(musica);
			// salvar a imagem da capa do disco
			if (nomeArquivoCapaSelecionada != null) {
				Fachada.alterarCapaDiscoMusica(musica, nomeArquivoCapaSelecionada, caminhoImagemCapaSelecionada);	
			} else if (getCapaLabel().getIcon() == null && musica.getNomeArquivoCapa() != null) {
				Fachada.alterarCapaDiscoMusica(musica, null, null);
			}

			// testar se o cantor é um novo cantor ou se não tem cantor para
			// cadastrar (cantor desconhecido)
			if (musica.getCantores() == null
					|| musica.getCantores().size() == 0) {
				String nomeSemEspacos = getCantorTextField().getText().trim()
						.replaceAll(" ", "").toUpperCase();

				if (nomeSemEspacos != null && !nomeSemEspacos.equals("")) {
					// cadastrar o novo cantor
					Cantor cantor = new Cantor();
					cantor.setNomeSemEspacos(nomeSemEspacos);

					String nome = JOptionPane.showInputDialog(
							EditarMusicasPanel.this,
							"Por favor, indique o nome do Cantor ("
									+ cantor.getNomeSemEspacos() + ")",
							"Cantor", JOptionPane.QUESTION_MESSAGE);

					if (nome != null && !nome.equals("")) {
						cantor.setNome(nome);
					} else {
						cantor.setNome(cantor.getNomeSemEspacos());
					}

					Fachada.cadastrarCantor(cantor);
					Fachada.adicionarCantorAMusica(musica, cantor);
				}
			}
			
			new Thread() {
				public void run() {
					pai.musicaFoiEditada();					
				}
			}.start();
			
			JOptionPane.showMessageDialog(EditarMusicasPanel.this,
					"Música alterada com sucesso!", "Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Erro ao alterar a música no Banco de Dados", "Erro ao alterar Música", JOptionPane.ERROR_MESSAGE);
		}

		cancelarOperacao();
		//$hide<<$
	}

	public void atualizaNomeArquivoDaMusica() {
		//$hide>>$
		if (musica != null) {
			String nomeArquivoAntigo = "";
			
			try {
				Musica mTemp = Fachada.getMusica(musica.getIdMusica());
				if (mTemp != null) {
					nomeArquivoAntigo = mTemp.getNomeArquivo();	
				} else {
					cancelarOperacao();
					return;
				}
				
				System.out.println(nomeArquivoAntigo);
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			String nomeArquivo = null;
			
			// compondo o nome do arquivo final
			nomeArquivo = musica.getNome();
			if (musica.getCantores() != null && musica.getCantores().size() > 0) {
				nomeArquivo = nomeArquivo + " - "
						+ musica.getCantores().get(0).getNomeSemEspacos();
			}
			
			nomeArquivo += ".mp3";
			
			// testando se já existe um arquivo com o mesmo nome
			File arquivo = new File(Util.getDiretorioBase() + File.separator + musica.getDiretorio() + File.separator + nomeArquivo);
			int i = 0;
			while (arquivo.exists() && !arquivo.getName().equalsIgnoreCase(nomeArquivoAntigo)) {
				i++;
				nomeArquivo = musica.getNome() + " #" + i;
				if (musica.getCantores() != null && musica.getCantores().size() > 0) {
					nomeArquivo = nomeArquivo + " - "
							+ musica.getCantores().get(0).getNomeSemEspacos();
				}
				nomeArquivo += ".mp3";
				
				arquivo = new File(Util.getDiretorioBase() + File.separator + musica.getDiretorio() + File.separator + nomeArquivo);
			}
			
			musica.setNomeArquivo(nomeArquivo);
			
			if ((musica.getCantores() == null || musica.getCantores().size() == 0)
					&& (getCantorTextField().getText() != null && !getCantorTextField().getText().equals(""))) {
				musica.setNomeArquivo(musica.getNome() + " - " + getCantorTextField().getText().trim()
						.replaceAll(" ", "").toUpperCase() + ".mp3");
				
				// testando se já existe um arquivo com o mesmo nome
				arquivo = new File(Util.getDiretorioBase() + File.separator + musica.getDiretorio() + 
						File.separator + musica.getNomeArquivo());
				i = 0;
				while (arquivo.exists() && !arquivo.getName().equalsIgnoreCase(nomeArquivoAntigo)) {
					i++;
					
					musica.setNomeArquivo(musica.getNome() + " #" + i + " - " + getCantorTextField().getText().trim()
							.replaceAll(" ", "").toUpperCase() + ".mp3");
					
					arquivo = new File(Util.getDiretorioBase() + File.separator + musica.getDiretorio() + 
							File.separator + musica.getNomeArquivo());
				}
			}			
			
			getNomeArquivoTextField().setText(musica.getNomeArquivo());
		}			
		//$hide<<$
	}

	private void atualizarCantores() {
		//$hide>>$
		if (musica == null) return;
		
		// procurando se já existe o cantor cadastrado, se já exixtir, setar a
		// lista de cantores da música
		// usando o vetor de cantores diretamente, para não precisar fazer nova
		// pesquisa no BD
		if (getCantorTextField().getText() != null && !getCantorTextField().getText().equals("") && this.cantores != null
				&& this.cantores.size() > 0) {
			for (Cantor c : this.cantores) {
				if (c.getNomeSemEspacos()
						.equals(getCantorTextField().getText().trim().replaceAll(" ", "").toUpperCase())) {
					List<Cantor> lista = new ArrayList<Cantor>();
					lista.add(c);
	
					musica.setCantores(lista);
				}
			}
		} else {
			musica.setCantores(null);
		}
		//$hide<<$
	}

	private void cancelarOperacao() {
		//$hide>>$
		musica = null;
		
		apagarCapaTemporaria();
		nomeArquivoCapaSelecionada = null;
		caminhoImagemCapaSelecionada = null;
		
		limparCampos();
		desativarCampos();
		//$hide<<$
	}

	private void limparCampos() {
		//$hide>>$
		stop();
		getNomeTextField().setText("");
		getNomeTextField().setEditable(false);
		getTipoComboBox().setSelectedIndex(0);
		getCantorTextField().setText("");
		getCantorTextField().setEditable(false);
		getDuracaoTextField().setText("");
		getLetraTextArea().setText("");
		getDiretorioTextField().setText("");
		getQualidadeComboBox().setSelectedIndex(0);
		getNomeArquivoTextField().setText("");
		getObservacaoTextArea().setText("");
		getAssuntosList().clearSelection();
		getMusicasTableModel().getDataVector().clear();
		getMusicasTable().repaint();
		getCantoresTableModel().getDataVector().clear();
		getCantoresTable().repaint();
		getAnoTextField().setText("");
		
		getCapaLabel().setIcon(null);
		getCapaLabel().setText("Clique para Selecionar");
		//$hide<<$
	}

	private Vector<String> getCantoresNomesCampos() {
		if (cantoresNomesCampos == null) {
			cantoresNomesCampos = new Vector<String>();
			
			cantoresNomesCampos.add("Nome Sem Espaços");
			cantoresNomesCampos.add("Nome");
		}
		return cantoresNomesCampos;
	}

	private Vector<Vector<String>> getCantoresDados() {
		cantoresDados = new Vector<Vector<String>>();
		//$hide>>$
	
		for (Cantor c: getCantores()) {
			Vector<String> temp = new Vector<String>();
	
			temp.add(c.getNomeSemEspacos());
			temp.add(c.getNome());
			
			cantoresDados.add(temp);
		}			
		//$hide<<$
		return cantoresDados;
	}

	private List<Cantor> getCantores() {
		//$hide>>$
		try {
			String nomeSemEspacos = "";
			
			if (musica != null && musica.getCantores() != null && musica.getCantores().size() > 0) {
				nomeSemEspacos = musica.getCantores().get(0).getNomeSemEspacos();
			} else {
				if (getCantorTextField().getText() != null && !getCantorTextField().getText().equals("")) {
					nomeSemEspacos = getCantorTextField().getText().trim().replaceAll(" ", "").toUpperCase();
				}
			}
			
			if (musica != null) {
				cantores = Fachada.listarCantoresPorDiversos(null, nomeSemEspacos, musica.getTipoArquivo());	
			} else {
				cantores = Fachada.listarCantoresPorDiversos(null, nomeSemEspacos, Constantes.TIPO_ARQUIVO_NAO_LISTAR);
			}
			
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
		return cantores;
	}

	private DefaultTableModel getCantoresTableModel() {
		//$hide>>$
		if (cantoresTableModel == null) {
			cantoresTableModel = new DefaultTableModel() {
				private static final long serialVersionUID = 2014828586714349050L;
	
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			cantoresTableModel.setDataVector(getCantoresDados(), getCantoresNomesCampos());
		}
		//$hide<<$
		return cantoresTableModel;
	}

	private Vector<String> getMusicasNomesCampos() {
		if (musicasNomesCampos == null) {
			musicasNomesCampos = new Vector<String>();
			
			musicasNomesCampos.add("Nome");
			musicasNomesCampos.add("Duração");
			if (tipoArquivo == Constantes.TIPO_ARQUIVO_MENSAGEM) {
				musicasNomesCampos.add("Intérprete");
			} else if (tipoArquivo == Constantes.TIPO_ARQUIVO_MUSICA_INSTRUMENTAL) {
				musicasNomesCampos.add("Intérprete");
			} else if (tipoArquivo == Constantes.TIPO_ARQUIVO_MUSICA_CANTADA) {
				musicasNomesCampos.add("Cantor");
			}
		}
		
		return musicasNomesCampos;
	}

	private Vector<Vector<String>> getMusicasDados() {
		musicasDados = new Vector<Vector<String>>();
		
		//$hide>>$
		for (Musica m: getMusicas()) {
			Vector<String> temp = new Vector<String>();
	
			temp.add(m.getNome());
			temp.add(Util.formataDuracao(m.getDuracao()));
			if (m.getCantores().size() >= 1) {
				for (Cantor c: m.getCantores()) {
					temp.add(c.getNome());
				}
			} else {
				temp.add("");
			}
			
			musicasDados.add(temp);
		}			
		//$hide<<$
		
		return musicasDados;
	}

	private List<Musica> getMusicas() {
		//$hide>>$
		try {
			String nome = "";
			
			if (musica != null) {
				nome = musica.getNome();
			}
			
			if (musica != null) {
				musicas = Fachada.listarMusicasPorDiversos(nome, null, null, null, null, null, null, null, 0, musica.getTipoArquivo());	
			} else {
				musicas = Fachada.listarMusicasPorDiversos(nome, null, null, null, null, null, null, null, 0, Constantes.TIPO_ARQUIVO_NAO_LISTAR);
			}
			
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
		return musicas;
	}

	private DefaultTableModel getMusicasTableModel() {
		if (musicasTableModel == null) {
			musicasTableModel = new DefaultTableModel() {
				private static final long serialVersionUID = 2014828586714349050L;
	
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			//$hide>>$
			musicasTableModel.setDataVector(getMusicasDados(), getMusicasNomesCampos());
			//$hide<<$
		}
		return musicasTableModel;
	}

	private void cadastrarTipo() {
		//$hide>>$
		String tipo = JOptionPane.showInputDialog(EditarMusicasPanel.this, "Por favor, indique o Ritmo a ser Cadastrado",
				"Indique o Ritmo", JOptionPane.QUESTION_MESSAGE);
		
		if (tipo != null && !tipo.equals("")) {
			Tipo t = new Tipo();
			t.setTipo(tipo);
			t.setTipoArquivo(tipoArquivo);
			
			try {
				Fachada.cadastrarTipo(t);
				getTipoComboBox().removeAllItems();
				for (String s: getTiposString()) {
					getTipoComboBox().addItem(s);
				}
				
				pai.novoRitmoCadastrado();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//$hide<<$
	}

	private void cadastrarQualidade() {
		//$hide>>$
		String qualidade = JOptionPane.showInputDialog(EditarMusicasPanel.this, "Por favor, indique a Qualidade a ser Cadastrada",
				"Indique a Qualidade", JOptionPane.QUESTION_MESSAGE);
		
		if (qualidade != null && !qualidade.equals("")) {
			Qualidade q = new Qualidade();
			q.setQualidade(qualidade);
			
			try {
				Fachada.cadastrarQualidade(q);
				getQualidadeComboBox().removeAllItems();
				for (String s: getQualidadesString()) {
					getQualidadeComboBox().addItem(s);
				}
				
				pai.novaQualidadeCadastrada();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//$hide<<$
	}

	private void cadastrarAssunto() {
		//$hide>>$
		String assunto = JOptionPane.showInputDialog(EditarMusicasPanel.this, "Por favor, indique o Assunto a ser Cadastrado",
				"Indique o Assunto", JOptionPane.QUESTION_MESSAGE);
		
		if (assunto != null && !assunto.equals("")) {
			Assunto a = new Assunto();
			a.setAssunto(assunto);
			a.setTipoArquivo(tipoArquivo);
			
			try {
				Fachada.cadastrarAssunto(a);
				getAssuntosList().setListData(getAssuntosString());
				pai.novoAssuntoCadastrado();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//$hide<<$
	}

	private List<Assunto> getAssuntos() {
		assuntos = new ArrayList<Assunto>();
		//$hide>>$
		try {
			if (musica != null) {
				assuntos = Fachada.listarAssuntos(musica.getTipoArquivo());	
			} else {
				assuntos = Fachada.listarAssuntos(Constantes.TIPO_ARQUIVO_NAO_LISTAR);
			}
			
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
		return assuntos;
	}

	private List<Qualidade> getQualidades() {
		qualidades = new ArrayList<Qualidade>();
		//$hide>>$
		try {
			qualidades = Fachada.listarQualidades();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
		return qualidades;
	}

	private List<Tipo> getTipos() {
		tipos = new ArrayList<Tipo>();
		//$hide>>$
		try {
			if (musica != null) {
				tipos = Fachada.listarTipos(musica.getTipoArquivo());	
			} else {
				tipos = Fachada.listarTipos(Constantes.TIPO_ARQUIVO_NAO_LISTAR);
			}
			
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
		return tipos;
	}

	private Vector<String> getAssuntosString() {
		assuntosString = new Vector<String>();
		//$hide>>$
		// assuntosString.add("");
		
		for (Assunto a: getAssuntos()) {
			assuntosString.add(a.getAssunto());
		}
		//$hide<<$
		return assuntosString;
	}

	private Vector<String> getQualidadesString() {
		qualidadesString = new Vector<String>();
		//$hide>>$
		qualidadesString.add("");
		
		for (Qualidade q: getQualidades()) {
			qualidadesString.add(q.getQualidade());
		}
		//$hide<<$
		return qualidadesString;
	}

	private Vector<String> getTiposString() {
		tiposString = new Vector<String>();
		//$hide>>$	
		tiposString.add("");
		
		for (Tipo t: getTipos()) {
			tiposString.add(t.getTipo());
		}
		//$hide<<$
		return tiposString;
	}

	
	private void tocarArquivoEscolhido() {
		//$hide>>$
		if (musica != null) {
			try {
				Musica m = Fachada.getMusica(musica.getIdMusica());
				GlobalPlayer.play(Util.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + m.getNomeArquivo());
				getControle1Panel().add(GlobalPlayer.getControle(), BorderLayout.SOUTH);
				validate();	
				GlobalPlayer.getControle().repaint();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		//$hide<<$
	}

	private void stop() {
		//$hide>>$
		GlobalPlayer.stop();
		//$hide<<$
	}

	private void tocarArquivoDoCadastro() {
		//$hide>>$
		if (musicas != null && musicas.size() > 0 && getMusicasTable().getSelectedRow() != -1) {		
			Musica m = musicas.get(getMusicasTable().getSelectedRow());
			String nomeArquivo = Util.getDiretorioBase() + File.separator + m.getDiretorio() +
				File.separator + m.getNomeArquivo();
			
			GlobalPlayer.play(nomeArquivo);
			getControlePanel().add(GlobalPlayer.getControle(), BorderLayout.SOUTH);				
			validate();	
			GlobalPlayer.getControle().repaint();
		}
		//$hide<<$
	}

	public void editarMusica(Musica musica) {
		//$hide>>$
		limparCampos();
		ativarCampos();
		
		this.musica = (Musica) musica.clone();
		
		switch (musica.getTipoArquivo()) {
		case Constantes.TIPO_ARQUIVO_MUSICA_CANTADA:
			configurarCadastroMusicasCantadas();
			break;
		case Constantes.TIPO_ARQUIVO_MUSICA_INSTRUMENTAL:
			configurarCadastroMusicasInstrumentais();
			break;
		case Constantes.TIPO_ARQUIVO_MENSAGEM:
			configurarCadastroMensagens();
			break;
		default:
			JOptionPane.showMessageDialog(this, "Tipo de Arquivo inválido.", "Tipo de Arquivo inválido", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		getNomeTextField().setText(musica.getNome());
		
		getTipoComboBox().setSelectedIndex(0);
		if (musica.getTipo() != null) {
			getTipoComboBox().setSelectedItem(musica.getTipo().getTipo());
		}
		
		if (musica.getCantores() != null && musica.getCantores().size() > 0) {
			getCantorTextField().setText(musica.getCantores().get(0).getNomeSemEspacos());
		}
		
		if (musica.getDuracao() > 0) {
			getDuracaoTextField().setText(Util.formataDuracao(musica.getDuracao()));
		} else {
			getDuracaoTextField().setText("");
		}
		
		if (musica.getLetra() != null) {
			getLetraTextArea().setText(musica.getLetra());
		} else {
			getLetraTextArea().setText("");
		}
		
		getDiretorioTextField().setText(musica.getDiretorio());
		
		getQualidadeComboBox().setSelectedItem(0);
		if (musica.getQualidade() != null) {
			getQualidadeComboBox().setSelectedItem(musica.getQualidade().getQualidade());
		}
		
		getNomeArquivoTextField().setText(musica.getNomeArquivo());
		
		if (musica.getObservacao() != null) {
			getObservacaoTextArea().setText(musica.getObservacao());
		} else {
			getObservacaoTextArea().setText("");
		}
		
		if (musica.getAno() > 1900 && musica.getAno() <= 3000) {
			getAnoTextField().setText("" + musica.getAno());
		}
		
		getAssuntosList().clearSelection();
		if (musica.getAssuntos() != null && musica.getAssuntos().size() > 0) {
			int indices[] = new int[musica.getAssuntos().size()];			
			
			// TODO ver uma forma melhor de fazer isso
			for (int i = 0; i < musica.getAssuntos().size(); i++) {
				getAssuntosList().setSelectedValue(musica.getAssuntos().get(i).getAssunto(), true);
				indices[i] = getAssuntosList().getSelectedIndex();
			}
			
			getAssuntosList().setSelectedIndices(indices);
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
		
		atualizarTabelaDeMusicas();
		atualizarTabelaDeCantores();
		//$hide<<$
	}
	
	public void setPai(MusicasInternalFrame pai) {
		//$hide>>$
		this.pai = pai;
		//$hide<<$
	}
	
	public void novoTipoCadastrado() {
		//$hide>>$
		String tipo = (String) getTipoComboBox().getSelectedItem();
		
		getTipoComboBox().removeAllItems();
		for (String s: getTiposString()) {
			getTipoComboBox().addItem(s);
		}
		
		getTipoComboBox().setSelectedItem(tipo);
		//$hide<<$
	}
	
	public void novaQualidadeCadastrada() {
		//$hide>>$
		String qualidade = (String) getQualidadeComboBox().getSelectedItem();
		
		getQualidadeComboBox().removeAllItems();
		for (String s: getQualidadesString()) {
			getQualidadeComboBox().addItem(s);
		}
		
		getQualidadeComboBox().setSelectedItem(qualidade);
		//$hide<<$
	}
	
	public void novoAssuntoCadastrado() {
		//$hide>>$
		Object assuntos[] = getAssuntosList().getSelectedValues();
		
		getAssuntosList().setListData(getAssuntosString());		
		getAssuntosList().clearSelection();
		
		if (assuntos != null && assuntos.length > 0) {
			int indices[] = new int[assuntos.length];			
			
			// TODO ver uma forma melhor de fazer isso
			for (int i = 0; i < assuntos.length; i++) {
				getAssuntosList().setSelectedValue(assuntos[i], true);
				indices[i] = getAssuntosList().getSelectedIndex();
			}
			
			getAssuntosList().setSelectedIndices(indices);
		}//$hide<<$
	}

	/**
	 * This method initializes substituirArquivoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubstituirArquivoButton() {
		if (substituirArquivoButton == null) {
			substituirArquivoButton = new JButton();
			substituirArquivoButton.setText("Substituir Arquivo");
			substituirArquivoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					substituirArquivo();
					//$hide<<$
				}
			});
			substituirArquivoButton.setFocusable(false);
		}
		return substituirArquivoButton;
	}
	
	public void atualizarTabelaDeMusicas() {
		//$hide>>$
		getMusicasTableModel().setDataVector(getMusicasDados(),
				getMusicasNomesCampos());
		getMusicasTable();
		//$hide<<$
	}
	
	public void atualizarTabelaDeCantores() {
		//$hide>>$
		getCantoresTableModel().setDataVector(getCantoresDados(),
			 	getCantoresNomesCampos());
		
		atualizarCantores();
		//$hide<<$
	}
	
	private void substituirArquivo() {
		//$hide>>$
		Musica m;
		if (musica != null) {
			try {
				m = Fachada.getMusica(musica.getIdMusica());
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		} else {
			return;
		}
		
		JFileChooser chooser = new JFileChooser();
		if (Util.diretorioPassado != null) {
			chooser.setCurrentDirectory(new File(Util.diretorioPassado));
		} else if (Util.getDiretorioBase() != null) {
			chooser.setCurrentDirectory(new File(Util.getDiretorioBase()));
		}
		
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);				
		
		int opcao = chooser.showOpenDialog(EditarMusicasPanel.this);
		
		if (opcao == JFileChooser.APPROVE_OPTION) {
			File arquivo = chooser.getSelectedFile();
			
			try {
				File tempFile = File.createTempFile("colec", ".mp3");
				
				String nomeArquivoAntigo = Util.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + m.getNomeArquivo();
				
				if (nomeArquivoAntigo.equals(arquivo.getPath())) {
					JOptionPane.showMessageDialog(EditarMusicasPanel.this, "O Arquivo escolhido é o mesmo que já está na coleção.",
							"Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (Util.copyFile(nomeArquivoAntigo, tempFile.getPath())) {
					File arquivoAntigo = new File(nomeArquivoAntigo);
					if (arquivoAntigo.delete()) {
						if (Util.copyFile(arquivo.getPath(), nomeArquivoAntigo)) {
							m.setDuracao(Util.getMP3Duration(nomeArquivoAntigo));
							
							Fachada.alterarMusica(m);
							Fachada.dispararAlteracaoArquivoMusica(m);
							
							pai.musicaFoiEditada();
							
							musica.setDuracao(m.getDuracao());
							getDuracaoTextField().setText(Util.formataDuracao(musica.getDuracao()));
							
							JOptionPane.showMessageDialog(EditarMusicasPanel.this, "Arquivo Substituído com sucesso.\n\n" +
									"A duração da música foi alterada para " + Util.formataDuracao(m.getDuracao()) +
									", conforme a duração do novo Arquivo.\n" +
									"Se houver alguma observação relativa ao arquivo antigo, lembre-se de alterar\no campo " +
									"para estar de acordo com o novo.", "Arquivo Substituído", JOptionPane.INFORMATION_MESSAGE);
						} else {
							if (Util.copyFile(tempFile.getPath(), nomeArquivoAntigo)) {
								System.out.println("Operação restaurada");;
							}
						}
					}					
					
					tempFile.delete();
				} else {
					System.out.println("Erro ao copiar o arquivo temporário");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//$hide<<$
	}

	/**
	 * This method initializes duracaoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDuracaoPanel() {
		if (duracaoPanel == null) {
			GridBagConstraints gridBagConstraints56 = new GridBagConstraints();
			gridBagConstraints56.gridx = 1;
			gridBagConstraints56.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints56.fill = GridBagConstraints.NONE;
			gridBagConstraints56.insets = new Insets(0, 35, 0, 0);
			gridBagConstraints56.gridy = 0;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints10.gridy = 0;
			gridBagConstraints10.insets = new Insets(8, 0, 0, 0);
			gridBagConstraints10.gridx = 0;
			duracaoPanel = new JPanel();
			duracaoPanel.setLayout(new GridBagLayout());
			duracaoPanel.add(duracaoLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(8, 0, 0, 0), 0, 0));
			duracaoPanel.add(getAlterarDuracaButton(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0, 8, 0, 0), 0, 0));
		}
		return duracaoPanel;
	}

	/**
	 * This method initializes alterarDuracaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAlterarDuracaButton() {
		if (alterarDuracaButton == null) {
			alterarDuracaButton = new JButton();
			alterarDuracaButton.setText("Alterar");
			alterarDuracaButton.setFont(new Font("Dialog", Font.BOLD, 10));
			alterarDuracaButton.setPreferredSize(new java.awt.Dimension(50, 16));
			alterarDuracaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					alterarDuracao();
					//$hide<<$
				}
			});
			alterarDuracaButton.setFocusable(false);
			alterarDuracaButton.setMargin(new java.awt.Insets(2, 4, 2, 4));
			alterarDuracaButton.setMaximumSize(new java.awt.Dimension(50, 16));
			alterarDuracaButton.setMinimumSize(new java.awt.Dimension(50, 16));
		}
		return alterarDuracaButton;
	}
	
	private void alterarDuracao() {
		//$hide>>$
		if (musica == null) return;
		
		String duracaoString = JOptionPane.showInputDialog(EditarMusicasPanel.this, "Digite a nova duração (no formato \"00:00\")",
				"Alterar Duração", JOptionPane.QUESTION_MESSAGE);
		
		if (duracaoString != null) {
			if (Pattern.matches("[0-9]{1,2}:[0-9]{2}", duracaoString)) {
				String segundos = duracaoString.substring(duracaoString.length() - 2);
				String minutos = duracaoString.substring(0, duracaoString.length() - 3);
				
				int duracao = Integer.parseInt(segundos);
				duracao += Integer.parseInt(minutos) * 60;
				
				musica.setDuracao(duracao);
				getDuracaoTextField().setText(Util.formataDuracao(duracao));
			}
		}
		//$hide<<$
	}
	
	private JLabel getCapaLabel() {
		if(capaLabel == null) {
			capaLabel = new JLabel();
			capaLabel.setText("Clique para Selecionar");
			capaLabel.setHorizontalAlignment(SwingConstants.CENTER);
			capaLabel.setIcon(null);
			capaLabel.setIconTextGap(0);
			capaLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0,0,0)));
			capaLabel.setEnabled(false);
			capaLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					//$hide>>$
					if (!capaLabel.isEnabled()) return;
					
					if (capaLabel.getIcon() == null) {
						// escolhendo o arquivo de imagem
						alterarCapaDisco();	
					} else {
						// visualizar em tamanho grande
						try {
							BufferedImage bi;
							if (caminhoImagemCapaSelecionada != null) {
								bi = ImageIO.read(new File(caminhoImagemCapaSelecionada));	
							} else {
								try {
									bi = ImageIO.read(Fachada.getCapaDiscoMusica(musica));
								} catch (DataException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									return;
								}
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
							JOptionPane.showMessageDialog(EditarMusicasPanel.this, null, 
								"Capa Selecionada", JOptionPane.PLAIN_MESSAGE, new ImageIcon(imagem));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//$hide<<$
				}
			});
		}
		return capaLabel;
	}
	
	private void alterarCapaDisco() {
		//$hide>>$
		if (nomeArquivoCapaSelecionada == null && getCapaLabel().getIcon() == null) {
			escolherNovaCapa();
		} else {
			Object[] options = {"Remover a Imagem",
                    "Alterar a Imagem",
                    "Cancelar"};
			int n = JOptionPane.showOptionDialog(EditarMusicasPanel.this, "O que você deseja Fazer?", "Alterar Capa do Disco",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			
			if (n == JOptionPane.YES_OPTION) {
				// remover a imagem
				apagarCapaTemporaria();
				nomeArquivoCapaSelecionada = null;
				caminhoImagemCapaSelecionada = null;
				getCapaLabel().setIcon(null);
				getCapaLabel().setText("Clique para Selecionar");
			} else if (n == JOptionPane.NO_OPTION) {
				escolherNovaCapa();
			}
		}
		//$hide<<$
	}
	
	private void escolherNovaCapa() {
		//$hide>>$
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);					
		chooser.setFileFilter(new FileNameExtensionFilter("Arquivos de Imagem", "jpg", "bmp", "jpeg", "wbmp", "png", "gif"));
		int opcao = chooser.showOpenDialog(EditarMusicasPanel.this);
		
		if (opcao == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				apagarCapaTemporaria();
				caminhoImagemCapaSelecionada = Util.salvarArquivoDiretorioTemporario(file);
				nomeArquivoCapaSelecionada = file.getName();
				BufferedImage image = ImageIO.read(file);
				int side = capaLabel.getWidth() < capaLabel.getHeight() ? capaLabel.getWidth() : capaLabel.getHeight();
				Image scaledImage = image.getScaledInstance(side, side, Image.SCALE_SMOOTH);
				capaLabel.setText("");
				capaLabel.setIcon(new ImageIcon(scaledImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//$hide<<$
	}
	
	private void apagarCapaTemporaria() {
		//$hide>>$
		if (caminhoImagemCapaSelecionada != null) {
			File arquivo = new File(caminhoImagemCapaSelecionada);
			arquivo.delete();
		}
		//$hide<<$
	}
	
	private JPanel getCapaControlePanel() {
		if(capaControlePanel == null) {
			capaControlePanel = new JPanel();
			capaControlePanel.setLayout(null);
			capaControlePanel.add(getCapaLabelLabel());
			capaControlePanel.add(getAlterarCapaButton());
		}
		return capaControlePanel;
	}
	
	private JLabel getCapaLabelLabel() {
		if(capaLabelLabel == null) {
			capaLabelLabel = new JLabel();
			capaLabelLabel.setText("Capa do Disco");
			capaLabelLabel.setBounds(1, 0, 68, 14);
		}
		return capaLabelLabel;
	}
	
	private JButton getAlterarCapaButton() {
		if(alterarCapaButton == null) {
			alterarCapaButton = new JButton();
			alterarCapaButton.setText("Alterar");
			alterarCapaButton.setFont(new Font("Dialog", Font.BOLD, 10));
			alterarCapaButton.setBounds(77, 0, 65, 15);
			alterarCapaButton.setMargin(new java.awt.Insets(0, 4, 0, 4));
			alterarCapaButton.setFont(new java.awt.Font("Dialog",1,10));
			alterarCapaButton.setFocusable(false);
			alterarCapaButton.setSize(50, 15);
			alterarCapaButton.setPreferredSize(new java.awt.Dimension(50, 15));
			alterarCapaButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					//$hide>>$
					alterarCapaDisco();
					//$hide<<$
				}
			});
			alterarCapaButton.setEnabled(false);
		}
		return alterarCapaButton;
	}
	
	private JTextField getAnoTextField() {
		if(anoTextField == null) {
			anoTextField = new JTextField();
		}
		return anoTextField;
	}
	
	private JLabel getAnoLabel() {
		if(anoLabel == null) {
			anoLabel = new JLabel();
			anoLabel.setText("Ano");
		}
		return anoLabel;
	}
	
	private void configurarCadastroMusicasCantadas() {
		//$hide>>$
		tipoArquivo = Constantes.TIPO_ARQUIVO_MUSICA_CANTADA;
		
		if (nomeDaMusicaLabel != null) nomeDaMusicaLabel.setText("Nome");
		if (cantorLabel != null) cantorLabel.setText("Cantor (Nome Sem Espaços)");
		if (tipoLabel != null) {
			tipoLabel.setText("Ritmo");
			tipoLabel.setEnabled(true);
		}
		getNovoAssuntoButton().setEnabled(true);
		getNovoRitmoButton().setEnabled(true);
		getTipoComboBox().setEnabled(true);
		if (letraLabel != null) letraLabel.setText("Letra");
		if (assuntosLabel != null) assuntosLabel.setText("Assuntos");
		TitledBorder tb = (TitledBorder) getCantoresPanel().getBorder();
		tb.setTitle("Cantores");
		getCantoresPanel().repaint();
		TitledBorder tbm = (TitledBorder) getMusicasPanel().getBorder();
		tbm.setTitle("Músicas");
		getMusicasPanel().repaint();
		if (letraLabel != null) letraLabel.setEnabled(true);
		getLetraTextArea().setEnabled(true);
		getLetraTextArea().setEditable(true);
		getLetraTextArea().setBackground(Color.white);
		if (letraLabel != null) letraLabel.setText("Letra");
		
		if (assuntosLabel != null) assuntosLabel.setEnabled(true);
		if (novoAssuntoButton != null) novoAssuntoButton.setEnabled(true);
		getAssuntosList().setEnabled(true);
		getAssuntosList().setBackground(Color.white);
		
		getLimparSelecaoAssuntosButton().setEnabled(true);
		
		// atualizando as listas de Ritmos e de Assuntos
		getAssuntosList().setListData(getAssuntosString());
		getTipoComboBox().removeAllItems();
		for (String s: getTiposString()) {
			getTipoComboBox().addItem(s);
		}
		
		musicasNomesCampos = null;
		
		atualizarTabelaDeMusicas();
		atualizarTabelaDeCantores();
		//$hide<<$
	}
	
	private void configurarCadastroMusicasInstrumentais() {
		//$hide>>$
		tipoArquivo = Constantes.TIPO_ARQUIVO_MUSICA_INSTRUMENTAL;
		
		nomeDaMusicaLabel.setText("Nome");
		cantorLabel.setText("Intérprete (Nome Sem Espaços)");
		tipoLabel.setText("Estilo");
		tipoLabel.setEnabled(true);
		getNovoAssuntoButton().setEnabled(true);
		getNovoRitmoButton().setEnabled(true);
		getTipoComboBox().setEnabled(true);
		anoLabel.setText("Ano");
		assuntosLabel.setText("Instrumentos");
		TitledBorder tb = (TitledBorder) getCantoresPanel().getBorder();
		tb.setTitle("Intérpretes");
		getCantoresPanel().repaint();
		TitledBorder tbm = (TitledBorder) getMusicasPanel().getBorder();
		tbm.setTitle("Músicas");
		getMusicasPanel().repaint();
		letraLabel.setEnabled(false);
		getLetraTextArea().setEnabled(false);
		getLetraTextArea().setEditable(false);
		getLetraTextArea().setBackground(new Color(238, 238, 238));
		// letraLabel.setText("Texto");
		
		assuntosLabel.setEnabled(true);
		novoAssuntoButton.setEnabled(true);
		getAssuntosList().setEnabled(true);
		getAssuntosList().setBackground(Color.white);
		getLimparSelecaoAssuntosButton().setEnabled(true);
		
		// atualizando as listas de Ritmos e de Assuntos
		getAssuntosList().setListData(getAssuntosString());
		getTipoComboBox().removeAllItems();
		for (String s: getTiposString()) {
			getTipoComboBox().addItem(s);
		}
		
		musicasNomesCampos = null;
		
		atualizarTabelaDeMusicas();
		atualizarTabelaDeCantores();
		//$hide<<$
	}
	
	private void configurarCadastroMensagens() {
		//$hide>>$
		tipoArquivo = Constantes.TIPO_ARQUIVO_MENSAGEM;
		
		nomeDaMusicaLabel.setText("Nome");
		cantorLabel.setText("Intérprete (Nome Sem Espaços)");
		// tipoLabel.setText("Estilo");
		tipoLabel.setEnabled(false);
		getNovoAssuntoButton().setEnabled(false);
		getNovoRitmoButton().setEnabled(false);
		getTipoComboBox().setEnabled(false);
		anoLabel.setText("Ano");
		assuntosLabel.setText("Assuntos");
		TitledBorder tb = (TitledBorder) getCantoresPanel().getBorder();
		tb.setTitle("Intérpretes");
		getCantoresPanel().repaint();
		TitledBorder tbm = (TitledBorder) getMusicasPanel().getBorder();
		tbm.setTitle("Mensagens");
		getMusicasPanel().repaint();
		letraLabel.setEnabled(true);
		getLetraTextArea().setEnabled(true);
		getLetraTextArea().setEditable(true);
		getLetraTextArea().setBackground(Color.white);
		letraLabel.setText("Texto");
		
		assuntosLabel.setEnabled(true);
		novoAssuntoButton.setEnabled(true);
		getAssuntosList().setEnabled(true);
		getAssuntosList().setBackground(Color.white);
		getLimparSelecaoAssuntosButton().setEnabled(true);
		
		// atualizando as listas de Ritmos e de Assuntos
		getAssuntosList().setListData(getAssuntosString());
		getTipoComboBox().removeAllItems();
		for (String s: getTiposString()) {
			getTipoComboBox().addItem(s);
		}
		
		musicasNomesCampos = null;
		
		atualizarTabelaDeMusicas();
		atualizarTabelaDeCantores();
		//$hide<<$
	}

}
