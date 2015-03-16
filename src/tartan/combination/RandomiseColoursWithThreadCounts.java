package tartan.combination;


import tartan.Tartan;
import tartan.TartanThread;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collections.*;

/**
 * Concrete Class
 */
public class RandomiseColoursWithThreadCounts extends UnaryOperation {

    Tartan newTartan = null;

    @Override
    public Tartan performUnaryOperation(Tartan t1) {


        ArrayList<TartanThread> newThreads = new ArrayList<TartanThread>();
        ArrayList<TartanThread> shuffledListColours = new ArrayList<TartanThread>();
        ArrayList<Integer> shuffledThreadCounts = new ArrayList<Integer>();

        for (int i = 0; i < t1.getThreadList().size(); i++) {
            shuffledListColours.add(t1.getThreadList().get(i));
            shuffledThreadCounts.add(t1.getThreadList().get(i).getThreadCount());
        }
        Collections.shuffle(shuffledListColours);
        Collections.shuffle(shuffledThreadCounts);




        //STORE EVERYTHING = TARTAN THREAD

        //STORE THREADCOUNTS


        for (int i = 0; i < t1.getThreadList().size(); i++) {
            Color origColour = shuffledListColours.get(i).getColour();
            String origShortHand = shuffledListColours.get(i).getColourShortHand();
            String origColourName = shuffledListColours.get(i).getColourName();
            int origThreadCount = shuffledThreadCounts.get(i);


            // Color requiredThreadColour, String requiredColourShortHand ,
            // int requiredThreadCount, String requiredColourName
            TartanThread newThread = new TartanThread(origColour, origShortHand,
                    origThreadCount, origColourName);
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
