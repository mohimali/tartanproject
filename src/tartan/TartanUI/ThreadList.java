package tartan.TartanUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mohim on 01/02/2015.
 */
public class ThreadList extends JPanel{

    JLabel test = new JLabel("Test");
    public ThreadList()
    {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.yellow);
        this.setSize(500,500);
        addItem(new JLabel("Name:"), 0, 0, 1, 1, GridBagConstraints.WEST);
        addItem(new JLabel("Name1:"), 0, 1, 1, 1, GridBagConstraints.WEST);
        addItem(new JLabel("Name2:"), 0, 2, 1, 1, GridBagConstraints.WEST);

    }

    private void addItem(JComponent component, int x, int y, int width, int height, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = 100.0;
        gbc.weighty = 100.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        gbc.fill = GridBagConstraints.NONE;
        this.add(component, gbc);
    }

}
