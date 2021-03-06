package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import fr.eni.Enchere.BLL.ArticlesVendusManager;
import fr.eni.Enchere.BLL.UtilisateurManager;
import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.DAL.DALException;
import fr.eni.Enchere.DAL.DAOArt;
import fr.eni.Enchere.DAL.DAOFactory;

/**
 * Servlet implementation class AnnuleVente
 */
@WebServlet("/AnnuleVente")
public class AnnuleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		ArticlesVendusManager articleMngr = new ArticlesVendusManager();
		try {
			articleMngr.delete(noArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/Enchere/ServletListeEncheres");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
