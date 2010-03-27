package gui.musicas;

import java.io.File;

// para o caso de o usu�rio escolher cadastrar v�rias m�sicas de uma vez
// esse thread fica aguardando at� a tela ficar dispon�vel para editar
// uma nova m�sica (ou seja, at� que uma m�sica tenha sido cadastrada)
// para, s� ent�o, abrir a pr�xima para fazer o cadastro

public class MonitoraCadastroDeMusicasThread extends Thread {
	private CadastrarMusicasPanel tela = null;
	private File arquivos[] = null;
	
	public MonitoraCadastroDeMusicasThread(CadastrarMusicasPanel tela, File arquivos[]) {
		this.tela = tela;
		this.arquivos = arquivos;
	}
	
	public void run() {
		tela.getAbrirArquivoButton().setEnabled(false);
		
		for (File f: arquivos) {
			// espera at� que a tela esteja dispon�vel para abrir o arquivo
			while (!tela.isDisponivelParaAbrirArquivo()) {
				try {
					sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			tela.setDisponivelParaAbrirArquivo(false);
			tela.abrirArquivo(f);
		}
		
		tela.setDisponivelParaAbrirArquivo(false);
		tela.getAbrirArquivoButton().setEnabled(true);
	}
}