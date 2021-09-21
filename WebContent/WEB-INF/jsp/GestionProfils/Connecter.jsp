<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connecter</title>
</head>
<body>
<h1>Connecter</h1>

<% out.println("Bienvenue " + request.getParameter("pseudo")); %>

	<a href="<%=request.getContextPath()%>/Connection"><input type="button" Value="Déconnexion"></a>

</body>
</html>