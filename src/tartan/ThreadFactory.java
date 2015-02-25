package tartan;

import java.awt.Color;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mohim Ali on 08/11/2014.
 */
public class ThreadFactory {

    public static Color K = Color.BLACK;  //BLACK
    public static Color T = new Color(165, 42, 42);  //BROWN
    public static Color N = Color.GRAY; // GREY

    public static Color B = Color.BLUE; // BLUE
    public static Color G = Color.GREEN; // GREEN
    public static Color O = Color.ORANGE; // ORANGE
    public static Color P = Color.PINK; // PINK
    public static Color R = Color.RED; // RED
    public static Color W = Color.WHITE; // WHITE
    public static Color Y = Color.YELLOW; // YELLOW
    public static Color M = Color.MAGENTA; // MAGENTA
    public static Color C = Color.CYAN; // CYAN

    // Patterns - Eager Instantiation
    ArrayList<TartanThread> threads = new ArrayList<TartanThread>();


    public static synchronized ArrayList<TartanThread> buildThread(String string) {


        String[] parts = string.split(",");


        ArrayList<TartanThread> threads = new ArrayList<TartanThread>();

        Map<String, Color> myColor = new HashMap<String, Color>();

        myColor.put("K", K);
        myColor.put("T", T);
        myColor.put("N", N);
        myColor.put("B", B);
        myColor.put("G", G);
        myColor.put("O", O);
        myColor.put("P", P);
        myColor.put("R", R);
        myColor.put("W", W);
        myColor.put("Y", Y);
        myColor.put("M", M);
        myColor.put("C", C);


        int index = 0;
        while (index < parts.length) {
            Pattern p = Pattern.compile("([a-zA-Z]+)(\\d+)");
            Matcher m = p.matcher(parts[index]); //Blue 6, Green 8
            while (m.find()) {
                String colourToken = m.group(1);  // Thread Color
                String sizeToken = m.group(2); // Thread Size
                threads.add(new TartanThread(myColor.get(colourToken), "X", Integer.parseInt(sizeToken)));
            }
            index++;
        } // OUTER WHILE

        return threads;
    }
}

