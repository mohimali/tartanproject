package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mohim on 01/02/2015.
 */
public class ThreadList extends JPanel{

    ArrayList<ThreadListRow> threadListRow;
    int lastIndex = 1;
    public void init()
    {
        threadListRow = new ArrayList<ThreadListRow>();
        lastIndex = 1;
    }
    public ThreadList()
    {
        init();
        this.setLayout(new MigLayout());
        this.setBackground(Color.DARK_GRAY);
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,1)),
                "<html><font color='white'><b>Thread List</b></font></html>"));


    } // ThreadList constructor

    public void addThreadToList(Color colour,int threadCount, String colourName)
    {
        ThreadListRow currentRow = new ThreadListRow(lastIndex,colour,threadCount,colourName);
        threadListRow.add(currentRow);
        addThreadRow(currentRow);
        lastIndex++;

    } // addThreadToList

    private void addThreadRow(ThreadListRow newThreadListRow)
    {
        this.add(newThreadListRow, "wrap,width 300");
    }
    public void resetRows()
    {
        init();
    }

    public void updateThreadList()
    {

    } // UpdateThreadList

}
