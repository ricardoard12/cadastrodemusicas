package gui.musicas;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import bd.BDUtil;

import util.GlobalPlayer;
import classesbasicas.Constantes;
import classesbasicas.Musica;
import exceptions.ConfiguracaoInexistenteException;
import exceptions.DataException;

public class MusicasInternalFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6117989326603943792L;
	private JPanel jContentPane = null;
	private JTabbedPane tabbedPane = null;
	private CadastrarMusicasPanel cadastrarMusicasPanel = null;
	private ProcurarMusicasPanel procurarMusicasPanel = null;
	private EditarMusicasPanel editarMusicasPanel = null;
	
	Frame frameOwner = null;
	
	/**
	 * This is the xxx default constructor
	 */
	public MusicasInternalFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(645, 402);
		this.setFrameIcon(new ImageIcon(getClass().getResource("/figuras/icones/music.png")));
		this.setClosable(true);
		try {
			this.setTitle(BDUtil.getConfiguracao(Constantes.CONFIGURACAO_ITEM_MENU_MUSICAS));
		} catch (ConfiguracaoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes tabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			try {
				tabbedPane.addTab(BDUtil.getConfiguracao(Constantes.CONFIGURACAO_TAB_CADASTRAR_MUSICAS_TITULO), null, getCadastrarMusicasPanel(), null);
				tabbedPane.addTab(BDUtil.getConfiguracao(Constantes.CONFIGURACAO_TAB_PROCURAR_MUSICAS_TITULO), null, getProcurarMusicasPanel(), null);
				tabbedPane.addTab(BDUtil.getConfiguracao(Constantes.CONFIGURACAO_TAB_EDITAR_MUSICAS_TITULO), null, getEditarMusicasPanel(), null);
			} catch (ConfiguracaoInexistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return tabbedPane;
	}

	/**
	 * This method initializes cadastrarMusicasPanel	
	 * 	
	 * @return gui.musicas.CadastrarMusicasPanel	
	 */
	private CadastrarMusicasPanel getCadastrarMusicasPanel() {
		if (cadastrarMusicasPanel == null) {
			cadastrarMusicasPanel = new CadastrarMusicasPanel();
			cadastrarMusicasPanel.setPai(this);
		}
		return cadastrarMusicasPanel;
	}

	/**
	 * This method initializes procurarMusicasPanel	
	 * 	
	 * @return gui.musicas.ProcurarMusicasPanel	
	 */
	public ProcurarMusicasPanel getProcurarMusicasPanel() {
		if (procurarMusicasPanel == null) {
			procurarMusicasPanel = new ProcurarMusicasPanel();
			procurarMusicasPanel.setPai(this);
		}
		return procurarMusicasPanel;
	}

	/**
	 * This method initializes editarMusicasPanel	
	 * 	
	 * @return gui.musicas.EditarMusicasPanel	
	 */
	private EditarMusicasPanel getEditarMusicasPanel() {
		if (editarMusicasPanel == null) {
			editarMusicasPanel = new EditarMusicasPanel();
			editarMusicasPanel.setPai(this);
		}
		return editarMusicasPanel;
	}
	
	public void editarMusica(Musica m) {
		getEditarMusicasPanel().editarMusica(m);
		getTabbedPane().setSelectedIndex(2);
	}

	@Override
	public void dispose() {
		fechar();
	}
	
	public void fechar() {
		GlobalPlayer.stop();
		super.dispose();	
	}
	
	
	public void musicaFoiCadastrada() {
		getProcurarMusicasPanel().procurarMusicas();
		getEditarMusicasPanel().atualizarTabelaDeCantores();
		getEditarMusicasPanel().atualizarTabelaDeMusicas();
		getEditarMusicasPanel().atualizaNomeArquivoDaMusica();
	}
	
	public void musicaFoiEditada() {
		getProcurarMusicasPanel().procurarMusicas();
		getCadastrarMusicasPanel().atualizarTabelaDeCantores();
		getCadastrarMusicasPanel().atualizarTabelaDeMusicas();
		getCadastrarMusicasPanel().atualizaNomeArquivoDaMusica();
	}
	
	public void musicaFoiApagada() {
		getProcurarMusicasPanel().procurarMusicas();
		getCadastrarMusicasPanel().atualizarTabelaDeMusicas();
		getEditarMusicasPanel().atualizarTabelaDeMusicas();
		getCadastrarMusicasPanel().atualizaNomeArquivoDaMusica();
		getEditarMusicasPanel().atualizaNomeArquivoDaMusica();
	}
	
	public void novoRitmoCadastrado() {
		int indice = getTabbedPane().getSelectedIndex();
		
		if (indice == 0) {
			getEditarMusicasPanel().novoTipoCadastrado();
		} 
		
		if (indice == 2) {
			getCadastrarMusicasPanel().novoTipoCadastrado();
		}
		
		getProcurarMusicasPanel().atualizarRitmos();
	}
	
	public void novaQualidadeCadastrada() {
		int indice = getTabbedPane().getSelectedIndex();
		
		if (indice == 0) {
			getEditarMusicasPanel().novaQualidadeCadastrada();
		} 
		
		if (indice == 2) {
			getCadastrarMusicasPanel().novaQualidadeCadastrada();
		}
		
		getProcurarMusicasPanel().atualizarQualidades();
	}
	
	public void novoAssuntoCadastrado() {
		int indice = getTabbedPane().getSelectedIndex();
		
		if (indice == 0) {
			getEditarMusicasPanel().novoAssuntoCadastrado();
		} 
		
		if (indice == 2) {
			getCadastrarMusicasPanel().novoAssuntoCadastrado();
		}
		
		getProcurarMusicasPanel().atualizarAssuntos();
	}

	public Frame getFrameOwner() {
		return frameOwner;
	}

	public void setFrameOwner(Frame frameOwner) {
		this.frameOwner = frameOwner;
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
