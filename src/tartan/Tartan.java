package tartan;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mohim Ali on 08/11/2014.
 */
public class Tartan {

    // tartan.Tartan Dimensions
    int width = 1;
    int height = 1;


    String originalThreadList;
    // Is the tartan a Asymmetrical tartan or a Symmetrical one.
    ArrayList<TartanThread> threads = new ArrayList<TartanThread>();
    int settCount;
    boolean isSymmetrical = true;
    public double threadSizes[];

    public Tartan(String requiredThreads,
                  int requiredSettCount,
                  int requiredDimension,
                  boolean requiredIsSymmetrical) {

        originalThreadList = requiredThreads;
        threads = ThreadFactory.buildThread(requiredThreads);
        settCount = requiredSettCount;
        width = height = requiredDimension; //Its a square so width height are same
        isSymmetrical = requiredIsSymmetrical;

        threadSizes = new double[]{};
        threadSizes = computeThreadSizes(threads);

    }



    public double[] computeThreadSizes( ArrayList<TartanThread> threadList)
    {
        double total = 0;
        double newThreadSizes[] = new double[threadList.size()];


        for (TartanThread thread :threadList ) {
            total += thread.getSize();

        }


        for (int x = 0; x <threadList.size(); x++) {
            newThreadSizes[x] = ((threadList.get(x).getSize() / total) * width) / (settCount);

        }

        return newThreadSizes;

    }


    public void updateSettCount(int newSettCount) {
        settCount = newSettCount;
    } // updateSettCount


    public int getSettCount() {
        return settCount;
    } // getSettCount

    public double getThreadSizes(int i) {
        return threadSizes[i];
    } // getThreadSizes

    public Color getThreadColour(int i) {
        return threads.get(i).getColour();
    } // getThreadSizes

    public int getThreadSizesCount() {
        return threads.size();

    } // getThreadSizes

    public double getDimensions() {
        return width;
    } // getThreadSizes

    public String toString() {
        String threadAll = "";

        for (TartanThread item : threads)
            threadAll += item.toString() + ",";

        return "tartan.Tartan: \n" +
                "OriginalThreads: " + originalThreadList + "\n" +
                "Real Threads: " + threadAll + "\n" +
                "IsSymmetrical: " + isSymmetrical + "\n" +
                "settCount: " + settCount + "\n" +
                "Width: " + width + "\n" +
                "Height: " + height + "\n" +
                "threadSizesActual: " + getRealThreadSizes() +  "\n";
    }

    public String getRealThreadSizes()
    {
        String sizes="";

        for (double size : threadSizes)
            sizes += sizes + size + ",";

        return sizes;
    }


}
