/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.interfaces;

import it.unitn.disi.webprog2016.convictor.app.beans.Photo;
import it.unitn.disi.webprog2016.convictor.app.beans.Restaurant;
import java.sql.SQLException;
import java.util.List;

/**
 * Restaurant DAO.
 * @author Giovanni De Toni
 */
public interface RestaurantDAO {
    
    List<Restaurant> getRestaurantByString(String pattern, int offset) throws SQLException;
    Restaurant getRestaurantById(int id ) throws SQLException;
    int insertRestaurant(Restaurant restaurant) throws SQLException;
    int updateRestaurant(Restaurant restaurant, int id) throws SQLException;
    List<Restaurant> getRestaurantByUserId(int id) throws SQLException;
	@Deprecated
	void insertPhoto(Photo photo) throws SQLException;
	
}
