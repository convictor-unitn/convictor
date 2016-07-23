/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import it.unitn.disi.webprog2016.convictor.app.beans.*;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.NoticeDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.RestaurantDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author umberto
 */
public class UserProfileController extends AbstractController {
  
	/**
	 * This action shows the user profile
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
		NoticeDAO noticeDAO = (NoticeDAO) request.getServletContext().getAttribute("noticedao");
		if( user == null ) {
			response.sendError(401);
			return "";
		}
		
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
        
		if(user instanceof Administrator) {
			try {
				List<Notice> notices = noticeDAO.getAdministratorNotices(user.getId(), noticePage);
				user.setNotices(notices);
			} catch (SQLException ex) {
				Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		if(user instanceof RestaurantOwner) {
			try {
				List<Notice> notices = noticeDAO.getRestaurantOwnerNotices(user.getId(), noticePage);
				user.setNotices(notices);
				RestaurantOwner restaurantOwner = (RestaurantOwner) user;
				restaurantOwner.setRestaurants(restaurantDAO.getRestaurantByUserId(user.getId()));
			} catch (SQLException ex) {
				Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		request.setAttribute("user", user);
        
        // Set the next pagination 
        if (user.getNotices() != null) {
            if (user.getNotices().size() > 0) {
                request.setAttribute("nextPagination", noticePage+1);
            } else if (request.getParameter("noticePage") != null) {
                // This logic block exist to ensure that the user doesn't
                // navigate to a blank page because there are no more result
                // to show.
                int pageIndex = request.getQueryString().indexOf("&noticePage=");
                String queryString = request.getQueryString().substring(0, pageIndex);
                request.setAttribute("nextPagination", noticePage-1);
                response.sendRedirect(request.getContextPath()+"/userProfile/show?"
                        + queryString +"&noticePage="
                        + String.valueOf(noticePage-1));
                return "";
            } else {
                request.setAttribute("nextPagination", noticePage);
            }
        } else {
            request.setAttribute("nextPagination", noticePage);
        }
		
		return "/userProfile/show";
	}
	
	/**
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		NoticeDAO noticeDAO = (NoticeDAO) request.getServletContext().getAttribute("noticedao");
		if( user == null ) {
			response.sendError(401);
			return "";
		}
		
		request.setAttribute("user", user);
		
		return "/userProfile/edit";
	}
	
	/**
	 * This action updates the user profile
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		User user;
		try {
			// Get parameters from request
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String email = request.getParameter("email");
			String oldPassword = request.getParameter("oldPassword");
			String password = request.getParameter("password");
			String passwordConfirmation = request.getParameter("passwordConfirmation");
			
			// Cerco l'utente nel db
			user = userDAO.getUserById(((User) request.getSession().getAttribute("user")).getId());
			// Current email
			String oldEmail = user.getEmail();
			
			if( user == null ) {
				response.sendError(401);
				return "";
			}
			
			user.setName(name);
			user.setSurname(surname);
			user.setEmail(email);
			
			if(password.equals("")) {
				user.validate();
				user.getErrors().remove("password");
				user.getErrors().remove("passwordConfirmation");
				
				if(user.isValid()) {
					
					// Check if the user has inserted an existing email
					User testEmail = userDAO.getUserByEmail(user.getEmail());
					if (testEmail != null) {
						if (!oldEmail.equals(user.getEmail())) {
							user.setError("email", "La mail inserita è già presente");
							return "/userProfile/edit";
						}
					}
					
					userDAO.updateUser(user);
					request.getSession().removeAttribute("user");
					request.getSession().setAttribute("user", user);
					response.sendRedirect(request.getContextPath()+"/userProfile/show");
					return "";
				}
				else
				{
					return "/userProfile/edit";
				}
				
			} else {
				if(!user.getPassword().equals(oldPassword)) {
					user.setError("oldPassword", "La vecchia password non coincide");
				}
				user.setPassword(password);
				user.setPasswordConfirmation(passwordConfirmation);
				
				if(user.isValid()) {
					
					// Check if the user has inserted an existing email
					User testEmail = userDAO.getUserByEmail(user.getEmail());
					if (testEmail != null) {
						if (!oldEmail.equals(user.getEmail())) {
							user.setError("email", "La mail inserita è già presente");
							return "/userProfile/edit";
						}
					}
					
					userDAO.updateUser(user);
					userDAO.updateUserPassword(user);
					request.getSession().removeAttribute("user");
					request.getSession().setAttribute("user", user);
					response.sendRedirect(request.getContextPath()+"/userProfile/show");
					return "";
				}
				else
				{
					return "/userProfile/edit";
				}
				
			}
		} catch (Exception ex) {
			Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
			response.sendError(500);
			return "";
		}
	}
}
