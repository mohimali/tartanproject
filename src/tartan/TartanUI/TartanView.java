package tartan.TartanUI;

// This is the View
// Its only job is to display what the user sees
// It performs no calculations, but instead passes
// information entered by the user to whomever needs
// it.

import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();
import java.awt.event.ActionListener;	//for addController()

import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.UIManager.LookAndFeelInfo;

public class TartanView{

    private JFrame frame;

    ThreadChooser leftColourChooser;
    TartanDisplay tartanDisplay;
    ThreadList threadList;

    private TextField myTextField;
    private Button button;
    // Called from the Model


    // If the btnAddThread is clicked execute a method
    // in the Controller named actionPerformed

    void addThreadListener(ActionListener listenForAddThreadButton){

        leftColourChooser.btnAddThread.addActionListener(listenForAddThreadButton);

    }

    void setAddThreadStatus(String test)
    {
        leftColourChooser.lblBlue.setText(test);
    }

    // Open a popup that contains the error message passed

    void displayErrorMessage(String errorMessage){

        JOptionPane.showMessageDialog(frame, errorMessage);

    }


    public TartanView() {
        initComponents();
    }


    private void initComponents() {
        frame = new JFrame("Tartan Designer");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridBagLayout());
        Container mainWindow = frame.getContentPane();
        mainWindow.setBackground(Color.CYAN);

        //CREATE LEFT_CHOOSER
        leftColourChooser = new ThreadChooser();
        leftColourChooser.setBackground(Color.ORANGE);

        tartanDisplay = new TartanDisplay();


        threadList = new ThreadList();

        //UIManager.put("Label.disabledForeground",Color.blue); // Remove foreground of grey label
        //UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Serif", Font.BOLD, 14));


        addItem(frame,leftColourChooser, 0, 0, 1, 1, GridBagConstraints.NORTHWEST);
        addItem(frame,tartanDisplay, 1, 0, 1, 1, GridBagConstraints.NORTH);
        addItem(frame,threadList, 0, 1, 1, 1, GridBagConstraints.NORTHWEST);



        frame.setVisible(true);


    }

    private void addItem(JFrame p, JComponent component, int x, int y, int width, int height, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = 100.0;
        gbc.weighty = 100.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        gbc.fill = GridBagConstraints.NONE;
        p.add(component, gbc);
    }
/*
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    // If Nimbus unavailable set gui to other feel
                }
                new TartanView();
            }
        });
    }
*/

}