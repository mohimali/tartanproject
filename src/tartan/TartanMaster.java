/*
 * ref: http://xmlgraphics.apache.org/batik/using/svg-generator.html
 * 
 * 
 */
package tartan;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

import static java.awt.Color.*;

// http://xmlgraphics.apache.org/batik/using/svg-generator.html

/**
 * @author Mohim Ali
 */
public class TartanMaster {


    public static double tartanDimensions;

    public static double origThreadSizes[];// remove end number later just


    //Upgrade to RGB later
    public static Color colours[];

    public static int settCount = 0; // cant be odd
    public static int warp = 0;
    public static int weft = 0;

    // int requiredThreadCount, int requiredThreadSizes[],
    // Color requiredColours[], int requiredSettCount

    public static void init() {
        tartanDimensions = 800;

        origThreadSizes = new double[]{10, 50, 50, 10};  // remove end number later just



        //Upgrade to RGB later
        colours = new Color[]{black, red,
                black, yellow}; // May be static so cant be changed?

        settCount = 4;
        warp = 0;
        weft = 0;
    }


    public void paint(Graphics2D g2d, Tartan tartan1) {


        //g2d.translate(threadSettWidth[x], 0);
        for (int i = 0; i < tartan1.getSettCount() / 2; i += 1) {

            //forwards pattern
            for (int x = 0; x < tartan1.getThreadSizesCount(); x += 1) {
                g2d.setPaint(colours[x]);
                // System.out.println("mainTSize: " + tartan1.getThreadSizes(x) + "mainTDim: " + tartan1.getDimensions());
                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getThreadSizes(x), tartan1.getDimensions()) {
                });
                g2d.translate(tartan1.getThreadSizes(x), 0);
                //System.out.println("fowards loop");
                //System.out.println(threadSettWidth[x]);
                //System.out.println(colors[x]);
            } //forwards for

            //reverse pattern
            for (int x = tartan1.getThreadSizesCount() - 1; x >= 0; x -= 1) {
                g2d.setPaint(colours[x]);
                // System.out.println("mainTSize: " + tartan1.getThreadSizes(x) + "mainTDim: " + tartan1.getDimensions());
                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getThreadSizes(x), tartan1.getDimensions()) {
                });
                g2d.translate(tartan1.getThreadSizes(x), 0);
                //System.out.println("fowards loop");
                //System.out.println(threadSettWidth[x]);
                //System.out.println(colors[x]);
            } //reverse for


        } // most outer for
    }

    public static void main(String[] args) throws IOException {

        init();

        // Create an instance of a tartan
        Tartan tartan = new Tartan(origThreadSizes, colours,
                settCount, tartanDimensions);

        // Get a DOMImplementation.
        DOMImplementation domImpl =
                GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // Ask the test to render into the SVG Graphics2D implementation.
        TartanMaster test = new TartanMaster();
        test.paint(svgGenerator, tartan);

        // Finally, stream out SVG to the standard output using
        // UTF-8 encoding.
        boolean useCSS = true; // we want to use CSS style attributes
        Writer out = new OutputStreamWriter(System.out, "UTF-8");
        svgGenerator.stream(out, useCSS);

    }
}
