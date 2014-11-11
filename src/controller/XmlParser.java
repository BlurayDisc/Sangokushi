/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import model.Database;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author RuN
 */
public class XmlParser {
    
    private Document doc;
    private final String filePath;
    
    public XmlParser() {
        
        filePath = "src" + File.separator + "controller" + File.separator + "save.xml";
    }
    
    public void startParse() {
        try{
            parseXmlFile();                 // reads the xml file into a Document object.
            parseDocument();                // parse the document and store the information in book objects.
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {}
        //System.out.println(bookList);       // print out the book list.
    }
    
    private void parseXmlFile() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = builder.parse(new File(filePath));
    }
    
    private void parseDocument() throws XPathExpressionException {
        
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList forceList;
        NodeList cityList;
        NodeList characterList;
        Node structure;
        Node technology;
        Database db = Database.getInstance();
        String string;
        int id;
        
        // Parse ForceList
        string = "force";
        forceList = (NodeList) xpath.evaluate(string, doc, XPathConstants.NODESET);
        for (int i = 0; i < forceList.getLength(); i++) {
            
            // Parse Attributes
            string = "@id";
            id = (int) xpath.evaluate(string, forceList.item(i), XPathConstants.NUMBER);
            string = "@silver";
            int silver = (int) xpath.evaluate(string, forceList.item(i), XPathConstants.NUMBER);
            string = "@grain";
            int grain = (int) xpath.evaluate(string, forceList.item(i), XPathConstants.NUMBER);
            
            // Parse CityList
            string = "city";
            cityList = (NodeList) xpath.evaluate(string, forceList.item(i),XPathConstants.NODESET);
            for (int j = 0; j < cityList.getLength(); j++) {
                
                // Parse Attributes
                string = "@id";
                id = (int) xpath.evaluate(string, cityList.item(j), XPathConstants.NUMBER);
                string = "@soldiers";
                int soldiers = (int) xpath.evaluate(string, cityList.item(j), XPathConstants.NUMBER);
                
                // Parse CharacterList
                string = "character";
                characterList = (NodeList) xpath.evaluate(string, cityList.item(j), XPathConstants.NODESET);
                for (int k = 0; k < characterList.getLength(); k++) {
                    
                    // Parse Attributes
                    string = "@id";
                    id = (int) xpath.evaluate(string, characterList.item(k), XPathConstants.NUMBER);
                }
                
                // Parser StructuresList
                string = "structures";
                structure = (Node) xpath.evaluate(string, cityList.item(j), XPathConstants.NODE);
  
            }
            
            // Parser Technology
            string = "technology";
            technology = (Node) xpath.evaluate(string, forceList.item(i), XPathConstants.NODE);
        }
        
        // Update        
    }
}
