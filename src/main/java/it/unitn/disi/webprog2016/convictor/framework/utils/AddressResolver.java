/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 *
 * @author Giovanni
 */
public class AddressResolver {
		
	private final String API_KEY;
	private String zipcode;
	private ArrayList<String> city;
	private ArrayList<String> street;
	private ArrayList<String> state;
	private String address;
	private double latitude;
	private double longitude;
	
	final int MAX_DIM = 10;
	
	
	
	public AddressResolver () {
		this.API_KEY = "AIzaSyBbiud33G2KsodO5JvP-5HQzoSTuWiI0a8";
		this.street = new ArrayList<String>();
		this.city = new ArrayList<String>();
		this.state = new ArrayList<String>();
		
	}

	
	private void composeAddress () {
		String comma = ",";
		String plus = "+";
		
		String component = "&components=country:IT";
		
		this.address = this.zipcode+plus;
		
		for (int i = 0; i < street.size(); i++) {
			this.address += street.get(i);
			// Prevent to add another '+' next to the last word
			if (i < street.size() -1 )
				this.address+=plus;
		}
		
		this.address+=comma;
		
		for (int i = 0; i < city.size(); i++) {
			this.address+=city.get(i);
			if (i < city.size() -1 )
				this.address+=plus;
		}
		this.address+=comma;
		for (int i = 0; i < state.size(); i++) {
			this.address+=state.get(i);
			if (i < state.size() -1 )
				this.address+=plus;
		}
		
		this.address+=component;
		
	}
	
	public void resolveAddress() throws IOException {
		this.composeAddress();
		System.out.println(this.address);

		String recv;
		String recvbuff = null;
		URL jsonpage = new URL("https://maps.googleapis.com/maps/api/geocode/json?"+this.address+"&key="+this.API_KEY);
		System.out.println(jsonpage);
		URLConnection urlcon = jsonpage.openConnection();
		try (BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()))) {
			while ((recv = buffread.readLine()) != null)
				recvbuff += recv;
		} catch (Exception e){
			
		}

		System.out.println(recvbuff);

	}
	
	public double getLatitude () {		
		return this.latitude;
	}
	
	public double getLongitude () {		
		return this.longitude;
	}	
	
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = Integer.toString(zipcode);
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		
		String[] tmp; 
		tmp = city.split(" ");
		
		for (int i = 0; i < tmp.length; i++) {
			this.city.add(i, tmp[i]);
		}
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		String[] tmp; 
		tmp = street.split(" ");
		
		
		
		for (int i = 0; i < tmp.length; i++) {
			this.street.add(i, tmp[i]);
		}
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		String[] tmp; 
		tmp = state.split(" ");
		
		for (int i = 0; i < tmp.length; i++) {
			this.state.add(i, tmp[i]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		System.out.println();

		
		AddressResolver ad = new AddressResolver();
		ad.setZipcode(36043);
		ad.setStreet("via san michele");
		ad.setCity("Malo");
		ad.setState("IT");
		ad.resolveAddress();
		
		System.out.print(ad.getLatitude()+" "+ad.getLongitude());
		
		
	}
	
	
}
