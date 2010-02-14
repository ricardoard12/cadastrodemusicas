package gui.verificacoes;

import java.util.List;

import util.Util;
import classesbasicas.Musica;
import exceptions.DataException;
import fachada.Fachada;

public class VerificarArquivosThread extends Thread {
	private VerificarArquivosPanel painel = null;
	
	public VerificarArquivosThread(VerificarArquivosPanel painel) {
		this.painel = painel;
	}
	
	public void run() {
		List<Musica> musicas;
		int pro = 0;
		int verificadas = 0;
		
		painel.getLogTextArea().append("Consultando as Músicas no BD\n\n");
		
		/*try {
			sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			musicas = Fachada.listarTodasAsMusicasEmOrdemAlfabetica();
			
			painel.getTotalTextField().setText("" + musicas.size());
			painel.getVerificadasTextField().setText("" + 0);
			
			/*try {
				sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
			for (Musica m: musicas) {
				painel.getMusicaTextField().setText(m.getNome());
				painel.getProblemasEncontradosTextField().setText("" + pro);
				
				painel.getLogTextArea().append("Verificando a Música " + m.getNome() + "\n");
				painel.getLogTextArea().setCaretPosition(painel.getLogTextArea().getText().length());
				painel.getLogTextArea().append("Diretório: " + m.getDiretorio() + "   Arquivo: " + m.getNomeArquivo() + "\n");
				
				/*try {
					sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				if (Util.verificarArquivo(m)) {
					painel.getLogTextArea().append("Resultado: OK\n\n");
				} else {
					painel.getLogTextArea().append("Resultado: ERRO, arquivo não encontrado\n\n");
					pro++;
				}
				
				/*try {
					sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				verificadas++;
				painel.getVerificadasTextField().setText("" + verificadas);
				painel.getLogTextArea().setCaretPosition(painel.getLogTextArea().getText().length());
			}
			painel.getLogTextArea().append("Verificação Concluída.");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
