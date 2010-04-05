package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Date;

import javazoom.jlgui.player.amp.tag.TagInfo;
import javazoom.jlgui.player.amp.tag.TagInfoFactory;
import bd.BDUtil;
import classesbasicas.Constantes;
import classesbasicas.Musica;
import exceptions.DataException;
import exceptions.DiretorioBaseInvalidoException;

public class Util {
	
	private static int musicasPorDisco = 600;
	private static String tipoDeMidia = "DVD";
	private static String diretorioBase = null;
	
	private static String caminhoDiretorioTemp = null;
	
	public static String diretorioPassado = null;

	public static void iniciarDiretorioBase() throws DiretorioBaseInvalidoException {
		try {
			diretorioBase = BDUtil.getDiretorioBase();
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getDiretorioBase() {
		return diretorioBase;
	}
	
	public static String getCaminhoDiretorioTemporario() {
		if (caminhoDiretorioTemp == null) {
			caminhoDiretorioTemp = diretorioBase + File.separator + Constantes.nomeDiretorioTemp;	
			File tempDir = new File(caminhoDiretorioTemp);
			if (!tempDir.exists()) {
				tempDir.mkdir();
			}
		}
		return caminhoDiretorioTemp;
	}
	
	public static String salvarArquivoDiretorioTemporario(File arquivo) throws IOException {
		String nomeArquivoRet = null;
		String nomeArquivo;
		
		File diretorioTemporario = new File(getCaminhoDiretorioTemporario());
		
		nomeArquivo = Util.gerarChaveUnica("" + (new Date().getTime()) + arquivo.getPath());
		nomeArquivo = diretorioTemporario.getPath() + File.separator + nomeArquivo;
		
		File arquivoTemp = new File(nomeArquivo);
		// arquivoTemp.createNewFile();
		
		copyFile(arquivo.getPath(), arquivoTemp.getPath());
		
		nomeArquivoRet = nomeArquivo;
		return nomeArquivoRet;
	}
	
	public static void setDiretorioBase(String dir) {
		diretorioBase = dir;
		try {
			BDUtil.salvarDiretorioBase(dir);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String intToString(int inteiro) {
		int c = inteiro - 1;
		
		int char1;		
		int char2;
		int char3;
		
		int a = (int) 'A';
		int z = (int) 'Z';
		
		int numeroDeLetras = z - a + 1;
		
		char3 = c % numeroDeLetras;
		c = c / numeroDeLetras;
		char2 = c % numeroDeLetras;
		c = c / numeroDeLetras;
		char1 = c % numeroDeLetras;
		c = c / numeroDeLetras;
		
		String r = "" + ((char) (char1 + a)) + ((char) (char2 + a)) + ((char) (char3 + a));
		
		return r;
	}
	
	public static int stringToInt(String string) {
		char char1 = string.charAt(0);
		char char2 = string.charAt(1);
		char char3 = string.charAt(2);
		
		int a = (int) 'A';
		int z = (int) 'Z';
		
		int numeroDeLetras = z - a + 1;
		
		int num = 0;
		
		num += (((int) char1) - a) * numeroDeLetras * numeroDeLetras;
		num += (((int) char2) - a) * numeroDeLetras;
		num += (((int) char3) - a) + 1;
		
		return num;		
	}
	
	public static int getMP3Duration(String filePath) {
		int duracao = 0;

		TagInfoFactory factory = TagInfoFactory.getInstance();
	    TagInfo taginfo = factory.getTagInfo(filePath);
	    duracao = (int) taginfo.getPlayTime();
		
		return duracao;
	}
	
	// retorna o número do dvd a ser utilizado para o cadastro de uma nova música
	/*public static int getProximoNumeroDVD() throws DiretorioBaseInvalidoException {
		if (diretorioBase == null) {
			throw new DiretorioBaseInvalidoException("Por favor, cadastre o Diretório Base!");
		} else {
			int maior = 1;
			
			File diretorio = new File(diretorioBase);
			File[] files = diretorio.listFiles();
			
			for (File f: files) {
				if (f.isDirectory()) {
					String nomeDiretorio = f.getName();
										
					if (nomeDiretorio.startsWith("DVD")) {
						// System.out.println(nomeDiretorio);
						// System.out.println(nomeDiretorio.substring(3));
						
						try {
							int num = Integer.parseInt(nomeDiretorio.substring(3));
							maior = maior > num ? maior : num;
						} catch (NumberFormatException e) {
							// TODO: handle exception
						}						
					}
				}
			}
			
			if (getProximoNumeroDeMusica(maior) > musicasPorDisco) {
				maior++;
			}
			
			System.out.println("Número do DVD: " + maior);
			return maior;
		}
	}*/
	
	/*public static int getProximoNumeroDeMusica(int dvd) throws DiretorioBaseInvalidoException {
		if (diretorioBase == null) {
			throw new DiretorioBaseInvalidoException();
		}
		
		DecimalFormat nf = new DecimalFormat("00");
		String caminhoCompleto = diretorioBase + "\\DVD" + nf.format(dvd);
		
		File diretorio = new File(caminhoCompleto);
		if (!diretorio.exists()) {
			
			// diretorio.createNewFile();
			diretorio.mkdir();
			
			return 1;
		}
		
		File[] files = diretorio.listFiles();
		if (files == null || files.length == 0) return 1;
		
		int maior = 0;
		
		for (File f: files) {
			String num = f.getName().substring(0, 3);
			if (stringToInt(num) > maior) maior = stringToInt(num);
		}
		
		maior++;
		System.out.println("Maior Número do diretório: " + maior + " (" + intToString(maior) + ")");
		return maior;
	}*/
	
	public static void main(String args[]) {		
//		System.out.println("1 - " + intToString(1));
//		System.out.println("2 - " + intToString(2));
//		System.out.println("30 - " + intToString(30));
//		
//		System.out.println("AAA - " + stringToInt("AAA"));
//		System.out.println("AAB - " + stringToInt("AAB"));
//		System.out.println("ABD - " + stringToInt("ABD"));
		
		// mudaNomes();
		
		// cadastrarMusicasPorDiretorio();
		// cadastrarMusicasPorSelecao();
		// listarMusicas();
		
	/*	diretorioBase = "C:\\comp\\Minhas músicas\\Imburana - Músicas\\Coleção de Músicas";
		try {
			getProximoNumeroDVD();
			getProximoNumeroDeMusica(2);
		} catch (DiretorioBaseInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// apagaLetrasIniciais();
		
		
		System.exit(0);
	}
	
	
	
	/**
	 * Copia arquivos fisicamente
	 * @param  inFile  Caminho completo do arquivo de origem
	 * @param  outFile Caminho completo do arquivo de destino
	 * @return true se a cópia do arquivo for realizada com sucesso
	 */
	public static boolean copyFile(String inFile, String outFile) {
		InputStream is = null;
		OutputStream os = null;
		byte[] buffer;
		boolean success = true;
		try {
			is = new FileInputStream(inFile);
			os = new FileOutputStream(outFile);
			buffer = new byte[is.available()];
			is.read(buffer);
			os.write(buffer);
		} catch (IOException e) {
			success = false;
		} catch (OutOfMemoryError e) {
			success = false;
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}				
			} catch (IOException e) {
			}
		}
		return success;
	}

	public static int getMusicasPorDisco() {
		return musicasPorDisco;
	}

	public static void setMusicasPorDisco(int musicasPorDisco) {
		Util.musicasPorDisco = musicasPorDisco;
	}

	public static String getTipoDeMidia() {
		return tipoDeMidia;
	}

	public static void setTipoDeMidia(String tipoDeMidia) {
		Util.tipoDeMidia = tipoDeMidia;
	}

	public static String getDiretorioProximoArquivo() {
		return BDUtil.getDiretorioProximoArquivo();
	}
	
	public static String compoeNomeArquivo(Musica musica) {
		String nomeArquivo = null;
		
		if (musica == null) return null;
		
		// compondo o nome do arquivo final
		nomeArquivo = musica.getNome();
		if (musica.getCantores() != null && musica.getCantores().size() > 0) {
			nomeArquivo = nomeArquivo + " - "
					+ musica.getCantores().get(0).getNomeSemEspacos();
		}
		
		nomeArquivo += ".mp3";
		
		// testando se já existe um arquivo com o mesmo nome
		File arquivo = new File(getDiretorioBase() + File.separator + musica.getDiretorio() + File.separator + nomeArquivo);
		int i = 0;
		while (arquivo.exists()) {
			i++;
			nomeArquivo = musica.getNome() + " #" + i;
			if (musica.getCantores() != null && musica.getCantores().size() > 0) {
				nomeArquivo = nomeArquivo + " - "
						+ musica.getCantores().get(0).getNomeSemEspacos();
			}
			nomeArquivo += ".mp3";
			
			arquivo = new File(getDiretorioBase() + File.separator + musica.getDiretorio() + File.separator + nomeArquivo);
		}
		
		
		return nomeArquivo;		
		
	}
	
	public static String formataDuracao(int duracao) {
		DecimalFormat df = new DecimalFormat("00");
		return duracao / 60 + ":" + df.format(duracao % 60);
	}
	
	// transforma a duração de String (00:00) para int
	public static int getDuracao(String duracaoString) {
		int duracao = 0;
		
		
		
		return duracao;
	}
	
	public static boolean verificarArquivo(Musica m) {
		boolean existeArquivo = false;
		
		String caminhoCompleto = getDiretorioBase() + File.separator + 
			m.getDiretorio() + File.separator + m.getNomeArquivo();
		
		File arquivo = new File(caminhoCompleto);
		
		if (arquivo.exists()) {
			existeArquivo = true;
		}
		
		return existeArquivo;
	}
	
	public static String getNomeArquivo(Musica m) {
		return getDiretorioBase() + File.separator + 
		m.getDiretorio() + File.separator + m.getNomeArquivo();
	}
	
	public static String gerarChaveUnica(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(s.getBytes());
			BigInteger hash = new BigInteger(1, md.digest());
			return hash.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void apagarArquivosTemporarios() {
		File tempDir = new File(getCaminhoDiretorioTemporario());
		
		if (!tempDir.exists()) return;
		
		File arquivos[] = tempDir.listFiles();
		for (File arquivo: arquivos) {
			arquivo.delete();
		}
	}
}
