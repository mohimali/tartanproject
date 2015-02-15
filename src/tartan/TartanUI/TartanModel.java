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
    //NOTE COULD USE DIFFFERENT COLOURS STANDARD MODERN, ETC AND THESE COULD BE SEPARE TABS IN LIST YO

    public static void main(String[] args)
    {
      //TartanModel tm = new TartanModel();
      //tm.loadColours();

    }

    public TartanModel()
    {

        initTartan();

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