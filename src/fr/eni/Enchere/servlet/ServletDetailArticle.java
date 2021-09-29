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
import fr.eni.Enchere.BO.Utilisateur;
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
		Date dateMilliSec = new Date( System.currentTimeMillis());
		try {	if ( request.getParameter("noArticle")==(null)) {
				article = (ArticlesVendu) session.getAttribute("articlesession");
				
				request.setAttribute("article", articleDAO.selectbyId(article.getNoArticle()));
				request.setAttribute("retrait", retraitDAO.selectbyId(article.getNoArticle()));
					
			}else { 
				request.setAttribute("article", articleDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle"))));
				request.setAttribute("retrait", retraitDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle"))));
				article = articleDAO.selectbyId(Integer.parseInt(request.getParameter("noArticle")));
			}
						
						
						session.setAttribute("articlesession", article);
						rechercheEnchere = enchereDAO.sqlSelectMax(article.getNoArticle());
						request.setAttribute("rechercheEnchere", rechercheEnchere);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date dateFinEncheres = (Date) article.getDateFinEncheres();
		if (dateMilliSec.compareTo(dateFinEncheres) > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("/EnchereRemportee");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/GestionEncheres/DetailsVente.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DAOArt<Enchere> enchereDAO = DAOFactory.getEnchereDAO();
		Enchere rechercheEnchere = null;
		Enchere rechercheEnchere2 = null;
		ArticlesVendu article = (ArticlesVendu) session.getAttribute("articlesession");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		int noUtilisateur = utilisateur.getNoUtilisateur();
		int articleNoUtilisateur = article.getNoUtilisateur();
		int articleNoArticle = article.getNoArticle();
		int articlePrixInitial = article.getPrixInitial();
		int montant = Integer.parseInt(request.getParameter("montantEnchere"));
		Date dateMilliSec = new Date( System.currentTimeMillis());
		Enchere enchere = new Enchere(noUtilisateur, dateMilliSec, articleNoArticle, montant);
		try {
			rechercheEnchere = enchereDAO.sqlSelectMax(articleNoArticle);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (Integer.class.isInstance(montant) && montant > articlePrixInitial) {
			if (rechercheEnchere == null) {
				try {
					System.out.println("1");
					enchereDAO.insert(enchere);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					System.out.println("2");
					rechercheEnchere2 = enchereDAO.selectbyIdUserAndIdArticle(noUtilisateur, articleNoArticle);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (enchere.getMontantEnchere() > rechercheEnchere.getMontantEnchere()) {
					if (rechercheEnchere2 != null) {
						try {
							enchereDAO.update(enchere);
							
							System.out.println("3");
						} catch (DALException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						try {
							enchereDAO.insert(enchere);
						} catch (DALException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
//						try {
//							System.out.println("4");
//							enchereDAO.delete(articleNoArticle, rechercheEnchere.getNoUtilisateur());
//							if (rechercheEnchere2 == null) {
//								enchereDAO.insert(enchere);
//							}else {
//								enchereDAO.update(enchere);
//							}
//							
//						} catch (DALException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
				
				}
			
		}
	}
		response.sendRedirect("/Enchere/ServletDetailArticle");
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/GestionEncheres/DetailsVente.jsp");
//		rd.forward(request, response);
		
		
		
	
		
	}

}
