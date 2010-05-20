package util;

import java.io.File;

import javax.swing.JFileChooser;

public class InUtil {
	// muda os nomes dos arquvos no diretório, colocando antes dos nomes de todos 
	// os arquivos uma numeração (com letras)
	public static void mudaNomes() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int opcao = chooser.showOpenDialog(null);
		
		if (opcao == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			File files[] = file.listFiles();
			
			for (int i = 0; i < files.length; i++) {
				// System.out.println(files[i].getPath());
				String nome = files[i].getName();
				System.out.println(nome);
				nome = Util.intToString(i + 1) + " - " + nome;
				System.out.println(nome);
				
				String path;
				path = files[i].getParent();
				System.out.println(path);
				
				String nomeMudado = path + "\\" + nome;
				
				
				System.out.println(nomeMudado);
				File dest = new File(nomeMudado);
				files[i].renameTo(dest);
			}
		}
	}	
	
	
	// cadastra todas as músicas de um diretório no BD (o usuário escolhe o diretório)
	/*public static void cadastrarMusicasPorDiretorio() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(new File("C:\\comp\\Minhas músicas\\Imburana - Músicas\\Coleção de Músicas"));
		
		int opcao = chooser.showOpenDialog(null);
		
		if (opcao == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			File files[] = file.listFiles();
			
			for (int i = 0; i < files.length; i++) {
			// for (int i = 0; i < 1; i++) {
				// System.out.println(files[i].getPath());
				
				Musica musica = new Musica();
				
				String nome = files[i].getName();
				
				// pegando a duração da música
				int duracao = Util.getMP3Duration(files[i].getPath());
				
				// pegando o nome do arquivo
				String nomeArquivo = files[i].getName();
				
				// pegando o numero do dvd
				String numdvd = files[i].getParent();
				numdvd = numdvd.substring(numdvd.length() - 2);
				
				// pegando o numero da faixa
				String numfaixa = nome.substring(0, 3);
				
				// pegando o nome da música (pode ainda ter o nome do cantor nesse nome, por isso ele pode ter uma modificação depois
				nome = nome.substring(6, nome.length() - 4);
				
				int divisor = nome.lastIndexOf(" - ");
				String cantor = "";
				if (divisor != -1) {
					cantor = nome.substring(divisor + 3);
					cantor = cantor.trim().toUpperCase().replaceAll(" ", "");
					nome = nome.substring(0, divisor);
				}	
				
				nome = nome.trim();
				
				int numerodafaixa = Util.stringToInt(numfaixa);
				int numerododvd = Integer.parseInt(numdvd);
				
				System.out.println("");
				System.out.println("Nome do arquivo: " + nomeArquivo);
				System.out.println("Nome da música: " + nome);
				if (!cantor.equals("")) System.out.println("Cantor: " + cantor);
				if (duracao != 0) System.out.println("Duração: " + duracao / 60 + ":" + duracao % 60);
				System.out.println("Número do dvd: " + numerododvd);
				System.out.println("Número da faixa: " + numerodafaixa);
				
				// setando os valores no objeto musica que será cadastrado
				musica.setNomeArquivo(nomeArquivo);
				musica.setNome(nome);
				musica.setDuracao(duracao);
				musica.setNumeroDvd(numerododvd);
				musica.setNumeroFaixa(numerodafaixa);
				
				// tentando recuperar o cantor (pra o caso de já existir)
				if (!cantor.equals("")) {
					CantorDAO cantorDAO = new CantorDAOMySQL();
					try {
						List<Cantor> lista = cantorDAO.listarCantoresPorNomeSemEspacos(cantor);
						if (lista.size() == 0) {
							// o cantor ainda não foi cadastrado, cadastrar o cantor
							Cantor c = new Cantor();
							c.setNomeSemEspacos(cantor);
							cantorDAO.cadastrarCantor(c);
							lista.add(c);
						}
						musica.setCantores(lista);
					} catch (DataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 					
				} else {
					musica.setCantores(null);
				}
				
				MusicaDAO musicaDAO = new MusicaDAOMySQL();
				try {
					musicaDAO.cadastrarMusica(musica);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}*/
	
	// permite que o usuário selecione algumas musicas para serem cadastradas
	/*public static void cadastrarMusicasPorSelecao() {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		chooser.setCurrentDirectory(new File("C:\\comp\\Minhas músicas\\Imburana - Músicas\\Coleção de Músicas"));
		
		int opcao = chooser.showOpenDialog(null);
		
		if (opcao == JFileChooser.APPROVE_OPTION) {
			File files[] = chooser.getSelectedFiles();
			// File files[] = file.listFiles();
			
			for (int i = 0; i < files.length; i++) {
			// for (int i = 0; i < 1; i++) {
				// System.out.println(files[i].getPath());
				
				Musica musica = new Musica();
				
				String nome = files[i].getName();
				
				// pegando a duração da música
				int duracao = Util.getMP3Duration(files[i].getPath());
				
				// pegando o nome do arquivo
				String nomeArquivo = files[i].getName();
				
				// pegando o numero do dvd
				String numdvd = files[i].getParent();
				numdvd = numdvd.substring(numdvd.length() - 2);
				
				// pegando o numero da faixa
				String numfaixa = nome.substring(0, 3);
				
				// pegando o nome da música (pode ainda ter o nome do cantor nesse nome, por isso ele pode ter uma modificação depois
				nome = nome.substring(6, nome.length() - 4);
				
				int divisor = nome.lastIndexOf(" - ");
				String cantor = "";
				if (divisor != -1) {
					cantor = nome.substring(divisor + 3);
					cantor = cantor.trim().toUpperCase().replaceAll(" ", "");
					nome = nome.substring(0, divisor);
				}	
				
				nome = nome.trim();
				
				int numerodafaixa = Util.stringToInt(numfaixa);
				int numerododvd = Integer.parseInt(numdvd);
				
				System.out.println("");
				System.out.println("Nome do arquivo: " + nomeArquivo);
				System.out.println("Nome da música: " + nome);
				if (!cantor.equals("")) System.out.println("Cantor: " + cantor);
				if (duracao != 0) System.out.println("Duração: " + duracao / 60 + ":" + duracao % 60);
				System.out.println("Número do dvd: " + numerododvd);
				System.out.println("Número da faixa: " + numerodafaixa);
				
				// setando os valores no objeto musica que será cadastrado
				musica.setNomeArquivo(nomeArquivo);
				musica.setNome(nome);
				musica.setDuracao(duracao);
				musica.setNumeroDvd(numerododvd);
				musica.setNumeroFaixa(numerodafaixa);
				
				// tentando recuperar o cantor (pra o caso de já existir)
				if (!cantor.equals("")) {
					CantorDAO cantorDAO = new CantorDAOMySQL();
					try {
						List<Cantor> lista = cantorDAO.listarCantoresPorNomeSemEspacos(cantor);
						if (lista.size() == 0) {
							// o cantor ainda não foi cadastrado, cadastrar o cantor
							Cantor c = new Cantor();
							c.setNomeSemEspacos(cantor);
							cantorDAO.cadastrarCantor(c);
							lista.add(c);
						}
						musica.setCantores(lista);
					} catch (DataException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 					
				} else {
					musica.setCantores(null);
				}
				
				MusicaDAO musicaDAO = new MusicaDAOMySQL();
				try {
					musicaDAO.cadastrarMusica(musica);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}*/

	
	// Permite selecionar uma música para ser cadastrada
	/*public static void cadastrarMusica() {
		JFileChooser chooser = new JFileChooser();
		// chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser
				.setCurrentDirectory(new File(
						"C:\\comp\\Minhas músicas\\Imburana - Músicas\\Coleção de Músicas"));

		int opcao = chooser.showOpenDialog(null);

		if (opcao == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			// File files[] = file.listFiles();

			// for (int i = 0; i < files.length; i++) {
			// for (int i = 0; i < 1; i++) {
			// System.out.println(files[i].getPath());

			Musica musica = new Musica();

			String nome = file.getName();

			// pegando a duração da música
			int duracao = Util.getMP3Duration(file.getPath());

			// pegando o nome do arquivo
			String nomeArquivo = file.getName();

			// pegando o numero do dvd
			String numdvd = file.getParent();
			numdvd = numdvd.substring(numdvd.length() - 2);

			// pegando o numero da faixa
			String numfaixa = nome.substring(0, 3);

			// pegando o nome da música (pode ainda ter o nome do cantor nesse
			// nome, por isso ele pode ter uma modificação depois
			nome = nome.substring(6, nome.length() - 4);

			int divisor = nome.lastIndexOf(" - ");
			String cantor = "";
			if (divisor != -1) {
				cantor = nome.substring(divisor + 3);
				cantor = cantor.trim().toUpperCase().replaceAll(" ", "");
				nome = nome.substring(0, divisor);
			}

			nome = nome.trim();

			int numerodafaixa = Util.stringToInt(numfaixa);
			int numerododvd = Integer.parseInt(numdvd);

			System.out.println("");
			System.out.println("Nome do arquivo: " + nomeArquivo);
			System.out.println("Nome da música: " + nome);
			if (!cantor.equals(""))
				System.out.println("Cantor: " + cantor);
			if (duracao != 0)
				System.out.println("Duração: " + duracao / 60 + ":" + duracao
						% 60);
			System.out.println("Número do dvd: " + numerododvd);
			System.out.println("Número da faixa: " + numerodafaixa);

			// setando os valores no objeto musica que será cadastrado
			musica.setNomeArquivo(nomeArquivo);
			musica.setNome(nome);
			musica.setDuracao(duracao);
			musica.setNumeroDvd(numerododvd);
			musica.setNumeroFaixa(numerodafaixa);

			// tentando recuperar o cantor (pra o caso de já existir)
			if (!cantor.equals("")) {
				CantorDAO cantorDAO = new CantorDAOMySQL();
				try {
					List<Cantor> lista = cantorDAO
							.listarCantoresPorNomeSemEspacos(cantor);
					if (lista.size() == 0) {
						// o cantor ainda não foi cadastrado, cadastrar o cantor
						Cantor c = new Cantor();
						c.setNomeSemEspacos(cantor);
						cantorDAO.cadastrarCantor(c);
						lista.add(c);
					}
					musica.setCantores(lista);
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				musica.setCantores(null);
			}

			MusicaDAO musicaDAO = new MusicaDAOMySQL();
			try {
				musicaDAO.cadastrarMusica(musica);
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	
	// método feito para se usr somente por uma vez...
	// muda os nomes dos arquivos da coleção e coloca todos eles em uma só pasta
	/*private static void apagaLetrasIniciais() {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		List<Musica> musicas = null;
		
		Util.iniciarDiretorioBase();
				
		try {
			musicas = musicaDAO.listarMusicas();
			
			for (Musica m: musicas) {
				DecimalFormat df = new DecimalFormat("00");
				
				String nomeArquivoAntigo = Util.getDiretorioBase() + File.separator + Util.getTipoDeMidia() + 
					df.format(m.getNumeroDvd()) + File.separator + m.getNomeArquivo();
				
				String nomeArquivoNovo = Util.getDiretorioBase() + File.separator + m.getNome();
				if (m.getCantores() != null && m.getCantores().size() > 0) {
					nomeArquivoNovo += " - " + m.getCantores().get(0).getNomeSemEspacos();
				}
				nomeArquivoNovo += ".mp3";
				
				File antigo = new File(nomeArquivoAntigo);
				File novo = new File(nomeArquivoNovo);
				
				m.setNumeroDvd(0);
				m.setNumeroFaixa(0);
				m.setNomeArquivo(novo.getName());
				musicaDAO.alterarMusica(m);
				
				antigo.renameTo(novo);
			}
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	// testa se todos os nomes de arquivos contidos na coleção
	// correspondem a arquivos no disco
	/*private static void validaMusicas() {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		Util.iniciarDiretorioBase();
		boolean tudoOk = true;
		int contagem = 0;
		DecimalFormat df = new DecimalFormat("00");
		
		try {
			List<Musica> musicas = musicaDAO.listarMusicas();
			
			for (Musica m: musicas) {
				String nomeArquivo = Util.getDiretorioBase() + File.separator +
					Util.getTipoDeMidia() + df.format(m.getNumeroDvd()) + 
					File.separator + m.getNomeArquivo();
				
				File arquivo = new File(nomeArquivo);
				
				if (!arquivo.exists()) {
					System.out.println("O arquivo de nome \"" + nomeArquivo + "\" não existe" );
					tudoOk = false;
					contagem++;
				} else {
					// System.out.println("OK - " + m.getIdMusica());
				}
			}
			
			if (tudoOk) {
				System.out.println("Todas as músicas foram testadas com êxito!");
			} else {
				System.out.println("Número de músicas com o nome do arquivo errado: " + contagem);
			}
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*private static void criaColecao() {
		Util.iniciarDiretorioBase();
		DecimalFormat df = new DecimalFormat("00");
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		
		try {
			List<Musica> musicas = musicaDAO.listarMusicas();
			
			for (Musica m: musicas) {
				m.setNumeroDvd(Util.getProximoNumeroDVD());
				m.setNumeroFaixa(Util.getProximoNumeroDeMusica(m.getNumeroDvd()));
				
				String nomeArquivoOrigem = Util.getDiretorioBase() + File.separator + m.getNomeArquivo();
				String nomeArquivoDestino = Util.getDiretorioBase() + File.separator + 
					Util.getTipoDeMidia() + df.format(m.getNumeroDvd()) + File.separator;
				nomeArquivoDestino += Util.intToString(m.getNumeroFaixa()) + " - " + m.getNome();
				
				if (m.getCantores() != null && m.getCantores().size() > 0) {
					nomeArquivoDestino += " - " + m.getCantores().get(0).getNome();
				}
				
				nomeArquivoDestino += ".mp3";
				
				File origem = new File(nomeArquivoOrigem);
				File destino = new File(nomeArquivoDestino);
				
				System.out.println("movendo o arquivo para seu lugar...");
				origem.renameTo(destino);
				while (!destino.exists()) {
					try {
						System.out.println("Esperando a finalização da operação com o arquivo...");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				System.out.println("mudando o nome do arquivo para \"" + nomeArquivoDestino);
				m.setNomeArquivo(nomeArquivoDestino);
				
				System.out.println("alterando a música no banco de dados");
				musicaDAO.alterarMusica(m);					
			}
		} catch (DataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DiretorioBaseInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*private static void mudaNomesDosArquivosErrados() {
		MusicaDAO musicaDAO = new MusicaDAOMySQL();
		
		try {
			List<Musica> musicas = musicaDAO.listarMusicasEmOrdemAlfabetica();
			
			for (Musica m: musicas) {
				File arquivo = new File(m.getNomeArquivo());
				
				m.setNomeArquivo(arquivo.getName());
				
				musicaDAO.alterarMusica(m);
			}
		} catch (DataException e){
			e.printStackTrace();
		}
	}*/
	
	public static void main(String[] args) {
		// validaMusicas();
		// criaColecao();
		// mudaNomesDosArquivosErrados();
	}
	
	
}
