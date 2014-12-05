package tartan.combination;



import tartan.*;


import java.awt.*;
import java.util.ArrayList;


/**
 * Concrete Class
 */
public class BrighterColours extends UnaryOperation {

    Tartan newTartan = null;

    @Override
    public Tartan performUnaryOperation(Tartan t1) {


        ArrayList<TartanThread> newThreads = new ArrayList<TartanThread>();

        // Take even element e.g remember 0 index so 0,2,4,6
        for(int i=0;i < t1.getThreadList().size();i++)
        {


            Color originalColour = t1.getThreadList().get(i).getColour();
            int originalSize = t1.getThreadList().get(i).getSize();

            Color brighterColour =  originalColour.brighter();


            for(int x=0;x<10;x++)
            {
                brighterColour = brighterColour.brighter();
            }

            TartanThread newC = new TartanThread(brighterColour,originalSize);
            newThreads.add(newC);
        }



        String requiredThreads = "New Model of t1";
        int requiredSettCount = t1.getSettCount();
        int requiredDimensions = t1.getDimensions();
        boolean isSymmetrical = true;

        newTartan = new Tartan(newThreads,requiredSettCount,requiredDimensions,isSymmetrical);
        //newTartan.setThreadArray(newThreads);
        return newTartan;
    }

    @Override
    public Tartan getTartan() {
        return newTartan;

    }
}
