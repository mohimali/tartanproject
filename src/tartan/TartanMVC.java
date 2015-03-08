package tartan;

import tartan.TartanUI.TartanController;
import tartan.TartanUI.TartanModel;
import tartan.TartanUI.TartanView;

import javax.swing.*;

import javax.swing.UIManager.*;
import javax.swing.border.Border;
import java.awt.*;

public class TartanMVC {

    public static TartanView theView;
    public static TartanModel theModel;
    public static TartanController theController;


    public static void main(String[] args) {
        /*
        UIManager.put("ToolTip[Enabled].backgroundPainter", Color.darkGray); //#fff7c8

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(new ToolTipLookAndFeel());
                    //UIManager.put("ToolTip.background", Color.red); //#fff7c8
                    break;
                }
            }
        } catch (Exception e) {
        } */

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