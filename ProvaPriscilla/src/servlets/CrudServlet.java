package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UsuarioBean;
import gerenciadores.Gerenciador;
import gerenciadores.GerenciadorDeCookies;
import gerenciadores.GerenciadorDeSession;

/**
 * Servlet implementation class crudServlet
 */
@WebServlet("/crudServlet")
public class CrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrudServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		GerenciadorDeSession genSession = new GerenciadorDeSession(request.getSession());
		
		GerenciadorDeCookies gen = new GerenciadorDeCookies(request, response);
		int qtdadeLinhas = genSession.listarUsuario() != null? genSession.listarUsuario().size()+1 : 6;
		ArrayList<String> usuarios = gen.listarUsuario();
		switch (request.getParameter("operacao")) {
		case "cadastroUsuario":
			genSession.adicionarUsuario(new UsuarioBean(request.getParameter("nome"),request.getParameter("user"),request.getParameter("senha")));
			gen.adicionarUsuario(new UsuarioBean(request.getParameter("nome"), request.getParameter("user"), request.getParameter("senha")));
			break;
		case "remocaoUsuario":
			genSession.removerUsuario(new UsuarioBean(request.getParameter("nome"), request.getParameter("user"), request.getParameter("senha")));
			gen.removerUsuario(new UsuarioBean(request.getParameter("nome"),request.getParameter("user"), request.getParameter("senha")));
			break;
		case "atualizarUsuario":
			genSession.atualizarUsuario(new UsuarioBean(request.getParameter("nome"), request.getParameter("user"), request.getParameter("senha")));
			gen.atualizarUsuario(new UsuarioBean(request.getParameter("nome"),request.getParameter("user"), request.getParameter("senha")));
			break;
		default:
			break;
		}
		request.setAttribute("usuarios", usuarios);
		request.setAttribute("linhas",qtdadeLinhas);
		request.getRequestDispatcher("crud.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private ArrayList<String> atualizarSession(String nome, String login, String senha, ArrayList<String> usuarios) {

		for (String string : usuarios) {
			if(string.contains(nome) || string.contains(login)) {
				usuarios.remove(string);
				usuarios.add(nome+".."+login+".."+senha);
				break;
			}
		}
		return usuarios;
	}
}
