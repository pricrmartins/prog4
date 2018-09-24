package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.media.sound.RealTimeSequencerProvider;

import gerenciadores.GerenciadorDeCookies;

/**
 * Servlet implementation class Calculadora
 */
@WebServlet("/Calculadora")
public class Calculadora extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Calculadora() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		String conta = request.getParameter("display");
		String resultado;
		GerenciadorDeCookies gen = new GerenciadorDeCookies(request, response);

		System.out.println(request.getParameter("acao"));

		if (conta.equals("CE")) {
			gen.limpar();
			response.sendRedirect("http://localhost:8080/CalculadoraComCookies");
		} else {
			try {
				resultado = engine.eval(conta) + " ";
				gen.adicionarOperacao(conta + "=" + resultado);
				ArrayList<String> historico = gen.listarCookies();
				historico.add(conta + "=" + resultado);
				request.setAttribute("historico", historico);
				request.setAttribute("resultado", resultado);
				request.getRequestDispatcher("calculadora.jsp").forward(request, response);
			} catch (Exception e) {
				response.sendRedirect("http://www.google.com.br");
			}
		}
		
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

}
