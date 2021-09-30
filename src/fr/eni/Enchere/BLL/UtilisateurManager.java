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
		Utilisateur utilisateur = dao.VerifConnection(Pseudo, Password);
		 return utilisateur;
		 
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
