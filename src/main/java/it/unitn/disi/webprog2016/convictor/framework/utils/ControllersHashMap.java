/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webprog2016.convictor.framework.utils;

import it.unitn.disi.webprog2016.convictor.framework.controllers.Controller;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giovanni
 */
public class ControllersHashMap <String, Controller> extends HashMap{
    
    
    public Controller getController(String key) throws ControllerNotFoundException {
        Controller result = null;
        result = (Controller) super.get(key);
        if (result == null) throw new ControllerNotFoundException();
        return result;
    } 
}
