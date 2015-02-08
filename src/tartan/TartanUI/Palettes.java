package tartan.TartanUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Mohim on 07/02/2015.
 */
public class Palettes extends JPanel{


    JButton[][] palettes;

    public Palettes(int width, int height,int eachPaletteSize) {
        Random rand = new Random();
        this.setLayout(new GridLayout(height, width));
        this.setOpaque(true);
        this.setForeground(Color.white);
        palettes = new JButton[width][height];
        Border blackline,  raisedbevel;

        blackline = BorderFactory.createLineBorder(Color.black);
        raisedbevel = BorderFactory.createRaisedBevelBorder();


        Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, blackline);

        this.setBorder(compound);

        for (int x = 0; x < palettes.length; x++) {
            for (int y = 0; y < palettes[x].length; y++) {
                palettes[x][y] = new JButton("");
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, g, b);
                palettes[x][y].setBackground(randomColor);

                palettes[x][y].setPreferredSize(new Dimension(eachPaletteSize, eachPaletteSize));
                palettes[x][y].setOpaque(true);
                palettes[x][y].setBorder(BorderFactory.createEmptyBorder());
                this.add(palettes[x][y]);

            }
        }
    }



    void addGridColourListener(ActionListener listenForGridColourButton) {


        for (int x = 0; x < palettes.length; x++) {
            for (int y = 0; y < palettes[x].length; y++) {
                palettes[x][y].addActionListener(listenForGridColourButton);
            }
        }
    }



}
