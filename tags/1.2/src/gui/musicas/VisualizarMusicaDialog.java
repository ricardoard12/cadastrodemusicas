package gui.musicas;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;

import util.GlobalPlayer;
import classesbasicas.Musica;

public class VisualizarMusicaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private VisualizarMusicaPanel visualizarMusicaPanel = null;
	Musica musica = null;

	/**
	 * @param owner
	 */
	public VisualizarMusicaDialog(Frame owner, Musica musica) {
		super(owner);
		initialize();
		pack();
		this.setSize(752, 286);
		// this.setPreferedSize(650, 200);
		getVisualizarMusicaPanel().visualizarMusica(musica);
		this.musica = musica;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(650, 200);
		this.setModal(true);
		this.setTitle("Visualizar Música");
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
			jContentPane.add(getVisualizarMusicaPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes visualizarMusicaPanel	
	 * 	
	 * @return gui.musicas.VisualizarMusicaPanel	
	 */
	private VisualizarMusicaPanel getVisualizarMusicaPanel() {
		if (visualizarMusicaPanel == null) {
			visualizarMusicaPanel = new VisualizarMusicaPanel();
		}
		return visualizarMusicaPanel;
	}
	
	@Override
	public void setVisible(boolean arg0) {
		// TODO Auto-generated method stub
		GlobalPlayer.stop();
		super.setVisible(arg0);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
