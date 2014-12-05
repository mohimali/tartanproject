package tartan.combination;


import tartan.*;


import java.util.ArrayList;


/**
 * Concrete Class
 */
public class OddEven extends BinaryOperation {

    Tartan newTartan = null;

    @Override
    public Tartan performBinaryOperation(Tartan t1, Tartan t2) {


        ArrayList<TartanThread> newThreads = new ArrayList<TartanThread>();

        // Take even element e.g remember 0 index so 0,2,4,6
        for (int i = 0; i < t1.getThreadList().size(); i = i + 2) {
            newThreads.add(t1.getThreadList().get(i));
        }

        // Take even element e.g remember 0 index so 1,3,5,7
        for (int i = 1; i < t2.getThreadList().size(); i = i + 2) {
            newThreads.add(t2.getThreadList().get(i));
        }

        String requiredThreads = "Combined Model of t1+t2";
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
