package classesbasicas;

import java.io.Serializable;
import java.util.Date;

public class Cantor implements Serializable {
	private int idCantor; // chave primária da tabela no banco de dados
	private String chaveUnica = null;
	private String nomeSemEspacos;
	private String nome;
	
	public int getIdCantor() {
		return idCantor;
	}
	
	public void setIdCantor(int idCantor) {
		this.idCantor = idCantor;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNomeSemEspacos() {
		return nomeSemEspacos;
	}
	
	public void setNomeSemEspacos(String nomeSemEspacos) {
		this.nomeSemEspacos = nomeSemEspacos;
	}

	public Object clone() {
		Cantor inst = new Cantor();
		inst.idCantor = this.idCantor;
		inst.nomeSemEspacos = this.nomeSemEspacos == null ? null : new String(this.nomeSemEspacos);
		inst.chaveUnica = this.chaveUnica;
		inst.nome = this.nome == null ? null : new String(this.nome);
		return inst;
	}

	public String getChaveUnica() {
		if (chaveUnica == null) {
			Date date = new Date();		
			String s = "" + date.toString() + idCantor + nomeSemEspacos + nome;
			this.chaveUnica = util.Util.gerarChaveUnica(s);
			System.out.println(this.chaveUnica);
		}
		
		return this.chaveUnica;
	}

	public void setChaveUnica(String chaveUnica) {
		this.chaveUnica = chaveUnica;
	}

	/**
	 * Returns <code>true</code> if this <code>Cantor</code> is the same as the o argument.
	 *
	 * @return <code>true</code> if this <code>Cantor</code> is the same as the o argument.
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (o.getClass() != getClass()) {
			return false;
		}
		Cantor castedObj = (Cantor) o;
		return ((this.idCantor == castedObj.idCantor)
			&& (this.nomeSemEspacos == null	? castedObj.nomeSemEspacos == null : this.nomeSemEspacos.equals(castedObj.nomeSemEspacos)) 
			&& (this.nome == null ? castedObj.nome == null : this.nome.equals(castedObj.nome))
			&& (this.chaveUnica == null ? castedObj.chaveUnica == null : this.chaveUnica.equals(castedObj.chaveUnica))
		);
	}	
}
