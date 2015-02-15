package tartan.TartanUI;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mohim on 09/02/2015.
 */
public class PaletteColour {
    private String type;
    private int id;
    private String name;
    private String code;
    private Color colour;

    static String commaSeparatedRGBPattern = "^(\\d{3}),(\\d{3}),(\\d{3})$";
    static final int HEXLENGTH = 8;
    static final String hexaDecimalPattern = "^0x([\\da-fA-F]{1,8})$";

    //empty constructor
    public PaletteColour() {
    }


    public static String getRGBFormat(Color c) {
        String rgb = ("" + String.format("%03d", c.getRed()) + ","
                + String.format("%03d", c.getGreen()) + ","
                + String.format("%03d", c.getBlue()));

        return rgb;
    }


    public static Color hexToColour(String colourCode) {

        //modified from http://sanjaal.com/java/751/java-utilities/rgb-to-hex-and-hex-to-rgb-conversion-using-java-and-regular-expressions/
        Color rgbValue = Color.WHITE;//DEFAULT
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


    // CODE BEING PASSED IN IS HEX form 0x000000
    public PaletteColour(String type, int id, String name, String code) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.code = code;
        this.colour = this.hexToColour(code);


    }

    //CODE BEING PASSED IN IS RGB in form "244,222,123" seperated by commas
    // if we are passed in the mode=1 then that means the code used is of type rgb so we need to convert accordingly
    public PaletteColour(String type, int id, String name, String code, int mode) {
        this.type = type;
        this.id = id;
        this.name = name;
        if (mode == 1) {
            System.out.println("Step1: " + code);
            this.code = this.rgbToHex(code);
            System.out.println("Step2: " + this.code);
            this.colour = this.hexToColour(this.code);
            System.out.println("Step3: " + this.colour);

        } else {
            this.code = code;
            this.colour = this.hexToColour(code);
        }
    }

    public Color getColour() {
        return this.colour;
    }

    public Color setColour(Color requiredColour) {
        return this.colour = requiredColour;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String colour) {
        this.name = colour;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
        setColour(hexToColour(code));
    }


    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Colour Palette - ");
        sb.append("ID:" + getID());
        sb.append(", ");
        sb.append("Colour:" + getName());
        sb.append(", ");
        sb.append("Code:" + getCode());
        sb.append(",");
        sb.append("RGB:" + getColour());
        sb.append(".");
        return sb.toString();
    }

    public static void main(String[] args) {
        //Color c = Color.RED;
        // String rgb = ("" + String.format("%03d", c.getRed()) + ","
        //    + String.format("%03d", c.getGreen())  + ","
        //     + String.format("%03d", c.getBlue()));

        //String.format("%03d", yournumber);
        //String other = rgbToHex(rgb);
        //System.out.println("Test" + other );

        //Color newwwww = hexToColour(other);
        // System.out.println("Test2" + newwwww );


    }


}
