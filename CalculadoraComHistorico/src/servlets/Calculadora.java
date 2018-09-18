package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		String conta = request.getParameter("display");
		try {
			conta = engine.eval(conta) + " ";
		} catch (Exception e) {
			response.sendRedirect("http://www.google.com.br");
		}
		String historico = getDaCookieHistorico(request);
		String listaConta = getDaCookieLista(request);

		historico = getDaCookieHistorico(request);
		if(historico != null) {
			historico = historico + "\n" + request.getParameter("display")+"="+ conta;
	        response.addCookie(new Cookie("historico", historico));
		}else {
			historico = request.getParameter("display") + "=" + conta+ "\n";
			 response.addCookie(new Cookie("historico", historico));
		}
		
		if(listaConta != null) {
			listaConta += 1;
	        response.addCookie(new Cookie("listaConta", listaConta));
		}else {
			listaConta += 1;
	        response.addCookie(new Cookie("listaConta", listaConta));
		}
		
        request.setAttribute("resultado", conta);
        
		
		/*
		HttpSession session = request.getSession();
		String historico = "";
		ArrayList<String> lista = null;
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
		
		
		*/
		request.getRequestDispatcher("calculadora.jsp").forward(request, response);

	}

	private String getDaCookieLista(HttpServletRequest request) {
        
        // Recupera cookie
		if(request.getCookies() != null) {
        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals("listaConta"))
                return cookie.getValue();
		}
        return null;
    }
	private String getDaCookieHistorico(HttpServletRequest request) {
        
        // Recupera cookie
		if(request.getCookies() != null) {
        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals("historico"))
                return cookie.getValue();
		}
        return null;
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
