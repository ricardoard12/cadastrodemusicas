package gui.player;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import classesbasicas.Playlist;
import fachada.Fachada;

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
public class CarregarPlaylistDialog extends javax.swing.JDialog {
	private JLabel labelPlaylists;
	private JComboBox comboBoxPlaylists;
	private JButton buttonOK;
	private String playlistEscolhida = null;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				CarregarPlaylistDialog inst = new CarregarPlaylistDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public CarregarPlaylistDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setTitle("Carregar Playlist");
				thisLayout.rowWeights = new double[] {0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7};
				thisLayout.columnWeights = new double[] {0.9, 0.1};
				thisLayout.columnWidths = new int[] {7, 7};
				getContentPane().setLayout(thisLayout);
				this.setPreferredSize(new java.awt.Dimension(200, 82));
				{
					labelPlaylists = new JLabel();
					getContentPane().add(labelPlaylists, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					labelPlaylists.setText("Playlists");
				}
				{
					String[] lista = null;
					//$hide>>$
					List<Playlist> playlists = Fachada.listarPlaylists();
					lista = new String[playlists.size() + 1];
					lista[0] = "";
					for (int i = 1; i <= playlists.size(); i++) lista[i] = playlists.get(i-1).getNome();
					//$hide<<$
					lista = (lista == null ? new String[]{"Teste P1", "Teste P2"} : lista);
					ComboBoxModel comboBoxPlaylistModel = 
						new DefaultComboBoxModel(lista);
					comboBoxPlaylists = new JComboBox();
					getContentPane().add(comboBoxPlaylists, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					comboBoxPlaylists.setModel(comboBoxPlaylistModel);
				}
				{
					buttonOK = new JButton();
					getContentPane().add(buttonOK, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					buttonOK.setText("OK");
					buttonOK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							playlistEscolhida = (String) comboBoxPlaylists.getSelectedItem();
							if (playlistEscolhida.equals("")) playlistEscolhida = null;
							dispose();
						}
					});
				}
			}
			pack();
			this.setSize(200, 82);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getPlaylistEscolhida() {
		return playlistEscolhida;
	}

}
