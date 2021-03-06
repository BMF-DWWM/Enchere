package fr.eni.Enchere.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.BLL.ArticlesVendusManager;
import fr.eni.Enchere.BLL.EnchereManager;
import fr.eni.Enchere.BLL.RetraitManager;
import fr.eni.Enchere.BLL.UtilisateurManager;
import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Retrait;
import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOArt;
import fr.eni.Enchere.DAL.DAOFactory;
import fr.eni.Enchere.DAL.DAOUtilisateur;

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
		HttpSession session = request.getSession();
		ArticlesVendusManager articleMngr = new ArticlesVendusManager();
		RetraitManager retraitMngr = new RetraitManager();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		 String nomArticle = request.getParameter("article");
		 String description = request.getParameter("description");
		 Date dateDebutEncheres= Date.valueOf(request.getParameter("debutEnchere"));
		 Date dateFinEncheres= Date.valueOf(request.getParameter("finEnchere"));
	     int prixInitial = Integer.parseInt(request.getParameter("mise"));
	     int prixVente = 0;
	     int noUtilisateur= utilisateur.getNoUtilisateur();
	     int noCategorie = Integer.parseInt(request.getParameter("categories"));
	     
	     ArticlesVendu articlesVendu = new ArticlesVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres,
	    		 prixInitial, prixVente, noUtilisateur, noCategorie);
	     
	     try {
	    	 System.out.println("1");
	    	 articleMngr.insert(articlesVendu);
	    	 System.out.println("2");
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     int noArticle = articlesVendu.getNoArticle();
	     String rue = utilisateur.getRue();
	     String codePostal = utilisateur.getCodePostal();
	     String ville = utilisateur.getVille();
	     Retrait retrait = new Retrait(noArticle, rue, codePostal, ville);
	     
	     
	     try {
	    	 System.out.println("3");
	    	 retraitMngr.insert(retrait);
	    	 System.out.println("4");
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     	response.sendRedirect("/Enchere/ServletMisEnVenteArticle");
			//RequestDispatcher rd = request.getRequestDispatcher("/ServletMisEnVenteArticle");
			//rd.forward(request, response);
	}
}
