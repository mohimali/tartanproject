/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tartan;

import javax.swing.JApplet;

/*	@(#)Tartan.java	Copyright 1997 Gregory J. Scott */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.*;
import java.lang.*;
import java.net.URL;

public class Tartan1 extends java.applet.Applet {

    Object colors[];
    int counts[];
    int cMax;
    String tartan;

    public void init() {
        int i;
        colors = new Color[20];
        counts = new int[20];
        String sr, sg, sb, sc;
        i = 0;
        cMax = 0;

        sc = getParameter("c00");
        sr = getParameter("r00");
        sg = getParameter("g00");
        sb = getParameter("b00");
        System.out.println(sc);
        System.out.println(sr);
        System.out.println(sg);
        System.out.println(sb);
        counts[0] = Integer.parseInt(sc);

        colors[0] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));
        
        sc = getParameter("c01");
        sr = getParameter("r01");
        sg = getParameter("g01");
        sb = getParameter("b01");
        counts[1] = Integer.parseInt(sc);
        colors[1] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c02");
        sr = getParameter("r02");
        sg = getParameter("g02");
        sb = getParameter("b02");
        counts[2] = Integer.parseInt(sc);
        colors[2] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c03");
        sr = getParameter("r03");
        sg = getParameter("g03");
        sb = getParameter("b03");
        counts[3] = Integer.parseInt(sc);
        colors[3] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c04");
        sr = getParameter("r04");
        sg = getParameter("g04");
        sb = getParameter("b04");
        counts[4] = Integer.parseInt(sc);
        colors[4] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c05");
        sr = getParameter("r05");
        sg = getParameter("g05");
        sb = getParameter("b05");
        counts[5] = Integer.parseInt(sc);
        colors[5] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c06");
        sr = getParameter("r06");
        sg = getParameter("g06");
        sb = getParameter("b06");
        counts[6] = Integer.parseInt(sc);
        colors[6] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c07");
        sr = getParameter("r07");
        sg = getParameter("g07");
        sb = getParameter("b07");
        counts[7] = Integer.parseInt(sc);
        colors[7] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c08");
        sr = getParameter("r08");
        sg = getParameter("g08");
        sb = getParameter("b08");
        counts[8] = Integer.parseInt(sc);
        colors[8] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c09");
        sr = getParameter("r09");
        sg = getParameter("g09");
        sb = getParameter("b09");
        counts[9] = Integer.parseInt(sc);
        colors[9] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c10");
        sr = getParameter("r10");
        sg = getParameter("g10");
        sb = getParameter("b10");
        counts[10] = Integer.parseInt(sc);
        colors[10] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c11");
        sr = getParameter("r11");
        sg = getParameter("g11");
        sb = getParameter("b11");
        counts[11] = Integer.parseInt(sc);
        colors[11] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c12");
        sr = getParameter("r12");
        sg = getParameter("g12");
        sb = getParameter("b12");
        counts[12] = Integer.parseInt(sc);
        colors[12] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c13");
        sr = getParameter("r13");
        sg = getParameter("g13");
        sb = getParameter("b13");
        counts[13] = Integer.parseInt(sc);
        colors[13] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c14");
        sr = getParameter("r14");
        sg = getParameter("g14");
        sb = getParameter("b14");
        counts[14] = Integer.parseInt(sc);
        colors[14] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c15");
        sr = getParameter("r15");
        sg = getParameter("g15");
        sb = getParameter("b15");
        counts[15] = Integer.parseInt(sc);
        colors[15] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c16");
        sr = getParameter("r16");
        sg = getParameter("g16");
        sb = getParameter("b16");
        counts[16] = Integer.parseInt(sc);
        colors[16] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c17");
        sr = getParameter("r17");
        sg = getParameter("g17");
        sb = getParameter("b17");
        counts[17] = Integer.parseInt(sc);
        colors[17] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c18");
        sr = getParameter("r18");
        sg = getParameter("g18");
        sb = getParameter("b18");
        counts[18] = Integer.parseInt(sc);
        colors[18] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        sc = getParameter("c19");
        sr = getParameter("r19");
        sg = getParameter("g19");
        sb = getParameter("b19");
        counts[19] = Integer.parseInt(sc);
        colors[19] = new Color(Integer.parseInt(sr),
                Integer.parseInt(sg),
                Integer.parseInt(sb));

        for (i = 0; i <= 19; i++) {
            if (counts[i] > 0) {
                cMax++;
            }
        }
    }

    public void paint(Graphics g) {
        int x, y, xMin, xMax, yMin, yMax, c, i, j, nStart;
        boolean bOver;
        xMax = getSize().width;
        yMax = getSize().height;
        xMin = 0;
        x = xMin;
        yMin = 0;
        y = yMin;

        while (y < yMax) {
            for (c = 0; c <= cMax; c++) {
                g.setColor((Color) (colors[c]));
                for (j = 1; j <= counts[c]; j++) {
                    if (y < yMax) {
                        g.drawLine(xMin, y, xMax, y);
                        y++;
                    }
                }
            }
            for (c = cMax; c > 0; c--) {
                g.setColor((Color) (colors[c]));
                for (j = 1; j <= counts[c]; j++) {
                    if (y < yMax) {
                        g.drawLine(xMin, y, xMax, y);
                        y++;
                    }
                }
            }
        }

        nStart = 0;
        while (x < xMax) {
            for (c = 0; c <= cMax; c++) {
                g.setColor((Color) (colors[c]));
                for (j = 1; j <= counts[c]; j++) {
                    if (x < xMax) {
                        i = nStart;
                        while (i + 1 < yMax) {
                            g.drawLine(x, i, x, i + 1);
                            i += 4;
                        }
                        x++;
                        nStart = (nStart + 1) % 4;
                    }
                }
            }

            for (c = cMax; c > 0; c--) {
                g.setColor((Color) (colors[c]));
                for (j = 1; j <= counts[c]; j++) {
                    if (x < xMax) {
                        i = nStart;
                        while (i + 1 < yMax) {
                            g.drawLine(x, i, x, i + 1);
                            i += 4;
                        }
                        x++;
                        nStart = (nStart + 1) % 4;
                    }
                }
            }

        }

    }
}
