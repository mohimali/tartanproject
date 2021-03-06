package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Mohim on 01/02/2015.
 */
public class ThreadChooser extends JPanel {

    private static int PALETTE_MODE = 0;
    private static int ROW_COLOUR_MODE = 1;
    private int currentMode = 0;
    private int rowToBeChanged = 0;
    JButton btnAddThread,
            btnChooseColour,
            btnCustomColourChooser,
            btnResetTartan,btnUpdateSettCount;

    JLabel lblThreadCount,lblUpdateSettCount;
    JSpinner sprThreadCount,sprSettCount;

    ThreadList threadListRows;
    int noOfPalettesX = 7;
    int noOfPalettesY = 4;
    int eachPaletteSize = 25;
    int modifier = 0;
    Palettes palettes;
    SinglePalette singlePalette;

    JScrollPane jScrollPane;
    boolean singlePaletteStatus;
    private String oldColourNameSelected="";

    public void init() {
        palettes = new Palettes(noOfPalettesX, noOfPalettesY, eachPaletteSize);
        singlePalette = new SinglePalette(palettes.getFirstColour(), (int) palettes.getPreferredSize().getWidth() + modifier,
                (int) palettes.getPreferredSize().getHeight() + modifier,palettes.getFirstColourName(),palettes.getFirstColourShortHand());
        singlePaletteStatus = true;
        threadListRows = new ThreadList();

        jScrollPane = new JScrollPane(threadListRows);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(7);

        //this.add(jScrollPane, BorderLayout.CENTER);
        int current =1;
        int min = 1;
        int max = 99;
        int step = 1;
        SpinnerNumberModel jsrThreadCountModel = new SpinnerNumberModel(current, min, max, step);
        sprThreadCount = new JSpinner(jsrThreadCountModel);

        current = 2;

        SpinnerNumberModel jsrSettCountModel = new SpinnerNumberModel(current, min, max, step);

        sprSettCount = new JSpinner(jsrSettCountModel);
        btnResetTartan = new JButton("Reset Tartan");

        btnUpdateSettCount = new JButton("Update \n Sett Count");
        lblUpdateSettCount = new JLabel("<html><font color='white'><b>Sett Count</b></font></html>");
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

    public void addThreadToList(Color colour,int threadCount, String colourName, String colourShortHand)
    {
        //ID ,ColourName, ThreadCount, Cross, Update
        threadListRows.addThreadToList(colour, threadCount, colourName, colourShortHand);
    }
    public ThreadChooser() {



        //CREATE LEFT_CHOOSER
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new MigLayout("", // Layout Constraints
                "", // Column constraints
                "[]10[]10[]30[]10[]")); // Row constraints);

        init();
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
        this.add(btnAddThread, "align left,Wrap");
        this.add(lblUpdateSettCount, "align left");
        this.add(sprSettCount, "align left");
        this.add(btnUpdateSettCount, "align left, Wrap");
        //this.add(threadListRows,"span,align left"+width+height2);
        this.add(jScrollPane,"span,align left"+width+height2);

        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 1)),
                "<html><font color='white'><b>Choose a colour and ThreadCount</b></font></html>"));
        palettes.setVisible(false);
    }


    void updateSinglePaletteColour(Color newColour,String newColourName,String shortHand) {

        singlePaletteStatus = !singlePaletteStatus;
        if (singlePaletteStatus) {
            singlePalette.setVisible(true);
            palettes.setVisible(false);

            if (newColour != null && newColour != null)
                singlePalette.updatePaletteColour(newColour,newColourName,shortHand);
        } else {
            singlePalette.setVisible(false);
            palettes.setVisible(true);
        }
    }

    public void updateCustomPaletteColour(Color newColour, String name,String shortHand) {
        if (!singlePaletteStatus) {
            singlePaletteStatus = true;
            singlePalette.setVisible(true);
            palettes.setVisible(false);
        }
        if (newColour != null) {
            singlePalette.updatePaletteColour(newColour,name,shortHand);
            palettes.addNewCustomColour(newColour, name,shortHand);
        }
    } // updateCustomPaletteColour

    public String getColourName() {
        return singlePalette.getColourName();
    } // getColourName


    public void resetTartan() {

        threadListRows.resetRows();
    }

    public Color getRowThreadColour(int rowIndex) {
        return threadListRows.getRowThreadColour(rowIndex);
    }

    public ThreadListRow getChosenRow(int rowIndex) {
        return threadListRows.getChosenRow(rowIndex);
    }



    public void removeThreadRow(int rowIndex) {
        threadListRows.removeThreadRow(rowIndex);
    }

    public void addUpdateColourRowListener(int index,ActionListener l) {
        threadListRows.addUpdateColourRowListener(index,l);
    }

    public void allowColourPalette(String myName,int rowIndex,Color myColour,String colourShortHand) {
        singlePalette.setVisible(false);
        palettes.setVisible(true);
        currentMode = ROW_COLOUR_MODE;
        oldColourNameSelected = myName;
        palettes.setColourByName(myName);
        rowToBeChanged = rowIndex;
        updateColourRow(rowIndex, myColour, myName,colourShortHand);
        singlePaletteStatus = false;
    }

    public int getCurrentMode()
    {
        return currentMode;
    }

    public String getOldColourToBeChanged(){
        return oldColourNameSelected;
    }

    public void updateComponentsStatus(boolean status) {
        btnAddThread.setEnabled(status);
        btnChooseColour.setEnabled(status);
        btnCustomColourChooser.setEnabled(status);
        btnResetTartan.setEnabled(status);
        threadListRows.setEnabledStatus(status);
    }

    public int getCurrentRowIndex() {
        return rowToBeChanged;
    }

    public void resetMode(String myName) {
        currentMode = PALETTE_MODE;
        palettes.resetHighlight(myName);
    }

    public void updateColourRow(int myRowIndex, Color myColour,String myName,String colourShortHand) {
        //myRowIndex, myColour, myName, colourShortHand
        threadListRows.updateColourRow(myRowIndex,myColour,myName,colourShortHand);

    }

    public void resetAllColourPalettes() {
        palettes.resetAllHighlight();
    }

    public String getColourShortHand() {
        return singlePalette.getColourShortHand();
    }

    public void addSettCountUpdateListener(ActionListener settCountUpdateListener) {
        btnUpdateSettCount.addActionListener(settCountUpdateListener);
    }

    public int getSettCount() {
        return Integer.parseInt(sprSettCount.getValue().toString());
    }

    public ArrayList<PaletteColour> getAllColourPalettes() {
        return palettes.getColoursArray();
    }

    public void updateSettCount(int mySettCount) {
        sprSettCount.setValue(mySettCount);
    }
}
