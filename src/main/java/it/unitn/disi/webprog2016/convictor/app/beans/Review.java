/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;
import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;
import java.util.Date;

/**
 * Review bean class. 
 * @author Giovanni De Toni 
 */
public class Review extends AbstractBean {
    
    private int registeredUserId;
    private String registeredUserName;
    private int restaurantId;
    private String description;
    private int rating;
	private Date createdAt;

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
            this.setError("user_id", "L'id utente non è valido");
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
            this.restaurantId = Integer.parseInt(restaurantId);
        } catch (Exception e) {
            this.setRegisteredUserId(-1);
            this.setError("restaurant_id", "L'id del ristorante non è valido");
        }
        
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
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    /**
     * Overloading to cast a string to an integer
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        try {
           this.rating = Integer.parseInt(rating); 
        } catch (Exception e) {
            this.setRating(-1);
            this.setError("rating", "Il rating non è un valore valido");
        }
    }
    
    
    public String getRegisteredUserName() {
        return registeredUserName;
    }

    public void setRegisteredUserName(String registeredUserName) {
        this.registeredUserName = registeredUserName;
    }	

    @Override
    public boolean validate() {
        boolean status = true;
        if (this.getDescription().equals("")) {
            status = false;
            this.setError("description", "La descrizione non è valida");
        }
        if (this.getRating() < 1 || this.getRating() > 5) {
            status = false;
            this.setError("rating", "Il rating inserito non è compreso tra i valori corretti");
        }
        if (this.getRegisteredUserId() <= 0) {
            status = false;
            this.setError("user_id", "L'id utente è minore o uguale a zero");
        }
        if (this.getRestaurantId() <= 0) {
            status = false;
            this.setError("restaurant_id", "Il restaurant_id è minore o uguale a zero");
        }
        return status;
    }
    
}
