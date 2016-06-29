/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.app.beans.User;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.UserDAO;
import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author umberto
 */
public class SessionsController extends AbstractController{
    
    public String new_(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{	
		return "/sessions/new";
	}
    
    public String create(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user;
		try {
			user = userDAO.authenticate(email, password);
			
			if(user.isValid()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				response.sendRedirect(request.getContextPath());
				return "";
			}
			
			request.setAttribute("user", user);
		} catch (SQLException ex) {
			Logger.getLogger(SessionsController.class.getName()).log(Level.SEVERE, null, ex);
			response.sendError(500);
			return "";
		}
		return "/sessions/new";
	}
	
	public String destroy(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
		response.sendRedirect(request.getContextPath());
		return "";
	}
}
