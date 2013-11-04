package classesbasicas;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.Util;

public class Musica implements Serializable {
	private int idMusica; // chave primária da tabela no banco de dados
	private int idArquivoMusica;
	private Tipo tipo;
	private Qualidade qualidade;
	private String nome;
	private String letra;
	private int duracao; // duração da música em segundos
	private String observacao;
	private String chaveUnica; // chave unica, deve ser gerada antes do primeiro salvamento no banco de dados
							   // utilizada para efeitos de sincronização do BD entre diferentes pessoas utilizando o sistema 
	private int ano = -1;
	private String nomeArquivoCapa;
	private BufferedImage capa = null;
	
	private List<Cantor> cantores;
	private List<Assunto> assuntos;
	
	private Date created = null;
	private Date modified = null;
	
	private int tipoArquivo;
	
	private static final long serialVersionUID = -6046323901198312198L;;
	
	public String getDescricaoCompleta() {
		String descricao = "Dados da Música\n";
		descricao += "idMusica: " + idMusica + "\n";
		descricao += "idArquivoMusica: " + idArquivoMusica + "\n";
		descricao += "Nome: " + nome + "\n";
		descricao += "Cantor: " + (cantores != null && cantores.size() > 0 ? cantores.get(0).getNome() : "") + "\n";
		descricao += "Tipo do Arquivo: " + Constantes.TIPO_ARQUIVO_NOMES_TIPOS[tipoArquivo] + "\n";
		descricao += "Duração: " + Util.formataDuracao(duracao) + "\n";
		descricao += "Ritmo: " + (tipo != null ? tipo.getTipo() : "") + "\n";
		String assuntosString = "";
		if (assuntos != null && assuntos.size() > 0) {
			for (int j = 0; j < assuntos.size(); j++) {
				assuntosString += assuntos.get(j).getAssunto();
				if (j < assuntos.size() - 1) assuntosString += ", ";
			}
		}
		descricao += "Assuntos: " + assuntosString + "\n";
		descricao += "Qualidade: " + (qualidade != null ? qualidade.getQualidade() : "") + "\n";
		descricao += "Ano: " + (ano > 0 ? ano : "") + "\n";
		descricao += "Chave Única: " + chaveUnica + "\n";
		descricao += "Nome Arquivo Capa: " + nomeArquivoCapa + "\n";
		descricao += "Created: " + created.toString() + "\n";
		descricao += "Modified: " + modified.toString() + "\n";
		descricao += "Observação: " + observacao + "\n";
		descricao += "Letra: " + letra;

		return descricao;
	}
	
