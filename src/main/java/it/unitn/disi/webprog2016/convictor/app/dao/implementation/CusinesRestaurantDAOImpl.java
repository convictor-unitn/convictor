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
    
}
