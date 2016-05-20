/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class implements the doAction method with reflection
 * It must be implemented in a real controller with specific action to be called
 * When you inherits from this class all action methods must have this signature
 * * <code>
 *     protected String actionName(HttpServletRequest request, HttpServletResponse response, String format) throws IOException, ServletException {
 * </code>
 * @author umberto
 */
public abstract class AbstractController implements Controller {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response, String action, String format) {		
		try {
			Class paramsTypes[] = new Class[]{HttpServletRequest.class, HttpServletResponse.class};
			Object params[] = new Object[] { request, response };
			Method m = this.getClass().getDeclaredMethod(action, paramsTypes);
			String view = (String) m.invoke(this, params);
			request.getRequestDispatcher("/WEB-INF/"+view+"."+format.toLowerCase()+".jsp").forward(request, response);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
			try {
				response.sendError(500);
			} catch (IOException e) {
				Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, null, e);
			}
			Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ServletException | IOException ex) {
			Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
