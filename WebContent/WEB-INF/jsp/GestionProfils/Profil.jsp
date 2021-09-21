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
		<%@include file="/EnteteEnchere.html" %>
	
	<div class="lectureProfil">
	<form action="<%=request.getContextPath()%>" method="get">
	<h2 class="titrepProfil">Mon profil</h2>
		<div class="containerFormulaire">
			<div class="container1">
			<label>Pseudo :</label>
			<label>Nom :</label> 
			<label>Prénom :</label>
			<label>Email :</label> 
			<label>Téléphone :</label> 
			<label>Rue :</label> 
			<label>Code postal :</label> 
			<label>Ville :</label> 
			<label>Mot de passe :</label> >
			<label>Confirmation :</label> 
			</div>
		</div>
	</form>
</div>