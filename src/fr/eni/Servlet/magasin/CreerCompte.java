package fr.eni.Servlet.magasin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.magasin.bo.Utilisateur;
import fr.eni.magasin.dal.MagasinDAOjdbcimpl;


@WebServlet("/CreerCompte")
public class CreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/CreerCompte.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String CreerPseudo = request.getParameter("pseudo");		
		String CreerPassword = request.getParameter("password");
		
		MagasinDAOjdbcimpl test = new MagasinDAOjdbcimpl();
		
		Utilisateur Resultat = test.VerifPseudo(CreerPseudo);
		
		if (Resultat == null){

			test.CreationCompte(CreerPseudo, CreerPassword);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/Connecter.jsp");
			rd.forward(request, response);
		}
		
		else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/CreerCompte.jsp");
			rd.forward(request, response);
		}
		
	}

}
