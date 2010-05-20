package gui.musicas;

import java.io.File;

// para o caso de o usuário escolher cadastrar várias músicas de uma vez
// esse thread fica aguardando até a tela ficar disponível para editar
// uma nova música (ou seja, até que uma música tenha sido cadastrada)
// para, só então, abrir a próxima para fazer o cadastro

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
			// espera até que a tela esteja disponível para abrir o arquivo
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