<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style><%@include file="/Style.css"%></style>
<title>Creation Profil</title>
</head>
<body>
		<%@include file="/EnteteEnchere.html" %>
	
<div class="creationProfil">
	<form action="<%=request.getContextPath()%>/ServletCreationProfil" method="post">
	<h2 class="titrepProfil">Mon profil</h2>
		<div class="containerFormulaire">
			<div class="container1">
			<label>Pseudo :</label> <input type="text" id="pseudo" name="pseudo">
			<label>Nom :</label> <input type="text" id="nom" name="nom">
			<label>Prénom :</label> <input type="text" id="prenom" name="prenom">
			<label>Email :</label> <input type="text" id="email" name="email">
			<label>Téléphone :</label> <input type="text" id="telephone" name="telephone">
			<label>Rue :</label> <input type="text" id="rue" name="rue">
			<label>Code postal :</label> <input type="text" id="codePostal" name="codePostal">
			<label>Ville :</label> <input type="text" id="ville" name="ville">
			<label>Mot de passe :</label> <input type="text" id="mdp" name="mdp">
			<label>Confirmation :</label> <input type="text" id="mdpConfirm" name="mdpConfirm">
			</div>
			<div class="containerBouton">
			<input type="submit" value="Créer">
			<input type="button" onclick="https://google.fr" value="Annuler">
			</div>
		</div>
	</form>
</div>
	
</body>
</html>