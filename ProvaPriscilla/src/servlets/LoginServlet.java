package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gerenciadores.GerenciadorDeCookies;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet(urlPatterns = "loginServlet", initParams = { @WebInitParam(name = "user", value = "admin"),
		@WebInitParam(name = "senha", value = "admin123") })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* Cookies*/
		GerenciadorDeCookies gen = new GerenciadorDeCookies(request, response);
		ArrayList<String> cookies = gen.listarUsuario();
		request.setAttribute("usuarios", cookies);
		if (cookies.size() == 0) {
			ServletConfig config = getServletConfig();
			String user = config.getInitParameter("user");
			String senha = config.getInitParameter("senha");
			if (request.getParameter("user").equals(user)
					&& request.getParameter("senha").equals(senha)) {
				
				request.getRequestDispatcher("crud.jsp").forward(request, response);

			}
			
		}else{
			String login = request.getParameter("user");
			String senha = request.getParameter("senha");
			for (String string : cookies) {
				if(string.contains(login+".."+senha)) 
				{
					request.getRequestDispatcher("crud.jsp").forward(request, response);
				}
			}
		}
		response.getWriter().append("Por favor, entre com os usuários já criados!").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
