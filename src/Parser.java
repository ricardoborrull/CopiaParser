import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Persona> personas = null;

	public Parser() {
		personas = new ArrayList<Persona>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representaci�n DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument() {
		// obtenemos el elemento ra�z
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("persona");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (persona)
				Element el = (Element) nl.item(i);

				// obtenemos una persona
				Persona p = getPersona(el);

				// lo a�adimos al array
				personas.add(p);
			}
		}
	}
	
	private Persona getPersona(Element personaEle){
		
		//para cada elemento persona, obtenemos su nombre y su edad
		String nombre = getTextValue(personaEle,"nombre");
		int edad = getIntValue(personaEle,"edad");
		
		//Creamos una nueva persona con los elementos le�dos del nodo
		Persona e = new Persona(nombre,edad);

		return e;		
		
	}
	
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}		
		return textVal;
	}
	
	private int getIntValue(Element ele, String tagName) {				
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	
	public void print(){

		Iterator it = personas.iterator();
		while(it.hasNext()) {
			Persona p=(Persona) it.next();
			p.print();
		}
	}
	
	

}