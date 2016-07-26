/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.Photo;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.PhotoDAO;
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
public class PhotoDAOImpl extends DatabaseDAO implements PhotoDAO {

	public PhotoDAOImpl(DatabaseConnectionManager c) {
		super(c);
	}

	@Override
	public List<Photo> getRestaurantPhotos(int restaurant_id) throws SQLException {
		List<Photo> photos = new ArrayList<>();
		String query = "SELECT id, url FROM photos WHERE restaurant_id = ? ORDER BY created_at DESC";
		
		PreparedStatement stmt = this.getDbManager().getConnection().prepareStatement(query);
		try {
			stmt.setInt(1, restaurant_id);
			ResultSet results = stmt.executeQuery();
			if(results!=null) {
				while(results.next()) {
					Photo photo = new Photo();
					photo.setId(results.getInt("id"));
					photo.setUrl(results.getString("url"));
					photo.setRestaurantId(restaurant_id);
					photos.add(photo);
				}
			}
		}
		finally {
			stmt.close();
		}
		
		return photos;
	}

	@Override
	public void insertPhoto(Photo photo) throws SQLException {
		String query = "INSERT INTO photos (url, restaurant_id) VALUES (?,?)";
		
		PreparedStatement stmt = this.getDbManager().getConnection().prepareStatement(query);
		try {
			stmt.setString(1, photo.getUrl());
			stmt.setInt(2, photo.getRestaurantId());
			stmt.execute();
			ResultSet result;
			result = stmt.getGeneratedKeys();
			if(result.next() && result != null){
				photo.setId(result.getInt(1));
			}
		}
		finally {
			stmt.close();
		}
	}

	@Override
	public Photo getPhotoById(int id) throws SQLException {
		Photo photo = new Photo();
		String query = "SELECT id, url, restaurant_id FROM photos WHERE id = ?";
		
		PreparedStatement stmt = this.getDbManager().getConnection().prepareStatement(query);
		try {
			stmt.setInt(1, id);
			ResultSet results = stmt.executeQuery();
			if(results!=null) {
				while(results.next()) {
					photo.setId(results.getInt("id"));
					photo.setUrl(results.getString("url"));
					photo.setRestaurantId(results.getInt("restaurant_id"));
				}
			}
		}
		finally {
			stmt.close();
		}
		return photo;
	}

	@Override
	public void deletePhotoById(int id_photo) throws SQLException {
		String query= "DELETE FROM photos WHERE id=?";
	}

	@Override
	public Photo getPhotoByUrl(String url) throws SQLException {
		Photo photo = new Photo();
		String query = "SELECT id, url, restaurant_id FROM photos WHERE url = ?";
		
		PreparedStatement stmt = this.getDbManager().getConnection().prepareStatement(query);
		try {
			stmt.setString(1, url);
			ResultSet results = stmt.executeQuery();
			if(results!=null) {
				while(results.next()) {
					photo.setId(results.getInt("id"));
					photo.setUrl(results.getString("url"));
					photo.setRestaurantId(results.getInt("restaurant_id"));
				}
			}
		}
		finally {
			stmt.close();
		}
		return photo;
	}
	
}
