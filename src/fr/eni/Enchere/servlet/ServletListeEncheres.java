package fr.eni.Enchere.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ServletListeEncheres
 */
@WebServlet("/ServletListeEncheres")
public class ServletListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOArt<ArticlesVendu> articleDAO = DAOFactory.getArticleDAO();
		try {
			request.setAttribute("listeArticle",articleDAO.selectAll());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionEncheres/ListeEncheres.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOArt<ArticlesVendu> articleDAO = DAOFactory.getArticleDAO();
		String categorie;
			String mot = request.getParameter("s");
			if (request.getParameter("categories").equals("")) {
				categorie = "";
			}
			else {
				categorie = " and no_categorie =" +request.getParameter("categories");
			}
//			try {
//				articleDAO.selectAllByMotCle(mot, categorie);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			try {
				request.setAttribute("listeArticle", articleDAO.selectAllByMotCle(mot, categorie));
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionEncheres/ListeEncheres.jsp");
				rd.forward(request, response);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
