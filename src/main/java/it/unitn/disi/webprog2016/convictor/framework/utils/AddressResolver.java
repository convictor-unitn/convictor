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
	private ArrayList<String> address;
	private double latitude;
	private double longitude;
	
	final int MAX_DIM = 10;
	
	
	
	public AddressResolver () {
		this.API_KEY = "AIzaSyBbiud33G2KsodO5JvP-5HQzoSTuWiI0a8";
		this.street = new ArrayList<String>();
		this.city = new ArrayList<String>();
		this.state = new ArrayList<String>();
		this.address = new ArrayList<String>();
	}

	
	private void composeAddress () {
		String comma = ",";
		String plus = "+";
//		this.address=this.zipcode+comma;
//		for (int i = 0; i < street.length; i++) {
//			this.address.
//		}
//		this.address+=comma;
//		for (int i = 0; i < city.length; i++) {
//			this.address+=city[i];
//		}
//		this.address+=comma;
//		for (int i = 0; i < state.length; i++) {
//			this.address+=state[i];
//		}
	}
	
	public void resolveAddress() throws IOException {
		this.composeAddress();
		System.out.println(this.address);

		String recv;
		String recvbuff = null;
		URL jsonpage = new URL("https://maps.googleapis.com/maps/api/geocode/json?+"+this.address+"&key="+this.API_KEY);
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
//		this.zipcode = zipcode;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		
		StringTokenizer st = new StringTokenizer(city," ",true);
		
		for (int i = 0; i < st.countTokens(); i++) {
//			this.city[i] = (String)st.nextElement();
		}
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		StringTokenizer st = new StringTokenizer(street," ",true);
		for (int i = 0; i < st.countTokens(); i++) {
//			this.street[i] = (String)st.nextElement();
		}
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		StringTokenizer st = new StringTokenizer(state," ",true);
		
		for (int i = 0; i < st.countTokens(); i++) {
//			this.state[i] = (String)st.nextElement();
		}
	}
	
}
