/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.Review;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.ReviewDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giovanni De Toni
 */
public class ReviewDAOImpl extends DatabaseDAO implements ReviewDAO {

    static int MAX_RESULT = 5;
    
    public ReviewDAOImpl(DatabaseConnectionManager c) {
        super(c);
    }

    @Override
    public List<Review> getRestaurantReviews(int restaurant_id, int offset) throws SQLException {
        
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT reviews.id, reviews.created_at, restaurant_id, registered_user_id, rating, description, users.name, users.surname  from reviews inner join users on users.id = registered_user_id where reviews.restaurant_id = ? ORDER BY reviews.created_at DESC LIMIT ? OFFSET ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, restaurant_id);
            stm.setInt(2, MAX_RESULT);
            stm.setInt(3, offset*MAX_RESULT);
            ResultSet reviewSet = stm.executeQuery();
            try {
                while (reviewSet.next()) {                    
                    Review tmp = new Review();
                    tmp.setId(reviewSet.getInt("id"));
					tmp.setCreatedAt(new Date(reviewSet.getTimestamp("created_at").getTime()));
                    tmp.setRestaurantId(reviewSet.getString("restaurant_id"));
                    tmp.setRegisteredUserId(reviewSet.getString("registered_user_id"));
                    tmp.setRegisteredUserName(
                            reviewSet.getString("name")+ " "+
                            reviewSet.getString("surname")
                    );
                    tmp.setRating(reviewSet.getString("rating"));
                    tmp.setDescription(reviewSet.getString("description"));
					Date tmpDate = new Date(reviewSet.getTimestamp("created_at").getTime());
					tmp.setCreatedAt(tmpDate);
                    reviews.add(tmp);
                }
            } finally {
                reviewSet.close();
            }
        } finally {
            stm.close();
        }
        return reviews;
    }

    @Override
    public int insertReview(Review review) throws SQLException {
        int restaurant_id = -1;
        String query = "INSERT INTO reviews (registered_user_id, restaurant_id, description, rating) VALUES(?, ?, ?, ?)";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        try {
            stm.setInt(1, review.getRegisteredUserId());
            stm.setInt(2, review.getRestaurantId());
            stm.setString(3, review.getDescription());
            stm.setInt(4, review.getRating());
            stm.executeUpdate();
            ResultSet result;
			result = stm.getGeneratedKeys();
			if(result.next() && result != null){
				restaurant_id=result.getInt(1);
			}
        } finally {
            stm.close();
        }
        return restaurant_id;
    }

	@Override
	public Review getReviewById(int id) throws SQLException {
		Review tmp = new Review();
        String query = "SELECT reviews.id, reviews.created_at, restaurant_id, registered_user_id, users.name, users.surname, rating, description FROM reviews INNER JOIN users ON registered_user_id = users.id WHERE reviews.id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, id);
            ResultSet reviewSet = stm.executeQuery();
            try {
                while (reviewSet.next()) {                    
                    tmp.setId(reviewSet.getInt("id"));
					tmp.setCreatedAt(new Date(reviewSet.getTimestamp("created_at").getTime()));
                    tmp.setRestaurantId(reviewSet.getString("restaurant_id"));
                    tmp.setRegisteredUserId(reviewSet.getString("registered_user_id"));
                    tmp.setRegisteredUserName(
                            reviewSet.getString("name")+ " "+
                            reviewSet.getString("surname")
                    );
                    tmp.setRating(reviewSet.getString("rating"));
                    tmp.setDescription(reviewSet.getString("description"));
				}
            } finally {
                reviewSet.close();
            }
        } finally {
            stm.close();
        }
		return tmp;
	}

	@Override
	public List<Review> getMostRecentReviewsByUserId(int user_id) throws SQLException {
		List<Review> reviews = new ArrayList<>();
        String query = "SELECT id, restaurant_id, registered_user_id FROM reviews WHERE registered_user_id = ? AND created_at > ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.HOUR_OF_DAY, -24);
			Date tmpDate = cal.getTime();
			
            stm.setInt(1, user_id);
            stm.setTimestamp(2, new Timestamp(tmpDate.getTime()));
            ResultSet reviewSet = stm.executeQuery();
            try {
                while (reviewSet.next()) {                    
                    Review tmp = new Review();
                    tmp.setId(reviewSet.getInt("id"));
                    tmp.setRestaurantId(reviewSet.getString("restaurant_id"));
                    tmp.setRegisteredUserId(reviewSet.getString("registered_user_id"));
                    reviews.add(tmp);
                }
            } finally {
                reviewSet.close();
            }
        } finally {
            stm.close();
        }
        return reviews;
	}
    
}
