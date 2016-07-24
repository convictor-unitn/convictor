/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.utils;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Giovanni
 */
@Path("Items")
public class ItemsServiceResource {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of ItemsServiceResource
	 */
	public ItemsServiceResource() {
	}

	/**
	 * Retrieves representation of an instance of it.unitn.disi.webprog2016.convictor.framework.utils.ItemsServiceResource
	 * @return an instance of java.lang.String
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public String getJson() {
		//TODO return proper representation object
		throw new UnsupportedOperationException();
	}

	/**
	 * PUT method for updating or creating an instance of ItemsServiceResource
	 * @param content representation for the resource
	 */
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
	public void putJson(String content) {
	}
}
