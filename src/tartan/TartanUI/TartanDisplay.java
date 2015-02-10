package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;
import tartan.SVGTartan;
import tartan.Tartan;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Mohim on 01/02/2015.
 */
public class TartanDisplay extends JPanel {

    public TartanDisplay(Tartan tartan) {

        this.setLayout(new MigLayout("insets 0 0 0 0"));

        this.setBackground(Color.darkGray);

        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,1)),
                "<html><font color='white'><b>Tartan</b></font></html>"));

        Dimension tartanDimensions = new Dimension(tartan.getDimensions(),tartan.getDimensions());

        // Create an instance of an SVG styled tartan
        SVGTartan svgTartan = new SVGTartan(tartan);
        SVGDocument doc = svgTartan.getSVGTartan();

        // Display the document.
        JSVGCanvas canvas = new JSVGCanvas(); // holds doc with graphics
        canvas.setSVGDocument(doc); // adds doc to canvas

        //MUST MATCH TARTAN DIMENSIONS
        canvas.setPreferredSize(tartanDimensions);

        canvas.setOpaque(true);
        canvas.setBackground(Color.CYAN);
        this.add(canvas);

    }
}
