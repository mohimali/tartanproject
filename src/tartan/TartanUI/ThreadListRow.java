package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Mohim on 16/02/2015.
 */
public class ThreadListRow extends JPanel {

    private JLabel lblColourName,lblRowIndex,lblColourShortHand;
    private JButton btnColourBox,
                    btnCross,
                    btnUpdate;

    private int index = 0; // To display numbered rows.
    private JSpinner sprThreadCount;

    String myColourName;

    String myColourShortHand = "";

    public String getMyColourShortHand()
    {
        return myColourShortHand;
    }

    public void setColourShortHand(String colourShortHand)
    {
        this.myColourShortHand = colourShortHand;
    }

    public void addUpdateThreadListener(int index, ActionListener l)
    {
        btnUpdate.addActionListener(l);
        btnUpdate.putClientProperty("RowIndex",index);
    }

    public int getUpdateRowIndex()
    {
        return Integer.parseInt(btnUpdate.getClientProperty("RowIndex").toString());

    }

    public void addDeleteThreadListener(int rowIndex, ActionListener l)
    {
        btnCross.addActionListener(l);
        btnCross.putClientProperty("RowIndex",index);
    }

    public void addUpdateColourListener(int index, ActionListener l) {
        btnColourBox.addActionListener(l);
        btnColourBox.putClientProperty("RowIndex",index);
        btnColourBox.putClientProperty("Name",myColourName);
        btnColourBox.putClientProperty("ShortHand",myColourShortHand);
    } // addUpdateColourListener

    public int getThreadCount()
    {
        return Integer.parseInt(sprThreadCount.getValue().toString());
    }

    public void updateColour(Color myColour,String myName,String colourShortHand)
    {
        //myRowIndex, myColour, myName, colourShortHand
        myColourName = myName;
        btnColourBox.putClientProperty("Name",myName);
        btnColourBox.putClientProperty("ShortHand",colourShortHand);
        this.myColourShortHand = colourShortHand;
        btnColourBox.setBackground(myColour);
        lblColourName.setText("<html><font color='white'><b>" + myName  + ": "  + "</b></font></html>");
        btnColourBox.putClientProperty("ShortHand",colourShortHand);
        lblColourShortHand.setText("<html><font color='white'><b>" + colourShortHand  + ": "  + "</b></font></html>");
    }

    private void init(int requiredIndex,Color colour,int threadCount, String colourName,String colourShortHand)
    {

        lblRowIndex = new JLabel("<html><font color='white'><b>" + requiredIndex  + ": "  + "</b></font></html>");
        lblColourName = new JLabel("<html><font color='white'><b>" + colourName  + ": "  + "</b></font></html>");
        lblColourShortHand = new JLabel("<html><font color='white'><b>" + colourShortHand  + ": "  + "</b></font></html>");
        myColourName = colourName;
        btnColourBox = new JButton();

        int current =threadCount;
        int min = 1;
        int max = 99;
        int step = 1;
        index= requiredIndex;
        SpinnerNumberModel jsrThreadCountModel = new SpinnerNumberModel(current, min, max, step);
        sprThreadCount = new JSpinner(jsrThreadCountModel);


        btnCross = new JButton();
        btnUpdate = new JButton("Update");


        btnColourBox.setBackground(colour);
        btnColourBox.setPreferredSize(new Dimension(25,25));
        btnCross.setIcon(new ImageIcon(this.getClass().getResource("resources/images/cross.png")));
        btnColourBox.putClientProperty("ShortHand",colourShortHand);
    }


    public ThreadListRow(int requiredIndex,Color colour,int threadCount, String colourName,String myColourShortHand)
    {
        this.myColourShortHand = myColourShortHand;
        init(requiredIndex, colour,threadCount,colourName,myColourShortHand);

        this.setLayout(new MigLayout());
        this.setBackground(Color.DARK_GRAY);
        this.add(lblRowIndex,"width 50");
        this.add(lblColourName,"width 100");
        this.add(btnColourBox,"width 100"); //MAKE THE COLOUR DROP DOWN
        this.add(lblColourShortHand,"width 50");
        this.add(sprThreadCount,"width 100");
        this.add(btnCross,"width 100");
        this.add(btnUpdate,"width 100");




    }

    public Color getThreadColour() {
        return btnColourBox.getBackground();
    }

    public void setRowIndex(int requiredRowIndex) {
        index = requiredRowIndex;
        lblRowIndex.setText("<html><font color='white'><b>" + index  + ": "  + "</b></font></html>");
        btnCross.putClientProperty("RowIndex",index);
        btnUpdate.putClientProperty("RowIndex",index);
        btnColourBox.putClientProperty("RowIndex",index);
    }

    public int getRowIndex() {
        return index;
    }


    public void disableComponents(boolean enabledStatus) {

        btnColourBox.setEnabled(enabledStatus);
        btnCross.setEnabled(enabledStatus);
        btnUpdate.setEnabled(enabledStatus);
        sprThreadCount.setEnabled(enabledStatus);
    }
}
