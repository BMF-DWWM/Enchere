package fr.eni.Enchere.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOArt;
import fr.eni.Enchere.DAL.DAOFactory;

/**
 * Servlet implementation class ServletMisEnVenteArticle
 */
@WebServlet("/ServletMisEnVenteArticle")
public class ServletMisEnVenteArticle extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionEncheres/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DAOArt<ArticlesVendu> articleDAO = DAOFactory.getArticleDAO();
		 String nomArticle = request.getParameter("article");
		 String description = request.getParameter("description");
		 Date dateDebutEncheres= Date.valueOf(request.getParameter("debutEnchere"));
		 Date dateFinEncheres= Date.valueOf(request.getParameter("finEnchere"));
	     int prixInitial = Integer.parseInt(request.getParameter("mise"));
	     int prixVente = 0;
	     int noUtilisateur= 1;
	     int noCategorie = Integer.parseInt(request.getParameter("categories"));
	     
	     ArticlesVendu articlesVendu = new ArticlesVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres,
	    		 prixInitial, prixVente, noUtilisateur, noCategorie);
	     try {
			articleDAO.insert(articlesVendu);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionEncheres/NouvelleVente.jsp");
	     rd.forward(request, response);
	}

}
