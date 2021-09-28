package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOFactory;

/**
 * Servlet implementation class modifieProfil
 */
@WebServlet("/modifieProfil")
public class modifieProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/GestionProfils/ModifierProfil.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd;
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		
		int noUtilisateur = utilisateur.getNoUtilisateur();
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String password = request.getParameter("password");
		int credit = utilisateur.getCredit();
		boolean administrateur = utilisateur.isAdministrateur();
		
		
		Utilisateur modifUtil = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, password, credit, administrateur);
		
		session.setAttribute("utilisateur", modifUtil);
		try {
			DAOFactory.getUtilisateurDAO().update(modifUtil);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionProfils/ModifierProfil.jsp");
		rd.forward(request, response);
	}

}
