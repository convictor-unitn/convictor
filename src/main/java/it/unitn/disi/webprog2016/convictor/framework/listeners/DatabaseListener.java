/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.listeners;

import it.unitn.disi.webprog2016.convictor.app.beans.Cusine;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.CusineDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.CusinesRestaurantDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.NoticeDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.OpeningTimeDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.PhotoDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.PriceSlotDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.RestaurantDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.ReviewDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.UserDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.CusinesRestaurantDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
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
		String dbUrl;
		String dbUsername;
		String dbPassword;
		
		if(System.getenv("DATABASE_URL") == null) {
			dbUrl = sce.getServletContext().getInitParameter("dburl");
			dbUsername = sce.getServletContext().getInitParameter("dbUsername");
			dbPassword = sce.getServletContext().getInitParameter("dbPassword");
		} else {
			URI dbUri;
			try {
				dbUri = new URI(System.getenv("DATABASE_URL"));
				dbUsername = dbUri.getUserInfo().split(":")[0];
				dbPassword = dbUri.getUserInfo().split(":")[1];
				dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath(); 
			} catch (URISyntaxException ex) {
				Logger.getLogger(DatabaseListener.class.getName()).log(Level.SEVERE, null, ex);
				throw new RuntimeException(ex);
			}	
		}
		
		try {
			DatabaseConnectionManager manager = new DatabaseConnectionManager(dbUrl, dbUsername, dbPassword);
			sce.getServletContext().setAttribute("dbmanager", manager);
            sce.getServletContext().setAttribute("userdao", new UserDAOImpl(manager) );
            sce.getServletContext().setAttribute("reviewdao", new ReviewDAOImpl(manager));
            sce.getServletContext().setAttribute("restaurantdao", new RestaurantDAOImpl(manager));
            sce.getServletContext().setAttribute("noticedao", new NoticeDAOImpl(manager));
            sce.getServletContext().setAttribute("cusinesrestaurantdao", new CusinesRestaurantDAOImpl(manager));
            sce.getServletContext().setAttribute("openingtimesdao", new OpeningTimeDAOImpl(manager));
			sce.getServletContext().setAttribute("cusinedao", new CusineDAOImpl(manager));
			sce.getServletContext().setAttribute("priceslotdao", new PriceSlotDAOImpl(manager));
			sce.getServletContext().setAttribute("photodao", new PhotoDAOImpl(manager));
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
