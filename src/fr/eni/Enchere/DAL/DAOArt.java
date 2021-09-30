package fr.eni.Enchere.DAL;

import java.util.List;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Enchere;
import fr.eni.Enchere.BO.Utilisateur;

public interface DAOArt<T> {

		
		public T selectbyId(int id) throws  DALException;
			
		public List<T> selectAll() throws DALException; 
		
		public void update (T t) throws DALException;
		
		public void insert (T t) throws DALException;
		
		public void delete (int id) throws DALException; 
		
		public List<T> selectAllByMotCle(String mot, String categorie, String option) throws DALException;
		
		public Enchere selectbyIdUserAndIdArticle(int idUtilisateur, int IdArticle) throws DALException;
		
		public Enchere sqlSelectMax (int idArticle) throws DALException;
		
		public void delete(int noArticle, int noutilisateur) throws DALException ;
		
		public void updateNoAcquereur (int noAcquereur, int noArticle) throws DALException;
		
		public Enchere selectbyIdUser(int iduser) throws DALException ;
		
		public void UpdateCreditInsertEnchere(int soldeCredit, int montantEnchere, int noUtilisateur) throws DALException;
		
		public void UpdateCreditUpdateEnchere(int soldeCredit, int nouvelleEnchere, int ancienneEnchere, int noUtilisateur) throws DALException;
		
		public void UpdateCreditRollBackEnchere(int soldeCredit, int derniereEnchere, int noUtilisateur) throws DALException;
		
		

	}


