/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tartan;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

// Im using swing(paintComponent()) + batik so use graphics2d
import org.apache.batik.swing.*;
import org.apache.batik.svggen.*;
import org.apache.batik.dom.svg.SVGDOMImplementation;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.w3c.dom.*;
import org.w3c.dom.svg.*;

public class TartanMasterBackup extends JPanel {

    public static Dimension dimension;
    public JSVGCanvas canvas;
    
    public TartanMasterBackup() {
        canvas = new JSVGCanvas();
        setBackground(Color.WHITE);
    }
    
    public static void init() {
        
        // Size of JFrame
        dimension = new Dimension(600,400); 
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(0, 0, getWidth(), getHeight());
        /*
            // Create a converter for this document.
        SVGGraphics2D g2d = SVGGraphics2D g;
        
        Shape rect = new Rectangle2D.Double(0, 0, 200, 400);
        g2d.setPaint(Color.red);
        g2d.fill(rect);

        g2d.setSVGCanvasSize(new Dimension(400, 400));
        // Populate the document root with the generated SVG content.
        Element root = doc.getDocumentElement();
        g2d.getRoot(root);
                */
               
    }
    
    public static SVGDocument createSVG()
    {
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);
        
        return doc;
    }
    
    public static SVGDocument createTartan(SVGDocument doc)
    {
      
        
        return doc;
    }
    
    public static void createJFrame(SVGDocument doc)
    {
        
        
    
       
        
       
        
        // Display the document.
        
        JFrame jFrame = new JFrame();
        jFrame.setSize(dimension);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.add(new TartanMasterBackup());
        JSVGCanvas canvas = new JSVGCanvas();
        // When user clicks close, safely dispose of the JFrame.
        
        
        // Add the canvas to the JFrame
        jFrame.getContentPane().add(canvas);
        canvas.setSVGDocument(doc);
        jFrame.pack();
        jFrame.setVisible(true);
        
    }
    
    public static void main(String[] args) {

        // Set up variables  
        init();
        
        // Create an SVG document.
        SVGDocument doc = createSVG();
        
        

        
        // Draw Graphics and add to existing doc
        //doc = createTartan(doc);

        // Add the doc to the JFrame
        createJFrame(doc);
    } // main
    
} // class
