/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.Cusine;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.CusineDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author umberto
 */
public class CusineDAOImpl extends DatabaseDAO implements CusineDAO {

	public CusineDAOImpl(DatabaseConnectionManager c) {
		super(c);
	}

	@Override
	public List<Cusine> getAllCusines() throws SQLException {
		List<Cusine> cusines = new ArrayList<>();
        String query = "SELECT cusines.id, cusines.name FROM cusines";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
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
