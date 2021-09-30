package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Enchere;
import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOArt;
import fr.eni.Enchere.DAL.DAOFactory;
import fr.eni.Enchere.DAL.DAOUtilisateur;

/**
 * Servlet implementation class AffichageProfil
 */
@WebServlet("/AffichageProfil")
public class AffichageProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String pseudo = request.getParameter("pseudoVendeur");
		
		
		System.out.println(pseudo);
		
		try {
			Utilisateur vendeur = DAOFactory.getUtilisateurDAO().selectbyPseudo(pseudo);
			request.setAttribute("vendeur", vendeur);
		if(vendeur != null) {
			rd = request.getRequestDispatcher("WEB-INF/jsp/GestionProfils/Profil.jsp");
			rd.forward(request, response);
			
		}
		else {
			rd = request.getRequestDispatcher("WEB-INF/jsp/Erreur.jsp");
			rd.forward(request, response);
		}
		}catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
