/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.interfaces;

import it.unitn.disi.webprog2016.convictor.app.beans.Cusine;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author umberto
 */
public interface CusineDAO {
	
	List<Cusine> getAllCusines() throws SQLException;
	
}
