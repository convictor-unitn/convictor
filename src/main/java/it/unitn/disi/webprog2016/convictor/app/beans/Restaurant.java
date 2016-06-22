/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;
import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;

/**
 * Restaurant bean class. 
 * @author Giovanni De Toni
 */
public class Restaurant extends AbstractBean {
    
    private String name;
    private String description;
    private String street;
    private String city;
    private String zipCode;
    private String province;
    private String fullAddress;
    private String website;
    private int slotPrice;
    private int rating;
    private int mainPhotoId;
    private int restaurantOwnerId;

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
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the fullAddress
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * @param fullAddress the fullAddress to set
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the slotPrice
     */
    public int getSlotPrice() {
        return slotPrice;
    }

    /**
     * @param slotPrice the slotPrice to set
     */
    public void setSlotPrice(int slotPrice) {
        this.slotPrice = slotPrice;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param slot_price 
     */
    public void setSlot_price(String slot_price) {
        this.slotPrice = Integer.parseInt(slot_price);
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
     * Overloading to cast a string value to an integer
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = Integer.parseInt(rating);
    }


    /**
     * @return the mainPhotoId
     */
    public int getMainPhotoId() {
        return mainPhotoId;
    }

    /**
     * @param mainPhotoId the mainPhotoId to set
     */
    public void setMainPhotoId(int mainPhotoId) {
        this.mainPhotoId = mainPhotoId;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param main_photo_id the mainPhotoId to set
     */
    public void setMain_photo_id(String main_photo_id) {
        this.mainPhotoId = Integer.parseInt(main_photo_id);
    }

    /**
     * @return the restaurantOwnerId
     */
    public int getRestaurantOwnerId() {
        return restaurantOwnerId;
    }

    /**
     * @param restaurantOwnerId the restaurantOwnerId to set
     */
    public void setRestaurantOwnerId(int restaurantOwnerId) {
        this.restaurantOwnerId = restaurantOwnerId;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param restaurant_owner_id the restaurantOwnerId to set
     */
    public void setRestaurant_owner_id(String restaurant_owner_id) {
        this.restaurantOwnerId = Integer.parseInt(restaurant_owner_id);
    }

    @Override
    public boolean validate() {
        boolean status = true;
        
        
        if (this.getName().equals("")) {
            status=false;
            this.setError("name", "The name is not valid!");
        }
        
        if (this.getDescription().equals("")) {
            status = false;
            this.setError("description", "The description is not valid!");
        }
        
        if (this.getStreet().equals("")) {
            status = false;
            this.setError("street", "The street is not valid!");
        }
        
        if (this.getCity().equals("")) {
            status = false;
            this.setError("city", "The city is not valid!");
        }
        
        if (this.getZipCode().equals("")) {
            status = false;
            this.setError("zip", "The zip is not valid!");
        }
        
        if (this.getProvince().equals("")) {
            status = false;
            this.setError("province", "The province is not valid!");
        } 
        
        if (this.getSlotPrice() == 0) {
            status = false;
            this.setError("slot_price", "The slot price is not valid!");
        }
        
        return status;
    }
    
    
    
}
