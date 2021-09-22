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
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns ="",
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
		HttpSession session = (HttpSession) request.getAttribute("idSession");
		System.out.println("je passe dans le filtre");
		
		if(session != null) {
			chain.doFilter(request, response);
		}
		else {
			rd = request.getRequestDispatcher("/Connection");
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
