package gui.sincronizacao;
import exceptions.DataException;
import exceptions.DiretorioBaseInvalidoException;
import fachada.Fachada;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import util.Util;
import bd.BDUtil;
import classesbasicas.Assunto;
import classesbasicas.Cantor;
import classesbasicas.Constantes;
import classesbasicas.Log;
import classesbasicas.Musica;
import classesbasicas.Tipo;
import classesbasicas.Log.TipoOperacao;
import dao.LogDAO;
import dao.impl.LogDAOMySQL;


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
public class Sincronizacao extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JCheckBox ativarSincronizacaoCheckBox;
	private JButton exportaDadosButton;
	private JButton limparDadosButton;
	private JButton importarDadosButton;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Sincronizacao inst = new Sincronizacao();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Sincronizacao() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Ferramenta de Sincronização");
			this.setResizable(false);
			{
				ativarSincronizacaoCheckBox = new JCheckBox();
				getContentPane().add(ativarSincronizacaoCheckBox, "Center");
				ativarSincronizacaoCheckBox.setText("Ativar Sincronização");
				ativarSincronizacaoCheckBox.setBounds(12, 10, 172, 27);
				//$hide>>$
				ativarSincronizacaoCheckBox.setSelected(BDUtil.isSincronizacaoAtiva());
				//$hide<<$
				
				System.out.println(ativarSincronizacaoCheckBox.isSelected());
				ativarSincronizacaoCheckBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent evt) {
						if (evt.getStateChange() == ItemEvent.SELECTED) { //$hide>>$
							atualizarSincronizacaoAtiva(true);
						} else {
							atualizarSincronizacaoAtiva(false);
						}
						
						//$hide<<$
					}
				});
			}
			{
				exportaDadosButton = new JButton();
				getContentPane().add(exportaDadosButton);
				exportaDadosButton.setText("Exportar Dados");
				exportaDadosButton.setBounds(12, 40, 141, 32);
				exportaDadosButton.setFocusable(false);
				exportaDadosButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//$hide>>$
						exportarDados();
						//$hide<<$
					}
				});
			}
			{
				importarDadosButton = new JButton();
				getContentPane().add(importarDadosButton);
				importarDadosButton.setText("Importar Dados");
				importarDadosButton.setBounds(164, 40, 131, 32);
				importarDadosButton.setFocusable(false);
				importarDadosButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						importarDados();
					}
				});
			}
			{
				limparDadosButton = new JButton();
				getContentPane().add(limparDadosButton);
				limparDadosButton.setText("Limpar Dados");
				limparDadosButton.setBounds(12, 75, 141, 30);
				limparDadosButton.setSize(141, 32);
				limparDadosButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//$hide>>$
						int ok = JOptionPane.showConfirmDialog(Sincronizacao.this, "Tem Certeza de que deseja excluir\nos dados de sincronização?", "Excluir Dados", JOptionPane.YES_NO_CANCEL_OPTION);
						if (ok == JOptionPane.OK_OPTION) {
							try {
								BDUtil.excluirDadosSincronizacao();
							} catch (SQLException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(Sincronizacao.this, "Houve um erro ao excluir os dados de sincronização.", "Erro", JOptionPane.ERROR_MESSAGE);
							}
						}
						//$hide<<$						
					}
				});
			}
			pack();
			this.setSize(311, 147);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//$hide>>$
	private void exportarDados() {		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);					
		int opcao = chooser.showOpenDialog(this);
		String caminhoArquivo = null;

		if (opcao == JFileChooser.APPROVE_OPTION) { 
			 caminhoArquivo = chooser.getSelectedFile().getPath();
		} else {
			return;
		}

		// adicionando uma pasta temporária para a cópia de todos os arquivos necessários
		String nomeDiretorio = "";
		File tempDir = new File(Util.getCaminhoDiretorioTemporario());
		File tempDirLogs = null;
		do {
			nomeDiretorio = Util.gerarChaveUnica("" + (new Date().getTime()));
			tempDirLogs = new File(tempDir.getAbsolutePath() + File.separator + nomeDiretorio);
		} while (tempDirLogs.exists());
		tempDirLogs.mkdir();
		// criado o diretório onde ficarao todos os dados das alteracoes realizadas
		
		try {			
			LogDAO dao = new LogDAOMySQL();
			List<Log> logsListados = dao.listarLogs(); // todos os logs não excluídos
			List<String> objetosIgnorar = new ArrayList<String>();
			
			List<Log> logs = new ArrayList<Log>(); // somente os logs que interessam
			
			for (Log l: logsListados) {
				// se o objeto já foi processado, ele está inserido na lista dos objetos a ignorar
				System.out.println("Eh alteracao arquivo musica: " + (l.getTipoOperacao() != TipoOperacao.ALTERACAO_ARQUIVO_MUSICA));
				if (objetosIgnorar.indexOf(l.getChaveUnicaObjeto()) >= 0 && l.getTipoOperacao() != TipoOperacao.ALTERACAO_ARQUIVO_MUSICA) continue;
				
				// Operacao de cadastro: salvar o arquivo da música, mais os dados mais atuais da música
				if (l.getTipoOperacao() == TipoOperacao.CADASTRO) {
					if (l.getObjeto() instanceof Musica) {
						Musica mLog = (Musica) l.getObjeto();
						Musica musica = Fachada.getMusica(mLog.getIdMusica());
						
						// se não encontrou a música, ignora o log (a música foi excluída)
						if (musica == null) {
							// adicionar o objetos a lista de objetos a ignorar, assim se houver outro log referente ao mesmo objeto, ele nao sera processado
							objetosIgnorar.add(l.getChaveUnicaObjeto());
							continue;
						}

						// salvando o arquivo MP3 da música para o diretorio temporario
						Util.copyFile(BDUtil.getDiretorioBase() + File.separator + musica.getDiretorio() + File.separator + musica.getNomeArquivo()
								, tempDirLogs.getAbsolutePath() + File.separator + musica.getChaveUnica());
						
						l.setObjeto(musica);
						logs.add(l);
						
						objetosIgnorar.add(l.getChaveUnicaObjeto());
					} else if (l.getObjeto() instanceof Cantor) {
						/*
						 * pode-se ignorar, pois só interessa um Cantor que faça parte de alguma música.
						 * e o cantor já vai dentro do objeto Musica quando a música é exportada
						 */
					} else if (l.getObjeto() instanceof Tipo) {
						/*
						 * pode-se ignorar, pois só interessa um Ritmo (Tipo) que faça parte de alguma música.
						 * e o tipo já vai dentro do objeto Musica quando a música é exportada
						 */
					} else if (l.getObjeto() instanceof Assunto) {
						/*
						 * pode-se ignorar, pois só interessa um Assunto que faça parte de alguma música.
						 * e o Assunto já vai dentro do objeto Musica quando a música é exportada
						 */
					} else {
						System.out.println("Erro ao exportar Objeto -> Objeto de Tipo insperado.");
					}
				} else if (l.getTipoOperacao() == TipoOperacao.ALTERACAO) {
					if (l.getObjeto() instanceof Musica) {
						Musica mLog = (Musica) l.getObjeto();
						Musica musica = Fachada.getMusica(mLog.getIdMusica());
						
						// se não encontrou a música, ignora o log (a música foi excluída)
						if (musica == null) {
							// adicionar o objetos a lista de objetos a ignorar, assim se houver outro log referente ao mesmo objeto, ele nao sera processado
							objetosIgnorar.add(l.getChaveUnicaObjeto());
							continue;
						}
						
						l.setObjeto(musica);
						logs.add(l);
						
						objetosIgnorar.add(l.getChaveUnicaObjeto());
					} else if (l.getObjeto() instanceof Cantor) {
						Cantor cLog = (Cantor) l.getObjeto();
						Cantor cantor = Fachada.getCantor(cLog.getIdCantor());
						if (cantor == null) {
							objetosIgnorar.add(l.getChaveUnicaObjeto());
							continue;
						}
						l.setObjeto(cantor);
						logs.add(l);
						objetosIgnorar.add(l.getChaveUnicaObjeto());
					} else if (l.getObjeto() instanceof Tipo) {
						Tipo tLog = (Tipo) l.getObjeto();
						Tipo tipo = Fachada.getTipo(tLog.getIdTipo());
						if (tipo == null) {
							objetosIgnorar.add(l.getChaveUnicaObjeto());
							continue;
						}
						l.setObjeto(tipo);
						logs.add(l);
						objetosIgnorar.add(l.getChaveUnicaObjeto());
					} else if (l.getObjeto() instanceof Assunto) {
						Assunto aLog = (Assunto) l.getObjeto();
						Assunto assunto = Fachada.getAssunto(aLog.getIdAssunto());
						if (assunto == null) {
							objetosIgnorar.add(l.getChaveUnicaObjeto());
							continue;
						}
						l.setObjeto(assunto);
						logs.add(l);
						objetosIgnorar.add(l.getChaveUnicaObjeto());
					} else {
						System.out.println("Erro ao exportar Objeto -> Objeto de Tipo insperado.");
					}

				} else if (l.getTipoOperacao() == TipoOperacao.DELECAO) {
					logs.add(l);
					objetosIgnorar.add(l.getChaveUnicaObjeto());
				} else if (l.getTipoOperacao() == TipoOperacao.ADICAO_CANTOR_A_MUSICA) {
					if (l.getObjeto() instanceof Musica) {
						Musica mLog = (Musica) l.getObjeto();
						Musica musica = Fachada.getMusica(mLog.getIdMusica());
						
						// se não encontrou a música, ignora o log (a música foi excluída)
						if (musica == null) {
							// adicionar o objetos a lista de objetos a ignorar, assim se houver outro log referente ao mesmo objeto, ele nao sera processado
							objetosIgnorar.add(l.getChaveUnicaObjeto());
							continue;
						}
						
						l.setObjeto(musica);
						logs.add(l);
						
						objetosIgnorar.add(l.getChaveUnicaObjeto());
					} 
				} else if (l.getTipoOperacao() == TipoOperacao.ALTERACAO_ARQUIVO_MUSICA) {
					System.out.println("Alteracao de arquivo de musica");
					Musica mLog = (Musica) l.getObjeto();
					Musica musica = Fachada.getMusica(mLog.getIdMusica());
					
					// se não encontrou a música, ignora o log (a música foi excluída)
					if (musica == null) {
						// adicionar o objetos a lista de objetos a ignorar, assim se houver outro log referente ao mesmo objeto, ele nao sera processado
						objetosIgnorar.add(l.getChaveUnicaObjeto());
						continue;
					}

					// salvando o arquivo MP3 da música para o diretorio temporario
					Util.copyFile(BDUtil.getDiretorioBase() + File.separator + musica.getDiretorio() + File.separator + musica.getNomeArquivo()
							, tempDirLogs.getAbsolutePath() + File.separator + musica.getChaveUnica());
					
					l.setObjeto(musica);
					logs.add(l);
				}
			}
			
			FileOutputStream fos = new FileOutputStream(tempDirLogs.getAbsolutePath() + File.separator + Constantes.NOME_ARQUIVO_LOG);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(logs);
			oos.flush();
			oos.close();
			fos.close();
			
			// criar o arquivo ZIP com todos os dados exportados
			FileOutputStream arquivoSaida = new FileOutputStream(caminhoArquivo);
			ZipOutputStream arquivoZip = new ZipOutputStream(arquivoSaida);
			byte temp[] = new byte[8192];
			for (File arquivo: tempDirLogs.listFiles()) {
				FileInputStream in = new FileInputStream(arquivo);
				arquivoZip.putNextEntry(new ZipEntry(arquivo.getName()));
				int len;
				while ((len = in.read(temp)) > 0) {
					arquivoZip.write(temp, 0, len);
				}
				arquivoZip.closeEntry();
				in.close();
			}
			arquivoZip.close();

			// remover os arquivos temporarios
			for (File arquivo: tempDirLogs.listFiles()) {
				arquivo.delete();
			}
			tempDirLogs.delete();
			
			JOptionPane.showMessageDialog(this, "Alterações exportadas com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DiretorioBaseInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void atualizarSincronizacaoAtiva(boolean ativa) {
		System.out.println(ativarSincronizacaoCheckBox.isSelected());
		String sql = "UPDATE configuracoes SET valor = " + (ativa ? "'ativa'" : "'inativa'") + " WHERE configuracao LIKE 'sincronizacao'";
		System.out.println(sql);
		Statement stat;
		try {
			stat = BDUtil.getConexao().createStatement();
			stat.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void importarDados() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);					
		int opcao = chooser.showOpenDialog(this);
		String caminhoArquivo = null;

		if (opcao == JFileChooser.APPROVE_OPTION) { 
			 caminhoArquivo = chooser.getSelectedFile().getAbsolutePath();
		} else {
			return;
		}
		
		// adicionando uma pasta temporária para a cópia de todos os arquivos necessários
		String nomeDiretorio = "";
		File tempDir = new File(Util.getCaminhoDiretorioTemporario());
		File tempDirLogs = null;
		do {
			nomeDiretorio = Util.gerarChaveUnica("" + (new Date().getTime()));
			tempDirLogs = new File(tempDir.getAbsolutePath() + File.separator + nomeDiretorio);
		} while (tempDirLogs.exists());
		tempDirLogs.mkdir();
		// criado o diretório onde ficarao todos os dados das alteracoes realizadas
		
		try {
			// copiando os arquivos dentro do arquivo zip para a pasta temporaria criada
			FileInputStream in;
			in = new FileInputStream(caminhoArquivo);
			ZipInputStream arquivoZip = new ZipInputStream(in);
			ZipEntry arquivo;
			byte temp[] = new byte[8192];
			while ((arquivo = arquivoZip.getNextEntry()) != null) {
				FileOutputStream out = new FileOutputStream(tempDirLogs + File.separator + arquivo.getName());
				int len;
				while ((len = arquivoZip.read(temp, 0, temp.length)) > 0) {
					out.write(temp, 0, len);
				}
				out.close();
				arquivoZip.closeEntry();
			}
			arquivoZip.close();
			
			// lendo o arquivo de logs
			FileInputStream is = new FileInputStream(tempDirLogs + File.separator + Constantes.NOME_ARQUIVO_LOG);
			ObjectInputStream ois = new ObjectInputStream(is);
			Object o = ois.readObject();
			if (o instanceof List<?>) {
				List<Log> logs = (List<Log>) o;
				DialogImportacao importacao = new DialogImportacao(this, logs, tempDirLogs);
				importacao.setModal(true);
				importacao.setVisible(true);
				System.out.println("Sincronização Finalizada.");
				System.out.println("Size: " + logs.size());
				for (Log l: logs) {
					System.out.println("Log: " + l.getClasseObjeto() + " - " + l.getTipoOperacao());
				}
			}
			
			// removendo os arquivos temporarios criados
			for (File arquivoTemporario: tempDirLogs.listFiles()) {
				arquivoTemporario.delete();
			}
			tempDirLogs.delete();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//$hide<<$
}
