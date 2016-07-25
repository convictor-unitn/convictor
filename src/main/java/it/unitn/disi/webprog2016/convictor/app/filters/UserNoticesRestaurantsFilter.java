/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.filters;

import it.unitn.disi.webprog2016.convictor.app.beans.Administrator;
import it.unitn.disi.webprog2016.convictor.app.beans.Notice;
import it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner;
import it.unitn.disi.webprog2016.convictor.app.beans.User;
import it.unitn.disi.webprog2016.convictor.app.controllers.UserProfileController;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.NoticeDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.RestaurantDAO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Giovanni De Toni
 */
public class UserNoticesRestaurantsFilter implements Filter {
	
	private static final boolean debug = true;

	// The filter configuration object we are associated with.  If
	// this value is null, this filter instance is not currently
	// configured. 
	private FilterConfig filterConfig = null;
	
	public UserNoticesRestaurantsFilter() {
	}	
	
	private void doBeforeProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		if (debug) {
			log("UserNoticesRestaurantsFilter:DoBeforeProcessing");
		}
	}	
	
	private void doAfterProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		if (debug) {
			log("UserNoticesRestaurantsFilter:DoAfterProcessing");
		}
	}

	/**
	 *
	 * @param request The servlet request we are processing
	 * @param response The servlet response we are creating
	 * @param chain The filter chain we are processing
	 *
	 * @exception IOException if an input/output error occurs
	 * @exception ServletException if a servlet error occurs
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		if (debug) {
			log("UserNoticesRestaurantsFilter:doFilter()");
		}
		RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
		NoticeDAO noticeDAO = (NoticeDAO) request.getServletContext().getAttribute("noticedao");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		 // Set up notice pagination
        int noticePage =0;
        if (request.getParameter("noticePage") != null) {
            try {
                noticePage = Integer.valueOf(request.getParameter("noticePage"));
                if (noticePage < 0) {noticePage = 0;}
            } catch (Exception e) {
                noticePage = 0;
            }
        }
		
		User user = (User) req.getSession(true).getAttribute("user");
		if (user != null) {
			if(user instanceof Administrator) {
				try {
					List<Notice> notices = noticeDAO.getAdministratorNotices(user.getId(), noticePage);
					user.setNotices(notices);
				} catch (Exception ex) {
					Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

			if(user instanceof RestaurantOwner) {
				try {
					List<Notice> notices = noticeDAO.getRestaurantOwnerNotices(user.getId(), noticePage);
					user.setNotices(notices);
					RestaurantOwner restaurantOwner = (RestaurantOwner) user;
					restaurantOwner.setRestaurants(restaurantDAO.getRestaurantByUserId(user.getId()));
				} catch (Exception ex) {
					Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		
		request.setAttribute("user", user);
		
		doBeforeProcessing(request, response);
		
		Throwable problem = null;
		try {
			chain.doFilter(request, response);
		} catch (Throwable t) {
			// If an exception is thrown somewhere down the filter chain,
			// we still want to execute our after processing, and then
			// rethrow the problem after that.
			problem = t;
			t.printStackTrace();
		}
		
		doAfterProcessing(request, response);

		// If there was a problem, we want to rethrow it if it is
		// a known type, otherwise log it.
		if (problem != null) {
			if (problem instanceof ServletException) {
				throw (ServletException) problem;
			}
			if (problem instanceof IOException) {
				throw (IOException) problem;
			}
			sendProcessingError(problem, response);
		}
	}

	/**
	 * Return the filter configuration object for this filter.
	 */
	public FilterConfig getFilterConfig() {
		return (this.filterConfig);
	}

	/**
	 * Set the filter configuration object for this filter.
	 *
	 * @param filterConfig The filter configuration object
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * Destroy method for this filter
	 */
	public void destroy() {		
	}

	/**
	 * Init method for this filter
	 */
	public void init(FilterConfig filterConfig) {		
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			if (debug) {				
				log("UserNoticesRestaurantsFilter:Initializing filter");
			}
		}
	}

	/**
	 * Return a String representation of this object.
	 */
	@Override
	public String toString() {
		if (filterConfig == null) {
			return ("UserNoticesRestaurantsFilter()");
		}
		StringBuffer sb = new StringBuffer("UserNoticesRestaurantsFilter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}
	
	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);		
		
		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				PrintStream ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps);				
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

				// PENDING! Localize this for next official release
				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");				
				pw.print(stackTrace);				
				pw.print("</pre></body>\n</html>"); //NOI18N
				pw.close();
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		} else {
			try {
				PrintStream ps = new PrintStream(response.getOutputStream());
				t.printStackTrace(ps);
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		}
	}
	
	public static String getStackTrace(Throwable t) {
		String stackTrace = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			sw.close();
			stackTrace = sw.getBuffer().toString();
		} catch (Exception ex) {
		}
		return stackTrace;
	}
	
	public void log(String msg) {
		filterConfig.getServletContext().log(msg);		
	}
	
}
