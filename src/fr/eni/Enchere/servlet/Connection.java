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
import fr.eni.Enchere.DAL.DAOFactory;



/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/GestionProfils/Connection.jsp");
		
		session.invalidate();
		
		rd.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		HttpSession session = request.getSession();
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		
		session.setAttribute("pseudo", pseudo);
		session.setAttribute("password", password);
		
		System.out.println("Pseudo : "+ pseudo);
		System.out.println("Password : "+ password);	
		
		Utilisateur utilDAO = DAOFactory.getUtilisateurDAO().VerifConnection(pseudo, password);
		
		session.setAttribute("utilisateur", utilDAO);

		if(utilDAO != null) {
			response.sendRedirect("/Enchere/ServletListeEncheres");
			//rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionEncheres/ListeEncheres.jsp");
			//rd.forward(request, response);
		}
		else {
			rd = request.getRequestDispatcher("WEB-INF/jsp/Erreur.jsp");
			rd.forward(request, response);
		}
	
	}

}
		


