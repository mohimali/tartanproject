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
    private Color rgb;

    //empty constructor
    public PaletteColour()
    {
    }

    public Color hexToRGB(String colourCode) {

        return new Color(
                Integer.valueOf( colourCode.substring( 1, 3 ), 16 ),
                Integer.valueOf( colourCode.substring( 3, 5 ), 16 ),
                Integer.valueOf( colourCode.substring( 5, 7 ), 16 ));
    }

    static String commaSeparatedRGBPattern = "^(\\d{3}),(\\d{3}),(\\d{3})$";
    static final int HEXLENGTH = 8;
    static final String hexaDecimalPattern = "^0x([\\da-fA-F]{1,8})$";

    public Color hexToRGB1(String colourCode) {

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


    public PaletteColour(String type, int id, String name, String code) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.code = code;
        this.rgb = hexToRGB1(code);
    }

    public Color getRGB()
    {
        return this.rgb;
    }

    public Color setRGB(Color rgb)
    {
        return this.rgb = rgb;
    }


    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getID()
    {
        return this.id;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String colour)
    {
        this.name = colour;
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
        setRGB(hexToRGB1(code));
    }



    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Colour Palette - ");
        sb.append("ID:" + getID());
        sb.append(", ");
        sb.append("Colour:" + getName());
        sb.append(", ");
        sb.append("Code:" + getCode());
        sb.append(",");
        sb.append("RGB:" + getRGB());
        sb.append(".");
        return sb.toString();
    }



}
