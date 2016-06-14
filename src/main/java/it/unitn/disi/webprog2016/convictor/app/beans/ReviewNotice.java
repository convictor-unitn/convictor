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
public class ReviewNotice extends AbstractBean {
    
    private Integer registeredUserId;
    private Integer reviewId;

    /**
     * @return the registeredUserId
     */
    public Integer getRegisteredUserId() {
        return registeredUserId;
    }

    /**
     * @param registeredUserId the registeredUserId to set
     */
    public void setRegisteredUserId(Integer registeredUserId) {
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
    public Integer getReviewId() {
        return reviewId;
    }

    /**
     * @param reviewId the reviewId to set
     */
    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }
    
     /**
     * Overloading to cast a string to an integer
     * @param reviewId the reviewId to set
     */
    public void setReviewId(String reviewId) {
        this.reviewId = Integer.parseInt(reviewId);
    }
    
    
    
}
