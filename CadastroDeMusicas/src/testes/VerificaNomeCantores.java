package testes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.cmc.music.clean.Diacriticals;
import org.cmc.music.common.ID3ReadException;
import org.cmc.music.metadata.MusicMetadata;
import org.cmc.music.metadata.MusicMetadataSet;
import org.cmc.music.myid3.MyID3;

import util.Util;
import classesbasicas.Cantor;
import classesbasicas.Musica;
import exceptions.DataException;
import exceptions.DiretorioBaseInvalidoException;
import fachada.Fachada;

public class VerificaNomeCantores {
	private static void verificarNomesCantores() {
		/*try {

			int contagem = 0;
			
			FileWriter file = new FileWriter("cantores.txt");
			List<Musica> musicas;
			
			musicas = Fachada.listarTodasAsMusicasEmOrdemAlfabetica();
			
			for (Musica musica: musicas) {
				String nome = musica.getNomeArquivo();
				
				String nomeCantor = nome.substring(0, nome.length() - 4);
				
				int divisor = nomeCantor.lastIndexOf(" - ");
				if (divisor != -1) {
					nomeCantor = nomeCantor.substring(divisor + 3);
					nomeCantor = nomeCantor.trim().toUpperCase().replaceAll(" ", "");
				} else {
					nomeCantor = "";
				}
				
				Cantor cantor = musica.getCantores().isEmpty() ? null : musica.getCantores().get(0);
				
				if (!nomeCantor.equals("") && !cantor.getNomeSemEspacos().equals(nomeCantor)) {
					contagem++;
					file.write(musica.getNomeArquivo() + " ==> " + cantor.getNome() +  "\n");
				}
			}
			
			file.write("" + contagem + " problemas.");
			
			file.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void testaCantoresDiretorio() {
		
	}
	
	private static void testaDuracaoMusicas() {
		/*List<Musica> musicas;
		int i = 0;

		try {
			Util.iniciarDiretorioBase();
		} catch (DiretorioBaseInvalidoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			musicas = Fachada.listarTodasAsMusicasEmOrdemAlfabetica();
			
			
			System.out.println(musicas.size() + "Músicas cadastradas");
			for (Musica m: musicas) {
			try {
				
					String nomeArquivo = Util.getNomeArquivo(m);
					
					int duracaoMP3 = Util.getMP3Duration(nomeArquivo);
					
					if (m.getDuracao() == 0 || Math.abs(duracaoMP3 - m.getDuracao()) == 1) {
						m.setDuracao(duracaoMP3);
						Fachada.alterarMusica(m);
					}
					
					if (duracaoMP3 != m.getDuracao()) {
						System.out.println(m.getIdMusica() + " - " + m.getNome() + " - Duracao Lida: " + duracaoMP3 + " - Duracao Antiga: " + m.getDuracao());
						i++;
					}
				
			} catch (Exception e) {
				System.out.println(m.getIdMusica() + " - " + m.getNome() + " - " + Util.getNomeArquivo(m));
			}
			}	
			
			
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Total de Músicas: " + i);*/
		
	
	}
	
	// muda os nomes dos arquivos para os nomes contidos no ID3 tag
	private static void mudaNomeArquivos(String caminho) {
		File dir = new File(caminho);
		
		if (!dir.isDirectory()) {
			System.out.println("O caminho especificado não é um diretório.");
			return;
		}
		
		File[] arquivos = dir.listFiles();
		
		for (File arquivo: arquivos) {
			// verificando se é um arquivo mp3
			if (!arquivo.isDirectory() && arquivo.getPath().toLowerCase().endsWith(".mp3")) {
				try {
					MusicMetadataSet src_set = new MyID3().read(arquivo);
										
					if (src_set == null) {
						System.out.println("Arquivo sem informações de Tags");
					}
					
					
					MusicMetadata metadata = (MusicMetadata) src_set.getSimplified();
					
					String caminhoFinal = "";
					
					// System.out.println(MusicMetadata);
					String genero = metadata.getGenreName();
					
					
					/*if (metadata.getArtist() == null || metadata.getArtist().trim().equals("") || metadata.getArtist().equalsIgnoreCase("a nomear")) {
						caminhoFinal = metadata.getSongTitle();
					} else {
						caminhoFinal = metadata.getSongTitle() + " - " + metadata.getArtist();
					}*/
					
					// caminhoFinal = arquivo.getParent() + File.separator + caminhoFinal + ".mp3";
					// System.out.println(caminhoFinal);
					
					// mudando o nome do arquivo
					// arquivo.renameTo(new File(caminhoFinal));
					// System.out.println("Arquivo renomeado: " + caminhoFinal);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Bronca...");
					System.out.println(arquivo.getPath());
					e.printStackTrace();					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		mudaNomeArquivos("D:\\HD Externo Renatinho\\Musicas Bruno");
		// mudaNomeArquivos("D:\\Teste");
	}
}
