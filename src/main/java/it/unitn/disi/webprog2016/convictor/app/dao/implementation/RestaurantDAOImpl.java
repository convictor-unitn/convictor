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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
        
        String query = "INSERT INTO restaurants (name, description, street, city, zip_code, province, full_address, website, slot_price, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        try {
            stm.setString(1, restaurant.getName());
            stm.setString(2, restaurant.getDescription());
            stm.setString(3, restaurant.getStreet());
            stm.setString(4, restaurant.getCity());
            stm.setString(5, restaurant.getZipCode());
            stm.setString(6, restaurant.getProvince());
            stm.setString(7, restaurant.getStreet() + restaurant.getCity() + restaurant.getZipCode() + restaurant.getProvince());
            stm.setString(8, restaurant.getWebsite());
            stm.setInt(9, restaurant.getSlotPrice());
            stm.setString(10, restaurant.getEmail());
            stm.setString(11, restaurant.getPhone());
			stm.executeUpdate();
			ResultSet result;
			result = stm.getGeneratedKeys();
			if(result.next() && result != null){
				restaurant_id=result.getInt(1);
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
         
        String query = "UPDATE restaurants  SET name=?, description=?, street=?, city=?, zip_code=?, province=?, full_address=?, website=?, slot_price=? , phone=?, email=?   WHERE id=?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setString(1, restaurant.getName());
            stm.setString(2, restaurant.getDescription());
            stm.setString(3, restaurant.getStreet());
            stm.setString(4, restaurant.getCity());
            stm.setString(5, restaurant.getZipCode());
            stm.setString(6, restaurant.getProvince());
            stm.setString(7, restaurant.getStreet() + restaurant.getCity() + restaurant.getZipCode() + restaurant.getProvince());
            stm.setString(8, restaurant.getWebsite());
            stm.setInt(9, restaurant.getSlotPrice());
            stm.setInt(10, restaurant.getId());
            stm.setString(11, restaurant.getPhone());
            stm.setString(12, restaurant.getEmail());
            stm.execute();
        } finally {
            stm.close();
        }
        return restaurant.getId();
    }

    @Override
    public List<Restaurant> getRestaurantByUserId(int id) throws SQLException {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT id, name, description, street, city, zip_code, province, full_address, website, slot_price, rating, main_phot_id, restaurant_owner_id, phone, email FROM restaurant WHERE restaurant_owner_id = ?";
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
                    tmp.setEmail(ownersSet.getString("email"));
                    tmp.setPhone(ownersSet.getString("phone"));
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
                    tmp.setEmail(ownersSet.getString("email"));
                    tmp.setPhone(ownersSet.getString("phone"));
                }
            } finally {
                ownersSet.close();
            }
            
        } finally {
            stm.close();
        }
        return tmp;
    }

    @Override
    public HashMap<Integer, List<Restaurant>> getRestaurantByString(String pattern, int offset) throws SQLException {
        
        HashMap<Integer, List<Restaurant>> result = new HashMap<>();
        List<Restaurant> listResult = new ArrayList<>();
        Integer counter = 0;
        String fullTextPattern = pattern.replace(" ", "&");
        
        // Deleted fro these query ' char. They cause errors when stm.setString
        // is called. PreparedStatement should add single quote automatically.
        String count ="SELECT COUNT(*) FROM restaurants WHERE tsv @@ tsquery(?) OR searchable ILIKE ?";
        String query ="SELECT * FROM restaurants WHERE tsv @@ tsquery(?) OR searchable ILIKE ? LIMIT 10 OFFSET ?";
        
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(count);
        PreparedStatement stm2 = this.getDbManager().getConnection().prepareStatement(query);
        try {
            // Obtain the number of record
            stm.setString(1, fullTextPattern);
            
            // The ILIKE has to be set like this because PreparedStatement
            // doesn't like %?% this pattern. Therefore, we must concatenate
            // % at the beginning and at the end of the fullTextPattern.
            stm.setString(2, "%"+fullTextPattern+"%");
            
            ResultSet countSet = stm.executeQuery();
            try {
                while(countSet.next()) {
                    counter = countSet.getInt("count");
                }
            } finally {
                countSet.close();
            }
            
            // Obtain the restaurant paginated 
            stm2.setString(1, fullTextPattern);
            stm2.setString(2, "%"+fullTextPattern+"%");
            stm2.setInt(3, counter);
            ResultSet restaurantSet = stm2.executeQuery();
            try {
                while(restaurantSet.next()) {
                    Restaurant tmp = new Restaurant();
                    tmp.setId(restaurantSet.getInt("id"));
                    tmp.setName(restaurantSet.getString("name"));
                    tmp.setDescription(restaurantSet.getString("description"));
                    tmp.setStreet(restaurantSet.getString("street"));
                    tmp.setCity(restaurantSet.getString("city"));
                    tmp.setZipCode(restaurantSet.getString("zip_code"));
                    tmp.setProvince(restaurantSet.getString("province"));
                    tmp.setFullAddress(restaurantSet.getString("full_address"));
                    tmp.setWebsite(restaurantSet.getString("website"));
                    tmp.setSlotPrice(restaurantSet.getInt("slot_price"));
                    tmp.setRating(restaurantSet.getString("rating"));
                    tmp.setMainPhotoId(restaurantSet.getInt("main_photo_id"));
                    tmp.setRestaurantOwnerId(restaurantSet.getInt("restaurant_owner_id"));
                    tmp.setEmail(restaurantSet.getString("email"));
                    tmp.setPhone(restaurantSet.getString("phone"));
                    listResult.add(tmp);   
                }
            } finally {
                restaurantSet.close();
            }
            result.put(counter, listResult);
                
        } finally {
            stm.close();
            stm2.close();
        }
        return result;
    }
    
}
