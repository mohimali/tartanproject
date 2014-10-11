/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tartan;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.*;
import java.awt.Color;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.IOException;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

//http://xmlgraphics.apache.org/batik/using/svg-generator.html
/**
 *
 * @author mohim
 */
///             ////////////////
//GIT HUBBBB
///             //////////
public class TartanMaster {

    public static int MAX_TARTAN_SIZE = 10;

    // Black, red, black,yellow
    public static Color colors[]; // 
    public static int threadSettLength[]; // How long each set is
    public static int threadSettWidth[]; // 
    public static int settLength; //How long a sett lasts until next repeat
    public static int maxSetts; // 

    public static void init() {
        settLength = 3; // 4 elements or rects inside
        maxSetts = 2;

        //threadSettLength = new int[] {20,20,20,20,20,20,20,20,20,20};
        threadSettLength = new int[]{20};

        threadSettWidth = new int[]{1, 5, 5, 1};

        //Upgrade to RGB later
        colors = new Color[]{Color.black, Color.red, Color.black, Color.yellow};
    }

    public void paint(Graphics2D g2d) {
        
        int orien = 0 //start with vertical, 1=vertical , 1 = horizontal
        int threadCount = 0;
        int pivot = 0;
        int weave = 0;
        int warp = 0; // Vetical stripes
        int weft = 0; // Horizontal Stripes 
        int sett = 0;  // Each sett for the pattern
        System.out.println("xscale:" + g2d.getTransform().getTranslateX());
        for (int j = 0; j <= maxSetts; j += 1) {

            //g2d.translate(threadSettWidth[x], 0);
            for (int i = 0; i <= maxSetts; i += 1) {

                //forwards pattern
                for (int x = 0; x <= settLength; x += 1) {
                    g2d.setPaint(colors[x]);
                    g2d.fill(new Rectangle(threadSettWidth[x], threadSettLength[0]));
                    g2d.translate(threadSettWidth[x], 0);
                  //System.out.println("fowards loop");
                    //System.out.println(threadSettWidth[x]);
                    //System.out.println(colors[x]);
                }
                System.out.println("----------------------------");

                //Reverse Patterns
                for (int x = settLength - 1; x > 0; x -= 1) {
                    g2d.setPaint(colors[x]);
                    g2d.fill(new Rectangle(threadSettWidth[x], threadSettLength[0]));
                    g2d.translate(threadSettWidth[x], 0);
                  //System.out.println("reverse loop");
                    //System.out.println(threadSettWidth[x]);
                    //System.out.println(colors[x]);
                }
            } // end first row
            g2d.translate(-1 * g2d.getTransform().getTranslateX(), threadSettLength[0]);
            System.out.println("master:" + g2d.getTransform().getTranslateX());
          // --------------//
            //g2d.translate(threadSettWidth[x], 0);

            
            
        } //vertical drop swtch height and width

        /*
         for (int x = 0; x <= 40; x += 40) {
         System.out.println(x);
         g2d.setPaint(Color.red);
         // x,y,width, height,
         g2d.fill(new Rectangle(40, 40, 40, 80));
         g2d.translate(40, 0);
         }
         */
        /*
         Shape rect = new Rectangle2D.Double(0, 0, 40, 80);
    
         g2d.setPaint(Color.red);
         g2d.fill(rect);
         g2d.translate(40, 0);
         g2d.setPaint(Color.green);
         g2d.fill(rect);
         g2d.translate(40, 0);
         g2d.setPaint(Color.blue);
         g2d.fill(rect);
         */
    //g2d.drawLine(0, 0, 100, 100);
    }

    public static void main(String[] args) throws IOException {

        init();
        // Get a DOMImplementation.
        DOMImplementation domImpl
                = GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // Ask the test to render into the SVG Graphics2D implementation.
        TartanMaster test = new TartanMaster();
        test.paint(svgGenerator);

    // Finally, stream out SVG to the standard output using
        // UTF-8 encoding.
        boolean useCSS = true; // we want to use CSS style attributes
        Writer out = new OutputStreamWriter(System.out, "UTF-8");
        svgGenerator.stream(out, useCSS);

    }
}
