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
<%@include file="/WEB-INF/jsp/HeadersFooters/Entete.jsp" %>

	
	<div class="Recherche" >
		<form action="<%=request.getContextPath()%>/ServletListeEncheres" method="post">
			<h2>Liste des enchères</h2>
				<div class="container1">
					<div class="containerConnexion">
						<p>Filtres :<p>
						<input type="search" id="recherche-article" name="s" maxlength="50"   placeholder="Mots-clés" >
						<p>Catégorie :<p> 
						<select name="categories" id="select-categorie">
							<option value="">Toutes</option>
							<option value="1">Informatique</option>
							<option value="2">Ameublement</option>
							<option value="3">Vêtement</option>
							<option value="4">Sport/Loisirs</option>
						</select>
						<c:if test="${not empty usersession  }">
						<select name="achatVente" id="achat vente">
							<option value="">-----Achats-----</option>
							<option value="1">enchères ouverte</option>
							<option value="3">mes enchères remportées</option>
							<option value="">-----Mes ventes-----</option>
							<option value="4">Mes ventes en cours</option>
							<option value="5">ventes non débutées</option>
							<option value="6">ventes terminées</option>
						</select>
						</c:if>
						
					</div>
					<button class="containerBouton" id="btnRecherche" type="submit" >Rechercher</button>
				</div>
		</form>
	</div>
		
	
	<div class="affichageArticle">
		<c:forEach items="${listeArticle}" var="article">
  		<div class='container1'>
  			<div class=''>
  			<c:if test="${getdate > article.dateFinEncheres or getdate < article.dateDebutEncheres}"><div><a class="nomArticle" href="<%=request.getContextPath()%>/EnchereRemportee?noArticle=${article.noArticle}">${article.nomArticle }</a></div> </c:if>
  			<c:if test="${getdate < article.dateFinEncheres}"><div><a class="nomArticle" href="<%=request.getContextPath()%>/ServletDetailArticle?noArticle=${article.noArticle}">${article.nomArticle }</a></div></c:if>
  			<label class="ligne">Prix : ${article.prixInitial }</label>
  			<label class="ligne">Description : ${article.description }</label>
  			<label class="ligne">Fin de l'enchère : ${article.dateFinEncheres }</label>
  			<label class="ligne">Vendeur : <a href="<%=request.getContextPath()%>/AffichageProfil?pseudoVendeur=${article.pseudoUtilisateur}">${article.pseudoUtilisateur }</a></label>
  			</div>
  		</div>
 	</c:forEach>
	
	
	 
	</div>

</body>
</html>