/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unitn.disi.webprog2016.convictor.framework.controllers.Controller;
import it.unitn.disi.webprog2016.convictor.app.controllers.StaticPagesController;
import it.unitn.disi.webprog2016.convictor.framework.utils.Route;
import it.unitn.disi.webprog2016.convictor.framework.utils.RouteId;
import it.unitn.disi.webprog2016.convictor.framework.utils.RouteXMLParser;
import it.unitn.disi.webprog2016.convictor.framework.utils.ControllerXMLParser;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * This servlet processes the url the user is surfing. It must be initialized
 * with two functions initControllers, initRoutes.
 * This servlet matches the current request url to a route initialized.
 * If the route exists check whether the controller declared exists
 * If true calls the <code>doAction</code> method
 * 
 * @author umberto
 */
public class RouterServlet extends HttpServlet {

        private final String route_config_file = "";
        private final String controller_config_file = "";
	private Map<RouteId, Route> routes;
	private Map<String, Controller> controllers;
	
	@Override
	public void init() throws ServletException {
		routes = new HashMap<>();
		controllers = new HashMap<>();
                		
		initRoutes();
		initControllers();
	}
	
	private void initControllers() {
            try {
                ControllerXMLParser result = new ControllerXMLParser(
                                            controller_config_file,
                                            "/controllers/controller");
                ArrayList<ArrayList> list = result.getControllers();
                for (ArrayList elem : list) {
                    
                }
            } catch (Exception e) {
            }
            controllers.put("StaticPages", new StaticPagesController());
	}
	
	private void initRoutes() {
            try {
                RouteXMLParser result = new RouteXMLParser(route_config_file,
                                            "/routes/route");
                ArrayList<ArrayList> list = result.getRoutes();
                for (ArrayList elem : list) {
                   Route route = new Route( (String) elem.get(0), 
                                            (String) elem.get(1), 
                                            (String) elem.get(2), 
                                            (String) elem.get(3));
                   RouteId routeId = new RouteId(route.getUrl(), 
                                                route.getMethod());
                   routes.put(routeId, route);
                }
            } catch ( Exception e ) {
                System.err.println(e.getMessage());
            }   
	}
	
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		RouteId routeId = new RouteId(request.getPathInfo(), request.getMethod());
		Route route = routes.get(routeId);
		
		Controller controller;
		
		if(route!=null){
			controller = controllers.get(route.getControllerName());
			if(controller!=null) {
				controller.doAction(request, response, route.getActionName(), route.getFormat());
			}
			else
			{
				response.sendError(404);
			}
		}
		else
		{
			response.sendError(404);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
