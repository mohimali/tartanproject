package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import tartan.Tartan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Mohim on 06/03/2015.
 */
public class TartanCombine extends JPanel {

    JLabel lblNew = new JLabel("STUFF");
    JPanel allTartans;
    JButton btnRefreshTartanList;
    int noOfTartansWidth = 4;


    TartanSingle tartanSingle;
    TartanSingle tartanSingle1;
    TartanSingle tartanSingle2;
    TartanSingle tartanSingle3;


    public void init() {
        //height then width
        allTartans = new JPanel(new GridLayout(0, noOfTartansWidth));
        tartanSingle = new TartanSingle();
        tartanSingle1 = new TartanSingle();
        tartanSingle2 = new TartanSingle();
        tartanSingle3= new TartanSingle();
        btnRefreshTartanList = new JButton("Load Tartans");
    }

    public TartanCombine() {
        init();

        //Tartan tartan = new Tartan

        this.setLayout(new MigLayout("insets 0 0 0 0"));
        this.setBackground(Color.red);
        this.add(lblNew, "Wrap,growx,span");
        this.add(btnRefreshTartanList,"Wrap,growx,span");

        Tartan t = new Tartan(100,1);

        t.addThread(Color.blue, 10, "ASDA", "a");
        t.addThread(Color.green, 1, "BASDA","b");
        t.addThread(Color.white, 1, "BASDA","b");
        t.addThread(Color.black, 1, "BASDA","b");
        //tartanSingle.addTartanToDisplay(t);

        this.add(tartanSingle, "");


    }



    public void populateAllTartans(Tartan[] tartansArray) {
        for (int i = 0; i < tartansArray.length; i++) {
            //this.addNewTartan(tartansArray[i]);


        }
    }

    private void addNewTartan(Tartan tartan) {

    }

    public void addRefreshTartansList(ActionListener refreshTartansList) {
        btnRefreshTartanList.addActionListener(refreshTartansList);
    }
}
