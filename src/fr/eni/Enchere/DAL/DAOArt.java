package fr.eni.Enchere.DAL;

import java.util.List;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Utilisateur;

public interface DAOArt<T> {

		
		public T selectbyId(int id) throws  DALException;
			
		public List<T> selectAll() throws DALException; 
		
		public void update (T t) throws DALException;
		
		public void insert (T t) throws DALException;
		
		public void delete (int id) throws DALException; 
		
		public List<T> selectAllByMotCle(String mot, String categorie, String option) throws DALException;
		
		
		

	}


