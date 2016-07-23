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
import java.util.Scanner;
import org.json.*;
		
/**
 * Utility class to resolve a restaurant address into 
 * LATITUDE and LONGITUDE coordinates
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
	
	private final int MAX_DIM = 10;
	private final String COUNTRY = "IT";
	
	
	public AddressResolver () {
//		this.API_KEY = "AIzaSyBbiud33G2KsodO5JvP-5HQzoSTuWiI0a8";
		this.API_KEY = "AIzaSyCMJtaVznqkvqvaR1GFfLXkZHyzd4zfbXk";
		this.street = new ArrayList<String>();
		this.city = new ArrayList<String>();
		this.state = new ArrayList<String>();
		
	}

	
	private void composeAddress () {
		String comma = ",";
		String plus = "+";
		
		String component = "&components=country:"+COUNTRY;
		
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
				
		// build a URL
		String s = "https://maps.googleapis.com/maps/api/geocode/json?"+this.address+"&key="+this.API_KEY;
		URL url = new URL(s);

		// read from the URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		// build a JSON object
		JSONObject obj = new JSONObject(str);
		if (! obj.getString("status").equals("OK"))
			return;

		// get the first result
		JSONObject res = obj.getJSONArray("results").getJSONObject(0);
		JSONObject loc = res.getJSONObject("geometry").getJSONObject("location");
		
		this.latitude = loc.getDouble("lat");
		this.longitude = loc.getDouble("lng");
		
//		System.out.println("lat: " + loc.getDouble("lat") + ", lng: " + loc.getDouble("lng"));
		

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
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	
	// Run this file to test the results
	public static void main(String[] args) throws IOException {
		
		System.out.println();

		
		AddressResolver ad = new AddressResolver();
		ad.setZipcode("36043");
		ad.setStreet("via san michele");
		ad.setCity("Malo");
		ad.setState("IT");
		ad.resolveAddress();
		
		System.out.print(ad.getLatitude()+" "+ad.getLongitude());
		
		
	}
	
	
}
