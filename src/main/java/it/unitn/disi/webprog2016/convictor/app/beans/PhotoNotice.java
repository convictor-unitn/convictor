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
       try {
            this.registeredUserId = Integer.parseInt(registeredUserId);
        } catch (Exception e) {
            this.setRegisteredUserId(-1);
            this.setError("user_id", "L'id utente inserito non è valido");
        }
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
        try {
            this.setPhotoId(Integer.parseInt(photoId));
        } catch (Exception e) {
            this.setError("photo_id", "L'id della foto non è valido");
            this.setPhotoId(-1);
        }
    }
    
    /**
     * Return the viewable message representing this notice.
     * @return A string message
     */
	@Override
	public String getDescription() {
		String userName = registeredUser.getName() + " " + registeredUser.getSurname();
        String photoMessage ="<a href=\"/restaurant/show?id="+ photo.getRestaurantId() + "\">foto</a>";
        String message = userName + " ha inserito una nuova " + photoMessage + "!";
        return message;
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
            this.setError("user_id","L'id utente è minore o uguale a zero");
        }
        if (this.getPhotoId() <= 0) {
            status = false;
            this.setError("photo_id","L'id della foto è minore o uguale a zero");
        }
        return status;
    }
    
	public User getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(User registeredUser) {
		this.registeredUser = registeredUser;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
}
