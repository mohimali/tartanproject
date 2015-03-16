package tartan;

import tartan.TartanUI.PaletteColour;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mohim Ali on 08/11/2014.
 */
public class ThreadFactory {


    public static Color K = Color.BLACK;  //BLACK
    public static Color T = new Color(165, 42, 42);  //BROWN
    public static Color N = Color.GRAY; // GREY

    public static Color B = Color.BLUE; // BLUE
    public static Color G = Color.GREEN; // GREEN
    public static Color O = Color.ORANGE; // ORANGE
    public static Color P = Color.PINK; // PINK
    public static Color R = Color.RED; // RED
    public static Color W = Color.WHITE; // WHITE
    public static Color Y = Color.YELLOW; // YELLOW
    public static Color M = Color.MAGENTA; // MAGENTA
    public static Color C = Color.CYAN; // CYAN

    // Patterns - Eager Instantiation
    ArrayList<TartanThread> threads = new ArrayList<TartanThread>();

    String accumulatedErrors = "";
    boolean errorsDetected = false;

    private TartanThread tartanThreadSpec[];

    public boolean getDetectedErrors() {
        return errorsDetected;
    }

    public String getAccumulatedErrors() {
        return accumulatedErrors;
    }

    public void getShortHandNotations() {

    }

    public ArrayList<TartanThread> buildThreadX(String originalSett, ArrayList<PaletteColour> coloursArray) {

        errorsDetected = false;
        accumulatedErrors = "";
        threads = new ArrayList<TartanThread>();
        String[] parts = originalSett.split(",");
        Pattern p = Pattern.compile("([a-zA-Z]+)(\\d+)"); //Pattern.compile("([a-z]+)([0-9]+)");


        for (int i = 0; i < parts.length; i++) {
            String currentPart = parts[i];
            System.out.println("Part: " + currentPart);
            Matcher m = p.matcher(currentPart);

            while (m.find()) {
                String extractedThreadShortHand = m.group(1);
                int extractedThreadCount = Integer.parseInt(m.group(2));

                if (extractedThreadCount > 99) {
                    errorsDetected = true;
                    accumulatedErrors += "Your thread count " + extractedThreadCount +
                            " is greater than the limit of 99 \n";
                }

                //System.out.println("extractedThreadShortHand: " + extractedThreadShortHand);
                //System.out.println("extractedThreadCount:" + extractedThreadCount);


                if (!m.find()) {
                    // COULDNT FIND IT
                    //System.out.println("Couldnt find anything");
                }

                Color calcColour = null;
                String calcColourName = null;
                boolean found = false;
                int index = 0;
                while (!found && (index < coloursArray.size())) {

                    //System.out.println("index: " + index + "myShort: " + extractedThreadShortHand + "yourShort" + coloursArray.get(index).getShortHand());
                    if (extractedThreadShortHand.trim().equals(coloursArray.get(index).getShortHand().trim())) {
                        calcColour = coloursArray.get(index).getColour();
                        calcColourName = coloursArray.get(index).getName();
                        found = true;
                        //System.out.println("true");
                    }

                    index++;
                } // inner while


                TartanThread tartanThread = new TartanThread(calcColour, extractedThreadShortHand, extractedThreadCount, calcColourName);

                if (calcColour == null || extractedThreadShortHand == null || calcColourName == null) {
                    errorsDetected = true;


                }
                //System.out.println("tartanThread" + tartanThread);
                threads.add(tartanThread);

            } //  outer while

        } //END LOOPS

        System.out.println("ThreadTime");

        String createdNotation = "";

        for (int x = 0; x < threads.size(); x++) {
            System.out.println(threads.get(x).toString());

            String currentShortHand = threads.get(x).getColourShortHand();
            int currentThreadCount = threads.get(x).getThreadCount();

            if (x < threads.size() - 1) {
                createdNotation += currentShortHand + currentThreadCount + ",";
            } else {
                createdNotation += currentShortHand + currentThreadCount;
            }
        }

        System.out.println("originalSett: " + originalSett);
        System.out.println("createdNotation: " + createdNotation);

        //SANITY CHECK

        if (!(originalSett.equals(createdNotation))) {
            errorsDetected = true;

            // ACCUMULATE WHICH ERRORS DETECTED
            int i = 0;
            while (i < threads.size()) {
                TartanThread tempTartanThread = threads.get(i);
                String createdThread = "" + threads.get(i).getColourShortHand() + threads.get(i).getThreadCount();
                if (!parts[i].equals(createdThread)) {
                    accumulatedErrors += "" + parts[i] + " not recognised as a valid Colour and/or ThreadCount \n";
                }

                i++;
            }
        }

        /*
        for (int x = 0; x < coloursArray.size(); x++) {
            //System.out.println(coloursArray.get(x).getName());
        }
        */


        return threads;
    }


        public static synchronized ArrayList<TartanThread> buildThread(String string) {


            String[] parts = string.split(",");


            ArrayList<TartanThread> threads = new ArrayList<TartanThread>();

            Map<String, Color> myColor = new HashMap<String, Color>();

            myColor.put("K", K);
            myColor.put("T", T);
            myColor.put("N", N);
            myColor.put("B", B);
            myColor.put("G", G);
            myColor.put("O", O);
            myColor.put("P", P);
            myColor.put("R", R);
            myColor.put("W", W);
            myColor.put("Y", Y);
            myColor.put("M", M);
            myColor.put("C", C);


            int index = 0;
            while (index < parts.length) {
                Pattern p = Pattern.compile("([a-zA-Z]+)(\\d+)");
                Matcher m = p.matcher(parts[index]); //Blue 6, Green 8
                while (m.find()) {
                    String colourToken = m.group(1);  // Thread Color
                    String sizeToken = m.group(2); // Thread Size
                    threads.add(new TartanThread(myColor.get(colourToken), "X", Integer.parseInt(sizeToken)));
                }
                index++;
            } // OUTER WHILE

            return threads;
        }

    public ArrayList<TartanThread> getThreadList() {
        return threads;
    }

}

