package tartan.TartanUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.*;

/**
 * Created by Mohim on 05/02/2015.
 */
public class SinglePalette extends JButton {

    Color paletteColour;
    Dimension dimension;
    String colourName;
    String colourShortHand;
    public Color getPaletteColour()
    {
        return paletteColour;
    }

    public SinglePalette(Color requiredColour,int requiredSizeWidth,
                         int requiredSizeHeight,String requiredColourName)
    {
        paletteColour = new Color(requiredColour.getRGB());
        dimension = new Dimension(requiredSizeWidth, requiredSizeHeight);
        this.colourName = requiredColourName;
        this.setBackground(requiredColour);
        this.setPreferredSize(dimension);
        this.setBorder(BorderFactory.createRaisedBevelBorder());

    }

    public String getColourShortHand()
    {
        return colourShortHand;
    }

    public void setColourShortHand(String colourShortHand){
        this.colourShortHand = colourShortHand;
    }

    public String getColourName()
    {
        return colourName;

    }


    public void updatePaletteColour(Color requiredColour,String requiredColourName,String requiredShortHand)
    {
        paletteColour = requiredColour;
        this.setBackground(requiredColour);
        colourName = requiredColourName;
        colourShortHand = requiredShortHand;
    }

    public void updatePaletteSize(int requiredSizeWidth,
                                  int requiredSizeHeight)
    {
        dimension.setSize(requiredSizeWidth,requiredSizeHeight);
    }

}
