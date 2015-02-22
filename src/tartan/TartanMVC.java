package tartan;

import tartan.TartanUI.TartanController;
import tartan.TartanUI.TartanModel;
import tartan.TartanUI.TartanView;

import javax.swing.*;

import javax.swing.UIManager.*;

public class TartanMVC {

    public static TartanView theView;
    public static TartanModel theModel;
    public static TartanController theController;


    public static void main(String[] args) {

        //theView.setVisible(true);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        /*
        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                theModel = new TartanModel();
                theView = new TartanView();
                theController = new TartanController(theView, theModel);


            }
        });





    }
}