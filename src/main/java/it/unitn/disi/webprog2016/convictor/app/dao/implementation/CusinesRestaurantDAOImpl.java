/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.Cusine;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.CusinesRestaurantDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uriel
 */
public class CusinesRestaurantDAOImpl extends DatabaseDAO implements CusinesRestaurantDAO {

    public CusinesRestaurantDAOImpl(DatabaseConnectionManager c) {
        super(c);
    }

    @Override
    public List<Cusine> getCusinesByRestaurantId(int restaurant_id) throws SQLException {
        List<Cusine> cusines = new ArrayList<>();
        String query = "select cusines.id, cusines.name from cusines_restaurants inner join cusines on cusines_restaurants.cusine_id = cusines.id where restaurant_id =?;";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, restaurant_id);
            ResultSet cusinesSet = stm.executeQuery();
            try {
                while(cusinesSet.next()) {
                    Cusine tmp = new Cusine();
                    tmp.setId(cusinesSet.getInt("id"));
                    tmp.setName(cusinesSet.getString("name"));
                    cusines.add(tmp);
                }
            } finally {
                cusinesSet.close();
            }
        } finally {
            stm.close();
        }
       return cusines;
    }

    @Override
    public void insertRestaurantCusines(int restaurant_id, List<Cusine> cusines) throws SQLException {
       String query = "INSERT INTO cusines_restaurants (restaurant_id, cusine_id) VALUES (?, ?);";
        for (int i = 0; i < cusines.size(); i++) {
            PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
            try {
                stm.setInt(1, restaurant_id);
                stm.setInt(2, cusines.get(i).getId());
                stm.executeUpdate();
            } finally {
                stm.close();
            }
        }
    }

    @Override
    public Cusine getCusinebyName(String name) throws SQLException {
        Cusine cusine = new Cusine();
        String query = "select * from cusines where name=?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setString(1, name);
            ResultSet cusinesSet = stm.executeQuery();
            try {
                while(cusinesSet.next()) {
                    cusine.setId(cusinesSet.getInt("id"));
                    cusine.setName(cusinesSet.getString("name"));
                }
            } finally {
                cusinesSet.close();
            }
        } finally {
            stm.close();
        }
       return cusine;
    }

    @Override
    public void updateRestaurantCusines(int restaurant_id, List<Cusine> cusines) throws SQLException {
        String query = "DELETE FROM cusines_restaurants WHERE restaurant_id = ?;";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, restaurant_id);
            stm.executeUpdate();
        } finally {
            stm.close();
            //this.insertRestaurantCusines(restaurant_id, cusines);
        }
    }
    
}
