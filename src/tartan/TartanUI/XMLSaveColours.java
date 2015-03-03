package tartan.TartanUI;

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

// xerces parser - jdk1.5
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;


//followed this guide http://www.java-samples.com/showtutorial.php?tutorialid=152
public class XMLSaveColours {

    //No generics
    ArrayList<PaletteColour> paletteColours = new ArrayList<PaletteColour>();
    Document dom;

    public XMLSaveColours() {
        //initialize the list
        loadXMLFromArray(null);
        //Get a DOM object
        createDocument();
        createDOMTree();
    }

    public void updateColoursXML(ArrayList<PaletteColour> pal)
    {
        paletteColours = new ArrayList<PaletteColour>();
        //initialize the list
        loadXMLFromArray(pal);

        //Get a DOM object
        createDocument();

        createDOMTree();

        //Save to file
        this.saveToFile();
    }


    public void saveToFile(){
        printToFile();

    }


    private void loadXMLFromArray(ArrayList<PaletteColour> coloursArray1){

        //EXTRACT THE ORIGINAL XML INFO FROM palette.xml and use the colours stored their
        ArrayList<PaletteColour> coloursArray;

        // FIRST CASE IS GETTING FROM XML FILE
        if (coloursArray1 == null) {
            XMLParserColours xpc = new XMLParserColours();
             coloursArray = xpc.getColoursArray();

        }
        // SECOND CASE IS GETTING FROM SAVED PALETTE GUI PASSED INTO coloursArray1
        else
        {
            coloursArray = coloursArray1;
        }

        // NEED TO TAKE NULL CASE
        for(int i=0; i < coloursArray.size();i++)
        {
            paletteColours.add(coloursArray.get(i));

        }

    }

    /**
     * Create XML TREE
     */
    private void createDocument() {

        //Factory instance
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            //BUILDER INSTANCE
            DocumentBuilder db = dbf.newDocumentBuilder();

            //DOC INSTANCE
            dom = db.newDocument();

        }catch(ParserConfigurationException pce) {
            //exit
            System.out.println("Couldn't initiate docbuilder. " + pce);
            System.exit(1);
        }

    }

    /**
     * The real workhorse which creates the XML structure
     */
    private void createDOMTree(){

        //create the root element <Palette>
        Element rootEle = dom.createElement("Palette");
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

        //create code element and code text node and attach it to colourElement
        Element shortHandEle = dom.createElement("ShortHand");
        Text shortHandText = dom.createTextNode(pc.getShortHand());
        shortHandEle.appendChild(shortHandText);
        colourEle.appendChild(shortHandEle);



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

            //System.out.println("XMLSave: "  + this.getClass().getResource("resources/xml/palette.xml"));
            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File("palette.xml")), format);


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

