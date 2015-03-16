package tartan.combination;


import tartan.Tartan;
import tartan.TartanThread;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Concrete Class
 */
public class RandomiseTogether extends BinaryOperation {

    Tartan newTartan = null;

    @Override
    public Tartan performBinaryOperation(Tartan t1, Tartan t2) {


        ArrayList<TartanThread> newThreads = new ArrayList<TartanThread>();
        ArrayList<TartanThread> shuffledListColoursT1 = new ArrayList<TartanThread>();
        ArrayList<TartanThread> shuffledListColoursT2 = new ArrayList<TartanThread>();

        for (int i = 0; i < t1.getThreadList().size(); i++) {
            shuffledListColoursT1.add(t1.getThreadList().get(i));
        }

        for (int i = 0; i < t2.getThreadList().size(); i++) {
            shuffledListColoursT2.add(t2.getThreadList().get(i));
        }


        Collections.shuffle(shuffledListColoursT1);
        Collections.shuffle(shuffledListColoursT2);


        //STORE EVERYTHING = TARTAN THREAD

        //STORE THREADCOUNTS


        for (int i = 0; ((i < t1.getThreadList().size()) || (i < t2.getThreadList().size())); i++) {

            if(i < t1.getThreadList().size()) {
                Color origColour = shuffledListColoursT1.get(i).getColour();
                String origShortHand = shuffledListColoursT1.get(i).getColourShortHand();
                String origColourName = shuffledListColoursT1.get(i).getColourName();
                int origThreadCount = shuffledListColoursT1.get(i).getThreadCount();


                TartanThread newThread1 = new TartanThread(origColour, origShortHand,
                        origThreadCount, origColourName);
                newThreads.add(newThread1);
            }

            if(i < t2.getThreadList().size()) {
                Color origColour2 = shuffledListColoursT2.get(i).getColour();
                String origShortHand2 = shuffledListColoursT2.get(i).getColourShortHand();
                String origColourName2 = shuffledListColoursT2.get(i).getColourName();
                int origThreadCount2 = shuffledListColoursT2.get(i).getThreadCount();
                TartanThread newThread2 = new TartanThread(origColour2, origShortHand2,
                        origThreadCount2, origColourName2);


                newThreads.add(newThread2);
            }

            // Color requiredThreadColour, String requiredColourShortHand ,
            // int requiredThreadCount, String requiredColourName

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
