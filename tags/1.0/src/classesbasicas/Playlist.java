package classesbasicas;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private int idPlaylist;
	private String nome;
	
	private List<Musica> itens = new ArrayList<Musica>();

	public int getIdPlaylist() {
		return idPlaylist;
	}

	public void setIdPlaylist(int idPlaylist) {
		this.idPlaylist = idPlaylist;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Musica> getItens() {
		return itens;
	}

	public void setItens(List<Musica> itens) {
		this.itens = itens;
		// salvar no banco de dados
	}
	
	
}
