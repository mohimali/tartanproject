package tartan;

import java.awt.Color;

/**
 * This class records a single instance of a tartan.Tartan thread which
 * consists of a colour and size. It was intentionally named "TartanThread"
 * rather then "Thread" on its own because java already has a class called
 * "Thread". To avoid confusion it has been named TartanThread.
 */
public class TartanThread {

    Color colour;
    int threadCount;
    String colourName = "";
    String colourShortHand = "";
    public TartanThread(Color requiredThreadColour, String requiredColourShortHand ,int requiredThreadCount) {
        colour = requiredThreadColour;
        threadCount = requiredThreadCount;
        colourShortHand = requiredColourShortHand;
    }

    public TartanThread(Color requiredThreadColour, String requiredColourShortHand , int requiredThreadCount, String requiredColourName) {
        colour = requiredThreadColour;
        threadCount = requiredThreadCount;
        colourName = requiredColourName;
        colourShortHand = requiredColourShortHand;
    }


    public void setColourName(String requiredColourName)
    {
        colourName = requiredColourName;
    }

    public String getColourName()
    {
        return colourName;
    }


    public TartanThread getTartanThread() {
        return this;
    }

    public Color getColour() {
        return colour;

    }


    public int getThreadCount() {
        return threadCount;

    }

    public String toString() {
        return "Colour:" + colour + " Size: " + threadCount + " Name: " + colourName;

    }


}
