package classesbasicas;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Musica implements Serializable {
	private int idMusica; // chave primária da tabela no banco de dados
	private Tipo tipo;
	private Qualidade qualidade;
	private String nome;
	private String letra;
	private int duracao; // duração da música em segundos
	private String observacao;
	private String nomeArquivo;
	private String diretorio;
	private String chaveUnica; // chave unica, deve ser gerada antes do primeiro salvamento no banco de dados
							   // utilizada para efeitos de sincronização do BD entre diferentes pessoas utilizando o sistema 
	private int ano = -1;
	private String nomeArquivoCapa;
	private BufferedImage capa;
	
	private List<Cantor> cantores;
	private List<Assunto> assuntos;
	
	private Date created = null;
	private Date modified = null;
	
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
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
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
	public String getDiretorio() {
		return diretorio;
	}
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
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
			observacao + nomeArquivo + diretorio;
		
		this.chaveUnica = util.Util.gerarChaveUnica(s);
		System.out.println(this.chaveUnica);
		return this.chaveUnica;
	}
	public Object clone() {
		Musica inst = new Musica();
		inst.idMusica = this.idMusica;
		inst.tipo = this.tipo == null ? null : (Tipo) this.tipo.clone();
		inst.qualidade = this.qualidade == null
			? null
			: (Qualidade) this.qualidade.clone();
		inst.nome = this.nome == null ? null : new String(this.nome);
		inst.letra = this.letra == null ? null : new String(this.letra);
		inst.duracao = this.duracao;
		inst.observacao = this.observacao == null ? null : new String(
			this.observacao);
		inst.nomeArquivo = this.nomeArquivo == null ? null : new String(
			this.nomeArquivo);
		inst.diretorio = this.diretorio == null ? null : new String(
			this.diretorio);
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
			&& (this.nomeArquivo == null
				? castedObj.nomeArquivo == null
				: this.nomeArquivo.equals(castedObj.nomeArquivo))
			&& (this.diretorio == null
				? castedObj.diretorio == null
				: this.diretorio.equals(castedObj.diretorio))
			&& this.ano == castedObj.ano
			&& (this.cantores == null
				? castedObj.cantores == null
				: this.cantores.equals(castedObj.cantores)) && (this.assuntos == null
			? castedObj.assuntos == null
			: this.assuntos.equals(castedObj.assuntos))
			&& this.chaveUnica == null ? castedObj.chaveUnica == null : this.chaveUnica.equals(castedObj.chaveUnica));
	}
	/**
	 * Override hashCode.
	 *
	 * @return the Objects hashcode.
	 */
	public int hashCode() {
		int hashCode = 1;
		hashCode = 31 * hashCode + idMusica;
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
		hashCode = 31
			* hashCode
			+ (nomeArquivo == null ? 0 : nomeArquivo.hashCode());
		hashCode = 31
			* hashCode
			+ (diretorio == null ? 0 : diretorio.hashCode());
		hashCode = 31 * hashCode + (cantores == null ? 0 : cantores.hashCode());
		hashCode = 31 * hashCode + (assuntos == null ? 0 : assuntos.hashCode());
		return hashCode;
	}
	
}
