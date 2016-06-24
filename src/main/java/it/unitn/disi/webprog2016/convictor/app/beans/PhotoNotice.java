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
public class PhotoNotice extends AbstractBean implements Notice {
    
    private int registeredUserId;
    private int photoId;
	private User registeredUser;
	private Photo photo;

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

	@Override
	public String getDescription() {
		return "DA IMPLEMENTARE";
	}

	@Override
	public String getNoticeType() {
		return this.getClass().toString();
	}
    
    @Override
    public boolean validate() {
        boolean status=true;
        if (this.getRegisteredUserId() <= 0) {
            status = false;
            this.setError("user_id","The user_id is equal or less than zero");
        }
        if (this.getPhotoId() <= 0) {
            status = false;
            this.setError("photo_id","The photo_id is equal or less than zero");
        }
        return status;
    }
    
}
