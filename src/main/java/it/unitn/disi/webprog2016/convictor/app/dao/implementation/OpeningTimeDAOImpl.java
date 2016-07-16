/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.OpeningTime;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.OpeningTimesDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uriel
 */
public class OpeningTimeDAOImpl extends DatabaseDAO implements OpeningTimesDAO {

    public OpeningTimeDAOImpl(DatabaseConnectionManager c) {
        super(c);
    }

    @Override
    public List<OpeningTime> getResaurantOpeningTimes(int restaurant_id) throws SQLException {
        List<OpeningTime> openingTimes = new ArrayList<>();
        String query = "SELECT day, open_at, close_at FROM opening_times WHERE restaurant_id=?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, restaurant_id);
            ResultSet openingTimesSet = stm.executeQuery();
            try {
                while(openingTimesSet.next()) {
                    OpeningTime tmp = new OpeningTime();
                    tmp.setDay(openingTimesSet.getInt("day"));
                    tmp.setDayString(tmp.getDay());
                    tmp.setOpenAt(openingTimesSet.getTime("open_at"));
                    tmp.setCloseAt(openingTimesSet.getTime("close_at"));
                    openingTimes.add(tmp);
                }
            } finally {
                openingTimesSet.close();
            }
            
        } finally {
            stm.close();
        }
        return openingTimes;
    }

    @Override
    public void insertRestaurantOpeningTimes(int restaurant_id, List<OpeningTime> times) throws SQLException {        
        String query = "INSERT INTO opening_times (restaurant_id, day, open_at, close_at, open_at_afternoon, close_at_afternoon, dayoff) VALUES (?,?,?,?,?,?, ?)";
        for (OpeningTime time : times) {
            PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
            try {
                stm.setInt(1, restaurant_id);
                stm.setInt(2, time.getDay());
                stm.setTime(3, new Time(time.getOpenAt().getTime()));
                stm.setTime(4, new Time(time.getCloseAt().getTime()));
                stm.setTime(5, new Time(time.getOpenAtAfternoon().getTime()));
                stm.setTime(6, new Time(time.getCloseAtAfternoon().getTime()));
                stm.setBoolean(7, time.isDayoff());
                stm.executeUpdate();
            } finally {
                stm.close();
            }
        }
    }
    
}
