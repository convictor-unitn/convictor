/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.interfaces;

import it.unitn.disi.webprog2016.convictor.app.beans.Review;
import java.sql.SQLException;
import java.util.List;

/**
 * Review DAO.
 * @author Giovanni De Toni
 */
public interface ReviewDAO {
    
    List<Review> getRestaurantReviews(int restaurant_id, int offset) throws SQLException;
    int insertReview(Review review) throws SQLException;
    
}
