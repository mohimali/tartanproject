package tartan.TartanUI;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import tartan.Tartan;
import tartan.TartanThread;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by Mohim on 31/01/2015.
 */
public class TartanModel {


    private String test = "HELLOWORLD";

    private ArrayList<Tartan> tartansList = new ArrayList<Tartan>();
    private ArrayList<TartanThread> tartanThreadList= new ArrayList<TartanThread>();
    private static int tartanSize = 400;

    private static String originalThreads = "K4,G4,O4,R50,K50,Y4,B2,M1,P1,M10";

    public static void main(String[] args)
    {
      TartanModel tm = new TartanModel();
      tm.loadColours();

    }

    public TartanModel()
    {
        loadColours();
        initTartan();

    }
    public void loadColours()
    {

    }

    Document dom;
    private void parseXmlFile(){
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = db.parse("employees.xml");


        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(SAXException se) {
            se.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void parseDocument(){
        //get the root element
        Element docEle = dom.getDocumentElement();

        //get a nodelist of elements
        NodeList nl = docEle.getElementsByTagName("Employee");
        if(nl != null && nl.getLength() > 0) {
            for(int i = 0 ; i < nl.getLength();i++) {

                //get the employee element
                Element el = (Element)nl.item(i);

                //get the Employee object
                Employee e = getEmployee(el);

                //add it to list
                myEmpls.add(e);
            }
        }
    }
    /**
     * I take an employee element and read the values in, create
     * an Employee object and return it
     */
    private Employee getEmployee(Element empEl) {

        //for each <employee> element get text or int values of
        //name ,id, age and name
        String name = getTextValue(empEl,"Name");
        int id = getIntValue(empEl,"Id");
        int age = getIntValue(empEl,"Age");

        String type = empEl.getAttribute("type");

        //Create a new Employee with the value read from the xml nodes
        Employee e = new Employee(name,id,age,type);

        return e;
    }


    /**
     * I take a xml element and the tag name, look for the tag and get
     * the text content
     * i.e for <employee><name>John</name></employee> xml snippet if
     * the Element points to employee node and tagName is 'name' I will return John
     */
    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if(nl != null && nl.getLength() > 0) {
            Element el = (Element)nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }

        return textVal;
    }


    /**
     * Calls getTextValue and returns a int value
     */
    private int getIntValue(Element ele, String tagName) {
        //in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele,tagName));
    }

    PriorityQueue myEmpls;
    private void printData(){


        System.out.println("No of Employees '" + myEmpls.size() + "'.");

        Iterator it = myEmpls.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }



    protected String getTest() {
        return test;



    }

    public void initTartan()
    {
        Tartan tartan = new Tartan(originalThreads, 2, 400, true);
        addTartan(tartan);

    }


    public void addTartan(Tartan tartan)
    {
        tartansList.add(tartan);
    }

    public Tartan getTartan(int index)
    {
        return tartansList.get(index);
    }


    protected String getOriginalThreads() {
        return originalThreads;
    }

    protected void setOriginalThreads(String requiredThreads) {
         originalThreads = requiredThreads;
    }

    protected ArrayList<TartanThread> getTartanThreadList() {
        return tartanThreadList;
    }

    protected void addTartanThread(TartanThread thread) {
        tartanThreadList.add(thread);
    }

    protected void removeTartanThread(int index) {
        tartanThreadList.remove(index);
    }

    protected void swapTartanThread(int firstIndex,int secondIndex) {
        TartanThread left = tartanThreadList.get(firstIndex);
        TartanThread right = tartanThreadList.get(secondIndex);

        //SWAP THE LEFT WITH THE RIGHT AND VICE VERSA
        tartanThreadList.set(firstIndex,right);
        tartanThreadList.set(secondIndex,left);

    }






}