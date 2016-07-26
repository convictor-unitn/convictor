/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import it.unitn.disi.webprog2016.convictor.app.beans.OwnershipNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.PhotoRemovalNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.User;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.NoticeDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.PhotoDAO;
import it.unitn.disi.webprog2016.convictor.app.beans.Restaurant;
import it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.RestaurantDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.UserDAO;
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
	
	private static final String AMAZON_ACCESS_KEY = "AKIAINLCB7W3V5KLNOHQ";
    private static final String AMAZON_SECRET_KEY = "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";
    private static final String S3_BUCKET_NAME = "convictor";
	
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
	public String reportPhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		NoticeDAO noticeDAO = (NoticeDAO) request.getServletContext().getAttribute("noticedao");
		User user = (User) request.getSession(false).getAttribute("user");
		
		// Check and set the photo id
		int id=0;
		try {
			id = Integer.parseInt(request.getParameter("photoId"));
		} catch (Exception ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
			response.sendError(500);
			return "";
		}
		
		// Insert the notice
		PhotoRemovalNotice tmp = new PhotoRemovalNotice();			
		tmp.setPhotoId(id);
		tmp.setRegisteredUserId(user.getId());
		try {
			noticeDAO.insertPhotoRemovalNotice(tmp);
		} catch (Exception ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
			response.sendError(500);
			return "";
		} 
		
		// Set the object
		request.setAttribute("notice", tmp);
		return "/notices/reportPhoto";
	}
	
	public String approveReportPhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		NoticeDAO noticeDAO = (NoticeDAO) request.getServletContext().getAttribute("noticedao");
		PhotoDAO photoDAO = (PhotoDAO) request.getServletContext().getAttribute("photodao");
		User user = (User) request.getSession(false).getAttribute("user");
		
		// Check and set the photo id
		int id=0;
		int noticeId=0;
		try {
			id = Integer.parseInt(request.getParameter("photoId"));
			noticeId = Integer.parseInt(request.getParameter("noticeId"));
		} catch (Exception ex) {
			response.sendError(500);
			return "";
		}
		
		// Check what the administrator decided
		boolean status = false;
		if (request.getParameter("approve") != null) {
			status = true;
		}
		
		try {
			noticeDAO.approvePhotoRemovalNotice(status, noticeId);
			
			if (status) {
				
				/*BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AMAZON_ACCESS_KEY, AMAZON_SECRET_KEY);
                AmazonS3 s3client = new AmazonS3Client(awsCredentials);
				DeleteObjectRequest deleteRequest = new DeleteObjectRequest(S3_BUCKET_NAME, S3_BUCKET_NAME);
				*/
				//noticeDAO.deletePhotoNotice(noticeId);
				photoDAO.deletePhotoById(id);
			}
			
			
			
		} catch (Exception e) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, e);
			response.sendError(500);
			return "";
		}
		
		request.setAttribute("status", status);
		
		return "/notices/approveReportPhoto";
	}
	
	public String showClaimRestaurantOwnership(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO noticeDAO = (NoticeDAO) request.getServletContext().getAttribute("noticedao");
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
		OwnershipNotice notice;
		int noticeId;
		
		try {
			noticeId = Integer.parseInt(request.getParameter("noticeId"));
		}
		catch(NumberFormatException ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
			response.sendError(500);
			return "";
		}
		
		try {
			notice = noticeDAO.getOwnershipNoticeById(noticeId);
			notice.setRegisteredUser(userDAO.getUserById(notice.getRegisteredUserId()));
			notice.setRestaurant(restaurantDAO.getRestaurantById(notice.getRestaurantId()));
			request.setAttribute("ownershipNotice", notice);
		} catch (SQLException ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return "/notices/showClaimRestaurantOwnership";
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
		NoticeDAO noticeDAO = (NoticeDAO) request.getServletContext().getAttribute("noticedao");
		RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		
		int noticeId;
		boolean noticeApproved;
		OwnershipNotice notice;
		Restaurant restaurant;
		RestaurantOwner restaurantOwner;
		
		try {
			noticeId = Integer.parseInt(request.getParameter("noticeId"));
			noticeApproved = Boolean.valueOf(request.getParameter("noticeApproved"));
		}
		catch(NumberFormatException ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
			response.sendError(500);
			return "";
		}
		
		try {
			notice = noticeDAO.getOwnershipNoticeById(noticeId);
			restaurant = restaurantDAO.getRestaurantById(notice.getRestaurantId());
			
			if(restaurant.getRestaurantOwnerId() > 0 && noticeApproved != false) {
				notice.setError("restaurant", "Questo ristorante è già stato assegnato");
			}
			
			// Check if the user is already a restaurant owner
			boolean alreadyOwner = false;
			if(userDAO.getRestaurantOwnerById(notice.getRegisteredUserId()) != null) {
				alreadyOwner = true;
			}
			
			if(notice.isValid()) {
				noticeDAO.approveOwershipNotice(noticeApproved, noticeId);
				if (noticeApproved) {
					if (!alreadyOwner) {
						userDAO.promoteUserToRestaurantOwner(userDAO.getUserById(notice.getRegisteredUserId()));
					}
					restaurantOwner = userDAO.getRestaurantOwnerById(notice.getRegisteredUserId());
					restaurantDAO.assignRestaurant(restaurant, restaurantOwner);
				}
				notice = noticeDAO.getOwnershipNoticeById(noticeId);
			}
			request.setAttribute("ownershipNotice", notice);
		} catch (SQLException ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			Logger.getLogger(NoticesController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return "/notices/approveClaimRestaurantOwnership";
	}
}
