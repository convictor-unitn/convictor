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
import java.util.logging.Logger;

/**
 *
 * @author Giovanni
 */
public class AddressResolver {
		
	private final String API_KEY;
	private String address;
	private double latitude;
	private double longitude;
	
	
	public AddressResolver (){this.API_KEY = "AIzaSyBbiud33G2KsodO5JvP-5HQzoSTuWiI0a8";
};
	
	public void setAddress (String address) {
		this.address = address;			
	}
	
	public double getLatitude (double latitude) {
		double result = 0.0;
		
		
		
		return latitude;
	}
	
	public double getLongitude (double longitude) {
		double result = 0.0;
		
		return longitude;
	}
	
	private void resolveAddress() throws IOException {
		String recv;
		String recvbuff = null;
		URL jsonpage = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+this.address+"&key="+this.API_KEY);
		URLConnection urlcon = jsonpage.openConnection();
		try (BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()))) {
			while ((recv = buffread.readLine()) != null)
				recvbuff += recv;
		} catch (Exception e){
			
		}

		System.out.println(recvbuff);
		
		
		
	}
	
}
