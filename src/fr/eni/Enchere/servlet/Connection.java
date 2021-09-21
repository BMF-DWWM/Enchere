package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DAO;
import fr.eni.Enchere.DAL.DAOFactory;


/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Connection.jsp");
		rd.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		
		String pseudo = request.getParameter("pseudo");		
		String password = request.getParameter("password");
		
		System.out.println("Pseudo : "+ pseudo);
		System.out.println("Password : "+ password);	
		
		DAO<Utilisateur> utilDAO = DAOFactory.getUtilisateurDAO().VerifConnection(pseudo, password);

		if(utilDAO != null) {
			rd = request.getRequestDispatcher("WEB-INF/jsp/Connecter.jsp");
			rd.forward(request, response);
		}
		else {
			rd = request.getRequestDispatcher("WEB-INF/jsp/Erreur.jsp");
			rd.forward(request, response);
		}
	
	}

}
		


