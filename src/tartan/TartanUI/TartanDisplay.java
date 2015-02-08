package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import tartan.Tartan;
import tartan.combination.OPERATION;
import tartan.combination.Operate;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Mohim on 01/02/2015.
 */
public class TartanDisplay extends JPanel {

    JSVGCanvas canvas;

    public TartanDisplay(String threadStructure) {

        this.setLayout(new MigLayout());

        this.setOpaque(true);
        //this.setBackground(Color.RED);
        this.setBorder(BorderFactory.createEmptyBorder());
        // Create an instance of a tartan
        String threadT1 = threadStructure;

        Tartan t1 = new Tartan(threadT1, 2, 400, true);

        System.out.println("Starting System");
        System.out.println("Tartan1: " + t1.toString());

        // Create an SVG document.
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

        // Create a converter for this document.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(doc);

        this.paint(svgGenerator, t1);


        // Populate the document root with the generated SVG content.
        Element root = doc.getDocumentElement();
        svgGenerator.getRoot(root);

        // Display the document.
        JSVGCanvas canvas = new JSVGCanvas(); // holds doc with graphics
        canvas.setSVGDocument(doc); // adds doc to canvas
        //MUST MATCH TARTAN DIMENSIONS
        canvas.setPreferredSize(new Dimension(400, 400));
        this.add(canvas);



    }


    public void paint(Graphics2D g2d, Tartan tartan1) {

        int alpha1 = 100;
        int alpha2 = 0;
        // PRINT WARP
        for (int i = 0; i < tartan1.getSettCount() / 2; i += 1) {

            //forwards pattern
            for (int x = 0; x < tartan1.getThreadSizesCount(); x += 1) {
                g2d.setPaint(tartan1.getThreadColour(x));
                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getThreadSizes(x), tartan1.getDimensions()) {
                });
                g2d.translate(tartan1.getThreadSizes(x), 0);
            } //forwards for

            //reverse pattern
            for (int x = tartan1.getThreadSizesCount() - 1; x >= 0; x -= 1) {
                g2d.setPaint(tartan1.getThreadColour(x));
                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getThreadSizes(x), tartan1.getDimensions()) {
                });
                g2d.translate(tartan1.getThreadSizes(x), 0);
            } //reverse for


        } // most outer for

        //System.out.println("transformBefore:" + g2d.getTransform().getTranslateX());
        g2d.translate(-1 * g2d.getTransform().getTranslateX(), 0);
        //System.out.println("transformMOHIM:" + g2d.getTransform().getTranslateX());

        for (int i = 0; i < tartan1.getSettCount() / 2; i += 1) {

            //forwards pattern
            for (int x = 0; x < tartan1.getThreadSizesCount(); x += 1) {
                Color color = new Color(tartan1.getThreadColour(x).getRed(),
                        tartan1.getThreadColour(x).getGreen(),
                        tartan1.getThreadColour(x).getBlue(), alpha1); //Red
                g2d.setPaint(color);

                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getDimensions(), tartan1.getThreadSizes(x)) {
                });
                g2d.translate(0, tartan1.getThreadSizes(x));

            } //forwards for

            //reverse pattern
            for (int x = tartan1.getThreadSizesCount() - 1; x >= 0; x -= 1) {
                Color color = new Color(tartan1.getThreadColour(x).getRed(),
                        tartan1.getThreadColour(x).getGreen(),
                        tartan1.getThreadColour(x).getBlue(), alpha1); //Red
                g2d.setPaint(color);

                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getDimensions(), tartan1.getThreadSizes(x)) {
                });
                g2d.translate(0, tartan1.getThreadSizes(x));
            } //reverse for


        }


    }

}
