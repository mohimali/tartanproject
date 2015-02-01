package tartan.TartanUI;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

/**
 * Created by Mohim on 01/02/2015.
 */
public class ThreadChooser extends JPanel {

    GridBagConstraints gbcColourChooser = new GridBagConstraints();
    JLabel  lblRed,lblGreen,lblBlue,lblSize;
    JSlider slrRed,slrGreen,slrBlue,slrSize;
    JButton btnAddThread;


    public ThreadChooser()
    {
        //CREATE LEFT_CHOOSER
        this.setBackground(Color.ORANGE);
        this.setLayout(new GridBagLayout());

        lblRed = new JLabel("<html> <font color='red'>Red</font></html>");
        lblGreen = new JLabel("<html> <font color='green'>Green</font></html>");
        lblBlue = new JLabel("<html> <font color='blue'>Blue</font></html>");
        lblSize = new JLabel("Size");
        btnAddThread = new JButton("Add Thread");

        UIManager.put("Label.disabledForeground",Color.blue); // Remove foreground of grey label
        //UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Serif", Font.BOLD, 14));

        gbcColourChooser.insets = new Insets(5,5,5,5);//ADD PADDING, TOP,LEFT,BOTTOM,RIGHT
        gbcColourChooser.gridx = 0;
        gbcColourChooser.gridy = 0;
        this.add(lblRed,gbcColourChooser);


        gbcColourChooser.gridx = 0;
        gbcColourChooser.gridy = 1;
        this.add(lblGreen,gbcColourChooser);
        gbcColourChooser.gridx = 0;
        gbcColourChooser.gridy = 2;
        this.add(lblBlue,gbcColourChooser);

        gbcColourChooser.gridx = 0;
        gbcColourChooser.gridy = 3;
        this.add(lblSize,gbcColourChooser);

        //ADD ADD THREAD BUTTON
        gbcColourChooser.gridx = 1;
        gbcColourChooser.gridy = 4;
        this.add(btnAddThread,gbcColourChooser);

        //SLIDER ADDING
        slrRed = new JSlider(JSlider.HORIZONTAL,
                0,255, 0);
        slrGreen = new JSlider(JSlider.HORIZONTAL,
                0,255, 0);
        slrBlue = new JSlider(JSlider.HORIZONTAL,
                0,255, 0);

        slrSize = new JSlider(JSlider.HORIZONTAL,
                0,255, 0);

        //Create the label table
        Hashtable hshColorLabels = new Hashtable();
        hshColorLabels.put( new Integer( 0 ), new JLabel("0") );
        hshColorLabels.put( new Integer(255 ), new JLabel("255") );

        //Turn on labels at major tick marks.
        slrRed.setMajorTickSpacing(51);
        slrRed.setPaintTicks(true);
        slrRed.setPaintLabels(true);

        slrGreen.setMajorTickSpacing(51);
        slrGreen.setPaintTicks(true);
        slrGreen.setPaintLabels(true);

        slrBlue.setMajorTickSpacing(51);
        slrBlue.setPaintTicks(true);
        slrBlue.setPaintLabels(true);

        slrSize.setMajorTickSpacing(51);
        slrSize.setPaintTicks(true);
        slrSize.setPaintLabels(true);

        slrRed.setLabelTable( hshColorLabels );
        slrGreen.setLabelTable( hshColorLabels );
        slrBlue.setLabelTable( hshColorLabels );
        slrSize.setLabelTable( hshColorLabels );

        gbcColourChooser.gridx = 1;
        gbcColourChooser.gridy = 0;
        this.add(slrRed,gbcColourChooser);

        gbcColourChooser.gridx = 1;
        gbcColourChooser.gridy = 1;
        this.add(slrGreen,gbcColourChooser);

        gbcColourChooser.gridx = 1;
        gbcColourChooser.gridy = 2;
        this.add(slrBlue,gbcColourChooser);

        gbcColourChooser.gridx = 1;
        gbcColourChooser.gridy = 3;
        this.add(slrSize,gbcColourChooser);



    }
}
