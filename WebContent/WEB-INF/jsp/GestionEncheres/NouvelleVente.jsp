<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle vente</title>
</head>
<body>
	<%@include file="/WEB-INF/jsp/HeadersFooters/EnteteEnchere.html" %>
	<h2>Nouvelle vente</h2>
	
	<div class="creationProfil">
	<form action="<%=request.getContextPath()%>/ServletMisEnVenteArticle" method="post">
	<h2 class="titrepProfil">Mon profil</h2>
		<div class="containerFormulaire">
			<div class="container1">
			<label>Article :</label> <input type="text" id="article" name="article">
			<label>Description :</label> <input type="text" id="description" name="description"  >
			<label>Catégorie :</label> <select name="categories" id="select-categorie">
										<option value="toutes">Toutes</option>
										<option value="1">Informatique</option>
										<option value="2">Ameublement</option>
										<option value="3">Vêtement</option>
										<option value="4">Sport/Loisirs</option>
										</select>
			<label>Photo de l'article </label> <input type="file" id="photoArticle" name="photoArticle">
			<label>Mise à prix :</label> <input type="number" id="mise" name="mise">
			<label>Debut de l'enchère :</label> <input type="date" id="debutEnchere" name="debutEnchere">
			<label>Fin de l'enchère :</label> <input type="date" id="finEnchere" name="finEnchere">
		<div class="retrait">
			<label>Rue :</label> <input type="text" id="rue" name="rue" value="${utilisateur.rue}">
			<label>Code postal :</label> <input type="text" id="codePostal" name="codePostal" value="${utilisateur.codePostal}">
			<label>Ville :</label> <input type="text" id="ville" name="ville" value="${utilisateur.ville}">
			
		</div>
		</div>
			<div class="containerBouton">
			<input type="submit" value="Enregistrer" >
			<input type="button" onclick="https://google.fr" value="Annuler">
			<input type="button" onclick="https://google.fr" value="Annuler la vente">
			</div>
		</div>
	</form>
</div>
	
	

</body>
</html>