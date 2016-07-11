/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.beans;

import it.unitn.disi.webprog2016.convictor.framework.beans.AbstractBean;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author umberto
 */
public class User extends AbstractBean {
	
	private String email;
	private String password;
	private String passwordConfirmation;
	private String name;
	private String surname;
	private boolean admin;
	private List<Notice> notices;
	private List<Review> reviews;

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

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the full name
	 */
	public String getFullName() {
		return getName()+ " " +getSurname();
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(String admin) {
        try {
           setAdmin(Boolean.valueOf(admin)); 
        } catch (Exception e) {
            this.setError("admin", "Il valore di admin non è valido.");
        }
	}
	
    @Override
    public boolean validate() {
        
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,10}$";
        
        boolean status = true;
        if (this.getName().equals("")) {
            status = false;
            this.setError("name", "Surname is not valid");
        }
        if (this.getSurname().equals("")) {
            status = false;
            this.setError("surname", "Surname is not valid");
        }
        
        // Check if the email is not null and if it is a valid email.
        if (this.getEmail().equals("")) {
            status = false;
            this.setError("email", "Email is not valid");
        } else {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(this.getEmail());
            if (!matcher.matches()) {
                status = false;
                this.setError("email", "Email is not valid");
            }
        }
        
        // Check if the password is not null and if matches some strenght
        // rules, that are:
        // * 6-10 characters
        // * One lowercase letter
        // * One uppercase letter
        // * One numeric digit
        if (this.getPassword().equals("")) {
            status = false;
            this.setError("password", " Password must be at least 6 characters, no more than 10 characters, and must include at least one upper case letter, one lower case letter, and one numeric digit.");
        } else {
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(this.getPassword());
            if (!matcher.matches()) {
                status = false;
                this.setError("password", " Password must be at least 6 characters, no more than 10 characters, and must include at least one upper case letter, one lower case letter, and one numeric digit.");
            }
        }
		
		if(!this.getPassword().equals("")) {
			if(!this.getPassword().equals(this.getPasswordConfirmation())) {
				this.setError("passwordConfirmation", "La password non coincide con la conferma");
			}
		}
		
        return status;
    }

	/**
	 * @return the notices
	 */
	public List<Notice> getNotices() {
		return notices;
	}

	/**
	 * @param notices the notices to set
	 */
	public void setNotices(List<Notice> notices) {
		this.notices = notices;
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
}