	public String getDescricaoCompletaSemDiferencas(Musica m) {
		String descricao = "";
		descricao += (idMusica == m.getIdMusica()) ? "" : "idMusica: " + idMusica + "\n";
		descricao += (idArquivoMusica == m.getIdArquivoMusica()) ? "" : "idArquivoMusica: " + idArquivoMusica + "\n";
		descricao += (nome.equals(m.getNome())) ? "" : "Nome: " + nome + "\n";
		
		String temp1 = (cantores != null && cantores.size() > 0 ? cantores.get(0).getNome() : "");
		String temp2 = (m.getCantores() != null && m.getCantores().size() > 0 ? m.getCantores().get(0).getNome() : "");
		descricao += (isStringEqual(temp1, temp2)) ? "" : "Cantor: " + temp1 + "\n";
		descricao += (tipoArquivo == m.getTipoArquivo()) ? "" : "Tipo do Arquivo: " + Constantes.TIPO_ARQUIVO_NOMES_TIPOS[tipoArquivo] + "\n";
		descricao += (duracao == m.getDuracao()) ? "" : "Duração: " + Util.formataDuracao(duracao) + "\n";
		descricao += (tipo == m.getTipo() || (tipo != null && m.getTipo() != null && isStringEqual(tipo.getTipo(), m.getTipo().getTipo()))) ? "" : "Ritmo: " + (tipo != null ? tipo.getTipo() : "") + "\n";
		String assuntosString = "";
		String assuntosString2 = "";
		if (assuntos != null && assuntos.size() > 0) {
			for (int j = 0; j < assuntos.size(); j++) {
				assuntosString += assuntos.get(j).getAssunto();
				if (j < assuntos.size() - 1) assuntosString += ", ";
			}
		}
		if (m.getAssuntos() != null && m.getAssuntos().size() > 0) {
			for (int j = 0; j < m.getAssuntos().size(); j++) {
				assuntosString2 += m.getAssuntos().get(j).getAssunto();
				if (j < m.getAssuntos().size() - 1) assuntosString2 += ", ";
			}
		}
		descricao += (isStringEqual(assuntosString, assuntosString2)) ? "" : "Assuntos: " + assuntosString + "\n";
		
		if (qualidade == null && m.getQualidade() != null || qualidade != null && m.getQualidade() == null) {
			descricao += "Qualidade: " + (qualidade != null ? qualidade.getQualidade() : "") + "\n";
		} else if (qualidade != null) {
			descricao += (isStringEqual(qualidade.getQualidade(), m.getQualidade().getQualidade())) ? "" : "Qualidade: " + (qualidade != null ? qualidade.getQualidade() : "") + "\n";
		}
		
		descricao += (ano == m.getAno()) ? "" : "Ano: " + (ano > 0 ? ano : "") + "\n";
		descricao += (isStringEqual(chaveUnica, m.getChaveUnica())) ? "" : "Chave Única: " + chaveUnica + "\n";
		descricao += (isStringEqual(nomeArquivoCapa, m.getNomeArquivoCapa())) ? "" : "Nome Arquivo Capa: " + nomeArquivoCapa + "\n";
		descricao += (isStringEqual(created.toString(), m.getCreated().toString())) ? "" : "Created: " + created.toString() + "\n";
		descricao += (isStringEqual(modified.toString(), m.getModified().toString())) ? "" : "Modified: " + modified.toString() + "\n";
		descricao += (isStringEqual(observacao, m.getObservacao())) ? "" : "Observação: " + observacao + "\n";
		descricao += (isStringEqual(letra, m.getLetra())) ? "" : "Letra: " + letra;

		if (!descricao.trim().equals(""))
		{
			descricao = "Dados da Música\n" + descricao;	
		} else {
			descricao = "";
		}
		
		return descricao;
	}
	
	private boolean isStringEqual(String temp1, String temp2)
	{
		if (temp1 == null && temp2 == null) {
			return true;
		} else if (temp1 == null) {
			return false;
		} else {
			return temp1.equals(temp2);
		}
	}
	
