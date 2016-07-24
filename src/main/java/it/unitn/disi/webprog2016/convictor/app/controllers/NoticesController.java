/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.app.beans.OwnershipNotice;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.NoticeDAO;
import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author umberto
 */
public class NoticesController extends AbstractController {
	
	public String setApproval(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class noticeClass = Class.forName("it.unitn.disi.webprog2016.convictor.app.beans.", true, getClass().getClassLoader());
		
			switch(noticeClass.getName()) {
				case "PhotoRemovalNotice":
					
					break;
				case "OwnershipNotice":
					
					break;
				default:
					response.sendError(500);
					return "";
			}
			
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "/landingPage";
	}
	
	public String claimRestaurantOwnership(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		int restaurantId;
		int registeredUserId;
		String companyName;
		String vatNumber;
		String taxCode;
		String contactPhone;
		
		NoticeDAO noticeDAO = (NoticeDAO) request.getServletContext().getAttribute("noticedao");
		
		try {
			restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
			registeredUserId = Integer.parseInt(request.getParameter("registeredUserId"));
			companyName = request.getParameter("companyName");
			vatNumber = request.getParameter("vatNumber");
			taxCode = request.getParameter("taxCode");
			contactPhone = request.getParameter("contactPhone");
		}
		catch(NumberFormatException ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
			response.sendError(500);
			return "";
		}
		
		OwnershipNotice notice = new OwnershipNotice();
		notice.setRegisteredUserId(registeredUserId);
		notice.setRestaurantId(restaurantId);
		notice.setCompanyName(companyName);
		notice.setVatNumber(vatNumber);
		notice.setTaxCode(taxCode);
		notice.setContactPhone(contactPhone);
		
		try {
			notice.validate();
			if(notice.isValid()) {
				noticeDAO.insertOwnershipNotice(notice);
			}
			request.setAttribute("ownershipNotice", notice);
			return "/notices/claimRestaurantOwnership";
		} catch (SQLException ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
			response.sendError(500);
			return "";
		}
	}
	
	/**
	 * Only admin accessible
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException 
	 */
	public String approveClaimRestaurantOwnership(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		return "/notices/approveClaimRestaurantOwnership";
	}
}
