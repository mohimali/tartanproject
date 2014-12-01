package tartan;
import java.awt.Color;

/**
 * This class records a single instance of a tartan.Tartan thread which
 * consists of a colour and size. It was intentionally named TartanThread
 * rather then Thread on its own because java already has a class called
 * Thread. To avoid confusion it has been named TartanThread.
 */
public class TartanThread {

    Color colour;
    int   size;

    public TartanThread(Color requiredThreadColour, int requiredThreadSize )
    {
        colour = requiredThreadColour;
        size = requiredThreadSize;

    }


    public TartanThread getTartanThread()
    {
        return this;
    }

    public Color getColour()
    {
        return colour;

    }


    public int getSize()
    {
        return size;

    }

    public String toString()
    {
        return "Colour:" + colour + " Size: " + size ;

    }


}
