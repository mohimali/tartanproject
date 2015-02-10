package tartan.TartanUI;


import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

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
    ArrayList<PaletteColour> paletteColours = new ArrayList<PaletteColour>();
    Document dom;


    public XMLSaveColours() {

        //initialize the list
        loadData(null,"");

        //Get a DOM object
        createDocument();

        createDOMTree();

        //Save to file
        //this.saveToFile();

    }

    public void addColour(Color newColour,String name)
    {
        paletteColours = new ArrayList<PaletteColour>();
        //initialize the list
        loadData(newColour,name);

        //Get a DOM object
        createDocument();

        createDOMTree();

        //Save to file
        this.saveToFile();
    }


    public void saveToFile(){
        printToFile();

    }


    private void loadData(Color c,String name){

        //EXTRACT THE ORIGINAL XML INFO FROM palette.xml and use the colours stored their
        XMLParserColours xpc = new XMLParserColours();
        ArrayList<PaletteColour> coloursArray = xpc.getColoursArray();

        //type,id,name,code

        if(c != null)
        {
            //padded zeros
            String rgb = ("" + String.format("%03d", c.getRed()) + ","
                             + String.format("%03d", c.getGreen())  + ","
                             + String.format("%03d", c.getBlue()));
            PaletteColour newColour = new PaletteColour("Colour",coloursArray.size()+1,name,
                    rgb,1  );
            coloursArray.add(newColour);

        }

        for(int i=0; i < coloursArray.size();i++)
        {
            paletteColours.add(coloursArray.get(i));

        }

        //paletteColours.add(new PaletteColour("Colour",1,"Black","0x000000"));

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

        //create the root element <Pallete>
        Element rootEle = dom.createElement("Pallete");
        dom.appendChild(rootEle);

        //No enhanced for
        Iterator it  = paletteColours.iterator();
        while(it.hasNext()) {
            PaletteColour pc = (PaletteColour)it.next();
            //For each Colour object  create <Colour> element and attach it to root
            Element colourEle = createColourElement(pc);
            rootEle.appendChild(colourEle);
        }

    }


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
                    new FileOutputStream(new File("src/tartan/TartanUI/xml/palette.xml")), format);


            serializer.serialize(dom);

        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }


    public static void main(String args[]) {

        //create an instance
        //XMLSaveColours xce = new XMLSaveColours();

        //xce.addColour(Color.red,"chicken");
        //Save to file
        //xce.saveToFile();
    }
}

