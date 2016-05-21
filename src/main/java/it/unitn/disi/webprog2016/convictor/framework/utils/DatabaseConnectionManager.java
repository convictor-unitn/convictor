/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author umberto
 */
public class DatabaseConnectionManager implements Serializable{
	private final transient Connection connection;
	private final String dbUrl;
	
	public DatabaseConnectionManager(String dbUrl, String username, String password) throws SQLException {
		this.dbUrl = dbUrl;
		Properties props = new Properties();
 		props.setProperty("user", username);
		props.setProperty("password", password);
		
		try {
			Class.forName("org.postgresql.Driver", true, getClass().getClassLoader());
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());
			
		}
		
		this.connection = DriverManager.getConnection(this.dbUrl, props);
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void shutdown() {
		try {
			DriverManager.getConnection(dbUrl+";shutdown=true");
		}
		catch(Exception e) {
			Logger.getLogger(DatabaseConnectionManager.class.getName()).info(e.getMessage());
		}
	}
}
