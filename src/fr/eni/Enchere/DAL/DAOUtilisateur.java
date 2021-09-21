package fr.eni.Enchere.DAL;

import java.util.List;

import fr.eni.Enchere.BO.Utilisateur;

public interface DAOUtilisateur{
	
	public DAOUtilisateur selectbyId(int id) throws  DALException;
		
	public List<Utilisateur> selectAll() throws DALException; 
	
	public void update (Utilisateur t) throws DALException;
	
	public void insert (Utilisateur t) throws DALException;
	
	public void delete (int id) throws DALException; 
	
	public DAOUtilisateur VerifConnection(String ps,String p);
	

}
