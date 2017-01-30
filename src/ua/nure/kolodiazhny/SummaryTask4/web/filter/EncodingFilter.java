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

import org.apache.log4j.Logger;

/**
 * Encoding filter that checks character encoding of a request and sets it on
 * UTF-8 if needed.
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*", servletNames = "controller", initParams = @WebInitParam(name = "encoding", value = "UTF-8") )
public class EncodingFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

	private String encoding;

	public void destroy() {
		LOG.debug("EncodingFilter - Destroy.");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOG.debug("EncodingFilter - doFilter started.");

		String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null) {
			LOG.trace("Request encoding = null, set encoding --> " + encoding);
			request.setCharacterEncoding(encoding);
		}

		LOG.debug("EncodingFilter - doFilter finished.");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
		LOG.debug("EncodingFilter - Init.");
	}

}
