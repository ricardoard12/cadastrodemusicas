package gui.sincronizacao;
import exceptions.DataException;
import fachada.Fachada;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import util.Util;
import bd.BDUtil;
import classesbasicas.Cantor;
import classesbasicas.Log;
import classesbasicas.Musica;
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
		String nomeDiretorio = Util.gerarChaveUnica("" + (new Date().getTime()));
		File tempDir = new File(Util.getCaminhoDiretorioTemporario());
		File tempDirLogs = new File(tempDir.getPath() + File.separator + nomeDiretorio);
		while (tempDirLogs.exists()) {
			nomeDiretorio = Util.gerarChaveUnica("" + (new Date().getTime()));
			tempDirLogs = new File(tempDir.getPath() + File.separator + nomeDiretorio);
		}
		tempDirLogs.mkdir();
		
		try {			
			LogDAO dao = new LogDAOMySQL();
			List<Log> logsListados = dao.listarLogs(); // todos os logs não excluídos
			
			List<Log> logs = new ArrayList<Log>(); // somente os logs que interessam
			
			for (Log l: logsListados) {
				if (l.getTipoOperacao() == TipoOperacao.CADASTRO) {
					// pegar o arquivo para exportar
					if (l.getObjeto() instanceof Musica) {
						Musica mLog = (Musica) l.getObjeto();	
						
						List<Log> logsObjeto = Fachada.listarLogs(mLog.getChaveUnica());
						if (logsObjeto != null && logsObjeto.size() > 0) {
							// logs
						}
						
						
						// comparando a data de modified da música para verificar se a versão contida no log é a mais nova
						// caso não seja, ignorar o Log atual
						Musica mBD = Fachada.getMusica(mLog.getIdMusica());
						
						if (mBD != null && mBD.getModified().equals(mLog.getModified())) {
							// exportar a música
							l.setObjeto(mBD); // garante que está pegando a versão mais atual da música
							logs.add(l);
							
							// salvando o arquivo MP3 para o arquivo final
						} else {
							// ignorar a música atual
							continue;
						}
					} else if (l.getObjeto() instanceof Cantor) {
						
					} else {
						System.out.println("Erro ao exportar Objeto -> Objeto de Tipo insperado.");
					}
				}
			}
			
			// TODO remover arquivos e pasta temporária
			
			FileOutputStream fos = new FileOutputStream(caminhoArquivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(logs);
			oos.flush();
			oos.close();
			fos.close();
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
	//$hide<<$
}
