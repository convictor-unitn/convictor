/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.listeners;

import it.unitn.disi.webprog2016.convictor.app.dao.implementation.NoticeDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.RestaurantDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.ReviewDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.UserDAOImpl;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author umberto
 */
public class DatabaseListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String dbUrl = sce.getServletContext().getInitParameter("dburl");
		String dbUsername = sce.getServletContext().getInitParameter("dbUsername");
		String dbPassword = sce.getServletContext().getInitParameter("dbPassword");
		try {
			DatabaseConnectionManager manager = new DatabaseConnectionManager(dbUrl, dbUsername, dbPassword);
			sce.getServletContext().setAttribute("dbmanager", manager);
            sce.getServletContext().setAttribute("userdao", new UserDAOImpl(manager) );
            sce.getServletContext().setAttribute("reviewdao", new ReviewDAOImpl(manager));
            sce.getServletContext().setAttribute("restaurantdao", new RestaurantDAOImpl(manager));
            sce.getServletContext().setAttribute("noticedao", new NoticeDAOImpl(manager));
		}
		catch(SQLException e) {
			Logger.getLogger(getClass().getName()).severe(e.toString());
            throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DatabaseConnectionManager manager = (DatabaseConnectionManager) sce.getServletContext().getAttribute("dbmanager");
		if(manager!=null) {
			manager.shutdown();
		}
	}
}
