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
public class OwnershipNotice extends AbstractBean implements Notice {
    
    private Integer registeredUserId;
    private Integer restaurantId;

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
     * @return the restaurantId
     */
    public Integer getRestaurantId() {
        return restaurantId;
    }

    /**
     * @param restaurantId the restaurantId to set
     */
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    /**
     * Overloading to cast a string to an integer
     * @param restaurantId the restaurantId to set
     */
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = Integer.parseInt(restaurantId);
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
        if (this.getRestaurantId() <= 0) {
            status = false;
            this.setError("restaurant_id","The restaurant_id is equal or less than zero");
        }
        return status;
    }
    
    
}
