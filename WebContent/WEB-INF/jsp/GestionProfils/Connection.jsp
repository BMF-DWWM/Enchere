<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<style><%@include file="/Style.css"%></style>
</head>
<body>
<%@include file="/WEB-INF/jsp/HeadersFooters/EnteteEnchere.html" %>
<form method="post" action="Connection">
	<h2>Connexion</h2>
	<div class="container1">
		<div class="containerConnexion">
		
			<label>Pseudo</label>
			<input type="text" name="pseudo">
			
			<label>Mot de passe</label>
			<input type="text" name="password">
			<div class="boutonsConnexion" >
			<input class="containerBouton" type="submit" value="Connexion">
			
			
				<div>
					<a href="<%=request.getContextPath()%>/CreerCompte"><input class="containerBouton" type="button" name="creerCompte" Value="Créer un compte"></a>
				</div>
				
				<div>
					<a href="<%=request.getContextPath()%>/modifieProfil"><input class="containerBouton" type="button" name="modifProfil" Value="Modifier Profil"></a>
				</div>
				<div>
					<a href="<%=request.getContextPath()%>/MdpOublier"><input class="containerBouton" type="button" name="MdpOublier" Value="Mot de passe oublié"></a>
				</div>
			</div>
		</div>
	</div>	
</form>

</body>
</html>