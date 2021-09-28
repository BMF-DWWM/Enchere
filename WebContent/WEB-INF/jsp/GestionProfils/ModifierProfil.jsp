<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style><%@include file="/Style.css"%></style>
<title>Creation Profil</title>
</head>
<body>
		<%@include file="/WEB-INF/jsp/HeadersFooters/EnteteEnchere.html" %>
	
<div class="creationProfil">
	<form action="<%=request.getContextPath()%>/modifieProfil" method="post">
	<h2 class="titrepProfil">Mon profil</h2>
			<div class="container1">
				<div class="containerFormulaire">
					<div class="label-input-container">
						<label class="my-label">Pseudo : </label> 
						<input type="text" name="pseudo"  value="${utilisateur.pseudo}" >
						
					</div>
					<div class="label-input-container">
						<label class="my-label">Email :</label> 
						<input type="text" name="email"  value="${utilisateur.email}">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Prénom :</label> 
						<input type="text" name="prenom"  value="${utilisateur.prenom}">
					</div>
					<div class="label-input-container">
						<label class="my-label">Nom :</label> 
						<input type="text" name="nom"  value="${utilisateur.nom}">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Téléphone :</label> 
						<input type="text" name="telephone"  value="${utilisateur.telephone}">
					</div>
					<div class="label-input-container">
						<label class="my-label">Rue :</label> 
						<input type="text" name="rue"  value="${utilisateur.rue}">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Code postal :</label> 
						<input type="text" name="codePostal"  value="${utilisateur.codePostal}">
					</div>
					<div class="label-input-container">
						<label class="my-label">Ville :</label> 
						<input type="text" name="ville"  value="${utilisateur.ville}">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Mot de passe :</label> 
						<input type="text" name="password"  value="${utilisateur.motDePasse}">
					</div>	
					<div class="label-input-container">
						<label class="my-label">Confirmation :</label> 
						<input type="text" name="passwordConfirm"  value="${utilisateur.motDePasse}">
					</div>
				</div>
			</div>
			
			<div >
			<input class="containerBouton" type="submit" value="Modifier">
			<a href="<%=request.getContextPath()%>/ServletListeEncheres"><input class="containerBouton" type="button" name="Retour" Value="Annuler"></a>
			</div>
			<div >
			<input class="containerBouton" type="submit" value="Modifier">
			<a href="<%=request.getContextPath()%>/ServletListeEncheres"><input class="containerBouton" type="button" name="Retour" Value="Annuler"></a>
			</div>
			
		
	</form>
</div>
	

<a href="<%=request.getContextPath()%>/Connection"><input type="button" Value="Déconnexion"></a>
<a href="<%=request.getContextPath()%>/modifieProfil"><input type="button" Value="Retour"></a>



</body>
</html>