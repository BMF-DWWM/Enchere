package fr.eni.Enchere.DAL;

import java.util.List;

import fr.eni.Enchere.BO.Enchere;
import fr.eni.Enchere.BO.Utilisateur;

public interface DAOUtilisateur{
	
	public Utilisateur selectbyId(int id) throws  DALException;
		
	public List<Utilisateur> selectAll() throws DALException; 
	
	public void update (Utilisateur t) throws DALException;
	
	public void insert (Utilisateur t) throws DALException;
	
	public Enchere VerifDelete (int idUser) throws DALException; 
	
	public Utilisateur VerifConnection(String pseudo,String password);
	
	public boolean VerifPseudo(String Pseudo,String Email);
	
	public Utilisateur CreationCompte (Utilisateur utilisateur);

	boolean updateMdp(String Pseudo,String Password) throws DALException;

	Utilisateur selectbyPseudo(String pseudo) throws DALException;
	
	public void deleteUser(int idUser)throws DALException;

}
