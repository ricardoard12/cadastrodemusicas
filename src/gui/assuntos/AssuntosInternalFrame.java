package gui.assuntos;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

public class AssuntosInternalFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2213634541862693033L;
	private JPanel jContentPane = null;
	private JTabbedPane tabbedPane = null;
	private CadastrarAssuntosPanel cadastrarAssuntosPanel = null;

	/**
	 * This is the xxx default constructor
	 */
	public AssuntosInternalFrame() {
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
		this.setTitle("Assuntos");
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
			tabbedPane.addTab("Cadastrar Assunto", null, getCadastrarAssuntosPanel(), null);
		}
		return tabbedPane;
	}

	/**
	 * This method initializes cadastrarAssuntosPanel	
	 * 	
	 * @return gui.assuntos.CadastrarAssuntosPanel	
	 */
	private CadastrarAssuntosPanel getCadastrarAssuntosPanel() {
		if (cadastrarAssuntosPanel == null) {
			cadastrarAssuntosPanel = new CadastrarAssuntosPanel();
		}
		return cadastrarAssuntosPanel;
	}

}
