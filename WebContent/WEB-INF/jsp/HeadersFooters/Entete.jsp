<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Entete Enchere</title>
<style>
<%@include file="../../../Style.css"%>
</style>
</head>
<body>  

	<header>
		<div class="EnTete">
			<div >
			<a class="logo" href="<%=request.getContextPath()%>/ServletListeEncheres" >ENI-Encheres</a>
			<!--  <h1>ENI-Encheres</h1>-->
			<p>Achetez, vendez, découvrez !</p>
			</div>		
			<div>
				<div>
					<d:if test="${utilisateur.noUtilisateur == null}">
							<a href="<%=request.getContextPath()%>/Connection"><input class="containerBoutonEntete"  type="button" name="Connection" Value="Se connecter"></a>
							<a href="<%=request.getContextPath()%>/CreerCompte"><input class="containerBoutonEntete"  type="button" name="Connection" Value="S'inscrire"></a>
					</d:if>
				</div>
				<div>	
					<d:if test="${utilisateur.noUtilisateur != null}"> 
						<a href="<%=request.getContextPath()%>/ServletMisEnVenteArticle"><input class="containerBoutonEntete" type="button" Value="Vendre un article"></a>
						<a href="<%=request.getContextPath()%>/modifieProfil"><input class="containerBoutonEntete" type="button" Value="Mon Profil"></a>
						<a href="<%=request.getContextPath()%>/Connection"><input class="containerBoutonEntete" type="button" Value="Déconnexion"></a>
					</d:if>
				</div>
			</div>
		</div>

	</header>

</body>
</html>