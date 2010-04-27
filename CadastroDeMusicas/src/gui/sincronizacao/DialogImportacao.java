package gui.sincronizacao;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import util.Util;
import bd.BDUtil;

import classesbasicas.Assunto;
import classesbasicas.Cantor;
import classesbasicas.Log;
import classesbasicas.Musica;
import classesbasicas.Tipo;
import classesbasicas.Log.TipoOperacao;
import exceptions.DataException;
import fachada.Fachada;


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
public class DialogImportacao extends javax.swing.JDialog {
	private JLabel LabelLog;
	private JLabel LabelObservacoes;
	private JTextArea textAreaObservacoes;
	private JTextArea textAreaLog;

	/**
	* Auto-generated main method to display this JDialog
	*/
	
	public DialogImportacao(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.0, 0.9};
				thisLayout.rowHeights = new int[] {7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7};
				getContentPane().setLayout(thisLayout);
				this.setPreferredSize(new java.awt.Dimension(545, 373));
				this.setTitle("Sincronização de Dados");
				{
					LabelLog = new JLabel();
					getContentPane().add(LabelLog, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(2, 6, 0, 2), 0, 0));
					LabelLog.setText("Dados do Log");
				}
				{
					LabelObservacoes = new JLabel();
					getContentPane().add(LabelObservacoes, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(2, 6, 0, 0), 0, 0));
					LabelObservacoes.setText("Observações");
				}
				{
					textAreaLog = new JTextArea();
					getContentPane().add(textAreaLog, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(6, 6, 6, 0), 0, 0));
				}
				{
					textAreaObservacoes = new JTextArea();
					getContentPane().add(textAreaObservacoes, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(6, 6, 6, 6), 0, 0));
				}
			}
			pack();
			this.setSize(545, 373);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sincronizar(List<Log> logs, File diretorioArquivos) {
		setVisible(true);
		
		// iniciando a sincronização dos dados
		for (int i = 0; i < logs.size(); i++) {
			Log l = logs.get(i);
			
			textAreaLog.setText("");
			textAreaObservacoes.setText("");
			
			String logString = "Log " + (i + 1) + "/" + logs.size() + "\n";
			logString += "Operação: " + l.getNomeOperacao() + "(" + l.getClasseObjeto() + ")\n\n";
			
			if (l.getTipoOperacao() == TipoOperacao.CADASTRO) {
				if (l.getObjeto() instanceof Musica) {
					// preenchendo a área de texto do Log
					Musica m = (Musica) l.getObjeto();
					logString += m.getDescricaoCompleta();
					textAreaLog.setText(logString);
					
					// preenchendo a área de texto dos dados locais (Observações)
					// procurando uma música com o mesmo nome da cadastrada
					try {
						List<Musica> musicasLocais = Fachada.listarMusicasPorDiversos(m.getNome(), null, null, null, null, null, null, null, -1);
						String textoObservacoes = "";
						if (musicasLocais != null && musicasLocais.size() > 0) {
							 textoObservacoes += "Foram encontradas " + musicasLocais.size() + " músicas Locais com o mesmo nome da Música contida no Log.\n\n"; 
							for (Musica musica: musicasLocais) {
								textoObservacoes += musica.getDescricaoCompleta(); 
							}
						} else {
							textoObservacoes += "Não foram encontradas músicas Locais com o mesmo nome da Música contida no Log.";
						}
						textAreaObservacoes.setText(textoObservacoes);
					} catch (DataException e) {
						e.printStackTrace();
					}
					
				} else if (l.getObjeto() instanceof Cantor) {
					logString += "Log Ignorado.";
				} else if (l.getObjeto() instanceof Tipo) {
					logString += "Log Ignorado.";
				} else if (l.getObjeto() instanceof Assunto) {
					logString += "Log Ignorado.";
				} else {
					logString += "Erro ao exportar Objeto -> Objeto de Tipo insperado.";
				}
			}
		}
		
		dispose();
	}

}
