package classesbasicas;

import java.util.Date;
import java.util.List;

public class Colecao {
	int idColecao;
	String nome;
	String descricao;
	private Date created = null;
	private Date modified = null;
	
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
	

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	
}
