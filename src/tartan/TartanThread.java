package tartan;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class records a single instance of a tartan.Tartan thread which
 * consists of a colour and size. It was intentionally named "TartanThread"
 * rather then "Thread" on its own because java already has a class called
 * "Thread". To avoid confusion it has been named TartanThread.
 */
public class TartanThread {

    Color colour;
    int threadCount;
    String colourName = "";
    String colourShortHand = "";
    private String colourCode;
    static String commaSeparatedRGBPattern = "^(\\d{3}),(\\d{3}),(\\d{3})$";
    static final int HEXLENGTH = 8;
    static final String hexaDecimalPattern = "^0x([\\da-fA-F]{1,8})$";


    public TartanThread() {
        //colour = requiredThreadColour;
        //threadCount = requiredThreadCount;
        //colourShortHand = requiredColourShortHand;
    }


    public TartanThread(Color requiredThreadColour, String requiredColourShortHand ,int requiredThreadCount) {
        colour = requiredThreadColour;
        threadCount = requiredThreadCount;
        colourShortHand = requiredColourShortHand;
        colourCode = rgbToHex(getRGBFormat(requiredThreadColour));
    }

    public TartanThread(Color requiredThreadColour, String requiredColourShortHand , int requiredThreadCount, String requiredColourName) {
        //chosenColour,colourShortHand,chosenThreadCount,chosenColourName

        colour = requiredThreadColour;
        threadCount = requiredThreadCount;
        colourName = requiredColourName;
        colourShortHand = requiredColourShortHand;
        colourCode = rgbToHex(getRGBFormat(requiredThreadColour));
    }

    public String getColourCode()
    {
        return colourCode;
    }

    public static String getRGBFormat(Color c) {
        String rgb = ("" + String.format("%03d", c.getRed()) + ","
                + String.format("%03d", c.getGreen()) + ","
                + String.format("%03d", c.getBlue()));

        return rgb;
    }


    public static Color hexToColour(String colourCode) {

        //modified from http://sanjaal.com/java/751/java-utilities/rgb-to-hex-and-hex-to-rgb-conversion-using-java-and-regular-expressions/
        Color rgbValue = Color.PINK;//DEFAULT
        Pattern hexPattern = Pattern.compile(hexaDecimalPattern);
        Matcher hexMatcher = hexPattern.matcher(colourCode);

        if (hexMatcher.find()) {
            int hexInt = Integer.valueOf(colourCode.substring(2), 16)
                    .intValue();

            int r = (hexInt & 0xFF0000) >> 16;
            int g = (hexInt & 0xFF00) >> 8;
            int b = (hexInt & 0xFF);
            rgbValue = new Color(r, g, b);
        }
        return rgbValue;
    }

    private static String rgbToHex(String rgb) {
        String hexValue = "";
        Pattern rgbPattern = Pattern.compile(commaSeparatedRGBPattern);
        Matcher rgbMatcher = rgbPattern.matcher(rgb);

        int red;
        int green;
        int blue;
        if (rgbMatcher.find()) {
            red = Integer.parseInt(rgbMatcher.group(1));
            green = Integer.parseInt(rgbMatcher.group(2));
            blue = Integer.parseInt(rgbMatcher.group(3));
            Color color = new Color(red, green, blue);
            hexValue = Integer.toHexString(color.getRGB() & 0x00ffffff);
            int numberOfZeroesNeededForPadding = HEXLENGTH - hexValue.length();
            String zeroPads = "";
            for (int i = 0; i < numberOfZeroesNeededForPadding; i++) {
                zeroPads += "0";
            }
            hexValue = "0x" + zeroPads + hexValue;
        }
        return hexValue;
    }

    public void setColourName(String requiredColourName)
    {
        colourName = requiredColourName;
    }

    public String getColourName()
    {
        return colourName;
    }


    public TartanThread getTartanThread() {
        return this;
    }

    public Color getColour() {
        return colour;

    }


    public int getThreadCount() {
        return threadCount;

    }

    public String toString() {
        return "Colour:" + colour + " Size: " + threadCount + " Name: " + colourName + " Short: " + colourShortHand;

    }


    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
        this.colour = hexToColour(colourCode);
    }

    public void setColourShortHand(String colourShortHand) {
        this.colourShortHand = colourShortHand;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public String getColourShortHand() {
        return colourShortHand;
    }
}
