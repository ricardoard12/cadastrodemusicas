package gui.player;

import exceptions.ConfiguracaoInexistenteException;
import exceptions.DataException;
import exceptions.DiretorioBaseInvalidoException;
import fachada.Fachada;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicSliderUI;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import player.MP3PlayerExterno;
import util.Util;
import bd.BDUtil;
import classesbasicas.Constantes;
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
public class PlayerPanel extends javax.swing.JPanel {
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final int VOLUME_MAX = 100;
	
	private final ImageIcon PAUSE_LABEL_PAUSE = new ImageIcon(getClass().getResource("/figuras/media/media_pause.png"));  //  @jve:decl-index=0:
	private final ImageIcon PAUSE_LABEL_PLAY = new ImageIcon(getClass().getResource("/figuras/media/media_play_green.png"));
	private JButton buttonExportarPlaylist;
	private JButton buttonCarregarPlaylist;
	private JButton buttonExcluirPlaylist;
	private JButton buttonSalvarPlaylist;
	private JButton configuracoesButton;
	private JLabel duracaoPlaylistLabel;
	private JButton abaixoButton;
	private JButton acimaButton;
	private JButton removerDaListaButton;
	private JPanel botoesPlayListPanel;
	private final ImageIcon MUTE_LABEL_SOUND_ON = new ImageIcon(getClass().getResource("/figuras/media/sound.png"));
	private final ImageIcon MUTE_LABEL_SOUND_OFF = new ImageIcon(getClass().getResource("/figuras/media/sound-off.png"));
	private MP3PlayerExterno player = null;
	double gain;
	int frames = 0;
	boolean eventoAutomatico = false;
	boolean terminouMusica = false;
	
	int posicaoAtualSliderSalva = 0;
	boolean mousePressionado = false;	
	private JLabel nomeMusicaAtualLabel;
	private JLabel duracaoMusicaAtualLabel;
	private JButton muteButton;
	private JList playListList;
	private JScrollPane playListScrollPane;
	private JSlider volumeSlider;
	private JButton proximaButton;
	private JButton playPauseButton;
	private JButton anteriorButton;
	private JButton stopButton;
	private JPanel botoesPanel;
	private JSlider progressoSlider;
	
	// private List<Musica> musicas = new ArrayList<Musica>();
	private Musica musicaAtual;	
	private int indiceAtual = -1;
	
	private List<String> arquivosTemporarios = new ArrayList<String>();
	
	private int intervaloEntreMusicas = 0;
	private boolean intervalo = false;
	
	private Playlist playlist = new Playlist();

