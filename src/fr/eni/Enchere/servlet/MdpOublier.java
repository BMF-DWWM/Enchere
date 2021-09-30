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
		UtilisateurManager utilManager = new UtilisateurManager();
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		
		try {
			
			boolean verifmdpconfirm;
			try {
				verifmdpconfirm = utilManager.verifmdpconfirm(password, passwordConfirm);
			} catch (BLLExceptions e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean mdpChanger = utilManager.updateMdp(pseudo, password);
				
		if(verifmdpconfirm = true &&  mdpChanger == true ) {
			response.sendRedirect("Connection");
			
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


