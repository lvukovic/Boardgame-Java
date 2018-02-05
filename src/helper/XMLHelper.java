/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author lukav
 */
public class XMLHelper {
    public static Element rootElement(Document doc) {
        Element rootEl = doc.createElement("ZmijeILjestve");
        doc.appendChild(rootEl);

        return rootEl;
    }

    public static Element moveElement(Document doc, Element parentElement) {
        Element moveEl = doc.createElement("Move");
        parentElement.appendChild(moveEl);
        
        return moveEl;
    }
     public static Element xElement(Document doc, Element parentElement, int x) {
        Element xEl = doc.createElement("Xkoordinata");
        xEl.appendChild(doc.createTextNode(String.valueOf(x)));
       
        parentElement.appendChild(xEl);
        
        return xEl;
    }
     
     public static Element yElement(Document doc, Element parentElement, int y) {
        Element yEl = doc.createElement("Ykoordinata");
         yEl.appendChild(doc.createTextNode(String.valueOf(y)));
        parentElement.appendChild(yEl);
        
        return yEl;
    }
      public static Element posElement(Document doc, Element parentElement, int pos) {
        Element yEl = doc.createElement("Position");
         yEl.appendChild(doc.createTextNode(String.valueOf(pos)));
        parentElement.appendChild(yEl);
        
        return yEl;
    }
}
