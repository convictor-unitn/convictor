/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * This filter excludes the static files which must be accessed without passing the 
 * router servlet.
 * @author umberto
 */
public class StaticFilesFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI().substring(req.getContextPath().length());

		if (path.startsWith("/css") || path.startsWith("/js") || path.startsWith("/images")) {
			chain.doFilter(request, response);
		} else {
			if(path.startsWith("/restaurants/qrcode")) {
				chain.doFilter(request, response);
			} else {
				request.getRequestDispatcher("/pages" + path).forward(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}
	
}
