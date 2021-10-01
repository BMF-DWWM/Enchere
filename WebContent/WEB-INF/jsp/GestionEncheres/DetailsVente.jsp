<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail Vente</title>
<style><%@include file="/Style.css"%></style>
</head>
<body>
<%@include file="/WEB-INF/jsp/HeadersFooters/Entete.jsp" %>	

<!-- PUUUSSSHHH -->
<div class="containerVente">
	<form action="<%=request.getContextPath()%>/ServletDetailArticle" method="post">
	<h2>Détail vente</h2>
	<div class="containerFormulaire">
	<div class="container1">
	
		<label class="nomArticle">${article.nomArticle }</label>
		<label >Description : ${article.description }</label>
		<c:choose>
			<c:when test="${article.noCategorie ==1}">
				<label class="">Catégorie : Informatique</label>
			</c:when>
			<c:when test="${article.noCategorie ==2}">
				<label>Catégorie : Ameublement</label>
			</c:when>
			<c:when test="${article.noCategorie ==3}">
				<label>Catégorie : Vétement</label>
			</c:when>
			<c:when test="${article.noCategorie ==4}">
				<label>Catégorie : Sport / Loisir</label>
			</c:when>
		</c:choose>
	
		<label>Meilleure offre : ${article.nomArticle }</label>
		<label> Mise à prix : ${article.prixInitial }</label>
		<label>Fin de l'enchère : ${article.dateFinEncheres }</label>
		<label>Retrait : ${retrait.rue } ${retrait.codePostal } ${retrait.ville}</label>
		<label>Vendeur : ${article.pseudoUtilisateur }</label>
		<label>Ma proposition : </label>
		<c:if test="${not empty utilisateur }">
			<c:if test="${empty rechercheEnchere.montantEnchere }"><input  type="number" name="montantEnchere" value="${article.prixInitial+1 }"></c:if>
		<c:if test="${ not empty rechercheEnchere.montantEnchere }"><input  type="number" name="montantEnchere" value="${rechercheEnchere.montantEnchere+1 }"></c:if>
		<input class="containerBouton" type="submit" value="Enchérir">
		</c:if>
		
		
			<a href="<%=request.getContextPath()%>/ServletListeEncheres"><input class="containerBouton" type="button" name="retour" Value="Retour"></a>
			<c:if test="${article.noUtilisateur == utilisateur.noUtilisateur && getdate < article.dateDebutEncheres }"><div class="containerBoutonWide"><a href="<%=request.getContextPath()%>/AnnuleVente?noArticle=${article.noArticle}">Annuler la vente</a></div></c:if>
			
			
		</div>
	
		</div>
	</form>
</div>	

</body>
</html>