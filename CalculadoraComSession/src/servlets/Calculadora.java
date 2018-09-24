package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.jms.Session;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		String conta = request.getParameter("display");
		
		if (conta.equals("CE")) {
			HttpSession s =  request.getSession(true);
			s.invalidate();
			response.sendRedirect("http://localhost:8080/CalculadoraComSession");
		} else {
		try {
			conta = engine.eval(conta) + " ";
		} catch (Exception e) {
			response.sendRedirect("http://www.google.com.br");
		}
		HttpSession session = request.getSession();
		String historico = "";
		ArrayList<String> lista = null;
		System.out.println(request.getParameter("teste"));
		if (session.getAttribute("historico") != null) {
			lista = (ArrayList<String>) session.getAttribute("lista");
			lista.add(request.getParameter("display")+"="+ conta);
			historico = (String) session.getAttribute("historico");
			historico = historico + "\n" + request.getParameter("display")+"="+ conta;
		} else {
			lista = new ArrayList<String>();
			lista.add(request.getParameter("display") + "=" + conta);
			historico = request.getParameter("display") + "=" + conta;
		}
		session.setAttribute("linhas", String.valueOf(lista.size()*3));
		session.setAttribute("lista", lista);
		session.setAttribute("historico", historico);
		request.setAttribute("resultado", conta);
		request.getRequestDispatcher("calculadora.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
