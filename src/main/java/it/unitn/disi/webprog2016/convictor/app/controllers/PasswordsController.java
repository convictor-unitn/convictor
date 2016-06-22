/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author umberto
 */
public class PasswordsController extends AbstractController  {
    
    /**
     * This actions shows the form with email of the user who wants to reset the password
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String requestNewPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/passwords/requestNewPassword";
	}
    
    /**
     * This actions creates the reset token and redirects to new password page
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getResetToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/");
		return "";
	}
    
    /**
     * This action shows the form for password reset
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String new_(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/passwords/new";
	}
    
    /**
     * This action sets the new password
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/landingPage";
	}
}
