package util;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import player.MP3PlayerInterno;
import classesbasicas.Musica;

public class GlobalPlayer {
	private static MP3PlayerInterno player = null;
	private static File tempFile = null;
	
	public static Component getControle() {
		return player.getController();
	}

	public static MP3PlayerInterno getPlayer() {
		return player;
	}

	public static void setPlayer(MP3PlayerInterno player) {
		GlobalPlayer.player = player;
	}
	
	public static void play(String nomeArquivo, boolean criarArquivoTemp) {	
		stop();
		
		try {
			if (player == null) {
				player = new MP3PlayerInterno();
			}
			
			if (criarArquivoTemp)
			{
				tempFile = File.createTempFile("colec", ".mp3");
				File arquivo = new File(nomeArquivo);			
				Util.copyFile(arquivo.getPath(), tempFile.getPath());
				
		     	player.open(tempFile);
			} else {
				player.open(new File(nomeArquivo));
			}

	     	player.play();
	     	player.getController().setVisible(true);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void play(Musica m) {	
		stop();
		
		try {
			if (player == null) {
				player = new MP3PlayerInterno();
			}
			
	     	player.open(new File(Util.getCaminhoArquivoTempMusica(m)));
	     	player.play();
	     	player.getController().setVisible(true);
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void stop() {		
		if (GlobalPlayer.getPlayer() != null) {						
			GlobalPlayer.getControle().setVisible(false);

			try {
				GlobalPlayer.getPlayer().stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}							
			
			if (tempFile != null) {
				tempFile.delete();
				tempFile = null;
			}			
			
			System.gc();
		}
	}
}
