/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;
import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;

/**
 * Ownership Notice bean.
 * @author Giovanni De Toni
 */
public class OwnershipNotice extends AbstractBean implements Notice, Approvable {
    
    private int registeredUserId;
    private int restaurantId;
	private boolean approved;
	private User registeredUser;
	private Restaurant restaurant;
	private String companyName;
	private String vatNumber;
	private String taxCode;
	private String contactPhone;

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
     * @return the restaurantId
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * @param restaurantId the restaurantId to set
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    /**
     * Overloading to cast a string to an integer
     * @param restaurantId the restaurantId to set
     */
    public void setRestaurantId(String restaurantId) {
        try {
            this.setRestaurantId(Integer.parseInt(restaurantId));
        } catch (Exception e) {
            this.setError("restaurant_id", "L'id del ristorante non è valido");
            this.setRestaurantId(-1);
        }
    }
    
    public User getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(User registeredUser) {
		this.registeredUser = registeredUser;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
    /**
     * Return the viewable message representing this notice.
     * @return A string message
     */
	@Override
	public String getDescription() {
		String userName = registeredUser.getName() + " " + registeredUser.getSurname();
        String restaurantProfile ="<a href=\"/restaurants/show?id="+restaurantId + "\">"+restaurantId+"</a>";
        String message = userName + " ha richiesto l'assegnazione del ristorante " + restaurantProfile;
        return message;
    }

	@Override
	public String getNoticeType() {
		return this.getClass().toString();
	}

	@Override
	public void approve() {
		this.setApproved(true);
	}

	@Override
	public void decline() {
		this.setApproved(false);
	}

	@Override
	public boolean getApproved() {
		return this.isApproved();
	}
    
    @Override
    public boolean validate() {
        boolean status=true;
        if (this.getRegisteredUserId() <= 0) {
            status = false;
            this.setError("user_id","L'id utente è minore o uguale a zero");
        }
        if (this.getRestaurantId() <= 0) {
            status = false;
            this.setError("restaurant_id","L'id del ristorante è minore o uguale a zero");
        }
		if (this.getCompanyName().equals("") || this.getCompanyName() == null ) {
            status = false;
            this.setError("company_name","Il nome azienda non può essere vuoto");
        }
		if (this.getVatNumber().equals("") || this.getVatNumber() == null ) {
            status = false;
            this.setError("vat_number","La partita IVA non può essere vuota");
        }
		if (this.getTaxCode().equals("") || this.getTaxCode() == null ) {
            status = false;
            this.setError("tax_code","Il codice fiscale non può essere vuoto");
        }
        return status;
    }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	
}
