package fr.eni.Enchere.BLL;

import java.util.List;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOArt;
import fr.eni.Enchere.DAL.DAOFactory;
import fr.eni.Enchere.DAL.DAOUtilisateur;

public class ArticlesVendusManager {
	private static DAOArt<ArticlesVendu> daoArticle;

	
	static {
		daoArticle =  DAOFactory.getArticleDAO();
	}
	

	public ArticlesVendu selectbyId(int id) throws DALException {
		return daoArticle.selectbyId(id);
		
	}

	public List<ArticlesVendu> selectAll() throws DALException{
		return daoArticle.selectAll();
	}
	
	
	public List<ArticlesVendu> selectAllByMotCle(String mot, String categorie, String option) throws DALException {
		return daoArticle.selectAllByMotCle(mot, categorie, option);
	}
	
	public void update(ArticlesVendu article ) throws DALException {
		
	}
	
	public void insert(ArticlesVendu article) throws DALException{
		
	}
	public void delete(int noArticle) throws DALException {
		
	}
	
	public void updateNoAcquereur(int noAcquereur, int noArticle) throws DALException {
		
	}
	
}
