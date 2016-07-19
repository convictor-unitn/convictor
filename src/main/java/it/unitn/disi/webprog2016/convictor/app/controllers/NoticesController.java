/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import java.io.IOException;
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
	
}
