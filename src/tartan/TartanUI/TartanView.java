package tartan.TartanUI;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionListener;    //for addController()

import com.bric.swing.ColorPicker;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import javax.swing.*;


public class TartanView {

    private JFrame frame;

    ThreadChooser leftColourChooser;
    TartanDisplay tartanDisplay;
    ThreadList threadList;

    private TextField myTextField;
    private Button button;
    // Called from the Model


    // If the btnAddThread is clicked execute a method
    // in the Controller named actionPerformed

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

        //leftColourChooser.updateSinglePaletteColour(null);
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
        initComponents();
    }


    private void initComponents() {
        frame = new JFrame("Tartan Designer");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new MigLayout("", // Layout Constraints
                "[]80[]", // Column constraints
                "[]50[]")); // Row constraints);

        Container mainWindow = frame.getContentPane();
        mainWindow.setBackground(Color.WHITE);

        //CREATE LEFT_CHOOSER
        leftColourChooser = new ThreadChooser();


        tartanDisplay = new TartanDisplay("K4,G4,O4,R50,K50,Y4,B2,M1,P1,M10");

        threadList = new ThreadList();

        //UIManager.put("Label.disabledForeground",Color.blue); // Remove foreground of grey label
        //UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Serif", Font.BOLD, 14));

        frame.add(leftColourChooser, "aligny top");
        frame.add(tartanDisplay, "wrap");
        frame.add(threadList, "span");


        frame.pack();
        frame.setVisible(true);

        //cp.updateUI();

    }


}