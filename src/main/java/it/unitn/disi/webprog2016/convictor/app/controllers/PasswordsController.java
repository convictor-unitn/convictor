/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.app.beans.User;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.UserDAO;
import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import it.unitn.disi.webprog2016.convictor.framework.mailer.EmailMessage;
import it.unitn.disi.webprog2016.convictor.framework.mailer.Mailer;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

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
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		Mailer mailer = (Mailer) request.getServletContext().getAttribute("mailer");
		String email = request.getParameter("email");
		String status = "";
		
		try {
			User user = userDAO.getResetPasswordToken(email);
			if(user==null) {
				status = "failure";
			} else {
				status = "success";
				String uri, scheme, port;
				if(System.getenv("DATABASE_URL") != null) {
					scheme = "https";
					port = "";
				} else {
					scheme = "http";
					port = ":" + request.getServerPort();
				}
				uri = scheme + "://" +   // "http" + "://
				request.getServerName() +       // "myhost"
				port +
				request.getContextPath() +       // "/people"
				"/passwords/new?" +                           // "?"
				"reset_password_token="+user.getResetPasswordToken();
				
				EmailMessage mail = mailer.createEmailMessage(new ArrayList<String>() {{this.add(user.getEmail());}});
				mail.setSubject("Recupero password");
				mail.setText("Per recuperare la password clicca su questo link: "+uri);
				mailer.sendEmailMessage(mail);
			}
		} catch (SQLException ex) {
			Logger.getLogger(PasswordsController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(PasswordsController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		request.setAttribute("status", status);
		return "/passwords/getResetToken";
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
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("user", user);
		request.setAttribute("resetPasswordToken", request.getParameter("reset_password_token"));
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
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		
		try {
			User user = userDAO.getUserByResetToken(request.getParameter("reset_password_token"));
			request.setAttribute("resetPasswordToken", request.getParameter("reset_password_token"));
			
			if(user==null) {
				request.setAttribute("resetPasswordToken", "expired");				
				return "/passwords/requestNewPassword";
			}
			
			DateTime nowPlus30Min = DateTime.now();
			DateTime resetPasswordSentAt = new DateTime(user.getResetPasswordSentAt());
			boolean validDate = nowPlus30Min.isBefore(user.getResetPasswordSentAt().plusMinutes(15));
			//boolean validDate = nowPlus30Min.toInstant().getMillis() <= (user.getResetPasswordSentAt().getTime()+1800000);
			
			System.err.println(nowPlus30Min.toString() + "   "+ resetPasswordSentAt.toString());
			
			if(user.getResetPasswordToken().equals(request.getParameter("reset_password_token")) && validDate) {
				user.setPassword(request.getParameter("password"));
				user.setPasswordConfirmation(request.getParameter("passwordConfirmation"));
				user.validate();
				if(user.isValid()) {
					user.setResetPasswordToken("");
					userDAO.updateUser(user);
					HttpSession session = request.getSession(true);
					session.setAttribute("user", user);
					response.sendRedirect(request.getContextPath()+"/");
					return "";
				} else {
					String errors = ""; boolean status = true;
					for (String e : user.getErrors().keySet()) {
						if (status == true) {errors += "&";}
						errors += e+"=on";
						status = true;
					}
					
					response.sendRedirect(request.getContextPath()+"/passwords/new?reset_password_token="+user.getResetPasswordToken()+errors);
					return ""; 
				}
			} else {
				request.setAttribute("resetPasswordToken", "expired");				
				return "/passwords/requestNewPassword"; 
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(PasswordsController.class.getName()).log(Level.SEVERE, null, ex);
			response.sendError(404);
			return "";
		}
	}
}
