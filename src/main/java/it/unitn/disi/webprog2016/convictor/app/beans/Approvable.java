/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;

/**
 *
 * @author umberto
 */
public interface Approvable {
	
	int getId();
	void approve();
	void decline();
	boolean getApproved();
	
}
