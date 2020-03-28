package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Panel;

/*
 * This class represents a point charge in the program
 */
public class PointCharge extends Panel implements Constants {

	private static final long serialVersionUID = 1L;
	public double charge; // same as # of lines that will come out of it
	public int x, y;
	public Point point;
	public int linesConnected = 0;

	// Charge cannot be 0
	public PointCharge(double charge, int x, int y) {
		this.charge = charge;
		this.x = x;
		this.y = y;
		point = new Point(x, y);
		linesConnected = 0;
	}
	
	public void drawCircle(Graphics2D g2) {		
		if(Math.signum(charge) == 1)  g2.setColor(Color.BLUE);
		else if (Math.signum(charge) ==-1) g2.setColor(Color.RED);
		
		g2.fillOval(x-CHARGE_DIAMETER/2, y-CHARGE_DIAMETER/2, CHARGE_DIAMETER, CHARGE_DIAMETER);
	}
	
	public boolean equals(PointCharge c) {
		if(c.x ==x && c.y==y && c.charge== charge) return true;
		else return false;
	}
}
