/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.interfaces;

import it.unitn.disi.webprog2016.convictor.app.beans.Photo;
import it.unitn.disi.webprog2016.convictor.app.beans.Restaurant;
import it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner;
import java.sql.SQLException;
import java.util.List;

/**
 * Restaurant DAO.
 * @author Giovanni De Toni
 */
public interface RestaurantDAO {
    
    List<Restaurant> getRestaurantByString(String pattern, int offset) throws SQLException;
    List<Restaurant> getRestaurantByString(String pattern, int offset, List<String> cusines) throws SQLException;
    
    List<Restaurant> getRestaurantByStringOrderByName(String pattern, int offset) throws SQLException;
    List<Restaurant> getRestaurantByStringOrderByName(String pattern, int offset, List<String> cusines) throws SQLException;
    
    List<Restaurant> getRestauranyByStringOrderByPrice(String pattern, int offset, int type) throws SQLException;
    List<Restaurant> getRestauranyByStringOrderByPrice(String pattern, int offset, int type, List<String> cusines) throws SQLException;
    
	List<Restaurant> getRestaurantByUserId(int id) throws SQLException;
	Restaurant getRestaurantById(int id ) throws SQLException;
	
	int insertRestaurant(Restaurant restaurant) throws SQLException;
    int updateRestaurant(Restaurant restaurant, int id) throws SQLException;
    
    void computeRating(int restaurant_id) throws SQLException;
    
    @Deprecated
	void insertPhoto(Photo photo) throws SQLException;
	
	void assignRestaurant(Restaurant restaurant, RestaurantOwner restaurantOwner) throws Exception;
}
