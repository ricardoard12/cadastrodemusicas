package classesbasicas;

import java.io.Serializable;

public class Qualidade implements Serializable {
	private int idQualidade; // chave primária no banco de dados
	private String qualidade;
	
	public int getIdQualidade() {
		return idQualidade;
	}
	
	public void setIdQualidade(int idQualidade) {
		this.idQualidade = idQualidade;
	}
	
	public String getQualidade() {
		return qualidade;
	}
	
	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}

	public Object clone() {
		Qualidade inst = new Qualidade();
		inst.idQualidade = this.idQualidade;
		inst.qualidade = this.qualidade == null ? null : new String(
			this.qualidade);
		return inst;
	}

	/**
	 * Returns <code>true</code> if this <code>Qualidade</code> is the same as the o argument.
	 *
	 * @return <code>true</code> if this <code>Qualidade</code> is the same as the o argument.
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
		Qualidade castedObj = (Qualidade) o;
		return ((this.idQualidade == castedObj.idQualidade) && (this.qualidade == null
			? castedObj.qualidade == null
			: this.qualidade.equals(castedObj.qualidade)));
	}	
	
}
