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
    public double[] threadCounts;

    public String getOriginalThreadList() {
        return originalThreadList;

    }

    public void updateThreadList(ArrayList<TartanThread> newThreadList) {
        threads = newThreadList;

    }

    public ArrayList<TartanThread> getThreadList() {
        return threads;

    }


    public Tartan(int tartanSize, int mySettCount) {

        settCount = mySettCount;
        width = height = tartanSize; //Its a square so width height are same
        isSymmetrical = true;
        originalThreadList = "";
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);

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
        //TO ACCOUNT FOR THE NUMBER OF TIMES SETT APPEARS PER DIMENSIONS WE MULTIPLY BY 2
        double newThreadSizes[] = new double[threadList.size() * 2];

        //System.out.println("me1:"+threadList.size());
        for (TartanThread thread : threadList) {
            total += thread.getThreadCount();

        }

        //total = total * settCount; //TO ACCOUNT FOR THE NUMBER OF TIMES SETT APPEARS PER DIMENSIONS
        //System.out.println("totalX123: " + total);

        //RECORD THE FORWARDS THREADS HALF SETT

        total = total * 2;
        int index = 0;
        for (int x = 0; x < threadList.size(); x++) {
            //newThreadSizes[index] = (width / (threadList.size()*total)) * threadList.get(x).getThreadCount();
            newThreadSizes[index] = (threadList.get(x).getThreadCount() / total)   * width;
            //System.out.println("newThreadSizes: " + newThreadSizes[x]);
            index++;

        }

        for (int x = threadList.size()-1 ; x >=0; x--) {
            newThreadSizes[index] = (threadList.get(x).getThreadCount() / total) * width;
            //System.out.println("newThreadSizes: " + newThreadSizes[x]);
            index++;

        }

        //System.out.println("length: "+newThreadSizes.length);
        //System.out.println(index);


        return newThreadSizes;

    }


    public void updateSettCount(int newSettCount) {
        settCount = newSettCount;
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);
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
        //System.out.println("stup1: " + threadCounts.length);
        int total = 0;
        for (int x=0;x<threadCounts.length;x++) {
            sizes += (threadCounts[x] + ", ");
            total += threadCounts[x];
            //System.out.println("index: " + x);
        }

        sizes += "\ntotal: " + total;

        return sizes;
    }


    public void addThread(Color myColour, int myThreadCount, String myColourName, String colourShortHand) {
        TartanThread tt = new TartanThread(myColour, colourShortHand, myThreadCount, myColourName);

        threads.add(tt);
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);


    }

    public void updateThreadDetails(int rowIndex, Color chosenColour, int chosenThreadCount,
                                    String chosenColourName, String colourShortHand) {

        //rowIndex, chosenColour, chosenThreadCount, chosenColourName, colourShortHand
        TartanThread tt = new TartanThread(chosenColour, colourShortHand, chosenThreadCount, chosenColourName);

        threads.set(rowIndex, tt);
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);
    }

    public void removeThread(int rowIndex) {
        threads.remove(rowIndex);
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);
    }

    public void updateColourDetails(int myRowIndex, Color myColour, String myName, String colourShortHand) {

        //myRowIndex, myColour, myName, colourShortHand
        TartanThread tt = new TartanThread(myColour, colourShortHand, threads.get(myRowIndex).getThreadCount(), myName);
        threads.set(myRowIndex, tt);
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);
    }

    public boolean getSymmetric() {
        return isSymmetrical;
    }

    public void updateDimensions(int size) {
        width = height = size;
        threadCounts = new double[]{};
        threadCounts = computeThreadSizes(threads);

    }
}
