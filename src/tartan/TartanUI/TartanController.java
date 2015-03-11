package tartan.TartanUI;

import tartan.Tartan;
import tartan.TartanThread;
import tartan.ThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.lang.String;
// The Controller coordinates interactions
// between the View and Model

public class TartanController {

    private TartanModel theModel;
    private TartanView theView;
    private JFileChooser openFile = new JFileChooser();
    private JFileChooser saveFile = new JFileChooser();
    private XMLParserTartan xpTartan = new XMLParserTartan();
    private XMLSaveTartan xsTartan = new XMLSaveTartan();

    String currentFileDirectory = "";
    ThreadFactory threadFactory = new ThreadFactory();

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
        this.theView.addRefreshTartansList(new RefreshTartansList());
        this.theView.addSettCountUpdateListener(new SettCountUpdateListener());
        this.theView.addConfigUpdateListener(new ConfigUpdateListener());
        this.theView.addCombineTartansListener(new CombineTartansListener());
        this.theView.addActionMenu(new MenuAction("New Tartan", new ImageIcon(this.getClass().getResource("resources/images/new.png"))));
        this.theView.addActionMenu(new MenuAction("Save my Tartan", new ImageIcon(this.getClass().getResource("resources/images/save.png"))));
        this.theView.addActionMenu(new MenuAction("Load existing Tartan", new ImageIcon(this.getClass().getResource("resources/images/load.png"))));
        this.theView.addActionMenu(new MenuAction("Upload tartan to web", new ImageIcon(this.getClass().getResource("resources/images/upload.png"))));

