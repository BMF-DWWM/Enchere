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
	<!-- <header>
	<div class="EnTete">
		<h1>ENI-Enchères</h1>
		
		<a href="/Enchere/WebContent/WEB-INF/jsp/Connection.jsp"  >S'inscrire - Se connecter</a>
	
	</div>
	
	</header>
	 -->
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
		
		<button id="btnRecherche" type="submit" >Rechercher</button>
		</form>
	</div>
	
	<div class="affichageArticle">
	<c:forEach items="${listeArticle}" var="article">
  		<div class='containerArticle'></div>
  			<div class='containerImage'></div>
  			<div class='containerTexte'>
  			<a href="<%=request.getContextPath()%>/ServletListeEncheres">${article.nomArticle }</a>
  			<label> Prix : ${article.prixInitial }</label>
  			<label>Fin de l'enchère : ${article.dateFinEncheres }</label>
  			<label>Vendeur : <a href="<%=request.getContextPath()%>/ServletListeEncheres">${article.pseudoUtilisateur }</a></label>
  			</div>
 	</c:forEach>
	<!-- Afficher derniers articles ajoutés -->
	
	<!-- Afficher dernières ventes réalisées -->
	
	
	
	</div>

</body>
</html>