<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="jstl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>inclusão de contato</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/agenda/incluir" method="post">
		<p>nome:</p>
		<input type="text" name="nome" >
		<p>idade:</p>
		<input type="number" name="idade">
		<p>tell:</p>
		<input type="text" name="tell" >
		<br>
		<button type="submit">Salvar</button> 
	</form>
</body>
</html>