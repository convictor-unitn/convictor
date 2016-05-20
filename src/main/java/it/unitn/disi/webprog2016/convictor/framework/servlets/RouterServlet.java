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
		controllers.put("StaticPages", new StaticPagesController());
	}
	
	private void initRoutes() {
		Route route1 = new Route("GET","/", "StaticPages", "home");
		RouteId routeId1 = new RouteId(route1.getUrl(), route1.getMethod());
		routes.put(routeId1, route1);
		Route route2 = new Route("GET","/protected", "StaticPages", "protected_page");
		RouteId routeId2 = new RouteId(route2.getUrl(), route1.getMethod());
		routes.put(routeId2, route2);
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
