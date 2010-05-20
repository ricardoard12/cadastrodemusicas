package gui.cantores;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import classesbasicas.Cantor;

public class CantoresInternalFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4412647208033762712L;
	private JPanel jContentPane = null;
	private JTabbedPane cantoresTabbedPane = null;
	private procurarCantoresPanel procurarCantoresPanel = null;
	private EditarCantoresPanel editarCantoresPanel = null;

	/**
	 * This is the xxx default constructor
	 */
	public CantoresInternalFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("Cantores");
		this.setClosable(true);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getCantoresTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes cantoresTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getCantoresTabbedPane() {
		if (cantoresTabbedPane == null) {
			cantoresTabbedPane = new JTabbedPane();
			cantoresTabbedPane.addTab("Procurar Cantores", null, getProcurarCantoresPanel(), null);
			cantoresTabbedPane.addTab("Editar Cantor", null, getEditarCantoresPanel(), null);
		}
		return cantoresTabbedPane;
	}

	/**
	 * This method initializes procurarCantoresPanel	
	 * 	
	 * @return gui.cantores.procurarCantoresPanel	
	 */
	private procurarCantoresPanel getProcurarCantoresPanel() {
		if (procurarCantoresPanel == null) {
			procurarCantoresPanel = new procurarCantoresPanel();
			procurarCantoresPanel.setPai(this);
		}
		return procurarCantoresPanel;
	}

	/**
	 * This method initializes editarCantoresPanel	
	 * 	
	 * @return gui.cantores.EditarCantoresPanel	
	 */
	private EditarCantoresPanel getEditarCantoresPanel() {
		if (editarCantoresPanel == null) {
			editarCantoresPanel = new EditarCantoresPanel();
		}
		return editarCantoresPanel;
	}
	
	// métodos criados pelo programador
	public void editarCantor(Cantor c) {
		getEditarCantoresPanel().editarCantor(c);
		getCantoresTabbedPane().setSelectedIndex(1);
	}

}
