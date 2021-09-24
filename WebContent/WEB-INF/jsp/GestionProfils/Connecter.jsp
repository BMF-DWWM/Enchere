<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connecter</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/HeadersFooters/EnteteEnchere.html" %>
<h1>Connecter</h1>

<% out.println("Bienvenue " + session.getAttribute("pseudo")); %>

	<a href="<%=request.getContextPath()%>/modifieProfil"><input type="button" Value="Modifier Profil"></a>
	<a href="<%=request.getContextPath()%>/Connection"><input type="button" Value="Déconnexion"></a>
	<a href="<%=request.getContextPath()%>/ServletMisEnVenteArticle"><input type="button" Value="Faire une vente"></a>

</body>
</html>