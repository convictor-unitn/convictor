/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.app.beans.User;
import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                               
                user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("passwordNew"));
                user.setPasswordConfirmation(request.getParameter("passwordConfirmation")); 
                
		return "/registrations/new";
	}
    
}
