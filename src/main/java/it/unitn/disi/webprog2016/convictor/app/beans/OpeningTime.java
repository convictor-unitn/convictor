/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;

import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umberto
 */
public class OpeningTime extends AbstractBean {
	private int restaurantId;
    private int day;
    private String dayString;
	private Date openAt;
	private Date closeAt;
    private Date openAtAfternoon;
    private Date closeAtAfternoon;
    private boolean dayoff;

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
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(String restaurantId) {
		try {
            this.setRestaurantId(Integer.parseInt(restaurantId));
        } catch (Exception e) {
            this.setError("restaurant_id", "L'id del ristorante non è valido.");
            this.setRestaurantId(-1);
        }
	}

	/**
	 * @return the openAt
	 */
	public Date getOpenAt() {
		return openAt;
	}

	/**
	 * @param openAt the openAt to set
	 */
	public void setOpenAt(Date openAt) {
		this.openAt = openAt;
	}
	
	/**
	 * @param openAt the openAt to set
	 */
	public void setOpenAt(String openAt) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		try {
			setOpenAt(df.parse(openAt));
		} catch (ParseException ex) {
			// TODO: quando si farà la validazione dei beans inserire l'errore sulla data non valida
			Logger.getLogger(OpeningTime.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * @return the closeAt
	 */
	public Date getCloseAt() {
		return closeAt;
	}

	/**
	 * @param closeAt the closeAt to set
	 */
	public void setCloseAt(Date closeAt) {
		this.closeAt = closeAt;
	}
	
	/**
	 * @param closeAt the closeAt to set
	 */
	public void setCloseAt(String closeAt) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		try {
			setCloseAt(df.parse(closeAt));
		} catch (ParseException ex) {
			// TODO: quando si farà la validazione dei beans inserire l'errore sulla data non valida
			Logger.getLogger(OpeningTime.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
    
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    
    public String getDayString() {
        return dayString;
    }
    
    public void setDayString(int day) {
        switch(day) {
            case 1:
                dayString = "Lunedì";
                break;
            case 2:
                dayString = "Martedì";
                break;
            case 3:
                dayString = "Mercoledì";
                break;
            case 4:
                dayString = "Giovedì";
                break;
            case 5:
                dayString = "Venerdì";
                break;
            case 6:
                dayString = "Sabato";
                break;
            case 7:
                dayString = "Domenica";
                break;
            default:
                break;
        }
    }

    /**
     * @return the openAtAfternoon
     */
    public Date getOpenAtAfternoon() {
        return openAtAfternoon;
    }

    /**
     * @param openAtAfternoon the openAtAfternoon to set
     */
    public void setOpenAtAfternoon(Date openAtAfternoon) {
        this.openAtAfternoon = openAtAfternoon;
    }
    
    public void setOpenAtAfternoon(String openAt) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		try {
			setOpenAtAfternoon(df.parse(openAt));
		} catch (ParseException ex) {
			// TODO: quando si farà la validazione dei beans inserire l'errore sulla data non valida
			Logger.getLogger(OpeningTime.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

    /**
     * @return the closeAtAfternoon
     */
    public Date getCloseAtAfternoon() {
        return closeAtAfternoon;
    }

    /**
     * @param closeAtAfternoon the closeAtAfternoon to set
     */
    public void setCloseAtAfternoon(Date closeAtAfternoon) {
        this.closeAtAfternoon = closeAtAfternoon;
    }
    
     public void setCloseAtAfternoon(String closeAtAfternoon) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		/*try {
			setCloseAtAfternoon(df.parse(closeAtAfternoon));
		} catch (ParseException ex) {
			// TODO: quando si farà la validazione dei beans inserire l'errore sulla data non valida
			Logger.getLogger(OpeningTime.class.getName()).log(Level.SEVERE, null, ex);
		}*/
	}

    /**
     * @return the dayoff
     */
    public boolean isDayoff() {
        return dayoff;
    }

    /**
     * @param dayoff the dayoff to set
     */
    public void setDayoff(boolean dayoff) {
        this.dayoff = dayoff;
    }
	
	@Override
    public boolean validate() {
        boolean status = true;
        
        if (this.getCloseAt() == null) {
            status = false;
            this.setError("close_at", "The close_at date is not valid!");
        }
        if (this.getOpenAt() == null) {
            status = false;
            this.setError("open_at", "The open_at date is not valid!");
        }
        if (this.getCloseAtAfternoon() == null) {
            status = false;
            this.setError("open_at", "The open_at date is not valid!");
        }
        if (this.getOpenAtAfternoon()== null) {
            status = false;
            this.setError("open_at", "The open_at date is not valid!");
        }
        return status;
    }

    public void setDay(String day) {
        switch(day){
            case "monday":
                this.setDay(1);
                break;
            case "tuesday":
                this.setDay(2);
                break;
            case "wednesday":
                this.setDay(3);
                break;
            case "thursday":
                this.setDay(4);
                break;
            case "friday":
                this.setDay(5);
                break;
            case "saturday":
                this.setDay(6);
                break;
            case "sunday":
                this.setDay(7);
                break;
            default:
                this.setDay(1);
                break;
        }
    }
}
