<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle vente</title>
</head>
<body>
	<%@include file="/EnteteEnchere.html" %>
	<h2>Nouvelle vente</h2>
	
	<div class="creationProfil">
	<form action="<%=request.getContextPath()%>/ServletCreationProfil" method="post">
	<h2 class="titrepProfil">Mon profil</h2>
		<div class="containerFormulaire">
			<div class="container1">
			<label>Article :</label> <input type="text" id="article" name="article">
			<label>Description :</label> <input type="text" id="description" name="description"  >
			<label>Catégorie :</label> <select name="categories" id="select-categorie">
										<option value="toutes">Toutes</option>
										<option value="informatique">Informatique</option>
										<option value="ameublement">Ameublement</option>
										<option value="vetement">Vêtement</option>
										<option value="sportLoisir">Sport/Loisirs</option>
										</select>
			<label>Photo de l'article </label> <input type="file" id="photoArticle" name="photoArticle">
			<label>Mise à prix :</label> <input type="number" id="mise" name="mise">
			<label>Debut de l'enchère :</label> <input type="date" id="debutEnchere" name="debutEnchere">
			<label>Fin de l'enchère :</label> <input type="date" id="finEnchere" name="finEnchere">
		<div class="retrait">
			<label>Rue :</label> <input type="text" id="rue" name="rue">
			<label>Code postal :</label> <input type="text" id="codePostal" name="codePostal">
			<label>Ville :</label> <input type="text" id="ville" name="ville">
			
		</div>
		</div>
			<div class="containerBouton">
			<input type="submit" value="Enregistrer">
			<input type="button" onclick="https://google.fr" value="Annuler">
			<input type="button" onclick="https://google.fr" value="Annuler la vente">
			</div>
		</div>
	</form>
</div>
	
	

</body>
</html>