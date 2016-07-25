/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.interfaces;

import it.unitn.disi.webprog2016.convictor.app.beans.Notice;
import it.unitn.disi.webprog2016.convictor.app.beans.OwnershipNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.PhotoNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.PhotoRemovalNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.ReviewNotice;
import java.sql.SQLException;
import java.util.List;

/**
 * Notice DAO
 * @author Giovanni De Toni
 */
public interface NoticeDAO {
    
	OwnershipNotice getOwnershipNoticeById(int id) throws Exception;
	
    List<Notice> getAdministratorNotices(int id, int offset) throws Exception;
    List<Notice> getRestaurantOwnerNotices(int id, int offset) throws Exception;
    
    void insertPhotoNotice(PhotoNotice notice) throws SQLException;
    void insertPhotoRemovalNotice(PhotoRemovalNotice notice)throws SQLException;
    void insertReviewNotice(ReviewNotice notice)throws SQLException;
    void insertOwnershipNotice(OwnershipNotice notice)throws SQLException;
    
    void approvePhotoRemovalNotice(boolean approved, int id) throws SQLException;
    void approveOwershipNotice(boolean approved, int id) throws SQLException;
	
}
