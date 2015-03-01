package tartan.TartanUI;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tartan.TartanThread;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl;
import com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl;

// xerces parser - jdk1.5
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/*
**  followed this guide http://www.java-samples.com/showtutorial.php?tutorialid=152
 */
public class XMLParserTartan extends DefaultHandler {

    private ArrayList<TartanThread> threadList;
    private String tempVal;
    private TartanThread tempThread;
    private int settCount;
    boolean errorsDetected = false;
    private String errorMessages = "";

    public String getErrorMessages()
    {
        return errorMessages;
    }
    public XMLParserTartan() {
        threadList = new ArrayList<TartanThread>();
        parseDocument();
    }

    public ArrayList getThreadList() {
        return threadList;
    }


    private void parseDocument() {

        //File myFile = new File("SettSchema.xml");
        //System.out.println(myFile.getAbsoluteFile());


        // FIX FOR UPDATED XERCES ELSE WONT LET ME SET SCHEMA
        System.setProperty("javax.xml.parsers.SAXParserFactory", "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");


        try {
            //Schema schema = null;
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            SAXParserFactory spf = SAXParserFactory.newInstance();

            Schema schema = factory.newSchema(new File("SettSchema.xml"));

            spf.setSchema(schema);

            //VALIDATION


            Validator validator = schema.newValidator();
            validator.getErrorHandler();
            try {
                validator.validate(new StreamSource("sett1.xml"));
            } catch (Exception e) {
                errorsDetected = true;
                errorMessages += e.getMessage() + "\n";
            }
            //get a new instance of parser
            SAXParser sp = spf.newSAXParser();
            //parse the file and also register this class for call backs


            //System.out.println("XMLLoad: "  + this.getClass().getResource("resources/xml/palette.xml"));
            //System.out.println("me" + test);

            try {

                if (!errorsDetected) {
                    sp.parse("sett1.xml", this);
                    System.out.println("Using sett1.xml");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void printThreads() {

        System.out.println("No of threads '" + threadList.size() + "'.");
        System.out.println("SettCount '" + settCount + "'.");
        Iterator it = threadList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    private void printErrorMessages() {

        System.out.println(errorMessages);
    }


    //Event Handlers
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //reset
        tempVal = "";


        if (qName.equalsIgnoreCase("Thread")) {
            //create a new instance of Thread
            tempThread = new TartanThread();
            //tempThread.setType(attributes.getValue("type"));
        }
    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        //Color colour;
        //int threadCount;
        // String colourName = "";
        //String colourShortHand = "";

        //String type, int id, String colour, String code
        if (qName.equalsIgnoreCase("Thread")) {
            //ADD TO LIST
            threadList.add(tempThread);

        } else if (qName.equalsIgnoreCase("ID")) {
            //System.out.println("id: " + tempVal);
            //tempThread.setID(Integer.parseInt(tempVal));
        } else if (qName.equalsIgnoreCase("ColourName")) {
            tempThread.setColourName(tempVal);
        } else if (qName.equalsIgnoreCase("ColourCode")) {
            tempThread.setColourCode(tempVal);
            //System.out.println("Code: " + tempVal);
        } else if (qName.equalsIgnoreCase("ColourShortHand")) {
            tempThread.setColourShortHand(tempVal);
            //System.out.println("ShortHand: " + tempVal);
        } else if (qName.equalsIgnoreCase("ThreadCount")) {
            tempThread.setThreadCount(Integer.parseInt(tempVal));
        } else if (qName.equalsIgnoreCase("SettCount")) {
            this.settCount = Integer.parseInt(tempVal);
        }

    }

    public static void main(String[] args) {
        XMLParserTartan xpt = new XMLParserTartan();

        xpt.printThreads();

        xpt.printErrorMessages();

    }

}
