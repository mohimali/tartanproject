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
    public ArrayList<TartanThread> threads = new ArrayList<TartanThread>();
    int settCount;
    boolean isSymmetrical = true;
    public double threadCounts[];

    public String getOriginalThreadList() {
        return originalThreadList;

    }

    public void updateThreadList(ArrayList<TartanThread> newThreadList) {
        threads = newThreadList;

    }

    public ArrayList<TartanThread> getThreadList() {
        return threads;

    }


    public Tartan(int tartanSize) {

        settCount = 2;
        width = height = tartanSize; //Its a square so width height are same
        isSymmetrical = true;
        originalThreadList="";
        //threadSizes = new double[]{};
        //threadSizes = computeThreadSizes(threads);

    }

    public Tartan(ArrayList<TartanThread> requiredThreads,
                  int requiredSettCount,
                  int requiredDimension,
                  boolean requiredIsSymmetrical) {

        originalThreadList = "Combined Model";


        threads = requiredThreads;
        //threads = ThreadFactory.buildThread(requiredThreads);
        settCount = requiredSettCount;
        width = height = requiredDimension; //Its a square so width height are same
        isSymmetrical = requiredIsSymmetrical;

        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);

    }




    public Tartan(String requiredThreads,
                  int requiredSettCount,
                  int requiredDimension,
                  boolean requiredIsSymmetrical) {

        originalThreadList = requiredThreads;


        threads = ThreadFactory.buildThread(requiredThreads);
        settCount = requiredSettCount;
        width = height = requiredDimension; //Its a square so width height are same
        isSymmetrical = requiredIsSymmetrical;

        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);

    }


    public void setThreadArray(ArrayList<TartanThread> requiredList) {
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(requiredList);
    }

    public double[] computeThreadSizes(ArrayList<TartanThread> threadList) {
        double total = 0;
        double newThreadSizes[] = new double[threadList.size()];


        for (TartanThread thread : threadList) {
            total += thread.getThreadCount();

        }


        for (int x = 0; x < threadList.size(); x++) {
            newThreadSizes[x] = ((threadList.get(x).getThreadCount() / total) * width) / (settCount);

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
        return threadCounts[i];
    } // getThreadSizes

    public Color getThreadColour(int i) {
        return threads.get(i).getColour();
    } // getThreadColour

    public int getThreadSizesCount() {
        return threads.size();

    } // getThreadSizes

    public int getDimensions() {
        return width;
    } // getDimensions

    public String toString() {
        String threadAll = "";

        for (TartanThread item : threads) {
            threadAll += item.toString() + ",";
        }

        return "tartan.Tartan: \n" +
                "OriginalThreads: " + originalThreadList + "\n" +
                "Real Threads: " + threadAll + "\n" +
                "IsSymmetrical: " + isSymmetrical + "\n" +
                "settCount: " + settCount + "\n" +
                "Width: " + width + "\n" +
                "Height: " + height + "\n" +
                "threadSizesActual: " + getRealThreadSizes() + "\n";
    }

    public String getRealThreadSizes() {
        String sizes = "";

        for (double size : threadCounts)
            sizes += sizes + size + ",";

        return sizes;
    }


    public void addThread(Color myColour, int myThreadCount, String myColourName) {
        TartanThread tt = new TartanThread(myColour,myThreadCount,myColourName);

        threads.add(tt);
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);


    }

    public void updateThreadDetails(int rowIndex, Color chosenColour, int chosenThreadCount, String chosenColourName) {
        TartanThread tt = new TartanThread(chosenColour,chosenThreadCount,chosenColourName);

        threads.set(rowIndex,tt);
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);
    }

    public void removeThread(int rowIndex) {
        threads.remove(rowIndex);
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);
    }
}
