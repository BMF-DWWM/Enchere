package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOFactory;


@WebServlet("/MdpOublier")
public class MdpOublier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionProfils/MdpOublier.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		
		System.out.println("Pseudo : "+ pseudo);
		System.out.println("Password : "+ password);	
		System.out.println("Password : "+ passwordConfirm);	
		
		
		
		if(password.equals(passwordConfirm)) {
			try {
				Utilisateur utilDAO = DAOFactory.getUtilisateurDAO().updateMdp(pseudo, password);
				
		if(utilDAO != null) {
			rd = request.getRequestDispatcher("WEB-INF/jsp/GestionProfils/Connection.jsp");
			rd.forward(request, response);
		}
		else {
			rd = request.getRequestDispatcher("WEB-INF/jsp/Erreur.jsp");
			rd.forward(request, response);
		}
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	}

}
