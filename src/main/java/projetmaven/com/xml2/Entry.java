package projetmaven.com.xml2;


import javax.xml.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import projetmaven.com.xml2.model.Car;
import projetmaven.com.xml2.model.Parking;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class Entry {
		public static void main(String[] args) {
			test1();
			
		}
		
		public static void test1() 
		{
			Parking parking1 = new Parking(1, "Jean Jaures");
			Parking parking2 = new Parking(2, "Capitole");
			
			parking1.addCar(new Car(11,"VD334JK", "Peugeot", "Rouge"));
			parking1.addCar(new Car(23,"VFERSJK", "Renault", "Bleu"));
			parking1.addCar(new Car(465,"VH342JG", "Ford", "Rouge"));
			parking1.addCar(new Car(52,"DG4T2JK", "Ford", "Bleu"));
			parking1.addCar(new Car(85,"VD332JR", "Peugeot", "Rouge"));				
			parking2.addCar(new Car(34,"VD334EK", "Peugeot", "Vert"));
			parking2.addCar(new Car(22,"VFERSAK", "Fiat", "Rouge"));
			parking2.addCar(new Car(624,"VH34HJG", "Ford", "Vert"));
			parking2.addCar(new Car(888,"VDADJR", "Renault", "Rouge"));											List<Parking> parkings  = new ArrayList();
			parkings.add(parking1);
			parkings.add(parking2);																
			try {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder;					
				docBuilder = docFactory.newDocumentBuilder();					
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("parkings");
				doc.appendChild(rootElement);					
				for(int j = 0; j < parkings.size(); j++){
					Element parkElement = doc.createElement("parking");
					rootElement.appendChild(parkElement);
					parkElement.setAttribute("id", Integer.toString(j+1));						
					for(int i = 0; i < parkings.get(j).getCars().size(); i++){
						Element carElement = doc.createElement("car");
						parkElement.appendChild(carElement);
						carElement.setAttribute("id", Integer.toString(parkings.get(j).getCars().get(i).getId()));
						carElement.setAttribute("matricule", parkings.get(j).getCars().get(i).getImmat());							
						Element marqueElement = doc.createElement("marque");
						Text marque = doc.createTextNode(parkings.get(j).getCars().get(i).getModel());
						carElement.appendChild(marqueElement);
						marqueElement.appendChild(marque);							
						Element couleurElement = doc.createElement("couleur");
						Text couleur = doc.createTextNode(parkings.get(j).getCars().get(i).getColor());
						carElement.appendChild(couleurElement);
						couleurElement.appendChild(couleur);							
					}
				}					
				TransformerFactory transFactory = TransformerFactory.newInstance();
				Transformer transformer = transFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("parkings.xml"));					
				transformer.transform(source, result);	
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(new File("parkings.json"), parking1);
				
				Parking p = mapper.readValue(new File("parkings.json"), Parking.class);
				System.out.println(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		public static void test2() throws Exception
		{
			File xmlFile = new File("parkings.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement();
			
			NodeList carNodes = doc.getElementsByTagName("car");
		    for(int i=0; i<carNodes.getLength(); i++)
		    {
		        Node studentNode = carNodes.item(i);
		        if(studentNode.getNodeType() == Node.ELEMENT_NODE)
		        {
		            Element carElement = (Element) studentNode;
		            String matricule = carElement.getAttribute("matricule");
		            String marque = carElement.getElementsByTagName("marque").item(0).getTextContent();
		            String couleur = carElement.getElementsByTagName("couleur").item(0).getTextContent();
		            System.out.println("marque voiture = " + marque);
		            System.out.println("couleur voiture = " + couleur);
		            System.out.println("matricule voiture = " + matricule);
		        }
		    }
		}
}