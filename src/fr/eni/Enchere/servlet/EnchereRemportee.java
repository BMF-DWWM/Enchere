package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Enchere;
import fr.eni.Enchere.BO.Retrait;
import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOArt;
import fr.eni.Enchere.DAL.DAOFactory;
import fr.eni.Enchere.DAL.DAOUtilisateur;

/**
 * Servlet implementation class EnchereRemportee
 */
@WebServlet("/EnchereRemportee")
public class EnchereRemportee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOArt<ArticlesVendu> articleDAO = DAOFactory.getArticleDAO();
		DAOArt<Enchere> enchereDAO = DAOFactory.getEnchereDAO();
		DAOArt<Retrait> retraitDAO = DAOFactory.getretraitDAO();
		DAOUtilisateur utilisateurDAO = DAOFactory.getUtilisateurDAO();
		
		try {
			ArticlesVendu article = articleDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle")));
			Enchere enchereMax = enchereDAO.sqlSelectMax(Integer.parseInt(request.getParameter("noArticle")));
			Retrait retrait = retraitDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle")));
			Utilisateur utilisateurGagnantEnchere = utilisateurDAO.selectbyId(enchereMax.getNoUtilisateur());
			request.setAttribute("utilisateurGagnantEnchere", utilisateurGagnantEnchere);
			request.setAttribute("article", article);
			request.setAttribute("enchere", enchereMax);
			int montantEnchereMax = enchereMax.getMontantEnchere();
			if (montantEnchereMax!= 0 && montantEnchereMax > article.getPrixInitial()) {
				articleDAO.updateNoAcquereur(enchereMax.getNoUtilisateur(), article.getNoArticle());
				
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/GestionEncheres/EnchereRemportee.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
