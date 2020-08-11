package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Filter implements javax.servlet.Filter {
	FilterConfig filterConfig = null;

	@Override
	public void init (FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		if (filterConfig.getInitParameter("access").equalsIgnoreCase("true")) {
			HttpServletRequest req = (HttpServletRequest) request;
			String servletPath = req.getServletPath();
			System.out.println(servletPath);
			if (servletPath.equalsIgnoreCase("/registration") || servletPath.equalsIgnoreCase("/authorization")) {

				System.out.println(servletPath + " first if");

				filterChain.doFilter(request, response);

			}
			else if (session.getAttribute("ac")!=null){

				filterChain.doFilter(request,response);
			} else {
				System.out.println(servletPath + " second if");

				response.sendRedirect("/authorization");
			}

		}

	}

	@Override
	public void destroy () {

	}
}
