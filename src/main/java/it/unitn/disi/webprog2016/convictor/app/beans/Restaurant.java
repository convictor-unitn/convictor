/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;
import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;

/**
 *
 * @author Giovanni De Toni
 */
public class Restaurant extends AbstractBean {
    
    private String name;
    private String description;
    private String street;
    private String city;
    private String zip_code;
    private String province;
    private String full_address;
    private String address;
    private Integer slot_price;
    private Integer rating;
    private Integer main_photo_id;
    private Integer restaurant_owner_id;

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
     * @return the zip_code
     */
    public String getZip_code() {
        return zip_code;
    }

    /**
     * @param zip_code the zip_code to set
     */
    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
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
     * @return the full_address
     */
    public String getFull_address() {
        return full_address;
    }

    /**
     * @param full_address the full_address to set
     */
    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the slot_price
     */
    public Integer getSlot_price() {
        return slot_price;
    }

    /**
     * @param slot_price the slot_price to set
     */
    public void setSlot_price(Integer slot_price) {
        this.slot_price = slot_price;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param slot_price 
     */
    public void setSlot_price(String slot_price) {
        this.slot_price = Integer.parseInt(slot_price);
    } 

    /**
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Integer rating) {
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
     * @return the main_photo_id
     */
    public Integer getMain_photo_id() {
        return main_photo_id;
    }

    /**
     * @param main_photo_id the main_photo_id to set
     */
    public void setMain_photo_id(Integer main_photo_id) {
        this.main_photo_id = main_photo_id;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param main_photo_id the main_photo_id to set
     */
    public void setMain_photo_id(String main_photo_id) {
        this.main_photo_id = Integer.parseInt(main_photo_id);
    }

    /**
     * @return the restaurant_owner_id
     */
    public Integer getRestaurant_owner_id() {
        return restaurant_owner_id;
    }

    /**
     * @param restaurant_owner_id the restaurant_owner_id to set
     */
    public void setRestaurant_owner_id(Integer restaurant_owner_id) {
        this.restaurant_owner_id = restaurant_owner_id;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param restaurant_owner_id the restaurant_owner_id to set
     */
    public void setRestaurant_owner_id(String restaurant_owner_id) {
        this.restaurant_owner_id = Integer.parseInt(restaurant_owner_id);
    }
    
    
    
}
