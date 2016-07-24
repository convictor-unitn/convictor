/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This represents an abstract bean with common properties
 * @author umberto
 */
public abstract class AbstractBean implements Serializable {
	
	private int id;
	private Date createdAt;
	private Date updatedAt;
	private final Map<String, String> errors;
	
	public AbstractBean() {
		this.errors = new HashMap<>();
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
	public void setError(String field, String error) {
		errors.put(field, error);
	}
	
	public boolean isValid(){
		return errors.isEmpty();
	}

    public void setId(int id) {
        this.id = id;
    }
    
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    
	public int getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
    
    public abstract boolean validate();
	
}
