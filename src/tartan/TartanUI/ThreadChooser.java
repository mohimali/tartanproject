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
            btnCustomColourChooser,
            btnResetTartan;

    JLabel lblThreadCount;
    JSpinner sprThreadCount;

    ThreadList threadListRows;
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
        threadListRows = new ThreadList();
        int current =1;
        int min = 1;
        int max = 99;
        int step = 1;
        SpinnerNumberModel jsrThreadCountModel = new SpinnerNumberModel(current, min, max, step);
        sprThreadCount = new JSpinner(jsrThreadCountModel);

        btnResetTartan = new JButton("Reset Tartan");
    }

    protected String getThreadCount()
    {
        return sprThreadCount.getValue().toString();
    }

    protected Color getThreadColour()
    {
        return this.singlePalette.getPaletteColour();
    }

    public void saveCustomColours() {
        palettes.saveCustomColours();
    }

    public void addThreadToList(Color colour,int threadCount, String colourName)
    {
        //ID ,ColourName, ThreadCount, Cross, Update
        threadListRows.addThreadToList(colour, threadCount, colourName);
    }
    public ThreadChooser() {

        init();

        //CREATE LEFT_CHOOSER
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new MigLayout("", // Layout Constraints
                "", // Column constraints
                "[]10[]10[]30[]10[]")); // Row constraints);

        btnChooseColour = new JButton("Choose Colour");
        btnAddThread = new JButton("Add Thread");
        btnCustomColourChooser = new JButton("<html><font color='green'>Custom Colour</font></html>");
        lblThreadCount = new JLabel("<html><font color='white'><b>Thread Count<b/></font></html>");
        this.setPreferredSize(new Dimension(300, 420));
        UIManager.put("Label.disabledForeground", Color.blue); // Remove foreground of grey label

        String width = ",width 500:500:500";
        String height = ",height 125:125:125";
        String height2 = ",height 200:200:200";
        this.add(singlePalette, "Wrap, hidemode 3, span 6,align center" + width + height);
        this.add(palettes, "Wrap,hidemode 3, span 6,align center" + width + height);
        this.add(btnChooseColour, "align left, hidemode 3,span 1");
        this.add(btnCustomColourChooser, "align left,hidemode 3, span 2");
        this.add(btnResetTartan, "Wrap,align left,Wrap,hidemode 3, span 2");
        this.add(lblThreadCount, "align left");
        this.add(sprThreadCount, "align left");
        this.add(btnAddThread, "align left,wrap");
        this.add(threadListRows,"span,align left"+width+height2);

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
    } // updateCustomPaletteColour

    public String getColourName() {
        return singlePalette.getColourName();
    } // getColourName


    public void resetTartan() {

        threadListRows.resetRows();
    }
}
