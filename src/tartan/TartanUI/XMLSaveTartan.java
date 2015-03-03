package tartan.TartanUI;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import tartan.TartanThread;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

// xerces parser - jdk1.5


//followed this guide http://www.java-samples.com/showtutorial.php?tutorialid=152
public class XMLSaveTartan {

    //No generics
    ArrayList<TartanThread> threads = new ArrayList<TartanThread>(); // EMPTY STUFF
    Document dom;
    int settCount = 2; // DEFAULT ENSURE IT CHANGES
    int index=1;

    public XMLSaveTartan() {
        //initialize the list
        //loadXMLFromArray(null);
        //Get a DOM object
        //createDocument();
        //createDOMTree();
    }

    public void updateTartanXML(ArrayList<TartanThread> threadData,int settCount,String fileName)
    {
        index = 1;
        threads = new ArrayList<TartanThread>();
        threads = threadData;
        //initialize the list
        //loadXMLFromArray(null);

        //Get a DOM object
        createDocument(threadData);

        createDOMTree();

        //Save to file
        this.saveToFile(fileName);
    }


    public void saveToFile(String fileName){
        printToFile(fileName);

    }




    /**
     * Create XML TREE
     */
    private void createDocument(ArrayList<TartanThread> threadData) {

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

        //create the root element <SettSpec>
        Element rootEle = dom.createElement("SettSpec");
        dom.appendChild(rootEle);

        //No enhanced for
        Iterator it  = threads.iterator();
        while(it.hasNext()) {
            TartanThread pc = (TartanThread)it.next();
            //For each Thread object  create <Thread> element and attach it to root
            Element threadEle = createThreadElement(pc);
            rootEle.appendChild(threadEle);
        }

    }


    private Element createThreadElement(TartanThread pc){

        Element threadEle = dom.createElement("Thread");
        
        System.out.println(pc);
        //String type, int id, String name, String code

        //create id element and id text node and attach it to colourElement
        Element idEle = dom.createElement("ID");
        Text idText = dom.createTextNode(index + "");
        idEle.appendChild(idText);
        threadEle.appendChild(idEle);

        //create name element and name text node and attach it to colourElement
        Element nameEle = dom.createElement("ColourName");
        Text nameText = dom.createTextNode(pc.getColourName() + "");
        nameEle.appendChild(nameText);
        threadEle.appendChild(nameEle);

        //create code element and code text node and attach it to colourElement
        Element codeEle = dom.createElement("ColourCode");
        Text codeText = dom.createTextNode(pc.getColourCode());
        codeEle.appendChild(codeText);
        threadEle.appendChild(codeEle);

        //create code element and code text node and attach it to colourElement
        Element shortHandEle = dom.createElement("ShortHand");
        Text shortHandText = dom.createTextNode(pc.getColourShortHand());
        shortHandEle.appendChild(shortHandText);
        threadEle.appendChild(shortHandEle);

        //create code element and code text node and attach it to colourElement
        Element threadCountHandEle = dom.createElement("ThreadCount");
        Text threadCountText = dom.createTextNode(pc.getThreadCount() + "");
        threadCountHandEle.appendChild(threadCountText);
        threadEle.appendChild(threadCountHandEle);

        index++;
        return threadEle;
    }

    /**
     * This method uses Xerces specific classes
     * prints the XML document to file.
     */
    private void printToFile(String fileName){

        try
        {
            //print
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);

            File myFile = new File(fileName);
            //to generate output to console use this serializer
            //XMLSerializer serializer = new XMLSerializer(System.out, format);

            //System.out.println("XMLSave: "  + this.getClass().getResource("resources/xml/palette.xml"));
            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(myFile), format);


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

