package bd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class LeDumpDeSaida extends Thread {
	InputStream entrada = null;
	OutputStream saida = null;
	
	public LeDumpDeSaida(InputStream entrada, OutputStream saida) {
		this.entrada = entrada;
		this.saida = saida;
	}
	
	public void run() {
		try {
			PrintWriter pw = null;
			
			if (saida != null) pw = new PrintWriter(saida);
			else System.out.println("arquivo de saída nulo");
			
			InputStreamReader isr = new InputStreamReader(entrada);
			BufferedReader br = new BufferedReader(isr);
			
			String linha = null;
			
			while ((linha = br.readLine()) != null) {
				if (pw != null) {
					pw.println(linha);
					pw.flush();
				}				
			}
			
			if (pw != null) pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
