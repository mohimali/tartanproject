package tartan.TartanUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
**  followed this guide http://www.java-samples.com/showtutorial.php?tutorialid=152
 */
public class XMLParserColours extends DefaultHandler{

    private ArrayList<PaletteColour> coloursList;
    private String tempVal;
    private PaletteColour tempCol;
    public XMLParserColours(){
        coloursList = new ArrayList<PaletteColour>();
        parseDocument();
    }

    public ArrayList getColoursArray() {
        return coloursList;
    }

    private void parseDocument() {

        //get a factory
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {

            //get a new instance of parser
            SAXParser sp = spf.newSAXParser();
            //parse the file and also register this class for call backs


            //System.out.println("XMLLoad: "  + this.getClass().getResource("resources/xml/palette.xml"));
            //System.out.println("me" + test);

            try{
                // CHECK IF THIER IS A FILE ALREADY WITH CUSTOM DATA
                sp.parse("palette.xml", this);
                System.out.println("Using file");
            }
            catch(Exception e)
            {
                // ELSE USE EMBED FROM .JAR
                String tempURL = this.getClass().getResource("resources/xml/palette.xml").toString();
                System.out.println("Using resource");
                sp.parse(tempURL, this);
            }


        }catch(SAXException se) {
            se.printStackTrace();
        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch (IOException ie) {
            ie.printStackTrace();
        }
    }


    private void printColours(){

        System.out.println("No of Colours '" + coloursList.size() + "'.");

        Iterator it = coloursList.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }


    //Event Handlers
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //reset
        tempVal = "";
        if(qName.equalsIgnoreCase("Colour")) {
            //create a new instance of Colour
            tempCol = new PaletteColour();
            tempCol.setType(attributes.getValue("type"));
        }
    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch,start,length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {


        //String type, int id, String colour, String code
        if(qName.equalsIgnoreCase("Colour")) {
            //ADD TO LIST
            coloursList.add(tempCol);

        }else if (qName.equalsIgnoreCase("Id"))
        {
            //System.out.println(tempVal);
            tempCol.setID(Integer.parseInt(tempVal));
        }
        else if (qName.equalsIgnoreCase("Name"))
        {
            tempCol.setName(tempVal);
        }
        else if (qName.equalsIgnoreCase("Code"))
        {
            tempCol.setCode(tempVal);
            System.out.println("Code: " + tempVal);
        }
        else if(qName.equalsIgnoreCase("ShortHand"))
        {
            tempCol.setShortHand(tempVal);
            System.out.println("ShortHand: " + tempVal);
        }

    }

    public static void main(String[] args){
        XMLParserColours xpc = new XMLParserColours();
        xpc.getColoursArray();
    }

}
