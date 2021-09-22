package fr.eni.Enchere.DAL;


import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Enchere;
import fr.eni.Enchere.BO.Retrait;

public class DAOFactory {
	public static DAOUtilisateur getUtilisateurDAO() {
		return new UtilisateurDAOImplt();
	}
	
	public static DAOArt<ArticlesVendu> getArticleDAO(){
		return new ArticleDAOImplt();
	}
	
	public static DAOArt<Enchere> getEnchereDAO(){
		return new EnchereDAOImplt();
	}
	
	public static DAOArt<Retrait> getRetraitDAO(){
		return new RetraitDAOImplt();
	}
}
