/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import it.unitn.disi.webprog2016.convictor.app.beans.Cusine;
import it.unitn.disi.webprog2016.convictor.app.beans.OpeningTime;
import it.unitn.disi.webprog2016.convictor.app.beans.PriceSlot;
import it.unitn.disi.webprog2016.convictor.app.beans.Restaurant;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.CusineDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.CusinesRestaurantDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.OpeningTimesDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.PriceSlotDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.RestaurantDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.ReviewDAO;
import it.unitn.disi.webprog2016.convictor.framework.controllers.AbstractController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * In this controller there add all restaurant management pages
 * @author umberto
 */
public class RestaurantsController extends AbstractController {
   
    /**
     * Index method, it handles the search action to find restaurants given
     * a search query.
     * @param request Object representing the request made
     * @param response Object representing the response that will be sent to
     * the client
     * @return A string representing the view and it sets a "results" var that
     * can be used inside a JSP. 
     * @throws IOException
     * @throws ServletException 
     */
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
        // Set the query
        String query = request.getParameter("query");
        if (query==null) {
            query = "";
        }
        
        // Set the page index (pagination)
        int page=0;
        if (request.getParameter("page") != null ){
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        // Set the type of sorting
        String sortMethod = request.getParameter("sorting");
        if (sortMethod == null) {
            sortMethod = "";
        }
        
        // Retrive restaurants from the database given the query string
        RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
        try {
            List<Restaurant> tmp;
            switch(sortMethod) {
                case "nameSorting":
                    tmp = restaurantDAO.getRestaurantByStringOrderByName(query, page);
                    break;
                case "priceAscSorting":
                    tmp = restaurantDAO.getRestauranyByStringOrderByPrice(query, page, 1);
                    break;
                case "priceDescSorting":
                    tmp = restaurantDAO.getRestauranyByStringOrderByPrice(query, page, 0);
                    break;
                default:
                    tmp = restaurantDAO.getRestaurantByString(query, page);
            }
             if (tmp != null) {
                request.setAttribute("results", tmp);
            } else {
                request.setAttribute("results", new ArrayList<>());
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        } 
		
		CusineDAO cusineDAO = (CusineDAO) request.getServletContext().getAttribute("cusinedao");
		try {
			List<Cusine> allCusines = cusineDAO.getAllCusines();
			request.setAttribute("allCusines", allCusines);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
		}
        
        // Set the search string as page attribute
        request.setAttribute("queryString", query);
        
        return "/restaurants/index";
	}
    
    /**
     * Show method, it show the restaurant page given its id.
     * @param request Object representing the request made
     * @param response Object representing the response that will be sent to
     * the client
     * @return A string representing the view and it sets a "restaurant" variable  
     * that can be used inside a JSP. 
     * @throws IOException
     * @throws ServletException
     */
    public String show(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        int reviewPage = 0;        
        if (request.getParameter("reviewPage") != null) {
            reviewPage = Integer.parseInt(request.getParameter("reviewPage"));
        }
        
        // Retrive the restaurant from the database given its id 
        RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
        ReviewDAO reviewDAO = (ReviewDAO) request.getServletContext().getAttribute("reviewdao");
        CusinesRestaurantDAO cusinesRestaurantDAO = (CusinesRestaurantDAO) request.getServletContext().getAttribute("cusinesrestaurantdao");
        OpeningTimesDAO openingTimeDAO = (OpeningTimesDAO) request.getServletContext().getAttribute("openingtimesdao");
        try {
            
            Restaurant tmp = restaurantDAO.getRestaurantById(id);
            if (tmp != null) {
                tmp.setCusine(cusinesRestaurantDAO.getCusinesByRestaurantId(id));
                tmp.setReviews(reviewDAO.getRestaurantReviews(id, reviewPage));
                tmp.setOpeningTimes(openingTimeDAO.getResaurantOpeningTimes(id));
                request.setAttribute("restaurant", tmp);
            } else {
                response.sendError(404);
                return "";
            }
            
            return "/restaurants/show";
            
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }
	}
    
    /**
     * New method, it show the page used to insert a new restaurant.
     * @param request Object representing the request made
     * @param response Object representing the response that will be sent to
     * the client
     * @return A string representing the view and it sets an "allCusines" variable  
     * and an "allPriceSlot" variable that can be used inside a JSP. 
     * @throws IOException
     * @throws ServletException
     */
    public String new_(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
        // Get all cusines available to make them available inside the JSP
        CusineDAO cusineDAO = (CusineDAO) request.getServletContext().getAttribute("cusinedao");
		try {
			List<Cusine> allCusines = cusineDAO.getAllCusines();
			request.setAttribute("allCusines", allCusines);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
        // Get all price slots available to make them available inside the JSP
		PriceSlotDAO priceSlotDAO = (PriceSlotDAO) request.getServletContext().getAttribute("priceslotdao");
		try {
			List<PriceSlot> allPriceSlot = priceSlotDAO.getAllPriceSlots();
			request.setAttribute("allPriceSlot", allPriceSlot);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
        return "/restaurants/new";
	}
    
    /**
     * Create method, it create the Restaurant object that will be saved, if valid,
     * inside the database.
     * @param request Object representing the request made
     * @param response Object representing the response that will be sent to
     * the client
     * @return A string representing the view and it sets an "allCusines" variable  
     * and an "allPriceSlot" variable that can be used inside a JSP. 
     * @throws IOException
     * @throws ServletException
     */
    public String create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
        Restaurant tmp = new Restaurant();
		CusineDAO cusineDAO = (CusineDAO) request.getServletContext().getAttribute("cusinedao");
		List<Cusine> allCusines=null;
		try {
			allCusines = cusineDAO.getAllCusines();
			request.setAttribute("allCusines", allCusines);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
		}
        
        tmp.setName(request.getParameter("name"));
        tmp.setCity(request.getParameter("city"));
        tmp.setStreet(request.getParameter("street"));
        tmp.setZipCode(request.getParameter("zipcode"));
        tmp.setProvince(request.getParameter("province"));
        tmp.setDescription(request.getParameter("description"));
        tmp.setPhone(request.getParameter("phone"));
        tmp.setEmail(request.getParameter("email"));
        tmp.setWebsite(request.getParameter("website"));
		tmp.setSlotPrice(request.getParameter("priceslotselected"));
        
        String[] cusines = request.getParameterValues("cusines");
        List<Cusine> list = new ArrayList<>();
        List<OpeningTime> listTime = new ArrayList<>();
       
        try {
            for (String name : cusines) {
				if(allCusines==null) break;
                for(Cusine c : allCusines) {
					if(c.getId()==Integer.parseInt(name)) {
						list.add(c);
					}
				}               
            }
        } catch (Exception e) {
            // If somebody doesn't insert cusines, List<Cusine> will
            // be inserted empty. The validate procedure will discover
            // the error. 
            // This catch exists only to ensure that the exception doesn't
            // kill the request.
        }
        tmp.setCusine(list);        
        
        // Set the opening times
        String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        for (String day : days) {
            OpeningTime tmpTime = new OpeningTime();
            tmpTime.setDay(day);
            if (request.getParameter("dayoff_"+day) != null) {
                tmpTime.setDayoff(true);
            } else {
                if (!"".equals(request.getParameter("open_at_"+day)) &&
                    !"".equals(request.getParameter("close_at_"+day)) &&  
                    !"".equals(request.getParameter("open_at_afternoon_"+day)) &&
                    !"".equals(request.getParameter("close_at_afternoon_"+day)) 
                    ) {
                    Logger.getLogger("TEST").log(Level.SEVERE,request.getParameter("close_at_afternoon_"+day));
                    tmpTime.setOpenAt(request.getParameter("open_at_"+day));
                    tmpTime.setCloseAt(request.getParameter("close_at_"+day));
                    tmpTime.setOpenAtAfternoon(request.getParameter("open_at_afternoon_"+day));
                    tmpTime.setCloseAtAfternoon(request.getParameter("close_at_afternoon_"+day));
                    tmpTime.setDayoff(false);
                    if (tmpTime.validate()) {
                        listTime.add(tmpTime);
                    }
                }
            }         
        }
        tmp.setOpeningTimes(listTime);
        
        tmp.validate();
        try {       
            if (tmp.isValid()) {
                int id = ((RestaurantDAO) request.getServletContext().getAttribute("restaurantdao")).insertRestaurant(tmp);
                ((CusinesRestaurantDAO)request.getServletContext().getAttribute("cusinesrestaurantdao")).insertRestaurantCusines(id, list);
                ((OpeningTimesDAO) request.getServletContext().getAttribute("openingtimesdao")).insertRestaurantOpeningTimes(id, listTime);
                response.sendRedirect(request.getContextPath()+"/restaurants/show?id="+id);
				return "";
            }
			else
			{
                // So we can give to the user the same page, with already datas
                // filled and also the errors made. 
				request.setAttribute("restaurant", tmp);
			}
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }
        return "/restaurants/new";
	}
    
    /**
     * Edit method, it show the page used to edit a restaurant.
     * @param request Object representing the request made
     * @param response Object representing the response that will be sent to
     * the client
     * @return A string representing the view and it sets an "allCusines" variable,  
     * an "allPriceSlot" variable and a "restaurant" variable that can be 
     * used inside a JSP.
     * @throws IOException
     * @throws ServletException
     */
    public String edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {        
        
        RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
        CusinesRestaurantDAO cusinesRestaurantDAO = (CusinesRestaurantDAO) request.getServletContext().getAttribute("cusinesrestaurantdao");
        OpeningTimesDAO openingTimeDAO = (OpeningTimesDAO) request.getServletContext().getAttribute("openingtimesdao");
        
        int id = Integer.parseInt(request.getParameter("id"));
        
		//Retrieve cusines list from database to fill restaurant edit form - GR
		CusineDAO cusineDAO = (CusineDAO) request.getServletContext().getAttribute("cusinedao");
		List<Cusine> allCusines=null;
		try {
			allCusines = cusineDAO.getAllCusines();
			request.setAttribute("allCusines", allCusines);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
		}
		
		//Retrieve priceSlot list from database to fill restaurant edit form - GR
		PriceSlotDAO priceSlotDAO = (PriceSlotDAO) request.getServletContext().getAttribute("priceslotdao");
		try {
			List<PriceSlot> allPriceSlot = priceSlotDAO.getAllPriceSlots();
			request.setAttribute("allPriceSlot", allPriceSlot);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }	
		
		try {
            Restaurant tmp = restaurantDAO.getRestaurantById(id);
            
            if (tmp != null) {
                tmp.setCusine(cusinesRestaurantDAO.getCusinesByRestaurantId(id));
                tmp.setOpeningTimes(openingTimeDAO.getResaurantOpeningTimes(id));
                request.setAttribute("restaurant", tmp);
            } else {
                response.sendError(404);
                return "";
            }
            
            return "/restaurants/edit";
            
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }
	}
    
    /**
     * Update method, it updates the Restaurant object that will be saved, if valid,
     * inside the database.
     * @param request Object representing the request made
     * @param response Object representing the response that will be sent to
     * the client
     * @return Send to the restaurant page modified 
     * @throws IOException
     * @throws ServletException
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
        int id = Integer.parseInt(request.getParameter("id")); 
        
        // Set all cusines 
        CusineDAO cusineDAO = (CusineDAO) request.getServletContext().getAttribute("cusinedao");
		List<Cusine> allCusines=null;
		try {
			allCusines = cusineDAO.getAllCusines();
			request.setAttribute("allCusines", allCusines);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
		}
        
        Restaurant tmp = new Restaurant();
        tmp.setName(request.getParameter("name"));
        tmp.setCity(request.getParameter("city"));
        tmp.setStreet(request.getParameter("street"));
        tmp.setZipCode(request.getParameter("zipcode"));
        tmp.setProvince(request.getParameter("province"));
        tmp.setDescription(request.getParameter("description"));
        tmp.setPhone(request.getParameter("phone"));
        tmp.setEmail(request.getParameter("email"));
        tmp.setWebsite(request.getParameter("website"));
		tmp.setSlotPrice(request.getParameter("priceslotselected"));
        
        String[] cusines = request.getParameterValues("cusines");
        List<Cusine> list = new ArrayList<>();
        List<OpeningTime> listTime = new ArrayList<>();
        
        
        try {
            for (String name : cusines) {
				if(allCusines==null) break;
                for(Cusine c : allCusines) {
					if(c.getId()==Integer.parseInt(name)) {
						list.add(c);
					}
				}               
            }
        } catch (Exception e) {
            // If somebody doesn't insert cusines, List<Cusine> will
            // be inserted empty. The validate procedure will discover
            // the error. 
            // This catch exists only to ensure that the exception doesn't
            // kill the request.
        }
        tmp.setCusine(list); 
        
       // Set the opening times
        String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        for (String day : days) {
            OpeningTime tmpTime = new OpeningTime();
            tmpTime.setDay(day);
            if (request.getParameter("dayoff_"+day) != null) {
                tmpTime.setDayoff(true);
            } else {
                if (!"".equals(request.getParameter("open_at_"+day)) &&
                    !"".equals(request.getParameter("close_at_"+day)) &&  
                    !"".equals(request.getParameter("open_at_afternoon_"+day)) &&
                    !"".equals(request.getParameter("close_at_afternoon_"+day)) 
                    ) {
                    tmpTime.setOpenAt(request.getParameter("open_at_"+day));
                    tmpTime.setCloseAt(request.getParameter("close_at_"+day));
                    tmpTime.setOpenAtAfternoon(request.getParameter("open_at_afternoon_"+day));
                    tmpTime.setCloseAtAfternoon(request.getParameter("close_at_afternoon_"+day));
                    tmpTime.setDayoff(false);
                    if (tmpTime.validate()) {
                        listTime.add(tmpTime);
                    }
                }
            }         
        }
        tmp.setOpeningTimes(listTime);
        
        tmp.validate();
        
        try {
            if (tmp.isValid()) {
                int id_rest = ((RestaurantDAO) request.getServletContext().getAttribute("restaurantdao")).updateRestaurant(tmp, id);
                ((CusinesRestaurantDAO)request.getServletContext().getAttribute("cusinesrestaurantdao")).updateRestaurantCusines(id, list);
                ((OpeningTimesDAO) request.getServletContext().getAttribute("openingtimesdao")).updateRestaurantOpeningTimes(id, listTime);
                response.sendRedirect(request.getContextPath()+"/restaurants/show?id="+id_rest);
                return "";
            }else
			{
                // So we can give to the user the same page, with already datas
                // filled and also the errors made. 
				request.setAttribute("restaurant", tmp);
			}
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }
        return "/restaurants/edit";
	}
    
    private List<Restaurant> sortingQuery(String request, String query, int page) {
        return new ArrayList<>();
    }
}
