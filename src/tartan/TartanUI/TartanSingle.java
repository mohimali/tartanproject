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
import javax.swing.event.MouseInputListener;
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
    SVGTartan svgTartan;
    SVGDocument doc;
    JSVGCanvas canvas;
    Dimension tartanDimensions;
    Color background = Color.darkGray;
    Border highlightBorder = BorderFactory.createLineBorder(Color.green, 3);
    Border originalBorder = BorderFactory.createLineBorder(Color.darkGray, 3);
    int tartanID = 0;
    boolean updateDetected = true;
    Tartan currentTartan;
    final JPanel outside = this;

    public Tartan getTartan() {
        return currentTartan;
    } //getTartan

    public void doCreationCanvas(Tartan tartan) {
        setBorder(originalBorder);
        tartanDimensions = new Dimension(tartan.getDimensions(), tartan.getDimensions());
        svgTartan = new SVGTartan(tartan);
        doc = svgTartan.getSVGTartan();
        // Display the document.
        canvas = new JSVGCanvas(); // holds doc with graphics
        canvas.setSVGDocument(doc); // adds doc to canvas

        //MUST MATCH TARTAN DIMENSIONS
        canvas.setPreferredSize(tartanDimensions);

        canvas.setOpaque(true);
        canvas.setBackground(Color.white);

        canvas.setTransferHandler(null);
        this.setBackground(background);
        this.setPreferredSize(tartanDimensions);


    }

    public TartanSingle(Tartan ts) {
        currentTartan = ts;
        addMouseListener(this);
        this.setLayout(new MigLayout(""));
        //this.setBorder(BorderFactory.createEmptyBorder());
        tartanID = 0;
        doCreationCanvas(ts);
        canvas.addMouseListener(new MouseInputListener() {
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
                outside.dispatchEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                outside.dispatchEvent(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        addCanvas();

    }

    public TartanSingle(int index, Tartan tartanx) {
        addMouseListener(this);
        currentTartan = tartanx;
        this.setLayout(new MigLayout(""));
        //this.setBorder(BorderFactory.createEmptyBorder());
        tartanID = index;
        final JPanel outside = this;
        doCreationCanvas(tartanx);
        canvas.addMouseListener(new MouseInputListener() {
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
                outside.dispatchEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                outside.dispatchEvent(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        addCanvas();

    }

    public void addCanvas() {
        this.add(canvas, "push, align center");
    }

    public void addTartanToDisplay(Tartan newTartan) {

        currentTartan = newTartan;
        //System.out.println("addTartanToDisplay" + newTartan);
        svgTartan = new SVGTartan(newTartan);
        doc = svgTartan.getSVGTartan();

        // Display the document.
        canvas.setSVGDocument(doc); // adds doc to canvas

        tartanDimensions = new Dimension(newTartan.getDimensions(), newTartan.getDimensions());
    }


    //@Override
    public void paintComponent1(Graphics g) {
        if (currentTartan != null) {

            //this.paintTartan((Graphics2D) this.getGraphics(), currentTartan);
            super.paintComponent(g);
            Graphics2D aaa = this.paintTartan((Graphics2D) g, currentTartan);
            //super.paintComponent(aaa);
            if (updateDetected) {
                //this.repaint();
                updateDetected = false;
                //this.repaint();
            }

        } // big if


    } //paintComponent

    public void setUpdateStatus(boolean status) {
        updateDetected = status;
        //repaint();
    }

    public Graphics2D paintTartan(Graphics2D g2d, Tartan tartan1) {

        int alpha1 = 100;
        int alpha2 = 0;

        //System.out.println(tartan1.toString());


        for (int i = 0; i < tartan1.getSettCount(); i += 1) {
            //forwards pattern
            int tempIndex = 0;

            // PRINT WARP  downwards
            for (int x = 0; x < tartan1.getThreadSizesCount(); x += 1) {
                g2d.setPaint(tartan1.getThreadColour(x));
                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getThreadSizes(tempIndex) / (double) tartan1.getSettCount(), tartan1.getDimensions()) {
                });
                g2d.translate(tartan1.getThreadSizes(tempIndex) / (double) tartan1.getSettCount(), 0);
                //System.out.println("getThreadSizes:" + tartan1.getThreadSizes(tempIndex) / (double) tartan1.getSettCount());
                //System.out.println("transformSingleBefore:" + g2d.getTransform().getTranslateX());

                tempIndex++;

            } //forwards for

            //System.out.println("transformHalfBefore:" + g2d.getTransform().getTranslateX());

            //reverse pattern
            for (int x = tartan1.getThreadSizesCount() - 1; x >= 0; x -= 1) {
                g2d.setPaint(tartan1.getThreadColour(x));
                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getThreadSizes(tempIndex) / (double) tartan1.getSettCount(), tartan1.getDimensions()) {
                });
                g2d.translate(tartan1.getThreadSizes(tempIndex) / (double) tartan1.getSettCount(), 0);
                tempIndex++;
            } //reverse for

        } // most outer for


        //System.out.println("transformBefore:" + g2d.getTransform().getTranslateX());
        g2d.translate(-1 * g2d.getTransform().getTranslateX(), 0);
        //g2d.translate(0,0);
        //System.out.println("transformMOHIM:" + g2d.getTransform().getTranslateX());

        //WEFT

        for (int i = 0; i < tartan1.getSettCount(); i += 1) {
            int tempIndex = 0;
            //forwards pattern
            for (int x = 0; x < tartan1.getThreadSizesCount(); x += 1) {

                Color color = new Color(tartan1.getThreadColour(x).getRed(),
                        tartan1.getThreadColour(x).getGreen(),
                        tartan1.getThreadColour(x).getBlue(), alpha1); //Red
                g2d.setPaint(color);

                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getDimensions(), tartan1.getThreadSizes(tempIndex) / (double) tartan1.getSettCount()) {
                });
                g2d.translate(0, tartan1.getThreadSizes(tempIndex) / (double) tartan1.getSettCount());
                tempIndex++;
            } //forwards for


            //reverse pattern
            for (int x = tartan1.getThreadSizesCount() - 1; x >= 0; x -= 1) {
                Color color = new Color(tartan1.getThreadColour(x).getRed(),
                        tartan1.getThreadColour(x).getGreen(),
                        tartan1.getThreadColour(x).getBlue(), alpha1); //Red
                g2d.setPaint(color);

                g2d.fill(new Rectangle2D.Double(0, 0, tartan1.getDimensions(), tartan1.getThreadSizes(tempIndex) / (double) tartan1.getSettCount()) {
                });
                g2d.translate(0, tartan1.getThreadSizes(tempIndex) / (double) tartan1.getSettCount());
                tempIndex++;
            } //reverse for

        } // FOR WEFT

        //System.out.println("");
        return g2d;
    } // paintTartan


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
        setBorder(highlightBorder);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(originalBorder
        );
    }


}