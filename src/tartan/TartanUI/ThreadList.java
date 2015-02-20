package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Mohim on 01/02/2015.
 */
public class ThreadList extends JPanel{

    ArrayList<ThreadListRow> threadListRow;
    JScrollPane js;
    JPanel jp;
    int lastIndex = 1;
    public void init()
    {
        this.removeAll();
        threadListRow = new ArrayList<ThreadListRow>();
        lastIndex = 0;
    }

    public void addThreadChangedListener(int index, ActionListener l)
    {
        System.out.println("threadIndex: " + index);
        threadListRow.get(index).addUpdateThreadListener(index, l);
    }

    public void addDeleteThreadListener(int index, ActionListener listenForDeleteThreadListener) {
        threadListRow.get(index).addDeleteThreadListener(index,listenForDeleteThreadListener);
    }

    public ThreadList()
    {

        this.setLayout(new MigLayout());
        init();
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
        // ADD THE NEW ROW
        this.add(newThreadListRow, "wrap,width 300");
    }
    public void resetRows()
    {
        init();
        this.repaint();
    }

    public void updateThreadList()
    {
        System.out.println("REMOVING ALL");

        this.removeAll();
        validate();
        repaint();

        lastIndex = 0;

        if (threadListRow.size() > 0) {
            for (int i = 0; i < threadListRow.size(); i++) {
                ThreadListRow currentRow = threadListRow.get(i);
                currentRow.setRowIndex(i);
                threadListRow.set(i, currentRow);

                this.addThreadRow(threadListRow.get(i));
                System.out.println("indexreq: " + i + " other: " + threadListRow.get(i).getRowIndex());
                lastIndex++;
                validate();
                repaint();

            }
        }






    } // UpdateThreadList

    public Color getRowThreadColour(int rowIndex)
    {
        return threadListRow.get(rowIndex).getThreadColour();
    }

    public ThreadListRow getChosenRow(int rowIndex) {
        return threadListRow.get(rowIndex);
    }


    public void removeThreadRow(int rowIndex) {
        threadListRow.remove(rowIndex);
        updateThreadList();


    }

    public void addUpdateColourRowListener(int index,ActionListener l) {
        //threadListRow.get(index);
    }
}
