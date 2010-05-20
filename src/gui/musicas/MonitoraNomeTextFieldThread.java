package gui.musicas;

import javax.swing.JPanel;

public class MonitoraNomeTextFieldThread extends Thread {
	
	// private CadastrarMusicasPanel tela;
	JPanel tela;
	
	// indica quando o thread deve encerrar
	private boolean finalizar;
	
	public MonitoraNomeTextFieldThread(JPanel tela) {
		this.tela = tela;
		finalizar = false;
	}
	
	public void run() {
		while(!finalizar) {
			try {
				finalizar = true;
				sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (tela instanceof CadastrarMusicasPanel) {
			CadastrarMusicasPanel telaDeCadastro = (CadastrarMusicasPanel) tela;
			telaDeCadastro.monitoraNomeTextFieldThreadFinalizou();
		} else {
			if (tela instanceof EditarMusicasPanel) {
				EditarMusicasPanel telaDeEdicao = (EditarMusicasPanel) tela;
				telaDeEdicao.monitoraNomeTextFieldThreadFinalizou();
			}
		}
		
	}
	
	public void naoFinalizarAinda() {
		finalizar = false;
	}
}
