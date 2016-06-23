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
    public int insertRestaurant(Restaurant restaurant) throws SQLException {
        
        // Check if restaurant is valid
        if (!restaurant.validate()) return -1;
        
        int restaurant_id = -1;
        
        String query = "INSERT INTO restaurants (name, description, street, city, zip_code, province, full_address, website, slot_price ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
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
            
            ResultSet id = stm.executeQuery(query);
            try {
                restaurant_id = id.getInt("id");
            } finally {
                id.close();
            }
        } finally {
            stm.close();
        }
        
        return restaurant_id;
        
    }

    @Override
    public int updateRestaurant(Restaurant restaurant) throws SQLException {
        
        // Check if restaurant is valid
        if (!restaurant.validate()) return -1;   
         
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
        return restaurant.getId();
    }

    @Override
    public List<Restaurant> getRestaurantByUserId(int id) throws SQLException {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT id, name, description, street, city, zip_code, province, full_address, website, slot_price, rating, main_phot_id, restaurant_owner_id FROM restaurant WHERE restaurant_owner_id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
         
            stm.setInt(0, id);
            ResultSet ownersSet = stm.executeQuery();
            try {
                while(ownersSet.next()) {
                    Restaurant tmp = new Restaurant();
                    tmp.setId(ownersSet.getInt("id"));
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

    @Override
    public Restaurant getRestaurantById(int id) throws SQLException {
        Restaurant tmp = null;
        String query = "SELECT * FROM restaurants WHERE id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, id);
            ResultSet ownersSet = stm.executeQuery();
            try {
                while(ownersSet.next()) {
                    tmp = new Restaurant();
                    tmp.setId(ownersSet.getInt("id"));
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
                }
            } finally {
                ownersSet.close();
            }
            
        } finally {
            stm.close();
        }
        return tmp;
    }
    
}
