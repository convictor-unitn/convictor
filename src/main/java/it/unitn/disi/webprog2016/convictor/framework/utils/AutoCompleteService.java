package it.unitn.disi.webprog2016.convictor.framework.utils;

import com.google.gson.Gson;
import it.unitn.disi.webprog2016.convictor.app.dao.implementation.RestaurantDAOImpl;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.RestaurantDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Stefano
 */
@Path("languages")
public class AutoCompleteService {

    private List<String> RESTAURANTS = new ArrayList<>();

    @Context
    private UriInfo context;

    @Context
    private HttpServletRequest request;

    /**
     * Creates a new instance of AutoCompleteService
     */
    public AutoCompleteService() {
		
		RestaurantDAO restDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
		

    }

    /**
     * Retrieves representation of an instance of
     * it.unitn.science.computerScience.webDeveloping.exercise1.services.AutoCompleteService
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getLanguages() {
        Gson gson = new Gson();
        final String term = request.getParameter("term");

        final List<String> results = new ArrayList<>();
        if (term == null) {
            return gson.toJson(RESTAURANTS);
        } else {
            RESTAURANTS.stream().filter((language) -> (language.toLowerCase().contains(term.toLowerCase()))).forEach((_item) -> {
                results.add(_item);
            });
        }

        return gson.toJson(results);
    }

    /**
     * Retrieves representation of an instance of
     * it.unitn.science.computerScience.webDeveloping.exercise1.services.AutoCompleteService
     *
     * @param term
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{term}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getLanguages(@PathParam("term") String term) {

        List<String> results = new ArrayList<>();
        RESTAURANTS.stream().filter((language) -> (language.toLowerCase().contains(term.toLowerCase()))).forEach((_item) -> {
            results.add(_item);
        });
        Gson gson = new Gson();
        return gson.toJson(results);
    }
}