	private PlayerFrame playerFrame;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PlayerPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public PlayerPanel() {
		super();
		
		//$hide>>$
		try {
			playlist = Fachada.getDefaultPlaylist();
			if (playlist == null) {
				playlist = new Playlist();
			}
		} catch (DataException e) {
			System.out.println("Erro ao Carregar Playlist");
		}
		//$hide<<$
		
		initGUI();
		//$hide>>$
		try {
			player = new MP3PlayerExterno(this);
		} catch (JavaLayerException e) {
			JOptionPane.showMessageDialog(this, "Não Foi possível inicializar o Player.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		atualizarListaDeMusicas();
		try {
			String configIntervalo = BDUtil.getConfiguracao(Constantes.CONFIGURACAO_INTERVALO_ENTRE_MUSICAS);
			intervaloEntreMusicas = Integer.parseInt(configIntervalo);
		} catch (ConfiguracaoInexistenteException e) {
			e.printStackTrace();
		} catch (DataException e) {
			e.printStackTrace();
		}
		
		//$hide<<$
	}
	
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			this.setPreferredSize(new java.awt.Dimension(217, 274));
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 1.0, 0.0};
			thisLayout.rowHeights = new int[] {2, 1, 1, 7, 7};
			thisLayout.columnWeights = new double[] {0.9, 0.1};
			thisLayout.columnWidths = new int[] {8, 2};
			this.setLayout(thisLayout);
			this.setSize(217, 274);
			this.setMaximumSize(new java.awt.Dimension(217, 274));
			{
				nomeMusicaAtualLabel = new JLabel();
				this.add(nomeMusicaAtualLabel, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.BOTH, new Insets(8, 4, 2, 4), 0, 0));
				nomeMusicaAtualLabel.setText("Nenhuma Música Sendo Tocada");
				nomeMusicaAtualLabel.setPreferredSize(new java.awt.Dimension(60, 20));
				nomeMusicaAtualLabel.setSize(60, 20);
				nomeMusicaAtualLabel.setFont(new java.awt.Font("Arial",1,12));
				nomeMusicaAtualLabel.setToolTipText("Nome da Música que está Sendo Tocada");
				nomeMusicaAtualLabel.setMaximumSize(new java.awt.Dimension(60, 15));
				nomeMusicaAtualLabel.setMinimumSize(new java.awt.Dimension(60, 15));
			}
			{
				duracaoMusicaAtualLabel = new JLabel();
				this.add(duracaoMusicaAtualLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 0, 0, 4), 0, 0));
				duracaoMusicaAtualLabel.setText("0:00/0:00");
				duracaoMusicaAtualLabel.setPreferredSize(new java.awt.Dimension(21, 17));
				duracaoMusicaAtualLabel.setSize(40, 20);
				duracaoMusicaAtualLabel.setFont(new java.awt.Font("Arial",0,11));
				duracaoMusicaAtualLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			}
			{
				progressoSlider = new JSlider(0, MP3PlayerExterno.getFramesProgresso());
				//$hide>>$
				progressoSlider.addMouseListener(new java.awt.event.MouseAdapter() {   
					public void mousePressed(java.awt.event.MouseEvent e) {  
						mousePressionado = true;
						posicaoAtualSliderSalva = progressoSlider.getValue();
						
					}
					public void mouseReleased(java.awt.event.MouseEvent e) {
						if (progressoSlider.getValue() != posicaoAtualSliderSalva && !terminouMusica) {
							// seek position
							player.irParaPosicao(progressoSlider.getValue());
						}
						mousePressionado = false;
					}
				});
				
				progressoSlider.setUI(new BasicSliderUI(progressoSlider) {
				    protected void scrollDueToClickInTrack(int direction) {
				        // this is the default behaviour, let's comment that out
				        //scrollByBlock(direction);

				        int value = progressoSlider.getValue(); 

				        if (progressoSlider.getOrientation() == JSlider.HORIZONTAL) {
				            value = this.valueForXPosition(progressoSlider.getMousePosition().x);
				        } else if (progressoSlider.getOrientation() == JSlider.VERTICAL) {
				            value = this.valueForYPosition(progressoSlider.getMousePosition().y);
				        }
				        progressoSlider.setValue(value);
				    }
				});
				//$hide<<$
				progressoSlider.setValue(0);
				this.add(progressoSlider, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 0, 0, 0), 0, 0));
				progressoSlider.setFocusable(false);
			}
			{
				botoesPanel = new JPanel();
				GridBagLayout botoesPanelLayout = new GridBagLayout();
				botoesPanelLayout.rowWeights = new double[] {0.1};
				botoesPanelLayout.rowHeights = new int[] {7};
				botoesPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.5};
				botoesPanelLayout.columnWidths = new int[] {1, 1, 1, 1, 1, 1};
				botoesPanel.setLayout(botoesPanelLayout);
				this.add(botoesPanel, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				botoesPanel.setPreferredSize(new java.awt.Dimension(279, 24));
				{
					stopButton = new JButton();
					stopButton.setIcon(new ImageIcon(getClass().getResource("/figuras/media/media_stop_red.png")));
					botoesPanel.add(stopButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(2, 0, 0, 0), 0, 0));
					stopButton.setFocusable(false);
					stopButton.setMargin(new java.awt.Insets(4, 4, 3, 3));
					stopButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//$hide>>$
							stop();
							//$hide<<$
						}
					});
				}
				{
					anteriorButton = new JButton();
					anteriorButton.setIcon(new ImageIcon(getClass().getResource("/figuras/media/media_step_back.png")));
					botoesPanel.add(anteriorButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0, 2, 0, 0), 0, 0));
					anteriorButton.setFocusable(false);
					anteriorButton.setMargin(new java.awt.Insets(4, 4, 3, 3));
					anteriorButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//$hide>>$
							musicaAnterior();
							//$hide<<$
						}
					});
				}
				{
					playPauseButton = new JButton();
					playPauseButton.setIcon(new ImageIcon(getClass().getResource("/figuras/media/media_play_green.png")));
					botoesPanel.add(playPauseButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					playPauseButton.setFocusable(false);
					playPauseButton.setMargin(new java.awt.Insets(4, 8, 3, 7));
					playPauseButton.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							//$hide>>$
							try {
								if (player.getStatus() == BasicPlayer.PAUSED) {
									player.resume();
									playPauseButton.setIcon(PAUSE_LABEL_PAUSE);
								}
								else if (player.getStatus() == BasicPlayer.PLAYING) {
									player.pause();
									playPauseButton.setIcon(PAUSE_LABEL_PLAY);
								} else {
									// int indice = playListList.getSelectedIndex();
									// if (indice >= 0 && indice < musicas.size()) {
									if (indiceAtual >= 0 && indiceAtual < playlist.getItens().size()) {
										tocar(indiceAtual);
									}
								}
							} catch (BasicPlayerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//$hide<<$
						}
					});
				}
				{
					proximaButton = new JButton();
					proximaButton.setIcon(new ImageIcon(getClass().getResource("/figuras/media/media_step_forward.png")));
					botoesPanel.add(proximaButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					proximaButton.setFocusable(false);
					proximaButton.setMargin(new java.awt.Insets(4, 4, 3, 3));
					proximaButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							//$hide>>$
							proximaMusica();
							//$hide<<$
						}
					});
				}
				{
					muteButton = new JButton();
					muteButton.setIcon(new ImageIcon(getClass().getResource("/figuras/media/sound.png")));
					botoesPanel.add(muteButton, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0, 2, 0, 0), 0, 0));
					muteButton.setFocusable(false);
					muteButton.setMargin(new java.awt.Insets(4, 4, 3, 3));
				}
				{
					volumeSlider = new JSlider();
					botoesPanel.add(volumeSlider, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					volumeSlider.setPreferredSize(new java.awt.Dimension(65, 23));
					//$hide>>$
					volumeSlider.setUI(new BasicSliderUI(volumeSlider) {
					    protected void scrollDueToClickInTrack(int direction) {
					        int value = volumeSlider.getValue(); 

					        if (volumeSlider.getOrientation() == JSlider.HORIZONTAL) {
					            value = this.valueForXPosition(volumeSlider.getMousePosition().x);
					        } else if (volumeSlider.getOrientation() == JSlider.VERTICAL) {
					            value = this.valueForYPosition(volumeSlider.getMousePosition().y);
					        }
					        volumeSlider.setValue(value);
					    }
					});
					//$hide<<$
					volumeSlider.setFocusable(false);
				}
			}
			{
				playListScrollPane = new JScrollPane();
				this.add(playListScrollPane, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					playListList = new JList();
					playListList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
					playListScrollPane.setViewportView(playListList);
					//$hide>>$
					playListList.setCellRenderer(new DefaultListCellRenderer() {
						public Component getListCellRendererComponent(JList list, 
		                        Object value, int index, boolean isSelected, boolean cellHasFocus) {
								super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
								if (index == PlayerPanel.this.getIndiceAtual()) 
									this.setBackground(new Color(0, 220, 0));
								else {
									if (isSelected) {
										this.setBackground(new Color(240, 240, 240));
									} else {
										this.setBackground(Color.white);	
									}
								}
								
								if (index == PlayerPanel.this.playlist.getItens().size()) {
									this.setForeground(Color.white);
								} else {
									this.setForeground(Color.black);	
								}						
								
								return this;
							}
						}
					);
					//$hide<<$
					playListList.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							if (evt.getClickCount() == 2) {
					            int indice = playListList.locationToIndex(evt.getPoint());
					          //$hide>>$
					            tocar(indice);
					          //$hide<<$
					         }
						}
					});
				}
			}
			{
				botoesPlayListPanel = new JPanel();
				GridBagLayout botoesPlayListPanelLayout = new GridBagLayout();
				this.add(botoesPlayListPanel, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				botoesPlayListPanelLayout.rowWeights = new double[] {0.1};
				botoesPlayListPanelLayout.rowHeights = new int[] {7};
				botoesPlayListPanelLayout.columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};
				botoesPlayListPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
				botoesPlayListPanel.setLayout(botoesPlayListPanelLayout);
				{
					removerDaListaButton = new JButton();
					botoesPlayListPanel.add(removerDaListaButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					removerDaListaButton.setIcon(new ImageIcon(getClass().getResource("/figuras/media/document_delete.png")));
					removerDaListaButton.setMargin(new java.awt.Insets(3, 4, 2, 3));
					removerDaListaButton.setFocusable(false);
					removerDaListaButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							int[] indices = playListList.getSelectedIndices();
							for (int i = indices.length - 1; i >= 0; i--) {
								int indice = indices[i];
								if (indice != indiceAtual && indice >= 0 && indice < playlist.getItens().size()) {
									playlist.getItens().remove(indice);
									try {
										Fachada.alterarPlaylist(playlist);
									} catch (DataException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if (indice < indiceAtual) {
										indiceAtual--;
									}
								}
							}
							atualizarListaDeMusicas();							
						}
					});
				}
				{
					acimaButton = new JButton();
					botoesPlayListPanel.add(acimaButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					acimaButton.setIcon(new ImageIcon(getClass().getResource("/figuras/media/arrow_up_green.png")));
					acimaButton.setMargin(new java.awt.Insets(3, 4, 2, 3));
					acimaButton.setFocusable(false);
					acimaButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							int indice = playListList.getSelectedIndex();
							if (indice > 0 && indice <= playlist.getItens().size() - 1) {
								Musica m = playlist.getItens().remove(indice);
								playlist.getItens().add(indice - 1, m);
								try {
									Fachada.alterarPlaylist(playlist);
								} catch (DataException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (indice == indiceAtual + 1) indiceAtual++;
								else if (indice == indiceAtual) indiceAtual--;
								atualizarListaDeMusicas();
								playListList.setSelectedIndex(indice - 1);	
							}							
						}
					});
				}
				{
					abaixoButton = new JButton();
					botoesPlayListPanel.add(abaixoButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					abaixoButton.setIcon(new ImageIcon(getClass().getResource("/figuras/media/arrow_down_green.png")));
					abaixoButton.setMargin(new java.awt.Insets(3, 4, 2, 3));
					abaixoButton.setFocusable(false);
					abaixoButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							int indice = playListList.getSelectedIndex();
							if (indice < playlist.getItens().size() - 1 && indice >= 0) {
								Musica m = playlist.getItens().remove(indice);
								playlist.getItens().add(indice + 1, m);
								try {
									Fachada.alterarPlaylist(playlist);
								} catch (DataException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (indice == indiceAtual - 1) indiceAtual--;
								else if (indice == indiceAtual) indiceAtual++;
								atualizarListaDeMusicas();
								playListList.setSelectedIndex(indice + 1);	
							}							
						}
					});
				}
				{
					duracaoPlaylistLabel = new JLabel();
					botoesPlayListPanel.add(duracaoPlaylistLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 32, 0, 0), 0, 0));
					duracaoPlaylistLabel.setText("Tempo Total: 00:00");
				}
				{
					configuracoesButton = new JButton();
					botoesPlayListPanel.add(configuracoesButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 103), 0, 0));
					configuracoesButton.setIcon(new ImageIcon(getClass().getResource("/figuras/gear_preferences.png")));
					configuracoesButton.setFocusable(false);
					configuracoesButton.setMargin(new java.awt.Insets(3, 4, 2, 3));
					configuracoesButton.setToolTipText("Configurações");
					configuracoesButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							abrirConfiguracoesPlayer();
						}
					});
				}
				{
					buttonSalvarPlaylist = new JButton();
					buttonSalvarPlaylist.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/disk_blue16x16.png")));
					buttonSalvarPlaylist.setMargin(new java.awt.Insets(3, 4, 2, 3));
					botoesPlayListPanel.add(buttonSalvarPlaylist, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 103), 0, 0));
					buttonSalvarPlaylist.setSize(27, 25);
					buttonSalvarPlaylist.setFocusable(false);
					buttonSalvarPlaylist.setToolTipText("Salvar Playlist Atual");
					buttonSalvarPlaylist.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							salvarPlaylistAtual();
						}
					});
				}
				{
					buttonExcluirPlaylist = new JButton();
					botoesPlayListPanel.add(buttonExcluirPlaylist, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 49), 0, 0));
					buttonExcluirPlaylist.setIcon(new ImageIcon(getClass().getResource("/figuras/icones/garbage.png")));
					buttonExcluirPlaylist.setMargin(new java.awt.Insets(3, 4, 2, 3));
					buttonExcluirPlaylist.setFocusable(false);
					buttonExcluirPlaylist.setToolTipText("Remover Playlists Salvas");
					buttonExcluirPlaylist.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							removerPlaylist();
						}
					});
				}
				{
					buttonCarregarPlaylist = new JButton();
					botoesPlayListPanel.add(buttonCarregarPlaylist, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 56, 0, 0), 0, 0));
					buttonCarregarPlaylist.setText("Carregar");
					buttonCarregarPlaylist.setFocusable(false);
					buttonCarregarPlaylist.setToolTipText("Carregar Playlist Salva anteriormente");
					buttonCarregarPlaylist.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							carregarPlaylist();
						}

					});
				}
				{
					buttonExportarPlaylist = new JButton();
					botoesPlayListPanel.add(buttonExportarPlaylist, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					buttonExportarPlaylist.setText("Exportar");
					buttonExportarPlaylist.setFocusable(false);
					buttonExportarPlaylist.setEnabled(false);
					buttonExportarPlaylist.setToolTipText("Exportar Playlist Atual Como uma Coleção");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JSlider getVolumeSlider() {
		return volumeSlider;
	}
	
	public JSlider getProgressoSlider() {
		return progressoSlider;
	}
	
	public JButton getMuteButton() {
		return muteButton;		
	}
	
	public void finalizouMusica() {
		//$hide>>$
		if (indiceAtual == playlist.getItens().size() - 1) {
			terminouMusica = true;	
			stop();
		} else {
			// aguardar o intervalo
			intervalo = true;
			for (int i = intervaloEntreMusicas; i > 0; i--) {
				try {
					if (!intervalo) return;
					duracaoMusicaAtualLabel.setText(Util.formataDuracao(i));
					Thread.sleep(1000);					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			intervalo = false;			
			tocar(indiceAtual + 1);
		}
		//$hide<<$
	}
	
	public void setProgressPosition(int pos) {
		//$hide>>$
		if (!mousePressionado) {
			eventoAutomatico = true;
			getProgressoSlider().setValue(pos);
			eventoAutomatico = false;
		}		
		int segundos = (int) ((long) pos * musicaAtual.getDuracao()) / progressoSlider.getMaximum();
		duracaoMusicaAtualLabel.setText(Util.formataDuracao(segundos) + "/" + Util.formataDuracao(musicaAtual.getDuracao()));
		//$hide<<$
	}
	
	// muda o ícone do muteLabel para o com o volume normal (sem mute) 
	public void setSoundOnIcon() {
		getMuteButton().setIcon(MUTE_LABEL_SOUND_ON);
	}
	
	public void setSoundOffIcon() {
		getMuteButton().setIcon(MUTE_LABEL_SOUND_OFF);
	}
	
	public void setPosition(int pos) {
		if (!mousePressionado) {
			eventoAutomatico = true;
			getProgressoSlider().setValue(pos);
			eventoAutomatico = false;
		}		
	}
	
	public void inicializar() {
		playPauseButton.setIcon(PAUSE_LABEL_PAUSE);
		terminouMusica = false;
	}
	
	public void open(List<Musica> musicas) {
		//$hide>>$
		this.playlist.getItens().clear();
		
		if (musicas != null && musicas.size() > 0) {
			for (Musica m: musicas) {
				this.playlist.getItens().add(m);
			}
			try {
				Fachada.alterarPlaylist(playlist);
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tocar(0);
		} else {
			indiceAtual = -1;
		}
		
		atualizarListaDeMusicas();
		//$hide<<$
	}
	
	private void tocar(int indice) {
		//$hide>>$	
		stop();
		
		if (indice >= playlist.getItens().size() || indice < 0) {
			return;
		}
		
		Musica m = playlist.getItens().get(indice);
		indiceAtual = indice;
		playListList.updateUI();
		String nomeArquivo = null;
		try {			
			musicaAtual = m;
			nomeMusicaAtualLabel.setText(m.getNome());
			duracaoMusicaAtualLabel.setText("0:00/" + Util.formataDuracao(m.getDuracao()));
			try {
				nomeArquivo = Util.salvarArquivoDiretorioTemporario(new File(BDUtil.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + m.getNomeArquivo()));
				arquivosTemporarios.add(nomeArquivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (nomeArquivo != null) {
				player.open(new File(nomeArquivo));
				player.play();	
			} else {
				JOptionPane.showMessageDialog(this, "Erro ao tentar tocar a música.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DiretorioBaseInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
		
	}
	
	private void apagarArquivosTemporarios() {
		//$hide>>$
		for (String arquivo: arquivosTemporarios) {
			File f = new File(arquivo);
			f.delete();
		}
		//$hide<<$
	}

	private void atualizarListaDeMusicas() {
		//$hide>>$
		String[] nomesMusicas = new String[playlist.getItens().size() + 1];
		int tempoTotal = 0;
		for (int i = 0; i < playlist.getItens().size(); i++) {
			nomesMusicas[i] = playlist.getItens().get(i).getNome() + " (" + Util.formataDuracao(playlist.getItens().get(i).getDuracao()) + ")";
			tempoTotal += playlist.getItens().get(i).getDuracao();
		}
		nomesMusicas[playlist.getItens().size()] = "";
		ListModel playListListModel = new DefaultComboBoxModel(nomesMusicas);
		playListList.setModel(playListListModel);
		
		duracaoPlaylistLabel.setText("Tempo Total: " + Util.formataDuracao(tempoTotal));
		//$hide<<$
	}
	
	private void stop() {
		//$hide>>$
		intervalo = false;
		try {
			player.stop();
			playPauseButton.setIcon(PAUSE_LABEL_PLAY);
			progressoSlider.setValue(0);
			if (indiceAtual >= 0 && indiceAtual < playlist.getItens().size()) {
				duracaoMusicaAtualLabel.setText("0:00/" + Util.formataDuracao(indiceAtual > 0 ?  playlist.getItens().get(indiceAtual).getDuracao() : 0));	
			} else {
				duracaoMusicaAtualLabel.setText("0:00/0:00");
			}
			
			apagarArquivosTemporarios();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//$hide<<$
	}

	public void adicionarMusicas(List<Musica> musicas) {
		//$hide>>$
		for (Musica m: musicas) {
			this.playlist.getItens().add(m);
		}
		try {
			Fachada.alterarPlaylist(playlist);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		atualizarListaDeMusicas();
		//$hide<<$
	}
	
	private void proximaMusica() {
		//$hide>>$
		if (indiceAtual != playlist.getItens().size() - 1) {
			tocar(indiceAtual + 1);
		}
		//$hide<<$
	}
	
	private void musicaAnterior() {
		//$hide>>$
		if (indiceAtual != 0) {
			tocar(indiceAtual - 1);
		}
		//$hide<<$
	}
	
	public int getIndiceAtual() {
		return indiceAtual;
	}

	public void parar() {
		stop();
	}
	
	private void abrirConfiguracoesPlayer() {
		//$hide>>$
		String intervaloString = "Intervalo Entre as Músicas";
		JTextField intervaloTextField = new JTextField();
		intervaloTextField.setText("" + intervaloEntreMusicas);
		
		Object[] itens = {
			intervaloString, intervaloTextField
		};
		
		if (JOptionPane.showConfirmDialog(this, itens, "Configurações", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			intervaloEntreMusicas = Integer.parseInt(intervaloTextField.getText());
			try {
				BDUtil.salvarConfiguracao(Constantes.CONFIGURACAO_INTERVALO_ENTRE_MUSICAS, "" + intervaloEntreMusicas);
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//$hide<<$
	}
	
	private void salvarPlaylistAtual() {
		SalvarPlaylistJDialog dialog = new SalvarPlaylistJDialog(playerFrame);
		dialog.setPlaylistSalvar(playlist);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	//$hide>>$
	public void setPlayerFrame(PlayerFrame playerFrame) {
		this.playerFrame = playerFrame;
	}
	//$hide<<$
	
	
	private void carregarPlaylist() {
		//$hide>>$
		CarregarPlaylistDialog dialog = new CarregarPlaylistDialog(playerFrame, false);
		dialog.setModal(true);
		dialog.setVisible(true);
		String nome = dialog.getPlaylistEscolhida();
		
		if (nome != null) {
			try {
				Playlist p = Fachada.getPlaylist(nome);
				open(p.getItens());
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//$hide<<$
	}
	
	private void removerPlaylist() {
		//$hide>>$
		CarregarPlaylistDialog dialog = new CarregarPlaylistDialog(playerFrame, true);
		
		dialog.setModal(true);
		dialog.setVisible(true);
		String nome = dialog.getPlaylistEscolhida();
		
		if (nome != null) {
			try {
				Playlist p = Fachada.getPlaylist(nome);
				Fachada.removerPlaylist(p);
				JOptionPane.showMessageDialog(this, "Playlist Removida.", "Playlist Removida", JOptionPane.ERROR_MESSAGE);
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//$hide<<$
	}
}

