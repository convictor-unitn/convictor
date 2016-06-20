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
public class PriceSlot extends AbstractBean{
	private int slot;
	private String name;

	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}

	/**
	 * @param slot the slot to set
	 */
	public void setSlot(int slot) {
		this.slot = slot;
	}
	
	/**
	 * @param slot the slot to set
	 */
	public void setSlot(String slot) {
		try {
			setSlot(Integer.parseInt(slot));
		} catch (NumberFormatException ex) {
			// TODO: quando si farà la validazione dei beans inserire l'errore sulla data non valida
			Logger.getLogger(OpeningTime.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
