package fr.eni.Enchere.Filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.BO.Utilisateur;


@WebFilter(urlPatterns ={"/modifieProfil","/ServletMisEnVenteArticle"},
		   dispatcherTypes = {
					DispatcherType.REQUEST,
					DispatcherType.INCLUDE,
					DispatcherType.FORWARD,
					DispatcherType.ERROR,
					}
			)
public class FiltreConnecter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		RequestDispatcher rd;

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		System.out.println("je passe dans le filtre");

		if(utilisateur != null) {
			System.out.println(utilisateur.getNoUtilisateur());
			System.out.println("filtre réussi");
			chain.doFilter(request, response);
		}
		else {
			rd = request.getRequestDispatcher("/Connection");
			System.out.println("Non connecter");
			rd.forward(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
