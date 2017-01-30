package ua.nure.kolodiazhny.SummaryTask4.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Security filter. Blocks unauthorized access to secure web pages.
 *
 * @author B.kolodiazhny
 */
@WebFilter(filterName = "CommandAccessFilter", urlPatterns = "/access", servletNames = { "Controller" }, initParams = {
		@WebInitParam(name = "admin", value = "addProduct"), @WebInitParam(name = "client", value = "displayProducts"),
		@WebInitParam(name = "common", value = "logout"), @WebInitParam(name = "noControl", value = "login") })
public class CommandAccessFilter implements Filter {

	public void destroy() {
		// No operations
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
