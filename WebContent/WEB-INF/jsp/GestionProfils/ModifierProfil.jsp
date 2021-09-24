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
<form action="<%=request.getContextPath()%>/modifieProfil" method="post">
<h2 class="titrepProfil">Mon profil</h2>



	<div class="containerFormulaire">
		<div class="container1">
			<div>
				<label>Pseudo :</label> 
				<input type="text" name="pseudo" value="${utilisateur.pseudo}" >
			</div>
			<div>
				<label>Nom :</label> 
				<input type="text" name="nom" value="${utilisateur.nom}" >
			</div>
			<div>
				<label>Prénom :</label> 
				<input type="text" name="prenom" value="${utilisateur.prenom}" >
			</div>
			<div>
				<label>Email :</label> 
				<input type="text" name="email" value="${utilisateur.email}" >
			</div>
			<div>
				<label>Téléphone :</label> 
				<input type="text" name="telephone" value="${utilisateur.telephone}" >
			</div>
			<div>
				<label>Code postal :</label> 
				<input type="text" name="codepostal" value="${utilisateur.codePostal}" >
			</div>
			<div>
				<label>Ville :</label> 
				<input type="text" name="ville" value="${utilisateur.ville}" >
			</div>
			<div>
				<label>Mot de passe :</label> 
				<input type="text" name="motdepasse" value="${utilisateur.motDePasse}" >
			</div>
			<div>
				<label>Confirmer mot de passe :</label> 
				<input type="text" name="confirmmotdepasse" value="" >
			</div>
		</div>
		<div>
		<input type="submit" value="Envoyer" >
		</div>
	</form>
</div>

<a href="<%=request.getContextPath()%>/Connection"><input type="button" Value="Déconnexion"></a>



</body>
</html>