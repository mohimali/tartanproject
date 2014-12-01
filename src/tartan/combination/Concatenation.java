package tartan.combination;



import tartan.*;



import java.util.ArrayList;


/**
 * Created by HomeM5 on 01/12/2014.
 */
public class Concatenation extends BinaryOperation {


    @Override
    public Tartan performUnaryOperation(Tartan t1, Tartan t2) {

        Tartan tartan = null;
        ArrayList<TartanThread> newThreads = new ArrayList<TartanThread>();

        for(int i=0;i < t1.getThreadList().size();i++)
        {
            newThreads.add(t1.getThreadList().get(i));
        }

        for(int i=0;i < t1.getThreadList().size();i++)
        {
            newThreads.add(t1.getThreadList().get(i));
        }

        return null;
    }

    @Override
    public Tartan getTartan() {
        return null;

    }
}
