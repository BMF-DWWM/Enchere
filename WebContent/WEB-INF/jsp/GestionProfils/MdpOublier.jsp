<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mot de passe Oublier</title>
</head>
<body>
<form method="post" action="MdpOublier">
	<label>Pseudo</label>
	<input type="text" name="pseudo">
	
	<label>Mot de passe</label>
	<input type="text" name="password">
	
	<label>Confirmation mot de passe</label>
	<input type="text" name="passwordConfirm">
	
	<input type="submit" value="Modifier">
</form>
</body>
</html>