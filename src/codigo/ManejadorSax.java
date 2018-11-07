/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;



    

/**
 *
 * @author xp
 */
    class SAX {
    
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

public class ManejadorSax extends DefaultHandler{
    
    int ultimoelement;
    String cadena_resultado = "";
    
    public ManejadorSax(){
        ultimoelement = 0;
    }
    public void startElement(String uri, String localName, String qName, Attributes atts)throws SAXException{
        if(qName.equals("libro")){
            cadena_resultado = cadena_resultado + "\nPublicado en: " + atts.getValue(atts.getQName(0))+"\n";
            ultimoelement = 1;
        }
        else if(qName.equals("Titulo")){
            ultimoelement = 2;
            cadena_resultado = cadena_resultado + "\nEl titulo es: ";
        }
        else if (qName.equals("Autor")) {
            ultimoelement = 3;
            cadena_resultado = cadena_resultado + "\nEl autor es: ";
        }
    }
    
    @Override public void endElement(String uri, String localName, String qName) throws SAXException{
        if(qName.equals("Libro")){
            System.out.println("He encontrado el final de un elemento.");
            cadena_resultado = cadena_resultado + "\n----------------------";
        }
    }
    


     public void characterS(char[] ch, int start, int length) throws SAXException{
        if(ultimoelement == 2){
            for (int i = start; i<length + start; i++){
                cadena_resultado = cadena_resultado + ch[i];
            }
        }
        else if(ultimoelement == 3){
            for (int i = start; i<length + start; i++){
                cadena_resultado = cadena_resultado + ch[i];
                
            }
        }
    }
    public String recorrerSax (File fXML, ManejadorSax sh, SAXParser parser){
        try {
            parser.parse(fXML, sh);
            return sh.cadena_resultado;
        }catch (SAXException e) {
            e.printStackTrace(); return "Error al pasear con SAX";
        }catch(Exception e){
            e.printStackTrace(); return "Error al pasear con SAX";
        }
        return sh.cadena_resultado;
    }
    
}