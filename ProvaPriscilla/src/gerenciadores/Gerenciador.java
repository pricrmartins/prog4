package gerenciadores;

import java.util.ArrayList;

import beans.UsuarioBean;

public interface Gerenciador {
	
	public void adicionarUsuario(UsuarioBean usuario);
	
	public void removerUsuario(UsuarioBean usuario);
	
	public void atualizarUsuario(UsuarioBean usuario);
	
	public void limpar();
	
	public ArrayList<String> listarUsuario();
}
