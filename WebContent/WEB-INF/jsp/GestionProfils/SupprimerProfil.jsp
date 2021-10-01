<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style><%@include file="/Style.css"%></style>
<title>Supprimer Profil</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/HeadersFooters/Entete.jsp" %>

<form action="SupprimerProfil" method="post">
	<h2>Supprimer le profil</h2>
	<div class="container1">
	
	<p>Etes vous sûr de vouloir supprimer votre profil ?</p>
	
	<a href="<%=request.getContextPath()%>/ServletListeEncheres"><input class="containerBouton" type="button" name="annuler" Value="Annuler"></a>
	
	<input class="containerBouton" type="submit" Value="Supprimer">
		</div>
</form>

</body>
</html>