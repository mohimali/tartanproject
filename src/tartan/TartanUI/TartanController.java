package tartan.TartanUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The Controller coordinates interactions
// between the View and Model

public class TartanController {

    private TartanModel theModel;
    private TartanView theView;

    public TartanController(TartanView theView, TartanModel theModel) {

        this.theModel = theModel;
        this.theView = theView;

        // PASS IN THE COLOURS FROM THE MODEL[XML FILE]
        //theView.initComponents();
        //CALL ALL LISTENERS AND ADD THEM FROM CONTROLLER
        this.theView.addThreadListener(new AddThreadListener());
        this.theView.addChooseColourListener(new ChooseColourListener());
        this.theView.leftColourChooser.palettes.addGridColourListener(new GridChooseColourListener());
        this.theView.addCustomColourListener(new AddCustomColourListener());
        this.theView.addSinglePaletteListener(new SinglePaletteListener());
        this.theView.addResetTartanListener(new ResetTartanListener());
    }

    class ResetTartanListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() instanceof JButton) {
                    //PASS DATA FROM THE MODEL IN HERE IF NEEDED
                    theModel.resetTartan();
                    theView.resetTartan();


                }

            } catch (NumberFormatException ex) {
                System.out.println(ex);
            }

        }

    } //ResetTartanListener class


    class SinglePaletteListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() instanceof JButton) {
                    //PASS DATA FROM THE MODEL IN HERE IF NEEDED
                    theView.leftColourChooser.updateSinglePaletteColour(null,null);

                }

            } catch (NumberFormatException ex) {
                System.out.println(ex);
                theView.displayErrorMessage("You Need to Enter 2 Integers");
            }

        }

    } //SinglePaletteListener class

    class AddCustomColourListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() instanceof JButton) {
                    //PASS DATA FROM THE MODEL IN HERE IF NEEDED
                    theView.displayCustomColourPicker();


                }

            } catch (NumberFormatException ex) {
                System.out.println(ex);
                theView.displayErrorMessage("You Need to Enter 2 Integers");
            }

        }

    } //AddCustomColourListener class


    class GridChooseColourListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() instanceof JButton) {

                    JButton currentJB = (JButton) e.getSource();
                    String myName = currentJB.getClientProperty("Name").toString();
                    Color myColour = currentJB.getBackground();

                    theView.leftColourChooser.updateSinglePaletteColour(myColour,myName);

                }

            } catch (NumberFormatException ex) {
                System.out.println(ex);
                theView.displayErrorMessage("You Need to Enter 2 Integers");
            }

        }

    } //AddCustomColourListener class

    class AddThreadListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                String strThreadCount = theView.getThreadCount();
                Color colour = theView.getThreadColour();
                String colourName = theView.getColourName();

                int threadCount = Integer.parseInt(strThreadCount.trim());
                if (threadCount > 99) {
                    theView.displayErrorMessage("You Need a number less then 99 for the thread count.");
                } else {

                    theModel.addTartanThread(colour, threadCount, colourName);
                    theView.updateTartan(theModel.getTartan());
                    theView.addThreadToList(colour,threadCount,colourName);
                }
            } catch (NumberFormatException ex) {
                System.out.println(ex);
                theView.displayErrorMessage("You Need a number less then 99 for the thread count.");
            }
        }
    } // AddThreadListener class

    class ChooseColourListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                theView.leftColourChooser.updateSinglePaletteColour(null,null);
            } catch (NumberFormatException ex) {
                System.out.println(ex);
                theView.displayErrorMessage("You Need to Enter 2 Integers");
            }
        }
    } // ChooseColourListener

} // TartanController class
