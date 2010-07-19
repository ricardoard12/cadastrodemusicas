package gui.musicas;

import exceptions.DataException;
import fachada.Fachada;
import gui.colecaodiscos.GerarColecaoDiscosDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import util.Util;
import classesbasicas.Assunto;
import classesbasicas.Cantor;
import classesbasicas.Colecao;
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
public class ProcurarMusicasPanel extends JPanel {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private static final long serialVersionUID = 1L;
	private JPanel dadosDaProcuraPanel = null;
	private JLabel nomeLabel = null;
	private JTextField nomeTextField = null;
	private JPanel tabelaPanel = null;
	private JScrollPane tabelaScrollPane = null;
	private JLabel colecaoBuscaLabel;
	private JRadioButton radioButtonMensagem;
	private JRadioButton radioButtonTodos;
	private JRadioButton radioButtonInstrumental;
	private JRadioButton radioButtonCantada;
	private ButtonGroup buttonGroupTipoArquivo;
	private JPanel panelTipoArquivo;
	private JTextField anoTextField;
	private JLabel anoLabel;
	private JComboBox colecaoBuscaComboBox;
	private JButton excluirColecaoButton;
	private JButton removerMusicasDaColecaoButton;
	private JButton novaColecaoButton;
	private JButton adicionarAColecaoButton;
	private JComboBox colecoesComboBox;
	private JTable musicasTable = null;
	private JPanel botoesPanel = null;
	private JButton editarButton = null;
	private JButton excluirButton = null;
	private JButton playButton = null;
	private JButton adicionarListaReproducaoButton = null;
	
	private List<Musica> musicas = null;  //  @jve:decl-index=0:
	private Vector<String> nomesCampos = null;  //  @jve:decl-index=0:
	private Vector<Vector<String>> musicasDados = null;  //  @jve:decl-index=0:
	private DefaultTableModel musicasTableModel = null;  //  @jve:decl-index=0:
	private JLabel cantorLabel = null;
	private JTextField cantorTextField = null;

