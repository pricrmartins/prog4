package gerenciadores;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UsuarioBean;

public class GerenciadorDeCookies implements Gerenciador{
	@SuppressWarnings("unused")
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private Cookie[] listaCookies;
	
	public GerenciadorDeCookies(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.listaCookies = request.getCookies();
	}
	public void adicionarUsuario(UsuarioBean usuario) {
		Cookie cookie;
		if(this.listaCookies == null) {
			cookie = new Cookie("login0",
					usuario.nome+".."+usuario.login+".."+usuario.senha);
			this.response.addCookie(cookie);
		}
		else {
			cookie = new Cookie("login"+this.listaCookies.length,
					usuario.nome+".."+usuario.login+".."+usuario.senha);
			this.response.addCookie(cookie);
		}
	}
	
	public void removerUsuario(UsuarioBean usuario){
		for (Cookie c : this.listaCookies) {
			if(c.getValue().equals(usuario.nome+".."+usuario.login+".."+usuario.senha)) 
			{
				c.setMaxAge(0);
				this.response.addCookie(c);
			}
		}
	}
	
	public void atualizarUsuario(UsuarioBean usuario){
		for (Cookie c : this.listaCookies) {
			if(c.getValue().contains(usuario.login+"..") || c.getValue().contains(usuario.nome+"..")) 
			{
				c.setValue(usuario.nome+".."+usuario.login+".."+usuario.senha);
				this.response.addCookie(c);
			}
		}
	}
	
	public void limpar() {
		for (Cookie c : this.listaCookies) {
			c.setMaxAge(0);
			this.response.addCookie(c);
		}
	}
	public ArrayList<String> listarUsuario() {
		ArrayList<String> lista = new ArrayList<String>();
		for (Cookie c : this.listaCookies) {
			if (!c.getName().equals("JSESSIONID")) {
				lista.add(c.getValue());
			}
		}
		return lista;
	}
}
