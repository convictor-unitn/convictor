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
	private User registeredUser;
	private Review review;

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
        try {
            this.registeredUserId = Integer.parseInt(registeredUserId);
        } catch (Exception e) {
            this.setRegisteredUserId(-1);
            this.setError("user_id", "L'id utente inserito non è valido");
        }
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
        try {
            this.reviewId = Integer.parseInt(reviewId);
        } catch (Exception e) {
            this.setReviewId(-1);
            this.setError("review_id", "L'id della recensione non è valido.");
        }
    }
    
    /**
     * Return the viewable message representing this notice.
     * @return A string message
     */
	@Override
	public String getDescription() {
		String userName = registeredUser.getName() + " " + registeredUser.getSurname();
        String reviewMessage ="<a href=\"/convictor/restaurants/showReview?id="+ review.getId() + "\"> recensione </a>";
        String message = userName + " ha inserito una nuova " + reviewMessage + "!";
        return message;
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
            this.setError("user_id", "Lo user_id è uguale o minore di zero");
        }
        if (this.getReviewId() <=0) {
            status = false;
            this.setError("review_id", "Lo review_id è uguale o minore di zero");
        }
        return status;
    }

	public User getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(User registeredUser) {
		this.registeredUser = registeredUser;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}
	
}
