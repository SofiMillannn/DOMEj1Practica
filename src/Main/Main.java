package Main;

import Konni.Konni;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class Main {
    static File fichero = new File("C:/Users/sofii/Desktop/DAM/AD/practicas/DOMEj1Practica/ficheros/ejemploDom.xml");
    public static void main(String[] args) {
        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        Document doc = null;
        Element raiz = null;
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.newDocument();
            raiz = doc.createElement("Konnis");
            doc.appendChild(raiz);



        } catch (ParserConfigurationException e) {
            System.out.println("Coudn't create the document");
        }

        crearKonni("1","Macaroni", 35,"Commander",doc,raiz);
        crearKonni("2","Nalgon", 27,"Second in command",doc,raiz);
        transformardeDocaXML(doc);
        leerXML();
    }

    private static void leerXML() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(fichero);

            System.out.println("Root element: " + document.getDocumentElement().getNodeName());

            NodeList listKonnis = document.getElementsByTagName("konni"); //to see all the nodes with thi tag inside the root

            for (int i = 0; i < listKonnis.getLength(); i++) {
                Node node = listKonnis.item(0);
                System.out.println("Element: " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    System.out.println("id: " + element.getAttribute("id"));
                    System.out.println("name: "+ element.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("age: "+ element.getElementsByTagName("age").item(0).getTextContent());
                    System.out.println("rank: "+ element.getElementsByTagName("rank").item(0).getTextContent());

                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Error when parsing" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error when reading XML");
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static void transformardeDocaXML(Document doc) {
        try{
            if (!fichero.getParentFile().exists()){
                fichero.getParentFile().mkdirs();
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            FileOutputStream fos = new FileOutputStream(fichero);
            StreamResult result = new StreamResult(fos);
             transformer.transform(domSource, result);
            fos.close();

        } catch (TransformerConfigurationException e) {
            System.out.println("Error in the configuration of the transformer");
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error when closing the file");
        } catch (TransformerException e) {
            System.out.println("Error when transformind the DOM in XML " + e.getMessage());
        }
    }

    private static void crearKonni(String id, String name, int age, String rank, Document doc, Element raiz) {
        Element konni = doc.createElement("konni");
        raiz.appendChild(konni);
        konni.setAttribute("id", id);
        crearElemento(name,"nombre", doc, konni);
        crearElemento(String.valueOf(age),"age",doc,konni);
        crearElemento(rank,"rank",doc,konni);
    }

    private static void crearElemento(String name,String tag, Document doc, Element konni) {
        Element theName = doc.createElement(tag);
        konni.appendChild(theName);
        theName.setTextContent(name);
    }
}
