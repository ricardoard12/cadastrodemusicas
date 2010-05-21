package classesbasicas;

import java.io.Serializable;
import java.util.Date;

public class Assunto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4119343566531550514L;
	private int idAssunto; // chave primária da tabela no banco de dados
	private String chaveUnica = null;
	private String assunto;
	private Date created = null;
	private Date modified = null;
	
	private int tipoArquivo;

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public String getDescricaoCompleta() {
		String descricao = "Dados do Assunto\n";
		descricao += "idAssunto: " + idAssunto + "\n";
		descricao += "assunto: " + assunto + "\n";
		descricao += "Chave Única: " + chaveUnica + "\n";
		descricao += "Created: " + created.toString() + "\n";
		descricao += "Modified: " + modified.toString() + "\n";
				
		return descricao;
	}

	public int getIdAssunto() {
		return idAssunto;
	}

	public void setIdAssunto(int idAssunto) {
		this.idAssunto = idAssunto;
	}

	public Object clone() {
		Assunto inst = new Assunto();
		inst.idAssunto = this.idAssunto;
		inst.assunto = this.assunto == null ? null : new String(this.assunto);
		inst.chaveUnica = this.chaveUnica;
		return inst;
	}
	
	public String getChaveUnica() {
		if (chaveUnica == null) {
			Date date = new Date();		
			String s = "" + date.toString() + idAssunto + assunto;
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

	/**
	 * Returns <code>true</code> if this <code>Assunto</code> is the same as the o argument.
	 *
	 * @return <code>true</code> if this <code>Assunto</code> is the same as the o argument.
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
		Assunto castedObj = (Assunto) o;
		return ((this.idAssunto == castedObj.idAssunto) 
				&& (this.assunto == null ? castedObj.assunto == null : this.assunto.equals(castedObj.assunto))
				&& this.chaveUnica == null ? castedObj.chaveUnica == null : this.chaveUnica.equals(castedObj.chaveUnica));
	}

	public void setTipoArquivo(int tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	
	public int getTipoArquivo() {
		return tipoArquivo;
	}
}
