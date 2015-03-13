package tartan.combination;


import tartan.*;


import java.util.ArrayList;


/**
 * Concrete Class
 */
public class Concatenation extends BinaryOperation {

    Tartan newTartan = null;

    @Override
    public Tartan performBinaryOperation(Tartan t1, Tartan t2) {


        ArrayList<TartanThread> newThreads = new ArrayList<TartanThread>();

        for (int i = 0; i < t1.getThreadList().size(); i++) {
            newThreads.add(t1.getThreadList().get(i));
        }

        for (int i = 0; i < t2.getThreadList().size(); i++) {
            newThreads.add(t2.getThreadList().get(i));
        }

        String requiredThreads = t1.getOriginalThreadList() + "," + t2.getOriginalThreadList();
        int requiredSettCount = Math.max(t1.getSettCount(),t2.getSettCount());
        int requiredDimensions = t1.getDimensions();
        boolean isSymmetrical = true;

        newTartan = new Tartan(newThreads, requiredSettCount, requiredDimensions, isSymmetrical);


        return newTartan;
    }

    @Override
    public Tartan getTartan() {
        return newTartan;

    }
}
