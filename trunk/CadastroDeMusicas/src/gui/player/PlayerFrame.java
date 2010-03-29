package gui.player;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
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
public class PlayerFrame extends javax.swing.JFrame {
	
	private PlayerPanel playerPanel;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PlayerFrame inst = new PlayerFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public PlayerFrame() {
		super();
		setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setIconImage(new ImageIcon(getClass().getResource("/figuras/icones/music.png")).getImage());
			this.setPreferredSize(new java.awt.Dimension(225, 294));
			this.setResizable(false);
			this.setTitle("Player");
			{
				playerPanel = new PlayerPanel();
				playerPanel.setPlayerFrame(this);
				getContentPane().add(playerPanel, BorderLayout.CENTER);
				playerPanel.setPreferredSize(new java.awt.Dimension(232, 288));
			}
			pack();
			this.setSize(225, 294);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				parar();
			}

			public void windowClosed(WindowEvent arg0) {
				parar();
			}
		});
	}
	
	@Override
	public void setVisible(boolean b) {
		if (!b) parar();
		super.setVisible(b);
	}
	
	public void open(List<Musica> musicas) {
		setVisible(true);
		playerPanel.open(musicas);
	}
	
	public void adicionarMusicasAoPlayer(List<Musica> musicas) {
		playerPanel.adicionarMusicas(musicas);
	}
	
	private void parar() {
		playerPanel.parar();		
	}
}
