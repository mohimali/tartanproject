package tartan.TartanUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
        this.setForeground(Color.WHITE);
        this.setBackground(Color.DARK_GRAY);
        palettes = new JButton[width][height];

        this.setBorder(BorderFactory.createRaisedBevelBorder());

        //EXTRACT THE XML INFO FROM palette.xml and use the colours stored their
        XMLParserColours xpc = new XMLParserColours();
        ArrayList<PaletteColour> coloursArray = xpc.getColoursArray();
        int coloursArrayIndex = 0;

        for (int x = 0; x < palettes.length; x++) {
            for (int y = 0; y < palettes[x].length; y++) {
                palettes[x][y] = new JButton("");
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, g, b);
                //SLATE GREY WITH A CAUSES PROBLEMS
                if (coloursArrayIndex < coloursArray.size())
                {
                    palettes[x][y].setBackground(coloursArray.get(coloursArrayIndex).getRGB());
                    //palettes[x][y].setForeground(Color.BLACK);
                }
                else
                {
                    palettes[x][y].setBackground(Color.WHITE);
                }
                coloursArrayIndex++;
                palettes[x][y].setPreferredSize(new Dimension(eachPaletteSize, eachPaletteSize));
                //palettes[x][y].setOpaque(true);
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