        loadAllTartans();


    }

    //Initialise all the information taken from a folder or online and placed into the model.
    public void loadAllTartans() {

        ArrayList<String> allFileNames = new ArrayList<String>();
        ArrayList<Tartan> allTartans = new ArrayList<Tartan>(); // to be recorded in model


        try {
            File targetFolder = new File("./allTartans");
            File[] allFiles = targetFolder.listFiles();

            // GET FILE NAMES FROM FOLDER "./allTartans"
            for (int i = 0; i < allFiles.length; i++) {
                if (allFiles[i].isFile()) {
                    //System.out.println("Tartan file " + i + " named " + allFiles[i].getName());
                    allFileNames.add("./allTartans/" + allFiles[i].getName());
                }
            } // for getting a list of file names


            for (int i = 0; i < allFileNames.size(); i++) {
                xpTartan.parseXMLFile(allFileNames.get(i));

                System.out.println(xpTartan.getErrorMessages());
                boolean errorsDetected = xpTartan.getErrorsDetected();

                if (!errorsDetected) {

                    System.out.println("no errors");
                    ArrayList<TartanThread> currentThreadList = xpTartan.getThreadList();
                    int currentSettCount = xpTartan.getSettCount();
                    Tartan currentTartan = new Tartan(currentThreadList, currentSettCount, 100, true);
                    allTartans.add(currentTartan);

                } else {
                    System.out.println("Tartan not recognised with name " + allFileNames.get(i));
                }

            } // for to parse tartan sett xml files

            // UPDATE THE MODEL WITH ALL THE NEW TARTANS WE FOUND
            theModel.populateTartansList(allTartans);

            //UPDATE THE VIEW WITH ALL THE TARTANS WE FOUND IN THE SECOND TAB
            theView.populateTartansList(theModel.getTartansList());


        } catch (NullPointerException e) {
            System.out.println(e);
            System.out.println("Couldnt find a folder allTartans where you tartans should be located");

        } catch (Exception e) {
            System.out.println(e);

        }


    } // loadAllTartans

    class RefreshTartansList implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() instanceof JButton) {

                    loadAllTartans();
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

    } //RefreshTartansList class

    class CombineTartansListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() instanceof JButton) {
                    theView.displayErrorMessage("stuppppppppp.");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

    } //RefreshTartansList class

    class ConfigUpdateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() instanceof JButton) {
                    //PASS DATA FROM THE MODEL IN HERE IF NEEDED

                    String settConfig = theView.getSettConfig();
                    ArrayList<PaletteColour> myColours = theView.getAllColourPalettes();


                    threadFactory.buildThreadX(settConfig, myColours);

                    if (threadFactory.getDetectedErrors()) {
                        //System.out.print(threadFactory.getAccumulatedErrors());
                        theView.displayErrorMessage("The Sett " + settConfig + " is not recognised. \n" +
                                threadFactory.getAccumulatedErrors());
                    } else {
                        theModel.replaceThreadList(threadFactory.getThreadList(),
                                theModel.getSettCount(),
                                theModel.getTartan().getDimensions(),
                                theModel.getTartan().getSymmetric());

                        theView.resetTartan();
                        populateViewsThreadList(theModel.getTartanThreadList());
                        theView.updateTartan(theModel.getTartan());
                        theView.populateTartansList(theModel.getTartansList());
                    }


                }

            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

    } //ConfigUpdateListener class


    class MenuAction extends AbstractAction {

        public MenuAction(String text, Icon icon) {
            super(text, icon);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                String command = e.getActionCommand();
                if (command == "New Tartan") {
                    theModel.resetTartan();
                    theView.resetTartan();
                    theView.populateTartansList(theModel.getTartansList());
                } // New Tartan
                else if (command == "Save my Tartan") {
                    saveFile.setDialogTitle("Save your tartan");
                    if (!currentFileDirectory.isEmpty()) ;
                    {
                        File filex = new File(currentFileDirectory);
                        saveFile.setCurrentDirectory(filex);
                    }

                    int option = saveFile.showSaveDialog(null);


                    if (option == JFileChooser.APPROVE_OPTION) {
                        File fileToSave;


                        if (!(saveFile.getSelectedFile().toString().endsWith(".xml"))) {
                            fileToSave = new File(saveFile.getSelectedFile().toString() + ".xml");
                        } else {
                            fileToSave = new File(saveFile.getSelectedFile().toString());
                        }

                        System.out.println("fileToSave: " + fileToSave.getAbsoluteFile());
                        currentFileDirectory = fileToSave.getAbsolutePath();
                        xsTartan.updateTartanXML(theModel.getTartanThreadList(), theModel.getSettCount(), fileToSave.getAbsolutePath());

                    }


                } //SAVE MY TARTAN
                else if (command == "Load existing Tartan") {


                    if (!currentFileDirectory.isEmpty()) ;
                    {
                        File filex = new File(currentFileDirectory);
                        openFile.setCurrentDirectory(filex);
                    }

                    openFile.showOpenDialog(null);


                    System.out.println("getting file: " + openFile.getSelectedFile().getAbsolutePath());
                    xpTartan.parseXMLFile(openFile.getSelectedFile().getAbsolutePath());


                    System.out.println(xpTartan.getErrorMessages());
                    boolean errorsDetected = xpTartan.getErrorsDetected();

                    if (!errorsDetected) {
                        theModel.resetTartan();
                        theView.resetTartan();

                        ArrayList<TartanThread> myNewThreads = xpTartan.getThreadList();

                        int mySettCount = xpTartan.getSettCount();

                        // UPDATE THE MODEL
                        theModel.replaceThreadList(myNewThreads, mySettCount, 400, true);
                        theModel.updateSettCount(mySettCount);
                        // UPDATE THE VIEW
                        theView.updateSettCount(mySettCount);
                        theView.updateTartan(theModel.getTartan());
                        theView.populateTartansList(theModel.getTartansList());

                        populateViewsThreadList(myNewThreads);
                        currentFileDirectory = openFile.getSelectedFile().getAbsolutePath();
                    } else {
                        // DISPLAY ERROR MESSAGE Sett isnt valid
                        theView.displayErrorMessage("Not a valid tartan Sett");
                    }


                } //Load existing Tartan
                else if (command == "Upload tartan to web") {

                } // Upload tartan to web

            } catch (
                    Exception ex
                    )

            {
                ex.printStackTrace();
            }
        }

    } // menuaction

    private void populateViewsThreadList(ArrayList<TartanThread> myNewThreads) {
        for (int i = 0; i < myNewThreads.size(); i++) {
            TartanThread currentThread = myNewThreads.get(i);

            Color colour = currentThread.getColour();
            int threadCount = currentThread.getThreadCount();
            String colourName = currentThread.getColourName();
            String colourShortHand = currentThread.getColourShortHand();

            theView.addThreadToList(colour, threadCount, colourName, colourShortHand);
            theView.addUpdateThreadListener(new UpdateThreadListener(), i);
            theView.addDeleteThreadListener(new DeleteThreadListener(), i);
            theView.addUpdateColourRowListener(new UpdateColourRowListener(), i);


        }
    }

    class ResetTartanListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() instanceof JButton) {
                    //PASS DATA FROM THE MODEL IN HERE IF NEEDED
                    theModel.resetTartan();
                    theView.resetTartan();
                    theView.populateTartansList(theModel.getTartansList());

                }

            } catch (NumberFormatException ex) {
                System.out.println(ex);
            }

        }

    } //ResetTartanListener class

    class SettCountUpdateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() instanceof JButton) {
                    int mySettCount = theView.getSettCount();
                    theModel.updateSettCount(mySettCount);
                    //theView.updateSettCount(mySettCount);
                    theView.updateTartan(theModel.getTartan());
                    theView.populateTartansList(theModel.getTartansList());
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    } //SettCountUpdateListener class


    class SinglePaletteListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() instanceof JButton) {
                    //PASS DATA FROM THE MODEL IN HERE IF NEEDED
                    theView.leftColourChooser.updateSinglePaletteColour(null, null, null);


                }

            } catch (Exception ex) {
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

            } catch (Exception ex) {
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

                    theView.leftColourChooser.updateSinglePaletteColour(myColour, myName, colourShortHand);

                    // may need rowIndex.
                    if (myMode == 1) {

                        theView.resetMode(theView.getOldColourToBeChanged());
                        theView.setEnabledAllComponents();
                        theModel.updateColourRow(myRowIndex, myColour, myName, colourShortHand);
                        theView.updateColourRow(myRowIndex, myColour, myName, colourShortHand);
                        System.out.println("myRowIndex: " + myRowIndex + " myColour: " +
                                myColour + " myName: " + myName + " colourShortHand: " + colourShortHand);
                        theView.updateTartan(theModel.getTartan());
                        theView.populateTartansList(theModel.getTartansList());
                    }
                    theView.resetMode(theView.getOldColourToBeChanged());
                    theView.resetAllColourPalettes();
                }
            } catch (Exception ex) {
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


                    if (theModel.getTartan().getThreadList().size() == 0) {
                        theModel.updateSettCount(theView.getSettCount());
                    }
                    theModel.addTartanThread(colour, threadCount, colourName, colourShortHand);
                    theView.updateTartan(theModel.getTartan());
                    theView.populateTartansList(theModel.getTartansList());
                    theView.addThreadToList(colour, threadCount, colourName, colourShortHand);
                    int lastIndex = theModel.getTartan().getThreadSizesCount() - 1;
                    theView.addUpdateThreadListener(new UpdateThreadListener(), lastIndex);
                    theView.addDeleteThreadListener(new DeleteThreadListener(), lastIndex);
                    theView.addUpdateColourRowListener(new UpdateColourRowListener(), lastIndex);
                }
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage("You Need a number less then 99 for the thread count.");
            }
        }
    } // AddThreadListener class

    class ChooseColourListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                theView.leftColourChooser.updateSinglePaletteColour(null, null, null);
            } catch (Exception ex) {
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
                String chosenColourName = chosenRow.getColourName();
                //System.out.println("stupid name  : " + chosenColourName);
                //System.out.println("stupid short had : " + colourShortHand);
                //////////////////THIS NEEDS TO GET CORRECT COLOUR PALETTE


                theModel.updateThreadDetails(rowIndex, chosenColour, chosenThreadCount, chosenColourName, colourShortHand);

                theView.updateTartan(theModel.getTartan());
                theView.populateTartansList(theModel.getTartansList());
                //theView.displayErrorMessage("updateThreadListener");
            } catch (Exception ex) {
                System.out.println(ex);

            }
        }
    } // UpdateThreadListener

    class DeleteThreadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                JButton currentJB = (JButton) e.getSource();
                int rowIndex = Integer.parseInt(currentJB.getClientProperty("RowIndex").toString());
                //theView.displayErrorMessage("Trying to remove: " + rowIndex + " from a modelThreadSizeOf: " + theModel.getTartan().getThreadList().size());

                theView.removeThreadRow(rowIndex);
                theModel.removeThreadRow(rowIndex);

                theView.updateTartan(theModel.getTartan());
                theView.populateTartansList(theModel.getTartansList());
            } catch (Exception ex) {
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

                // ENSURE COLOUR IS FOUND OR NOT FOUND AND HENCE DEFAULT TO BLACK ANY
                theView.resetAllColourPalettes();
                theView.allowColourPalette(myName, rowIndex, myColour, myColourShortHand);
                theView.updateTartan(theModel.getTartan());
                theView.populateTartansList(theModel.getTartansList());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    } // UpdateColourRowListener
} // TartanController class
