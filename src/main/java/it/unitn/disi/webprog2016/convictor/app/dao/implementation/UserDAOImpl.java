/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

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
        String query = "UPDATE users SET name=?, surname=?, password=?, email=?, admin=? WHERE id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setString(0, user.getName());
            stm.setString(1, user.getSurname());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getEmail());
            stm.setBoolean(4, user.isAdmin());
            stm.setInt(5, user.getId());
            stm.execute();
        } finally {
            stm.close();
        }
    }

    @Override
    public void insertUser(User user) throws SQLException {
        String query = "INSERT INTO users (name, surname, password, email, admin) VALUES (?, ?, ?, ? ?)";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setString(0, user.getName());
            stm.setString(1, user.getSurname());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getEmail());
            stm.setBoolean(4, user.isAdmin());
            stm.execute();
        } finally {
            stm.close();
        }
    }

    @Override
    public User authenticate(String email, String password) throws SQLException {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ? LIMIT 1";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
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

    
}
