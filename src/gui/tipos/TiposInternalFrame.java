package gui.tipos;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TiposInternalFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6673258073978497835L;
	private JPanel jContentPane = null;
	private JTabbedPane tabbedPane = null;
	private CadastrarTiposPanel cadastrarTiposPanel = null;

	/**
	 * This is the xxx default constructor
	 */
	public TiposInternalFrame() {
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
		this.setClosable(true);
		this.setTitle("Ritmos");
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
			jContentPane.add(getTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes tabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.addTab("Cadastrar Ritmo", null, getCadastrarTiposPanel(), null);
		}
		return tabbedPane;
	}

	/**
	 * This method initializes cadastrarTiposPanel	
	 * 	
	 * @return gui.tipos.CadastrarTiposPanel	
	 */
	private CadastrarTiposPanel getCadastrarTiposPanel() {
		if (cadastrarTiposPanel == null) {
			cadastrarTiposPanel = new CadastrarTiposPanel();
		}
		return cadastrarTiposPanel;
	}

}
