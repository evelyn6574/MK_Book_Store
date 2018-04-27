package filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import model.*;

/**
 * Servlet Filter implementation class Statistics
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/Analytics" }, servletNames = { "Analytics" })
public class Statistics implements Filter {
	
	private POData podata = new POData();

    /**
     * Default constructor. 
     */
    public Statistics() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		try {
			Map<POBean, Map<String, Integer>> map = (Map<POBean, Map<String, Integer>>) req.getAttribute("anonymizedpo");
			if (map != null) {
				for (POBean pobean : map.keySet()) {
					//String lastthree = pobean.getUsername().substring(pobean.getUsername().length() - 3);
					pobean.setUsername("******");
					pobean.setLname("Lname");
					pobean.setFname("Fname");
					pobean.setAddress(null);
				}
				req.getSession().setAttribute("anonymizedpo", map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
