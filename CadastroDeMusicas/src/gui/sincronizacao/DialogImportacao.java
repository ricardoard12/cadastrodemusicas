package gui.sincronizacao;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
import classesbasicas.Constantes;
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
	private JButton buttonRemover;
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
	
	public DialogImportacao(JFrame frame, List<Log> logs, File diretorioArquivos) {
		super(frame);
		initGUI();
		this.logs = logs;
		this.diretorioArquivos = diretorioArquivos;
		indiceAtual = 0;
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
						buttonIgnorar.setEnabled(false);
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
						buttonAdicionarDado.setEnabled(false);
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
						buttonAplicarAlteracoes.setEnabled(false);
						buttonAplicarAlteracoes.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								aplicarAlteracoes();
								indiceAtual++;
								processarEntradaLog();
							}
						});
					}
					{
						buttonRemover = new JButton();
						panelBotoes.add(buttonRemover);
						buttonRemover.setText("Remover Dado");
						buttonRemover.setEnabled(false);
						buttonRemover.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								removerDado();
								indiceAtual++;
								processarEntradaLog();
							}
						});
					}
				}
			}
			pack();
			this.setSize(1031, 487);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sincronizar() {
		// iniciando a sincronização dos dados
		//$hide>>$
		System.out.println("Iniciando a sincronização.");
		processarEntradaLog();
		//$hide<<$
	}
	
	public void setVisible(boolean b) {
		if (b) sincronizar();
		super.setVisible(b);
	}
	
	private void finalizarProcessamento() {
		dispose();		
	}
	
	private void processarEntradaLog() {
		//$hide>>$
		buttonIgnorar.setEnabled(false);
		buttonAdicionarDado.setEnabled(false);
		buttonAplicarAlteracoes.setEnabled(false);
		buttonRemover.setEnabled(false);

		if (indiceAtual >= logs.size()) {
			finalizarProcessamento();
			return;
		}
		
		Log l = logs.get(indiceAtual);
		
		textAreaLog.setText("");
		textAreaObservacoes.setText("");
		
		String logString = "Log " + (indiceAtual + 1) + "/" + logs.size() + "\n";
		logString += "Operação: " + l.getNomeOperacao() + "(" + l.getClasseObjeto() + ")\n\n";
		
		// Operações de Cadastro
		if (l.getTipoOperacao() == TipoOperacao.CADASTRO) {
			buttonIgnorar.setEnabled(true);
			buttonAdicionarDado.setEnabled(true);
			
			if (l.getObjeto() instanceof Musica) {
				// preenchendo a área de texto do Log
				Musica m = (Musica) l.getObjeto();
				logString += m.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				String textoObservacoes = "";
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando uma música com o mesmo nome da cadastrada
				try {
					Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
					if (musicaLocal != null) {
						textoObservacoes += "Foi encontrada uma música com a mesma Chave Única da Música do Log.\n\n"; 
						textoObservacoes += musicaLocal.getDescricaoCompleta(); 
					}
				} catch (DataException e) {
					e.printStackTrace();
				}
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando uma música com o mesmo nome da cadastrada
				try {
					textoObservacoes = "";
					
					Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
					if (musicaLocal != null) {
						textoObservacoes += "Foi encontrada uma música local com a mesma chave única da contida no log.\n";
						textoObservacoes += musicaLocal.getDescricaoCompleta();
					}
					
					List<Musica> musicasLocais = Fachada.listarMusicasPorDiversos(m.getNome(), null, null, null, null, null, null, null, -1, Constantes.TIPO_ARQUIVO_TODOS);
					if (musicasLocais != null && musicasLocais.size() > 0) {
						textoObservacoes += "\n\nForam encontradas " + musicasLocais.size() + " músicas Locais com o mesmo nome da Música contida no Log.\n\n"; 
						for (Musica musica: musicasLocais) {
							textoObservacoes += musica.getDescricaoCompleta(); 
						}
					} else {
						textoObservacoes += "Não foram encontradas músicas Locais com o mesmo nome da Música contida no Log.";
					}
				} catch (DataException e) {
					e.printStackTrace();
				}
				
				textAreaObservacoes.setText(textoObservacoes);
			} else if (l.getObjeto() instanceof Cantor) {
				logString += "Log Ignorado.";
			} else if (l.getObjeto() instanceof Tipo) {
				logString += "Log Ignorado.";
			} else if (l.getObjeto() instanceof Assunto) {
				logString += "Log Ignorado.";
			} else {
				logString += "Erro ao exportar Objeto -> Objeto de Tipo insperado.";
			}
		} else if (l.getTipoOperacao() == TipoOperacao.ALTERACAO) {
			buttonAplicarAlteracoes.setEnabled(true);
			buttonIgnorar.setEnabled(true);
			
			if (l.getObjeto() instanceof Musica) {
				// preenchendo a área de texto do Log
				Musica m = (Musica) l.getObjeto();
				logString += m.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando a música correspondente
				try {
					String textoObservacoes = "";
					Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
					if (musicaLocal != null) {
						if (musicaLocal.getIdMusica() == m.getIdMusica()) {
							textoObservacoes += "A música Local e a contida no Log coincidem no id e na Chave única.\nPODE SER ALTERADA COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrada uma música com a mesma chave Única da contida no Log.\nMas a música não tem o mesmo id (ATENÇÃO).\n";	
						}
						
						textoObservacoes += musicaLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrada uma música com a mesma chave Única da contida no Log.\nBuscando uma música com o mesmo id.\n";
						musicaLocal = Fachada.getMusica(m.getIdMusica());
						if (musicaLocal != null) {
							textoObservacoes += "MOSTRANDO A MÚSICA COM O MESMO ID.\n";
							textoObservacoes += musicaLocal.getDescricaoCompleta();		
						} else {
							textoObservacoes += "Não foi encontrada a música local correspondente à contida no log.";
						}	
					}					
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Cantor) {
				// preenchendo a área de texto do Log
				Cantor c = (Cantor) l.getObjeto();
				logString += c.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando o cantor correspondente
				try {
					String textoObservacoes = "";
					Cantor cantorLocal = Fachada.getCantorPorChaveUnica(c.getChaveUnica());
					if (cantorLocal != null) {
						if (cantorLocal.getIdCantor() == c.getIdCantor()) {
							textoObservacoes += "O Cantor Local e o contido no Log coincidem no id e na Chave única.\nPODE SER ALTERADO COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrado um Cantor com a mesma chave Única do contido no Log.\nMas o Cantor não tem o mesmo id (ATENÇÃO).\n";	
						}
						textoObservacoes += cantorLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrado um Cantor com a mesma chave Única do contido no Log.\nBuscanco um Cantor com o mesmo id.\n";
						cantorLocal = Fachada.getCantor(c.getIdCantor());
						if (cantorLocal != null) {
							textoObservacoes += "MOSTRANDO O CANTOR COM O MESMO ID.\n";
							textoObservacoes += cantorLocal.getDescricaoCompleta();
						} else {
							textoObservacoes += "Não foi encontrado o Cantor local correspondente ao contido no log.";
						}
					}
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Tipo) {
				Tipo t = (Tipo) l.getObjeto();
				logString += t.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando o tipo correspondente
				try {
					String textoObservacoes = "";
					Tipo tipoLocal = Fachada.getTipoPorChaveUnica(t.getChaveUnica());
					if (tipoLocal != null) {
						if (tipoLocal.getIdTipo() == t.getIdTipo()) {
							textoObservacoes += "O Ritmo Local e o contido no Log coincidem no id e na Chave única.\nPODE SER ALTERADO COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrado um Ritmo com a mesma chave Única do contido no Log.\nMas o Ritmo não tem o mesmo id (ATENÇÃO).\n";	
						}
						textoObservacoes += tipoLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrado um Ritmo com a mesma chave Única do contido no Log.\nBuscanco um Ritmo com o mesmo id.\n";
						tipoLocal = Fachada.getTipo(t.getIdTipo());
						if (tipoLocal != null) {
							textoObservacoes += "MOSTRANDO O RITMO COM O MESMO ID.\n";
							textoObservacoes += tipoLocal.getDescricaoCompleta();
						} else {
							textoObservacoes += "Não foi encontrado o Ritmo local correspondente ao contido no log.";
						}
					}
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Assunto) {
				Assunto a = (Assunto) l.getObjeto();
				logString += a.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando o assunto correspondente
				try {
					String textoObservacoes = "";
					Assunto assuntoLocal = Fachada.getAssuntoPorChaveUnica(a.getChaveUnica());
					if (assuntoLocal != null) {
						if (assuntoLocal.getIdAssunto() == a.getIdAssunto()) {
							textoObservacoes += "O Assunto Local e o contido no Log coincidem no id e na Chave única.\nPODE SER ALTERADO COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrado um Assunto com a mesma chave Única do contido no Log.\nMas o Assunto não tem o mesmo id (ATENÇÃO).\n";	
						}
						textoObservacoes += assuntoLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrado um Assunto com a mesma chave Única do contido no Log.\nBuscanco um Assunto com o mesmo id.\n";
						assuntoLocal = Fachada.getAssunto(a.getIdAssunto());
						if (assuntoLocal != null) {
							textoObservacoes += "MOSTRANDO O ASSUNTO COM O MESMO ID.\n";
							textoObservacoes += assuntoLocal.getDescricaoCompleta();
						} else {
							textoObservacoes += "Não foi encontrado o Assunto local correspondente ao contido no log.";
						}
					}
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
				
			} else {
				logString += "Erro ao exportar Objeto -> Objeto de Tipo insperado.";
				textAreaLog.setText(logString);
			}
		} else if (l.getTipoOperacao() == TipoOperacao.DELECAO) {
			buttonRemover.setEnabled(true);
			buttonIgnorar.setEnabled(true);
			
			if (l.getObjeto() instanceof Musica) {
				// preenchendo a área de texto do Log
				Musica m = (Musica) l.getObjeto();
				logString += m.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando a música correspondente
				try {
					String textoObservacoes = "";
					Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
					if (musicaLocal != null) {
						if (musicaLocal.getIdMusica() == m.getIdMusica()) {
							textoObservacoes += "A música Local e a contida no Log coincidem no id e na Chave única.\nPODE SER ALTERADA COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrada uma música com a mesma chave Única da contida no Log.\nMas a música não tem o mesmo id (ATENÇÃO).\n";	
						}
						
						textoObservacoes += musicaLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrada uma música com a mesma chave Única da contida no Log.\nBuscando uma música com o mesmo id.\n";
						musicaLocal = Fachada.getMusica(m.getIdMusica());
						if (musicaLocal != null) {
							textoObservacoes += "MOSTRANDO A MÚSICA COM O MESMO ID.\n";
							textoObservacoes += musicaLocal.getDescricaoCompleta();		
						} else {
							textoObservacoes += "Não foi encontrada a música local correspondente à contida no log.";
						}	
					}					
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Cantor) {
				// preenchendo a área de texto do Log
				Cantor c = (Cantor) l.getObjeto();
				logString += c.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando o cantor correspondente
				try {
					String textoObservacoes = "";
					Cantor cantorLocal = Fachada.getCantorPorChaveUnica(c.getChaveUnica());
					if (cantorLocal != null) {
						if (cantorLocal.getIdCantor() == c.getIdCantor()) {
							textoObservacoes += "O Cantor Local e o contido no Log coincidem no id e na Chave única.\nPODE SER ALTERADO COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrado um Cantor com a mesma chave Única do contido no Log.\nMas o Cantor não tem o mesmo id (ATENÇÃO).\n";	
						}
						textoObservacoes += cantorLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrado um Cantor com a mesma chave Única do contido no Log.\nBuscanco um Cantor com o mesmo id.\n";
						cantorLocal = Fachada.getCantor(c.getIdCantor());
						if (cantorLocal != null) {
							textoObservacoes += "MOSTRANDO O CANTOR COM O MESMO ID.\n";
							textoObservacoes += cantorLocal.getDescricaoCompleta();
						} else {
							textoObservacoes += "Não foi encontrado o Cantor local correspondente ao contido no log.";
						}
					}
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Tipo) {
				Tipo t = (Tipo) l.getObjeto();
				logString += t.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando o tipo correspondente
				try {
					String textoObservacoes = "";
					Tipo tipoLocal = Fachada.getTipoPorChaveUnica(t.getChaveUnica());
					if (tipoLocal != null) {
						if (tipoLocal.getIdTipo() == t.getIdTipo()) {
							textoObservacoes += "O Ritmo Local e o contido no Log coincidem no id e na Chave única.\nPODE SER ALTERADO COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrado um Ritmo com a mesma chave Única do contido no Log.\nMas o Ritmo não tem o mesmo id (ATENÇÃO).\n";	
						}
						textoObservacoes += tipoLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrado um Ritmo com a mesma chave Única do contido no Log.\nBuscanco um Ritmo com o mesmo id.\n";
						tipoLocal = Fachada.getTipo(t.getIdTipo());
						if (tipoLocal != null) {
							textoObservacoes += "MOSTRANDO O RITMO COM O MESMO ID.\n";
							textoObservacoes += tipoLocal.getDescricaoCompleta();
						} else {
							textoObservacoes += "Não foi encontrado o Ritmo local correspondente ao contido no log.";
						}
					}
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Assunto) {
				Assunto a = (Assunto) l.getObjeto();
				logString += a.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando o assunto correspondente
				try {
					String textoObservacoes = "";
					Assunto assuntoLocal = Fachada.getAssuntoPorChaveUnica(a.getChaveUnica());
					if (assuntoLocal != null) {
						if (assuntoLocal.getIdAssunto() == a.getIdAssunto()) {
							textoObservacoes += "O Assunto Local e o contido no Log coincidem no id e na Chave única.\nPODE SER ALTERADO COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrado um Assunto com a mesma chave Única do contido no Log.\nMas o Assunto não tem o mesmo id (ATENÇÃO).\n";	
						}
						textoObservacoes += assuntoLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrado um Assunto com a mesma chave Única do contido no Log.\nBuscanco um Assunto com o mesmo id.\n";
						assuntoLocal = Fachada.getAssunto(a.getIdAssunto());
						if (assuntoLocal != null) {
							textoObservacoes += "MOSTRANDO O ASSUNTO COM O MESMO ID.\n";
							textoObservacoes += assuntoLocal.getDescricaoCompleta();
						} else {
							textoObservacoes += "Não foi encontrado o Assunto local correspondente ao contido no log.";
						}
					}
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
				
			} else {
				logString += "Erro ao exportar Objeto -> Objeto de Tipo insperado.";
				textAreaLog.setText(logString);
			}
		} else if (l.getTipoOperacao() == TipoOperacao.ADICAO_CANTOR_A_MUSICA) {
			buttonAplicarAlteracoes.setEnabled(true);
			buttonIgnorar.setEnabled(true);
			
			if (l.getObjeto() instanceof Musica) {
				// preenchendo a área de texto do Log
				Musica m = (Musica) l.getObjeto();
				logString += m.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando a música correspondente
				try {
					String textoObservacoes = "";
					Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
					if (musicaLocal != null) {
						if (musicaLocal.getIdMusica() == m.getIdMusica()) {
							textoObservacoes += "A música Local e a contida no Log coincidem no id e na Chave única.\nPODE SER ALTERADA COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrada uma música com a mesma chave Única da contida no Log.\nMas a música não tem o mesmo id (ATENÇÃO).\n";	
						}
						
						textoObservacoes += musicaLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrada uma música com a mesma chave Única da contida no Log.\nBuscando uma música com o mesmo id.\n";
						musicaLocal = Fachada.getMusica(m.getIdMusica());
						if (musicaLocal != null) {
							textoObservacoes += "MOSTRANDO A MÚSICA COM O MESMO ID.\n";
							textoObservacoes += musicaLocal.getDescricaoCompleta();		
						} else {
							textoObservacoes += "Não foi encontrada a música local correspondente à contida no log.";
						}	
					}					
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
			}
		} else if (l.getTipoOperacao() == TipoOperacao.ALTERACAO_ARQUIVO_MUSICA) {
			buttonAplicarAlteracoes.setEnabled(true);
			buttonIgnorar.setEnabled(true);
			
			if (l.getObjeto() instanceof Musica) {
				// preenchendo a área de texto do Log
				Musica m = (Musica) l.getObjeto();
				logString += m.getDescricaoCompleta();
				textAreaLog.setText(logString);
				
				// preenchendo a área de texto dos dados locais (Observações)
				// procurando a música correspondente
				try {
					String textoObservacoes = "";
					Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
					if (musicaLocal != null) {
						if (musicaLocal.getIdMusica() == m.getIdMusica()) {
							textoObservacoes += "A música Local e a contida no Log coincidem no id e na Chave única.\nPODE SER ALTERADA COM SEGURANÇA.\n";
						} else {
							textoObservacoes += "Foi encontrada uma música com a mesma chave Única da contida no Log.\nMas a música não tem o mesmo id (ATENÇÃO).\n";	
						}
						
						textoObservacoes += musicaLocal.getDescricaoCompleta();
					} else {
						textoObservacoes += "NÃO Foi encontrada uma música com a mesma chave Única da contida no Log.\nBuscando uma música com o mesmo id.\n";
						musicaLocal = Fachada.getMusica(m.getIdMusica());
						if (musicaLocal != null) {
							textoObservacoes += "MOSTRANDO A MÚSICA COM O MESMO ID.\n";
							textoObservacoes += musicaLocal.getDescricaoCompleta();		
						} else {
							textoObservacoes += "Não foi encontrada a música local correspondente à contida no log.";
						}	
					}					
					
					textAreaObservacoes.setText(textoObservacoes);
				} catch (DataException e) {
					e.printStackTrace();
				}
			}
		}
		//$hide<<$
	}
	
	private void adicionarDado() {
		//$hide>>$
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
							novoAssunto.setChaveUnica(aLog.getChaveUnica());
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
						tipo.setChaveUnica(tipoMusica.getChaveUnica());
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
							novoCantor.setChaveUnica(c.getChaveUnica());
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
					
					// verificando se a música tem arquivo de capa de disco
					if (m.getNomeArquivoCapa() != null && !m.getNomeArquivoCapa().equals("")) {
						Fachada.alterarCapaDiscoMusica(m, m.getNomeArquivoCapa(), diretorioArquivos.getPath() + File.separator + m.getChaveUnica() + Constantes.STRING_CAPA_DISCO);
					}
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Erro ao Copiar o Arquivo MP3 da Música para o Destino.", "Erro ao Copiar Arquivo", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//$hide<<$
	}
	
	private void aplicarAlteracoes() {
		//$hide>>$
		Log l = logs.get(indiceAtual);
		
		if (l.getTipoOperacao() == TipoOperacao.ALTERACAO) {
			if (l.getObjeto() instanceof Musica) {				
				Musica m = (Musica) l.getObjeto();
				
				try {
					// Procurando a música exata dentro do banco de dados a ser alterada
					// procurando primeiro pela chave única
					Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
					if (musicaLocal == null) {
						musicaLocal = Fachada.getMusica(m.getIdMusica());
						if (musicaLocal == null) {
							JOptionPane.showMessageDialog(this, "Erro. Não foi encontrada nenhuma música Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
							return;	
						} else {
							int e = JOptionPane.showConfirmDialog(this, "Não foi encontrada uma música com a mesma chave única.\nDeseja alterar os dados da música com o mesmo id?\n" + musicaLocal.getDescricaoCompleta() + "\nEssa música deve ser alterada?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
							if (e == JOptionPane.YES_OPTION) {
								m.setIdMusica(musicaLocal.getIdMusica());
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					} else if (musicaLocal.getIdMusica() != m.getIdMusica()) {
						int e = JOptionPane.showConfirmDialog(this, "Os ids das Músicas com a mesma Chave Única não coincidem. Alterar a música com a mesma chave Única (" + musicaLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							m.setIdMusica(musicaLocal.getIdMusica());
						} else {
							// procurando pelo id
							musicaLocal = Fachada.getMusica(m.getIdMusica());
							if (musicaLocal != null) {
								e = JOptionPane.showConfirmDialog(this, "Existe uma música com o mesmo id (e chave única diferente -> " + musicaLocal.getDescricaoCompleta() + "). Essa música deve ser alterada?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
								if (e != JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
									return;	
								}
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// verificando se os assuntos da música estão cadastrados no sistema
				if (m.getAssuntos() != null && m.getAssuntos().size() > 0) {
					List<Assunto> assuntos = m.getAssuntos();
					List<Assunto> assuntosSalvarMusica = new ArrayList<Assunto>();
					for (Assunto aLog: assuntos) {
						try {
							Assunto assuntoLocal = Fachada.getAssuntoPorChaveUnica(aLog.getChaveUnica());
							if (assuntoLocal != null && assuntoLocal.getAssunto().equalsIgnoreCase(aLog.getAssunto())) {
								assuntosSalvarMusica.add(assuntoLocal);
							} else {
								List<Assunto> assuntosSistema = Fachada.listarAssuntos(aLog.getAssunto());
								if (assuntosSistema != null && assuntosSistema.size() > 0) {
									assuntosSalvarMusica.add(assuntosSistema.get(0));
								} else {
									Assunto novoAssunto = new Assunto();
									novoAssunto.setAssunto(aLog.getAssunto());
									novoAssunto.setChaveUnica(aLog.getChaveUnica());
									Fachada.cadastrarAssunto(novoAssunto);
									assuntosSalvarMusica.add(novoAssunto);
								}	
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
						Tipo tipoLocal = Fachada.getTipoPorChaveUnica(tipoMusica.getChaveUnica());
						if (tipoLocal != null && tipoLocal.getTipo().equalsIgnoreCase(tipoMusica.getTipo())) {
							m.setTipo(tipoLocal);
						} else {
							List<Tipo> tiposSistema = Fachada.listarTipos(tipoMusica.getTipo());
							if (tiposSistema != null && tiposSistema.size() > 0) {
								m.setTipo(tiposSistema.get(0));
							} else {
								Tipo tipo = new Tipo();
								tipo.setTipo(tipoMusica.getTipo());
								tipo.setChaveUnica(tipoMusica.getChaveUnica());
								Fachada.cadastrarTipo(tipo);
								m.setTipo(tipo);
							}	
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
							Cantor cantorLocal = Fachada.getCantorPorChaveUnica(c.getChaveUnica());
							if (cantorLocal != null && cantorLocal.getNome().equalsIgnoreCase(c.getNome())) {
								cantoresSalvarMusica.add(cantorLocal);
							} else {
								List<Cantor> cantoresSistema = Fachada.listarCantoresPorNome(c.getNome());
								if (cantoresSistema != null && cantoresSistema.size() > 0) {
									cantoresSalvarMusica.add(cantoresSistema.get(0));
								} else {
									Cantor novoCantor = new Cantor();
									novoCantor.setNome(c.getNome());
									novoCantor.setNomeSemEspacos(c.getNomeSemEspacos());
									novoCantor.setChaveUnica(c.getChaveUnica());
									Fachada.cadastrarCantor(novoCantor);
									cantoresSalvarMusica.add(novoCantor);
								}	
							}
						} catch (DataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					m.setCantores(cantoresSalvarMusica);
				}
								
				try {
					// verificando se o nome do arquivo foi alterado
					Musica musicaAntiga = Fachada.getMusica(m.getIdMusica());
					if (!musicaAntiga.getDiretorio().equals(m.getDiretorio()) || !musicaAntiga.getNomeArquivo().equals(m.getNomeArquivo())) {
						m.setDiretorio(musicaAntiga.getDiretorio());
						File arquivoMusicaAntiga = new File(Util.getDiretorioBase() + File.separator + musicaAntiga.getDiretorio() + File.separator + musicaAntiga.getNomeArquivo());
						File arquivoMusicaNova = new File(Util.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + m.getNomeArquivo());
						int cont = 0;
						while (arquivoMusicaNova.exists()) {
							cont++;
							String nomeArquivoNovo =  m.getNome() + " #" + cont;
							if (m.getCantores() != null && m.getCantores().size() > 0) {
								nomeArquivoNovo += " - " + m.getCantores().get(0).getNomeSemEspacos(); 
							}
							arquivoMusicaNova = new File(Util.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + nomeArquivoNovo);
							m.setNomeArquivo(nomeArquivoNovo);
						}
						arquivoMusicaAntiga.renameTo(arquivoMusicaNova);
					}
					
					Fachada.alterarMusica(m);
					// verificando se a música tem arquivo de capa de disco
					if (m.getNomeArquivoCapa() != null && !m.getNomeArquivoCapa().equals("")) {
						Fachada.alterarCapaDiscoMusica(m, m.getNomeArquivoCapa(), diretorioArquivos.getPath() + File.separator + m.getChaveUnica() + Constantes.STRING_CAPA_DISCO);
					} else {
						Fachada.alterarCapaDiscoMusica(m, null, null);
					}
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Cantor) {
				Cantor c = (Cantor) l.getObjeto();
				try {
					// Procurando o cantor exato do banco de dados que será alterado
					// procurando primeiramente pela chave única
					Cantor cantorLocal = Fachada.getCantorPorChaveUnica(c.getChaveUnica());					
					if (cantorLocal == null) {
						cantorLocal = Fachada.getCantor(c.getIdCantor());
						if (cantorLocal == null) {
							JOptionPane.showMessageDialog(this, "Erro. Não foi encontrado nenhum Cantor Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							int e = JOptionPane.showConfirmDialog(this, "Não foi encontrado um Cantor com a mesma chave única.\nDeseja alterar os dados do Cantor com o mesmo id?\n" + cantorLocal.getDescricaoCompleta() + "\nEsse Cantor deve ser alterado?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
							if (e == JOptionPane.YES_OPTION) {
								c.setIdCantor(cantorLocal.getIdCantor());
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					} else if (cantorLocal.getIdCantor() != c.getIdCantor()) {
						int e = JOptionPane.showConfirmDialog(this, "Os ids dos Cantores com a mesma Chave Única não coincidem. Alterar o Cantor com a mesma chave Única (" + cantorLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							c.setIdCantor(cantorLocal.getIdCantor());
						} else {
							// procurando pelo id
							cantorLocal = Fachada.getCantor(c.getIdCantor());
							if (cantorLocal != null) {
								e = JOptionPane.showConfirmDialog(this, "Existe um Cantor com o mesmo id (e chave única diferente -> " + cantorLocal.getDescricaoCompleta() + "). Esse Cantor deve ser alterado?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
								if (e != JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
									return;	
								}
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					Fachada.alterarCantor(c);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Tipo) {
				Tipo t = (Tipo) l.getObjeto();
				try {
					// Procurando o tipo exato dentro do banco de dados a ser alterado
					// procurando primeiro pela chave única
					Tipo tipoLocal = Fachada.getTipoPorChaveUnica(t.getChaveUnica());
					if (tipoLocal == null) {
						tipoLocal = Fachada.getTipo(t.getIdTipo());
						if (tipoLocal == null) {
							JOptionPane.showMessageDialog(this, "Erro. Não foi encontrado nenhum Ritmo Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							int e = JOptionPane.showConfirmDialog(this, "Não foi encontrado um Ritmo com a mesma chave única.\nDeseja alterar os dados do Ritmo com o mesmo id?\n" + tipoLocal.getDescricaoCompleta() + "\nEsse Ritmo deve ser alterado?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
							if (e == JOptionPane.YES_OPTION) {
								t.setIdTipo(tipoLocal.getIdTipo());
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}	
						}
					} else if (tipoLocal.getIdTipo() != t.getIdTipo()) {
						int e = JOptionPane.showConfirmDialog(this, "Os ids dos Ritmos com a mesma Chave Única não coincidem. Alterar o tipo com a mesma chave Única (" + tipoLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							t.setIdTipo(tipoLocal.getIdTipo());
						} else {
							// procurando pelo id
							tipoLocal = Fachada.getTipo(t.getIdTipo());
							if (tipoLocal != null) {
								e = JOptionPane.showConfirmDialog(this, "Existe um Ritmo com o mesmo id (e chave única diferente -> " + tipoLocal.getDescricaoCompleta() + "). Esse Ritmo deve ser alterado?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
								if (e != JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
									return;	
								}
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				} catch (DataException e1) {
					e1.printStackTrace();
				}
				
				try {
					Fachada.alterarTipo(t);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Assunto) {
				Assunto a = (Assunto) l.getObjeto();
				try {
					// Procurando o Assunto exato dentro do banco de dados a ser alterado
					// procurando primeiro pela chave única
					Assunto assuntoLocal = Fachada.getAssuntoPorChaveUnica(a.getChaveUnica());
					if (assuntoLocal == null) {
						assuntoLocal = Fachada.getAssunto(a.getIdAssunto());
						if (assuntoLocal == null) {
							JOptionPane.showMessageDialog(this, "Erro. Não foi encontrado nenhum Assunto Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							int e = JOptionPane.showConfirmDialog(this, "Não foi encontrado um Assunto com a mesma chave única.\nDeseja alterar os dados do Assunto com o mesmo id?\n" + assuntoLocal.getDescricaoCompleta() + "\nEsse Assunto deve ser alterado?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
							if (e == JOptionPane.YES_OPTION) {
								a.setIdAssunto(assuntoLocal.getIdAssunto());
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}	
						}
					} else if (assuntoLocal.getIdAssunto() != a.getIdAssunto()) {
						int e = JOptionPane.showConfirmDialog(this, "Os ids dos Assuntos com a mesma Chave Única não coincidem. Alterar o assunto com a mesma chave Única (" + assuntoLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							a.setIdAssunto(assuntoLocal.getIdAssunto());
						} else {
							// procurando pelo id
							assuntoLocal = Fachada.getAssunto(a.getIdAssunto());
							if (assuntoLocal != null) {
								e = JOptionPane.showConfirmDialog(this, "Existe um Assunto com o mesmo id (e chave única diferente -> " + assuntoLocal.getDescricaoCompleta() + "). Esse Assunto deve ser alterado?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
								if (e != JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
									return;	
								}
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				} catch (DataException e1) {
					e1.printStackTrace();
				}
				
				try {
					Fachada.alterarAssunto(a);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Objeto de Tipo Inesperado.", "Erro ao Importar.", JOptionPane.ERROR_MESSAGE);
			}	
		} else if (l.getTipoOperacao() == TipoOperacao.ADICAO_CANTOR_A_MUSICA) {
			if (l.getObjeto() instanceof Musica) {
				Musica m = (Musica) l.getObjeto();
				
				try {
					// Procurando a música exata dentro do banco de dados a ser alterada
					// procurando primeiro pela chave única
					Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
					if (musicaLocal == null) {
						musicaLocal = Fachada.getMusica(m.getIdMusica());
						if (musicaLocal == null) {
							JOptionPane.showMessageDialog(this, "Erro. Não foi encontrada nenhuma música Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
							return;	
						} else {
							int e = JOptionPane.showConfirmDialog(this, "Não foi encontrada uma música com a mesma chave única.\nDeseja alterar os dados da música com o mesmo id?\n" + musicaLocal.getDescricaoCompleta() + "\nEssa música deve ser alterada?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
							if (e == JOptionPane.YES_OPTION) {
								m.setIdMusica(musicaLocal.getIdMusica());
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					} else if (musicaLocal.getIdMusica() != m.getIdMusica()) {
						int e = JOptionPane.showConfirmDialog(this, "Os ids das Músicas com a mesma Chave Única não coincidem. Alterar a música com a mesma chave Única (" + musicaLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							m.setIdMusica(musicaLocal.getIdMusica());
						} else {
							// procurando pelo id
							musicaLocal = Fachada.getMusica(m.getIdMusica());
							if (musicaLocal != null) {
								e = JOptionPane.showConfirmDialog(this, "Existe uma música com o mesmo id (e chave única diferente -> " + musicaLocal.getDescricaoCompleta() + "). Essa música deve ser alterada?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
								if (e != JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
									return;	
								}
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// verificando se os assuntos da música estão cadastrados no sistema
				if (m.getAssuntos() != null && m.getAssuntos().size() > 0) {
					List<Assunto> assuntos = m.getAssuntos();
					List<Assunto> assuntosSalvarMusica = new ArrayList<Assunto>();
					for (Assunto aLog: assuntos) {
						try {
							Assunto assuntoLocal = Fachada.getAssuntoPorChaveUnica(aLog.getChaveUnica());
							if (assuntoLocal != null && assuntoLocal.getAssunto().equalsIgnoreCase(aLog.getAssunto())) {
								assuntosSalvarMusica.add(assuntoLocal);
							} else {
								List<Assunto> assuntosSistema = Fachada.listarAssuntos(aLog.getAssunto());
								if (assuntosSistema != null && assuntosSistema.size() > 0) {
									assuntosSalvarMusica.add(assuntosSistema.get(0));
								} else {
									Assunto novoAssunto = new Assunto();
									novoAssunto.setAssunto(aLog.getAssunto());
									novoAssunto.setChaveUnica(aLog.getChaveUnica());
									Fachada.cadastrarAssunto(novoAssunto);
									assuntosSalvarMusica.add(novoAssunto);
								}	
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
						Tipo tipoLocal = Fachada.getTipoPorChaveUnica(tipoMusica.getChaveUnica());
						if (tipoLocal != null && tipoLocal.getTipo().equalsIgnoreCase(tipoMusica.getTipo())) {
							m.setTipo(tipoLocal);
						} else {
							List<Tipo> tiposSistema = Fachada.listarTipos(tipoMusica.getTipo());
							if (tiposSistema != null && tiposSistema.size() > 0) {
								m.setTipo(tiposSistema.get(0));
							} else {
								Tipo tipo = new Tipo();
								tipo.setTipo(tipoMusica.getTipo());
								tipo.setChaveUnica(tipoMusica.getChaveUnica());
								Fachada.cadastrarTipo(tipo);
								m.setTipo(tipo);
							}	
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
							Cantor cantorLocal = Fachada.getCantorPorChaveUnica(c.getChaveUnica());
							if (cantorLocal != null && cantorLocal.getNome().equalsIgnoreCase(c.getNome())) {
								cantoresSalvarMusica.add(cantorLocal);
							} else {
								List<Cantor> cantoresSistema = Fachada.listarCantoresPorNome(c.getNome());
								if (cantoresSistema != null && cantoresSistema.size() > 0) {
									cantoresSalvarMusica.add(cantoresSistema.get(0));
								} else {
									Cantor novoCantor = new Cantor();
									novoCantor.setNome(c.getNome());
									novoCantor.setNomeSemEspacos(c.getNomeSemEspacos());
									novoCantor.setChaveUnica(c.getChaveUnica());
									Fachada.cadastrarCantor(novoCantor);
									cantoresSalvarMusica.add(novoCantor);
								}	
							}
						} catch (DataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					m.setCantores(cantoresSalvarMusica);
				}
								
				try {
					// verificando se o nome do arquivo foi alterado
					Musica musicaAntiga = Fachada.getMusica(m.getIdMusica());
					if (!musicaAntiga.getDiretorio().equals(m.getDiretorio()) || !musicaAntiga.getNomeArquivo().equals(m.getNomeArquivo())) {
						m.setDiretorio(musicaAntiga.getDiretorio());
						File arquivoMusicaAntiga = new File(Util.getDiretorioBase() + File.separator + musicaAntiga.getDiretorio() + File.separator + musicaAntiga.getNomeArquivo());
						File arquivoMusicaNova = new File(Util.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + m.getNomeArquivo());
						int cont = 0;
						while (arquivoMusicaNova.exists()) {
							cont++;
							String nomeArquivoNovo =  m.getNome() + " #" + cont;
							if (m.getCantores() != null && m.getCantores().size() > 0) {
								nomeArquivoNovo += " - " + m.getCantores().get(0).getNomeSemEspacos(); 
							}
							arquivoMusicaNova = new File(Util.getDiretorioBase() + File.separator + m.getDiretorio() + File.separator + nomeArquivoNovo);
							m.setNomeArquivo(nomeArquivoNovo);
						}
						arquivoMusicaAntiga.renameTo(arquivoMusicaNova);
					}
					
					Fachada.alterarMusica(m);
					// verificando se a música tem arquivo de capa de disco
					if (m.getNomeArquivoCapa() != null && !m.getNomeArquivoCapa().equals("")) {
						Fachada.alterarCapaDiscoMusica(m, m.getNomeArquivoCapa(), diretorioArquivos.getPath() + File.separator + m.getChaveUnica() + Constantes.STRING_CAPA_DISCO);
					} else {
						Fachada.alterarCapaDiscoMusica(m, null, null);
					}
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (l.getTipoOperacao() == TipoOperacao.ALTERACAO_ARQUIVO_MUSICA) {
			Musica m = (Musica) l.getObjeto();
			
			try {
				// Procurando a música exata dentro do banco de dados a ser alterada
				// procurando primeiro pela chave única
				Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
				if (musicaLocal == null) {
					musicaLocal = Fachada.getMusica(m.getIdMusica());
					if (musicaLocal == null) {
						JOptionPane.showMessageDialog(this, "Erro. Não foi encontrada nenhuma música Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
						return;	
					} else {
						int e = JOptionPane.showConfirmDialog(this, "Não foi encontrada uma música com a mesma chave única.\nDeseja alterar os dados da música com o mesmo id?\n" + musicaLocal.getDescricaoCompleta() + "\nEssa música deve ser alterada?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							m.setIdMusica(musicaLocal.getIdMusica());
						} else {
							JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				} else if (musicaLocal.getIdMusica() != m.getIdMusica()) {
					int e = JOptionPane.showConfirmDialog(this, "Os ids das Músicas com a mesma Chave Única não coincidem. Alterar a música com a mesma chave Única (" + musicaLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
					if (e == JOptionPane.YES_OPTION) {
						m.setIdMusica(musicaLocal.getIdMusica());
					} else {
						// procurando pelo id
						musicaLocal = Fachada.getMusica(m.getIdMusica());
						if (musicaLocal != null) {
							e = JOptionPane.showConfirmDialog(this, "Existe uma música com o mesmo id (e chave única diferente -> " + musicaLocal.getDescricaoCompleta() + "). Essa música deve ser alterada?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
							if (e != JOptionPane.YES_OPTION) {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;	
							}
						} else {
							JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
			} catch (DataException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// verificando o novo arquivo
			File arquivoCopiar = new File(diretorioArquivos.getPath() + File.separator + m.getChaveUnica());
			if (!arquivoCopiar.exists()) {
				JOptionPane.showMessageDialog(this, "Arquivo MP3 da Música não foi encontrado.", "Arquivo não encontrado", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// removendo o arquivo antigo da musica
			try {
				File tempFile = File.createTempFile("colec", ".mp3");
				// como a música já foi setada antes, aqui pode-se procurar pelo id
				Musica ma = Fachada.getMusica(m.getIdMusica());
				File arquivoAntigo = new File(Util.getDiretorioBase() + File.separator + ma.getDiretorio() + File.separator + ma.getNomeArquivo());
				// salvando o arquivo antigo para um arquivo temporario
				if (Util.copyFile(arquivoAntigo.getAbsolutePath(), tempFile.getAbsolutePath())) {
					// removendo o arquivo antigo
					arquivoAntigo.delete();
					
					// copiando o arquivo novo para o destino
					if (Util.copyFile(arquivoCopiar.getAbsolutePath(), arquivoAntigo.getAbsolutePath())) {
						System.out.println("Arquivo copiado com sucesso.");
						
						// removendo o arquivo antigo temporário
						tempFile.delete();
					} else if (Util.copyFile(tempFile.getAbsolutePath(), arquivoAntigo.getAbsolutePath())) {
						JOptionPane.showMessageDialog(this, "Erro ao Copiar o novo Arquivo.\n Os dados anteriores foram restaurados.", "Erro ao Copiar Arquivo.", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this, "Erro ao Copiar o novo Arquivo.\n Os dados anteriores nao puderam ser restaurados.", "Erro ao Copiar Arquivo.", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Erro ao Copiar o novo Arquivo.\n Os dados anteriores foram mantidos.", "Erro ao Copiar Arquivo.", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Tipo de Operação inválido..", "Operação inválida.", JOptionPane.ERROR_MESSAGE);
		}
		
		//$hide<<$
	}
	
	private void removerDado() {
		//$hide>>$
		Log l = logs.get(indiceAtual);
		
		if (l.getTipoOperacao() == TipoOperacao.DELECAO) {
			if (l.getObjeto() instanceof Musica) {				
				Musica m = (Musica) l.getObjeto();
				
				try {
					// Procurando a música exata dentro do banco de dados a ser alterada
					// procurando primeiro pela chave única
					Musica musicaLocal = Fachada.getMusicaPorChaveUnica(m.getChaveUnica());
					if (musicaLocal == null) {
						musicaLocal = Fachada.getMusica(m.getIdMusica());
						if (musicaLocal == null) {
							JOptionPane.showMessageDialog(this, "Erro. Não foi encontrada nenhuma música Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
							return;	
						} else {
							int e = JOptionPane.showConfirmDialog(this, "Não foi encontrada uma música com a mesma chave única.\nDeseja alterar os dados da música com o mesmo id?\n" + musicaLocal.getDescricaoCompleta() + "\nEssa música deve ser alterada?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
							if (e == JOptionPane.YES_OPTION) {
								m.setIdMusica(musicaLocal.getIdMusica());
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					} else if (musicaLocal.getIdMusica() != m.getIdMusica()) {
						int e = JOptionPane.showConfirmDialog(this, "Os ids das Músicas com a mesma Chave Única não coincidem. Alterar a música com a mesma chave Única (" + musicaLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							m.setIdMusica(musicaLocal.getIdMusica());
						} else {
							// procurando pelo id
							musicaLocal = Fachada.getMusica(m.getIdMusica());
							if (musicaLocal != null) {
								e = JOptionPane.showConfirmDialog(this, "Existe uma música com o mesmo id (e chave única diferente -> " + musicaLocal.getDescricaoCompleta() + "). Essa música deve ser alterada?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
								if (e != JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
									return;	
								}
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					Fachada.excluirMusica(m);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Cantor) {
				Cantor c = (Cantor) l.getObjeto();
				try {
					// Procurando o cantor exato do banco de dados que será alterado
					// procurando primeiramente pela chave única
					Cantor cantorLocal = Fachada.getCantorPorChaveUnica(c.getChaveUnica());					
					if (cantorLocal == null) {
						cantorLocal = Fachada.getCantor(c.getIdCantor());
						if (cantorLocal == null) {
							JOptionPane.showMessageDialog(this, "Erro. Não foi encontrado nenhum Cantor Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							int e = JOptionPane.showConfirmDialog(this, "Não foi encontrado um Cantor com a mesma chave única.\nDeseja alterar os dados do Cantor com o mesmo id?\n" + cantorLocal.getDescricaoCompleta() + "\nEsse Cantor deve ser alterado?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
							if (e == JOptionPane.YES_OPTION) {
								c.setIdCantor(cantorLocal.getIdCantor());
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					} else if (cantorLocal.getIdCantor() != c.getIdCantor()) {
						int e = JOptionPane.showConfirmDialog(this, "Os ids dos Cantores com a mesma Chave Única não coincidem. Alterar o Cantor com a mesma chave Única (" + cantorLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							c.setIdCantor(cantorLocal.getIdCantor());
						} else {
							// procurando pelo id
							cantorLocal = Fachada.getCantor(c.getIdCantor());
							if (cantorLocal != null) {
								e = JOptionPane.showConfirmDialog(this, "Existe um Cantor com o mesmo id (e chave única diferente -> " + cantorLocal.getDescricaoCompleta() + "). Esse Cantor deve ser alterado?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
								if (e != JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
									return;	
								}
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				} catch (DataException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					Fachada.excluirCantor(c);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Tipo) {
				Tipo t = (Tipo) l.getObjeto();
				try {
					// Procurando o tipo exato dentro do banco de dados a ser alterado
					// procurando primeiro pela chave única
					Tipo tipoLocal = Fachada.getTipoPorChaveUnica(t.getChaveUnica());
					if (tipoLocal == null) {
						tipoLocal = Fachada.getTipo(t.getIdTipo());
						if (tipoLocal == null) {
							JOptionPane.showMessageDialog(this, "Erro. Não foi encontrado nenhum Ritmo Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							int e = JOptionPane.showConfirmDialog(this, "Não foi encontrado um Ritmo com a mesma chave única.\nDeseja alterar os dados do Ritmo com o mesmo id?\n" + tipoLocal.getDescricaoCompleta() + "\nEsse Ritmo deve ser alterado?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
							if (e == JOptionPane.YES_OPTION) {
								t.setIdTipo(tipoLocal.getIdTipo());
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}	
						}
					} else if (tipoLocal.getIdTipo() != t.getIdTipo()) {
						int e = JOptionPane.showConfirmDialog(this, "Os ids dos Ritmos com a mesma Chave Única não coincidem. Alterar o tipo com a mesma chave Única (" + tipoLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							t.setIdTipo(tipoLocal.getIdTipo());
						} else {
							// procurando pelo id
							tipoLocal = Fachada.getTipo(t.getIdTipo());
							if (tipoLocal != null) {
								e = JOptionPane.showConfirmDialog(this, "Existe um Ritmo com o mesmo id (e chave única diferente -> " + tipoLocal.getDescricaoCompleta() + "). Esse Ritmo deve ser alterado?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
								if (e != JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
									return;	
								}
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				} catch (DataException e1) {
					e1.printStackTrace();
				}
				
				try {
					Fachada.excluirTipo(t);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (l.getObjeto() instanceof Assunto) {
				Assunto a = (Assunto) l.getObjeto();
				try {
					// Procurando o Assunto exato dentro do banco de dados a ser alterado
					// procurando primeiro pela chave única
					Assunto assuntoLocal = Fachada.getAssuntoPorChaveUnica(a.getChaveUnica());
					if (assuntoLocal == null) {
						assuntoLocal = Fachada.getAssunto(a.getIdAssunto());
						if (assuntoLocal == null) {
							JOptionPane.showMessageDialog(this, "Erro. Não foi encontrado nenhum Assunto Local correspondente (nem pelo id e nem pela chave única).", "Erro", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							int e = JOptionPane.showConfirmDialog(this, "Não foi encontrado um Assunto com a mesma chave única.\nDeseja alterar os dados do Assunto com o mesmo id?\n" + assuntoLocal.getDescricaoCompleta() + "\nEsse Assunto deve ser alterado?", "Chaves únicas diferentes.", JOptionPane.YES_NO_OPTION);
							if (e == JOptionPane.YES_OPTION) {
								a.setIdAssunto(assuntoLocal.getIdAssunto());
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}	
						}
					} else if (assuntoLocal.getIdAssunto() != a.getIdAssunto()) {
						int e = JOptionPane.showConfirmDialog(this, "Os ids dos Assuntos com a mesma Chave Única não coincidem. Alterar o assunto com a mesma chave Única (" + assuntoLocal.getDescricaoCompleta() + ")?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
						if (e == JOptionPane.YES_OPTION) {
							a.setIdAssunto(assuntoLocal.getIdAssunto());
						} else {
							// procurando pelo id
							assuntoLocal = Fachada.getAssunto(a.getIdAssunto());
							if (assuntoLocal != null) {
								e = JOptionPane.showConfirmDialog(this, "Existe um Assunto com o mesmo id (e chave única diferente -> " + assuntoLocal.getDescricaoCompleta() + "). Esse Assunto deve ser alterado?", "Ids não coincidem", JOptionPane.YES_NO_OPTION);
								if (e != JOptionPane.YES_OPTION) {
									JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
									return;	
								}
							} else {
								JOptionPane.showMessageDialog(this, "Nenhuma alteração foi feita.", "Erro", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
				} catch (DataException e1) {
					e1.printStackTrace();
				}
				
				try {
					Fachada.excluirAssunto(a);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Objeto de Tipo Inesperado.", "Erro ao Importar.", JOptionPane.ERROR_MESSAGE);
			}	
		}
		//$hide<<$
	}
	
	

}
