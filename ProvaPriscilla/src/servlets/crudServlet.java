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
import gerenciadores.GerenciadorDeCookies;

/**
 * Servlet implementation class crudServlet
 */
@WebServlet("/crudServlet")
public class crudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public crudServlet() {
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
		
		GerenciadorDeCookies gen = new GerenciadorDeCookies(request, response);
		HttpSession session = request.getSession();
		ArrayList<String> usuariosSession = new ArrayList<String>();
		if (session.getAttribute("usuariosSession") != null) {
			usuariosSession = (ArrayList<String>)session.getAttribute("usuariosSession");
		}
		
		
		ArrayList<String> usuarios = gen.listarCookies();
		switch (request.getParameter("operacao")) {
		case "cadastroUsuario":
			usuariosSession.add(request.getParameter("nome")+".."+request.getParameter("user")+".."+request.getParameter("senha"));
			gen.adicionarUsuario(new UsuarioBean(request.getParameter("nome"), request.getParameter("user"), request.getParameter("senha")));
			break;
		case "remocaoUsuario":
			usuariosSession.remove(request.getParameter("nome")+".."+request.getParameter("user")+".."+request.getParameter("senha"));
			gen.removerUsuario(new UsuarioBean(request.getParameter("nome"),request.getParameter("user"), request.getParameter("senha")));
			break;
		case "atualizarUsuario":
			usuariosSession = atualizarSession(request.getParameter("nome"),request.getParameter("user"),request.getParameter("senha"),usuariosSession);
			gen.atualizarUsuario(new UsuarioBean(request.getParameter("nome"),request.getParameter("user"), request.getParameter("senha")));
			break;
		default:
			break;
		}
		request.setAttribute("usuarios", usuarios);
		session.setAttribute("usuariosSession", usuariosSession);
		session.setAttribute("linhas",usuariosSession.size()+1);
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
