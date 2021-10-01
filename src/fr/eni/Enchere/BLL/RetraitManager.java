package fr.eni.Enchere.BLL;

import java.util.List;

import fr.eni.Enchere.BO.Retrait;
import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOArt;
import fr.eni.Enchere.DAL.DAOFactory;

public class RetraitManager {
	private static DAOArt<Retrait> daoRetrait;

	
	static {
		daoRetrait = DAOFactory.getretraitDAO();
	}
	

	public Retrait selectbyId(int id) throws DALException {
		return daoRetrait.selectbyId(id);
		
	}

	public List<Retrait> selectAll() throws DALException{
		return daoRetrait.selectAll();
		
	}
	
	
	public void update(Retrait retrait) throws DALException{
		daoRetrait.update(retrait);
	}
	
	public void insert(Retrait retrait) throws DALException {
		daoRetrait.insert(retrait);
	}
	
	public void delete(int noArticle) throws DALException {
		daoRetrait.delete(noArticle);
	}
}
