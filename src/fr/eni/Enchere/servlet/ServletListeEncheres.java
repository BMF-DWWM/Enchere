package fr.eni.Enchere.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Utilisateur;
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
		HttpSession session = request.getSession();
		DAOArt<ArticlesVendu> articleDAO = DAOFactory.getArticleDAO();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		try {
			request.setAttribute("listeArticle",articleDAO.selectAll());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("getdate", new Date( System.currentTimeMillis())); 
		request.setAttribute("usersession", utilisateur);
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionEncheres/ListeEncheres.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOArt<ArticlesVendu> articleDAO = DAOFactory.getArticleDAO();
		HttpSession session = request.getSession();
		String categorie;
		String option ="";
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		
			String mot = request.getParameter("s");
			if (request.getParameter("categories").equals("")) {
				categorie = "";
			}
			else {
				categorie = " and no_categorie =" +request.getParameter("categories");
			}
			if (request.getParameter("achatVente")=="2"){
				
			}
			else {
				if (request.getParameter("achatVente") != null) {
					int noUtilisateur = utilisateur.getNoUtilisateur();
					switch (request.getParameter("achatVente")) {
					case "1": option = "and GETDATE()  between date_debut_encheres and date_fin_encheres";break;
					case "3": option = "and av.no_acquereur = "+noUtilisateur+" ";break;
					
					case "4": option = "and av.no_utilisateur = "+noUtilisateur+"  and GETDATE() between date_debut_encheres and date_fin_encheres";break;
					case "5": option = "and av.no_utilisateur = "+noUtilisateur+" and GETDATE() < date_debut_encheres ";break;
					case "6": option = "and av.no_utilisateur = "+noUtilisateur+" and GETDATE() > date_fin_encheres ";break;
					
					default: option ="";
						break;
					}
				}
			}
		
			
			
			try {
				request.setAttribute("listeArticle", articleDAO.selectAllByMotCle(mot, categorie, option));
			
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("getdate", new Date( System.currentTimeMillis())); 
			request.setAttribute("usersession", utilisateur);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionEncheres/ListeEncheres.jsp");
			rd.forward(request, response);
	}

}
