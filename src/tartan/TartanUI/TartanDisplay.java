package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;
import tartan.SVGTartan;
import tartan.Tartan;
import tartan.TartanThread;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mohim on 01/02/2015.
 */
public class TartanDisplay extends JPanel {

    SVGTartan svgTartan;
    SVGDocument doc;
    JSVGCanvas canvas;
    Dimension tartanDimensions;
    public TartanDisplay() {

        this.setLayout(new MigLayout("insets 0 0 0 0"));

        this.setBackground(Color.darkGray);

        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,1)),
                "<html><font color='white'><b>Tartan</b></font></html>"));

        tartanDimensions = new Dimension(400,400);

        // Create an instance of an SVG styled tartan
        //SVGTartan svgTartan = new SVGTartan(tartan);
        svgTartan = new SVGTartan();
        doc = svgTartan.getSVGTartan();

        // Display the document.
        canvas = new JSVGCanvas(); // holds doc with graphics
        canvas.setSVGDocument(doc); // adds doc to canvas

        //MUST MATCH TARTAN DIMENSIONS
        canvas.setPreferredSize(tartanDimensions);

        canvas.setOpaque(true);
        canvas.setBackground(Color.WHITE);
        this.add(canvas);

    }

    public void addTartanToDisplay(Tartan newTartan)
    {
        System.out.println("Adding Thread");

        svgTartan = new SVGTartan(newTartan);
        doc = svgTartan.getSVGTartan();

        // Display the document.
        //canvas = new JSVGCanvas(); // holds doc with graphics
        canvas.setSVGDocument(doc); // adds doc to canvas
        //canvas.repaint();
        //this.repaint();
    }



    public void updateTartan(Tartan newTartan) {
        System.out.println("Updating Tartan Paint");
        addTartanToDisplay(newTartan);


    }
}
