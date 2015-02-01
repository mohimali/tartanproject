package tartan.TartanUI;

import tartan.Tartan;
import tartan.TartanThread;

import java.util.ArrayList;

/**
 * Created by Mohim on 31/01/2015.
 */
public class TartanModel {


    private String test = "HELLOWORLD";

    private ArrayList<Tartan> tartanList = new ArrayList<Tartan>();
    private ArrayList<TartanThread> tartanThreadList= new ArrayList<TartanThread>();

    private String originalThreads = "K4,G4,O4,R50,K50,Y4";

    protected String getTest() {
        return test;

    }

    protected String getOriginalThreads() {
        return originalThreads;
    }

    protected void setOriginalThreads(String requiredThreads) {
         originalThreads = requiredThreads;
    }

    protected ArrayList<TartanThread> getTartanThreadList() {
        return tartanThreadList;
    }

    protected void addTartanThread(TartanThread thread) {
        tartanThreadList.add(thread);
    }

    protected void removeTartanThread(int index) {
        tartanThreadList.remove(index);
    }

    protected void swapTartanThread(int firstIndex,int secondIndex) {
        TartanThread left = tartanThreadList.get(firstIndex);
        TartanThread right = tartanThreadList.get(secondIndex);

        //SWAP THE LEFT WITH THE RIGHT AND VICE VERSA
        tartanThreadList.set(firstIndex,right);
        tartanThreadList.set(secondIndex,left);

    }






}