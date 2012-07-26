package com.notsoaverage.colormanager;

/*
This software is released free of charge, as is, by Harlan Haskins, under the
terms of the GNU General Public License, viewable here: 
http://www.gnu.org/licenses/gpl.html
*/

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GradientPanel extends JPanel {
	
	Color color1 = new Color(255, 255, 255);
	Color color2 = new Color(255, 255, 255);
	
	public GradientPanel(int i, int j, int k, int l, int m, int n) {
		color1 = new Color(i, j, k);
		color2 = new Color(l, m, n);
	}
	
	public GradientPanel(int i, int j) {
        color1 = new Color(i, i, i);
        color2 = new Color(j, j, j);
    }
	
	public GradientPanel(Color c1, Color c2) {
        color1 = c1;
        color2 = c2;
    }
	
    public GradientPanel(int i, int j, int k, Color c2) {
        color1 = new Color(i, j, k);
        color2 = c2;
    }
    
    public GradientPanel(Color c1, int l, int m, int n) {
        color1 = c1;
        color2 = new Color(l, n, m);
    }
    
	public void setColors(int i, int j, int k, int l, int m, int n) {
		color1 = new Color(i, j, k);
		color2 = new Color(l, m, n);
	}
	
	public void setColors(int i, int j) {
        color1 = new Color(i, i, i);
        color2 = new Color(j, j, j);
    }
	
	public void setColors(Color c1, Color c2) {
        color1 = c1;
        color2 = c2;
    }
	
    public void setColors(int i, int j, int k, Color c2) {
        color1 = new Color(i, j, k);
        color2 = c2;
    }
    
    public void setColors(Color c1, int l, int m, int n) {
        color1 = c1;
        color2 = new Color(l, n, m);
    }

	public void setColor(int i, int j, int k, int l) {
		if (i == 1)
			color1 = new Color(j, k, l);
		else
			color2 = new Color(j, k, l);
	}
	
	public void setColor(int i, Color c) {
		if (i == 1)
			color1 = c;
		else
			color2 = c;
	}
	
	public Color getColor(int i) {
		if (i == 1)
			return color1;
		return color2;
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		int w = getWidth();
		int h = getHeight();
		GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
		setOpaque(false);
		super.paintComponent(g);
	}
}