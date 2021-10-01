package fr.eni.Enchere.BLL;

import java.util.List;

import fr.eni.Enchere.BO.Enchere;
import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOArt;
import fr.eni.Enchere.DAL.DAOFactory;
import fr.eni.Enchere.DAL.DAOUtilisateur;

public class EnchereManager {
	private static DAOArt<Enchere> daoEnchere;

	
	static {
		daoEnchere = DAOFactory.getEnchereDAO();
	}
	

	public Enchere selectbyIdUserAndIdArticle(int idUtilisateur, int IdArticle) throws DALException {
		return daoEnchere.selectbyIdUserAndIdArticle(idUtilisateur, IdArticle);
		
	}

	public Enchere selectbyId(int id) throws DALException {
		return daoEnchere.selectbyId(id);
		
	}
	
	
	public List<Enchere> selectAll() throws DALException {
		return daoEnchere.selectAll();
	}
	
	public void update(Enchere enchere) throws DALException {
		
	}
	
	public void insert(Enchere enchere) throws DALException {
		
	}
	
	public void delete(int noArticle) throws DALException {
		
	}
	
	public List<Enchere> selectAllByMotCle(String mot, String categorie, String option) throws DALException {
		return daoEnchere.selectAllByMotCle(mot, categorie, option);
	}
	
	public Enchere sqlSelectMax(int idArticle) throws DALException{
		return daoEnchere.sqlSelectMax(idArticle);
	}
	
	public void delete(int noArticle, int noutilisateur) throws DALException {
		
	}
	
	public Enchere selectbyIdUser(int iduser) throws DALException {
		return daoEnchere.selectbyIdUser(iduser);
	}
	
	public void UpdateCreditInsertEnchere(int soldeCredit, int montantEnchere, int noUtilisateur) throws DALException {
		
	}
	public void UpdateCreditUpdateEnchere(int soldeCredit, int nouvelleEnchere, int ancienneEnchere, int noUtilisateur) throws DALException {
		
	}
	public void UpdateCreditRollBackEnchere(int soldeCredit, int derniereEnchere, int noUtilisateur) throws DALException {
		
	}
}
