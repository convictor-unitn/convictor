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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT day, open_at, close_at, open_at_afternoon, close_at_afternoon, dayoff FROM opening_times WHERE restaurant_id=?";
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
                    tmp.setOpenAtAfternoon(openingTimesSet.getTime("open_at_afternoon"));
                    tmp.setCloseAtAfternoon(openingTimesSet.getTime("close_at_afternoon"));
                    tmp.setDayoff(openingTimesSet.getBoolean("dayoff"));
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
    
}
