package classesbasicas;

import java.util.List;

public class Colecao {
	int idColecao;
	String nome;
	String descricao;
	
	List<Musica> musicas = null;

	public int getIdColecao() {
		return idColecao;
	}

	public void setIdColecao(int idColecao) {
		this.idColecao = idColecao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
	
	
}
