<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style><%@include file="/Style.css"%></style>
<title>Profil</title>
</head>
<body>
	<%@include file="/WEB-INF/jsp/HeadersFooters/Entete.jsp" %>
	
	<div class="lectureProfil">
	<form action="<%=request.getContextPath()%>" method="get">
	<h2>Mon profil</h2>
			<div class="container1">
				<div class="containerFormulaire">
					<div class="label-input-container">
						<label class="my-label">Pseudo : </label> 
						<input type="text" name="pseudo"  value="${vendeur.pseudo}" >
						
					</div>
					<div class="label-input-container">
						<label class="my-label">Email :</label> 
						<input type="text" name="email"  value="${vendeur.email}">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Prénom :</label> 
						<input type="text" name="prenom"  value="${vendeur.prenom}">
					</div>
					<div class="label-input-container">
						<label class="my-label">Nom :</label> 
						<input type="text" name="nom"  value="${vendeur.nom}">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Téléphone :</label> 
						<input type="text" name="telephone"  value="${vendeur.telephone}">
					</div>
					<div class="label-input-container">
						<label class="my-label">Rue :</label> 
						<input type="text" name="rue"  value="${vendeur.rue}">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Code postal :</label> 
						<input type="text" name="codePostal"  value="${vendeur.codePostal}">
					</div>
					<div class="label-input-container">
						<label class="my-label">Ville :</label> 
						<input type="text" name="ville"  value="${vendeur.ville}">
					</div>
				</div>
				
			</div>
			
			<div >
			<a href="<%=request.getContextPath()%>/modifieProfil"><input class="containerBoutonWide" type="button" name="modifierProfil" Value="Modifier mon profil"></a>
			</div>
			<div >
			<a href="<%=request.getContextPath()%>/ServletListeEncheres"><input class="containerBouton" type="button" name="Retour" Value="Retour"></a>
			</div>
			
		
	</form>
</div>