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
    public List<Notice> getAdministratorNotices(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notice> getRestaurantOwnerNotices(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertPhotoNotice(PhotoNotice notice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertPhotoRemovalNotice(PhotoRemovalNotice notice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertReviewNotice(ReviewNotice notice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertOwnershipNotice(OwnershipNotice notice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void approvePhotoRemovalNotice(boolean approved) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void approveOwershipNotice(boolean approved) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
