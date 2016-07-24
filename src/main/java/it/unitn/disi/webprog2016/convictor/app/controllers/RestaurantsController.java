/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.controllers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import it.unitn.disi.webprog2016.convictor.app.beans.Administrator;
import it.unitn.disi.webprog2016.convictor.app.beans.Cusine;
import it.unitn.disi.webprog2016.convictor.app.beans.OpeningTime;
import it.unitn.disi.webprog2016.convictor.app.beans.PriceSlot;
import it.unitn.disi.webprog2016.convictor.app.beans.Restaurant;
import it.unitn.disi.webprog2016.convictor.app.beans.Review;
import it.unitn.disi.webprog2016.convictor.app.beans.ReviewNotice;
import it.unitn.disi.webprog2016.convictor.app.beans.User;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.CusineDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.CusinesRestaurantDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.NoticeDAO;
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
import it.unitn.disi.webprog2016.convictor.app.beans.Photo;
import it.unitn.disi.webprog2016.convictor.app.beans.RestaurantOwner;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.PhotoDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.UserDAO;
import java.util.Iterator;
import java.util.UUID;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * In this controller there add all restaurant management pages
 * @author umberto
 */
public class RestaurantsController extends AbstractController {
	
	private static final long serialVersionUID = -7720246048637220075L;
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 140; // 140MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 150; // 150MB
    private static final String UUID_STRING = "uuid";
	private static final String AMAZON_ACCESS_KEY = "AKIAINLCB7W3V5KLNOHQ";
    private static final String AMAZON_SECRET_KEY = "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";
    private static final String S3_BUCKET_NAME = "convictor";
	private static final Logger LOGGER = Logger.getLogger(RestaurantsController.class.getName());
    
    public RestaurantsController() {
        super();
    }
	
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
		
        // Retrive all cusines 
        CusineDAO cusineDAO = (CusineDAO) request.getServletContext().getAttribute("cusinedao");
		List<Cusine> allCusines;
        try {
			allCusines = cusineDAO.getAllCusines();
			request.setAttribute("allCusines", allCusines);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
		}
        
        // Set cusine filters (if present)
        List<String> cusineFilters = new ArrayList<>();
        for (Cusine c: allCusines) {
            if (request.getParameter(String.valueOf(c.getId())) != null) {
                cusineFilters.add(String.valueOf(c.getId()));
            }
        }
        
        // Set the query
        String query = request.getParameter("query");
        if (query==null) {
            query = "";
        }
        
