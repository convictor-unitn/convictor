/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.utils;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unitn.disi.webprog2016.convictor.framework.utils.NotFoundRoutesException;

/**
 *
 * @author Giovanni
 */
public class RoutesHashMap extends HashMap<RouteId, Route> {
    
    public Route getRoute(Object key) throws NotFoundRoutesException {
        Route result = null;
        result = super.get(key);
        
        if (result == null) throw new NotFoundRoutesException();
        
        return result;
    }        
}
