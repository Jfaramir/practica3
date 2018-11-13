/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



    

/**
 *
 * @author xp
 */




public class ManejadorSax extends DefaultHandler{
    
    int ultimoelement;
    String cadena_resultado = "";
    String cadena_resultado2 = "";
    
    File fichero = null;
    
    SAXParser parser;
    
    ManejadorSax sh;
    
    public int abrir_XML_SAX(File ficheroXML){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            
            parser = factory.newSAXParser();
            
            sh = new ManejadorSax();
            
            fichero = ficheroXML;
            
            return 0 ;
            
        } catch (Exception e) {
            return -1;
        }
    }
   
    
    
    public ManejadorSax(){
        ultimoelement = 0;
    }
    
    
    @Override public void startElement(String uri, String localName, String qName, Attributes atts)throws SAXException{
        if (qName.equals("Libros")){
            ultimoelement = 4;
            cadena_resultado = cadena_resultado + "" ;
        }
        else if(qName.equals("Libro")){
            cadena_resultado = cadena_resultado + "\nPublicado en: " + atts.getValue(atts.getQName(0))+"\n";
            ultimoelement = 1;
        }
        else if(qName.equals("Titulo")){
            ultimoelement = 2;
            cadena_resultado = cadena_resultado + "\nEl Titulo es:" ;
        }
        else if (qName.equals("Autor")) {
            ultimoelement = 3;
            cadena_resultado = cadena_resultado + "\nEl Autor es:" ;
        }
        
    }
    
    @Override public void endElement(String uri, String localName, String qName) throws SAXException{
        if(qName.equals("Libro")){
            System.out.println("He encontrado el final de un elemento.");
            cadena_resultado = cadena_resultado + "\n----------------------";
        }
    }
    


     public void characters(char[] ch, int start, int length) throws SAXException{
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
    
     public String recorrerSax (){
        try {
            parser.parse(fichero, sh);
            return sh.cadena_resultado;
        }catch (SAXException e) {
            e.printStackTrace(); return "Error al pasear con SAX";
        }catch(Exception e){
            e.printStackTrace(); return "Error al pasear con SAX";
        }
        
    }
     public String recorrerTodaviaMas (){
         try {
             parser.parse(fichero, sh);
             return sh.cadena_resultado2;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return sh.cadena_resultado2;
     }
}