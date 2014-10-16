/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tartan;

import java.awt.Color;
/**
 *
 * @author mohim
 */
public class Tartan {
    
   public int threadCount = 0;
   public int threadSizes[];
   public Color colours[];
   public int settCount = 0;
   public int warp = 0;
   public int weft = 0;
   
   

    private Tartan(int requiredThreadCount, int requiredThreadSizes[],
                   Color requiredColours[], int requiredSettCount)
    {
        threadCount = requiredThreadCount;
        threadSizes = requiredThreadSizes;
        
        settCount = requiredSettCount;
        colours = requiredColours;
        
        
    } // Constructor tartan
    
    public void updateThreadCount(int newThreadCount)
    {
        threadCount = newThreadCount;
    } // updateThreadCount
    
    public void updateThreadSizes(int newThreadSizes[])
    {
        threadSizes = newThreadSizes;
    } // updateThreadSizes
    
    public void updateColors(Color newColours[])
    {
        colours = newColours;
    } // updateThreadSizes
    
    public void updateSettCount(int newSettCount)
    {
        settCount = newSettCount;
    } // updateSettCount
    
    public int getThreadCount()
    {
        return threadCount;
    } // getThreadCount
    
    public int getSettCount()
    {
        return settCount;
    } // getSettCount
    
}
