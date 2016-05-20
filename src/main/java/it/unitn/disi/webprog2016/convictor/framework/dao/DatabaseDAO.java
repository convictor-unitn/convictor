/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.dao;

import java.sql.Connection;

/**
 *
 * @author umberto
 */
public abstract class DatabaseDAO {
	private final Connection connection;
	
	private DatabaseDAO(Connection c) {
		this.connection = c;
	}
}
