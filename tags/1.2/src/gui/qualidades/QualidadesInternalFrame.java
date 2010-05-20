package gui.qualidades;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

public class QualidadesInternalFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7351896601702552117L;
	private JPanel jContentPane = null;
	private JTabbedPane tabbedPane = null;
	private CadastrarQualidadePanel cadastrarQualidadePanel = null;

	/**
	 * This is the xxx default constructor
	 */
	public QualidadesInternalFrame() {
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
		this.setTitle("Qualidades");
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
			tabbedPane.addTab("Cadastrar Qualidade", null, getCadastrarQualidadePanel(), null);
		}
		return tabbedPane;
	}

	/**
	 * This method initializes cadastrarQualidadePanel	
	 * 	
	 * @return gui.qualidades.CadastrarQualidadePanel	
	 */
	private CadastrarQualidadePanel getCadastrarQualidadePanel() {
		if (cadastrarQualidadePanel == null) {
			cadastrarQualidadePanel = new CadastrarQualidadePanel();
		}
		return cadastrarQualidadePanel;
	}

}
