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
        this.setPreferredSize(new Dimension(500,100));

        this.add(test,"wrap");
        this.add(test1,"wrap");
        this.add(test2,"wrap");

    }

}
