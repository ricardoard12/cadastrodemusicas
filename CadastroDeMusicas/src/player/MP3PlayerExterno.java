// player das músicas que tocam no player do sistema (que roda em um frame externo)

package player;

import gui.mediacontroller.MediaControllerInterno;
import gui.player.PlayerPanel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javazoom.jl.decoder.Equalizer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import javazoom.jlgui.player.amp.tag.TagInfo;
import javazoom.jlgui.player.amp.tag.TagInfoFactory;
import javazoom.jlgui.player.amp.util.Config;

public class MP3PlayerExterno extends BasicPlayer implements ChangeListener, MouseListener {
	PlayerPanel controller = null;
	int duracaoDaMedia = -1;
	private static int framesProgresso = 10000;
	private static int frameMin = 0;
	private static int frameAtual = 0;
	private static int processarProgressoFlag = 0;
	File arquivo = null;
	TagInfo taginfo;
	Map properties = null;
	
	boolean mute = false;
	int volume = -1;
	
	public MP3PlayerExterno(PlayerPanel controller) throws JavaLayerException {
		super();
		this.controller = controller;
		addBasicPlayerListener(new BasicPlayerListener() {
			public void stateUpdated(BasicPlayerEvent bpe) {
				System.out.println(bpe);
				if (bpe.getCode() == BasicPlayerEvent.EOM) {
					MP3PlayerExterno.this.controller.finalizouMusica();
				}
			}		
			public void setController(BasicController arg0) {}
			
			public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
				if (processarProgressoFlag++ >= 20) {
					processarProgressoFlag = 0;
					int ftemp = (int) (long) microseconds / 1000000;
					ftemp = (ftemp * framesProgresso) / duracaoDaMedia; 
					if (ftemp != frameAtual) {
						frameAtual = ftemp;
						MP3PlayerExterno.this.controller.setProgressPosition(frameMin + ftemp);	
					}
				}
				
			}
			
			public void opened(Object arg0, Map properties) {
		        MP3PlayerExterno.this.properties = properties;
		        
		        Map props = ((javazoom.spi.PropertiesContainer) MP3PlayerExterno.this.m_audioInputStream).properties();
		        
		        float[] eq = (float []) props.get("mp3.equalizer");
		        System.out.println(eq.toString());
		        
		        for (int i = 0; i < eq.length; i++) {
		        	eq[i] = i < 8 ? (float) -1.0 : (float) 1.0;
		        }
			}		
		});
		
		controller.getVolumeSlider().addChangeListener(this);
		controller.getMuteButton().addMouseListener(this);
	}
	
	public void setMute(boolean m) {
		System.out.println("Volume Atual para Mute: " + volume);
		
		try {
			if (m) {
				setGain(getMinimumGain());
				controller.setSoundOffIcon();
			} else {
				setGain((double) volume / MediaControllerInterno.VOLUME_MAX);
				controller.setSoundOnIcon();
			}

			mute = m;
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void open(File arquivo) throws BasicPlayerException {
		super.open(arquivo);
		
		this.arquivo = arquivo;
		
		TagInfoFactory factory = TagInfoFactory.getInstance();
        taginfo = factory.getTagInfo(arquivo);
               
        duracaoDaMedia = (int) taginfo.getPlayTime();
        System.out.println("Duracao: " + taginfo.getPlayTime());     	
        
        frameAtual = 0;        
        frameMin = 0; 
        
        controller.setPosition(frameAtual);
        controller.inicializar();
	}
	
	@Override
	public void play() throws BasicPlayerException {
		super.play();
        if (mute) setGain(getMinimumGain());
        else if (volume != -1) {
        	setGain((double) volume / MediaControllerInterno.VOLUME_MAX);
        } else {
        	volume = MediaControllerInterno.VOLUME_MAX / 2;
        	setGain((double) volume / MediaControllerInterno.VOLUME_MAX);
        	controller.getVolumeSlider().setValue(volume);
        	controller.setSoundOnIcon();
        }
	}
	
	public Component getController() {
		return controller;
	}

	public void irParaPosicao(int value) {
		long bytes = Long.parseLong(properties.get("audio.length.bytes").toString());
		double rate = (double) value / framesProgresso;
		bytes = Math.round(bytes * rate);
		frameMin = value;

		if (getStatus() == STOPPED) {
			try {
				super.open(arquivo);
				controller.inicializar();
				seek(bytes);
				play();
				atualizarVolume();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				seek(bytes);
				atualizarVolume();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == controller.getVolumeSlider()) {
			atualizarVolume();
		}
	}

	private void atualizarVolume() {
		// processar mudança de volume			
		volume = controller.getVolumeSlider().getValue();
		System.out.println("Volume Atual: " + volume);
		
		if (mute) return;
		double gain = (double) volume / controller.getVolumeSlider().getMaximum();

		try {
			setGain(gain);
		} catch (BasicPlayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == controller.getMuteButton()) {	
			setMute(!mute);
		}
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static int getFramesProgresso() {
		return framesProgresso;
	}
}
