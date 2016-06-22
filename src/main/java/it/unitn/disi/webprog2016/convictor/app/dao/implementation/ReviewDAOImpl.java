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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giovanni De Toni
 */
public class ReviewDAOImpl extends DatabaseDAO implements ReviewDAO {

    public ReviewDAOImpl(DatabaseConnectionManager c) {
        super(c);
    }

    @Override
    public List<Review> getRestaurantReviews(int restaurant_id) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM review WHERE restaurant_id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(0, restaurant_id);
            ResultSet reviewSet = stm.executeQuery();
            try {
                while (reviewSet.next()) {                    
                    Review tmp = new Review();
                    tmp.setRestaurantId(reviewSet.getString("restaurant_id"));
                    tmp.setRegisteredUserId(reviewSet.getString("registered_user_id"));
                    tmp.setRating(reviewSet.getString("rating"));
                    tmp.setDescription(reviewSet.getString("description"));
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
    public void insertReview(Review review) throws SQLException {
        
        // Check if valid
        if (!review.validate()) return;
        
        String query = "INSERT INTO review (registered_user_id, restaurant_id, description, rating) VALUES(?, ?, ?, ?)";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(0, review.getRegisteredUserId());
            stm.setInt(1, review.getRestaurantId());
            stm.setString(2, review.getDescription());
            stm.setInt(3, review.getRating());
            stm.executeQuery();
        } finally {
            stm.close();
        }
    }
    
}
