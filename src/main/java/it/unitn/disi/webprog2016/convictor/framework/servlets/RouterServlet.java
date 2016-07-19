		/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unitn.disi.webprog2016.convictor.framework.controllers.Controller;
import it.unitn.disi.webprog2016.convictor.framework.utils.ControllerNotFoundException;
import it.unitn.disi.webprog2016.convictor.framework.utils.Route;
import it.unitn.disi.webprog2016.convictor.framework.utils.RouteId;
import it.unitn.disi.webprog2016.convictor.framework.utils.RouteXMLParser;
import it.unitn.disi.webprog2016.convictor.framework.utils.ControllerXMLParser;
import it.unitn.disi.webprog2016.convictor.framework.utils.ControllersHashMap;
import it.unitn.disi.webprog2016.convictor.framework.utils.RouteNotFoundException;
import it.unitn.disi.webprog2016.convictor.framework.utils.RoutesHashMap;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        private final String route_config_file = "WEB-INF/routes.xml";
        private final String controller_config_file = "WEB-INF/controllers.xml";
	private RoutesHashMap<RouteId, Route> routes;
	private ControllersHashMap<String, Controller> controllers;
        
	@Override
	public void init() throws ServletException {
		routes = new RoutesHashMap<>();
		controllers = new ControllersHashMap<>();
                		
		initRoutes();
		initControllers();
	}
	
	private void initControllers() {
            try {
                ControllerXMLParser result = new ControllerXMLParser(
                            this.getServletContext().getRealPath("/")+
                                            controller_config_file,
                                            "/controllers/controller");
                ArrayList<ArrayList> list = result.getControllers();
                for (ArrayList elem : list) {
                    controllers.put(
                        (String) elem.get(0), 
                        (Controller) Class.forName(
                            "it.unitn.disi.webprog2016.convictor.app.controllers."
                            + (String) elem.get(0)
                            + "Controller").newInstance());
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
	}
	
	private void initRoutes() {
            try {
                RouteXMLParser result = new RouteXMLParser(
                                            getServletContext().getRealPath("/")+
                                            route_config_file,
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
                throw new RuntimeException(e.getMessage());
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
        
        String pathInfo = request.getPathInfo();
        if(pathInfo.endsWith("/") && !pathInfo.equals("/")) {
            pathInfo= pathInfo.substring(0, pathInfo.length() - 1);
        }
        System.err.println(pathInfo);
		RouteId routeId = new RouteId(pathInfo, request.getMethod());
		Route route = null;
                try {
                    route = routes.getRoute(routeId);
                    Controller controller = null;
                    try {
                        controller = controllers.getController(route.getControllerName());
                        controller.doAction(request, response, route.getActionName(), route.getFormat());
                    } catch (ControllerNotFoundException e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
                        response.sendError(404, "Controller Not Found");
                    }
                    
                } catch (RouteNotFoundException e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
                    response.sendError(404, "Route Not Found");
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
