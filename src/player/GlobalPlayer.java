package player;

import gui.player.PlayerFrame;

public class GlobalPlayer {
	private static PlayerFrame player = new PlayerFrame();
	
	public static PlayerFrame getGlobalPlayer() {
		return player;
	}
}
