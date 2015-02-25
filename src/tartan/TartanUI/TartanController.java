package tartan.TartanUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        this.theView.addActionMenu(new MenuAction("New Tartan", new ImageIcon(this.getClass().getResource("resources/images/new.png"))));
        this.theView.addActionMenu(new MenuAction("Save my Tartan", new ImageIcon(this.getClass().getResource("resources/images/save.png"))));
        this.theView.addActionMenu(new MenuAction("Load existing Tartan", new ImageIcon(this.getClass().getResource("resources/images/load.png"))));
        this.theView.addActionMenu(new MenuAction("Upload tartan to web", new ImageIcon(this.getClass().getResource("resources/images/upload.png"))));
    }



    class MenuAction extends AbstractAction {

        public MenuAction(String text, Icon icon) {
            super(text, icon);
        }

        public void actionPerformed(ActionEvent e) {
            try {

                System.out.println("Command: " + e.getActionCommand());
                String command = e.getActionCommand();

                if (command == "New Tartan") {
                    theModel.resetTartan();
                    theView.resetTartan();
                } else if (command == "Save my Tartan") {
                theView.showSaveFileDialog();
                } else if (command == "Load existing Tartan") {
                    JFileChooser openFile = new JFileChooser();
                    openFile.showOpenDialog(null);
                } else if (command == "Upload tartan to web") {

                }

            } catch (
                    Exception ex
                    )

            {
                ex.printStackTrace();
            }
        }

    } // menuaction

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
                    theView.leftColourChooser.updateSinglePaletteColour(null, null,null);


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
                    String colourShortHand = currentJB.getClientProperty("ShortHand").toString();
                    Color myColour = currentJB.getBackground(); // FULL PALETTE COLOUR REQUIRED


                    theView.leftColourChooser.updateSinglePaletteColour(myColour, myName,colourShortHand);

                    // may need rowIndex.
                    if (myMode == 1) {

                        theView.resetMode(theView.getOldColourToBeChanged());
                        theView.setEnabledAllComponents();
                        theModel.updateColourRow(myRowIndex, myColour, myName,colourShortHand);
                        theView.updateColourRow(myRowIndex, myColour, myName,colourShortHand);

                        theView.updateTartan(theModel.getTartan());
                    }

                    theView.resetMode(theView.getOldColourToBeChanged());
                    theView.resetAllColourPalettes();
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
                String colourShortHand = theView.getColourShortHand();

                int threadCount = Integer.parseInt(strThreadCount.trim());
                if (threadCount > 99) {
                    theView.displayErrorMessage("You Need a number less then 99 for the thread count.");
                } else {

                    theModel.addTartanThread(colour, threadCount, colourName,colourShortHand);
                    theView.updateTartan(theModel.getTartan());
                    theView.addThreadToList(colour, threadCount, colourName,colourShortHand);
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
                theView.leftColourChooser.updateSinglePaletteColour(null, null,null);
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
                String colourShortHand = chosenRow.getMyColourShortHand();

                //////////////////THIS NEEDS TO GET CORRECT COLOUR PALETTE
                String chosenColourName = chosenRow.getName();

                theModel.updateThreadDetails(rowIndex, chosenColour, chosenThreadCount, chosenColourName,colourShortHand);

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
                String myColourShortHand = currentJB.getClientProperty("ShortHand").toString();
                //theView.displayErrorMessage("Trying to updateColour: " + rowIndex + " with name: " + myName);

                // ENSURE COLOUR IS FOUND OR NOT FOUND AND HENCE DEFAULT TO BLACK ANY

                theView.resetAllColourPalettes();

                theView.allowColourPalette(myName, rowIndex, myColour,myColourShortHand);

                theView.updateTartan(theModel.getTartan());

            } catch (NumberFormatException ex) {
                System.out.println(ex);

            }
        }
    } // UpdateColourRowListener
} // TartanController class
