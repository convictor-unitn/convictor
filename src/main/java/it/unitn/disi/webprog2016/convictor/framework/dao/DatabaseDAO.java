/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.dao;

import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.io.Serializable;

/**
 *
 * @author umberto
 */
public abstract class DatabaseDAO implements Serializable{
	private transient final DatabaseConnectionManager dbManager;
	
	public DatabaseDAO(DatabaseConnectionManager c) {
		this.dbManager = c;
	}
}
