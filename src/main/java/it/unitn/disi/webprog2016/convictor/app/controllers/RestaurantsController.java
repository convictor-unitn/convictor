/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.app.beans.Restaurant;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.RestaurantDAO;
import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * In this controller there add all restaurant management pages
 * @author umberto
 */
public class RestaurantsController extends AbstractController {
    
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
        String query = request.getParameter("query");
        int page=0;
        if (request.getParameter("page") != null ){
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
        try {
            HashMap<Integer, List<Restaurant>> tmp = restaurantDAO.getRestaurantByString(query, page);
            for(Integer id : tmp.keySet()) {
                request.setAttribute("paginationIndex", id);
                request.setAttribute("restaurantResult", tmp.get(id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        } 
        
        return "/restaurants/index";
	}
    
    public String show(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
        try {
            Restaurant tmp = restaurantDAO.getRestaurantById(id);
            if (tmp != null) {
                request.setAttribute("restaurant", tmp);
                return "/restaurants/show";
            } else {
                response.sendError(404);
                return "";
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }
	}
    
    public String new_(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "/restaurants/new";
	}
    
    // Mancano i campi del website e dello slot price
    public String create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
        Restaurant tmp = new Restaurant();
        
        tmp.setName(request.getParameter("name"));
        tmp.setCity(request.getParameter("city"));
        tmp.setStreet(request.getParameter("street"));
        tmp.setZipCode(request.getParameter("zipcode"));
        tmp.setProvince(request.getParameter("province"));
        tmp.setDescription(request.getParameter("description"));
        tmp.setPhone(request.getParameter("phone"));
        tmp.setEmail(request.getParameter("email"));
        tmp.setWebsite(request.getParameter("website"));
        //tmp.setSlotPrice("slotPrice");
        
        try {
            int id = ((RestaurantDAO) request.getServletContext().getAttribute("restaurantdao")).insertRestaurant(tmp);
            if (tmp.isValid()) {
                response.sendRedirect("/restaurants/show?id="+id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }
        return "/restaurants/new";
	}
    
    public String edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {        
        int id = Integer.parseInt(request.getParameter("id"));
        
        return "/restaurants/edit";
	}
    
    public String update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
        int id = Integer.parseInt(request.getParameter("id")); 
        
        Restaurant tmp = new Restaurant();
        tmp.setName(request.getParameter("name"));
        tmp.setCity(request.getParameter("city"));
        tmp.setStreet(request.getParameter("street"));
        tmp.setZipCode(request.getParameter("zipcode"));
        tmp.setProvince(request.getParameter("province"));
        tmp.setDescription(request.getParameter("description"));
        tmp.setPhone(request.getParameter("phone"));
        tmp.setEmail(request.getParameter("email"));
        tmp.setWebsite(request.getParameter("website"));
        //tmp.setSlotPrice(slotPrice);
        
        try {
            int id_rest = ((RestaurantDAO) request.getServletContext().getAttribute("restaurantdao")).updateRestaurant(tmp);
            if (tmp.isValid()) {
                response.sendRedirect("/restaurants/show?id="+id_rest);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }
        
        return "/restaurants/edit";
	}
}
