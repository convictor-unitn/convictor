/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.PriceSlot;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.PriceSlotDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giovanni
 */
public class PriceSlotDAOImpl extends DatabaseDAO implements PriceSlotDAO{
	public PriceSlotDAOImpl(DatabaseConnectionManager c) {
		super(c);
	}

	@Override
	public List<PriceSlot> getAllPriceSlots() throws SQLException {
		List<PriceSlot> pricesSlot = new ArrayList<>();
        String query = "SELECT price_slots.slot, price_slots.name FROM price_slots";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            ResultSet priceslotSet = stm.executeQuery();
            try {
                while(priceslotSet.next()) {
                    PriceSlot tmp = new PriceSlot();
                    tmp.setSlot(priceslotSet.getInt("slot"));
                    tmp.setName(priceslotSet.getString("name"));
                    pricesSlot.add(tmp);
                }
            } finally {
                priceslotSet.close();
            }
        } finally {
            stm.close();
        }
       return pricesSlot;
	}
}
