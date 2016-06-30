/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;

import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umberto
 */
public class Photo extends AbstractBean{

	private String url;
	private String description;
	private int restaurantId;
	
	
	@Override
	public boolean validate() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the restaurantId
	 */
	public int getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(String restaurantId) {
		try {
			this.setRestaurantId(Integer.parseInt(restaurantId));
		}
		catch (NumberFormatException e) {
			this.setError("restaurant_id", "L'id del ristorante non è valido.");
		}
	}
}
