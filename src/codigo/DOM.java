/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author xp
 */
public class DOM {
    Document doc = null;
    
    public int abrir_XML_DOM(File fichero) {
        try {
        
        DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();
        
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        doc = builder.parse(fichero);
        
        return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    
    public String recorrerDOMyMostrar(){
        String datos_nodo[] = null;
        String salida = "";
        Node node;
        
        Node raiz = doc.getFirstChild();
        NodeList nodelist = raiz.getChildNodes();
        
        for (int i=0; i < nodelist.getLength();i++){
            node = nodelist.item(i);
            if(node.getNodeType()== Node.ELEMENT_NODE){
                datos_nodo = procesarLibro(node);
                
                salida = salida + "\n" + "Publicado en: " + datos_nodo[0];
                salida = salida + "\n" + "El autor es: " + datos_nodo[2];
                salida = salida + "\n" + "El titulo es: " + datos_nodo[1];
                salida = salida + "\n -------------------";  
            }
        }
        return salida;
    }
    
    protected String[] procesarLibro(Node n){
        String datos[] = new String[3];
        Node ntemp = null;
        int contador = 1;
        
        datos[0]= n.getAttributes().item(0).getNodeValue();
        NodeList nodos = n.getChildNodes();
        
        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);
                
                if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                    datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                    contador++;
            }
        }
        return datos;
    }
    
    public int guardarDOMcomoFile(){
        try {
            File archivo_xml = new File("modificacion.xml");
            System.out.println("1");
            OutputFormat format = new OutputFormat(doc);
            System.out.println("2");
            format.setIndenting(true);
            System.out.println("3");
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream("archivo.xml"), format);
            System.out.println("4");
            serializer.serialize(doc);
            System.out.println("5");
            System.out.println(archivo_xml.toString()); 
            return 0;
            
            //http://www.latascadexela.es/2008/07/java-y-xml-dom-ii.html
            
        } catch (Exception e) {
            return -1;
        }
    }
        
    
    public int añadirDOM(String titulo, String Autor, String anno){
        try {
            Node ntitulo = doc.createElement("titulo");
            Node ntitulo_text = doc.createTextNode(titulo);
            ntitulo.appendChild(ntitulo_text);
            
            Node nAutor = doc.createElement("Autor");
            Node nAutor_text = doc.createTextNode(Autor);
            nAutor.appendChild(nAutor_text);
            
            Node nLibro = doc.createElement("libro");
            ((Element)nLibro).setAttribute("Publicado_en:", anno);
            nLibro.appendChild(ntitulo);
            nLibro.appendChild(nAutor);
            
            
            Node raiz = doc.getChildNodes().item(0);
            raiz.appendChild(nLibro);
            
            return 0;
        } catch (Exception e) {
            return -1;
        }
    } 
    
        
}
