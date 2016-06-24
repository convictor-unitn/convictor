/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import it.unitn.disi.webprog2016.convictor.app.beans.*;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.NoticeDAO;
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
		NoticeDAO noticeDAO = (NoticeDAO) request.getServletContext().getAttribute("noticedao");
		if( user == null ) {
			response.sendError(401);
			return "";
		}
		
		if(user instanceof Administrator) {
			try {
				List<Notice> notices = noticeDAO.getAdministratorNotices(user.getId());
				user.setNotices(notices);
			} catch (SQLException ex) {
				Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		if(user instanceof RestaurantOwner) {
			try {
				List<Notice> notices = noticeDAO.getRestaurantOwnerNotices(user.getId());
				user.setNotices(notices);
			} catch (SQLException ex) {
				Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		request.setAttribute("user", user);
		
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
		return "/landingPage";
	}
    
}
