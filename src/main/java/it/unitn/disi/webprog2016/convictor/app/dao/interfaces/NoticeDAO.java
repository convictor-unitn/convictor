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
import java.util.List;

/**
 * Notice DAO
 * @author Giovanni De Toni
 */
public interface NoticeDAO {
    
    List<Notice> getAdministratorNotices(int id);
    List<Notice> getRestaurantOwnerNotices(int id);
    
    void insertPhotoNotice(PhotoNotice notice);
    void insertPhotoRemovalNotice(PhotoRemovalNotice notice);
    void insertReviewNotice(ReviewNotice notice);
    void insertOwnershipNotice(OwnershipNotice notice);
    
    void approvePhotoRemovalNotice(boolean approved);
    void approveOwershipNotice(boolean approved);
    
}
