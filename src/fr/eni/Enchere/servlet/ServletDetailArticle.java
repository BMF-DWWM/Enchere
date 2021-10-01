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
 * Servlet implementation class ServletDetailArticle
 */
@WebServlet("/ServletDetailArticle")
public class ServletDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticlesVendusManager articleMngr = new ArticlesVendusManager();
		EnchereManager enchereMngr = new EnchereManager();
		RetraitManager retraitMngr = new RetraitManager();
		HttpSession session = request.getSession();
		ArticlesVendu article = null;
		Enchere rechercheEnchere = null;
		Date dateMilliSec = new Date( System.currentTimeMillis());
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		try {	if ( request.getParameter("noArticle")==(null)) {
				article = (ArticlesVendu) session.getAttribute("articlesession");
				
				request.setAttribute("article", articleMngr.selectbyId(article.getNoArticle()));
				request.setAttribute("retrait", retraitMngr.selectbyId(article.getNoArticle()));
					
			}else { 
				request.setAttribute("article", articleMngr.selectbyId(Integer.parseInt(request.getParameter("noArticle"))));
				request.setAttribute("retrait", retraitMngr.selectbyId(Integer.parseInt(request.getParameter("noArticle"))));
				article = articleMngr.selectbyId(Integer.parseInt(request.getParameter("noArticle")));
			}
						request.setAttribute("getdate", dateMilliSec);
						request.setAttribute("utilisateur", utilisateur);
						session.setAttribute("articlesession", article);
						rechercheEnchere = enchereMngr.sqlSelectMax(article.getNoArticle());
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
		UtilisateurManager utilisateurMngr = new UtilisateurManager();
		EnchereManager enchereMngr = new EnchereManager();
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
			//Recherche l'enchere la plus haute de l'article
			rechercheEnchere = enchereMngr.sqlSelectMax(articleNoArticle);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (Integer.class.isInstance(montant) && montant > articlePrixInitial) {
			if (rechercheEnchere == null) {
				//Si l'article n'a aucune enchere existante et que l'enchere proposé est polus grande que le prix initial alors
				try {
					System.out.println("1");
					enchereMngr.UpdateCreditInsertEnchere(utilisateur.getCredit(), montant, noUtilisateur);
					enchereMngr.insert(enchere);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					// Si une enchere existe, verifitcation si c'est l'enchere connecté qui la faite
					System.out.println("2");
					rechercheEnchere2 = enchereMngr.selectbyIdUserAndIdArticle(noUtilisateur, articleNoArticle);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//verifie si le montant de l'enchere est supérieur à la plus haute enchere sur l'article
				if (enchere.getMontantEnchere() > rechercheEnchere.getMontantEnchere()) {
					// si il a deja fait une enchere et que il est celui qui a fait l'enchere la plus ahute sur l'article
					if (rechercheEnchere2 != null ) {
						if ( rechercheEnchere.getMontantEnchere() == rechercheEnchere2.getMontantEnchere()) {
							try {
								Utilisateur user = utilisateurMngr.selectbyId(noUtilisateur);
								enchereMngr.UpdateCreditUpdateEnchere(user.getCredit(), montant, 
										rechercheEnchere.getMontantEnchere(), noUtilisateur);
								enchereMngr.update(enchere);
								System.out.println("3");
							} catch (DALException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							//si il a deja encherie et qu'il n'est pas celui qui a la plus haute enchere
							try {
								Utilisateur userEnchereMax = utilisateurMngr.selectbyId(rechercheEnchere.getNoUtilisateur());
								enchereMngr.UpdateCreditRollBackEnchere(userEnchereMax.getCredit(),rechercheEnchere.getMontantEnchere(),
										rechercheEnchere.getNoUtilisateur());
								enchereMngr.UpdateCreditInsertEnchere(utilisateur.getCredit(), montant, noUtilisateur);
								enchereMngr.update(enchere);
							} catch (DALException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						//si une enchere existe deja mais que l'utilisateur connecté n'a jamais enchérie
					}else {
						try {
							System.out.println("4");
							enchereMngr.UpdateCreditInsertEnchere(utilisateur.getCredit(), montant, noUtilisateur);
							Utilisateur userEnchereMax = utilisateurMngr.selectbyId(rechercheEnchere.getNoUtilisateur());
							enchereMngr.UpdateCreditRollBackEnchere(userEnchereMax.getCredit(),rechercheEnchere.getMontantEnchere(),
								rechercheEnchere.getNoUtilisateur());
							enchereMngr.insert(enchere);
						} catch (DALException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				
				}
			
		}
	}
		response.sendRedirect("/Enchere/ServletDetailArticle");

		
		
	
		
	}

}
