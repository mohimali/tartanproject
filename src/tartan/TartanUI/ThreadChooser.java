package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mohim on 01/02/2015.
 */
public class ThreadChooser extends JPanel {

    JButton btnAddThread,
            btnChooseColour,
            btnCustomColourChooser;

    JLabel lblThreadCount;
    JTextField txfThreadCount;
    int noOfPalettesX = 10;
    int noOfPalettesY = 4;
    int eachPaletteSize = 25;
    int modifier = 0;
    Palettes palettes;
    SinglePalette singlePalette;

    boolean singlePaletteStatus;

    public void init() {
        palettes = new Palettes(noOfPalettesX, noOfPalettesY, eachPaletteSize);
        singlePalette = new SinglePalette(palettes.getFirstColour(), (int) palettes.getPreferredSize().getWidth() + modifier,
                (int) palettes.getPreferredSize().getHeight() + modifier,palettes.getFirstColourName());
        singlePaletteStatus = true;


    }

    protected String getThreadCount()
    {
        return txfThreadCount.getText();
    }

    protected Color getThreadColour()
    {
        return this.singlePalette.getPaletteColour();
    }

    public void saveCustomColours() {
        palettes.saveCustomColours();
    }

    public ThreadChooser() {

        init();
        //CREATE LEFT_CHOOSER
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new MigLayout());

        btnChooseColour = new JButton("Choose Colour");
        btnAddThread = new JButton("Add Thread");
        btnCustomColourChooser = new JButton("<html><font color='green'>Custom Colour</font></html>");
        lblThreadCount = new JLabel("<html><font color='white'><b>Thread Count<b/></font></html>");
        txfThreadCount = new JTextField("1");
        this.setPreferredSize(new Dimension(300, 420));
        UIManager.put("Label.disabledForeground", Color.blue); // Remove foreground of grey label
        this.add(btnChooseColour, "Wrap,hidemode 3");
        this.add(singlePalette, "Wrap, hidemode 3, span 3");
        this.add(palettes, "Wrap,hidemode 3, span 3");
        this.add(btnCustomColourChooser, "Wrap,hidemode 3");
        this.add(lblThreadCount, "align right");
        this.add(txfThreadCount, "Wrap,width 100");
        this.add(btnAddThread, "span 3, align center");

        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 1)),
                "<html><font color='white'><b>Choose a colour and ThreadCount</b></font></html>"));
        palettes.setVisible(false);
    }


    void updateSinglePaletteColour(Color newColour,String newColourName) {

        singlePaletteStatus = !singlePaletteStatus;
        if (singlePaletteStatus) {
            singlePalette.setVisible(true);
            palettes.setVisible(false);

            if (newColour != null && newColour != null)
                singlePalette.updatePaletteColour(newColour,newColourName);
        } else {
            singlePalette.setVisible(false);
            palettes.setVisible(true);
        }
    }

    public void updateCustomPaletteColour(Color newColour, String name) {


        if (!singlePaletteStatus) {
            singlePaletteStatus = true;
            singlePalette.setVisible(true);
            palettes.setVisible(false);


        }
        if (newColour != null) {
            singlePalette.updatePaletteColour(newColour,name);
            palettes.addNewCustomColour(newColour, name);
        }

    }

    public String getColourName() {
        return singlePalette.getColourName();
    }


}
