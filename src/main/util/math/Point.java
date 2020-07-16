package main.util.math;

public class Point {
	public double x, y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point add(Vector v) {
		return new Point(x+v.xComp, y+v.yComp);
	}
	
	public Vector minus(Point p) {
		return new Vector(x-p.x, y-p.y, true);

	}
	
	public String toString() {
		return "x: " + x + " y: " +y;
	}
	
	public Point clone() {
		return new Point(x, y);
	}
}
