package gui.player;
import exceptions.DataException;
import fachada.Fachada;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import classesbasicas.Musica;
import classesbasicas.Playlist;


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
	private Playlist playlistSalvar;

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
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				this.setTitle("Salvar Playlist");
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7};
				thisLayout.columnWeights = new double[] {0.6, 0.3};
				thisLayout.columnWidths = new int[] {7, 7};
				getContentPane().setLayout(thisLayout);
				this.setResizable(false);
				this.setPreferredSize(new java.awt.Dimension(262, 114));
				{
					String[] lista = null;
					//$hide>>$
					List<Playlist> playlists = Fachada.listarPlaylists();
					lista = new String[playlists.size() + 1];
					lista[0] = "";
					for (int i = 1; i <= playlists.size(); i++) lista[i] = playlists.get(i-1).getNome();
					//$hide<<$
					lista = (lista == null ? new String[]{"Teste P1", "Teste P2"} : lista);
					ComboBoxModel comboBoxEscolherPlaylistModel = 
						new DefaultComboBoxModel(lista);
					comboBoxEscolherPlaylist = new JComboBox();
					getContentPane().add(comboBoxEscolherPlaylist, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
					comboBoxEscolherPlaylist.setModel(comboBoxEscolherPlaylistModel);
					comboBoxEscolherPlaylist.setPreferredSize(new java.awt.Dimension(101, 21));
					comboBoxEscolherPlaylist.setSize(100, 21);
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
					buttonCadastrarPlaylistEscolhida.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							salvarNaPlaylistSelecionada();
						}
					});
				}
				{
					buttonNovaPlaylist = new JButton();
					getContentPane().add(buttonNovaPlaylist, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 0, 5, 5), 0, 0));
					buttonNovaPlaylist.setText("Nova Playlist");
					buttonNovaPlaylist.setToolTipText("Adiciona Uma Nova Playlist com as Músicas Atuais");
					buttonNovaPlaylist.setFocusable(false);
					buttonNovaPlaylist.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							salvarNovaPlaylist();
						}
					});
				}
				{
					textFieldNovaPlaylist = new JTextField();
					getContentPane().add(textFieldNovaPlaylist, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 5, 5, 0), 0, 0));
					textFieldNovaPlaylist.setPreferredSize(new java.awt.Dimension(100, 21));
				}
			}
			pack();
			this.setSize(262, 114);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Playlist getPlaylistSalvar() {
		return playlistSalvar;
	}

	public void setPlaylistSalvar(Playlist playlistSalvar) {
		this.playlistSalvar = playlistSalvar;
	}
	
	private void salvarNovaPlaylist() {
		//$hide>>$
		Playlist p = new Playlist();
		String nome = textFieldNovaPlaylist.getText();
		
		if (nome == null || nome.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Nome inválido para a Playlist.\nPor favor, preencha ao Lado um nome para a Playlist.", "Nome Inválido", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			Playlist validacao = Fachada.getPlaylist(nome);
			if (validacao != null) {
				JOptionPane.showMessageDialog(this, "Nome de Playlist já existente no Sistema.\nPor favor, escolha um outro nome para a Playlist.", "Nome Inválido", JOptionPane.ERROR_MESSAGE);
			}
			
			p.setNome(nome);
			p.setItens(playlistSalvar.getItens());
			Fachada.adicionarPlaylist(p);
			
			JOptionPane.showMessageDialog(this, "Playlist Salva com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
	}
	
	private void salvarNaPlaylistSelecionada() {
		//$hide>>$
		String nome = (String) comboBoxEscolherPlaylist.getSelectedItem();
		if (nome == null || nome.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Por favor, Selecione uma das Playlists ao Lado.", "Playlist Inválida", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			Playlist p = Fachada.getPlaylist(nome);
			p.setItens(playlistSalvar.getItens());
			Fachada.alterarPlaylist(p);
			
			JOptionPane.showMessageDialog(this, "Playlist Salva com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
	}

}
