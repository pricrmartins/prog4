package gerenciadores;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UsuarioBean;

public class GerenciadorDeSession implements Gerenciador{

	private HttpSession _session = null;
	private ArrayList<String> usuariosSession = null;
	
	@SuppressWarnings("unchecked")
	public GerenciadorDeSession(HttpSession session) {
		_session = session;
		usuariosSession = _session.getAttribute("usuariosSession") != null ? 
				(ArrayList<String>)_session.getAttribute("usuariosSession"):new ArrayList<String>();
	}
	@Override
	public void adicionarUsuario(UsuarioBean usuario) {
		usuariosSession = listarUsuario();
		usuariosSession.add(usuario.nome+".."+usuario.login+".."+usuario.senha);
		_session.setAttribute("usuariosSession", usuariosSession);
	}

	@Override
	public void removerUsuario(UsuarioBean usuario) {
		usuariosSession = listarUsuario();
		usuariosSession.remove(usuario.nome+".."+usuario.login+".."+usuario.senha);
		_session.setAttribute("usuariosSession", usuariosSession);
	}

	@Override
	public void atualizarUsuario(UsuarioBean usuario) {
		usuariosSession = atualizarSession(usuario.nome,usuario.login,usuario.senha,listarUsuario());
		_session.setAttribute("usuariosSession", usuariosSession);
	}

	@Override
	public void limpar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> listarUsuario() {
		// TODO Auto-generated method stub
		return _session.getAttribute("usuariosSession") != null ? 
				(ArrayList<String>)_session.getAttribute("usuariosSession"):new ArrayList<String>();
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
