/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.interfaces;

import it.unitn.disi.webprog2016.convictor.app.beans.User;
import java.util.List;

/**
 * Users DAO
 * @author Giovanni De Toni
 */
public interface UserDAO {
    
    List<User> getAllUser();
    User getUserById(int id);
    void updateUser(User user);
    void insertUser(User user);
    
    User authenticate(String email, String password);
    
} 
