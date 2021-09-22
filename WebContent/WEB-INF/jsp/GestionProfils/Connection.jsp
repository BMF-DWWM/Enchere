<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
	<%@include file="../../../EnteteEnchere.html" %>
	<h1>Connexion</h1>
<form method="post" action="Connection">

	<label>Pseudo</label>
	<input type="text" name="pseudo">
	
	<label>Mot de passe</label>
	<input type="text" name="password">
	
	<input type="submit" value="Connexion">

	<div>
		<a href="<%=request.getContextPath()%>/CreerCompte"><input type="button" name="creerCompte" Value="Créer un compte"></a>
	</div>
</form>

</body>
</html>