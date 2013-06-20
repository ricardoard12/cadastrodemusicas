package classesbasicas;

public class Usuario {

	private String nome;
	private String telefone;
	private String login;
	private String senha;
	private String tipo;
	
	public Usuario() { }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean equals(Object usuario) {
		boolean key = true;
		
		if (!(usuario instanceof Usuario)) {
			key = false;
		} else {
			Usuario u = (Usuario) usuario;
			
			if (!u.getNome().equals(this.nome)) {
				key = false;
			}
			
			if (!u.getTelefone().equals(this.telefone)) {
				key = false;
			}
			
			if (!u.getLogin().equals(this.login)) {
				key = false;
			}
			
			if (!u.getSenha().equals(this.senha)) {
				key = false;
			}
			
			if (!u.getTipo().equals(this.tipo)) {
				key = false;
			}		
		}		
		
		return key;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
