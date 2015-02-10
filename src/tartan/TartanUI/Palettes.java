package tartan.TartanUI;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Palettes extends JPanel{

    JButton[][] palettes;

    //EXTRACT THE XML INFO FROM palette.xml and use the colours stored their
    XMLParserColours xpc = new XMLParserColours();
    ArrayList<PaletteColour> coloursArray = xpc.getColoursArray();
    XMLSaveColours xsc = new XMLSaveColours();
    int MAX = 40;
    int coloursArrayIndex = 0;
    int lastX = 0;
    int lastY = 0;
    int tempX = 0;
    int tempY = 3;
    public Palettes(int width, int height,int eachPaletteSize) {
        this.setLayout(new GridLayout(height, width));
        this.setOpaque(true);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.DARK_GRAY);
        palettes = new JButton[width][height];

        this.setBorder(BorderFactory.createRaisedBevelBorder());
        int stopCounting = 0;
        tempX = 0;
        //tempY= 3;
        for (lastX = 0; lastX < palettes.length; lastX++) {
            for (lastY = 0; lastY < palettes[lastX].length; lastY++) {


                if (coloursArrayIndex < coloursArray.size())
                {
                    palettes[lastX][lastY] = new JButton("");
                    palettes[lastX][lastY].setBackground(coloursArray.get(coloursArrayIndex).getColour());
                    coloursArrayIndex++;
                    palettes[lastX][lastY].setPreferredSize(new Dimension(eachPaletteSize, eachPaletteSize));
                    palettes[lastX][lastY].setBorder(BorderFactory.createEmptyBorder());
                    this.add(palettes[lastX][lastY]);

                }
                else if (coloursArrayIndex < MAX || stopCounting ==1 )
                {
                    stopCounting = 1;

                    palettes[lastX][lastY] = new JButton("");
                    //palettes[lastX][lastY].setBackground(coloursArray.get(coloursArrayIndex).getColour());
                    //coloursArrayIndex++;
                    palettes[lastX][lastY].setPreferredSize(new Dimension(eachPaletteSize, eachPaletteSize));
                    palettes[lastX][lastY].setBorder(BorderFactory.createEmptyBorder());
                    palettes[lastX][lastY].setText("X");
                    palettes[lastX][lastY].setEnabled(false);
                    this.add(palettes[lastX][lastY]);
                }
                else
                {
                    //DONT ADD ANY MORE COLOURS
                }


            } //INNER FOR

            
        }

    }


    public void addNewCustomColour(Color colour, String name)
    {
        //Save the colour
        xsc.addColour(colour,name);
        xpc = new XMLParserColours();
        coloursArray = xpc.getColoursArray();

        palettes[tempX][tempY].setEnabled(true);
        this.updateUI();
        palettes[tempX][tempY].setBackground(colour);
        coloursArrayIndex++;
        palettes[tempX][tempY].setText("");
        this.updateUI();

        tempX++;

        //get old colours in case its been updated

    }

    void addGridColourListener(ActionListener listenForGridColourButton) {


        for (int x = 0; x < palettes.length; x++) {
            for (int y = 0; y < palettes[x].length; y++) {
                palettes[x][y].addActionListener(listenForGridColourButton);
            }
        }
    }



}
