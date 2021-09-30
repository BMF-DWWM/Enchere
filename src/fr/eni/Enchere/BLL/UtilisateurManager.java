package fr.eni.Enchere.BLL;

import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DAOFactory;
import fr.eni.Enchere.DAL.DAOUtilisateur;

public class UtilisateurManager {
	private static DAOUtilisateur dao;

	
	static {
		dao = DAOFactory.getUtilisateurDAO();
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
