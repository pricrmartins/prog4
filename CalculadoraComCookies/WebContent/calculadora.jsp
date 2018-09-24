<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculadora com Histórico</title>
</head>
<body>
<% ArrayList<String> historico = (ArrayList<String>) request.getAttribute("historico"); %>
<textarea rows="<%=historico.size()%>" cols="40">
<%for (String s:historico){
	out.print(s);
	out.println();
}
%></textarea>
<form action="Calculadora" method="post">
	<fieldset>
		<legend>Calculadora</legend>
		<input type="text" name="display" value="<%=request.getAttribute("resultado") %>" id="display">
		<br>
		<input type="button" value="  7 " onclick="updateDisplay(7)">
		<input type="button" value=" 8 " onclick="updateDisplay(8)">
		<input type="button" value=" 9  " onclick="updateDisplay(9)">
		<input type="submit" name="acao" value="=">
		<br>
		<input type="button" value="  4 " onclick="updateDisplay(4)">
		<input type="button" value=" 5 " onclick="updateDisplay(5)">
		<input type="button" value=" 6  " onclick="updateDisplay(6)">
		<input type="button" value=" + " onclick="updateDisplay('+')">
		<br>
		<input type="button" value="  3 " onclick="updateDisplay(3)">
		<input type="button" value=" 2 " onclick="updateDisplay(2)">
		<input type="button" value=" 1  " onclick="updateDisplay(1)">
		<input type="button" value=" - " onclick="updateDisplay('-')">
		<br>
		<input type="submit" value="CE"  onclick="zerar()">
		<input type="button" value=" 0 " onclick="updateDisplay(0)">
		<input type="button" value=" % " onclick="updateDisplay('/')">
		<input type="button" value=" x " onclick="updateDisplay('*')">
	</fieldset>
</form>
</body>
<script >
	function updateDisplay(e) {
		display = document.getElementById("display");
		display.value = display.value + e	
	}
	
	function zerar() {
		display = document.getElementById("display");
		display.value = "CE"
	}
	
</script>
</html>