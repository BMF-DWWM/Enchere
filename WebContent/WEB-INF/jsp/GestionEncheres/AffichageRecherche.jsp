<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Affichage recherche</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/HeadersFooters/EnteteEnchere.html" %>
	
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
	
	<div class="filtres">
		<div>
			<p>Achats :</p>
			<input type="checkbox" id="encheresOuvertes" name="enchereOuvertes" checked>
			<label for="encheresOuvertes">Enchères ouvertes</label>
		</div>
		<div>
			<input type="checkbox" id="mesEncheresEnCours" name="mesEncheresEnCours" checked>
			<label for="mesEncheresEnCours">Mes enchères en cours</label>
		</div>
		<div>
			<input type="checkbox" id="mesEncheresRemportees" name="mesEncheresRemportees" checked>
			<label for="mesEncheresRemportees">Mes enchères remportées</label>
		</div>
		
		<div>
			<p>Mes Ventes :</p>
			<input type="checkbox" id="mesVentesEnCours" name="mesVentesEnCours" checked>
			<label for="mesVentesEnCours">Mes ventes en cours</label>
		</div>
		<div>
			<input type="checkbox" id="ventesNonDebutees" name="ventesNonDebutees" checked>
			<label for="ventesNonDebutees">Ventes non débutées</label>
		</div>
		<div>
			<input type="checkbox" id="ventesTerminees" name="ventesTerminees" checked>
			<label for="ventesTerminees">Ventes terminées</label>
		</div>
	</div>

</body>
</html>