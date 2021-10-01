package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.BLL.ArticlesVendusManager;
import fr.eni.Enchere.BLL.EnchereManager;
import fr.eni.Enchere.BLL.RetraitManager;
import fr.eni.Enchere.BLL.UtilisateurManager;
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
		ArticlesVendusManager articleMngr = new ArticlesVendusManager();
		EnchereManager enchereMngr = new EnchereManager();
		RetraitManager retraitMngr = new RetraitManager();
		UtilisateurManager utilisateurMngr = new UtilisateurManager();
		
		try {
		
				ArticlesVendu article = articleMngr.selectbyId(Integer.parseInt(request.getParameter("noArticle")));
				Enchere enchereMax = enchereMngr.sqlSelectMax(Integer.parseInt(request.getParameter("noArticle")));
				Retrait retrait = retraitMngr.selectbyId(Integer.parseInt(request.getParameter("noArticle")));
				Utilisateur utilisateurGagnantEnchere = null;
				if (enchereMngr.sqlSelectMax(Integer.parseInt(request.getParameter("noArticle"))) != null) {
					 utilisateurGagnantEnchere = utilisateurMngr.selectbyId(enchereMax.getNoUtilisateur());
					request.setAttribute("utilisateurGagnantEnchere", utilisateurGagnantEnchere);
				}
				
				request.setAttribute("article", article);
				request.setAttribute("enchere", enchereMax);
				int montantEnchereMax = 0;
				if (enchereMax != null) {
					montantEnchereMax = enchereMax.getMontantEnchere();	
				}
				
				if (montantEnchereMax!= 0 && montantEnchereMax > article.getPrixInitial()) {
					articleMngr.updateNoAcquereur(enchereMax.getNoUtilisateur(), article.getNoArticle());
			
			request.setAttribute("userWin", utilisateurGagnantEnchere );
			
				
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
