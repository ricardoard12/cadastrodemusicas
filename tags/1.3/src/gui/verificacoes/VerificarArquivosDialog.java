package gui.verificacoes;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class VerificarArquivosDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private VerificarArquivosPanel verificarArquivosPanel = null;

	/**
	 * @param owner
	 */
	public VerificarArquivosDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(485, 327);
		this.setTitle("Verificar Arquivos");
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
			jContentPane.add(getVerificarArquivosPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes verificarArquivosPanel	
	 * 	
	 * @return gui.verificacoes.VerificarArquivosPanel	
	 */
	private VerificarArquivosPanel getVerificarArquivosPanel() {
		if (verificarArquivosPanel == null) {
			verificarArquivosPanel = new VerificarArquivosPanel();
		}
		return verificarArquivosPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
