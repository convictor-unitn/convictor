/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.peserico.javaweb.templateapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This controller is used for Static Pages.
 * All actions must have this signature:
 * <code>
 *     protectedString home(HttpServletRequest request, HttpServletResponse response, String format) throws IOException, ServletException {
 * </code>
 * @author umberto
 */
public class StaticPagesController extends AbstractController {
	
	/**
	 * The home action
	 * @param request the httpRequest
	 * @param response the httpResponse
	 * @param format the format
	 * @return view the path of the view to call
	 * @throws IOException
	 * @throws ServletException
	 */
	protected String home(HttpServletRequest request, HttpServletResponse response, String format) throws IOException, ServletException {
	
		return "/pages/index";
	}
	
	/**
	 * The protected_page action
	 * @param request the httpRequest
	 * @param response the httpResponse
	 * @param format the format
	 * @return view the path of the view to call
	 * @throws IOException
	 * @throws ServletException
	 */
	protected String protected_page(HttpServletRequest request, HttpServletResponse response, String format) throws ServletException, IOException {
		
		return "/pages/protected";
	}
	
}
