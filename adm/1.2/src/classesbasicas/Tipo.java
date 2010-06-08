package classesbasicas;

import java.io.Serializable;
import java.util.Date;

public class Tipo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9079019559572300528L;
	private int idTipo; // chave primária da tabela no banco de dados
	private String chaveUnica = null;
	private String tipo;
	private Date created = null;
	private Date modified = null;
	
	public int getIdTipo() {
		return idTipo;
	}
	
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	
	public String getDescricaoCompleta() {
		String descricao = "Dados do Ritmo\n";
		descricao += "idTipo: " + idTipo + "\n";
		descricao += "tipo: " + tipo + "\n";
		descricao += "Chave Única: " + chaveUnica + "\n";
		descricao += "Created: " + created.toString() + "\n";
		descricao += "Modified: " + modified.toString() + "\n";
				
		return descricao;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getChaveUnica() {
		if (chaveUnica == null) {
			Date date = new Date();		
			String s = "" + date.toString() + idTipo + tipo;
			this.chaveUnica = util.Util.gerarChaveUnica(s);
			System.out.println(this.chaveUnica);
		}
		
		return this.chaveUnica;
	}

	public void setChaveUnica(String chaveUnica) {
		this.chaveUnica = chaveUnica;
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

	public Object clone() {
		Tipo inst = new Tipo();
		inst.idTipo = this.idTipo;
		inst.chaveUnica = this.chaveUnica;
		inst.tipo = this.tipo == null ? null : new String(this.tipo);
		return inst;
	}

	/**
	 * Returns <code>true</code> if this <code>Tipo</code> is the same as the o argument.
	 *
	 * @return <code>true</code> if this <code>Tipo</code> is the same as the o argument.
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
		Tipo castedObj = (Tipo) o;
		return ((this.idTipo == castedObj.idTipo) 
				&& (this.tipo == null ? castedObj.tipo == null : this.tipo.equals(castedObj.tipo))
				&& this.chaveUnica == null ? castedObj.chaveUnica == null : this.chaveUnica.equals(castedObj.chaveUnica));
	}
	
}
