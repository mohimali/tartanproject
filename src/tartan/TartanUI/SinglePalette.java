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

    public Color getPaletteColour()
    {
        return paletteColour;
    }

    public SinglePalette(Color requiredColour,int requiredSizeWidth,
                         int requiredSizeHeight)
    {
        paletteColour = new Color(requiredColour.getRGB());
        dimension = new Dimension(requiredSizeWidth, requiredSizeHeight);

        this.setBackground(requiredColour);
        this.setPreferredSize(dimension);

        Border blackline, raisedbevel;

        blackline = BorderFactory.createLineBorder(Color.black);
        raisedbevel = BorderFactory.createRaisedBevelBorder();


        Border compound = BorderFactory.createCompoundBorder(
                raisedbevel, blackline);

        this.setBorder(compound);

    }

    public void updatePaletteColour(Color requiredColour)
    {
        paletteColour = requiredColour;
        this.setBackground(requiredColour);
    }

    public void updatePaletteSize(int requiredSizeWidth,
                                  int requiredSizeHeight)
    {
        dimension.setSize(requiredSizeWidth,requiredSizeHeight);
    }

}
