package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/*
 * This class represents a point charge in the program
 */
public class PointCharge implements Constants {

	private static final long serialVersionUID = 1L;
	public double charge; // same as # of lines that will come out of it
	public int x, y;
	public Point point;
	public List<Double> thetaOfLines = new ArrayList<>();

	// Charge cannot be 0
	public PointCharge(double charge, int x, int y) {
		this.charge = charge;
		this.x = x;
		this.y = y;
		point = new Point(x, y);

	}
	
	public void drawCircle(Graphics2D g2) {		
		if(Math.signum(charge) == 1)  g2.setColor(Color.BLUE);
		else if (Math.signum(charge) ==-1) g2.setColor(Color.RED);
		else g2.setColor(Color.BLACK);
		
		g2.fillOval(x-CHARGE_DIAMETER/2, y-CHARGE_DIAMETER/2, CHARGE_DIAMETER, CHARGE_DIAMETER);
		g2.setColor(Color.WHITE);
		drawCenteredString(g2, "" + charge, new Rectangle(x-CHARGE_DIAMETER/2, y-CHARGE_DIAMETER/2, CHARGE_DIAMETER, CHARGE_DIAMETER), 
				new Font(g2.getFont().getName(), Font.PLAIN, 10));
	}
	
	/**
	 * Draw a String centered in the middle of a Rectangle.
	 *
	 * @param g The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 */
	public void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}
	public boolean equals(PointCharge c) {
		if(c.x ==x && c.y==y && c.charge== charge) return true;
		else return false;
	}
}
