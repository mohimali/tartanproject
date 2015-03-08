package tartan.TartanUI;



import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BorderHoverJPanel extends JPanel implements MouseListener
{
    Border blackBorder = BorderFactory.createLineBorder(Color.BLACK,5);
    Border redBorder = BorderFactory.createLineBorder(Color.RED,5);

    public BorderHoverJPanel()
    {
        this.setLayout(new MigLayout(""));
        addMouseListener(this);
        setBorder(blackBorder);
        setFocusable(true);
    }



    @Override public void mouseClicked(MouseEvent e){}
    @Override public void mousePressed(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e)
    {
        setBorder(redBorder);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        setBorder(blackBorder);
    }
}