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

        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                theView = new TartanView();

                theModel = new TartanModel();

                theController = new TartanController(theView,theModel);
            }
        });


        /*
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
                TartanModel theModel = new TartanModel();

                TartanController theController = new TartanController(theView,theModel);
            }
        });
*/





    }
}