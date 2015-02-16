package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Mohim on 16/02/2015.
 */
public class ThreadListRow extends JPanel {



    private JLabel lblColourName;
    private JButton btnColourBox,
                    btnCross,
                    btnUpdate;

    private int index = 0; // To display numbered rows.
    private JSpinner sprThreadCount;

    private void init(int requiredIndex,Color colour,int threadCount, String colourName)
    {

        lblColourName = new JLabel("<html><font color='white'><b>" + requiredIndex  + ": "
                                   + colourName + "</b></font></html>");
        btnColourBox = new JButton();

        int current =threadCount;
        int min = 1;
        int max = 99;
        int step = 1;
        SpinnerNumberModel jsrThreadCountModel = new SpinnerNumberModel(current, min, max, step);
        sprThreadCount = new JSpinner(jsrThreadCountModel);


        btnCross = new JButton();
        btnUpdate = new JButton("Update");


        btnColourBox.setBackground(colour);
        btnColourBox.setPreferredSize(new Dimension(25,25));
        btnCross.setIcon(new ImageIcon("src/tartan/TartanUI/images/cross.png"));

    }


    public ThreadListRow(int requiredIndex,Color colour,int threadCount, String colourName)
    {
        init(requiredIndex, colour,threadCount,colourName);

        this.setLayout(new MigLayout());
        this.setBackground(Color.DARK_GRAY);
        this.add(lblColourName,"width 200");
        this.add(btnColourBox,"width 100"); //MAKE THE COLOUR DROP DOWN
        this.add(sprThreadCount,"width 100");
        this.add(btnCross,"width 100");
        this.add(btnUpdate,"width 100");
    }




}
