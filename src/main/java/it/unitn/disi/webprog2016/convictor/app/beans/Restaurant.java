/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;
import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;
import java.util.List;

/**
 * Restaurant bean class. 
 * @author Giovanni De Toni
 */
public class Restaurant extends AbstractBean {
    
    private String name;
    private String description;
    private String street;
    private String city;
    private String zipCode;
    private String province;
    private String fullAddress;
    private String website;
    private String phone;
    private String email;
    private int slotPrice;
    private int rating;
    private int mainPhotoId;
    private int restaurantOwnerId;
	private List<Cusine> cusine;
	private List<Review> reviews;
    private List<OpeningTime> openingTimes;
	private List<Notice> notices;
	private List<Photo> photos;
	private RestaurantOwner owner;
	private double lat;
	private double lng;
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the fullAddress
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * @param fullAddress the fullAddress to set
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the slotPrice
     */
    public int getSlotPrice() {
        return slotPrice;
    }

    /**
     * @param slotPrice the slotPrice to set
     */
    public void setSlotPrice(int slotPrice) {
        this.slotPrice = slotPrice;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param slotPrice 
     */
    public void setSlotPrice(String slotPrice) {
        try {
            this.slotPrice = Integer.parseInt(slotPrice);
        } catch (Exception e) {
            this.setSlotPrice(-1);
            this.setError("slotPrice", "Non è stata scelta la fascia di prezzo");
        }
    } 

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param rating the rating to set
     */
    public void setRating(String rating) {
		try {
			this.rating = Integer.parseInt(rating);
		}
		catch(NumberFormatException e) {
			this.rating = -1;
            this.setError("slotPrice", "Lo slot price non è valido");
		}
        
    }


    /**
     * @return the mainPhotoId
     */
    public int getMainPhotoId() {
        return mainPhotoId;
    }

    /**
     * @param mainPhotoId the mainPhotoId to set
     */
    public void setMainPhotoId(int mainPhotoId) {
        this.mainPhotoId = mainPhotoId;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param mainPhotoId the mainPhotoId to set
     */
    public void setMainPhotoId(String mainPhotoId) {
        try {
            this.setMainPhotoId(Integer.parseInt(mainPhotoId));
        } catch (Exception e) {
            this.setMainPhotoId(-1);
            this.setError("photo_id", "L'id della foto principale non è valido");
        }
    }

    /**
     * @return the restaurantOwnerId
     */
    public int getRestaurantOwnerId() {
        return restaurantOwnerId;
    }

    /**
     * @param restaurantOwnerId the restaurantOwnerId to set
     */
    public void setRestaurantOwnerId(int restaurantOwnerId) {
        this.restaurantOwnerId = restaurantOwnerId;
    }
    
    /**
     * Overloading to cast a string value to an integer
     * @param restaurantOwnerId the restaurantOwnerId to set
     */
    public void setRestaurantOwnerId(String restaurantOwnerId) {
        try {
            this.setRestaurantOwnerId(Integer.parseInt(restaurantOwnerId));
        } catch (Exception e) {
            this.setRestaurantOwnerId(-1);
            this.setError("restaurant_owner_id", "L'id del proprietario non è valido");
        }
    }
    
    
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean validate() {
        boolean status = true;
        
        
        if (this.getName().equals("")) {
            status=false;
            this.setError("name", "Il nome del ristorante non è valido");
        }
        
        if (this.getDescription().equals("")) {
            status = false;
            this.setError("description", "La descrizione del ristorante non è valida");
        }
        
        if (this.getStreet().equals("")) {
            status = false;
            this.setError("street", "La via inserita non è valida");
        }
        
        if (this.getCity().equals("")) {
            status = false;
            this.setError("city", "La città inserita non è valida");
        }
        
        if (this.getZipCode().equals("")) {
            status = false;
            this.setError("zip", "Il codice postale inserito non è valido");
        }
        
        if (this.getProvince().equals("")) {
            status = false;
            this.setError("province", "La provincia inserita non è valida");
        } 
        
        if (this.getSlotPrice() == 0) {
            status = false;
            this.setError("slotPrice", "La fascia di prezzo selezionata non è valida");
        }
        
        if (this.getEmail().equals("")) {
            status = false;
            this.setError("email", "L'email inserita non è valida");
        }
        
        if (this.getPhone().equals("")) {
            status = false;
            this.setError("phone", "Il numero di telefono inserito non è valido");
        }
        
        if (this.getCusine().isEmpty()) {
            status = false;
            this.setError("cusines", "Non sono state scelte le tipologie di cucina");
        }
        
        if (this.getOpeningTimes().isEmpty() && this.getOpeningTimes().size() < 7) {
            status = false;
            this.setError("openingTimes", "Non sono stati inseriti gli orari del ristorante");
        }
        
        return status;
    }

    /**
     * @return the cusine
     */
    public List<Cusine> getCusine() {
        return cusine;
    }

    /**
     * @param cusine the cusine to set
     */
    public void setCusine(List<Cusine> cusine) {
        this.cusine = cusine;
    }

    /**
     * @return the reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the openingTimes
     */
    public List<OpeningTime> getOpeningTimes() {
        return openingTimes;
    }

    /**
     * @param openingTimes the openingTimes to set
     */
    public void setOpeningTimes(List<OpeningTime> openingTimes) {
        this.openingTimes = openingTimes;
    }

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public void setLat(String lat) {
		try {
			setLat(Double.parseDouble(lat));
		}
		catch(NumberFormatException e) {
			this.setError("lat", "Latitudine non valida. Deve essere un numero");
		}
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public void setLng(String lng) {
		try {
			setLng(Double.parseDouble(lng));
		}
		catch(NumberFormatException e) {
			this.setError("lng", "Longitudine non valida. Deve essere un numero");
		}
	}
    
    
    
}
