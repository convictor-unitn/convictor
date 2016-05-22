/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.utils;

import java.util.HashMap;

/**
 *
 * @author Giovanni
 */
    public class RoutesHashMap <RouteId, Route> extends HashMap {
    
    public RoutesHashMap () {};
    
    public Route getRoute(RouteId key) throws RouteNotFoundException {
        Route result = null;
        result = (Route) super.get(key);
        
        if (result == null) throw new RouteNotFoundException();
        
        return result;
    }        
}
