package tartan.TartanUI;

import org.w3c.css.sac.ElementSelector;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Palettes extends JPanel {

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
    int tempY = 0;
    int tempColourIndex = 0;
    boolean customColourChangedStatus = false;

    public Color getFirstColour() {
        if (palettes != null && palettes[0] != null && palettes[0][0] != null)
            return palettes[0][0].getBackground();
        else
            return Color.WHITE;

    }

    public String getFirstColourName() {
        if (palettes != null && palettes[0] != null && palettes[0][0] != null)
            return palettes[0][0].getClientProperty("Name").toString();

        return "Invalid Colour";

    }


    public boolean getCustomColourChangedStatus() {
        return customColourChangedStatus;
    }

    public Palettes(int width, int height, int eachPaletteSize) {
        this.setLayout(new GridLayout(height, width));
        this.setOpaque(true);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.DARK_GRAY);
        palettes = new JButton[width][height];
        this.setBorder(BorderFactory.createRaisedBevelBorder());

        //tempY= 3;
        outerloop:
        for (lastY = 0; lastY < palettes[0].length/*4*/; lastY++) {
            for (lastX = 0; lastX < palettes.length/*10*/; lastX++) {
                if (coloursArrayIndex < coloursArray.size()) {
                    palettes[lastX][lastY] = new JButton("");
                    palettes[lastX][lastY].setBackground(coloursArray.get(coloursArrayIndex).getColour());
                    palettes[lastX][lastY].setPreferredSize(new Dimension(eachPaletteSize, eachPaletteSize));
                    palettes[lastX][lastY].setBorder(BorderFactory.createEmptyBorder());
                    palettes[lastX][lastY].putClientProperty("Name", coloursArray.get(coloursArrayIndex).getName());
                    this.add(palettes[lastX][lastY]);
                    coloursArrayIndex++;
                } else {
                    tempX = lastX;
                    tempY = lastY;
                    break outerloop;
                }
            } //INNER FOR
        } //OUTER FOR

        // ADD THE OTHER CUSTOM COLOURS NOW UNTIL WE REACH THE MAX
        // SAVE THE CUSTOM REFERENCE.

        tempColourIndex = coloursArrayIndex;
        for (lastY = tempY; lastY < palettes[0].length/*4*/; lastY++) {
            for (lastX = tempX; lastX < palettes.length/*10*/; lastX++) {
                palettes[lastX][lastY] = new JButton("X");
                palettes[lastX][lastY].setBackground(Color.WHITE);
                palettes[lastX][lastY].setBackground(null);
                palettes[lastX][lastY].setPreferredSize(new Dimension(eachPaletteSize, eachPaletteSize));
                palettes[lastX][lastY].setBorder(BorderFactory.createEmptyBorder());
                // NO COLOUR NAME AT THIS POINT
                //palettes[lastX][lastY].putClientProperty("Name",coloursArray.get(coloursArrayIndex).getName());
                palettes[lastX][lastY].setEnabled(false);
                this.add(palettes[lastX][lastY]);
                coloursArrayIndex++;
            }
        }
    }

    public void addNewCustomColour(Color colour, String name) {
        if (tempColourIndex < MAX) {
            palettes[tempX][tempY].setEnabled(true);
            palettes[tempX][tempY].setText("");
            palettes[tempX][tempY].setBackground(colour);
            palettes[tempX][tempY].putClientProperty("Name", name);
            tempX++;
            tempColourIndex++;

            //type,id,name,code
            //padded zeros
            String rgb = PaletteColour.getRGBFormat(colour);

            PaletteColour newColour = new PaletteColour("Colour", coloursArray.size() + 1, name,
                    rgb, 1);
            coloursArray.add(newColour);
            customColourChangedStatus = true;
        }


    }

    public void saveCustomColours() {
        //Save the colour

        xsc.updateColoursXML(coloursArray);
        xpc = new XMLParserColours();
        coloursArray = xpc.getColoursArray();


        //get old colours in case its been updated
    }

    void addGridColourListener(ActionListener listenForGridColourButton) {
        for (int x = 0; x < palettes.length; x++) {
            for (int y = 0; y < palettes[x].length; y++) {
                palettes[x][y].addActionListener(listenForGridColourButton);
            }
        }
    }


    public void setColourByName(String colourByName) {

        for (int y = 0; y < palettes[0].length/*4*/; y++) {
            for (int x = 0; x < palettes.length/*10*/; x++) {
                if ((palettes[x][y].getClientProperty("Name") != null)
                        && (palettes[x][y].getClientProperty("Name").toString() == colourByName)) {
                    palettes[x][y].setBorder(BorderFactory.createEtchedBorder(Color.YELLOW, Color.WHITE));
                }
            }


        } // OUTSIDE FOR


    } // setColourByName

    public void resetHighlight(String myName) {
        for (int y = 0; y < palettes[0].length/*4*/; y++) {
            for (int x = 0; x < palettes.length/*10*/; x++) {
                if ((palettes[x][y].getClientProperty("Name") != null)
                        && (palettes[x][y].getClientProperty("Name").toString() == myName)) {
                    palettes[x][y].setBorder(BorderFactory.createEmptyBorder());

                }
            }
        } // OUTSIDE FOR


    }
}
