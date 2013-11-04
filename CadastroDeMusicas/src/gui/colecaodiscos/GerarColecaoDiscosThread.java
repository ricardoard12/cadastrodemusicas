package gui.colecaodiscos;

import exceptions.DataException;
import fachada.Fachada;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cmc.music.common.ID3ReadException;
import org.cmc.music.common.ID3WriteException;
import org.cmc.music.metadata.MusicMetadata;
import org.cmc.music.metadata.MusicMetadataSet;
import org.cmc.music.myid3.MyID3;

import util.Util;
import classesbasicas.Assunto;
import classesbasicas.Constantes;
import classesbasicas.Musica;
import classesbasicas.MusicaNaColecao;

public class GerarColecaoDiscosThread extends Thread {
	private GerarColecaoDiscosPanel painel = null;
	private Map<String, String> map = null;
	private List<Musica> colecao = null;
	
	public GerarColecaoDiscosThread(GerarColecaoDiscosPanel painel, Map<String, String> map) {
		this.painel = painel;
		this.map = map;
	}
	
	public GerarColecaoDiscosThread(List<Musica> musicas, GerarColecaoDiscosPanel painel, Map<String, String> map) {
		this.painel = painel;
		this.map = map;
		colecao = musicas;
	}
	
	public void run() {		
		painel.getStatusTextField().setBackground(Color.RED);
		painel.getStatusTextField().setText("Consultando Músicas no BD");		
		painel.getLogTextArea().append("Consultando Músicas no Banco de Dados\n");
		painel.getProgressBar().setBackground(Color.RED);
		
		try {
			List<Musica> musicasPorOrdemAlfabetica;
			if (colecao == null) {
				musicasPorOrdemAlfabetica = Fachada.listarTodasAsMusicasEmOrdemAlfabetica();	
			} else {
				musicasPorOrdemAlfabetica = colecao;
			}
						
			List<MusicaNaColecao> colecaoOrdemAlfabetica = new ArrayList<MusicaNaColecao>();			
			
			// configurando a barra de progresso
			painel.getProgressBar().setMinimum(0);
			painel.getProgressBar().setMaximum(musicasPorOrdemAlfabetica.size());
			
			painel.getTotalDeMusicasTextField().setText("" + musicasPorOrdemAlfabetica.size());
			painel.getLogTextArea().append("Total de Músicas: " + musicasPorOrdemAlfabetica.size() + "\n");	
			
			painel.getStatusTextField().setText("Copiando Músicas");
			painel.getLogTextArea().append("Iniciando a cópia das Músicas...\n\n");
			
			// criando o novo diretório onde será criada a coleção
			File diretorio = new File(map.get(Constantes.LOCAL_DE_DESTINO) + File.separator + map.get(Constantes.NOME_COLECAO).replaceAll("[/\\|?<>*]", "_"));
			int i = 1;
			while (diretorio.exists()) {				
				diretorio = new File(map.get(Constantes.LOCAL_DE_DESTINO) + File.separator + map.get(Constantes.NOME_COLECAO).replaceAll("[/\\|?<>*]", "_") + i);
				i++;
			}
			
			// TODO inserir uma verificação
			diretorio.mkdir();
			
			String midia = map.get(Constantes.MIDIA); 
			int numeroDoDisco = 1;
			int numeroDaMusicaNoDisco = 1;
			long tamanhoTotal = 0;
			long tamanhoMaximo;
			if (midia.equals(Constantes.MIDIA_CD)) {
				tamanhoMaximo = Constantes.TAMANHO_MAXIMO_CD;
			} else if (midia.equals(Constantes.MIDIA_DVD)){
				tamanhoMaximo = Constantes.TAMANHO_MAXIMO_DVD;
			} else { // cd de áudio
				tamanhoMaximo = Constantes.TAMANHO_MAXIMO_CD_AUDIO; // equivalente a 75 minutos
			}
			boolean limitarNumeroDeMusicas = map.get(Constantes.LIMITAR_MUSICAS).equals("true") ? true : false;
			int limiteDeMusicas = 0;
			if (limitarNumeroDeMusicas == true) {
				limiteDeMusicas = Integer.parseInt(map.get(Constantes.LIMITE_DE_MUSICAS));
			}
			
			// TODO inserir verificação
			DecimalFormat df = new DecimalFormat("000");
			File diretorioDoDisco = new File(diretorio.getPath() + File.separator + midia + df.format(numeroDoDisco));
			diretorioDoDisco.mkdir();
			
			for (Musica m: musicasPorOrdemAlfabetica) {
				painel.getLogTextArea().append("Copiando a Música " + m.getNome() + "\n");
				painel.getLogTextArea().setCaretPosition(painel.getLogTextArea().getText().length());
				painel.getMusicaAtualTextField().setText(m.getNome());
				
				File origem = new File(Util.getDiretorioBase() + File.separator + ""/*m.getDiretorio()*/ + 
						File.separator + ""/*m.getNomeArquivo()*/);
				
				// testando pra ver se o número máximo de músicas foi atingido
				if (limitarNumeroDeMusicas) {
					if (numeroDaMusicaNoDisco > limiteDeMusicas) {
						numeroDaMusicaNoDisco = 1;
						numeroDoDisco++;
						tamanhoTotal = 0;
						
						diretorioDoDisco = new File(diretorio.getPath() + File.separator + midia + df.format(numeroDoDisco));
						diretorioDoDisco.mkdir();
					}
				}
				
				// testar pra ver se, com a cópia dessa música, o tamanho máximo não será excedido
				if (midia.equals(Constantes.MIDIA_CD_AUDIO)) {
					tamanhoTotal += m.getDuracao();
				} else {
					tamanhoTotal += origem.length();
				}
				if (tamanhoTotal > tamanhoMaximo) {
					if (midia.equals(Constantes.MIDIA_CD_AUDIO)) {
						tamanhoTotal = m.getDuracao();
					} else {
						tamanhoTotal = origem.length();
					}
					numeroDoDisco++;
					numeroDaMusicaNoDisco = 1;
					diretorioDoDisco = new File(diretorio.getPath() + File.separator + midia + df.format(numeroDoDisco));
					diretorioDoDisco.mkdir();
				}
				
				// copiar o arquivo
				painel.getLogTextArea().append("Disco " + numeroDoDisco + ", Faixa " + numeroDaMusicaNoDisco + "... ");
				painel.getLogTextArea().setCaretPosition(painel.getLogTextArea().getText().length());
				String caminhoDestino = diretorioDoDisco.getPath() + File.separator;
				if (map.get(Constantes.INSERIR_LETRAS_INICIAIS).equals("true")) {
					caminhoDestino += Util.intToString(numeroDaMusicaNoDisco) + " - ";
				}
				caminhoDestino += m.getNome();
				if (m.getCantores() != null && m.getCantores().size() > 0) {
					caminhoDestino += " - " + m.getCantores().get(0).getNomeSemEspacos();
				}
				caminhoDestino += ".mp3";
				File destino = new File(caminhoDestino);
				i = 1;
				while (destino.exists()) {
					caminhoDestino = diretorioDoDisco.getPath() + File.separator;
					if (map.get(Constantes.INSERIR_LETRAS_INICIAIS).equals("true")) {
						caminhoDestino += Util.intToString(numeroDaMusicaNoDisco) + " - ";
					}
					caminhoDestino += m.getNome() + " #" + df.format(i);
					if (m.getCantores() != null || m.getCantores().size() > 0) {
						caminhoDestino += " - " + m.getCantores().get(0).getNomeSemEspacos();
					}
					caminhoDestino += ".mp3";
					destino = new File(caminhoDestino);					
				}
				Util.copyFile(origem.getPath(), destino.getPath());
				
				File src = destino;
				try {
					MusicMetadataSet src_set = new MyID3().read(src);
					if (src_set == null) {
						System.out.println("Arquivo sem informações de Tags");
					}
					
					MusicMetadata metadata = (MusicMetadata) src_set.getSimplified();
					
					metadata.setAlbum(map.get(Constantes.NOME_COLECAO) + ", Disco " + df.format(numeroDoDisco));
					metadata.setArtist(m.getCantores() == null || m.getCantores().size() == 0 ? "Não Informado" : m.getCantores().get(0).getNome());
					metadata.setDiscNumber(numeroDoDisco);
					metadata.setSongTitle(m.getNome());
					metadata.setTrackNumberNumeric(numeroDaMusicaNoDisco);
					
					new MyID3().update(destino, src_set, metadata);
					// System.out.println(src_set);
					
				} catch (ID3ReadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ID3WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				/*try {
					destino.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Não foi possível criar o arquivo de destino.");
				}*/
				
				/*TagOptionSingleton.getInstance().setDefaultSaveMode(TagConstant.MP3_FILE_SAVE_WRITE);
				TagOptionSingleton.getInstance().setOriginalSavedAfterAdjustingID3v2Padding(false);
				MP3File mp3 = null;
				try {
					mp3 = new MP3File(origem);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TagException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// verificando se o arquivo foi aberto com sucesso
				if (mp3 != null) {
					
					System.out.println(mp3.getID3v1Tag());
					System.out.println("-------------------------------------------------------------");
					System.out.println(mp3.getID3v2Tag());
					System.out.println("22222222222222222222222222222222222222222222222222222222222222222222222222222");
					// System.out.println(mp3.getID3v1Tag());
					
					if (mp3.getID3v1Tag() != null) {
						try {
							mp3.getID3v1Tag().setAlbumTitle("Coleção UDV, Disco " + df.format(numeroDoDisco));
						} catch(Exception e) {}
						
						try {
							mp3.getID3v1Tag().setAuthorComposer(m.getCantores() == null || m.getCantores().size() == 0 ? "Não Informado" : m.getCantores().get(0).getNome());
						} catch(Exception e) {}
						
						try {
							mp3.getID3v1Tag().setLeadArtist(m.getCantores() == null || m.getCantores().size() == 0 ? "Não Informado" : m.getCantores().get(0).getNome());
						} catch(Exception e) {}
						
						try {
							mp3.getID3v1Tag().setSongTitle(df.format(numeroDaMusicaNoDisco) + " " + m.getNome());
						} catch(Exception e) {}
						
						try {
							mp3.getID3v1Tag().setTrackNumberOnAlbum("" + numeroDaMusicaNoDisco);
						} catch(Exception e) {}
					} else {
						ID3v1_1 tag = new ID3v1_1();
						
						try {
							tag.setAlbumTitle("Coleção UDV, Disco " + df.format(numeroDoDisco));	
						} catch (Exception e) {}
						
						try {
							// tag.setAuthorComposer(m.getCantores() == null || m.getCantores().size() == 0 ? "Não Informado" : m.getCantores().get(0).getNome());
						} catch (Exception e) {}
						
						try {
							tag.setLeadArtist(m.getCantores() == null || m.getCantores().size() == 0 ? "Não Informado" : m.getCantores().get(0).getNome());
						} catch (Exception e) {}
						
						try {
							tag.setSongTitle(df.format(numeroDaMusicaNoDisco) + " " + m.getNome());
						} catch (Exception e) {}
			
						try {
							// tag.setTrackNumberOnAlbum("" + numeroDaMusicaNoDisco);
						} catch (Exception e) {}
						
						try {
							tag.setSongComment("Músicas UDV");
						} catch (Exception e) {}
						
						try {
							tag.setSongGenre("UDV");
						} catch (Exception e) {}
						
						try {
							tag.setYear("2003");
						} catch (Exception e) {}
						
						mp3.setID3v1Tag(tag);
					}

					if (mp3.getID3v2Tag() != null) {
						mp3.getID3v2Tag().setAlbumTitle("Coleção UDV, Disco " + df.format(numeroDoDisco));
						mp3.getID3v2Tag().setAuthorComposer(m.getCantores() == null || m.getCantores().size() == 0 ? "Não Informado" : m.getCantores().get(0).getNome());
						mp3.getID3v2Tag().setLeadArtist(m.getCantores() == null || m.getCantores().size() == 0 ? "Não Informado" : m.getCantores().get(0).getNome());
						mp3.getID3v2Tag().setSongTitle(df.format(numeroDaMusicaNoDisco) + " " + m.getNome());
						mp3.getID3v2Tag().setTrackNumberOnAlbum("" + numeroDaMusicaNoDisco);
					
						mp3.getID3v2Tag().setSongComment("Músicas UDV");
						mp3.getID3v2Tag().setSongGenre("UDV");
						mp3.getID3v2Tag().setSongLyric("Não Disponível");
						mp3.getID3v2Tag().setYearReleased("1999");
						
					} else {
					
					
						ID3v2_3 tag = new ID3v2_3();
						
						// tag.
						
						tag.setAlbumTitle("Coleção UDV, Disco " + df.format(numeroDoDisco));
						tag.setAuthorComposer(m.getCantores() == null || m.getCantores().size() == 0 ? "Não Informado" : m.getCantores().get(0).getNome());
						tag.setLeadArtist(m.getCantores() == null || m.getCantores().size() == 0 ? "Não Informado" : m.getCantores().get(0).getNome());
						tag.setSongTitle(df.format(numeroDaMusicaNoDisco) + " " + m.getNome());
						tag.setTrackNumberOnAlbum("" + numeroDaMusicaNoDisco);
						
						tag.setSongComment("Músicas UDV");
						tag.setSongGenre("UDV");
						tag.setSongLyric("Não Disponível");
						tag.setYearReleased("1999");
						
						mp3.setID3v2Tag(tag);
						
					}

					try {
						mp3.save(destino);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (TagException e) {
						e.printStackTrace();
					}
					
					MP3File mp32;
					try {
						mp32 = new MP3File(destino);
						System.out.println(mp32.getID3v1Tag());
						System.out.println("-------------------------------------------------------------");
						System.out.println(mp32.getID3v2Tag());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TagException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					
					
					
				// }
				
				painel.getLogTextArea().append("OK\n\n");
				// TODO inserir teste de cópia do arquivo
				
				MusicaNaColecao musicaNaColecao = new MusicaNaColecao();
				musicaNaColecao.setNomeMusica(m.getNome());
				if (m.getCantores() != null && m.getCantores().size() > 0) {
					musicaNaColecao.setCantor(m.getCantores().get(0).getNome());
				}
				if (m.getTipo() != null) {
					musicaNaColecao.setTipo(m.getTipo().getTipo());
				}
				musicaNaColecao.setNumeroDVD(numeroDoDisco);
				musicaNaColecao.setNumeroFaixa(numeroDaMusicaNoDisco);
				musicaNaColecao.setDuracaoMusica(m.getDuracao());
				if (m.getAssuntos() != null && m.getAssuntos().size() > 0) {
					musicaNaColecao.setAssunto("");
					for (Assunto a: m.getAssuntos()) {
						musicaNaColecao.setAssunto(musicaNaColecao.getAssunto() + a.getAssunto());
						if (m.getAssuntos().indexOf(a) < m.getAssuntos().size() - 1) {
							musicaNaColecao.setAssunto(musicaNaColecao.getAssunto() + ", ");
						}
					}
				}
				colecaoOrdemAlfabetica.add(musicaNaColecao);
				
				numeroDaMusicaNoDisco++;
				
				painel.getProgressBar().setValue(musicasPorOrdemAlfabetica.indexOf(m) + 1);
			}
						
			painel.getProgressBar().setForeground(Color.RED);
			painel.getProgressBar().setString("Aguarde...");
			
			// criar agora os relatórios
			painel.getLogTextArea().append("Preparando para criar as listagens...\n");
			painel.getLogTextArea().setCaretPosition(painel.getLogTextArea().getText().length());
			painel.getStatusTextField().setText("Criando as listagens");
			
			boolean pdf = map.get(Constantes.RELATORIO_PDF).equals("true") ? true : false;
			boolean html = map.get(Constantes.RELATORIO_HTML).equals("true") ? true : false;
			Map<String, String> reportParameters = new HashMap<String, String>();
			reportParameters.put(Constantes.MIDIA, midia.equals(Constantes.MIDIA_DVD) ? Constantes.MIDIA_DVD : Constantes.MIDIA_CD);
			reportParameters.put(Constantes.NOME_COLECAO, map.get(Constantes.NOME_COLECAO));
			
			painel.getLogTextArea().append("criando as listagens...\n");
			painel.getLogTextArea().setCaretPosition(painel.getLogTextArea().getText().length());
			
			if (map.get(Constantes.RELATORIO_ORDEM_ALFABETICA).equals("true")) {
				Fachada.criaRelatorioColecaoPorOrdemAlfabetica(colecaoOrdemAlfabetica, reportParameters, pdf, html, diretorio.getPath());
			}
			if (map.get(Constantes.RELATORIO_POR_CANTOR).equals("true")) {
				List<Musica> musicasPorCantor = Fachada.listarTodasAsMusicasOrdenarPorCantor();
				List<MusicaNaColecao> colecaoPorCantor = new ArrayList<MusicaNaColecao>();
				
				for (Musica m: musicasPorCantor) {
					int indice = musicasPorOrdemAlfabetica.indexOf(m);
					
					if (indice != -1) {
						colecaoPorCantor.add(colecaoOrdemAlfabetica.get(indice));
					}
				}
				
				Fachada.criaRelatorioColecaoPorCantor(colecaoPorCantor, reportParameters, pdf, html, diretorio.getPath());
			}
			if (map.get(Constantes.RELATORIO_POR_ASSUNTO).equals("true")) {
				List<Musica> musicasPorAssunto = Fachada.listarTodasAsMusicasOrdenarPorAssunto();
				List<MusicaNaColecao> colecaoPorAssunto = new ArrayList<MusicaNaColecao>();
				List<Musica> listaTemp = new ArrayList<Musica>();
				List<Integer> nTemp = new ArrayList<Integer>();
				
				for (Musica m: musicasPorAssunto) {
					if (m.getAssuntos() != null && m.getAssuntos().size() > 1) {
						int n = listaTemp.indexOf(m);
						
						if (n == -1) {
							n = 0;
							listaTemp.add(m);
							nTemp.add(n);
						}
					}
					
					int indice = musicasPorOrdemAlfabetica.indexOf(m);
					
					if (indice != -1) {
						if (m.getAssuntos() != null && m.getAssuntos().size() > 1) {
							MusicaNaColecao musicaTemp = (MusicaNaColecao) colecaoOrdemAlfabetica.get(indice).clone();
							
							int ind = listaTemp.indexOf(m);
							int t = nTemp.get(ind);
							
							musicaTemp.setAssunto(m.getAssuntos().get(t).getAssunto());
							
							nTemp.set(ind, t + 1);
							
							colecaoPorAssunto.add(musicaTemp);
						} else {
							colecaoPorAssunto.add(colecaoOrdemAlfabetica.get(indice));
						}						
					}
				}
				
				Fachada.criaRelatorioColecaoPorAssunto(colecaoPorAssunto, reportParameters, pdf, html, diretorio.getPath());
			}	
			painel.getLogTextArea().append("Listagens criadas com sucesso.\n\n");
			painel.getLogTextArea().setCaretPosition(painel.getLogTextArea().getText().length());
			painel.getProgressBar().setForeground(Color.GREEN);
			painel.getStatusTextField().setBackground(Color.GREEN);
			painel.getLogTextArea().append("Operação concluída com sucesso!");
			painel.getLogTextArea().setCaretPosition(painel.getLogTextArea().getText().length());
			painel.getProgressBar().setString("Concluído!");
			painel.getStatusTextField().setText("Operação Concluída com Sucesso!");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Override hashCode.
	 *
	 * @return the Objects hashcode.
	 */
	public int hashCode() {
		int hashCode = super.hashCode();
		hashCode = 31 * hashCode + (painel == null ? 0 : painel.hashCode());
		hashCode = 31 * hashCode + (map == null ? 0 : map.hashCode());
		return hashCode;
	}
}
