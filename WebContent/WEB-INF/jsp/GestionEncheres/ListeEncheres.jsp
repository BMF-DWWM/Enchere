<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style><%@include file="/Style.css"%></style>
<!-- <link href="/WEB-INF/Style.css" rel="stylesheet"> -->
<title>Liste des Enchères ENI</title>
</head>
<body>
	<%@include file="/EnteteEnchere.html" %>
	<!-- <header>
	<div class="EnTete">
		<h1>ENI-Enchères</h1>
		
		<a href="/Enchere/WebContent/WEB-INF/jsp/Connection.jsp"  >S'inscrire - Se connecter</a>
	
	</div>
	
	</header>
	 -->
	<div class="Recherche" >
		<h2>Liste des enchères</h2>
		<form>
		<p>Filtres :<p>
		<input type="search" id="recherche-article" name="s" maxlength="50"  placeholder="Le nom de l'article contient" >
		<p>Catégorie :<p> 
		<select name="categories" id="select-categorie">
		<option value="toutes">Toutes</option>
		<option value="informatique">Informatique</option>
		<option value="ameublement">Ameublement</option>
		<option value="vetement">Vêtement</option>
		<option value="sportLoisir">Sport/Loisirs</option>
		</select>
		
		<button id="btnRecherche" type="submit" >Rechercher</button>
		</form>
	</div>
	
	<div class="affichageArticle">
	
	<!-- Afficher derniers articles ajoutés -->
	
	<!-- Afficher dernières ventes réalisées -->
	
	
	
	</div>

</body>
</html>