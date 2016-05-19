/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.peserico.javaweb.templateapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface defines the behaviour of a controller
 * @author umberto
 */
public interface Controller {
	
	/**
	 * This method performs an action passed by parameter
	 * @param request the httpRequest
	 * @param response the httpResponse
	 * @param action the action to perform
	 * @param format the format of the action
	 */
	public void doAction(HttpServletRequest request, HttpServletResponse response, String action, String format);
	
}
