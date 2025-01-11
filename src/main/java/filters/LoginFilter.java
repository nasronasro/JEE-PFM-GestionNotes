package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
    public LoginFilter() {
        super();
    }
	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();
        
        if(httpRequest.getSession().getAttribute("username") != null) {
        	httpRequest.setAttribute("login", false);
        }else {
        	httpRequest.setAttribute("login", true);
        }
        
        if (requestURI.endsWith("/login") || requestURI.endsWith("/inscription")) {
            chain.doFilter(request, response);  
            return;
        }
        if (httpRequest.getSession().getAttribute("username") == null) {
            httpResponse.sendRedirect("login");
        } else {
            chain.doFilter(request, response);
        } 
	}
	public void init(FilterConfig fConfig) throws ServletException {

	}
}