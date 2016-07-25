/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.app.beans.OpeningTime;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.OpeningTimesDAO;
import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This controller is used for Static Pages or pages that doesn't fit
 * into other controller.
 * All actions must have this signature:
 * <code>
 *     public String home(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
 * </code>
 * @author Umberto Peserico
 */
public class StaticPagesController extends AbstractController {
	    
	/**
	 * The landing page action
	 * @param request
	 * @param response
	 * @return view the path of the view to call
	 * @throws ServletException
	 * @throws IOException 
	 */
	public String landingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OpeningTimesDAO openingDAO = (OpeningTimesDAO) request.getServletContext().getAttribute("openingtimesdao");
		
		for (int i = 0; i < 50; i++) {
			int[] days = new int[7];
			for (int j=0; j<7; j++)  {days[j] = 0;}
			try {
				List<OpeningTime> tmp = openingDAO.getResaurantOpeningTimes(i+1);
				for ( OpeningTime t : tmp) {
					days[t.getDay()-1] = 1;
				}
				
				for (int j=0; j<7; j++) {
					if (days[j] == 0) {
						OpeningTime o = new OpeningTime();
						o.setDay(j+1);
						o.setDayoff(true);
						o.setOpenAt(new Date());
						o.setCloseAt(new Date());
						o.setOpenAtAfternoon(new Date());
						o.setCloseAtAfternoon(new Date());
						tmp.add(o);
					}
				}
				openingDAO.updateRestaurantOpeningTimes(i, tmp);
				
			} catch (Exception ex) {
			
			}
		}
		return "/landingPage";
	}

}
