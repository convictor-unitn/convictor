/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.interfaces;

import it.unitn.disi.webprog2016.convictor.app.beans.PriceSlot;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Giovanni
 */
public interface PriceSlotDAO {
		List<PriceSlot> getAllPriceSlots() throws SQLException;
}
