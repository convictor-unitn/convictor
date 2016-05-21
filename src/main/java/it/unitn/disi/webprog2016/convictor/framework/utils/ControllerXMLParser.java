package it.unitn.disi.webprog2016.convictor.framework.utils;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML Parser dedicated to the controller.xml file.
 * @author Giovanni De Toni
 */
public class ControllerXMLParser extends XMLParser{

    /**
     * ControllerXMLParser Constructor
     * @param file_path path of the XML controller file.
     * @param expression regex to parser the XML file.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public ControllerXMLParser(String file_path, String expression) 
            throws ParserConfigurationException, SAXException, IOException {
        super(file_path, expression);
    }
    
    /**
     * Implementation of the checkSyntax abstract method.
     * @param list NodeList from the XML parsing.
     * @return True if everything is ok, false otherwise.
     */
    @Override
    protected boolean checkSyntax(NodeList list) {
        boolean status = true;
        for (int i = 0; i < list.getLength(); i++) {
            Node nNode = list.item(i);
            for (int j = 0; j < nNode.getChildNodes().getLength(); j++) {
                String elem = nNode.getChildNodes().item(j).getTextContent().trim();
                String type = nNode.getChildNodes().item(j).getNodeName();
                if (!type.equals("#text")) {
                    if (elem.equals("")) {
                        status = false;
                    }
                }
            }
        }
        return status;
    }
    
    /**
     * Method to convert the Controller list from NodeList to ArrayList.
     * @param list NodeList of the XML file
     * @return An ArrayList of the XML file contents.
     */
    private ArrayList generateControllerList(NodeList list) {
        ArrayList<ArrayList<String>> route_list = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node temp = list.item(i);
            ArrayList<String> temp_list = new ArrayList<>();
            for (int j = 0; j < temp.getChildNodes().getLength(); j++) {
                String elem = temp.getChildNodes().item(j).getTextContent().trim();
                temp_list.add(elem);
            }
            route_list.add(temp_list);
        }
        return route_list;
    }

    /**
     * This method will return an ArrayList with the XML file content
     * and will check if everything is ok with the data. It will raise
     * an exception if something goes wrong.
     * @return An ArrayList with the XML file contents
     * @throws Exception If data inside XML file are wrong.
     */
    public ArrayList getControllers() throws Exception {
        NodeList routes = this.parse();
        if (checkSyntax(routes)) {
           return generateControllerList(routes);
        }
        throw new RuntimeException("Syntax error inside XML Controller file");
    }
    
}
