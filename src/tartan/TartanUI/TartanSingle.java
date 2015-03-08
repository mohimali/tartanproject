package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;
import tartan.SVGTartan;
import tartan.Tartan;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TooManyListenersException;



public class TartanSingle extends JPanel implements MouseListener {
    JPanel borderContainer = new JPanel(new MigLayout());
    SVGTartan svgTartan;
    SVGDocument doc;
    JSVGCanvas canvas;
    Dimension tartanDimensions;
    Border blackBorder = BorderFactory.createLineBorder(Color.red, 5);
    Border greenBorder = BorderFactory.createLineBorder(Color.red, 5);
    int tartanID = 0;
    boolean updateDetected = true;


    Tartan currentTartan;
    final JPanel outside = this;

    public Tartan getTartan() {
        return currentTartan;
    } //getTartan


    public TartanSingle(Tartan ts) {
        currentTartan = ts;

        this.setLayout(new MigLayout(""));
        //this.setLayout(new MigLayout(""));
        tartanID = 0;

        addMouseListener(this);


        tartanDimensions = new Dimension(100, 100);

        // Create an instance of an SVG styled tartan
        //SVGTartan svgTartan = new SVGTartan(tartan);

        Tartan tartan2 = new Tartan(100, 2);

        svgTartan = new SVGTartan(tartan2);
        doc = svgTartan.getSVGTartan();

        // Display the document.
        canvas = new JSVGCanvas(); // holds doc with graphics
        canvas.setSVGDocument(doc); // adds doc to canvas

        //MUST MATCH TARTAN DIMENSIONS
        canvas.setPreferredSize(tartanDimensions);
        this.setPreferredSize(tartanDimensions);

        canvas.setOpaque(true);
        canvas.setBackground(Color.WHITE);
        this.setOpaque(true);

        this.setBackground(Color.white);



        /*
        canvas.addMouseListener(new MouseInputAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                outside.dispatchEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                outside.dispatchEvent(e);
            }

        });*/


        //this.add(canvas);

        this.addTartanToDisplay(ts);

    }

    public TartanSingle(int index) {

        this.setLayout(new MigLayout(""));
        tartanID = index;
        final JPanel outside = this;


        addMouseListener(this);
        tartanDimensions = new Dimension(100, 100);

        // Create an instance of an SVG styled tartan
        //SVGTartan svgTartan = new SVGTartan(tartan);

        Tartan tartan2 = new Tartan(100, 2);

        svgTartan = new SVGTartan(tartan2);
        doc = svgTartan.getSVGTartan();

        // Display the document.
        canvas = new JSVGCanvas(); // holds doc with graphics
        canvas.setSVGDocument(doc); // adds doc to canvas

        //MUST MATCH TARTAN DIMENSIONS
        canvas.setPreferredSize(tartanDimensions);
        this.setPreferredSize(tartanDimensions);

        canvas.setOpaque(true);
        canvas.setBackground(Color.WHITE);
        this.setBackground(Color.white);
        this.setOpaque(true);


        canvas.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                outside.dispatchEvent(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                outside.dispatchEvent(e);
            }
        });


        //this.add(canvas);

    }



    public void addTartanToDisplay(Tartan newTartan) {

        currentTartan = newTartan;
        svgTartan = new SVGTartan(newTartan);
        doc = svgTartan.getSVGTartan();
        tartanDimensions = new Dimension(newTartan.getDimensions(), newTartan.getDimensions());
        //MUST MATCH TARTAN DIMENSIONS
        canvas.setPreferredSize(tartanDimensions);
        this.setPreferredSize(tartanDimensions);
        // Display the document.
        canvas.setSVGDocument(doc); // adds doc to canvas

        //this.paintTartan(svgGenerator,currentTartan);
        //paintComponent1();

        updateDetected = true;
        this.repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        g.setClip(0, 0, 150, 150);
        if (currentTartan != null) {

            //this.paintTartan((Graphics2D) this.getGraphics(), currentTartan);

            Graphics2D aaa = this.paintTartan((Graphics2D) g, currentTartan);

            if (updateDetected) {
                updateDetected = false;
                this.repaint();

            }

        } // big if


    } //paintComponent

    public void setUpdateStatus(boolean status)
    {
        updateDetected = status;
    }

    public Graphics2D paintTartan(Graphics2D g2d, Tartan tartan1) {
        System.out.println("Sett CountX: " + tartan1.getSettCount());

        int alpha1 = 100;
        int alpha2 = 0;
        for (int i = 0; i < tartan1.getSettCount(); i += 1) {
            //System.out.println("INDEX : " + i);
            //forwards pattern
            int tempIndex = 0;

            // PRINT WARP  downwards
            for (int x = 0; x < tartan1.getThreadSizesCount(); x += 1) {
                g2d.setPaint(tartan1.getThreadColour(x));
                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getThreadSizes(tempIndex) / tartan1.getSettCount(), tartan1.getDimensions()) {
                });
                g2d.translate(tartan1.getThreadSizes(tempIndex) / tartan1.getSettCount(), 0);
                tempIndex++;
            } //forwards for

            //reverse pattern
            for (int x = tartan1.getThreadSizesCount() - 1; x >= 0; x -= 1) {
                g2d.setPaint(tartan1.getThreadColour(x));
                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getThreadSizes(tempIndex) / tartan1.getSettCount(), tartan1.getDimensions()) {
                });
                g2d.translate(tartan1.getThreadSizes(tempIndex) / tartan1.getSettCount(), 0);
                tempIndex++;
            } //reverse for

        } // most outer for


        System.out.println("transformBefore:" + g2d.getTransform().getTranslateX());
        g2d.translate(-1 * g2d.getTransform().getTranslateX(), 0);
        //g2d.translate(0,0);
        System.out.println("transformMOHIM:" + g2d.getTransform().getTranslateX());

        //WEFT

        for (int i = 0; i < tartan1.getSettCount(); i += 1) {
            int tempIndex = 0;
            //forwards pattern
            for (int x = 0; x < tartan1.getThreadSizesCount(); x += 1) {

                Color color = new Color(tartan1.getThreadColour(x).getRed(),
                        tartan1.getThreadColour(x).getGreen(),
                        tartan1.getThreadColour(x).getBlue(), alpha1); //Red
                g2d.setPaint(color);

                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getDimensions(), tartan1.getThreadSizes(tempIndex) / tartan1.getSettCount()) {
                });
                g2d.translate(0, tartan1.getThreadSizes(tempIndex) / tartan1.getSettCount());
                tempIndex++;
            } //forwards for


            //reverse pattern
            for (int x = tartan1.getThreadSizesCount() - 1; x >= 0; x -= 1) {
                Color color = new Color(tartan1.getThreadColour(x).getRed(),
                        tartan1.getThreadColour(x).getGreen(),
                        tartan1.getThreadColour(x).getBlue(), alpha1); //Red
                g2d.setPaint(color);

                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getDimensions(), tartan1.getThreadSizes(tempIndex) / tartan1.getSettCount()) {
                });
                g2d.translate(0, tartan1.getThreadSizes(tempIndex) / tartan1.getSettCount());
                tempIndex++;
            } //reverse for

        } // FOR WEFT
        //super.paintComponent(g2d);

        return g2d;
    } // paintTartan


    public void updateTartan(Tartan newTartan) {
        addTartanToDisplay(newTartan);


    }


    @Override
    public Dimension getPreferredSize() {
        return tartanDimensions;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Panel entered");

    }

    @Override
    public void mouseExited(MouseEvent e) {

        System.out.println("Panel exited");
    }


}