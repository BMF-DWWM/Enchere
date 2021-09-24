<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mot de passe Oublier</title>
<style><%@include file="/Style.css"%></style>
</head>
<body>
	<%@include file="/WEB-INF/jsp/HeadersFooters/EnteteEnchere.html" %>
	
<form method="post" action="MdpOublier">
	<div class="container1">
		<div class="containerFormulaire">
			<div class="label-input-container">		
				<label class="my-label">Pseudo : </label>
				<input type="text" name="pseudo">
			</div>
			<div class="label-input-container">	
				<label class="my-label">Mot de passe : </label>
				<input type="text" name="password">
			</div>
			<div class="label-input-container">		
				<label class="my-label">Confirmation : </label>
				<input type="text" name="passwordConfirm">
			</div>
		</div>
	</div>
	<div>
				<input class="containerBouton" type="submit" value="Modifier">
	</div>		
		
	
</form>
</body>
</html>