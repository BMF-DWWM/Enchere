<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style><%@include file="/Style.css"%></style>
<!-- <link href="/WEB-INF/Style.css" rel="stylesheet"> -->
<title>Liste des Enchères ENI</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/HeadersFooters/EnteteEnchere.html" %>
	
	<div class="Recherche" >
		<h2>Liste des enchères</h2>
		<form action="<%=request.getContextPath()%>/ServletListeEncheres" method="post">
		<p>Filtres :<p>
		<input type="search" id="recherche-article" name="s" maxlength="50"  placeholder="Le nom de l'article contient" >
		<p>Catégorie :<p> 
		<select name="categories" id="select-categorie">
		<option value="">Toutes</option>
		<option value="1">Informatique</option>
		<option value="2">Ameublement</option>
		<option value="3">Vêtement</option>
		<option value="4">Sport/Loisirs</option>
		</select>
			<select name="achatVente" id="achat vente">
		<option value="">-----Achats-----</option>
		<option value="1">enchères ouverte</option>
		<option value="2">mes enchères en cours</option>
		<option value="3">mes enchères remportées</option>
		<option value="">-----Mes ventes-----</option>
		<option value="4">Mes ventes en cours</option>
		<option value="5">ventes non débutées</option>
		<option value="6">ventes terminées</option>
		</select>
		
		<button id="btnRecherche" type="submit" >Rechercher</button>
		</form>
	</div>
	
	<div class="affichageArticle">
	<c:forEach items="${listeArticle}" var="article">
  		<div class='containerArticle'></div>
  			<div class='containerImage'></div>
  			<div class='containerTexte'>
   			<a href="<%=request.getContextPath()%>/ServletDetailArticle?noArticle=${article.noArticle}" >${article.nomArticle }</a>
  			<label> Prix : ${article.prixInitial }</label>
  			<label>Description : ${article.description }</label>
  			<label>Fin de l'enchère : ${article.dateFinEncheres }</label>
  			<label>Vendeur : <a href="<%=request.getContextPath()%>/ServletListeEncheres">${article.pseudoUtilisateur }</a></label> 
  			</div>
 	</c:forEach>
	<!-- Afficher derniers articles ajoutés -->
	
	<!-- Afficher dernières ventes réalisées -->
	
	
	
	</div>

</body>
</html>