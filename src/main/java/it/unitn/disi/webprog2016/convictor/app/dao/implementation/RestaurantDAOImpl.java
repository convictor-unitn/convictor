    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.app.dao.implementation;

import it.unitn.disi.webprog2016.convictor.app.beans.Photo;
import it.unitn.disi.webprog2016.convictor.app.beans.Restaurant;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.PhotoDAO;
import it.unitn.disi.webprog2016.convictor.app.dao.interfaces.RestaurantDAO;
import it.unitn.disi.webprog2016.convictor.framework.dao.DatabaseDAO;
import it.unitn.disi.webprog2016.convictor.framework.utils.DatabaseConnectionManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Restaurants DAO implementation. 
 * @author Giovanni De Toni
 */
public class RestaurantDAOImpl extends DatabaseDAO implements RestaurantDAO {

    // Max number of query result
    private static final int MAX_RESULTS = 10;
    
    public RestaurantDAOImpl(DatabaseConnectionManager c) {
        super(c);
    }

    @Override
    public int insertRestaurant(Restaurant restaurant) throws SQLException {
        
        // Check if restaurant is valid
        if (!restaurant.validate()) return -1;
        
        int restaurant_id = -1;
        
        String query = "INSERT INTO restaurants (name, description, street, city, zip_code, province, full_address, website, slot_price, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        try {
            stm.setString(1, restaurant.getName());
            stm.setString(2, restaurant.getDescription());
            stm.setString(3, restaurant.getStreet());
            stm.setString(4, restaurant.getCity());
            stm.setString(5, restaurant.getZipCode());
            stm.setString(6, restaurant.getProvince());
            stm.setString(7, restaurant.getStreet() + restaurant.getCity() + restaurant.getZipCode() + restaurant.getProvince());
            stm.setString(8, restaurant.getWebsite());
            stm.setInt(9, restaurant.getSlotPrice());
            stm.setString(10, restaurant.getEmail());
            stm.setString(11, restaurant.getPhone());
			stm.executeUpdate();
			ResultSet result;
			result = stm.getGeneratedKeys();
			if(result.next() && result != null){
				restaurant_id=result.getInt(1);
			}
        } finally {
            stm.close();
        }
        
