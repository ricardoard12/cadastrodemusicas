package gui.cantores;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import classesbasicas.Cantor;
import exceptions.DataException;
import fachada.Fachada;

public class procurarCantoresPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel dadosDaProcuraPanel = null;
	private JLabel nomeLabel = null;
	private JTextField nomeTextField = null;
	private JLabel nomeSemEspacosLabel = null;
	private JTextField nomeSemEspacosTextField = null;
	private JPanel tabelaPanel = null;
	private JScrollPane tabelaScrollPane = null;
	private JTable tabelaTable = null;
	private JPanel botoesPanel = null;
	private JButton editarButton = null;
	
	private CantoresInternalFrame pai = null;
	
	private Vector<String> nomesCampos = null;  //  @jve:decl-index=0:
	private Vector<Vector<String>> cantoresDados = null;
	private List<Cantor> cantores = null;
	private DefaultTableModel cantoresTableModel = null;
	
	/**
	 * This is the default constructor
	 */
	public procurarCantoresPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.gridx = 1;
		gridBagConstraints21.insets = new Insets(0, 8, 0, 0);
		gridBagConstraints21.gridy = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.gridy = 1;
		nomeSemEspacosLabel = new JLabel();
		nomeSemEspacosLabel.setText("Nome sem Espaços");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		this.setSize(619, 280);
		this.setLayout(new GridBagLayout());
		this.add(getDadosDaProcuraPanel(), gridBagConstraints);
		this.add(getTabelaPanel(), gridBagConstraints11);
		this.add(getBotoesPanel(), gridBagConstraints21);
	}

	/**
	 * This method initializes dadosDaProcuraPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosDaProcuraPanel() {
		if (dadosDaProcuraPanel == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.anchor = GridBagConstraints.SOUTHWEST;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints3.insets = new Insets(0, 14, 0, 0);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints2.insets = new Insets(0, 14, 0, 0);
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints1.gridx = 0;
			nomeLabel = new JLabel();
			nomeLabel.setText("Nome");
			dadosDaProcuraPanel = new JPanel();
			dadosDaProcuraPanel.setLayout(new GridBagLayout());
			dadosDaProcuraPanel.add(nomeLabel, gridBagConstraints4);
			dadosDaProcuraPanel.add(getNomeTextField(), gridBagConstraints1);
			dadosDaProcuraPanel.add(nomeSemEspacosLabel, gridBagConstraints2);
			dadosDaProcuraPanel.add(getNomeSemEspacosTextField(), gridBagConstraints3);
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
			nomeTextField.setColumns(20);
			nomeTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getCantoresTableModel().setDataVector(getCantoresDados(), getNomesCampos());
				}
			});
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
			nomeSemEspacosTextField.setColumns(20);
			nomeSemEspacosTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getCantoresTableModel().setDataVector(getCantoresDados(), getNomesCampos());
				}
			});
		}
		return nomeSemEspacosTextField;
	}

	/**
	 * This method initializes tabelaPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTabelaPanel() {
		if (tabelaPanel == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.weighty = 1.0;
			gridBagConstraints5.ipadx = 440;
			gridBagConstraints5.ipady = 196;
			gridBagConstraints5.insets = new Insets(4, 0, 0, 0);
			gridBagConstraints5.weightx = 1.0;
			tabelaPanel = new JPanel();
			tabelaPanel.setLayout(new GridBagLayout());
			tabelaPanel.add(getTabelaScrollPane(), gridBagConstraints5);
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
			tabelaScrollPane.setViewportView(getTabelaTable());
		}
		return tabelaScrollPane;
	}

	/**
	 * This method initializes tabelaTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTabelaTable() {
		if (tabelaTable == null) {
			tabelaTable = new JTable();
			tabelaTable.setModel(getCantoresTableModel());
		}
		return tabelaTable;
	}

	/**
	 * This method initializes botoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotoesPanel() {
		if (botoesPanel == null) {
			botoesPanel = new JPanel();
			botoesPanel.setLayout(new GridBagLayout());
			botoesPanel.add(getEditarButton(), new GridBagConstraints());
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
			editarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pai != null && getTabelaTable().getSelectedRow() != -1) {
						Cantor c = cantores.get(getTabelaTable().getSelectedRow());
						
						getNomeTextField().setText("");
						getNomeSemEspacosTextField().setText("");
						
						getCantoresTableModel().setDataVector(getCantoresDados(), getNomesCampos());
						
						pai.editarCantor(c);
					}
				}
			});
		}
		return editarButton;
	}
	
	// métodos criados pelo programador
	private Vector<String> getNomesCampos() {
		if (nomesCampos == null) {
			nomesCampos = new Vector<String>();
			
			nomesCampos.add("Nome");
			nomesCampos.add("Nome sem Espaços");
		}
		
		return nomesCampos;
	}
	
	private Vector<Vector<String>> getCantoresDados() {
		cantoresDados = new Vector<Vector<String>>();
		
		for (Cantor c: getCantores()) {
			Vector<String> temp = new Vector<String>();
			
			temp.add(c.getNome());
			temp.add(c.getNomeSemEspacos());
			
			cantoresDados.add(temp);
		}
		
		return cantoresDados;
	}
	
	private List<Cantor> getCantores() {
		
		String nome = getNomeTextField().getText();
		String nomeSemEspacos = getNomeSemEspacosTextField().getText();
		
		try {
			cantores = Fachada.listarCantoresPorDiversos(nome, nomeSemEspacos);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cantores;
	}
	
	private DefaultTableModel getCantoresTableModel() {
		if (cantoresTableModel == null) {
			cantoresTableModel = new DefaultTableModel() {
				private static final long serialVersionUID = 2014828586714349050L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			cantoresTableModel.setDataVector(getCantoresDados(), getNomesCampos());
		}
		return cantoresTableModel;
	}
	
	public void setPai(CantoresInternalFrame pai) {
		this.pai = pai;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
