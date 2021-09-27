package fr.eni.Enchere.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Enchere;
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
		DAOArt<Enchere> enchereDAO = DAOFactory.getEnchereDAO();
		HttpSession session = request.getSession();
		ArticlesVendu article = null;
		Enchere rechercheEnchere = null;
		try {
						request.setAttribute("article", articleDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle"))));
						request.setAttribute("retrait", retraitDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle"))));
						article = articleDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle")));
						session.setAttribute("articleNoUtilisateur", article.getNoUtilisateur());
						session.setAttribute("articleNoArticle", article.getNoArticle());
						int articleNoUtilisateur = (int) session.getAttribute("articleNoUtilisateur");
						int articleNoArticle = (int) session.getAttribute("articleNoArticle");
						rechercheEnchere = enchereDAO.sqlSelectMax((int) session.getAttribute("articleNoArticle"));
						request.setAttribute("rechercheEnchere", rechercheEnchere);
						System.out.println(rechercheEnchere);
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
		HttpSession session = request.getSession();
		DAOArt<Enchere> enchereDAO = DAOFactory.getEnchereDAO();
		Enchere rechercheEnchere = null;
		try {
			rechercheEnchere = enchereDAO.sqlSelectMax((int) session.getAttribute("articleNoArticle"));
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (rechercheEnchere == null) {
			
		}
		int articleNoUtilisateur = (int) session.getAttribute("articleNoUtilisateur");
		int articleNoArticle = (int) session.getAttribute("articleNoArticle");
		int montant = Integer.parseInt(request.getParameter("montantEnchere"));
		Date dateMilliSec = new Date( System.currentTimeMillis());
		Enchere enchere = new Enchere(articleNoUtilisateur, dateMilliSec, articleNoArticle, montant);
		
		try {
			rechercheEnchere = enchereDAO.selectbyIdUserAndIdArticle(articleNoUtilisateur, articleNoArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rechercheEnchere == null) {
			try {
				enchereDAO.insert(enchere);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
		}
		
	
		
	}

}
