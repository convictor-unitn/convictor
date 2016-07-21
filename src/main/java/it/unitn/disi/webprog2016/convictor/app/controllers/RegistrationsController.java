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
public class RegistrationsController extends AbstractController{
    
    public String new_(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		return "/registrations/new";	
	}
    
    public String create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();      
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");

		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("passwordNew"));
		user.setPasswordConfirmation(request.getParameter("passwordConfirmation")); 
		user.setPrivacy(request.getParameter("privacy"));
		
		if(!user.getPrivacy().equals("checked")) {
			user.setError("privacy", "Devi accettare le condizioni generali di utilizzo del servizio");
		}
		
		user.validate();
		if(user.isValid()) {
			try {
				user.setAdmin(false);
				userDAO.insertUser(user);
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				response.sendRedirect(request.getServletContext().getContextPath()+"/");
				return "";
			} catch (SQLException ex) {
				Logger.getLogger(RegistrationsController.class.getName()).log(Level.SEVERE, null, ex);
				response.sendError(500);
				return "";
			}
		} else {
			request.setAttribute("user", user);
			return "/registrations/new";
		}
	}
    
}
