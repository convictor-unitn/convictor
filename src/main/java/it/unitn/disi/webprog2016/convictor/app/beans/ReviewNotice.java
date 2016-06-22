/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;
import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;

/**
 * Review Notice bean.
 * @author Giovanni De Toni
 */
public class ReviewNotice extends AbstractBean implements Notice {
    
    private int registeredUserId;
    private int reviewId;

    /**
     * @return the registeredUserId
     */
    public int getRegisteredUserId() {
        return registeredUserId;
    }

    /**
     * @param registeredUserId the registeredUserId to set
     */
    public void setRegisteredUserId(int registeredUserId) {
        this.registeredUserId = registeredUserId;
    }
    
    /**
     * Overloading to cast a string to an integer
     * @param registeredUserId the registeredUserId to set
     */
    public void setRegisteredUserId(String registeredUserId) {
        this.registeredUserId = Integer.parseInt(registeredUserId);
    }

    /**
     * @return the reviewId
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * @param reviewId the reviewId to set
     */
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    
     /**
     * Overloading to cast a string to an integer
     * @param reviewId the reviewId to set
     */
    public void setReviewId(String reviewId) {
		this.reviewId = Integer.parseInt(reviewId);
    }

	@Override
	public String getDescription() {
		//TODO: Implementare la stringa della notifica
		return "DA IMPLEMENTARE!!!";
	}

	@Override
	public String getNoticeType() {
		return this.getClass().toString();
	}

    @Override
    public boolean validate() {
        boolean status=true;
        if (this.getRegisteredUserId() <= 0) {
            status = false;
            this.setError("user_id", "The user_id is equal or lessa than zero");
        }
        if (this.getReviewId() <=0) {
            status = false;
            this.setError("review_id", "The review_id is equal or less than zero");
        }
        return status;
    }
	
}
