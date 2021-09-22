<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier le profil</title>
</head>
<body>
	<%@include file="/WEB-INF/jsp/HeadersFooters/EnteteEnchere.html" %>
	
	<div class="lectureProfil">
	<form action="<%=request.getContextPath()%>" method="get">
	<h2 class="titrepProfil">Mon profil</h2>
		<div class="containerFormulaire">
			<div class="container1">
			<input type="text" disabled="disabled" value="${utilisateur.pseudo}" >
			<input type="text" readonly="readonly" value="${utilisateur.pseudo}" >
			<label>Nom :</label> 
			<label>Prénom :</label>
			<label>Email :</label> 
			<label>Téléphone :</label> 
			<label>Rue :</label> 
			<label>Code postal :</label> 
			<label>Ville :</label> 
			<label>Mot de passe :</label> >
			<label>Confirmation :</label> 
			</div>
		</div>
	</form>
</div>

<a href="<%=request.getContextPath()%>/Connection"><input type="button" Value="Déconnexion"></a>



</body>
</html>