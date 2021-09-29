<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Supprimer Profil</title>
</head>
<body>
<h1>test</h1>
<form action="SupprimerProfil" method="post">

	<p>êtes vous sur de vouloir supprimer votre profil ?</p>
	
	<a href="<%=request.getContextPath()%>/ServletListeEncheres"><input type="button" name="annuler" Value="Annuler"></a>
	
	<input type="submit" Value="Supprimer">
	
</form>

</body>
</html>