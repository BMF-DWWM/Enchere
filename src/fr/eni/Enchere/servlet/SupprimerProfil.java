package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.BO.Enchere;
import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOFactory;


@WebServlet("/SupprimerProfil")
public class SupprimerProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/GestionProfils/SupprimerProfil.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		HttpSession session = request.getSession();
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int idUser = utilisateur.getNoUtilisateur();
		System.out.println("je suis " + idUser );
		
		try {
		Enchere enchereMax = DAOFactory.getUtilisateurDAO().delete(idUser);
		int idarticle = enchereMax.getNoArticle();
		
		if(enchereMax != null) {
			request.setAttribute("rechercheEnchere", idarticle);
			response.sendRedirect("/ServletDetailArticle");
		}
		else {
			session.invalidate();
			response.sendRedirect("/ServletListeEncheres");
		}

		} catch (DALException e) {
			System.out.println("impossible de recuperer l'idUser" + idUser);
			e.printStackTrace();
		}

	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
