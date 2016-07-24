/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.Notice;
import it.unitn.disi.webprog2016.convictor.app.beans.OwnershipNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.Photo;
import it.unitn.disi.webprog2016.convictor.app.beans.PhotoNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.PhotoRemovalNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.Review;
import it.unitn.disi.webprog2016.convictor.app.beans.ReviewNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.User;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.NoticeDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the NoticeDAO interface.
 * @author Giovanni De Toni
 */
public class NoticeDAOImpl extends DatabaseDAO implements NoticeDAO {

    int MAX_RESULT = 5;
    
    public NoticeDAOImpl(DatabaseConnectionManager c) {
        super(c);
    }

    @Override
    public List<Notice> getAdministratorNotices(int id, int offset) throws SQLException {
        List<Notice> notices = new ArrayList<>();
		
		UserDAOImpl userDAO = new UserDAOImpl(this.getDbManager());
        PhotoDAOImpl photoDAO = new PhotoDAOImpl(this.getDbManager());
		
		PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(
                "SELECT * FROM ownership_notices LIMIT ? OFFSET ?"
            );
        PreparedStatement stm2 = this.getDbManager().getConnection().prepareStatement(
                "SELECT * FROM photo_remove_notices LIMIT ? OFFSET ?"
            );
        try {
            stm.setInt(1, MAX_RESULT);
            stm.setInt(2, MAX_RESULT*offset);
            ResultSet ownershipSet = stm.executeQuery();
            stm2.setInt(1, MAX_RESULT);
            stm2.setInt(2, MAX_RESULT*offset);
            ResultSet removalSet = stm2.executeQuery();
            
            // Fill the notices list
            try {
                while(ownershipSet.next()) {
                    OwnershipNotice tmp = new OwnershipNotice();
                    tmp.setId(ownershipSet.getString("id"));
					tmp.setCreatedAt(new Date(ownershipSet.getTimestamp("created_at").getTime()));
                    tmp.setRegisteredUserId(ownershipSet.getString("registered_user_id"));
                    tmp.setRestaurantId(ownershipSet.getString("restaurant_id"));
					
					User tmpUser = userDAO.getUserById(tmp.getRegisteredUserId());
					tmp.setRegisteredUser(tmpUser);
					
                    notices.add(tmp);
                }
                
                while (removalSet.next()) {
                    PhotoRemovalNotice tmp = new PhotoRemovalNotice();
                    tmp.setId(removalSet.getInt("id"));
					tmp.setCreatedAt(new Date(removalSet.getTimestamp("created_at").getTime()));
                    tmp.setRegisteredUserId(removalSet.getInt("registered_user_id"));
                    tmp.setPhotoId(removalSet.getInt("photo_id"));
                    tmp.setApproved(removalSet.getBoolean("approved"));
					
					User tmpUser = userDAO.getUserById(tmp.getRegisteredUserId());
					tmp.setRegisteredUser(tmpUser);
					
					Photo tmpPhoto = photoDAO.getPhotoById(tmp.getPhotoId());
					tmp.setPhoto(tmpPhoto);
					
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
    public List<Notice> getRestaurantOwnerNotices(int id, int offset) throws SQLException {
        List<Notice> notices = new ArrayList<>();
        String queryReviewNotices = "SELECT * FROM review_notices RE INNER JOIN reviews R ON RE.review_id = R.id INNER JOIN restaurants RES ON RES.id = R.restaurant_id INNER JOIN users U ON U.id = RES.restaurant_owner_id WHERE U.id=? LIMIT ? OFFSET ?";
        String queryPhotoNotices = "SELECT * FROM photo_notices PN INNER JOIN photos P ON PN.photo_id = P.id INNER JOIN restaurants RES ON RES.id = P.restaurant_id INNER JOIN users U ON U.id = RES.restaurant_owner_id WHERE U.id=? LIMIT ? OFFSET ?";
        
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(queryReviewNotices);
        PreparedStatement stm2 = this.getDbManager().getConnection().prepareStatement(queryPhotoNotices);
        
		UserDAOImpl userDAO = new UserDAOImpl(this.getDbManager());
		ReviewDAOImpl reviewDAO = new ReviewDAOImpl(this.getDbManager());
		PhotoDAOImpl photoDAO = new PhotoDAOImpl(this.getDbManager());
		
        try {
            stm.setInt(1, id);
            stm.setInt(2, MAX_RESULT);
            stm.setInt(3, MAX_RESULT*offset);
            stm2.setInt(1, id);
            stm2.setInt(2, MAX_RESULT);
            stm2.setInt(3, MAX_RESULT*offset);
            ResultSet reviewSet = stm.executeQuery();
            ResultSet photoSet = stm2.executeQuery();
            try {
                while(reviewSet.next()) {
                    ReviewNotice tmp = new ReviewNotice();
                    tmp.setId(reviewSet.getString("id"));
					tmp.setCreatedAt(new Date(reviewSet.getTimestamp("created_at").getTime()));
                    tmp.setRegisteredUserId(reviewSet.getString("registered_user_id"));
                    tmp.setReviewId(reviewSet.getString("review_id"));
					
					User tmpUser = userDAO.getUserById(tmp.getRegisteredUserId());
					tmp.setRegisteredUser(tmpUser);
					
					Review tmpReview = reviewDAO.getReviewById(tmp.getReviewId());
					tmp.setReview(tmpReview);
					
                    notices.add(tmp);
                }
                while(photoSet.next()) {
                    PhotoNotice tmp = new PhotoNotice();
                    tmp.setId(photoSet.getString("id"));
					tmp.setCreatedAt(new Date(photoSet.getTimestamp("created_at").getTime()));
                    tmp.setPhotoId(photoSet.getString("photo_id"));
                    tmp.setRegisteredUserId(photoSet.getString("registered_user_id"));
					
					User tmpUser = userDAO.getUserById(tmp.getRegisteredUserId());
					tmp.setRegisteredUser(tmpUser);
					
					Photo tmpPhoto = photoDAO.getPhotoById(tmp.getPhotoId());
					tmp.setPhoto(tmpPhoto);
					
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
        
        String query = "INSERT INTO photo_notices (registered_user_id, photo_id) VALUES(?, ?)";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, notice.getRegisteredUserId());
            stm.setInt(2, notice.getPhotoId());
			stm.executeUpdate();
        } finally {
            stm.close();
        }
    }

    @Override
    public void insertPhotoRemovalNotice(PhotoRemovalNotice notice) throws SQLException {
        
        // Check if valid
        if (!notice.validate()) return;
        
        String query = "INSERT INTO photo_removal_notices (registered_user_id, photo_id, approved) VALUES(?, ?, ?)";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, notice.getRegisteredUserId());
            stm.setInt(2, notice.getPhotoId());
            stm.setBoolean(3, false);
            stm.executeUpdate();
        } finally {
            stm.close();
        }
    }

    @Override
    public void insertReviewNotice(ReviewNotice notice) throws SQLException {
        
        String query = "INSERT INTO review_notices (review_id, registered_user_id) VALUES(?, ?);";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, notice.getReviewId());
            stm.setInt(2, notice.getRegisteredUserId());
            stm.executeUpdate();
        } finally {
            stm.close();
        }
    }

    @Override
    public void insertOwnershipNotice(OwnershipNotice notice) throws SQLException {
		String query = "INSERT INTO ownership_notices (registered_user_id, restaurant_id, company_name, vat_number, tax_code, contact_phone) VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt = this.getDbManager().getConnection().prepareStatement(query);
		try {
			stmt.setInt(1, notice.getRegisteredUserId());
			stmt.setInt(2, notice.getRestaurantId());
			stmt.setString(3, notice.getCompanyName());
			stmt.setString(4, notice.getVatNumber());
			stmt.setString(5, notice.getTaxCode());
			stmt.setString(6, notice.getContactPhone());
			stmt.execute();
		} finally {
			stmt.close();
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
		String query = "UPDATE ownership_notices SET approved = ? WHERE id = ? ";
		PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
		try {
			stm.setBoolean(1, approved);
			stm.setInt(2, id);
			stm.executeUpdate();
		} finally {
			stm.close();
		}
    }

	@Override
	public OwnershipNotice getOwnershipNoticeById(int id) throws Exception {
		OwnershipNotice notice = new OwnershipNotice();
		String query = "SELECT id, registered_user_id, restaurant_id, approved, company_name, vat_number, tax_code, contact_phone "
						+ "FROM ownership_notices "
						+ "WHERE id = ?";
		
		PreparedStatement stmt = this.getDbManager().getConnection().prepareStatement(query);
		try {
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			try {
				while (result.next()) {
					notice.setId(result.getInt("id"));
					notice.setRegisteredUserId(result.getInt("registered_user_id"));
					notice.setRestaurantId(result.getInt("restaurant_id"));
					notice.setApproved(result.getBoolean("approved"));
					notice.setCompanyName(result.getString("company_name"));
					notice.setVatNumber(result.getString("vat_number"));
					notice.setTaxCode(result.getString("tax_Code"));
					notice.setContactPhone(result.getString("contact_phone"));
				}
			} finally {
				result.close();
			}
		} finally {
			stmt.close();
		}
		
		return notice;
	}
    
}
