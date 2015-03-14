package tartan.combination;


import tartan.Tartan;
import tartan.TartanThread;

import java.awt.*;
import java.util.ArrayList;

/**
 * Concrete Class
 */
public class Tartanise extends UnaryOperation {

    Tartan newTartan = null;

    @Override
    public Tartan performUnaryOperation(Tartan t1) {


        ArrayList<TartanThread> newThreads = new ArrayList<TartanThread>();

        int tempSize = 1;
        int scaleFactor = 4;
        int total = t1.getThreadList().size();
        // Take even element e.g remember 0 index so 0,2,4,6
        for (int i = 0; i < t1.getThreadList().size(); i++) {

            int newSize;
            Color origColour = t1.getThreadList().get(i).getColour();
            String origShortHand = t1.getThreadList().get(i).getColourShortHand();
            String origColourName = t1.getThreadList().get(i).getColourName();
            int origThreadCount = t1.getThreadList().get(i).getThreadCount();


            if ((i % 2 ) == 0) {
                newSize = origThreadCount * scaleFactor;
                tempSize = origThreadCount;
            } else {
                newSize = tempSize;
            }


            // Color requiredThreadColour, String requiredColourShortHand ,
            // int requiredThreadCount, String requiredColourName
            TartanThread newThread = new TartanThread(origColour, origShortHand,
                    newSize, origColourName);
            newThreads.add(newThread);
        }


        String requiredThreads = "New Model of t1";
        int requiredSettCount = t1.getSettCount();
        int requiredDimensions = t1.getDimensions();
        boolean isSymmetrical = true;

        newTartan = new Tartan(newThreads, requiredSettCount, requiredDimensions, isSymmetrical);
        //newTartan.setThreadArray(newThreads);
        return newTartan;
    }

    @Override
    public Tartan getTartan() {
        return newTartan;

    }
}