	private MusicasInternalFrame pai = null;
	private JLabel ritmoLabel = null;
	private JLabel assuntoLabel = null;
	private JComboBox ritmoComboBox = null;
	private JComboBox assuntoComboBox = null;
	private JLabel observacaoLabel = null;
	private JTextField observacaoTextField = null;
	private JLabel letraLabel = null;
	private JTextField letraTextField = null;
	private JLabel qualidadeLabel = null;
	private JComboBox qualidadeComboBox = null;
	private JLabel numeroRegistrosLabel = null;
	private JTextField numeroRegistrosTextField = null;
	private JButton gerarColecaoButton = null;
	private List<Colecao> colecoes = null;
	/**
	 * This is the default constructor
	 */
	public ProcurarMusicasPanel() {
		super();
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		letraLabel = new JLabel();
		letraLabel.setText("Letra");
		letraLabel.setPreferredSize(new java.awt.Dimension(183, 14));
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.gridx = 0;
		gridBagConstraints14.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints14.ipadx = 0;
		gridBagConstraints14.ipady = 22;
		gridBagConstraints14.gridheight = 1;
		gridBagConstraints14.gridwidth = 1;
		gridBagConstraints14.gridy = 4;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 1;
		gridBagConstraints7.insets = new Insets(0, 16, 46, 0);
		gridBagConstraints7.fill = GridBagConstraints.BOTH;
		gridBagConstraints7.gridy = 3;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints6.insets = new Insets(0, 0, 4, 0);
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.fill = GridBagConstraints.BOTH;
		gridBagConstraints4.ipadx = 0;
		gridBagConstraints4.gridy = 3;
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new java.awt.Dimension(891, 550));
		this.add(getDadosDaProcuraPanel(), gridBagConstraints6);
		this.add(getTabelaPanel(), gridBagConstraints4);
		this.add(getBotoesPanel(), new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 14, 0, 0), 0, 0));
		this.add(getPanelTipoArquivo(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 17, 5, 3), 0, 0));
	}

	/**
	 * This method initializes dadosDaProcuraPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosDaProcuraPanel() {
		if (dadosDaProcuraPanel == null) {
			GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
			gridBagConstraints33.fill = GridBagConstraints.BOTH;
			gridBagConstraints33.gridy = 5;
			gridBagConstraints33.weightx = 1.0;
			gridBagConstraints33.gridx = 0;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints21.gridy = 4;
			qualidadeLabel = new JLabel();
			qualidadeLabel.setText("Qualidade");
			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints61.gridy = 5;
			gridBagConstraints61.weightx = 1.0;
			gridBagConstraints61.gridwidth = 1;
			gridBagConstraints61.insets = new Insets(0, 16, 0, 0);
			gridBagConstraints61.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints61.gridx = 1;
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints51.gridy = 4;
			gridBagConstraints51.insets = new Insets(0, 16, 0, 0);
			gridBagConstraints51.gridx = 1;
			GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
			gridBagConstraints42.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints42.gridy = 3;
			gridBagConstraints42.weightx = 1.0;
			gridBagConstraints42.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints42.insets = new Insets(0, 12, 0, 0);
			gridBagConstraints42.gridx = 2;
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			gridBagConstraints32.gridx = 2;
			gridBagConstraints32.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints32.insets = new Insets(0, 12, 0, 0);
			gridBagConstraints32.gridy = 2;
			observacaoLabel = new JLabel();
			observacaoLabel.setText("Observação");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints2.gridy = 3;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.insets = new Insets(0, 16, 0, 0);
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints15.gridy = 3;
			gridBagConstraints15.weightx = 1.0;
			gridBagConstraints15.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints15.gridx = 0;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 1;
			gridBagConstraints31.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints31.insets = new Insets(0, 16, 0, 0);
			gridBagConstraints31.gridy = 2;
			assuntoLabel = new JLabel();
			assuntoLabel.setText("Assunto / Instrumentos");
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints12.fill = GridBagConstraints.NONE;
			gridBagConstraints12.gridy = 2;
			ritmoLabel = new JLabel();
			ritmoLabel.setText("Ritmo / Estilo");
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints41.gridy = 1;
			gridBagConstraints41.weightx = 1.0;
			gridBagConstraints41.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints41.insets = new Insets(0, 12, 0, 0);
			gridBagConstraints41.gridx = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints3.insets = new Insets(0, 12, 0, 0);
			gridBagConstraints3.gridy = 0;
			cantorLabel = new JLabel();
			cantorLabel.setText("Cantor / Intérprete");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints1.gridwidth = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.gridx = 0;
			nomeLabel = new JLabel();
			nomeLabel.setText("Nome");
			dadosDaProcuraPanel = new JPanel();
			GridBagLayout dadosDaProcuraPanelLayout = new GridBagLayout();
			dadosDaProcuraPanel.setLayout(dadosDaProcuraPanelLayout);
			dadosDaProcuraPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			dadosDaProcuraPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
			dadosDaProcuraPanelLayout.columnWeights = new double[] {0.2, 0.2, 0.6};
			dadosDaProcuraPanelLayout.columnWidths = new int[] {7, 7, 100};
			dadosDaProcuraPanel.setPreferredSize(new java.awt.Dimension(630, 102));
			dadosDaProcuraPanel.add(getLetraTextField(), new GridBagConstraints(2, 5, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 12, 0, 0), 0, 0));
			dadosDaProcuraPanel.add(letraLabel, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 12, 0, 0), 0, 0));
			dadosDaProcuraPanel.add(nomeLabel, gridBagConstraints1);
			dadosDaProcuraPanel.add(getNomeTextField(), gridBagConstraints);
			dadosDaProcuraPanel.add(cantorLabel, gridBagConstraints3);
			dadosDaProcuraPanel.add(getCantorTextField(), gridBagConstraints41);
			dadosDaProcuraPanel.add(ritmoLabel, gridBagConstraints12);
			dadosDaProcuraPanel.add(assuntoLabel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 12, 0, 0), 0, 0));
			dadosDaProcuraPanel.add(getRitmoComboBox(), new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			atualizarRitmos();
			dadosDaProcuraPanel.add(getAssuntoComboBox(), new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 12, 0, 0), 0, 0));
			atualizarAssuntos();
			dadosDaProcuraPanel.add(observacaoLabel, gridBagConstraints32);
			dadosDaProcuraPanel.add(getObservacaoTextField(), gridBagConstraints42);
			dadosDaProcuraPanel.add(qualidadeLabel, gridBagConstraints21);
			dadosDaProcuraPanel.add(getQualidadeComboBox(), new GridBagConstraints(0, 5, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 144), 0, 0));
			dadosDaProcuraPanel.add(getColecaoBuscaLabel(), new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 12, 0, 0), 0, 0));
			dadosDaProcuraPanel.add(getColecaoBuscaComboBox(), new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 12, 0, 0), 0, 0));
			dadosDaProcuraPanel.add(getAnoLabel(), new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 121, 0, 0), 0, 0));
			dadosDaProcuraPanel.add(getAnoTextField(), new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
			atualizarQualidades();
		}
		return dadosDaProcuraPanel;
	}

	/**
	 * This method initializes nomeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomeTextField() {
		if (nomeTextField == null) {
			nomeTextField = new JTextField();
			nomeTextField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					//$hide>>$
					procurarMusicas();
					//$hide<<$
				}
				
			});
			nomeTextField.setColumns(10);
			nomeTextField.setPreferredSize(new java.awt.Dimension(403, 20));
			nomeTextField.setSize(291, 20);
		}
		return nomeTextField;
	}

	/**
	 * This method initializes tabelaPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTabelaPanel() {
		if (tabelaPanel == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.ipadx = 580;
			gridBagConstraints5.gridwidth = 4;
			gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints5.ipady = 312;
			tabelaPanel = new JPanel();
			BorderLayout tabelaPanelLayout = new BorderLayout();
			tabelaPanel.setLayout(tabelaPanelLayout);
			tabelaPanel.setPreferredSize(new java.awt.Dimension(686, 339));
			tabelaPanel.add(getTabelaScrollPane(), BorderLayout.CENTER);
		}
		return tabelaPanel;
	}

	/**
	 * This method initializes tabelaScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTabelaScrollPane() {
		if (tabelaScrollPane == null) {
			tabelaScrollPane = new JScrollPane();
			tabelaScrollPane.setViewportView(getMusicasTable());
		}
		return tabelaScrollPane;
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
			musicasTable.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						//$hide>>$
						showDialog(e);
						//$hide<<$
					}
				}

				private void showDialog(MouseEvent e) {
					if (musicas != null) {
						int row = musicasTable.rowAtPoint(e.getPoint());
						if (row >= 0 && musicas.get(row) != null) {
							//$hide>>$
							visualizarMusica(musicas.get(row));
							//$hide<<$
						}
					}
				}
			});
		}
		
		musicasTable.getColumn(musicasTable.getColumnName(0)).setPreferredWidth(115);
		musicasTable.getColumn(musicasTable.getColumnName(1)).setPreferredWidth(85);
		musicasTable.getColumn(musicasTable.getColumnName(2)).setPreferredWidth(20);
		musicasTable.getColumn(musicasTable.getColumnName(3)).setPreferredWidth(15);
		musicasTable.getColumn(musicasTable.getColumnName(4)).setPreferredWidth(100);
		
		return musicasTable;
	}

	/**
	 * This method initializes botoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotoesPanel() {
		if (botoesPanel == null) {
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 0;
			gridBagConstraints17.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints17.insets = new Insets(0, 0, 20, 0);
			gridBagConstraints17.gridy = 2;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints16.gridy = 1;
			gridBagConstraints16.weightx = 1.0;
			gridBagConstraints16.insets = new Insets(0, 0, 25, 0);
			gridBagConstraints16.gridx = 0;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 0;
			gridBagConstraints13.anchor = GridBagConstraints.CENTER;
			gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.gridy = 0;
			numeroRegistrosLabel = new JLabel();
			numeroRegistrosLabel.setText("Nº de Registros");
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 4;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridy = 3;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.insets = new Insets(20, 0, 0, 0);
			gridBagConstraints9.gridy = 5;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.insets = new Insets(20, 0, 0, 0);
			gridBagConstraints8.gridy = 6;
			botoesPanel = new JPanel();
			botoesPanel.setLayout(new GridBagLayout());
			botoesPanel.add(getEditarButton(), new GridBagConstraints(-1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(14, 8, 0, 8), 0, 0));
			botoesPanel.add(getExcluirButton(), new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 8, 0, 8), 0, 0));
			botoesPanel.add(getPlayButton(), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(14, 8, 0, 8), 0, 0));
			botoesPanel.add(getAdicionarListaReproducaoButton(), new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 8, 0, 8), 0, 0));
			botoesPanel.add(numeroRegistrosLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 8, 0, 8), 0, 0));
			botoesPanel.add(getNumeroRegistrosTextField(), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 8, 0, 8), 0, 0));
			botoesPanel.add(getGerarColecaoButton(), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(20, 8, 0, 8), 0, 0));
			botoesPanel.add(getColecoesComboBox(), new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			botoesPanel.add(getAdicionarAColecaoButton(), new GridBagConstraints(0, 14, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			botoesPanel.add(getNovaColecaoButton(), new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(20, 0, 0, 0), 0, 0));
			botoesPanel.add(getRemoverMusicasDaColecaoButton(), new GridBagConstraints(0, 15, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			botoesPanel.add(getExcluirColecaoButton(), new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		}
		return botoesPanel;
	}

	/**
	 * This method initializes editarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarButton() {
		if (editarButton == null) {
			editarButton = new JButton();
			editarButton.setFocusable(false);
			editarButton.setText("Editar");
			editarButton.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/edit16x16.png")));
			editarButton.setToolTipText("Edita a Música Selecionada");
			editarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int indice = getMusicasTable().getSelectedRow();
					if (pai != null && indice >= 0) {
						//$hide>>$
						pai.editarMusica(musicas.get(indice));
						//$hide<<$
					}
				}
			});
		}
		return editarButton;
	}

	/**
	 * This method initializes excluirButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getExcluirButton() {
		if (excluirButton == null) {
			excluirButton = new JButton();
			excluirButton.setFocusable(false);
			excluirButton.setText("Excluir");
			excluirButton.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/delete2.png")));
			excluirButton.setToolTipText("Excluir a Música Selecionada");
			excluirButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					int indices[] = getMusicasTable().getSelectedRows();
					
					if (indices == null || indices.length <= 0) return;
					
					int confirm = JOptionPane.showConfirmDialog(ProcurarMusicasPanel.this, 
							"Tem certeza de que deseja excluir as " + indices.length + " Músicas Selecionadas?\nESSA OPERAÇÃO NÃO PODE SER DESFEITA.", 
							"Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
					
					if (confirm == JOptionPane.OK_OPTION) {
						for (int indice: indices) {

							Musica m = musicas.get(indice);					
						
							if (indice >= 0) {						
								
								//$hide>>$
								String nomeArquivo = Util.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + m.getNomeArquivo();
								//$hide<<$
								File arquivo = new File(nomeArquivo);
								if (arquivo.delete() || !arquivo.exists()) {
									try {
										Fachada.excluirMusica(m);
									} catch (DataException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
										JOptionPane.showMessageDialog(ProcurarMusicasPanel.this, "Não foi possível remover a música do BD.", "Erro.", JOptionPane.ERROR_MESSAGE);
										pai.musicaFoiApagada();
										return;
									}
								}
							}
						}
						JOptionPane.showMessageDialog(ProcurarMusicasPanel.this, "Músicas excluídas com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						pai.musicaFoiApagada();
					}					
					//$hide<<$
				}
			});
		}
		return excluirButton;
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
			playButton.setText("Play");
			playButton.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/media_play_green.png")));
			playButton.setToolTipText("Toca as Músicas Selecionadas no Player");
			playButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					play();
					//$hide<<$
				}
			});
		}
		return playButton;
	}
	
	/**
	 * This method initializes adicionarListaReproducaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAdicionarListaReproducaoButton() {
		if (adicionarListaReproducaoButton == null) {
			adicionarListaReproducaoButton = new JButton();
			adicionarListaReproducaoButton.setFocusable(false);
			adicionarListaReproducaoButton.setText("Adic.");
			adicionarListaReproducaoButton.setIcon(new ImageIcon(getClass().getResource("/figuras/media/document_add.png")));
			adicionarListaReproducaoButton.setToolTipText("Adiciona as Músicas Selecionadas à Lista de Reprodução");
			adicionarListaReproducaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					adicionarAListaReproducao();
					//$hide<<$
				}
			});
		}
		return adicionarListaReproducaoButton;
	}
	
	private DefaultTableModel getMusicasTableModel() {
		if (musicasTableModel == null) {
			musicasTableModel = new DefaultTableModel() {
				private static final long serialVersionUID = 2014828586714349050L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
				
				
			};
			musicasTableModel.setDataVector(getMusicasDados(), getNomesCampos());
			getNumeroRegistrosTextField().setText("" + musicasDados.size());
		}
		return musicasTableModel;
	}

	/**
	 * This method initializes cantorTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCantorTextField() {
		if (cantorTextField == null) {
			cantorTextField = new JTextField();
			cantorTextField.setColumns(10);
			cantorTextField.setPreferredSize(new java.awt.Dimension(321, 20));
			cantorTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					procurarMusicas();
					//$hide<<$
				}
			});
		}
		return cantorTextField;
	}

	public void procurarMusicas() {
		getMusicasTableModel().setDataVector(getMusicasDados(), 
				getNomesCampos());
		
		ColorRenderer cr = new ColorRenderer();
		
		for (int j = 0; j < musicasTable.getColumnCount(); j++) {
			musicasTable.getColumn(getNomesCampos().get(j)).setCellRenderer(cr);
		}
		
		getMusicasTable();
		
		getNumeroRegistrosTextField().setText("" + musicasDados.size());
	}

	public void setPai(MusicasInternalFrame pai) {
		this.pai = pai;
	}

	private void play() {
		//$hide>>$
		int[] indices = getMusicasTable().getSelectedRows();
		
		if (indices != null && indices.length > 0) {
			List<Musica> ms = new ArrayList<Musica>();
			for (int i: indices) {
				ms.add(musicas.get(i));
			}
			player.GlobalPlayer.getGlobalPlayer().open(ms);	
		}
			
			/*String nomeArquivo = m.getNomeArquivo();
			String caminhoCompleto = Util.getDiretorioBase() + File.separator 
				+ m.getDiretorio() + File.separator + nomeArquivo;
			
			GlobalPlayer.play(caminhoCompleto);
			getControlesInPanel().add(GlobalPlayer.getControle(), BorderLayout.SOUTH);				
			getControlesInPanel().validate();
			GlobalPlayer.getControle().repaint();*/
		//$hide<<$
	}

	private void adicionarAListaReproducao() {
		//$hide>>$
		int[] indices = getMusicasTable().getSelectedRows();
		
		if (indices != null && indices.length > 0) {
			List<Musica> ms = new ArrayList<Musica>();
			for (int i: indices) {
				ms.add(musicas.get(i));
			}
			player.GlobalPlayer.getGlobalPlayer().adicionarMusicasAoPlayer(ms);	
		}		
		//$hide<<$
	}

	private List<Musica> getMusicas() {
		String nome = getNomeTextField().getText();
		String nomeCantor = getCantorTextField().getText();
		String ritmo = (String) getRitmoComboBox().getSelectedItem();
		String assunto = (String) getAssuntoComboBox().getSelectedItem();
		String observacao = getObservacaoTextField().getText();
		String qualidade = (String) getQualidadeComboBox().getSelectedItem();
		String letra = getLetraTextField().getText();
		int tipoArquivo = getTipoArquivo();
		Colecao colecao = null;
		int ano = 0;
		
		if (getColecaoBuscaComboBox().getSelectedIndex() > 0) {
			colecao = colecoes.get(getColecaoBuscaComboBox().getSelectedIndex() - 1);
		}
		
		String anoString = getAnoTextField().getText().trim();
		if (anoString.length() > 0) {
			if (anoString.matches("[0-9][0-9][0-9][0-9]")) {
				ano = Integer.parseInt(anoString);
			} else {
				JOptionPane.showMessageDialog(this, "O Campo Ano está inválido. Por favor, preencha o Campo com 4 dígitos numéricos ou deixe-o em Branco.", "Campo Ano inválido", JOptionPane.ERROR_MESSAGE);
				ano = 0;
				getAnoTextField().setText("");
			}
		}
		
		if ( (nome == null || nome.trim().equals("")) && (nomeCantor == null || nomeCantor.trim().equals("")) && 
			 (ritmo == null || ritmo.trim().equals("")) && (assunto == null || assunto.trim().equals("")) &&
			 (observacao == null || observacao.trim().equals("")) && (qualidade == null || qualidade.trim().equals("")) &&
			 (letra == null || letra.trim().equals("")) && ano == 0) {
			tipoArquivo = Constantes.TIPO_ARQUIVO_NAO_LISTAR;
		}

		//$hide>>$
		// só pra garantir...
		musicas = new ArrayList<Musica>();
		
		try {
			musicas = Fachada.listarMusicasPorDiversos(nome, nomeCantor, ritmo, assunto, observacao, qualidade, letra, colecao, ano, tipoArquivo);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
		return musicas;		
	}

	private int getTipoArquivo() {
		int tipoArquivo = Constantes.TIPO_ARQUIVO_NAO_LISTAR;
		if (getRadioButtonCantada().isSelected()) tipoArquivo = Constantes.TIPO_ARQUIVO_MUSICA_CANTADA;
		else if (getRadioButtonInstrumental().isSelected()) tipoArquivo = Constantes.TIPO_ARQUIVO_MUSICA_INSTRUMENTAL;
		else if (getRadioButtonMensagem().isSelected()) tipoArquivo = Constantes.TIPO_ARQUIVO_MENSAGEM;
		else tipoArquivo = Constantes.TIPO_ARQUIVO_TODOS;
		
		return tipoArquivo;
	}

	private Vector<Vector<String>> getMusicasDados() {
		musicasDados = new Vector<Vector<String>>();
		int tipoArquivo = getTipoArquivo();
		
		//$hide>>$
		for (Musica m: getMusicas()) {
			Vector<String> temp = new Vector<String>();

			if (tipoArquivo == Constantes.TIPO_ARQUIVO_TODOS) {
				String adicionar = "[" + Constantes.TIPO_ARQUIVO_NOMES_TIPOS[m.getTipoArquivo()].charAt(0) + "] ";
				temp.add(adicionar + m.getNome());
			} else {
				temp.add(m.getNome());	
			}
			
			if (m.getCantores() != null && m.getCantores().size() > 0) {
				String cantores = "";
				for (Cantor c: m.getCantores()) {
					cantores += c.getNome();
					if (m.getCantores().indexOf(c) < m.getCantores().size() - 1) {
						cantores += ", ";
					}
					temp.add(cantores);
				}
			} else {
				temp.add("");
			}
			if (m.getTipo() != null) {
				temp.add(m.getTipo().getTipo());
			} else {
				temp.add("");
			}
			if (m.getDuracao() > 0) {
				DecimalFormat df = new DecimalFormat("00");
				temp.add(m.getDuracao() / 60 + ":" + df.format(m.getDuracao() % 60));
			} else {
				temp.add("");
			}
			/*if (m.getTipoArquivo() >= Constantes.TIPO_ARQUIVO_MUSICA_CANTADA && m.getTipoArquivo() <= Constantes.TIPO_ARQUIVO_MENSAGEM) {
				temp.add(Constantes.TIPO_MUSICA_NOMES_TIPOS[m.getTipoArquivo()]);
			} else {
				temp.add("");
			}*/
			if (m.getAssuntos() != null && m.getAssuntos().size() > 0) {
				String assuntos = m.getAssuntos().get(0).getAssunto(); 
				for (int j = 1; j < m.getAssuntos().size(); j++) {
					assuntos += ", " + m.getAssuntos().get(j).getAssunto();
				}
				temp.add(assuntos);
			} else {
				temp.add("");
			}
			
			musicasDados.add(temp);
			
		}
		//$hide<<$
		
		return musicasDados;
	}

	private Vector<String> getNomesCampos() {
		if (nomesCampos == null) {
			nomesCampos = new Vector<String>();
			
			nomesCampos.add("Nome");
			if (getTipoArquivo() == Constantes.TIPO_ARQUIVO_TODOS) {
				nomesCampos.add("Cantor/Intérprete");
				nomesCampos.add("Ritmo/Estilo");	
			} else if (getTipoArquivo() == Constantes.TIPO_ARQUIVO_MENSAGEM) {
				nomesCampos.add("Intérprete");
				nomesCampos.add("");
			} else if (getTipoArquivo() == Constantes.TIPO_ARQUIVO_MUSICA_INSTRUMENTAL) {
				nomesCampos.add("Intérprete");
				nomesCampos.add("Estilo");
			} else if (getTipoArquivo() == Constantes.TIPO_ARQUIVO_MUSICA_CANTADA) {
				nomesCampos.add("Cantor");
				nomesCampos.add("Ritmo");
			}
			nomesCampos.add("Duração");
			if (getTipoArquivo() == Constantes.TIPO_ARQUIVO_TODOS) {
				nomesCampos.add("Assuntos/Instrumentos");
			} else if (getTipoArquivo() == Constantes.TIPO_ARQUIVO_MUSICA_INSTRUMENTAL) {
				nomesCampos.add("Instrumentos");
			} else {
				nomesCampos.add("Assuntos");
			}
		}		
		
		return nomesCampos;
	}

	public void finalizar() {
		//$hide>>$
		adicionarAListaReproducao();
		//$hide<<$
	}

	/**
	 * This method initializes ritmoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getRitmoComboBox() {
		if (ritmoComboBox == null) {
			ritmoComboBox = new JComboBox();
			ritmoComboBox.setPreferredSize(new java.awt.Dimension(60, 18));
			ritmoComboBox.setSize(80, 20);
			ritmoComboBox.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						//$hide>>$
						procurarMusicas();
						e.consume();
						//$hide<<$
					}
				}
			});
		}
		return ritmoComboBox;
	}

	/**
	 * This method initializes assuntoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getAssuntoComboBox() {
		if (assuntoComboBox == null) {
			assuntoComboBox = new JComboBox();
			assuntoComboBox.setPreferredSize(new java.awt.Dimension(168, 20));
			assuntoComboBox.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						//$hide>>$
						procurarMusicas();
						e.consume();
						//$hide<<$
					}
				}
			});
		}
		return assuntoComboBox;
	}

	/**
	 * This method initializes observacaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getObservacaoTextField() {
		if (observacaoTextField == null) {
			observacaoTextField = new JTextField();
			observacaoTextField.setColumns(10);
			observacaoTextField.setPreferredSize(new java.awt.Dimension(381, 20));
			observacaoTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					procurarMusicas();
					//$hide<<$
				}
			});
		}
		return observacaoTextField;
	}

	/**
	 * This method initializes letraTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getLetraTextField() {
		if (letraTextField == null) {
			letraTextField = new JTextField();
			letraTextField.setColumns(10);
			letraTextField.setPreferredSize(new java.awt.Dimension(324, 20));
			letraTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$
					procurarMusicas();
					//$hide<<$
				}
			});
		}
		return letraTextField;
	}

	/**
	 * This method initializes qualidadeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getQualidadeComboBox() {
		if (qualidadeComboBox == null) {
			qualidadeComboBox = new JComboBox();
			qualidadeComboBox.setSize(80, 20);
			qualidadeComboBox.setPreferredSize(new java.awt.Dimension(64, 20));
			qualidadeComboBox.setRenderer(new ColorCellRenderer());
			qualidadeComboBox.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						//$hide>>$
						procurarMusicas();
						e.consume();
						//$hide<<$
					}
				}
			});
		}
		return qualidadeComboBox;
	}
	
	public void atualizarRitmos() {
		//$hide>>$
		try {
			int tipoArquivo = getTipoArquivo();
			
			List<Tipo> tipos = Fachada.listarTipos(tipoArquivo);
			
			String s = (String) getRitmoComboBox().getSelectedItem();
			
			getRitmoComboBox().removeAllItems();
			
			getRitmoComboBox().addItem("");
			for (Tipo t: tipos) {
				getRitmoComboBox().addItem(t.getTipo());
			}
			
			if (s != null && !s.equals("")) {
				getRitmoComboBox().setSelectedItem(s);
			}
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
	}
	
	public void atualizarAssuntos() {
		//$hide>>$
		try {
			int tipoArquivo = getTipoArquivo();
			
			List<Assunto> assuntos = Fachada.listarAssuntos(tipoArquivo);
			
			String s = (String) getAssuntoComboBox().getSelectedItem();
			
			getAssuntoComboBox().removeAllItems();
			
			getAssuntoComboBox().addItem("");
			for (Assunto a: assuntos) {
				getAssuntoComboBox().addItem(a.getAssunto());
			}
			
			if (s != null && !s.equals("")) {
				getAssuntoComboBox().setSelectedItem(s);
			}
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//$hide<<$
	}
	
	public void atualizarQualidades() {
		//$hide>>$
		try {
			List<Qualidade> qualidades = Fachada.listarQualidades();
			
			String s = (String) getQualidadeComboBox().getSelectedItem();
			
			getQualidadeComboBox().removeAllItems();
			
			getQualidadeComboBox().addItem("");
			getQualidadeComboBox().getRenderer().getClass();//setBackground(Constantes.COR_QUALIDADE_FALTANDO);
			// getQualidadeComboBox().get
			for (Qualidade q: qualidades) {
				getQualidadeComboBox().addItem(q.getQualidade());
			}
			getQualidadeComboBox().getComponent(1).setBackground(Constantes.COR_QUALIDADE_RUIM);
			//getQualidadeComboBox().getComponent(2).setBackground(Constantes.COR_QUALIDADE_NORMAL);
			//getQualidadeComboBox().getComponent(3).setBackground(Constantes.COR_QUALIDADE_BOA);
			//getQualidadeComboBox().getComponent(4).setBackground(Constantes.COR_QUALIDADE_OTIMA);
			
			if (s != null && !s.equals("")) {
				getQualidadeComboBox().setSelectedItem(s);
			}
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
	}
	
	private void visualizarMusica(Musica m) {
		//$hide>>$
		VisualizarMusicaDialog dialogo = new VisualizarMusicaDialog((Frame) pai.getParent().getParent().getParent().getParent().getParent(), m);
		
		dialogo.setResizable(false);
		// dialogo.setPreferredSize(new Dimension(500, 200));
		dialogo.setLocationRelativeTo((Frame) pai.getParent().getParent().getParent().getParent().getParent());
		dialogo.setModal(true);
		dialogo.setVisible(true);
		
		// System.out.println("largura: " + dialogo.getSize().width);
		// System.out.println("altura: " + dialogo.getSize().height);
		//$hide<<$
	}

	/**
	 * This method initializes numeroRegistrosTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNumeroRegistrosTextField() {
		if (numeroRegistrosTextField == null) {
			numeroRegistrosTextField = new JTextField();
			numeroRegistrosTextField.setEditable(false);
			numeroRegistrosTextField.setHorizontalAlignment(JTextField.RIGHT);
		}
		return numeroRegistrosTextField;
	}

	/**
	 * This method initializes gerarColecaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGerarColecaoButton() {
		if (gerarColecaoButton == null) {
			gerarColecaoButton = new JButton();
			gerarColecaoButton.setText("Gerar Coleção");
			gerarColecaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//$hide>>$v
					gerarColecao();
					//$hide<<$
				}
			});
			gerarColecaoButton.setFocusable(false);
			gerarColecaoButton.setToolTipText("Gera uma Coleção com as Músicas ao Lado");
		}
		return gerarColecaoButton;
	}
	
	private void gerarColecao() {
		//$hide>>$
		if (musicas != null && musicas.size() != 0) {
			GerarColecaoDiscosDialog dialogo = new GerarColecaoDiscosDialog(musicas, pai.getFrameOwner());
			
			dialogo.setResizable(false);
			// dialogo.setPreferredSize(new Dimension(409, 260));
			dialogo.setLocationRelativeTo(pai.getFrameOwner());
			dialogo.setModal(true);
			dialogo.setVisible(true);
			
			System.out.println("largura: " + dialogo.getSize().width);
			System.out.println("altura: " + dialogo.getSize().height);
		}
		//$hide<<$
	}
	
	private JComboBox getColecoesComboBox() {
		if(colecoesComboBox == null) {
			colecoesComboBox = new JComboBox();
			//$hide>>$
			atualizarColecoes();
			//$hide<<$	
		}
		return colecoesComboBox;
	}
	
	private JButton getAdicionarAColecaoButton() {
		if(adicionarAColecaoButton == null) {
			adicionarAColecaoButton = new JButton();
			adicionarAColecaoButton.setText("Adicionar Músicas");
			adicionarAColecaoButton.setFocusable(false);
			adicionarAColecaoButton.setToolTipText("Adicionar as Músicas Selecionadas à Coleção");
			adicionarAColecaoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					//$hide>>$
					adicionarMusicasSelecionadasAColecao();
					//$hide<<$
				}
			});
		}
		return adicionarAColecaoButton;
	}
	
	private JButton getNovaColecaoButton() {
		if(novaColecaoButton == null) {
			novaColecaoButton = new JButton();
			novaColecaoButton.setText("Nova Coleção");
			novaColecaoButton.setFocusable(false);
			novaColecaoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					//$hide>>$
					cadastrarColecao();
					//$hide<<$
				}
			});
		}
		return novaColecaoButton;
	}
	
	private void cadastrarColecao() {
		//$hide>>$
		String nome = "Nome";
		JTextField nomeTextField = new JTextField();
		String descricao = "Descrição";		
		JTextField descricaoTextField = new JTextField();
		
		Object[] itens = {
			nome, nomeTextField,
			descricao, descricaoTextField
		};
		
		if (JOptionPane.showConfirmDialog(this, itens, "Nova Coleção", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			if (nomeTextField.getText() != null && nomeTextField.getText().length() > 0) {
				Colecao c = new Colecao();
				c.setNome(nomeTextField.getText());
				if (descricaoTextField.getText() != null && descricaoTextField.getText().length() > 0)
					c.setDescricao(descricaoTextField.getText());
				try {
					Fachada.cadastrarColecao(c);
					atualizarColecoes();
					JOptionPane.showMessageDialog(this, "Coleção Cadastrada com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} catch (DataException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Houve um Erro ao Cadastrar a Coleção.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		//$hide<<$
	}
	
	private void atualizarColecoes() {
		//$hide>>$
		// verificando as coleções selecionadas em colecoesComboBox e colecoesBuscaComboBox
		String colecoes = (String) this.getColecoesComboBox().getSelectedItem();
		String colecoesBusca = (String) this.getColecaoBuscaComboBox().getSelectedItem();
		
		try {
			this.colecoes = Fachada.listarColecoes();
		} catch (DataException e) {
			JOptionPane.showMessageDialog(this, "Houve um Erro ao Listar as Coleções.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		int numeroItens = 1;
		if (this.colecoes != null) numeroItens += this.colecoes.size();
		String[] itens = new String[numeroItens];
		itens[0] = "";
		if (this.colecoes != null) {
			for (int i = 0; i < this.colecoes.size(); i++) {
				itens[i + 1] = this.colecoes.get(i).getNome();
			}
		}			
		ComboBoxModel colecoesComboBoxModel = new DefaultComboBoxModel(itens);
		getColecoesComboBox().setModel(colecoesComboBoxModel);
		ComboBoxModel colecaoBuscaComboBoxModel = new DefaultComboBoxModel(itens);
		getColecaoBuscaComboBox().setModel(colecaoBuscaComboBoxModel);
		
		if (colecoes != null && !colecoes.equals("")) {
			getColecoesComboBox().setSelectedItem(colecoes);
		}
		if (colecoesBusca != null && !colecoesBusca.equals("")) {
			getColecaoBuscaComboBox().setSelectedItem(colecoesBusca);
			procurarMusicas();
		}
		//$hide<<$
	}
	
	private void adicionarMusicasSelecionadasAColecao() {
		//$hide>>$
		List<Musica> musicasAdicionar = new ArrayList<Musica>();
		
		int[] indices = getMusicasTable().getSelectedRows();
		
		if (indices.length <= 0 || colecoesComboBox.getSelectedIndex() == 0) return;
		
		for (int i: indices) {
			musicasAdicionar.add(musicas.get(i));	
		}			
		
		try {
			Fachada.adicionarMusicasAColecao(colecoes.get(colecoesComboBox.getSelectedIndex() - 1), musicasAdicionar);
			procurarMusicas();
			JOptionPane.showMessageDialog(this, "Músicas Adicionadas com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} catch (DataException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Houve um Erro ao Adicionar as Músicas.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		//$hide<<$
	}
	
	private JButton getRemoverMusicasDaColecaoButton() {
		if(removerMusicasDaColecaoButton == null) {
			removerMusicasDaColecaoButton = new JButton();
			removerMusicasDaColecaoButton.setText("Remover Músicas");
			removerMusicasDaColecaoButton.setFocusable(false);
			removerMusicasDaColecaoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					//$hide>>$
					removerMusicasDaColecao();
					//$hide<<$
				}
			});
		}
		return removerMusicasDaColecaoButton;
	}
	
	private JButton getExcluirColecaoButton() {
		if(excluirColecaoButton == null) {
			excluirColecaoButton = new JButton();
			excluirColecaoButton.setText("Excluir Coleção");
			excluirColecaoButton.setFocusable(false);
			excluirColecaoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					//$hide>>$
					excluirColecao();
					//$hide<<$
				}
			});
		}
		return excluirColecaoButton;
	}
	
	private void excluirColecao() {
		//$hide>>$
		if (getColecoesComboBox().getSelectedIndex() >= 1) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Tem Certeza de que deseja excluir a Coleção\n(Essa operação não pode ser desfeita)?", "Tem Certeza?", JOptionPane.YES_NO_OPTION)) {
				try {
					Fachada.excluirColecao(colecoes.get(getColecoesComboBox().getSelectedIndex() - 1));
					atualizarColecoes();
					procurarMusicas();
					JOptionPane.showMessageDialog(this, "Coleção excluída com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} catch (DataException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Houve um Erro ao excluir a Coleção.", "Erro", JOptionPane.ERROR_MESSAGE);
				}	
			} else return;

		}
		//$hide<<$
	}
	
	private void removerMusicasDaColecao() {
		//$hide>>$
		
		
		List<Musica> musicasRemover = new ArrayList<Musica>();
		
		int[] indices = getMusicasTable().getSelectedRows();
		
		if (indices.length <= 0 || colecoesComboBox.getSelectedIndex() == 0) return;
		
		if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(this, "Tem Certeza de que deseja remover as Músicas da Coleção Selecionada (" + colecoes.get(colecoesComboBox.getSelectedIndex() - 1).getNome() + ")?", "Tem Certeza?", JOptionPane.YES_NO_OPTION)) {
			return;
		}
		
		for (int i: indices) {
			musicasRemover.add(musicas.get(i));	
		}			
		
		try {
			Fachada.removerMusicasDaColecao(colecoes.get(colecoesComboBox.getSelectedIndex() - 1), musicasRemover);
			procurarMusicas();
			JOptionPane.showMessageDialog(this, "Músicas Removidas com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} catch (DataException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Houve um Erro ao Remover as Músicas.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		//$hide<<$
	}
	
	private JLabel getColecaoBuscaLabel() {
		if(colecaoBuscaLabel == null) {
			colecaoBuscaLabel = new JLabel();
			colecaoBuscaLabel.setText("Coleção");
		}
		return colecaoBuscaLabel;
	}
	
	private JComboBox getColecaoBuscaComboBox() {
		if(colecaoBuscaComboBox == null) { 
			colecaoBuscaComboBox = new JComboBox();
			colecaoBuscaComboBox.setPreferredSize(new java.awt.Dimension(169, 20));
			colecaoBuscaComboBox.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						//$hide>>$
						procurarMusicas();
						e.consume();
						//$hide<<$
					}
				}
			});
			//$hide>>$
			atualizarColecoes();
			//$hide<<$
		}
		return colecaoBuscaComboBox;
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
			anoTextField.setPreferredSize(new java.awt.Dimension(122, 20));
			anoTextField.setSize(169, 20);
			anoTextField.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					//$hide>>$
					procurarMusicas();
					//$hide<<$
				}
				
			});
		}
		return anoTextField;
	}

	public void novaColecaoCadastrada() {
		System.out.println("Nova coleção Cadastrada.");
		atualizarColecoes();
	}
	
	private JPanel getPanelTipoArquivo() {
		if(panelTipoArquivo == null) {
			panelTipoArquivo = new JPanel();
			BoxLayout panelTipoArquivoLayout = new BoxLayout(panelTipoArquivo, javax.swing.BoxLayout.Y_AXIS);
			panelTipoArquivo.setLayout(panelTipoArquivoLayout);
			panelTipoArquivo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED), "Tipo", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma",0,11), new java.awt.Color(0,0,0)));
			panelTipoArquivo.add(getRadioButtonCantada());
			panelTipoArquivo.add(getRadioButtonInstrumental());
			panelTipoArquivo.add(getRadioButtonMensagem());
			panelTipoArquivo.add(getRadioButtonTodos());
		}
		return panelTipoArquivo;
	}
	
	private ButtonGroup getButtonGroupTipoArquivo() {
		if(buttonGroupTipoArquivo == null) {
			buttonGroupTipoArquivo = new ButtonGroup();
		}
		return buttonGroupTipoArquivo;
	}
	
	private JRadioButton getRadioButtonCantada() {
		if(radioButtonCantada == null) {
			radioButtonCantada = new JRadioButton();
			radioButtonCantada.setText("Cantada");
			radioButtonCantada.setPreferredSize(new java.awt.Dimension(107, 23));
			radioButtonCantada.setMargin(new java.awt.Insets(0, 0, 0, 0));
			radioButtonCantada.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (radioButtonCantada.isSelected()) {
						configurarProcuraMusicasCantadas();	
					}
				}
			
			});
			getButtonGroupTipoArquivo().add(radioButtonCantada);
		}
		return radioButtonCantada;
	}
	
	private JRadioButton getRadioButtonInstrumental() {
		if(radioButtonInstrumental == null) {
			radioButtonInstrumental = new JRadioButton();
			radioButtonInstrumental.setText("Instrumental");
			radioButtonInstrumental.setMargin(new java.awt.Insets(0, 0, 0, 0));
			radioButtonInstrumental.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (radioButtonInstrumental.isSelected()) {
						configurarProcuraMusicasInstrumentais();	
					}
				}

			});
			getButtonGroupTipoArquivo().add(radioButtonInstrumental);
		}
		return radioButtonInstrumental;
	}
	
	private JRadioButton getRadioButtonMensagem() {
		if(radioButtonMensagem == null) {
			radioButtonMensagem = new JRadioButton();
			radioButtonMensagem.setText("Mensagem");
			radioButtonMensagem.setMargin(new java.awt.Insets(0, 0, 0, 0));
			radioButtonMensagem.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (radioButtonMensagem.isSelected()) {
						configurarProcuraMensagens();	
					}
				}
			});
			getButtonGroupTipoArquivo().add(radioButtonMensagem);
		}
		return radioButtonMensagem;
	}
	
	private JRadioButton getRadioButtonTodos() {
		if(radioButtonTodos == null) {
			radioButtonTodos = new JRadioButton();
			radioButtonTodos.setText("Todos");
			radioButtonTodos.setMargin(new java.awt.Insets(0, 0, 0, 0));
			radioButtonTodos.setSelected(true);
			radioButtonTodos.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (radioButtonTodos.isSelected()) {
						configurarProcuraTodas();	
					}
				}
			});
			getButtonGroupTipoArquivo().add(radioButtonTodos);
		}
		return radioButtonTodos;
	}
	
	private void configurarProcuraMusicasCantadas() {
		cantorLabel.setText("Cantor");
		
		ritmoLabel.setText("Ritmo");
		ritmoLabel.setEnabled(true);
		getRitmoComboBox().setEnabled(true);
		
		assuntoLabel.setText("Assunto");
		getAssuntoComboBox().setEnabled(true);
		
		letraLabel.setText("Letra");
		letraLabel.setEnabled(true);
		getLetraTextField().setEnabled(true);
		
		nomesCampos = null;
		
		procurarMusicas();
		atualizarRitmos();
		atualizarAssuntos();
	}
	
	private void configurarProcuraMusicasInstrumentais() {
		cantorLabel.setText("Intérprete");
		
		ritmoLabel.setText("Estilo");
		ritmoLabel.setEnabled(true);
		getRitmoComboBox().setEnabled(true);
		
		assuntoLabel.setText("Instrumentos");
		getAssuntoComboBox().setEnabled(true);
		
		// letraLabel.setText("Letra");
		letraLabel.setEnabled(false);
		getLetraTextField().setEnabled(false);
		
		nomesCampos = null;
		
		procurarMusicas();
		atualizarRitmos();
		atualizarAssuntos();
	}
	
	private void configurarProcuraMensagens() {
		cantorLabel.setText("Intérprete");
		
		// ritmoLabel.setText("Ritmo");
		ritmoLabel.setEnabled(false);
		getRitmoComboBox().setEnabled(false);
		
		assuntoLabel.setText("Assunto");
		getAssuntoComboBox().setEnabled(true);
		
		letraLabel.setText("Texto");
		letraLabel.setEnabled(true);
		getLetraTextField().setEnabled(true);
		
		nomesCampos = null;
		
		procurarMusicas();
		atualizarRitmos();
		atualizarAssuntos();
	}
	
	private void configurarProcuraTodas() {
		cantorLabel.setText("Cantor / Intérprete");
		
		ritmoLabel.setText("Ritmo / Estilo");
		ritmoLabel.setEnabled(true);
		getRitmoComboBox().setEnabled(true);
		
		assuntoLabel.setText("Assunto / Instrumentos");
		getAssuntoComboBox().setEnabled(true);
		
		letraLabel.setText("Letra / Texto");
		letraLabel.setEnabled(true);
		getLetraTextField().setEnabled(true);
		
		nomesCampos = null;
		
		procurarMusicas();
		atualizarRitmos();
		atualizarAssuntos();
	}
	
	//$hide>>$
	class ColorRenderer extends JLabel implements TableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Border bordaPadrao;
		private boolean voltarBorda = false;

		public ColorRenderer() {
			setOpaque(true);
			bordaPadrao = getBorder();
		}

		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (value != null)
				setText(value.toString());
			
			setBackground(new Color(244, 244, 244));
			setForeground(table.getForeground());
			
			if (musicas != null) {
				if (musicas.get(row).getQualidade() != null) {
					if (musicas.get(row).getQualidade().getQualidade().equals(Constantes.QUALIDADE_RUIM)) {
						setBackground(Constantes.COR_QUALIDADE_RUIM);
					} else if (musicas.get(row).getQualidade().getQualidade().equals(Constantes.QUALIDADE_BOA)) {
						setBackground(Constantes.COR_QUALIDADE_BOA);
					} else if (musicas.get(row).getQualidade().getQualidade().equals(Constantes.QUALIDADE_OTIMA)) {
						setBackground(Constantes.COR_QUALIDADE_OTIMA);
					}	
				} else {
					setBackground(Constantes.COR_QUALIDADE_FALTANDO);
				}
			}
			
			if (isSelected) {
				/*if (!getFont().isBold()) {*/
					// setFont(getFont().deriveFont(Font.BOLD));//.deriveFont(getFont().getSize() + 1));
					setBorder(BorderFactory.createLineBorder(Color.black, 1));
					voltarBorda = true;
				/*}*/
				
			} else {
				if (voltarBorda) {
					// setFont(getFont().deriveFont(Font.NORMAL));//.deriveFont(getFont().getSize() - 1));
					setBorder(bordaPadrao);
					voltarBorda = false;
				}
			}
			
			return this;
		}
	}
	//$hide<<$

}  //  @jve:decl-index=0:visual-constraint="10,10"

	class ColorCellRenderer implements ListCellRenderer {
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    // width doesn't matter as combobox will size
    // private final static Dimension preferredSize = new Dimension(0, 20);

    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {
      JLabel renderer = (JLabel) defaultRenderer
          .getListCellRendererComponent(list, value, index,
              isSelected, cellHasFocus);
      // if (value instanceof Color) {
        // renderer.setBackground((Color) value);
    	  if (value.equals("")) {
    		  renderer = (JLabel) defaultRenderer
              .getListCellRendererComponent(list, " ", index,
                  isSelected, cellHasFocus);
    		  renderer.setBackground(Constantes.COR_QUALIDADE_FALTANDO);
    	  } else if (value.equals(Constantes.QUALIDADE_RUIM)) {
    		  renderer.setBackground(Constantes.COR_QUALIDADE_RUIM);
    	  } else if (value.equals(Constantes.QUALIDADE_NORMAL)) {
    		  renderer.setBackground(Constantes.COR_QUALIDADE_NORMAL);
    	  } else if (value.equals(Constantes.QUALIDADE_BOA)) {
    		  renderer.setBackground(Constantes.COR_QUALIDADE_BOA);
    	  } else if (value.equals(Constantes.QUALIDADE_OTIMA)) {
    		  renderer.setBackground(Constantes.COR_QUALIDADE_OTIMA);
    	  }
      // }
      // renderer.setPreferredSize(preferredSize);
      return renderer;
    }
  }