        // Set the page index (pagination) and set a try-catch
        // block to block parsing exception.
        int page=0;
        if (request.getParameter("page") != null){
            try {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 0) {page=0;}
            } catch (Exception e) {
                Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, e);
                page = 0;
            }
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
            
            // Switch sort method and check if there are cusine filters on
            switch(sortMethod) {
                case "nameSorting":
                    if (cusineFilters.size() > 0 ) {
                        tmp = restaurantDAO.getRestaurantByStringOrderByName(query, page, cusineFilters);
                    } else {
                        tmp = restaurantDAO.getRestaurantByStringOrderByName(query, page);
                    }
                    break;
                case "priceAscSorting":
                    if (cusineFilters.size() > 0) {
                        tmp = restaurantDAO.getRestauranyByStringOrderByPrice(query, page, 1, cusineFilters);
                    } else {
                        tmp = restaurantDAO.getRestauranyByStringOrderByPrice(query, page, 1);
                    }
                    break;
                case "priceDescSorting":
                    if (cusineFilters.size() > 0) {
                        tmp = restaurantDAO.getRestauranyByStringOrderByPrice(query, page, 0, cusineFilters);
                    } else {
                        tmp = restaurantDAO.getRestauranyByStringOrderByPrice(query, page, 0);
                    }
                    break;
                default:
                    if (cusineFilters.size() > 0) {
                        tmp = restaurantDAO.getRestaurantByString(query, page, cusineFilters);
                    } else {
                        tmp = restaurantDAO.getRestaurantByString(query, page);
                    }
                }
            
            if (tmp != null) {
                request.setAttribute("results", tmp);
            } else {
                request.setAttribute("results", new ArrayList<>());
            }
            
            // Set the next pagination 
            if (tmp.size() > 0) {
                request.setAttribute("nextPagination", page+1);
            } else if (request.getParameter("page") != null) {
                // This logic block exist to ensure that the user doesn't
                // navigate to a blank page because there are no more result
                // to show.
                int pageIndex = request.getQueryString().indexOf("&page=");
                String queryString = request.getQueryString().substring(0, pageIndex);
                request.setAttribute("nextPagination", page-1);
                response.sendRedirect(request.getContextPath()+"/restaurants?"
                        + queryString +"&page="
                        + String.valueOf(page-1));
                return "";
            } else {
                request.setAttribute("nextPagination", page);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
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
        
        // Try catch to avoid parsing errors
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(404);
            return "";
        }
        
        // Retrive the restaurant from the database given its id 
        RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
        ReviewDAO reviewDAO = (ReviewDAO) request.getServletContext().getAttribute("reviewdao");
        CusinesRestaurantDAO cusinesRestaurantDAO = (CusinesRestaurantDAO) request.getServletContext().getAttribute("cusinesrestaurantdao");
        OpeningTimesDAO openingTimeDAO = (OpeningTimesDAO) request.getServletContext().getAttribute("openingtimesdao");
		PhotoDAO photoDAO = (PhotoDAO) request.getServletContext().getAttribute("photodao");
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		User currentUser = (User) request.getSession().getAttribute("user");
		
		int reviewPage = 0;        
        if (request.getParameter("reviewPage") != null) {
            try {
                reviewPage = Integer.parseInt(request.getParameter("reviewPage"));
                if (reviewPage < 0) {reviewPage = 0;}
            } catch (Exception ex) {
                Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
                reviewPage = 0;
            }
        }
		
        try {
            
            Restaurant tmp = restaurantDAO.getRestaurantById(id);
            if (tmp != null) {
                tmp.setCusine(cusinesRestaurantDAO.getCusinesByRestaurantId(id));
                tmp.setReviews(reviewDAO.getRestaurantReviews(id, reviewPage));
                tmp.setOpeningTimes(openingTimeDAO.getResaurantOpeningTimes(id));
				tmp.setPhotos(photoDAO.getRestaurantPhotos(id));
                request.setAttribute("restaurant", tmp);
            } else {
                response.sendError(404);
                return "";
            }
            
            // Set the next pagination 
            if (tmp.getReviews().size() > 0) {
                request.setAttribute("nextPagination", reviewPage+1);
            } else if (request.getParameter("reviewPage") != null) {
                // This logic block exist to ensure that the user doesn't
                // navigate to a blank page because there are no more result
                // to show.
                int pageIndex = request.getQueryString().indexOf("&reviewPage=");
                String queryString = request.getQueryString().substring(0, pageIndex);
                request.setAttribute("nextPagination", reviewPage-1);
                response.sendRedirect(request.getContextPath()+"/restaurants/show?"
                        + queryString +"&reviewPage="
                        + String.valueOf(reviewPage-1));
                return "";
            } else {
                request.setAttribute("nextPagination", reviewPage);
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
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		User currentUser = (User) request.getSession().getAttribute("user");
		
		// Authorizations
		if( !(currentUser instanceof Administrator)) {
			response.sendError(401);
			return "";
		}
		
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
		
		String[] days = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"};
		
		Restaurant tmp = new Restaurant();
		List<OpeningTime> tmp2 = new ArrayList<>();
		for (String day : days ) {
			OpeningTime time = new OpeningTime();
			time.setDay(day);
			time.setDayString(time.getDay());
			tmp2.add(time);
		}
		tmp.setOpeningTimes(tmp2);
		request.setAttribute("restaurant", tmp);
		
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
		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		User currentUser = (User) request.getSession().getAttribute("user");
		
		// Authorizations
		if( !(currentUser instanceof Administrator)) {
			response.sendError(401);
			return "";
		}
		
		List<Cusine> allCusines=null;
		try {
			allCusines = cusineDAO.getAllCusines();
			request.setAttribute("allCusines", allCusines);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
		}
        
		// Get all price slots available to make them available inside the JSP
		PriceSlotDAO priceSlotDAO = (PriceSlotDAO) request.getServletContext().getAttribute("priceslotdao");
		try {
			List<PriceSlot> allPriceSlot = priceSlotDAO.getAllPriceSlots();
			request.setAttribute("allPriceSlot", allPriceSlot);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
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
		List<OpeningTime> allTime = new ArrayList<>();
       
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
        String[] days = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"};
        for (String day : days) {
            OpeningTime tmpTime = new OpeningTime();
            tmpTime.setDay(day);
            if (request.getParameter("dayoff_"+day) != null) {
                tmpTime.setDayoff(true);
				
				tmpTime.setOpenAt("00:00");
				tmpTime.setCloseAt("00:00");
				tmpTime.setOpenAtAfternoon("00:00");
				tmpTime.setCloseAtAfternoon("00:00");
				
            } else {
                if (!"".equals(request.getParameter("open_at_"+day+"_hour")) &&
                    !"".equals(request.getParameter("close_at_"+day+"_hour")) &&  
                    !"".equals(request.getParameter("open_at_afternoon_"+day+"_hour")) &&
                    !"".equals(request.getParameter("close_at_afternoon_"+day+"_hour")) &&
					!"".equals(request.getParameter("open_at_"+day+"_minute")) &&
                    !"".equals(request.getParameter("close_at_"+day+"_minute")) &&  
                    !"".equals(request.getParameter("open_at_afternoon_"+day+"_minute")) &&
                    !"".equals(request.getParameter("close_at_afternoon_"+day+"_minute"))
                    ) {
                    
					// Set the time correctly inside the bean
					tmpTime.setOpenAt(
							request.getParameter("open_at_"+day+"_hour")+
							":"+request.getParameter("open_at_"+day+"_minute")
					);
                    tmpTime.setCloseAt(
							request.getParameter("close_at_"+day+"_hour")+
							":"+request.getParameter("close_at_"+day+"_minute")
					);
                    tmpTime.setOpenAtAfternoon(
							request.getParameter("open_at_afternoon_"+day+"_hour")+
							":"+request.getParameter("open_at_afternoon_"+day+"_minute")
					);
                    tmpTime.setCloseAtAfternoon(
							request.getParameter("close_at_afternoon_"+day+"_hour")+
							":"+request.getParameter("close_at_afternoon_"+day+"_minute")
					);
                    
					tmpTime.setDayoff(false);
                }
            }
			tmpTime.validate();
			if (tmpTime.isValid()) {
                listTime.add(tmpTime);
            }
			allTime.add(tmpTime);
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
                // filled and also the errors made. We also insert all the opening
				// times to permit an easy filling.
				tmp.setOpeningTimes(allTime);
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
        UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userdao");
		User currentUser = (User) request.getSession().getAttribute("user");
		
        int id = Integer.parseInt(request.getParameter("id"));
        
		// Authorizations
		boolean authorized = false;
		
		try {
			if( currentUser instanceof Administrator ) {
				authorized = true;
			} else if( currentUser instanceof RestaurantOwner) {
				boolean found = false;
				RestaurantOwner restaurantOwner = (RestaurantOwner) currentUser;
				restaurantOwner.setRestaurants(restaurantDAO.getRestaurantByUserId(currentUser.getId()));
				for(Restaurant r : restaurantOwner.getRestaurants()) {
					if(r.getId() == id) {
						found = true;
					}
				}
				
				if(found) {
					authorized = true;
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		if(!authorized) {
			response.sendError(401);
			return "";
		}
		
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
		CusineDAO cusineDAO = (CusineDAO) request.getServletContext().getAttribute("cusinedao");
		PriceSlotDAO priceSlotDAO = (PriceSlotDAO) request.getServletContext().getAttribute("priceslotdao");
		RestaurantDAO restaurantDAO = (RestaurantDAO) request.getServletContext().getAttribute("restaurantdao");
		
		User currentUser = (User) request.getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id")); 
        
		// Authorizations
		boolean authorized = false;
		
		try {
			if( currentUser instanceof Administrator ) {
				authorized = true;
			} else if( currentUser instanceof RestaurantOwner) {
				boolean found = false;
				RestaurantOwner restaurantOwner = (RestaurantOwner) currentUser;
				restaurantOwner.setRestaurants(restaurantDAO.getRestaurantByUserId(currentUser.getId()));
				for(Restaurant r : restaurantOwner.getRestaurants()) {
					if(r.getId() == id) {
						found = true;
					}
				}
				
				if(found) {
					authorized = true;
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		if(!authorized) {
			response.sendError(401);
			return "";
		}
		
        // Set all cusines 
        
		List<Cusine> allCusines=null;
		try {
			allCusines = cusineDAO.getAllCusines();
			request.setAttribute("allCusines", allCusines);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
		}
        
		// Get all price slots available to make them available inside the JSP
		
		try {
			List<PriceSlot> allPriceSlot = priceSlotDAO.getAllPriceSlots();
			request.setAttribute("allPriceSlot", allPriceSlot);
		} catch (SQLException ex) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
        Restaurant tmp = new Restaurant();
		tmp.setId(id);
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
        List<OpeningTime> allTime = new ArrayList<>();
        
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
        String[] days = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"};
        for (String day : days) {
            OpeningTime tmpTime = new OpeningTime();
            tmpTime.setDay(day);
            if (request.getParameter("dayoff_"+day) != null) {
                tmpTime.setDayoff(true);
				tmpTime.setOpenAt("00:00");
				tmpTime.setCloseAt("00:00");
				tmpTime.setOpenAtAfternoon("00:00");
				tmpTime.setCloseAtAfternoon("00:00");
            } else {
                if (!"".equals(request.getParameter("open_at_"+day+"_hour")) &&
                    !"".equals(request.getParameter("close_at_"+day+"_hour")) &&  
                    !"".equals(request.getParameter("open_at_afternoon_"+day+"_hour")) &&
                    !"".equals(request.getParameter("close_at_afternoon_"+day+"_hour")) &&
					!"".equals(request.getParameter("open_at_"+day+"_minute")) &&
                    !"".equals(request.getParameter("close_at_"+day+"_minute")) &&  
                    !"".equals(request.getParameter("open_at_afternoon_"+day+"_minute")) &&
                    !"".equals(request.getParameter("close_at_afternoon_"+day+"_minute"))
                    ) {
					
					// Set the time correctly inside the bean
					tmpTime.setOpenAt(
							request.getParameter("open_at_"+day+"_hour")+
							":"+request.getParameter("open_at_"+day+"_minute")
					);
                    tmpTime.setCloseAt(
							request.getParameter("close_at_"+day+"_hour")+
							":"+request.getParameter("close_at_"+day+"_minute")
					);
                    tmpTime.setOpenAtAfternoon(
							request.getParameter("open_at_afternoon_"+day+"_hour")+
							":"+request.getParameter("open_at_afternoon_"+day+"_minute")
					);
                    tmpTime.setCloseAtAfternoon(
							request.getParameter("close_at_afternoon_"+day+"_hour")+
							":"+request.getParameter("close_at_afternoon_"+day+"_minute")
					);
                    
					tmpTime.setDayoff(false);
                }
            }
			tmpTime.validate();
			if (tmpTime.isValid()) {
                listTime.add(tmpTime);
            }
			allTime.add(tmpTime);
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
				tmp.setOpeningTimes(allTime);
				request.setAttribute("restaurant", tmp);
			}
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }
        return "/restaurants/edit";
	}
	
	public String uploadPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Inizializzazione parametri
		String url = null;
		// checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            LOGGER.severe("Nessun file caricato");
			response.sendError(500);
            return "";
        }
		
		PhotoDAO photoDAO = (PhotoDAO) request.getServletContext().getAttribute("photodao");
		int restaurantId = 0;
		
		// Fine inizializzazione parametri
		
		// Authorizations
		User currentUser = (User) request.getSession().getAttribute("user");
		if(currentUser==null) {
			response.sendError(401);
			return "";
		}
		
		// Set delle impostazioni della richiesta 
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
		
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        String uuidValue = "";
        FileItem itemFile = null;
 
        try {
            // parses the request's content to extract file data
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();
 
            // iterates over form's fields to get UUID Value
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    if (item.getFieldName().equalsIgnoreCase(UUID_STRING)) {
                        uuidValue = item.getString();
                    }
					
					if(item.getFieldName().equals("id")) {
						restaurantId = Integer.parseInt(item.getString());
						request.setAttribute("restaurantId", restaurantId);
					}
                }
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                    itemFile = item;
                }
            }
			 
            if (itemFile != null && itemFile.getSize() != 0) {
                BasicAWSCredentials awsCredentials = new BasicAWSCredentials(AMAZON_ACCESS_KEY, AMAZON_SECRET_KEY);
                AmazonS3 s3client = new AmazonS3Client(awsCredentials);
                try {
					String uuid = UUID.randomUUID().toString();
                    ObjectMetadata om = new ObjectMetadata();
                    om.setContentLength(itemFile.getSize());
                    String ext = FilenameUtils.getExtension(itemFile.getName());
                    String keyName = uuid + '.' + ext;
 
                    PutObjectResult res = s3client.putObject(new PutObjectRequest(S3_BUCKET_NAME, keyName, itemFile.getInputStream(), om));
					s3client.setObjectAcl(S3_BUCKET_NAME, keyName, CannedAccessControlList.PublicRead);
 
					url = "https://s3.eu-central-1.amazonaws.com/"+S3_BUCKET_NAME+"/"+keyName;
					
					Photo photo = new Photo();
					photo.setRestaurantId(restaurantId);
					photo.setUrl(url);
					photoDAO.insertPhoto(photo);
					request.setAttribute("uploadStatus", "success");
					LOGGER.log(Level.INFO, "{0}:Upload done", uuidValue);
					
                } catch (AmazonServiceException ase) {
                    LOGGER.log(Level.SEVERE, "{0}:error:{1}", new Object[]{uuidValue, ase.getMessage()});
					request.setAttribute("uploadStatus", "failure");
                } catch (AmazonClientException ace) {
                    LOGGER.log(Level.SEVERE, "{0}:error:{1}", new Object[]{uuidValue, ace.getMessage()});
					request.setAttribute("uploadStatus", "failure");
                } catch (SQLException ex) {
					Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
					request.setAttribute("uploadStatus", "failure");
				}
            } else {
                LOGGER.log(Level.SEVERE,"{0}" + ":error:" + "No Upload file", uuidValue);
				request.setAttribute("uploadStatus", "failure");
            }
 
        } catch (FileUploadException | IOException ex) {
            LOGGER.log(Level.SEVERE,"{0}" + ":" + ":error: {1}", new Object[]{uuidValue, ex.getMessage()});
			request.setAttribute("uploadStatus", "failure");
        }
		
		return "/restaurants/upload";
	}
  
    /**
     * Method to add a review to a specific restaurant
     * @param request Object representing the request made
     * @param response Object representing the response that will be sent to
     * the client
     * @return Send to the restaurant page
     * @throws IOException
     * @throws ServletException
     */
    public String addReview(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        // Get the user and popolate a Review bean
        User tmpUser = (User) request.getSession(false).getAttribute("user");
        
		// Check if the user can insert a review
		try {
			List<Review> userReview = ((ReviewDAO) request.getServletContext().getAttribute("reviewdao")).getMostRecentReviewsByUserId(tmpUser.getId());
			Review tmp = new Review();
			for (Review r : userReview) {
				if (String.valueOf(r.getRestaurantId()).equals(request.getParameter("idRestaurant"))) {
					tmp.setRestaurantId(request.getParameter("idRestaurant"));
					request.setAttribute("review", tmp);
					request.setAttribute("alreadyReviewed", "error");
					return "/restaurants/review";
				}
			}
		} catch (Exception e) {
			Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(500);
            return "";
		}
		
		
		Review tmp = new Review();
        tmp.setRestaurantId(request.getParameter("idRestaurant"));
        tmp.setRegisteredUserId(tmpUser.getId());
        tmp.setRating(request.getParameter("rating"));
        tmp.setDescription(request.getParameter("reviewText"));
        
        tmp.validate();
        
        try {
          if (tmp.isValid()) {
			
            // Insert the review, the notice and update the restaurant rating
            ReviewNotice tmpNotice = new ReviewNotice();
            int reviewId = ((ReviewDAO)request.getServletContext().getAttribute("reviewdao")).insertReview(tmp);
            tmpNotice.setRegisteredUserId(tmpUser.getId());
            tmpNotice.setReviewId(reviewId);
            ((NoticeDAO) request.getServletContext().getAttribute("noticedao")).insertReviewNotice(tmpNotice);
            ((RestaurantDAO) request.getServletContext().getAttribute("restaurantdao")).computeRating(tmp.getRestaurantId());
            request.setAttribute("review", tmp);
          } else {
            request.setAttribute("review", tmp);
          }
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500);
            return "";
        }
      return "/restaurants/review";
    }
	
	public String qrcode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "";	
	}
	
}
