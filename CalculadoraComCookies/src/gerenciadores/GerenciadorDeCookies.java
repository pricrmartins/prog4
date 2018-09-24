package gerenciadores;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GerenciadorDeCookies {

	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private Cookie[] listaCookies;

	public GerenciadorDeCookies(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.listaCookies = request.getCookies();
	}

	public void adicionarOperacao(String operacao) {
		Cookie cookie;
		if(this.listaCookies == null) 
			cookie = new Cookie("operacao0",
	                   operacao);
		else
			cookie = new Cookie("operacao"+this.listaCookies.length,
	                   operacao);
		this.response.addCookie(cookie);
	}

	public void limpar() {
		for (Cookie c : this.listaCookies) {
			c.setMaxAge(0);
			this.response.addCookie(c);
		}
	}

	public ArrayList<String> listarCookies() {
		ArrayList<String> lista = new ArrayList<String>();
		for (Cookie c : this.listaCookies) {
			if (!c.getName().equals("JSESSIONID")) {
				lista.add(c.getValue());
			}
		}
		return lista;
	}
}
