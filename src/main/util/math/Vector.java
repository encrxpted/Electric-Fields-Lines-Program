package main.util.math;

/*
 * This class represents a 2D vector with x and y components
 */
public class Vector {
	public double mag;
	public double theta;
	public double xComp, yComp;
	
	// theta always in radians
	public Vector(double mag, double theta) {
		this.mag = mag;
		this.theta = theta;
		
		xComp = mag * Math.cos(theta);
		yComp = mag * Math.sin(theta);
	}
	
	public Vector(double xComp, double yComp, boolean useComps) {
		this.xComp = xComp;
		this.yComp = yComp;
		
		mag = Math.sqrt(xComp*xComp + yComp*yComp);
		
		theta = findAngle(mag, xComp, yComp);
	}
	
	public Vector(double startX, double startY, double endX, double endY) {
		xComp = endX - startX;
		yComp = endY - startY;
		
		mag = Math.sqrt(xComp*xComp + yComp*yComp);
		theta = findAngle(mag, xComp, yComp);
	}
	
	public Vector(Point start, Point end) {
		xComp = end.x - start.x;
		yComp = end.y - start.y;
		
		mag = Math.sqrt(xComp*xComp + yComp*yComp);
		theta = findAngle(mag, xComp, yComp);
	}
	
	public Vector add(Vector v) {
		return new Vector(xComp + v.xComp, yComp + v.yComp, true);
	}
	
	public Vector add(Point p) {
		return new Vector(xComp + p.x, yComp + p.y, true);
	}
	
	private double findAngle(double m, double x, double y) {
		if(x >= 0 && y>=0)
			return Math.atan(y/x);
		else if(x>=0 && y < 0) 
			return Math.atan(y/x) + 2*Math.PI;
		else if(x < 0 && y >=0) 
			return Math.atan(y/x) + Math.PI;
		else
			return Math.atan(y/x) + Math.PI;
	}
	
	public Vector clone() {
		return new Vector(mag, theta);
	}
	
	public String toString() {
		return "Mag: " + mag + "  Theta: " + theta;
	}
}
