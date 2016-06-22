/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.Restaurant;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.RestaurantDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Restaurants DAO implementation. 
 * @author Giovanni De Toni
 */
public class RestaurantDAOImpl extends DatabaseDAO implements RestaurantDAO {

    public RestaurantDAOImpl(DatabaseConnectionManager c) {
        super(c);
    }

    @Override
    public void insertRestaurant(Restaurant restaurant) throws SQLException {
        
        // Check if restaurant is valid
        if (!restaurant.validate()) return;
        
        String query = "INSERT INTO restaurants (name, description, street, city, zip_code, province, full_address, website, slot_price ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setString(0, restaurant.getName());
            stm.setString(1, restaurant.getDescription());
            stm.setString(2, restaurant.getStreet());
            stm.setString(3, restaurant.getCity());
            stm.setString(4, restaurant.getZipCode());
            stm.setString(5, restaurant.getProvince());
            stm.setString(6, restaurant.getStreet() + restaurant.getCity() + restaurant.getZipCode() + restaurant.getProvince());
            stm.setString(7, restaurant.getWebsite());
            stm.setInt(8, restaurant.getSlotPrice());
            stm.execute();
        } finally {
            stm.close();
        }
        
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) throws SQLException {
        
        // Check if restaurant is valid
        if (!restaurant.validate()) return;   
         
        String query = "UPDATE restaurants  SET name=?, description=?, street=?, city=?, zip_code=?, province=?, full_address=?, website=?, slot_price=? ) WHERE id=?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setString(0, restaurant.getName());
            stm.setString(1, restaurant.getDescription());
            stm.setString(2, restaurant.getStreet());
            stm.setString(3, restaurant.getCity());
            stm.setString(4, restaurant.getZipCode());
            stm.setString(5, restaurant.getProvince());
            stm.setString(6, restaurant.getStreet() + restaurant.getCity() + restaurant.getZipCode() + restaurant.getProvince());
            stm.setString(7, restaurant.getWebsite());
            stm.setInt(8, restaurant.getSlotPrice());
            stm.setInt(9, restaurant.getId());
            stm.execute();
        } finally {
            stm.close();
        }
        
    }

    @Override
    public List<Restaurant> getRestaurantByUserId(int id) throws SQLException {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM restaurant WHERE restaurant_owner_id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
         
            stm.setInt(0, id);
            ResultSet ownersSet = stm.executeQuery();
            try {
                while(ownersSet.next()) {
                    Restaurant tmp = new Restaurant();
                    tmp.setName(ownersSet.getString("name"));
                    tmp.setDescription(ownersSet.getString("description"));
                    tmp.setStreet(ownersSet.getString("street"));
                    tmp.setCity(ownersSet.getString("city"));
                    tmp.setZipCode(ownersSet.getString("zip_code"));
                    tmp.setProvince(ownersSet.getString("province"));
                    tmp.setFullAddress(ownersSet.getString("full_address"));
                    tmp.setWebsite(ownersSet.getString("website"));
                    tmp.setSlotPrice(ownersSet.getInt("slot_price"));
                    tmp.setRating(ownersSet.getString("rating"));
                    tmp.setMainPhotoId(ownersSet.getInt("main_photo_id"));
                    tmp.setRestaurantOwnerId(ownersSet.getInt("restaurant_owner_id"));
                    restaurants.add(tmp);
                }
            } finally {
                ownersSet.close();
            }
            
        } finally {
            stm.close();
        }
        return restaurants;
    }
    
}
