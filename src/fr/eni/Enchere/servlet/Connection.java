package fr.eni.Enchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.BLL.UtilisateurManager;
import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.DAOFactory;



/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/GestionProfils/Connection.jsp");
		
		
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("pseudo")) {
                    request.setAttribute("pseudo", cookie.getValue());
                }
            }
        }
		session.invalidate();
		
		rd.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		HttpSession session = request.getSession();
		UtilisateurManager utilManager = new UtilisateurManager();
		
		
		String pseudo = request.getParameter("pseudo");
		String password = request.getParameter("password");
		String sesouvenirdemoi = request.getParameter("sesouvenirdemoi");
		
		if (sesouvenirdemoi != null) {
			// Creation du cookie
			Cookie cookie = new Cookie("pseudo", pseudo);

			// definition de la limite de validite
			cookie.setMaxAge(60*60*24*30);

			// envoi du cookie dans la reponse HTTP
			response.addCookie(cookie);
		} 

		System.out.println("Pseudo : "+ pseudo);
		System.out.println("Password : "+ password);

		Utilisateur utilisateur = utilManager.verifConnection(pseudo, password);
		
		if(utilisateur != null) {
			session.setAttribute("utilisateur", utilisateur);
			response.sendRedirect("/Enchere/ServletListeEncheres");
			//rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionEncheres/ListeEncheres.jsp");
			//rd.forward(request, response);
		}
		else {
			rd = request.getRequestDispatcher("/WEB-INF/jsp/GestionProfils/Connection.jsp");
			rd.forward(request, response);
		}
	
	}

}
		
////côté backend
//request.setAttribute("error", "Mon erreur")
//
////côté vue (jsp)
//<% if(request.getAttribute("error") != null) {%>
// <div class="error">Une erreur a été rencontrée: <%=request.getAttribute("error")%></div>
//<%}%>