	public List<Assunto> getAssuntos() {
		return assuntos;
	}
	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}
	public List<Cantor> getCantores() {
		return cantores;
	}
	public void setCantores(List<Cantor> cantores) {
		this.cantores = cantores;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public int getIdMusica() {
		return idMusica;
	}
	public void setIdArquivoMusica(int idArquivoMusica) {
		this.idArquivoMusica = idArquivoMusica;
	}
	public int getIdArquivoMusica() {
		return idArquivoMusica;
	}
	public void setIdMusica(int idMusica) {
		this.idMusica = idMusica;
	}
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Qualidade getQualidade() {
		return qualidade;
	}
	public void setQualidade(Qualidade qualidade) {
		this.qualidade = qualidade;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public String getChaveUnica() {
		return chaveUnica;
	}
	public void setChaveUnica(String chaveUnica) {
		this.chaveUnica = chaveUnica;
	}

	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public BufferedImage getCapa() {
		return capa;
	}
	public void setCapa(BufferedImage capa) {
		this.capa = capa;
	}
	
	public String getNomeArquivoCapa() {
		return nomeArquivoCapa;
	}
	
	public void setNomeArquivoCapa(String nomeArquivoCapa) {
		this.nomeArquivoCapa = nomeArquivoCapa;
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
	
	public String gerarChaveUnica() {
		Date date = new Date();
		
		String s = "" + date.toString() + idMusica + (tipo != null ? tipo.getTipo() : "") +
			(qualidade != null ? qualidade.getQualidade() : "") + nome + letra + duracao +
			observacao + idArquivoMusica;
		
		this.chaveUnica = util.Util.gerarChaveUnica(s);
		System.out.println(this.chaveUnica);
		return this.chaveUnica;
	}
	public Object clone() {
		Musica inst = new Musica();
		inst.idMusica = this.idMusica;
		inst.idArquivoMusica = this.idArquivoMusica;
		inst.tipo = this.tipo == null ? null : (Tipo) this.tipo.clone();
		inst.qualidade = this.qualidade == null
			? null
			: (Qualidade) this.qualidade.clone();
		inst.nome = this.nome == null ? null : new String(this.nome);
		inst.letra = this.letra == null ? null : new String(this.letra);
		inst.duracao = this.duracao;
		inst.observacao = this.observacao == null ? null : new String(
			this.observacao);
		inst.cantores = null;
		if (this.cantores != null) {
			inst.cantores = new ArrayList<Cantor>();
			for (Cantor c: this.cantores) {
				inst.cantores.add((Cantor) c.clone());
			}
		}
		inst.ano = this.ano;
		inst.nomeArquivoCapa = this.nomeArquivoCapa;
		inst.assuntos = null;
		if (this.assuntos != null) {
			inst.assuntos = new ArrayList<Assunto>();
			for (Assunto a: this.assuntos) {
				inst.assuntos.add((Assunto) a.clone());
			}
		}	
		inst.chaveUnica = this.chaveUnica;
		inst.tipoArquivo = this.tipoArquivo;
		return inst;
	}
	/**
	 * Returns <code>true</code> if this <code>Musica</code> is the same as the o argument.
	 *
	 * @return <code>true</code> if this <code>Musica</code> is the same as the o argument.
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
		Musica castedObj = (Musica) o;
		return ((this.idMusica == castedObj.idMusica)
			&& (this.idArquivoMusica == castedObj.idArquivoMusica)
			&& (this.tipo == null ? castedObj.tipo == null : this.tipo
				.equals(castedObj.tipo))
			&& (this.qualidade == null
				? castedObj.qualidade == null
				: this.qualidade.equals(castedObj.qualidade))
			&& (this.nome == null ? castedObj.nome == null : this.nome
				.equals(castedObj.nome))
			&& (this.letra == null ? castedObj.letra == null : this.letra
				.equals(castedObj.letra))
			&& (this.duracao == castedObj.duracao)
			&& (this.observacao == null
				? castedObj.observacao == null
				: this.observacao.equals(castedObj.observacao))
			&& this.ano == castedObj.ano
			&& (this.cantores == null
				? castedObj.cantores == null
				: this.cantores.equals(castedObj.cantores)) && (this.assuntos == null
			? castedObj.assuntos == null
			: this.assuntos.equals(castedObj.assuntos))
			&& this.chaveUnica == null ? castedObj.chaveUnica == null : this.chaveUnica.equals(castedObj.chaveUnica)
			&& this.tipoArquivo == castedObj.tipoArquivo);
	}
	/**
	 * Override hashCode.
	 *
	 * @return the Objects hashcode.
	 */
	public int hashCode() {
		int hashCode = 1;
		hashCode = 31 * hashCode + idMusica;
		hashCode = 31 * hashCode + idArquivoMusica;
		hashCode = 31 * hashCode + (tipo == null ? 0 : tipo.hashCode());
		hashCode = 31
			* hashCode
			+ (qualidade == null ? 0 : qualidade.hashCode());
		hashCode = 31 * hashCode + (nome == null ? 0 : nome.hashCode());
		hashCode = 31 * hashCode + (letra == null ? 0 : letra.hashCode());
		hashCode = 31 * hashCode + duracao;
		hashCode = 31
			* hashCode
			+ (observacao == null ? 0 : observacao.hashCode());
		hashCode = 31 * hashCode + (cantores == null ? 0 : cantores.hashCode());
		hashCode = 31 * hashCode + (assuntos == null ? 0 : assuntos.hashCode());
		hashCode = 31 * hashCode + tipoArquivo;
		return hashCode;
	}

	public void setTipoArquivo(int tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	
	public int getTipoArquivo() {
		return tipoArquivo;
	}
	
}





