/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.Administrator;
import it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner;
import it.unitn.disi.webprog2016.convictor.app.beans.User;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.UserDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Users DAO implementation
 * @author Giovanni De Toni
 */
public class UserDAOImpl extends DatabaseDAO implements UserDAO{

    public UserDAOImpl(DatabaseConnectionManager c) {
        super(c);
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            ResultSet usersSet = stm.executeQuery();
            try {
                while(usersSet.next()) {
                    User tmp = new User();
                    tmp.setId(usersSet.getInt("id"));
                    tmp.setName(usersSet.getString("name"));
                    tmp.setEmail(usersSet.getString("email"));
                    tmp.setSurname(usersSet.getString("surname"));
                    tmp.setPassword(usersSet.getString("password"));
                    tmp.setAdmin(usersSet.getString("admin"));
                    users.add(tmp);
                }
            } finally {
                usersSet.close();
            }
        } finally {
            stm.close();
        }
        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
			stm.setInt(1, id);
            ResultSet usersSet = stm.executeQuery();
            try {
                while(usersSet.next()) {
                    user= new User();
                    user.setId(usersSet.getInt("id"));
                    user.setName(usersSet.getString("name"));
                    user.setEmail(usersSet.getString("email"));
                    user.setSurname(usersSet.getString("surname"));
                    user.setPassword(usersSet.getString("password"));
                    user.setAdmin(usersSet.getString("admin"));
                }
            } finally {
                usersSet.close();
            }
        } finally {
            stm.close();
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        
        // Check if the update is permitted
        if (!user.validate()) return;
        
        String query = "UPDATE users SET name=?, surname=?, password=?, email=?, admin=? WHERE id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setString(1, user.getName());
            stm.setString(2, user.getSurname());
            stm.setString(3, user.getPassword());
            stm.setString(4, user.getEmail());
            stm.setBoolean(5, user.isAdmin());
            stm.setInt(6, user.getId());
            stm.execute();
        } finally {
            stm.close();
        }
    }

    @Override
    public void insertUser(User user) throws SQLException {
        
        // Check if the user is valid
        if (!user.validate()) return;
        
        String query = "INSERT INTO users (name, surname, password, email, admin) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setString(1, user.getName());
            stm.setString(2, user.getSurname());
            stm.setString(3, user.getPassword());
            stm.setString(4, user.getEmail());
            stm.setBoolean(5, user.isAdmin());
            stm.execute();
        } finally {
            stm.close();
        }
    }

    @Override
    public User authenticate(String email, String password) throws SQLException {
        User user = null;
        String query = "SELECT users.id, name, email, surname, password, admin, restaurant_owners.user_id AS restaurant_owner_id FROM users LEFT JOIN restaurant_owners ON users.id=restaurant_owners.user_id WHERE email = ? AND password = ? LIMIT 1";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
			stm.setString(1, email);
			stm.setString(2, password);
            ResultSet usersSet = stm.executeQuery();
            try {	
                while(usersSet.next()) {
					if(usersSet.getBoolean("admin")) {
						user = new Administrator();
					} 
					else if (usersSet.getInt("restaurant_owner_id")!=0) {
						System.out.println("LOGGED AS RESTAURANT OWNER");
						user= new RestaurantOwner();
					}
					else {
						user= new User();
					}
						
					user.setId(usersSet.getInt("id"));
                    user.setName(usersSet.getString("name"));
                    user.setEmail(usersSet.getString("email"));
                    user.setSurname(usersSet.getString("surname"));
                    user.setPassword(usersSet.getString("password"));
                    user.setAdmin(usersSet.getString("admin"));
                }
				
				if(user==null) {
					user = new User();
					user.setEmail(email);
					user.setError("loginError", "Nome utente e/o password non validi");
				}
            } finally {
                usersSet.close();
            }
        } finally {
            stm.close();
        }
        return user;
    }

    
}
