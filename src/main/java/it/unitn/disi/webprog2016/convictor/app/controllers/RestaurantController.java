/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * In this controller there add all restaurant management pages
 * @author umberto
 */
public class RestaurantController extends AbstractController {
    
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "/restaurants/index";
	}
    
    public String show(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "/restaurants/show";
	}
    
    public String new_(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "/restaurants/new";
	}
    
    public String create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "/restaurants/new";
	}
    
    public String edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "/restaurants/edit";
	}
    
    public String update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return "/restaurants/edit";
	}
}
