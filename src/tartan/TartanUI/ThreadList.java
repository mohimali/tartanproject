package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mohim on 01/02/2015.
 */
public class ThreadList extends JPanel{

    JLabel test = new JLabel("Test");
    JLabel test1 = new JLabel("Test1");
    JLabel test2 = new JLabel("Test2");
    public ThreadList()
    {
        this.setLayout(new MigLayout());
        this.setBackground(Color.DARK_GRAY);
       // this.setPreferredSize(new Dimension(500,100));
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,1)),
                "<html><font color='white'><b>Thread List</b></font></html>"));

        this.add(test,"wrap,span");
        this.add(test1,"wrap,span");
        this.add(test2,"wrap,span");

    }

}
