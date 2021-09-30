package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.BLL.BLLExceptions;
import fr.eni.Enchere.BLL.UtilisateurManager;
import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DAOFactory;
import fr.eni.Enchere.DAL.DAOUtilisateur;




@WebServlet("/CreerCompte")
public class CreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/GestionProfils/CreationProfil.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd;
		UtilisateurManager utilManager = new UtilisateurManager();
		
		String newPseudo = request.getParameter("pseudo");		
		String newNom = request.getParameter("nom");
		String newPrenom = request.getParameter("prenom");
		String newEmail = request.getParameter("email");
		String newTelephone = request.getParameter("telephone");
		String newRue = request.getParameter("rue");
		String newCodePostal = request.getParameter("codePostal");
		String newVille = request.getParameter("ville");
		String newPassword = request.getParameter("password");
		String newPasswordConfirm = request.getParameter("passwordConfirm");
		
		boolean utilVerif = utilManager.verifPseudoMail(newPseudo, newEmail);

		try {
		boolean verifmdpconfirm = utilManager.verifmdpconfirm(newPassword, newPasswordConfirm);

		if(utilVerif == false && verifmdpconfirm == true) {

			Utilisateur newUtilisateur = new Utilisateur(newPseudo, newNom, newPrenom, newEmail, newTelephone, newRue, newCodePostal, newVille, newPassword);
			utilManager.createUtilisateur(newUtilisateur);
			rd = request.getRequestDispatcher("/Connection");
			rd.forward(request, response);
		}
			else {
				rd = request.getRequestDispatcher("WEB-INF/jsp/GestionProfils/CreationProfil.jsp");
				rd.forward(request, response);
			}
		} catch (BLLExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

