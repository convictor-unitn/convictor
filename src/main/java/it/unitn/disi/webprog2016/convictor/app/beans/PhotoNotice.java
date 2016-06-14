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
    
    private int registeredUserId;
    private int photoId;
    private Boolean remove;

    /**
     * @return the registeredUserId
     */
    public int getRegisteredUserId() {
        return registeredUserId;
    }

    /**
     * @param registeredUserId the registeredUserId to set
     */
    public void setRegisteredUserId(int registeredUserId) {
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
     * @return the photoId
     */
    public int getPhotoId() {
        return photoId;
    }

    /**
     * @param photoId the photoId to set
     */
    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
    
    /**
     * Overloading to cast a string to an integer
     * @param photoId the photoId to set
     */
    public void setPhotoId(String photoId) {
        this.photoId = Integer.parseInt(photoId);
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
     * Overloading to cast a string to a boolean 
     * @param remove the remove to set
     */
    public void setRemove(String remove) {
        this.remove = Boolean.valueOf(remove);
    }
    
}
