package fr.eni.Enchere.DAL;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Utilisateur;

public class DAOFactory {
	public static DAO<Utilisateur> getUtilisateurDAO() {
		return new UtilisateurDAOImplt();
	}
	
	public static DAO<ArticlesVendu> getArticleDAO(){
		return new ArticleDAOImplt();
	}
}
