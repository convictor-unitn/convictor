/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.servlets;

import it.unitn.disi.webprog2016.convictor.app.controllers.RestaurantsController;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author umberto
 */
public class QRcodeServlet extends HttpServlet {

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
		int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
			String uri, scheme, port;
			if(System.getenv("DATABASE_URL") != null) {
				scheme = "https";
				port = "";
			} else {
				scheme = "http";
				port = ":" + request.getServerPort();
			}
			uri = scheme + "://" + 
			request.getServerName() + 
			port +
			request.getContextPath() + "/restaurants/show?id="+id;

			System.err.println(uri);
			QRCode qrcode = QRCode.from(uri);
			System.err.println(qrcode);
			ByteArrayOutputStream out = qrcode.to(ImageType.JPG).stream();

			try (OutputStream outStream = response.getOutputStream()) {
				outStream.write(out.toByteArray());
				outStream.flush();
			}

			response.setContentType("image/png");
			response.setContentLength(out.size());
        } catch (NumberFormatException | IOException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
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
