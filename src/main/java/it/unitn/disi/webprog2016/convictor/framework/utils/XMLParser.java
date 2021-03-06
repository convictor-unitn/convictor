package it.unitn.disi.webprog2016.convictor.framework.utils;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Abstract class that implements a simple XML parser.
 * @author Giovanni De Toni
 */
public abstract class XMLParser {
   
    protected DocumentBuilderFactory factory;
    protected DocumentBuilder builder;
    protected XPath xPath;
    protected String expression;
    protected File file;
    protected Document document;
    
    /**
     * XMLParser Constructor.
     * @param file_path XML file path inside the filesystem
     * @param expression expression used to extract data from XML file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public XMLParser(String file_path, String expression) 
           throws ParserConfigurationException, SAXException, IOException {
       factory = DocumentBuilderFactory.newInstance();
       builder = factory.newDocumentBuilder();
       xPath = XPathFactory.newInstance().newXPath();
       this.expression = expression;
       file = new File(file_path);
       document = builder.parse(file);
       document.getDocumentElement().normalize();
   }
   
   /**
    * The parse function return a list of element that matched the
    * expression string in the XML file.
    * @return Nodelist containing all the elements.
    * @throws XPathExpressionException 
    */
   public NodeList parse() throws XPathExpressionException {
       NodeList list = (NodeList) xPath.compile(expression)
                                .evaluate(document, XPathConstants.NODESET);
       return list;
   }
   
   /**
    * Abstract method to check the validity of the XML file. This
    * was made to ensure correctness of data.
    * @param list NodeList generated by the XML parsing action.
    * @return True if everything is ok, false otherwise.
    */
   abstract protected boolean checkSyntax(NodeList list);
    
}
