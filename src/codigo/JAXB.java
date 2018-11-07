/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import generated.Libros;
import java.awt.List;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author xp
 */
public class JAXB {
    
    public int  abrir_XML_JAXB(File fichero, Libros misLibros){
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(Libros.class);
            
            Unmarshaller u = contexto.createUnmarshaller();
            
            misLibros = (Libros)u.unmarshal(fichero);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }
    public String recorrerJAXByMostrar(){
        String Datos_nodo[] = null;
        String cadena_resultado = "";
        
        List<Libros.Libro> lLibros = misLibros.getLibro();
        for(int i=0; i<Libros.size();i++){
            cadena_resultado = cadena_resultado + "\n" + "Publicado en: " + lLibros.get(i).getPublicadoEn();
            cadena_resultado = cadena_resultado + "\n" + "El titulo es: " + lLibros.get(i).getTitulo();
            cadena_resultado = cadena_resultado + "\n" + "El autor es: " + lLibros.get(i).getAutor();
        }
        return cadena_resultado;
    }
    
    
    
}
