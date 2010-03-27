package gui.colecaodiscos;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

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
public class GerarColecaoDiscosDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private GerarColecaoDiscosPanel gerarColecaoPanel = null;

	private List<Musica> colecao = null;
	
	public GerarColecaoDiscosDialog() {
		super((Frame) null);
		initialize();
	}
	
	/**
	 * @param owner
	 */
	public GerarColecaoDiscosDialog(Frame owner) {
		super(owner);
		initialize();
	}
	
	public GerarColecaoDiscosDialog(List<Musica> colecao, Frame owner) {
		super(owner);
		this.colecao = colecao;
		initialize();
	} 

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setModal(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Configurar Coleção");
		this.setContentPane(getJContentPane());
		pack();
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
			jContentPane.add(getGerarColecaoPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes gerarColecaoPanel	
	 * 	
	 * @return gui.colecao.GerarColecaoPanel	
	 */
	private GerarColecaoDiscosPanel getGerarColecaoPanel() {
		if (gerarColecaoPanel == null) {
			gerarColecaoPanel = new GerarColecaoDiscosPanel(this);
			if (colecao != null) gerarColecaoPanel.setColecao(colecao);
		}
		return gerarColecaoPanel;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
