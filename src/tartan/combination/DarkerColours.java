package tartan.combination;



import tartan.*;


import java.awt.*;
import java.util.ArrayList;


/**
 * Concrete Class
 */
public class DarkerColours extends UnaryOperation {

    Tartan newTartan = null;

    @Override
    public Tartan performUnaryOperation(Tartan t1) {


        ArrayList<TartanThread> newThreads = new ArrayList<TartanThread>();

        // Take even element e.g remember 0 index so 0,2,4,6
        for(int i=0;i < t1.getThreadList().size();i++)
        {


            Color originalColour = t1.getThreadList().get(i).getColour();
            int originalSize = t1.getThreadList().get(i).getSize();

            Color darkerColour = originalColour.darker();

            for(int x=0;x<2;x++)
            {
                darkerColour = darkerColour.darker();
            }


            TartanThread newC = new TartanThread(darkerColour,originalSize);
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
