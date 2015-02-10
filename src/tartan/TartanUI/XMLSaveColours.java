package tartan.TartanUI;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

//For jdk1.5 with built in xerces parser
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

//For JDK 1.3 or JDK 1.4  with xerces 2.7.1
//import org.apache.xml.serialize.XMLSerializer;
//import org.apache.xml.serialize.OutputFormat;

public class XMLSaveColours {

    //No generics
    ArrayList<PaletteColour> myData;
    Document dom;

    public XMLSaveColours() {
        //create a list to hold the data
        myData = new  ArrayList<PaletteColour>();

        //initialize the list
        loadData();

        //Get a DOM object
        createDocument();
    }


    public void runExample(){
        System.out.println("Started .. ");
        createDOMTree();
        printToFile();
        System.out.println("Generated file successfully.");
    }


    /**
     * Add a list of books to the list
     * In a production system you might populate the list from a DB
     */
    private void loadData(){

        myData.add(new PaletteColour("Colour",1,"Black","0x000000"));
        myData.add(new PaletteColour("Colour",2,"White","0xFFFFFF"));
        myData.add(new PaletteColour("Colour",3,"Navy","0x000080"));
        myData.add(new PaletteColour("Colour",4,"Blue","0x0000FF"));

    }

    /**
     * Using JAXP in implementation independent manner create a document object
     * using which we create a xml tree in memory
     */
    private void createDocument() {

        //get an instance of factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            //get an instance of builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //create an instance of DOM
            dom = db.newDocument();

        }catch(ParserConfigurationException pce) {
            //dump it
            System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
            System.exit(1);
        }

    }

    /**
     * The real workhorse which creates the XML structure
     */
    private void createDOMTree(){

        //create the root element <Books>
        Element rootEle = dom.createElement("Colour");
        dom.appendChild(rootEle);

        //No enhanced for
        Iterator it  = myData.iterator();
        while(it.hasNext()) {
            PaletteColour pc = (PaletteColour)it.next();
            //For each Book object  create <Book> element and attach it to root
            Element colourEle = createColourElement(pc);
            rootEle.appendChild(colourEle);
        }

    }

    /**
     * Helper method which creates a XML element <Book>
     * @param b The book for which we need to create an xml representation
     * @return XML element snippet representing a book
     */
    private Element createColourElement(PaletteColour pc){

        Element colourEle = dom.createElement("Colour");
        colourEle.setAttribute("type", pc.getType());

        //String type, int id, String name, String code

        //create id element and id text node and attach it to colourElement
        Element idEle = dom.createElement("Id");
        Text idText = dom.createTextNode(pc.getID() + "");
        idEle.appendChild(idText);
        colourEle.appendChild(idEle);

        //create name element and name text node and attach it to colourElement
        Element nameEle = dom.createElement("Name");
        Text nameText = dom.createTextNode(pc.getName());
        nameEle.appendChild(nameText);
        colourEle.appendChild(nameEle);

        //create code element and code text node and attach it to colourElement
        Element codeEle = dom.createElement("Code");
        Text codeText = dom.createTextNode(pc.getCode());
        codeEle.appendChild(codeText);
        colourEle.appendChild(codeEle);



        return colourEle;

    }

    /**
     * This method uses Xerces specific classes
     * prints the XML document to file.
     */
    private void printToFile(){

        try
        {
            //print
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);

            //to generate output to console use this serializer
            //XMLSerializer serializer = new XMLSerializer(System.out, format);


            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File("src/tartan/TartanUI/xml/newc.xml")), format);


            serializer.serialize(dom);

        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }


    public static void main(String args[]) {

        //create an instance
        XMLSaveColours xce = new XMLSaveColours();

        //run the example
        xce.runExample();
    }
}

