/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.utils;

import java.util.Objects;

/**
 * This route represents an id for a route.
 * @author umberto
 */
public class RouteId {
	private final String url;
	private final String method;

	/**
	 * Gets a route Id composed by url and method
	 * @param url
	 * @param method 
	 */
	public RouteId(String url, String method) {
		this.url = url;
		this.method = method;
	}

	@Override
	public String toString() {
		return "RouteId{" + "url=" + url + ", method=" + method + '}';
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 89 * hash + Objects.hashCode(this.url);
		hash = 89 * hash + Objects.hashCode(this.method);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RouteId other = (RouteId) obj;
		if (!Objects.equals(this.url, other.url)) {
			return false;
		}
		if (!Objects.equals(this.method, other.method)) {
			return false;
		}
		return true;
	}
	
	
}
