/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.mailer;

import java.util.*;
import javax.mail.*;

/**
 *
 * @author umberto
 */
public class Mailer {
	
	private final String from;
	private final Session session;
	private final Properties properties;
	private final Authenticator authenticator;
	
	public Mailer(String from, Properties properties, Authenticator authenticator) {
		this.from = from;
		this.properties = properties;
		this.authenticator = authenticator;
		this.session = Session.getInstance(properties, authenticator);
	}
	
	public void sendEmailMessage(EmailMessage msg) throws MessagingException {
		Transport.send(msg.getMessage());
	}
	
	public EmailMessage createEmailMessage(List<String> to) throws MessagingException {
		EmailMessage msg = new EmailMessage(from, session);
		msg.setTo(to);
		return msg;
	}
	
}
