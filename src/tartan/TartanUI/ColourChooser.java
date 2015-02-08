package tartan.TartanUI;

import com.bric.swing.ColorPicker;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 * Created by Mohim on 07/02/2015.
 */
public class ColourChooser extends JColorChooser {


    public ColourChooser()
    {
        super();

    }


    public static AbstractColorChooserPanel findPanel(JColorChooser chooser, String name) {
        AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
        for (int i = 0; i < panels.length; i++) {
            String clsName = panels[i].getClass().getName();
            if (clsName.equals(name)) {
                return panels[i];
            }
        }
        return null;
    }
}
