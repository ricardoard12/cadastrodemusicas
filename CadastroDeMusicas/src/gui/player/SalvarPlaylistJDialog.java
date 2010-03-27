package gui.player;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


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
public class SalvarPlaylistJDialog extends javax.swing.JDialog {
	private JComboBox comboBoxEscolherPlaylist;
	private JTextField textFieldNovaPlaylist;
	private JButton buttonNovaPlaylist;
	private JButton buttonCadastrarPlaylistEscolhida;
	private JLabel labelPlaylistsCadatradas;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				SalvarPlaylistJDialog inst = new SalvarPlaylistJDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public SalvarPlaylistJDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setTitle("Salvar Playlist");
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7};
				getContentPane().setLayout(thisLayout);
				{
					ComboBoxModel comboBoxEscolherPlaylistModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					comboBoxEscolherPlaylist = new JComboBox();
					getContentPane().add(comboBoxEscolherPlaylist, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
					comboBoxEscolherPlaylist.setModel(comboBoxEscolherPlaylistModel);
				}
				{
					labelPlaylistsCadatradas = new JLabel();
					getContentPane().add(labelPlaylistsCadatradas, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(6, 6, 2, 0), 0, 0));
					labelPlaylistsCadatradas.setText("Playlists Cadastradas");
				}
				{
					buttonCadastrarPlaylistEscolhida = new JButton();
					getContentPane().add(buttonCadastrarPlaylistEscolhida, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 5), 0, 0));
					buttonCadastrarPlaylistEscolhida.setText("Salvar na Playlist");
					buttonCadastrarPlaylistEscolhida.setFocusable(false);
					buttonCadastrarPlaylistEscolhida.setToolTipText("Salva os Arquivos para a Playlist Escolhida ao Lado");
				}
				{
					buttonNovaPlaylist = new JButton();
					getContentPane().add(buttonNovaPlaylist, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 0, 5, 5), 0, 0));
					buttonNovaPlaylist.setText("Nova Playlist");
					buttonNovaPlaylist.setFocusable(false);
				}
				{
					textFieldNovaPlaylist = new JTextField();
					getContentPane().add(textFieldNovaPlaylist, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 5, 5, 0), 0, 0));
					textFieldNovaPlaylist.setPreferredSize(new java.awt.Dimension(100, 21));
				}
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
