package gui.mediacontroller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.plaf.metal.MetalSliderUI;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import player.MP3PlayerInterno;

public class MediaControllerInterno extends JPanel {
	
	public static final int VOLUME_MAX = 100;
	
	private final ImageIcon PAUSE_LABEL_PAUSE = new ImageIcon(getClass().getResource("/figuras/media/media_pause.png"));  //  @jve:decl-index=0:
	private final ImageIcon PAUSE_LABEL_PLAY = new ImageIcon(getClass().getResource("/figuras/media/media_play.png"));
	private final ImageIcon MUTE_LABEL_SOUND_ON = new ImageIcon(getClass().getResource("/figuras/media/sound.png"));
	private final ImageIcon MUTE_LABEL_SOUND_OFF = new ImageIcon(getClass().getResource("/figuras/media/sound-off.png"));

	private static final long serialVersionUID = 1L;
	private JLabel pauseLabel = null;
	private JLabel muteLabel = null;
	private MP3PlayerInterno player = null;
	double gain;
	int frames = 0;
	boolean eventoAutomatico = false;
	
	boolean terminouMusica = false;
	
	int posicaoAtualSliderSalva = 0;
	boolean mousePressionado = false;
	
	private JSlider progressoSlider = null;
	private JSlider volumeSlider = null;
	/**
	 * This is the default constructor
	 */
	public MediaControllerInterno(MP3PlayerInterno player, int frames) {
		super();
		this.player = player;
		gain = player.getGainValue();
		this.frames = frames;
		System.out.println(frames);
		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.fill = GridBagConstraints.NONE;
		gridBagConstraints7.gridy = 0;
		gridBagConstraints7.weightx = 0.0;
		gridBagConstraints7.gridwidth = 0;
		gridBagConstraints7.gridheight = 0;
		gridBagConstraints7.gridx = 4;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridx = 1;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 3;
		gridBagConstraints3.gridy = 0;
		muteLabel = new JLabel();
		muteLabel.setFocusable(false);
		muteLabel.setText("");
		muteLabel.setHorizontalAlignment(JLabel.CENTER);
		muteLabel.setIcon(MUTE_LABEL_SOUND_ON);
		muteLabel.setPreferredSize(new Dimension(22, 22));
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints2.ipadx = 0;
		gridBagConstraints2.ipady = 0;
		gridBagConstraints2.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints2.gridy = 0;
		pauseLabel = new JLabel();
		pauseLabel.setFocusable(false);
		pauseLabel.setText("");
		pauseLabel.setHorizontalAlignment(JLabel.CENTER);
		pauseLabel.setIcon(PAUSE_LABEL_PAUSE);
		pauseLabel.setBackground(new Color(153, 153, 153));
		pauseLabel.setPreferredSize(new Dimension(22, 22));
		pauseLabel.addMouseListener(new java.awt.event.MouseAdapter() {   
			public void mousePressed(java.awt.event.MouseEvent e) {    
				System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
			}
			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {
					if (player.getStatus() == BasicPlayer.PAUSED) {
						player.resume();
						pauseLabel.setIcon(PAUSE_LABEL_PAUSE);
					}
					else if (player.getStatus() == BasicPlayer.PLAYING) {
						player.pause();
						pauseLabel.setIcon(PAUSE_LABEL_PLAY);
					} else {
						player.irParaPosicao(getProgressoSlider().getValue());
					}						
				} catch (BasicPlayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		this.setSize(526, 22);
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(204, 204, 204));
		this.add(pauseLabel, gridBagConstraints2);
		this.add(muteLabel, gridBagConstraints3);
		this.add(getProgressoSlider(), gridBagConstraints);
		this.add(getVolumeSlider(), gridBagConstraints7);
	}

	/**
	 * This method initializes progressoSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getProgressoSlider() {
		if (progressoSlider == null) {
			progressoSlider = new JSlider(0, frames);
			progressoSlider.setPreferredSize(new Dimension(200, 22));
			progressoSlider.setBackground(new Color(204, 204, 204));
			progressoSlider.setFocusable(false);
			progressoSlider.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if (!eventoAutomatico) {
						// System.out.println(progressoSlider.getValue());
					}
				}
			});
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
		}
		return progressoSlider;
	}

	/**
	 * This method initializes volumeSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	public JSlider getVolumeSlider() {
		if (volumeSlider == null) {
			volumeSlider = new JSlider(0, VOLUME_MAX);
			volumeSlider.setPreferredSize(new Dimension(80, 22));
			volumeSlider.setBackground(new Color(204, 204, 204));
			volumeSlider.setUI(new BasicSliderUI(volumeSlider) {
			    protected void scrollDueToClickInTrack(int direction) {
			        // this is the default behaviour, let's comment that out
			        // scrollByBlock(direction);
			        

			        int value = volumeSlider.getValue(); 

			        if (volumeSlider.getOrientation() == JSlider.HORIZONTAL) {
			            value = this.valueForXPosition(volumeSlider.getMousePosition().x);
			        } else if (volumeSlider.getOrientation() == JSlider.VERTICAL) {
			            value = this.valueForYPosition(volumeSlider.getMousePosition().y);
			        }
			        volumeSlider.setValue(value);
			    }
			});
		}
		return volumeSlider;
	}
	
	public void setPosition(int pos) {
		if (!mousePressionado) {
			eventoAutomatico = true;
			getProgressoSlider().setValue(pos);
			eventoAutomatico = false;
		}		
	}
	
	public void finalizouMusica() {
		pauseLabel.setIcon(PAUSE_LABEL_PLAY);
		terminouMusica = true;
	}
	
	public void inicializar() {
		pauseLabel.setIcon(PAUSE_LABEL_PAUSE);
		terminouMusica = false;
	}

	public JLabel getMuteLabel() {
		return muteLabel;
	}
	
	// muda o ícone do muteLabel para o com o volume normal (sem mute) 
	public void setSoundOnIcon() {
		getMuteLabel().setIcon(MUTE_LABEL_SOUND_ON);
	}
	
	public void setSoundOffIcon() {
		getMuteLabel().setIcon(MUTE_LABEL_SOUND_OFF);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
