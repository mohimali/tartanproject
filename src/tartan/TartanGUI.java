package tartan;/*
 * ref: http://xmlgraphics.apache.org/batik/using/svg-generator.html
 * 
 * 
 */


import java.awt.*;

import javax.swing.*;

import org.apache.batik.swing.*;
import org.apache.batik.dom.svg.SVGDOMImplementation;

import org.w3c.dom.*;
import org.w3c.dom.svg.*;


import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import tartan.combination.*;


// http://xmlgraphics.apache.org/batik/using/svg-generator.html

/**
 * @author Mohim Ali
 */
public class TartanGUI {


    public static int tartanDimensions;

    public static String inputColours;

    public static int settCount = 0; // cant be odd


    public static void init() {
        tartanDimensions = 300;
        settCount = 4;
        inputColours = "K4,G4,O4,R50,K50,Y4";
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

    public static void main(String[] args) throws IOException {


        // Initialise variables
        init();

        Operate combiner = new Operate();
//        public static Color K = Color.BLACK;  //BLACK
//        public static Color T = new Color(165, 42, 42);  //BROWN
//        public static Color N = Color.GRAY; // GREY
//
//        public static Color B = Color.BLUE; // BLUE
//        public static Color G = Color.GREEN; // GREEN
//        public static Color O = Color.ORANGE; // ORANGE
//        public static Color P = Color.PINK; // PINK
//        public static Color R = Color.RED; // RED
//        public static Color W = Color.WHITE; // WHITE
//        public static Color Y = Color.YELLOW; // YELLOW
//        public static Color M = Color.MAGENTA; // MAGENTA
//        public static Color C = Color.CYAN; // CYAN


        // Create an instance of a tartan
        int mode = 2; //1 means unary(1 Tartan transform), 2 means binary(2 tartans combined)
        String threadT1 = "K4,G4,O4,R50,K50,Y4";
        String threadT2 = "B1,K1,B1,C20,M20,W1";

        Tartan t1 = new Tartan(threadT1, settCount, tartanDimensions, true);
        Tartan t2 = new Tartan(threadT2, settCount, tartanDimensions, true);

        //Tartan t3 = combiner.performOperation(t1,t2,OPERATION.COMBINE_CONCATENATION);
        //Tartan t3 = combiner.performOperation(t1,t2, OPERATION_BINARY.COMBINE_ODD_EVEN);
        //Tartan t3 = combiner.performOperation(t1,OPERATION.INVERT_COLOUR);
        //Tartan t3 = combiner.performOperation(t1,OPERATION.DARKER_COLOUR);
        //Tartan t3 = combiner.performOperation(t1, OPERATION.BRIGHTER_COLOUR);

        System.out.println("Starting System");
        System.out.println("Tartan1: " + t1.toString());
        System.out.println("Tartan2: " + t2.toString());

        // Create an SVG document.
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);

        // Create a converter for this document.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(doc);
        svgGenerator.setSVGCanvasSize(new Dimension(800, 4000));

        TartanGUI test = new TartanGUI();

        if (mode == 1) //unary 1 tartan transformed
        {
            //test.paint(svgGenerator, t1);
            //test.paint(svgGenerator, t3);
        } else if (mode == 2) // binary  2 tartans combined
        {
            //test.paint(svgGenerator, t1);
            //test.paint(svgGenerator, t2);
            //test.paint(svgGenerator, t3);
        }


        // Populate the document root with the generated SVG content.
        Element root = doc.getDocumentElement();
        svgGenerator.getRoot(root);

        // Display the document.
        JSVGCanvas canvas = new JSVGCanvas(); // holds doc with graphics
        canvas.setSize(500,2000);
        //ScrollPane s;

//        s = new ScrollPane();
//        s.setSize(100,100);
//        //add("Center", s);
//        //c = new myCanvas();
//        //c.setSize(500,300);
//        s.add(canvas);

        JFrame f = new JFrame();  //holds JCanvas

        f.getContentPane().add(canvas);
        canvas.setSVGDocument(doc); // adds doc to canvas
        canvas.setBackground(Color.red);
        //add scroll pane

        f.pack();
        f.setSize(1000,1000);
        f.setVisible(true);


    }
}
