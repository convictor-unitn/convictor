/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;
import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;

/**
 * Ownership Notice bean.
 * @author Giovanni De Toni
 */
public class OwnershipNotice extends AbstractBean implements Notice, Approvable {
    
    private int registeredUserId;
    private int restaurantId;
	private boolean approved;
	private User registeredUser;

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
     * Overloading to cast a string to an integer
     * @param restaurantId the restaurantId to set
     */
    public void setRestaurantId(String restaurantId) {
        try {
            this.setRestaurantId(Integer.parseInt(restaurantId));
        } catch (Exception e) {
            this.setError("restaurant_id", "L'id del ristorante non è valido");
            this.setRestaurantId(-1);
        }
    }
    
    public User getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(User registeredUser) {
		this.registeredUser = registeredUser;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
    /**
     * Return the viewable message representing this notice.
     * @return A string message
     */
	@Override
	public String getDescription() {
		String userName = registeredUser.getName() + " " + registeredUser.getSurname();
        String userProfile = "<a href=\"/userProfile/show?id="+ registeredUserId + "\">"+userName+"</a>";
        String restaurantProfile ="<a href=\"/restaurants/show?id="+restaurantId + "\">"+restaurantId+"</a>";
        String message = userProfile + " ha richiesto l'assegnazione del ristorante " + restaurantProfile;
        return message;
    }

	@Override
	public String getNoticeType() {
		return this.getClass().toString();
	}

	@Override
	public void approve() {
		this.setApproved(true);
	}

	@Override
	public void decline() {
		this.setApproved(false);
	}

	@Override
	public boolean getApproved() {
		return this.isApproved();
	}
    
    @Override
    public boolean validate() {
        boolean status=true;
        if (this.getRegisteredUserId() <= 0) {
            status = false;
            this.setError("user_id","L'id utente è minore o uguale a zero");
        }
        if (this.getRestaurantId() <= 0) {
            status = false;
            this.setError("restaurant_id","L'id del ristorante è minore o uguale a zero");
        }
        return status;
    }

	
    
}
