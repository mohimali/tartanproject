package tartan.TartanUI;


import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;    //for addController()
import com.bric.swing.ColorPicker;
import net.miginfocom.swing.MigLayout;
import tartan.Tartan;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.BevelBorder;


public class TartanView {

    private JFrame frame;

    ThreadChooser leftColourChooser;
    TartanDisplay tartanDisplay;
    private JMenuBar menuBar;


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

    void addResetTartanListener(ActionListener listenForResetTartanButton) {
        leftColourChooser.btnResetTartan.addActionListener(listenForResetTartanButton);
    }

    public void addSinglePaletteListener(ActionListener listenForAddCustomColourButton) {
        leftColourChooser.singlePalette.addActionListener(listenForAddCustomColourButton);
    }

    public void addUpdateThreadListener(ActionListener listenForUpdateThreadButton,int index) {
        leftColourChooser.threadListRows.addThreadChangedListener(index, listenForUpdateThreadButton);
    }

    public void addDeleteThreadListener(ActionListener listenForDeleteThreadListener, int index) {
        leftColourChooser.threadListRows.addDeleteThreadListener(index,listenForDeleteThreadListener);
    }



    public void displayCustomColourPicker() {
        Color newColor = ColorPicker.showDialog(frame, leftColourChooser.singlePalette.getPaletteColour());
        String name;
        if (newColor != null) {
            name = JOptionPane.showInputDialog("Enter your new Custom colour e.g BlackMist");
            if ((name != null) && (name.equals(""))) {
                leftColourChooser.updateCustomPaletteColour(newColor, name);
                JOptionPane.showMessageDialog(null,
                        "Colour " + name + " has been added",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "You have not chosen the name for your colour. Colour has not been added.",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


    // Open a popup that contains the error message passed

    void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage);
    }


    public TartanView() {
        try {
            initComponents();

        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());


        }
    }

    public String getThreadCount()
    {
        return leftColourChooser.getThreadCount();
    }

    public Color getThreadColour()
    {
        return leftColourChooser.getThreadColour();
    }

    public void initComponents() throws Exception {
        frame = new JFrame("Tartan Designer");
        frame.setSize(1000, 600);

        // "src/tartan/TartanUI/images/a.jpg"
        try {
            File f1 = new File("Test.java");
            String path = f1.getCanonicalPath();
            //System.out.println(path);
            //frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/tartan/TartanUI/images/2.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }



        Container mainWindow = frame.getContentPane();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainWindow.setLayout(new MigLayout("", // Layout Constraints
                "[]80[]", // Column constraints
                "[]10[]")); // Row constraints);

        mainWindow.setBackground(Color.GRAY);

        //CREATE LEFT_CHOOSER
        leftColourChooser = new ThreadChooser();
        tartanDisplay = new TartanDisplay();

        mainWindow.add(leftColourChooser, "aligny top");
        mainWindow.add(tartanDisplay, "wrap, aligny top,growy");

        setUpMenuBars();
        frame.setJMenuBar(menuBar);


        // ADD WINDOW CLOSING LISTENERS
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (leftColourChooser.palettes.getCustomColourChangedStatus()) {
                    saveCustomColours();
                }
                frame.dispose();
            }
        });


        frame.pack();
        frame.setVisible(true);
    } // initComponents

    class MenuAction extends AbstractAction {

        public MenuAction(String text, Icon icon) {
            super(text, icon);
        }

        public void actionPerformed(ActionEvent e) {
            try {
               System.out.print("clicked");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void setUpMenuBars() {
        menuBar = new JMenuBar();
        JMenu formatMenu = new JMenu("File");
        formatMenu.setMnemonic('J');
        //System.out.println("samsung: "  + this.getClass().getResource("resources/images/1.gif"));
        MenuAction newTartanAction = new MenuAction("New Tartan", new ImageIcon(this.getClass().getResource("resources/images/new.png")));
        MenuAction saveTartanAction = new MenuAction("Save my Tartan", new ImageIcon(this.getClass().getResource("resources/images/save.png")));
        MenuAction loadTartanAction = new MenuAction("Load existing Tartan",new ImageIcon(this.getClass().getResource("resources/images/load.png")));
        MenuAction uploadTartanAction = new MenuAction("Upload tartan to web", new ImageIcon(this.getClass().getResource("resources/images/upload.png")));


        JMenuItem item;
        item = formatMenu.add(newTartanAction);
        item = formatMenu.add(saveTartanAction); // CHANGE LATER
        item = formatMenu.add(loadTartanAction); // CHANGE LATER
        item = formatMenu.add(uploadTartanAction); // CHANGE LATER

        menuBar.add(formatMenu);
        menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));

    }

    private void saveCustomColours() {
        int dlgButton = JOptionPane.YES_NO_OPTION;
        int dlgResult = JOptionPane.showConfirmDialog(null,
                "Do you wish to save the custom colours you created?",
                "Message", dlgButton);
        if (dlgResult == 0) {
            System.out.println("Yes option");
            leftColourChooser.saveCustomColours();
            //UPDATE THE CUSTOM COLOURS
        } else {
            System.out.println("No Option");

        }
    }


    public String getColourName() {
        return leftColourChooser.getColourName();
    }

    public void updateTartan(Tartan newTartan) {
        tartanDisplay.updateTartan(newTartan);
    }

    public void addThreadToList(Color colour, int threadCount, String colourName) {
        leftColourChooser.addThreadToList(colour,threadCount,colourName);
    }

    public void resetTartan()
    {
        leftColourChooser.resetTartan();
        tartanDisplay.resetTartan();

    }

    public ThreadListRow getChosenRow(int rowIndex) {

        return leftColourChooser.getChosenRow(rowIndex);
    }


public void addUpdateColourRowListener(ActionListener l,int index)
{
    leftColourChooser.addUpdateColourRowListener(index,l);
}

    public void removeThreadRow(int rowIndex) {
        leftColourChooser.removeThreadRow(rowIndex);
    }

    public void allowColourPalette(String myName,int rowIndex,Color myColour) {
        leftColourChooser.allowColourPalette(myName,rowIndex,myColour);
        leftColourChooser.updateComponentsStatus(false);
    }

    public int getCurrentMode() {
        return leftColourChooser.getCurrentMode();
    }

    public int getCurrentRowIndex() {
        return leftColourChooser.getCurrentRowIndex();
    }

    public void resetMode(String myName) {
        leftColourChooser.resetMode(myName);
    }

    public void setEnabledAllComponents() {
        leftColourChooser.updateComponentsStatus(true);
    }

    public void updateColourRow(int myRowIndex, Color myColour,String myName) {
        leftColourChooser.updateColourRow(myRowIndex,myColour,myName);
    }

    public void resetAllColourPalettes() {
        leftColourChooser.resetAllColourPalettes();
    }

    public String getOldColourToBeChanged() {
        return  leftColourChooser.getOldColourToBeChanged();
    }
} // TartanView