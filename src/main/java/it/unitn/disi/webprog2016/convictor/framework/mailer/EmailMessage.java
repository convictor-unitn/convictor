/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.mailer;

import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author umberto
 */
public class EmailMessage {
	
	private String from;
	private List<String> to;
	private Message mimeMessage;

	public EmailMessage(String from, Session session) throws MessagingException {
		this.from = from;
		this.to = null;
		this.mimeMessage = new MimeMessage(session);
		this.mimeMessage.setFrom(new InternetAddress(from));
	}

	void setTo(List<String> to) throws MessagingException {
		this.to = to;
		InternetAddress[] addresses = new InternetAddress[to.size()];
		for(int i=0; i<to.size(); i++) {
			addresses[i] = new InternetAddress(to.get(i));
		}
		this.mimeMessage.setRecipients(Message.RecipientType.TO, addresses);
	}

	public void setText(String text) throws MessagingException {
		this.mimeMessage.setText(text);
	}

	public void setHtml(String html) throws MessagingException {
		this.mimeMessage.setContent(html, "text/html");
	}

	public void setSubject(String subject) throws MessagingException {
		this.mimeMessage.setSubject(subject);
	}

	Message getMessage() {
		return this.mimeMessage;
	}
}
