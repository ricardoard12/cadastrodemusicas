package gui.sincronizacao;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import util.Util;
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
	private JButton buttonAplicarAlteracoes;
	private JButton buttonAdicionarDado;
	private JButton buttonIgnorar;
	private JPanel panelBotoes;
	private JTextArea textAreaObservacoes;
	private JTextArea textAreaLog;
	private List<Log> logs = null;
	private File diretorioArquivos = null;
	private int indiceAtual = 0;

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
				thisLayout.rowWeights = new double[] {0.0, 0.9, 0.0};
				thisLayout.rowHeights = new int[] {7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7};
				getContentPane().setLayout(thisLayout);
				this.setPreferredSize(new java.awt.Dimension(1031, 487));
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
					getContentPane().add(textAreaLog, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 6, 6, 0), 0, 0));
				}
				{
					textAreaObservacoes = new JTextArea();
					getContentPane().add(textAreaObservacoes, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 6, 6, 6), 0, 0));
				}
				{
					panelBotoes = new JPanel();
					getContentPane().add(panelBotoes, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					{
						buttonIgnorar = new JButton();
						panelBotoes.add(buttonIgnorar);
						buttonIgnorar.setText("Ignorar");
						buttonIgnorar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								indiceAtual++;
								processarEntradaLog();
							}
						});
					}
					{
						buttonAdicionarDado = new JButton();
						panelBotoes.add(buttonAdicionarDado);
						buttonAdicionarDado.setText("Adicionar Dado");
						buttonAdicionarDado.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								adicionarDado();
								indiceAtual++;
								processarEntradaLog();
							}
						});
					}
					{
						buttonAplicarAlteracoes = new JButton();
						panelBotoes.add(buttonAplicarAlteracoes);
						buttonAplicarAlteracoes.setText("Aplicar Alterações");
					}
				}
			}
			pack();
			this.setSize(1031, 487);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sincronizar(List<Log> logs, File diretorioArquivos) {
		this.logs = logs;
		this.diretorioArquivos = diretorioArquivos;
		indiceAtual = 0;
		
		setVisible(true);
		
		// iniciando a sincronização dos dados
		processarEntradaLog();
	}
	
	private void finalizarProcessamento() {
		dispose();		
	}
	
	private void processarEntradaLog() {
		if (indiceAtual >= logs.size()) {
			finalizarProcessamento();
			return;
		}
		
		Log l = logs.get(indiceAtual);
		
		textAreaLog.setText("");
		textAreaObservacoes.setText("");
		
		String logString = "Log " + (indiceAtual + 1) + "/" + logs.size() + "\n";
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
	
	private void adicionarDado() {
		Log l = logs.get(indiceAtual);
		
		if (l.getObjeto() instanceof Musica) {
			Musica m = (Musica) l.getObjeto();
			File arquivoCopiar = new File(diretorioArquivos.getPath() + File.separator + m.getChaveUnica());
			if (!arquivoCopiar.exists()) {
				JOptionPane.showMessageDialog(this, "Arquivo MP3 da Música não foi encontrado.", "Arquivo não encontrado", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// verificando se os assuntos da música estão cadastrados no sistema
			if (m.getAssuntos() != null && m.getAssuntos().size() > 0) {
				List<Assunto> assuntos = m.getAssuntos();
				List<Assunto> assuntosSalvarMusica = new ArrayList<Assunto>();
				for (Assunto aLog: assuntos) {
					try {
						List<Assunto> assuntosSistema = Fachada.listarAssuntos(aLog.getAssunto());
						if (assuntosSistema != null && assuntosSistema.size() > 0) {
							assuntosSalvarMusica.add(assuntosSistema.get(0));
						} else {
							Assunto novoAssunto = new Assunto();
							novoAssunto.setAssunto(aLog.getAssunto());
							Fachada.cadastrarAssunto(novoAssunto);
							assuntosSalvarMusica.add(novoAssunto);
						}
					} catch (DataException e) {
						e.printStackTrace();
					}
				}
				m.setAssuntos(assuntosSalvarMusica);
			}
			
			// verificando se o ritmo da musica ja esta cadastrado no sistema
			if (m.getTipo() != null) {
				Tipo tipoMusica = m.getTipo();
				try {
					List<Tipo> tiposSistema = Fachada.listarTipos(tipoMusica.getTipo());
					if (tiposSistema != null && tiposSistema.size() > 0) {
						m.setTipo(tiposSistema.get(0));
					} else {
						Tipo tipo = new Tipo();
						tipo.setTipo(tipoMusica.getTipo());
						Fachada.cadastrarTipo(tipo);
						m.setTipo(tipo);
					}
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// verificando se os cantores da musica ja estao cadastrados no sistema
			if (m.getCantores() != null && m.getCantores().size() > 0) {
				List<Cantor> cantoresMusica = m.getCantores();
				List<Cantor> cantoresSalvarMusica = new ArrayList<Cantor>();
				
				for (Cantor c: cantoresMusica) {
					try {
						List<Cantor> cantoresSistema = Fachada.listarCantoresPorNome(c.getNome());
						if (cantoresSistema != null && cantoresSistema.size() > 0) {
							cantoresSalvarMusica.add(cantoresSistema.get(0));
						} else {
							Cantor novoCantor = new Cantor();
							novoCantor.setNome(c.getNome());
							novoCantor.setNomeSemEspacos(c.getNomeSemEspacos());
							Fachada.cadastrarCantor(novoCantor);
							cantoresSalvarMusica.add(novoCantor);
						}
					} catch (DataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				m.setCantores(cantoresSalvarMusica);
			}
			
			// copiando o arquivo MP3 para a pasta de destino
			String diretorio = Util.getDiretorioProximoArquivo();
			m.setDiretorio(diretorio);
			File teste = new File(Util.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + m.getNomeArquivo());
			int i = 0;
			while (teste.exists()) {
				i++;
				String nomeArquivo = m.getNome() + " #" + i;
				if (m.getCantores() != null && m.getCantores().size() > 0) {
					nomeArquivo = nomeArquivo + " - " + m.getCantores().get(0).getNomeSemEspacos();
				}
				nomeArquivo += ".mp3";
				m.setNomeArquivo(nomeArquivo);
				teste = new File(Util.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + nomeArquivo);
			}
			
			if (Util.copyFile(arquivoCopiar.getAbsolutePath(), teste.getAbsolutePath())) {
				try {
					Fachada.cadastrarMusica(m);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Erro ao Copiar o Arquivo MP3 da Música para o Destino.", "Erro ao Copiar Arquivo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
