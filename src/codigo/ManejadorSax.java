/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import javax.xml.parsers.SAXParserFactory;
import jdk.internal.org.xml.sax.SAXException;
import org.xml.sax.Attributes;



    

/**
 *
 * @author xp
 */
public class SAX {
    
    File fichero = null;
    
    
    
    public int abrir_XML_SAX(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            
            parser = factory.newSAXParser();
            
            sh = new ManejadorSax();
            
            ficheroXML = fichero;
            
            return 0 ;
            
        } catch (Exception e) {
            return -1;
        }
    }
    
}

public class ManejadorSax {
    
    int ultimoelement;
    String cadena_resultado = "";
    
    public ManejadorSax(){
        ultimoelement = 0;
    }
    
    public void startElement(String uri, String localName, String qName, Attributes atts)throws SAXException{
        
    }
    
}