        return restaurant_id;
        
    }

    @Override
    public int updateRestaurant(Restaurant restaurant, int id) throws SQLException {
         
        String query = "UPDATE restaurants  SET name=?, description=?, street=?, city=?, zip_code=?, province=?, full_address=?, website=?, slot_price=? , phone=?, email=? WHERE id=?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setString(1, restaurant.getName());
            stm.setString(2, restaurant.getDescription());
            stm.setString(3, restaurant.getStreet());
            stm.setString(4, restaurant.getCity());
            stm.setString(5, restaurant.getZipCode());
            stm.setString(6, restaurant.getProvince());
            stm.setString(7, restaurant.getStreet() + restaurant.getCity() + restaurant.getZipCode() + restaurant.getProvince());
            stm.setString(8, restaurant.getWebsite());
            stm.setInt(9, restaurant.getSlotPrice());
            stm.setString(10, restaurant.getPhone());
            stm.setString(11, restaurant.getEmail());
            stm.setInt(12, id);
            stm.executeUpdate();
        } finally {
            stm.close();
        }
        return id;
    }

    @Override
    public List<Restaurant> getRestaurantByUserId(int id) throws SQLException {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT id, name, description, street, city, zip_code, province, full_address, website, slot_price, rating, main_phot_id, restaurant_owner_id, phone, email FROM restaurant WHERE restaurant_owner_id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(0, id);
            ResultSet ownersSet = stm.executeQuery();
            try {
                while(ownersSet.next()) {
                    Restaurant tmp = new Restaurant();
                    tmp.setId(ownersSet.getInt("id"));
                    tmp.setName(ownersSet.getString("name"));
                    tmp.setDescription(ownersSet.getString("description"));
                    tmp.setStreet(ownersSet.getString("street"));
                    tmp.setCity(ownersSet.getString("city"));
                    tmp.setZipCode(ownersSet.getString("zip_code"));
                    tmp.setProvince(ownersSet.getString("province"));
                    tmp.setFullAddress(ownersSet.getString("full_address"));
                    tmp.setWebsite(ownersSet.getString("website"));
                    tmp.setSlotPrice(ownersSet.getInt("slot_price"));
                    tmp.setRating(ownersSet.getString("rating"));
                    tmp.setMainPhotoId(ownersSet.getInt("main_photo_id"));
                    tmp.setRestaurantOwnerId(ownersSet.getInt("restaurant_owner_id"));
                    tmp.setEmail(ownersSet.getString("email"));
                    tmp.setPhone(ownersSet.getString("phone"));
                    restaurants.add(tmp);
                }
            } finally {
                ownersSet.close();
            }
            
        } finally {
            stm.close();
        }
        return restaurants;
    }

    @Override
    public Restaurant getRestaurantById(int id) throws SQLException {
        Restaurant tmp = null;
        String query = "SELECT * FROM restaurants WHERE id = ?";
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            stm.setInt(1, id);
            ResultSet ownersSet = stm.executeQuery();
            try {
                while(ownersSet.next()) {
                    tmp = new Restaurant();
                    tmp.setId(ownersSet.getInt("id"));
                    tmp.setName(ownersSet.getString("name"));
                    tmp.setDescription(ownersSet.getString("description"));
                    tmp.setStreet(ownersSet.getString("street"));
                    tmp.setCity(ownersSet.getString("city"));
                    tmp.setZipCode(ownersSet.getString("zip_code"));
                    tmp.setProvince(ownersSet.getString("province"));
                    tmp.setFullAddress(ownersSet.getString("full_address"));
                    tmp.setWebsite(ownersSet.getString("website"));
                    tmp.setSlotPrice(ownersSet.getInt("slot_price"));
                    tmp.setRating(ownersSet.getString("rating"));
                    tmp.setMainPhotoId(ownersSet.getInt("main_photo_id"));
                    tmp.setRestaurantOwnerId(ownersSet.getInt("restaurant_owner_id"));
                    tmp.setEmail(ownersSet.getString("email"));
                    tmp.setPhone(ownersSet.getString("phone"));
                }
            } finally {
                ownersSet.close();
            }
            
        } finally {
            stm.close();
        }
        return tmp;
    }

    private List<Restaurant> getRestaurantDefault(PreparedStatement stm) throws SQLException {
        List<Restaurant> listResult = new ArrayList<>();
        try {
            ResultSet restaurantSet = stm.executeQuery();
            try {
                while(restaurantSet.next()) {
                    Restaurant tmp = new Restaurant();
                    tmp.setId(restaurantSet.getInt("id"));
                    tmp.setName(restaurantSet.getString("name"));
                    tmp.setDescription(restaurantSet.getString("description"));
                    tmp.setStreet(restaurantSet.getString("street"));
                    tmp.setCity(restaurantSet.getString("city"));
                    tmp.setZipCode(restaurantSet.getString("zip_code"));
                    tmp.setProvince(restaurantSet.getString("province"));
                    tmp.setFullAddress(restaurantSet.getString("full_address"));
                    tmp.setWebsite(restaurantSet.getString("website"));
                    tmp.setSlotPrice(restaurantSet.getInt("slot_price"));
                    tmp.setRating(restaurantSet.getString("rating"));
                    tmp.setMainPhotoId(restaurantSet.getInt("main_photo_id"));
                    tmp.setRestaurantOwnerId(restaurantSet.getInt("restaurant_owner_id"));
                    tmp.setEmail(restaurantSet.getString("email"));
                    tmp.setPhone(restaurantSet.getString("phone"));
                    listResult.add(tmp);
                }
            } finally {
                restaurantSet.close();
            }            
        } catch (SQLException e) {
            throw new SQLException(e);
        }     
        return listResult;
    }
    
    @Override
    public List<Restaurant> getRestaurantByString(String pattern, int offset) throws SQLException {
        
        List<Restaurant> listResult = new ArrayList<>();
        String fullTextPattern = pattern.replace(" ", " & ");
        //int counter=0;
        
        // Deleted from these query single quote char. They cause errors when 
        // stm.setString is called. PreparedStatement should add single quote 
        // automatically.
        //String count ="SELECT COUNT(*) FROM restaurants WHERE tsv @@ tsquery(?) OR searchable ILIKE ?";
        String query ="SELECT * FROM restaurants WHERE tsv @@ tsquery(?) OR searchable ILIKE ? ORDER BY rating LIMIT ? OFFSET ?";
        
        //PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(count);
        PreparedStatement stm2 = this.getDbManager().getConnection().prepareStatement(query);
        try {
           /* // Obtain the number of record
            stm.setString(1, fullTextPattern);
            
            // The ILIKE has to be set like this because PreparedStatement
            // doesn't like %?% this pattern. Therefore, we must concatenate
            // % at the beginning and at the end of the fullTextPattern.
            stm.setString(2, "%"+fullTextPattern+"%");
            
            ResultSet countSet = stm.executeQuery();
            try {
                while(countSet.next()) {
                    counter = countSet.getInt("count");
                }
            } finally {
                countSet.close();
            }
            
            */
            
            // Obtain the restaurant paginated 
            stm2.setString(1, fullTextPattern);
            stm2.setString(2, "%"+pattern+"%");
            stm2.setInt(3, MAX_RESULTS);
            stm2.setInt(4, offset*MAX_RESULTS);
            
            listResult = this.getRestaurantDefault(stm2);
            
        } finally {
            stm2.close();
        }
        return listResult;
    }

    @Override
    public List<Restaurant> getRestaurantByStringOrderByName(String pattern, int offset) throws SQLException {
        List<Restaurant> listResult = new ArrayList<>();
        String fullTextPattern = pattern.replace(" ", " & ");
        
        String query ="SELECT * FROM restaurants WHERE tsv @@ tsquery(?) OR searchable ILIKE ? ORDER BY name LIMIT ? OFFSET ? ";
        
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            
            // Obtain the restaurant paginated 
            stm.setString(1, fullTextPattern);
            stm.setString(2, "%"+pattern+"%");
            stm.setInt(3, MAX_RESULTS);
            stm.setInt(4, offset*MAX_RESULTS);
            
            
            System.err.println(stm.toString());
            
            listResult = this.getRestaurantDefault(stm);
            
        } finally {
            stm.close();
        }
        return listResult;
    }

    @Override
    public List<Restaurant> getRestauranyByStringOrderByPrice(String pattern, int offset, int type) throws SQLException {
        List<Restaurant> listResult = new ArrayList<>();
        String fullTextPattern = pattern.replace(" ", " & ");
        
        String queryASC ="SELECT * FROM restaurants WHERE tsv @@ tsquery(?) OR searchable ILIKE ? ORDER BY slot_price ASC LIMIT 10 OFFSET ? ";
        String queryDESC ="SELECT * FROM restaurants WHERE tsv @@ tsquery(?) OR searchable ILIKE ? ORDER BY slot_price DESC LIMIT 10 OFFSET ?";
        
        PreparedStatement stm;
        if (type == 0) {
            stm = this.getDbManager().getConnection().prepareStatement(queryDESC);
        } else {
            stm = this.getDbManager().getConnection().prepareStatement(queryASC);
        }       
        
        try {
            
            // Obtain the restaurant paginated 
            stm.setString(1, fullTextPattern);
            stm.setString(2, "%"+pattern+"%");
            stm.setInt(3, MAX_RESULTS);
            stm.setInt(3, offset*MAX_RESULTS);
            
            listResult = this.getRestaurantDefault(stm);
            
        } finally {
            stm.close();
        }
        return listResult;
    }

    @Override
    public List<Restaurant> getRestaurantByString(String pattern, int offset, List<String> cusines) throws SQLException {
        List<Restaurant> listResult = new ArrayList<>();
        String fullTextPattern = pattern.replace(" ", " & ");
        
        // Set how many cusine fields we want to filter on
        String params = "";
        boolean setAND = false;
        for (String c : cusines) {
            if (!setAND) {
                setAND = true;
                params += " AND ";
                params += "cusines_restaurants.cusine_id = ? ";
            } else {
                params += "OR cusines_restaurants.cusine_id = ? ";
            }
        }
        
        // Set up everything inside the query. This should be safe because
        // the string concatenated are fixed and cannot be modified.
        String query ="SELECT DISTINCT restaurants.id, " +
				"restaurants.name, " +
				"restaurants.description, " +
				"restaurants.street, " +
				"restaurants.city, " +
				"restaurants.zip_code, " +
				"restaurants.province, " +
				"restaurants.full_address, " +
				"restaurants.website, " +
				"restaurants.slot_price, " +
				"restaurants.rating, " +
				"restaurants.main_photo_id, " +
				"restaurants.restaurant_owner_id, " +
				"restaurants.email, " +
				"restaurants.phone FROM restaurants INNER JOIN cusines_restaurants ON restaurants.id = restaurant_id WHERE tsv @@ tsquery(?) OR searchable ILIKE ? "+
                params
                +"ORDER BY restaurants.rating LIMIT ? OFFSET ? ";
        
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            
            // Obtain the restaurant paginated 
            stm.setString(1, fullTextPattern);
            stm.setString(2, "%"+pattern+"%");
            int counter = 1;
            for (String c : cusines) {
                stm.setInt(2+counter, Integer.valueOf(c));
                counter++;
            }
            stm.setInt(2+counter, MAX_RESULTS);
            stm.setInt(3+counter, offset*MAX_RESULTS);
            
            listResult = this.getRestaurantDefault(stm);
            
        } finally {
            stm.close();
        }
        return listResult;
    }

    @Override
    public List<Restaurant> getRestaurantByStringOrderByName(String pattern, int offset, List<String> cusines) throws SQLException {
         List<Restaurant> listResult = new ArrayList<>();
        String fullTextPattern = pattern.replace(" ", " & ");
        
        // Set how many cusine fields we want to filter on
        String params = "";
        boolean setAND = false;
        for (String c : cusines) {
            if (!setAND) {
                setAND = true;
                params += " AND ";
                params += "cusines_restaurants.cusine_id = ? ";
            } else {
                params += "OR cusines_restaurants.cusine_id = ? ";
            }
        }
        
        // Set up everything inside the query. This should be safe because
        // the string concatenated are fixed and cannot be modified.
        String query ="SELECT DISTINCT restaurants.id, " +
				"restaurants.name, " +
				"restaurants.description, " +
				"restaurants.street, " +
				"restaurants.city, " +
				"restaurants.zip_code, " +
				"restaurants.province, " +
				"restaurants.full_address, " +
				"restaurants.website, " +
				"restaurants.slot_price, " +
				"restaurants.rating, " +
				"restaurants.main_photo_id, " +
				"restaurants.restaurant_owner_id, " +
				"restaurants.email, " +
				"restaurants.phone FROM restaurants INNER JOIN cusines_restaurants ON restaurants.id = restaurant_id WHERE tsv @@ tsquery(?) OR searchable ILIKE ? "+
                params
                +"ORDER BY restaurants.name LIMIT ? OFFSET ? ";
        
        PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
        try {
            
            // Obtain the restaurant paginated 
            stm.setString(1, fullTextPattern);
            stm.setString(2, "%"+pattern+"%");
            int counter = 1;
            for (String c : cusines) {
                stm.setInt(2+counter, Integer.valueOf(c));
                counter++;
            }
            stm.setInt(2+counter, MAX_RESULTS);
            stm.setInt(3+counter, offset*MAX_RESULTS);
            
            listResult = this.getRestaurantDefault(stm);
            
        } finally {
            stm.close();
        }
        return listResult;
    }

    @Override
    public List<Restaurant> getRestauranyByStringOrderByPrice(String pattern, int offset, int type, List<String> cusines) throws SQLException {
        List<Restaurant> listResult = new ArrayList<>();
        String fullTextPattern = pattern.replace(" ", " & ");
        
        // Set how many cusine fields we want to filter on
        String params = "";
        boolean setAND = false;
        for (String c : cusines) {
            if (!setAND) {
                setAND = true;
                params += " AND ";
                params += "cusines_restaurants.cusine_id = ? ";
            } else {
                params += "OR cusines_restaurants.cusine_id = ? ";
            }
        }
        
        // Set up everything inside the query. This should be safe because
        // the string concatenated are fixed and cannot be modified.
        String queryDESC ="SELECT DISTINCT restaurants.id, " +
				"restaurants.name, " +
				"restaurants.description, " +
				"restaurants.street, " +
				"restaurants.city, " +
				"restaurants.zip_code, " +
				"restaurants.province, " +
				"restaurants.full_address, " +
				"restaurants.website, " +
				"restaurants.slot_price, " +
				"restaurants.rating, " +
				"restaurants.main_photo_id, " +
				"restaurants.restaurant_owner_id, " +
				"restaurants.email, " +
				"restaurants.phone FROM restaurants INNER JOIN cusines_restaurants ON restaurants.id = restaurant_id WHERE tsv @@ tsquery(?) OR searchable ILIKE ? "+
                params
                +"ORDER BY restaurants.slot_price DESC LIMIT ? OFFSET ? ";
        String queryASC ="SELECT DISTINCT restaurants.id, " +
				"restaurants.name, " +
				"restaurants.description, " +
				"restaurants.street, " +
				"restaurants.city, " +
				"restaurants.zip_code, " +
				"restaurants.province, " +
				"restaurants.full_address, " +
				"restaurants.website, " +
				"restaurants.slot_price, " +
				"restaurants.rating, " +
				"restaurants.main_photo_id, " +
				"restaurants.restaurant_owner_id, " +
				"restaurants.email, " +
				"restaurants.phone FROM restaurants INNER JOIN cusines_restaurants ON restaurants.id = restaurant_id WHERE tsv @@ tsquery(?) OR searchable ILIKE ? "+
                params
                +"ORDER BY restaurants.slot_price ASC LIMIT ? OFFSET ? ";
        
        PreparedStatement stm;
        if (type == 0) {
            stm = this.getDbManager().getConnection().prepareStatement(queryDESC);
        } else {
            stm = this.getDbManager().getConnection().prepareStatement(queryASC);
        }
        
        try {
            
            // Obtain the restaurant paginated 
            stm.setString(1, fullTextPattern);
            stm.setString(2, "%"+pattern+"%");
            int counter = 1;
            for (String c : cusines) {
                stm.setInt(2+counter, Integer.valueOf(c));
                counter++;
            }
            stm.setInt(2+counter, MAX_RESULTS);
            stm.setInt(3+counter, offset*MAX_RESULTS);
            
            listResult = this.getRestaurantDefault(stm);
            
        } finally {
            stm.close();
        }
        return listResult;
    }

	@Override
	public void insertPhoto(Photo photo) throws SQLException {
		PhotoDAO photoDao = new PhotoDAOImpl(this.getDbManager());
		photoDao.insertPhoto(photo);
	}

  @Override
  public void computeRating(int restaurant_id) throws SQLException {
    String query ="SELECT rating, count(*) FROM reviews WHERE restaurant_id= ? GROUP BY rating";
    String update = "UPDATE restaurants SET rating=? WHERE id=?";
    PreparedStatement stm = this.getDbManager().getConnection().prepareStatement(query);
    PreparedStatement stm2 = this.getDbManager().getConnection().prepareStatement(update);
    int [] counterRatings = new int[5];
    for (int i=0; i<5; i++) counterRatings[i]=0;
    try { 
      stm.setInt(1, restaurant_id);
      ResultSet ratings = stm.executeQuery();
      try {
        while(ratings.next()) {
          counterRatings[ratings.getInt("rating")-1] = ratings.getInt("count"); 
        }
      } finally {
        ratings.close();
      }
      
      // Compute the total rating
      int numerator =0; int denominator=0;
      for (int i = 0; i < 5; i++) {
        numerator += counterRatings[i]*(i+1);
        denominator += counterRatings[i];
      }
      
      // Set the rating
      stm2.setInt(1, numerator/denominator);
      stm2.setInt(2, restaurant_id);
      stm2.executeUpdate();
          
    } finally {
      stm.close();
    }
  }
    
	
	
}
