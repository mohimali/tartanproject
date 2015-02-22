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
                    theView.leftColourChooser.updateSinglePaletteColour(null, null);


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

                    // 0 Means colour palette mode, 1 means row colour updating mode
                    int myMode = theView.getCurrentMode();
                    int myRowIndex = theView.getCurrentRowIndex();

                    JButton currentJB = (JButton) e.getSource();
                    String myName = currentJB.getClientProperty("Name").toString();
                    Color myColour = currentJB.getBackground();


                    theView.leftColourChooser.updateSinglePaletteColour(myColour, myName);

                    // may need rowIndex.
                    if (myMode == 1)
                    {
                        theView.resetMode(theView.getColourName());
                        theView.setEnabledAllComponents();
                        theView.updateColourRow(myRowIndex,myColour,myName);

                        //update model
                        theModel.updateColourRow(myRowIndex,myColour,myName);
                    }




                    // UPDATE THE MODEL AS WELL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
                    //////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
                    theView.addThreadToList(colour, threadCount, colourName);
                    int lastIndex = theModel.getTartan().getThreadSizesCount() - 1;
                    theView.addUpdateThreadListener(new UpdateThreadListener(), lastIndex);
                    theView.addDeleteThreadListener(new DeleteThreadListener(), lastIndex);
                    theView.addUpdateColourRowListener(new UpdateColourRowListener(), lastIndex);
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
                theView.leftColourChooser.updateSinglePaletteColour(null, null);
            } catch (NumberFormatException ex) {
                System.out.println(ex);
                theView.displayErrorMessage("You Need to Enter 2 Integers");
            }
        }
    } // ChooseColourListener

    class UpdateThreadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                JButton currentJB = (JButton) e.getSource();
                int rowIndex = Integer.parseInt(currentJB.getClientProperty("RowIndex").toString());
                ThreadListRow chosenRow = theView.getChosenRow(rowIndex);
                Color chosenColour = chosenRow.getThreadColour();
                int chosenThreadCount = chosenRow.getThreadCount();

                //////////////////THIS NEEDS TO GET CORRECT COLOUR PALETTE
                String chosenColourName = chosenRow.getName();

                theModel.updateThreadDetails(rowIndex, chosenColour, chosenThreadCount, chosenColourName);

                theView.updateTartan(theModel.getTartan());

                //theView.displayErrorMessage("updateThreadListener");
            } catch (NumberFormatException ex) {
                System.out.println(ex);

            }
        }
    } // UpdateThreadListener

    class DeleteThreadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                JButton currentJB = (JButton) e.getSource();
                int rowIndex = Integer.parseInt(currentJB.getClientProperty("RowIndex").toString());
                theView.displayErrorMessage("Trying to remove: " + rowIndex + " from a modelThreadSizeOf: " + theModel.getTartan().getThreadList().size());

                theView.removeThreadRow(rowIndex);
                theModel.removeThreadRow(rowIndex);

                theView.updateTartan(theModel.getTartan());

            } catch (NumberFormatException ex) {
                System.out.println(ex);

            }
        }
    } // DeleteThreadListener

    class UpdateColourRowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                JButton currentJB = (JButton) e.getSource();
                int rowIndex = Integer.parseInt(currentJB.getClientProperty("RowIndex").toString());
                Color myColour = currentJB.getBackground();
                String myName = currentJB.getClientProperty("Name").toString();
                theView.displayErrorMessage("Trying to updateColour: " + rowIndex + " with name: " + myName);

                // ENSURE COLOUR IS FOUND OR NOT FOUND AND HENCE DEFAULT TO BLACK ANY
                theView.allowColourPalette(myName,rowIndex);

                theView.updateTartan(theModel.getTartan());

            } catch (NumberFormatException ex) {
                System.out.println(ex);

            }
        }
    } // UpdateColourRowListener
} // TartanController class
