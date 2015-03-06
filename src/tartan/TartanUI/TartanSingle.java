package tartan.TartanUI;

import net.miginfocom.swing.MigLayout;
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

public class TartanSingle extends JPanel implements  MouseListener {

    SVGTartan svgTartan;
    SVGDocument doc;
    JSVGCanvas canvas;

    Dimension tartanDimensions;
    Border blackBorder = BorderFactory.createLineBorder(Color.white,5);
    Border greenBorder = BorderFactory.createLineBorder(Color.green,5);
    int tartanID = 0;

    public TartanSingle() {

        //this.setLayout(new MigLayout(""));

        final JPanel outside = this;
        addMouseListener(this);
        setBorder(blackBorder);
        setFocusable(true);

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

        //canvas.setOpaque(true);
        canvas.setBackground(Color.WHITE);

        canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                outside.dispatchEvent(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                outside.dispatchEvent(e);
            }
        });

        this.add(canvas);


    }

    public void addTartanToDisplay(Tartan newTartan) {
        svgTartan = new SVGTartan(newTartan);
        doc = svgTartan.getSVGTartan();

        // Display the document.
        canvas.setSVGDocument(doc); // adds doc to canvas

        tartanDimensions = new Dimension(newTartan.getDimensions(), newTartan.getDimensions());
    }


    public void updateTartan(Tartan newTartan) {
        addTartanToDisplay(newTartan);


    }


    @Override
    public Dimension getPreferredSize()
    {
        return tartanDimensions;
    }

    @Override public void mouseClicked(MouseEvent e){}
    @Override public void mousePressed(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e)
    {
        setBorder(greenBorder);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        setBorder(blackBorder);
    }



}