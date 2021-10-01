package fr.eni.Enchere.BLL;

import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOFactory;
import fr.eni.Enchere.DAL.DAOUtilisateur;

public class UtilisateurManager {
	private static DAOUtilisateur dao;

	
	static {
		dao = DAOFactory.getUtilisateurDAO();
	}
	
	public void deleteUser(int idUser) throws DALException {
		dao.deleteUser(idUser);

	}
	
	
	public void update(Utilisateur utilisateur) throws DALException {
		dao.update(utilisateur);

	}
	
	
	public boolean updateMdp(String pseudo,String password) throws DALException {
		return dao.updateMdp(pseudo, password);

	}
	
	public Utilisateur selectbyPseudo(String Pseudo) throws DALException {
		Utilisateur utilisateur = dao.selectbyPseudo(Pseudo);
		 return utilisateur;
		 
	}
	
	public Utilisateur verifConnection(String Pseudo,String Password) {
		 return dao.VerifConnection(Pseudo, Password);
		
		 
	}
	
	public Utilisateur selectbyId(int id) throws DALException{
		return dao.selectbyId(id);
	}
	
	public boolean verifPseudoMail(String newPseudo,String newEmail) {
		return dao.VerifPseudo(newPseudo, newEmail);
		
	}

	public boolean verifmdpconfirm (String newPassword,String newPasswordConfirm)throws BLLExceptions{
		if (newPassword.equals(newPasswordConfirm)) {
		}
		return true;
		
	}
	
	public void createUtilisateur (Utilisateur utilisateur) throws BLLExceptions{
		dao.CreationCompte(utilisateur);
	}
	
	
}
