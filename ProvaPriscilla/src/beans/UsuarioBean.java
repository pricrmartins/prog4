package beans;

public class UsuarioBean {

	public String nome;
	public String login;
	public String senha;
	
	public UsuarioBean(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	public UsuarioBean(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

}
