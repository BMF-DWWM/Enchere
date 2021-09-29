<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enchère Remportée</title>
<style><%@include file="/Style.css"%></style>
</head>
<body>
<%@include file="/WEB-INF/jsp/HeadersFooters/Entete.jsp" %>
</body>	
		<label>${utilisateurGagnantEnchere.pseudo } a remporté l'enchere</label>
		<label>Meilleur offre : ${article.nomArticle }</label>
		<label> Mise à prix : ${article.prixInitial }</label>
		<label>Fin de l'enchère : ${article.dateFinEncheres }</label>
		<label>Retrait : ${retrait.rue } ${retrait.codePostal } ${retrait.ville}</label>
		<label> Vendeur : ${article.pseudoUtilisateur }</label>
		<label>Ma proposition : </label>
</html>