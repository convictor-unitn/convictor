/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.peserico.javaweb.templateapp.utils;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Giovanni De Toni
 */
public class RouteParser extends DefaultHandler {

    private final String route_file_path = "src/main/webapp/WEB-INF/routes.xml";
    private ArrayList tags;
    private SAXParser saxParser;
    private boolean element_find;
    private ArrayList<String> current_route;
    
    public RouteParser(){
        element_find = false;
    }
    
    @Override
    public void startDocument() throws SAXException {
        tags = new ArrayList<>();
    }
    
    @Override
    public void startElement(String namespaceURI,
                            String localName,
                            String qName, 
                            Attributes atts) throws SAXException 
    {
        switch(localName) {
            case "route":
                this.current_route = new ArrayList<>();
            break;
            
            case "url":
            case "method":
            case "controller":
            case "action":
            case "format":
                element_find = true;
            break;
            
            case "routes":
            break;
            
            default:
                throw new SAXException();
        }
    }
        
    @Override
    public void characters(char ch[], int start, int length) {
        if (this.element_find) {
            this.current_route.add(new String(ch, start, length));
            this.element_find = false;
        }
        
    }
    
   @Override
   public void endElement(String uri, 
      String localName, String qName) throws SAXException {
      if (qName.equalsIgnoreCase("route")) {
          this.tags.add(this.current_route);
      }
   }

    @Override
    public void endDocument() throws SAXException {
    }
    
    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }
    
    public ArrayList<String> parse() throws IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        try {
            saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(this);
            xmlReader.parse(convertToFileURL(this.route_file_path));
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(RouteParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.tags;
    }
    
}
