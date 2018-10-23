<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form get="post" action="crudServlet">
Session
<textarea rows="<%=request.getAttribute("usuarios")%>" cols="40">
<%=session.getAttribute("usuariosSession") %></textarea>
<br>
<br>
Cookies
<% ArrayList<String> historico = (ArrayList<String>) request.getAttribute("usuarios"); %>
<textarea rows="<%=historico.size()%>" cols="40">
<%for (String s:historico){
	out.print(s);
	out.println();
}
%></textarea>
<br>
<br>
Digite o nome login e senha do usuario e selecione as opções à baixo
<br>
Nome <input type="text" name="nome" required>
Login <input type="text" name="user" required>
Senha <input type="password" name="senha" required>
<br>
<input type="radio" name="operacao" value="cadastroUsuario"> Cadastrar Usuario
<br>
<br>
<input type="radio" name="operacao" value="remocaoUsuario"> Remoção Usuario
<br>
<br>
<input type="radio" name="operacao" value="atualizarUsuario"> Atualizar Usuario (Só é possível atualizar a senha)
<br>
<input type="submit" name="enviar">
<a href="http://localhost:8080/ProvaPriscilla/index.jsp">Voltar</a>
</form>
</body>
</html>