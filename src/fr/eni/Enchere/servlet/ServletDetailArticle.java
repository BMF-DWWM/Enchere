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
import fr.eni.Enchere.BO.Retrait;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOArt;
import fr.eni.Enchere.DAL.DAOFactory;

/**
 * Servlet implementation class ServletDetailArticle
 */
@WebServlet("/ServletDetailArticle")
public class ServletDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOArt<ArticlesVendu> articleDAO = DAOFactory.getArticleDAO();
		DAOArt<Retrait> retraitDAO = DAOFactory.getretraitDAO();
		try {
						request.setAttribute("article", articleDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle"))));
						request.setAttribute("retrait", retraitDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle"))));
			
						
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/GestionEncheres/DetailsVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOArt<ArticlesVendu> articleDAO = DAOFactory.getArticleDAO();
		ArticlesVendu article = null;
		System.out.println((request.getParameter("noArticle")));
		try {
			article = articleDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(article);
	}

}
