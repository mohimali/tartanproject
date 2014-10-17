/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tartan;

import java.awt.Color;

/**
 * @author mohim
 */
public class Tartan {

    // make then private and protected later. Find out what each mean.

    public double threadSizes[];
    public Color colours[];
    public int settCount = 0;
    public int warp = 0;
    public int weft = 0;

    public double dimensions = 1;

    Tartan(double requiredThreadSizes[],
           Color requiredColours[], int requiredSettCount,
           double requiredDimensions) {


        settCount = requiredSettCount;
        colours = requiredColours;

        dimensions = requiredDimensions;


        threadSizes = new double[]{};
        threadSizes = computeThreadSizes(requiredThreadSizes);
    } // Constructor tartan


    public double[] computeThreadSizes(double requiredThreadSizes[])
    {
        double total = 0;
        double newThreadSizes[] = new double[requiredThreadSizes.length];


        for (double requiredThreadSize : requiredThreadSizes) {
            total += requiredThreadSize;
        }


        for (int x = 0; x < requiredThreadSizes.length; x++) {


            newThreadSizes[x] = ((requiredThreadSizes[x] / total) * dimensions) / (settCount);
            System.out.println(newThreadSizes[x]);
        }

        return newThreadSizes;

    }

    public void updateThreadSizes(double newThreadSizes[]) {
        threadSizes = newThreadSizes;
    } // updateThreadSizes

    public void updateColors(Color newColours[]) {
        colours = newColours;
    } // updateThreadSizes

    public void updateSettCount(int newSettCount) {
        settCount = newSettCount;
    } // updateSettCount


    public int getSettCount() {
        return settCount;
    } // getSettCount

    public double getThreadSizes(int i) {
        return threadSizes[i];
    } // getThreadSizes

    public int getThreadSizesCount() {
        return threadSizes.length;
    } // getThreadSizes

    public double getDimensions() {
        return dimensions;
    } // getThreadSizes
}
