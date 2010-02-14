package gui;

import exceptions.DataException;
import exceptions.DiretorioBaseInvalidoException;
import fachada.Fachada;
import gui.about.About;
import gui.assuntos.AssuntosInternalFrame;
import gui.cantores.CantoresInternalFrame;
import gui.colecaodiscos.GerarColecaoDiscosDialog;
import gui.musicas.MusicasInternalFrame;
import gui.player.PlayerFrame;
import gui.tipos.TiposInternalFrame;
import gui.verificacoes.VerificarArquivosDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import util.GlobalPlayer;
import util.Util;
import bd.BDUtil;
import classesbasicas.Cantor;
import classesbasicas.Musica;


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
public class CadastroDeMusicas extends JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar barraDeMenuPrincipalJMenuBar = null;

	private JMenu sobreMenu = null;

	private JMenuItem sobreMenuItem = null;

	private JMenu cadastroMenu = null;

	private JMenu relatoriosMenu = null;

	private JMenu sistemaMenu = null;

	private JMenuItem sairMenuItem = null;

	private JMenuItem relatorioMusicaOrdemAlfabeticaMenuItem = null;

	private JMenuItem relatorioMusicasPorCantorMenuItem = null;

	private JMenuItem diretorioBaseMenuItem = null;

	private JMenuItem cadastrarMusicaMenuItem = null;

	private JMenuItem cadastrarAssuntoMenuItem = null;

	private JMenuItem cadastrarRitmoMenuItem = null;

	private JMenu buscaMenu = null;

	private JMenuItem buscarMusicaMenuItem = null;

	private JMenuItem buscarCantorMenuItem = null;

	private JDesktopPane desktopPane = null;

	private JMenuItem backupBDMenuItem = null;

	private JMenu todasAsMusicasMenu = null;
	
	// janela interna, só abre uma de cada vez, sempre nessa variável
	private JInternalFrame internalFrame = null;
	private JMenuItem abrirPlayerMenuItem;
	private JMenu playerMenu;

	private JLabel mysqlLabel = null;

	private JMenu colecaoMenu = null;

	private JMenuItem gerarColecaoMenuItem = null;

	private JMenu relatoriosCantoresMenu = null;

	private JMenuItem cantoresComNumeroDeMusicasMenuItem = null;

	private JMenuItem verificarArquivosMenuItem = null;

	private JMenuItem cadastrarDiretorioMenuItem = null;

	private JLabel backgroundLabel = null;

	/**
	 * This method initializes barraDeMenuPrincipalJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getBarraDeMenuPrincipalJMenuBar() {
		if (barraDeMenuPrincipalJMenuBar == null) {
			barraDeMenuPrincipalJMenuBar = new JMenuBar();
			barraDeMenuPrincipalJMenuBar.add(getSistemaMenu());
			barraDeMenuPrincipalJMenuBar.add(getCadastroMenu());
			barraDeMenuPrincipalJMenuBar.add(getBuscaMenu());
			barraDeMenuPrincipalJMenuBar.add(getRelatoriosMenu());
			barraDeMenuPrincipalJMenuBar.add(getColecaoMenu());
			barraDeMenuPrincipalJMenuBar.add(getPlayerMenu());
			barraDeMenuPrincipalJMenuBar.add(getSobreMenu());
		}
		return barraDeMenuPrincipalJMenuBar;
	}

	/**
	 * This method initializes sobreMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getSobreMenu() {
		if (sobreMenu == null) {
			sobreMenu = new JMenu();
			sobreMenu.setText("Sobre");
			sobreMenu.add(getSobreMenuItem());
		}
		return sobreMenu;
	}

	/**
	 * This method initializes sobreMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSobreMenuItem() {
		if (sobreMenuItem == null) {
			sobreMenuItem = new JMenuItem();
			sobreMenuItem.setText("Sobre");
			sobreMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					 new About(CadastroDeMusicas.this);
				}
			});
		}
		return sobreMenuItem;
	}

	/**
	 * This method initializes cadastroMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getCadastroMenu() {
		if (cadastroMenu == null) {
			cadastroMenu = new JMenu();
			cadastroMenu.setText("Cadastro");
			cadastroMenu.add(getCadastrarMusicaMenuItem());
			cadastroMenu.add(getCadastrarRitmoMenuItem());
			cadastroMenu.add(getCadastrarAssuntoMenuItem());
		}
		return cadastroMenu;
	}

	/**
	 * This method initializes relatoriosMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getRelatoriosMenu() {
		if (relatoriosMenu == null) {
			relatoriosMenu = new JMenu();
			relatoriosMenu.setText("Relatórios");
			relatoriosMenu.add(getTodasAsMusicasMenu());
			relatoriosMenu.add(getRelatoriosCantoresMenu());
		}
		return relatoriosMenu;
	}

	/**
	 * This method initializes sistemaMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getSistemaMenu() {
		if (sistemaMenu == null) {
			sistemaMenu = new JMenu();
			sistemaMenu.setText("Sistema");
			sistemaMenu.add(getDiretorioBaseMenuItem());
			sistemaMenu.add(getVerificarArquivosMenuItem());
			sistemaMenu.add(getBackupBDMenuItem());
			sistemaMenu.add(getCadastrarDiretorioMenuItem());
			sistemaMenu.addSeparator();
			sistemaMenu.add(getSairMenuItem());
		}
		return sistemaMenu;
	}

	/**
	 * This method initializes sairMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSairMenuItem() {
		if (sairMenuItem == null) {
			sairMenuItem = new JMenuItem();
			sairMenuItem.setText("Sair");
			sairMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					sair();
				}
			});
		}
		return sairMenuItem;
	}

	/**
	 * This method initializes relatorioMusicaOrdemAlfabeticaMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getRelatorioMusicaOrdemAlfabeticaMenuItem() {
		if (relatorioMusicaOrdemAlfabeticaMenuItem == null) {
			relatorioMusicaOrdemAlfabeticaMenuItem = new JMenuItem();
			relatorioMusicaOrdemAlfabeticaMenuItem.setText("Por Ordem Alfabética");
			relatorioMusicaOrdemAlfabeticaMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							Fachada.criaRelatorioMusicasOrdemAlfabetica();
						}
					});
		}
		return relatorioMusicaOrdemAlfabeticaMenuItem;
	}

	/**
	 * This method initializes relatorioMusicasPorCantorMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getRelatorioMusicasPorCantorMenuItem() {
		if (relatorioMusicasPorCantorMenuItem == null) {
			relatorioMusicasPorCantorMenuItem = new JMenuItem();
			relatorioMusicasPorCantorMenuItem.setText("Por Cantor");
			relatorioMusicasPorCantorMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							Fachada.criaRelatorioMusicasPorCantor();
						}
					});
		}
		return relatorioMusicasPorCantorMenuItem;
	}

	/**
	 * This method initializes diretorioBaseMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getDiretorioBaseMenuItem() {
		if (diretorioBaseMenuItem == null) {
			diretorioBaseMenuItem = new JMenuItem();
			diretorioBaseMenuItem.setText("Diretório Base");
			diretorioBaseMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					
					if (Util.getDiretorioBase() != null) {
						chooser.setCurrentDirectory(new File(Util.getDiretorioBase()));
					}					
					
					int opcao = chooser.showOpenDialog(CadastroDeMusicas.this);
					
					if (opcao == JFileChooser.APPROVE_OPTION) { 
						Util.setDiretorioBase(chooser.getSelectedFile().getPath());
					}
				}
			});
		}
		return diretorioBaseMenuItem;
	}

	/**
	 * This method initializes cadastrarMusicaMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCadastrarMusicaMenuItem() {
		if (cadastrarMusicaMenuItem == null) {
			cadastrarMusicaMenuItem = new JMenuItem();
			cadastrarMusicaMenuItem.setText("Músicas");
			cadastrarMusicaMenuItem.setActionCommand("Músicas");
			cadastrarMusicaMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (internalFrame != null) {
								JOptionPane.showMessageDialog(CadastroDeMusicas.this, "Por favor, feche a janela interna atual para abrir outra.",
										"Ops...", JOptionPane.ERROR_MESSAGE);
								return;
							}
							internalFrame = new MusicasInternalFrame();
							((MusicasInternalFrame) internalFrame).setFrameOwner(CadastroDeMusicas.this);
							adicionarInternalFrameListener();
							desktopPane.add(internalFrame);
							internalFrame.pack();
							internalFrame.setVisible(true);				
							try {
								internalFrame.setMaximum(true);
							} catch (PropertyVetoException e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return cadastrarMusicaMenuItem;
	}

	/**
	 * This method initializes cadastrarAssuntoMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCadastrarAssuntoMenuItem() {
		if (cadastrarAssuntoMenuItem == null) {
			cadastrarAssuntoMenuItem = new JMenuItem();
			cadastrarAssuntoMenuItem.setText("Assuntos");
			cadastrarAssuntoMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (internalFrame != null) {
						JOptionPane.showMessageDialog(CadastroDeMusicas.this, "Por favor, feche a janela interna atual para abrir outra.",
								"Ops...", JOptionPane.ERROR_MESSAGE);
						return;
					}
					internalFrame = new AssuntosInternalFrame();
					adicionarInternalFrameListener();
					desktopPane.add(internalFrame);
					internalFrame.pack();
					internalFrame.setVisible(true);				
					try {
						internalFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return cadastrarAssuntoMenuItem;
	}

	/**
	 * This method initializes cadastrarRitmoMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCadastrarRitmoMenuItem() {
		if (cadastrarRitmoMenuItem == null) {
			cadastrarRitmoMenuItem = new JMenuItem();
			cadastrarRitmoMenuItem.setText("Ritmos");
			cadastrarRitmoMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (internalFrame != null) {
						JOptionPane.showMessageDialog(CadastroDeMusicas.this, "Por favor, feche a janela interna atual para abrir outra.",
								"Ops...", JOptionPane.ERROR_MESSAGE);
						return;
					}
					internalFrame = new TiposInternalFrame();
					adicionarInternalFrameListener();
					desktopPane.add(internalFrame);
					internalFrame.pack();
					internalFrame.setVisible(true);				
					try {
						internalFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return cadastrarRitmoMenuItem;
	}

	private void adicionarInternalFrameListener() {
		internalFrame.addInternalFrameListener(new InternalFrameAdapter() {							
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				super.internalFrameClosed(e);
				internalFrame = null;
			}
		
		});
	}
	
	/**
	 * This method initializes buscaMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getBuscaMenu() {
		if (buscaMenu == null) {
			buscaMenu = new JMenu();
			buscaMenu.setText("Buscar");
			buscaMenu.add(getBuscarMusicaMenuItem());
			buscaMenu.add(getBuscarCantorMenuItem());
		}
		return buscaMenu;
	}

	/**
	 * This method initializes buscarMusicaMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getBuscarMusicaMenuItem() {
		if (buscarMusicaMenuItem == null) {
			buscarMusicaMenuItem = new JMenuItem();
			buscarMusicaMenuItem.setText("Músicas");
			buscarMusicaMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (internalFrame != null) {
						JOptionPane.showMessageDialog(CadastroDeMusicas.this, "Por favor, feche a janela interna atual para abrir outra.",
								"Ops...", JOptionPane.ERROR_MESSAGE);
						return;
					}
					internalFrame = new MusicasInternalFrame();
					((MusicasInternalFrame) internalFrame).setFrameOwner(CadastroDeMusicas.this);
					adicionarInternalFrameListener();
					desktopPane.add(internalFrame);
					((MusicasInternalFrame) internalFrame).getTabbedPane().setSelectedIndex(1);
					internalFrame.pack();
					internalFrame.setVisible(true);	
					try {
						internalFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return buscarMusicaMenuItem;
	}

	/**
	 * This method initializes buscarCantorMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getBuscarCantorMenuItem() {
		if (buscarCantorMenuItem == null) {
			buscarCantorMenuItem = new JMenuItem();
			buscarCantorMenuItem.setText("Cantores");
			buscarCantorMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (internalFrame != null) {
						JOptionPane.showMessageDialog(CadastroDeMusicas.this, "Por favor, feche a janela interna atual para abrir outra.",
								"Ops...", JOptionPane.ERROR_MESSAGE);
						return;
					}
					internalFrame = new CantoresInternalFrame();
					adicionarInternalFrameListener();
					desktopPane.add(internalFrame);
					((CantoresInternalFrame) internalFrame).getCantoresTabbedPane().setSelectedIndex(0);
					internalFrame.pack();
					internalFrame.setVisible(true);	
					try {
						internalFrame.setMaximum(true);
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return buscarCantorMenuItem;
	}

	/**
	 * This method initializes desktopPane	
	 * 	
	 * @return javax.swing.JDesktopPane	
	 */
	private JDesktopPane getDesktopPane() {
		if (desktopPane == null) {
			backgroundLabel = new JLabel();
			backgroundLabel.setBounds(new Rectangle(22, 251, 298, 272));
			backgroundLabel.setIcon(new ImageIcon(getClass().getResource("/figuras/Sol+Lua+e+Estrela.png")));
			backgroundLabel.setText("");
			mysqlLabel = new JLabel();
			mysqlLabel.setBounds(new Rectangle(613, 410, 174, 120));
			mysqlLabel.setIcon(new ImageIcon(getClass().getResource("/figuras/mysql.png")));
			mysqlLabel.setText("");
			desktopPane = new JDesktopPane();
			desktopPane.setBackground(new java.awt.Color(255,255,255));
			desktopPane.add(mysqlLabel, null);
			desktopPane.add(backgroundLabel, null);
		}
		return desktopPane;
	}

	/**
	 * This method initializes backupBDMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getBackupBDMenuItem() {
		if (backupBDMenuItem == null) {
			backupBDMenuItem = new JMenuItem();
			backupBDMenuItem.setText("Backup do BD");
			backupBDMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					backupBD();
				}
			});
		}
		
		backupBDMenuItem.setEnabled(false);
		return backupBDMenuItem;
	}

	/**
	 * This method initializes todasAsMusicasMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getTodasAsMusicasMenu() {
		if (todasAsMusicasMenu == null) {
			todasAsMusicasMenu = new JMenu();
			todasAsMusicasMenu.setText("Todas as Músicas");
			todasAsMusicasMenu.add(getRelatorioMusicaOrdemAlfabeticaMenuItem());
			todasAsMusicasMenu.add(getRelatorioMusicasPorCantorMenuItem());
		}
		return todasAsMusicasMenu;
	}

	/**
	 * This method initializes colecaoMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getColecaoMenu() {
		if (colecaoMenu == null) {
			colecaoMenu = new JMenu();
			colecaoMenu.setText("Coleção");
			colecaoMenu.add(getGerarColecaoMenuItem());
		}
		return colecaoMenu;
	}

	/**
	 * This method initializes gerarColecaoMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getGerarColecaoMenuItem() {
		if (gerarColecaoMenuItem == null) {
			gerarColecaoMenuItem = new JMenuItem();
			gerarColecaoMenuItem.setText("Gerar Coleção");
			gerarColecaoMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					GerarColecaoDiscosDialog dialogo = new GerarColecaoDiscosDialog(CadastroDeMusicas.this);
					
					dialogo.setResizable(false);
					// dialogo.setPreferredSize(new Dimension(409, 350));
					dialogo.setLocationRelativeTo(CadastroDeMusicas.this);
					dialogo.setModal(true);					
					dialogo.setVisible(true);
					
					System.out.println("largura: " + dialogo.getSize().width);
					System.out.println("altura: " + dialogo.getSize().height);
				}
			});
		}
		return gerarColecaoMenuItem;
	}

	/**
	 * This method initializes relatoriosCantoresMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getRelatoriosCantoresMenu() {
		if (relatoriosCantoresMenu == null) {
			relatoriosCantoresMenu = new JMenu();
			relatoriosCantoresMenu.setText("Cantores");
			relatoriosCantoresMenu.add(getCantoresComNumeroDeMusicasMenuItem());
		}
		return relatoriosCantoresMenu;
	}

	/**
	 * This method initializes cantoresComNumeroDeMusicasMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCantoresComNumeroDeMusicasMenuItem() {
		if (cantoresComNumeroDeMusicasMenuItem == null) {
			cantoresComNumeroDeMusicasMenuItem = new JMenuItem();
			cantoresComNumeroDeMusicasMenuItem.setText("Com Número de Músicas");
			cantoresComNumeroDeMusicasMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							//$hide>>$
							Fachada.criaRelatorioCantoresComNumeroDeMusicas();
							//$hide<<$
						}
					});
		}
		return cantoresComNumeroDeMusicasMenuItem;
	}

	/**
	 * This method initializes verificarArquivosMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getVerificarArquivosMenuItem() {
		if (verificarArquivosMenuItem == null) {
			verificarArquivosMenuItem = new JMenuItem();
			verificarArquivosMenuItem.setText("Verificar Arquivos");
			verificarArquivosMenuItem
					.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					VerificarArquivosDialog dialogo = new VerificarArquivosDialog(CadastroDeMusicas.this);
					
					dialogo.setResizable(false);
					dialogo.setPreferredSize(new Dimension(409, 260));
					dialogo.setLocationRelativeTo(CadastroDeMusicas.this);
					dialogo.setModal(true);
					dialogo.setVisible(true);
					
					System.out.println("largura: " + dialogo.getSize().width);
					System.out.println("altura: " + dialogo.getSize().height);
				}
			
			});
		}
		return verificarArquivosMenuItem;
	}

	/**
	 * This method initializes cadastrarDiretorioMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCadastrarDiretorioMenuItem() {
		if (cadastrarDiretorioMenuItem == null) {
			cadastrarDiretorioMenuItem = new JMenuItem();
			cadastrarDiretorioMenuItem.setText("Cadastrar Diretório");
			// cadastrarDiretorioMenuItem.setEnabled(false);
			cadastrarDiretorioMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							cadastrarTodasMusicasDeDiretorio();
						}
					});
		}
		return cadastrarDiretorioMenuItem;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//$hide>>$
			Util.iniciarDiretorioBase();
			//$hide<<$
		} catch (DiretorioBaseInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Por favor, cadastre o diretório base.",
					"Diretório Base inválido.", JOptionPane.ERROR_MESSAGE);
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CadastroDeMusicas thisClass = new CadastroDeMusicas();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
		//$hide>>$
		BDUtil.atualizarChavesUnicas();
		//$hide<<$
	}

	/**
	 * This is the default constructor
	 */
	public CadastroDeMusicas() {
		super();
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setJMenuBar(getBarraDeMenuPrincipalJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Sistema de Cadastro de Músicas");
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/figuras/icones/star_grey.png")));
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				sair();
			}
		
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				sair();
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(new Rectangle(0, 0, 800, 600));
		
		// posiciona a janela no centro da tela
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		int width = this.getWidth();
		int height = this.getHeight();
		setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);
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
			jContentPane.add(getDesktopPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private void backupBD() {
		//$hide>>$
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File("CadastroDeMusicasBD.BAK"));
		fileChooser.setDialogTitle("Salvar Backup do Banco de Dados");
		fileChooser.setApproveButtonText("Salvar");
		fileChooser.setApproveButtonToolTipText("Salvar o Backup no arquivo indicado");		
		
		int option = fileChooser.showDialog(this, "Salvar");
		
		if (option == JFileChooser.CANCEL_OPTION) {
			System.out.println("o usuário cancelou a operação");
		}
		
		if (option == JFileChooser.APPROVE_OPTION) {
			System.out.println("o usuário salvou no arquivo " + fileChooser.getSelectedFile().getAbsolutePath());
			BDUtil.backupBD(fileChooser.getSelectedFile().getAbsolutePath());
		}
		//$hide<<$
	}
	
	private void sair() {		
		System.out.println("Saindo da aplicação.");
		
		// new SplashScreen(this, "/figuras/mysql.png", "Fechando o programa", 4);
		
		// this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/figuras/icones/star_grey.png")));
		//$hide>>$
		GlobalPlayer.stop();
		player.GlobalPlayer.getGlobalPlayer().setVisible(false);
		setVisible(false);
		/*new Thread() {
			public void run() {*/
				BDUtil.desativarBD();
				//$hide<<$
		/*	}
		}.start();
		
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			
		Util.apagarArquivosTemporarios();
		System.exit(0);
	}
	
	private void cadastrarTodasMusicasDeDiretorio() {
		//$hide>>$
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		if (Util.getDiretorioBase() != null) {
			chooser.setCurrentDirectory(new File("."));
		}					
		
		int opcao = chooser.showOpenDialog(CadastroDeMusicas.this);
		
		if (opcao == JFileChooser.APPROVE_OPTION) { 
			File dir = chooser.getSelectedFile();
			File musicas[] = dir.listFiles();
			
			System.out.println("Quantidade de Arquivos: " + musicas.length);
			
			int i = 1;
			
			for (File musica : musicas) {
				String nome = musica.getName();
				System.out.println("Arquivo: " + nome);
				if (nome.toLowerCase().endsWith(".mp3")) {
					cadastrarMusica(musica);
					System.gc();
					
					i++;					
					if (i % 10 == 0) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		//$hide<<$
	}
		
	
	public void cadastrarMusica(File arquivo) {
		//$hide>>$
		System.out.println("Cadastrando o arquivo " + arquivo.getName());
		
		String nomeDoArquivo = arquivo.getPath();

		String nome = arquivo.getName();

		// pegando o nome do diretório
		String diretorio = Util.getDiretorioProximoArquivo();
		
		// pegando a duração da música
		int duracao = Util.getMP3Duration(arquivo.getPath());
		
		// tirando a extensão do nome do arquivo
		nome = nome.substring(0, nome.length() - 4);

		int divisor = nome.lastIndexOf(" - ");
		String cantor = "";
		String cantorCompleto = "";
		if (divisor != -1) {
			cantor = nome.substring(divisor + 3);
			cantorCompleto = cantor;
			cantor = cantor.trim().toUpperCase().replaceAll(" ", "");
			nome = nome.substring(0, divisor);
		}

		nome = nome.trim();

		Musica musica = new Musica();

		// setando os valores no objeto musica que será cadastrado
		musica.setNome(nome);
		musica.setDuracao(duracao);
		musica.setDiretorio(diretorio);
				
		// Comentar todo esse trecho depois
		/*try {
			Tipo tipoMusica = null;
			MusicMetadataSet src_set = new MyID3().read(arquivo);
			MusicMetadata metadata = (MusicMetadata) src_set.getSimplified();
			String genero = metadata.getGenreName();
			List<Tipo> tipos;
			tipos = Fachada.listarTipos();
			for (Tipo tipo: tipos) {
				if (tipo.getTipo().equalsIgnoreCase(genero)) {
					tipoMusica = new Tipo();
					tipoMusica.setIdTipo(tipo.getIdTipo());
					tipoMusica.setTipo(tipo.getTipo());
				}
			}
			if (tipoMusica == null) {
				tipoMusica = new Tipo();
				tipoMusica.setTipo(genero);
				Fachada.cadastrarTipo(tipoMusica);
			}
			musica.setTipo(tipoMusica);
		} catch (DataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ID3ReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		// até aqui
		
		
		System.out.println("Música: " + musica.getNome());
				
		String nomeArquivo = null;
		
		// compondo o nome do arquivo final
		nomeArquivo = musica.getNome();
		if (cantor != null && cantor.length() > 0) {
			nomeArquivo = nomeArquivo + " - " + cantor;
		}
		
		nomeArquivo += ".mp3";
		
		// testando se já existe um arquivo com o mesmo nome
		File arquivoExistente = new File(Util.getDiretorioBase() + File.separator + musica.getDiretorio() + File.separator + nomeArquivo);
		int i = 0;
		while (arquivoExistente.exists()) {
			i++;
			nomeArquivo = musica.getNome() + " #" + i;
			if (cantor != null && cantor.length() > 0) {
				nomeArquivo = nomeArquivo + " - " + cantor;
			}
			
			nomeArquivo += ".mp3";
			
			arquivoExistente = new File(Util.getDiretorioBase() + File.separator + musica.getDiretorio() + File.separator + nomeArquivo);
		}
		
		musica.setNomeArquivo(nomeArquivo);
		
		String caminhoCompleto = Util.getDiretorioBase() + File.separator + musica.getDiretorio() + File.separator +
			musica.getNomeArquivo();
			
		if (Util.copyFile(nomeDoArquivo, caminhoCompleto)) {
			File apagar = new File(nomeDoArquivo);
			apagar.delete();
			
			// cadastrar a música
			try {				
				Fachada.cadastrarMusica(musica);

				// testar se tem o nome do cantor
				if (cantor != null & !cantor.equals("")) {
					// verificar se o cantor já está cadastrado
					List<Cantor> cantores = Fachada.listarCantoresPorDiversos(null, cantor);
					
					Cantor c = null;
					
					if (cantores != null && cantores.size() > 0) {
						for (Cantor cl: cantores) {
							if (cl.getNomeSemEspacos().equals(cantor)) c = cl;
						}						
					} 
					
					if (c == null) {
						c = new Cantor();
						c.setNomeSemEspacos(cantor);
						c.setNome(cantorCompleto);
						Fachada.cadastrarCantor(c);
					}
					
					Fachada.adicionarCantorAMusica(musica, c);
				}
				
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Houve um erro ao cadastrar a música no Banco de Dados.");
			}			
		} else {
			System.out.println("Não foi possível copiar o arquivo para o destino.");
		}
		//$hide<<$
	}
	
	private JMenu getPlayerMenu() {
		if(playerMenu == null) {
			playerMenu = new JMenu();
			playerMenu.setText("Player");
			playerMenu.add(getAbrirPlayerMenuItem());
		}
		return playerMenu;
	}
	
	private JMenuItem getAbrirPlayerMenuItem() {
		if(abrirPlayerMenuItem == null) {
			abrirPlayerMenuItem = new JMenuItem();
			abrirPlayerMenuItem.setText("Abrir Player");
			abrirPlayerMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					//$hide>>$
					abrirPlayer();
					//$hide<<$
				}
			});
		}
		return abrirPlayerMenuItem;
	}
	
	private void abrirPlayer() {
		//$hide>>$
		player.GlobalPlayer.getGlobalPlayer().setVisible(true);
		//$hide<<$
	}

}
