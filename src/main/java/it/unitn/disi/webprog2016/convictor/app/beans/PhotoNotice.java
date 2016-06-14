/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;
import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;

/**
 * Photo Notice bean. 
 * @author Giovanni De Toni
 */
public class PhotoNotice extends AbstractBean {
    
    private Integer registeredUserId;
    private Integer photo;
    private Boolean remove;

    /**
     * @return the registeredUserId
     */
    public Integer getRegisteredUserId() {
        return registeredUserId;
    }

    /**
     * @param registeredUserId the registeredUserId to set
     */
    public void setRegisteredUserId(Integer registeredUserId) {
        this.registeredUserId = registeredUserId;
    }
    
    /**
     * Overloading to cast a string to an integer
     * @param registeredUserId the registeredUserId to set
     */
    public void setRegisteredUserId(String registeredUserId) {
        this.registeredUserId = Integer.parseInt(registeredUserId);
    }

    /**
     * @return the photo
     */
    public Integer getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(Integer photo) {
        this.photo = photo;
    }

    /**
     * CHECK IF JSTL WORK WITH isRemove METHOD
     * @return the remove
     */
    public boolean isRemove() {
        return remove;
    }

    /**
     * @param remove the remove to set
     */
    public void setRemove(Boolean remove) {
        this.remove = remove;
    }
    /**
     * Overloading to cast a string to an integer
     * @param remove the remove to set
     */
    public void setRemove(String remove) {
        this.remove = Boolean.valueOf(remove);
    }
    
}
