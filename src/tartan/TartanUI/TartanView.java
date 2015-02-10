package tartan.TartanUI;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;    //for addController()

import com.bric.swing.ColorPicker;
import net.miginfocom.swing.MigLayout;
import tartan.Tartan;

import java.awt.*;
import javax.swing.*;


public class TartanView {

    private JFrame frame;

    ThreadChooser leftColourChooser;
    TartanDisplay tartanDisplay;
    ThreadList threadList;

    // If the btnAddThread is then go the the controller
    // and do the actionPerformed method.
    void addThreadListener(ActionListener listenForAddThreadButton) {
        leftColourChooser.btnAddThread.addActionListener(listenForAddThreadButton);
    }

    void addChooseColourListener(ActionListener listenForChooseColourButton) {
        leftColourChooser.btnChooseColour.addActionListener(listenForChooseColourButton);
    }

    void addCustomColourListener(ActionListener listenForAddCustomColourButton) {
        leftColourChooser.btnCustomColourChooser.addActionListener(listenForAddCustomColourButton);
    }

    public void addSinglePaletteListener(ActionListener listenForAddCustomColourButton) {
        leftColourChooser.singlePalette.addActionListener(listenForAddCustomColourButton);
    }


    void setAddThreadStatus(String test) {
        JOptionPane.showMessageDialog(null, "ss", "InfoBox: " + "Test", JOptionPane.INFORMATION_MESSAGE);
    }


    public void displayCustomColourPicker() {
        Color newColor = ColorPicker.showDialog(frame, leftColourChooser.singlePalette.getPaletteColour());
        leftColourChooser.updateCustomPaletteColour(newColor);
    }


    // Open a popup that contains the error message passed

    void displayErrorMessage(String errorMessage) {

        JOptionPane.showMessageDialog(frame, errorMessage);

    }


    public TartanView() {

    }


    public void initComponents(Tartan tartan) {
        frame = new JFrame("Tartan Designer");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new MigLayout("", // Layout Constraints
                "[]80[]", // Column constraints
                "[]10[]")); // Row constraints);

        Container mainWindow = frame.getContentPane();
        mainWindow.setBackground(Color.GRAY);

        //CREATE LEFT_CHOOSER
        leftColourChooser = new ThreadChooser();
        tartanDisplay = new TartanDisplay(tartan);
        threadList = new ThreadList();
        //leftColourChooser.setBorder(BorderFactory.createTitledBorder("<html><font color='white'>Choose a colour</font></html>"));
        //tartanDisplay.setBorder(BorderFactory.createTitledBorder("Tartan"));
        //threadList.setBorder(BorderFactory.createTitledBorder("<html><font color='white'>Thread list</font></html>"));

        //UIManager.put("Label.disabledForeground",Color.blue); // Remove foreground of grey label
        //UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Serif", Font.BOLD, 14));

        frame.add(leftColourChooser, "aligny top");
        frame.add(tartanDisplay, "wrap, aligny top,growy");
        frame.add(threadList, "span,growx");


        frame.pack();
        frame.setVisible(true);

        //cp.updateUI();

    }


}