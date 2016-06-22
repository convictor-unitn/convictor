/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.Notice;
import it.unitn.disi.webprog2016.convictor.app.beans.OwnershipNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.PhotoNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.PhotoRemovalNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.ReviewNotice;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.NoticeDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the NoticeDAO interface.
 * @author Giovanni De Toni
 */
public class NoticeDAOImpl extends DatabaseDAO implements NoticeDAO {

    public NoticeDAOImpl(DatabaseConnectionManager c) {
        super(c);
    }

    @Override
    public List<Notice> getAdministratorNotices(int id) throws SQLException {
        List<Notice> notices = new ArrayList<>();
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(
                "SELECT * FROM ownership_notices"
            );
        PreparedStatement stm2 = this.getDbManager().getConnection().prepareStatement(
                "SELECT * FROM photo_removal_notices"
            );
        try {   
            
            stm.setInt(1, id);
            stm2.setInt(1, id);
            ResultSet ownershipSet = stm.executeQuery();
            ResultSet removalSet = stm2.executeQuery();
            
            // Fill the notices list
            try {
                while(ownershipSet.next()) {
                    OwnershipNotice tmp = new OwnershipNotice();
                    tmp.setId(ownershipSet.getString("id"));
                    tmp.setRegisteredUserId(ownershipSet.getString("registered_user_id"));
                    tmp.setRestaurantId(ownershipSet.getString("restaurant_id"));
                    notices.add(tmp);
                }
                
                while (removalSet.next()) {
                    PhotoRemovalNotice tmp = new PhotoRemovalNotice();
                    tmp.setId(removalSet.getInt("id"));
                    tmp.setRegisteredUserId(removalSet.getInt("registered_user_id"));
                    tmp.setPhotoId(removalSet.getInt("photo_id"));
                    tmp.setApproved(removalSet.getBoolean("approved"));
                    notices.add(tmp);
                }
                
            } finally {
                ownershipSet.close();
                removalSet.close();
            }    
        } finally {
            stm.close();
            stm2.close();
        }
        return notices;
    }

    @Override
    public List<Notice> getRestaurantOwnerNotices(int id) throws SQLException {
        List<Notice> notices = new ArrayList<>();
        String queryReviewNotices = "SELECT * FROM review_notices RE INNER JOIN reviews R ON RE.review_id = R.id INNER JOIN restaurant RES ON RES.id = R.restaurant_id INNER JOIN users U ON U.id = RES.restaurant_owner_id WHERE U.id=?";
        String queryPhotoNotices = "SELECT * FROM photo_notices PN INNER JOIN photos P ON PN.photo_id = P.id INNER JOIN restaurant RES ON RES.id = P.restaurant_id INNER JOIN users U ON U.id = RES.restaurant_owner_id WHERE U.id=?;";
        
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(queryReviewNotices);
        PreparedStatement stm2 = this.getDbManager().getConnection().prepareStatement(queryPhotoNotices);
        
        try {
            stm.setInt(1, id);
            stm2.setInt(1, id);
            ResultSet reviewSet = stm.executeQuery();
            ResultSet photoSet = stm.executeQuery();
            try {
                while(reviewSet.next()) {
                    ReviewNotice tmp = new ReviewNotice();
                    tmp.setId(reviewSet.getString("id"));
                    tmp.setRegisteredUserId(reviewSet.getString("registered_user_id"));
                    tmp.setReviewId(reviewSet.getString("review_id"));
                    notices.add(tmp);
                }
                while(photoSet.next()) {
                    PhotoNotice tmp = new PhotoNotice();
                    tmp.setId(photoSet.getString("id"));
                    tmp.setPhotoId(photoSet.getString("photo_id"));
                    tmp.setRegisteredUserId(photoSet.getString("registered_user_id"));
                    notices.add(tmp);
                }
            } finally {
                reviewSet.close();
                photoSet.close();
            }
            
        } finally {
            stm.close();
            stm2.close();
        }
        
        return notices;
    }

    @Override
    public void insertPhotoNotice(PhotoNotice notice) throws SQLException {
        
        // Check if valid
        if (!notice.validate()) return;
        
        String query = "INSERT INTO photo_notices VALUES(?, ?, ?, ?);";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(2, notice.getRegisteredUserId());
            stm.setInt(3, notice.getPhotoId());
            stm.executeQuery();
        } finally {
            stm.close();
        }
    }

    @Override
    public void insertPhotoRemovalNotice(PhotoRemovalNotice notice) throws SQLException {
        
        // Check if valid
        if (!notice.validate()) return;
        
        String query = "INSERT INTO photo_removal_notices VALUES(?, ?, ?, ?, ?);";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(2, notice.getRegisteredUserId());
            stm.setInt(3, notice.getPhotoId());
            stm.setBoolean(4, false);
            stm.executeQuery();
        } finally {
            stm.close();
        }
    }

    @Override
    public void insertReviewNotice(ReviewNotice notice) throws SQLException {
        
        // Check if valid
        if (!notice.validate()) return;
        
        String query = "INSERT INTO review_notices VALUES(?, ?, ?, ?);";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(2, notice.getRegisteredUserId());
            stm.setInt(3, notice.getReviewId());
            stm.executeQuery();
        } finally {
            stm.close();
        }
    }

    @Override
    public void insertOwnershipNotice(OwnershipNotice notice) throws SQLException {
        
        // Check if valid
        if (!notice.validate()) return;
        
        String query = "INSERT INTO ownership_notices VALUES(?, ?, ?, ?, ?);";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(2, notice.getRegisteredUserId());
            stm.setInt(3, notice.getRestaurantId());
            stm.setBoolean(4, false);
            stm.executeQuery();
        } finally {
            stm.close();
        }
    }

    @Override
    public void approvePhotoRemovalNotice(boolean approved, int id) throws SQLException {
        
        // Check if valid
        if (id <= 0) return;
        
        String query = "UPDATE photo_removal_notice SET approved = ? WHERE id = ? ";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setBoolean(0, approved);
            stm.setInt(1, id);
            stm.executeQuery();
        } finally {
            stm.close();
        }
    }

    @Override
    public void approveOwershipNotice(boolean approved, int id) throws SQLException {
        
        // Check if valid
        if (id <= 0) return;
        
        String query = "UPDATE ownership_notice SET approved = ? WHERE id = ? ";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setBoolean(0, approved);
            stm.setInt(1, id);
            stm.executeQuery();
        } finally {
            stm.close();
        }
    }
    
}
