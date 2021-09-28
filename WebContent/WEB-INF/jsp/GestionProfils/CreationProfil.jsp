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
		<%@include file="/WEB-INF/jsp/HeadersFooters/Entete.jsp" %>
	
<div class="creationProfil">
	<form action="<%=request.getContextPath()%>/CreerCompte" method="post">
	<h2 class="titrepProfil">Mon profil</h2>
			<div class="container1">
				<div class="containerFormulaire">
					<div class="label-input-container">
						<label class="my-label">Pseudo :</label> 
						<input type="text" name="pseudo">
					</div>
					<div class="label-input-container">
						<label class="my-label">Email :</label> 
						<input type="text" name="email">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Prénom :</label> 
						<input type="text" name="prenom">
					</div>
					<div class="label-input-container">
						<label class="my-label">Nom :</label> 
						<input type="text" name="nom">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Téléphone :</label> 
						<input type="text" name="telephone">
					</div>
					<div class="label-input-container">
						<label class="my-label">Rue :</label> 
						<input type="text" name="rue">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Code postal :</label> 
						<input type="text" name="codePostal">
					</div>
					<div class="label-input-container">
						<label class="my-label">Ville :</label> 
						<input type="text" name="ville">
					</div>
				</div>
				<div class="containerFormulaire">	
					<div class="label-input-container">
						<label class="my-label">Mot de passe :</label> 
						<input type="text" name="password">
					</div>	
					<div class="label-input-container">
						<label class="my-label">Confirmation :</label> 
						<input type="text" name="passwordConfirm">
					</div>
				</div>
			</div>
			
			<div >
			<input class="containerBouton" type="submit" value="Créer">
			<a href="<%=request.getContextPath()%>/ServletListeEncheres"><input class="containerBouton" type="button" name="Retour" Value="Annuler"></a>
			</div>
			
		
	</form>
</div>
	
</body>
</html>