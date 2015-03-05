package tartan.TartanUI;


import tartan.Tartan;
import tartan.TartanThread;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Mohim on 31/01/2015.
 */
public class TartanModel {

    private ArrayList<Tartan> tartansList = new ArrayList<Tartan>();
    private ArrayList<TartanThread> tartanThreadList = new ArrayList<TartanThread>();
    private static int tartanSize = 400;
    private static int tartanSettCount = 2;
    private Tartan tartan = new Tartan(tartanSize,tartanSettCount);

    private static String originalThreads = "K4,G4,O4,R50,K50,Y4,B2,M1,P1,M10";

    //NOTE COULD USE DIFFFERENT COLOURS STANDARD MODERN, ETC AND THESE COULD BE SEPARE TABS IN LIST YO

    public static void main(String[] args) {
        Tartan t = new Tartan(400,1);

        t.addThread(Color.red, 2, "ASDA", "a");
        t.addThread(Color.red, 1, "BASDA","b");
        t.addThread(Color.red, 1, "BASDA","b");
        System.out.println(t);


    }


    public TartanModel() {

        initTartan();

    }

    public void initTartan() {
        Tartan tartan = new Tartan(originalThreads, 2, tartanSize, true);
        addTartan(tartan);

    }

    public void addTartan(Tartan tartan) {
        tartansList.add(tartan);
    }

    public Tartan getTartan() {
        return tartan;
    }


    protected ArrayList<TartanThread> getTartanThreadList() {
        return tartan.getThreadList();
    }

    public void addTartanThread(Color myColour, int myThreadCount, String myColourName, String colourShortHand) {

        TartanThread newThread = new TartanThread(myColour, colourShortHand, myThreadCount, myColourName);
        tartan.addThread(myColour, myThreadCount, myColourName, colourShortHand);
        //tartan.toString();
    }


    protected void removeTartanThread(int index) {
        tartan.getThreadList().remove(index);
    }

    protected void swapTartanThread(int firstIndex, int secondIndex) {
        // BETTER TO STORE THIS INFORMATION IN THE TARTAN CLASS LATER
        TartanThread left = tartan.getThreadList().get(firstIndex);
        TartanThread right = tartan.getThreadList().get(secondIndex);

        //SWAP THE LEFT WITH THE RIGHT AND VICE VERSA
        tartan.getThreadList().set(firstIndex, right);
        tartan.getThreadList().set(secondIndex, left);
    }

    protected String getOriginalThreads() {
        return originalThreads;
    }

    protected void setOriginalThreads(String requiredThreads) {
        originalThreads = requiredThreads;
    }

    public void resetTartan() {

        //RESET ALL VARIABLES
        tartansList = new ArrayList<Tartan>();
        tartanThreadList = new ArrayList<TartanThread>();
        tartan = new Tartan(tartanSize,tartanSettCount);

    }


    public void updateThreadDetails(int rowIndex, Color chosenColour, int chosenThreadCount,
                                    String chosenColourName, String colourShortHand) {

        //rowIndex, chosenColour, chosenThreadCount, chosenColourName, colourShortHand
        tartan.updateThreadDetails(rowIndex, chosenColour, chosenThreadCount, chosenColourName, colourShortHand);
    }

    public void removeThreadRow(int rowIndex) {
        tartan.removeThread(rowIndex);
    }


    public void updateColourRow(int myRowIndex, Color myColour, String myName, String colourShortHand) {

        //myRowIndex, myColour, myName, colourShortHand
        tartan.updateColourDetails(myRowIndex, myColour, myName, colourShortHand);
    }


    public void updateSettCount(int mySettCount) {
        tartan.updateSettCount(mySettCount);
    }

    public void replaceThreadList(ArrayList<TartanThread> myNewThreads, int mySettCount, int dimension, boolean symmetric) {
        tartan = new Tartan(myNewThreads, mySettCount, dimension, symmetric);

    }

    public int getSettCount() {
        return tartan.getSettCount();
    }
}