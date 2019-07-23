<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alteração do contato ${contato.nome}</title>
</head>
<body>
	<jstl:if test="mensagemErro != NULL">
		<div class="alert alert-danger" role="alert">${mensagemErro}</div>
	</jstl:if>
	<form action="${pageContext.request.contextPath}/agenda/editar"
		method="post">
		<p>nome:</p>
		<input type="text" name="nome" value="${contato.nome }">
		<p>idade:</p>
		<input type="number" name="idade" value="${contato.idade }">
		<p>tell:</p>
		<input type="text" name="tell" value="${contato.tell }"> <br>
		<input type="hidden" name="id" value="${contato.id }">
		<button type="submit">Salvar</button>
	</form>
</body>
</html>