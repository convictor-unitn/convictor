/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.listeners;

import it.unitn.disi.webprog2016.convictor.framework.mailer.Mailer;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author umberto
 */
public class MailerLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String from = "Convictor <convictor@peserico.me>";
		String host = "mail.umbytux.com";
		final String username = "convictor@peserico.me";
		final String password = "ConvictorMail26072016";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.checkserveridentity", "false");
		properties.put("mail.smtp.ssl.trust", "*");
		
		Mailer mailer = new Mailer(from, properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
		});
		sce.getServletContext().setAttribute("mailer", mailer);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
