/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.interfaces;

import it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner;
import it.unitn.disi.webprog2016.convictor.app.beans.User;
import java.sql.SQLException;
import java.util.List;

/**
 * Users DAO
 * @author Giovanni De Toni
 */
public interface UserDAO {
    
    List<User> getAllUser() throws SQLException;
    User getUserById(int id) throws Exception;
	User getUserByEmail(String email) throws SQLException;
	User getUserByResetToken(String resetToken) throws SQLException;
	
    void updateUser(User user) throws SQLException;
	void updateUserPassword(User user) throws Exception;
    void insertUser(User user) throws SQLException;
	
    User authenticate(String email, String password) throws SQLException;
    User getResetPasswordToken(String email) throws SQLException;
	
	void promoteUserToRestaurantOwner(User user) throws Exception;
	
	RestaurantOwner getRestaurantOwnerById(int id) throws Exception; 
	
} 
