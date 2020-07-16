package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import main.components.ElectricFieldLine;
import main.components.PointCharge;
import main.util.math.Point;
import main.util.math.Vector;

public class ElectricFieldCalc implements Constants {
	
	// Draws lines specified by the user
	public static void drawListedLines(Graphics2D g) {
		for(ElectricFieldLine l : Application.lines) 
			calcAndDrawLine(l.initialCharge, l.initialAngle, g);
			
	}
		
	// calculates points for one curve
	public static void calcAndDrawLine(PointCharge c, double theta1, Graphics2D g) {
		List<Point> xyPoints = new ArrayList<>(); // list of xy coords for the curve
		List<Double> thetas = new ArrayList<>();
		Vector step = new Vector(TICK, theta1);
		Point p = new Point(c.x, c.y);
		xyPoints.add(p);
		boolean end = false;
		
		while(xyPoints.size() < 800 && !end) {
			
			Point newPoint = p.add(step);
			
			double eTheta = findEAtPos(newPoint).theta;
			thetas.add(eTheta);
			if(c.charge >= 0)
				step = new Vector(TICK, eTheta);
			else {
				if(eTheta < Math.PI && eTheta >= 0)step = new Vector(TICK, eTheta+Math.PI);
				else if(eTheta >= Math.PI) step = new Vector(TICK, eTheta-Math.PI);
			}
			
			g.setColor(Color.BLACK);
			Line2D segment = new Line2D.Double(p.x, p.y, newPoint.x, newPoint.y);
			
			for(PointCharge otherCharge : Application.pointCharges) {
				// if is intersecting another charge, break loop
				if(!otherCharge.equals(c) && p.x <= otherCharge.x + CHARGE_RADIUS && p.x >= otherCharge.x - CHARGE_RADIUS
						&& p.y <= otherCharge.y + CHARGE_RADIUS && p.y >= otherCharge.y - CHARGE_RADIUS) {
					end = true;
					break;
				}
			}
			g.draw(segment);
			
			// draw the arrow at the 100th point
			if(xyPoints.size() == 100) 
				drawArrowHead(p, eTheta, g);
			
			xyPoints.add(p);
			p = newPoint.clone();
		}
		
		// if the loop was broken before it got to 100
		if(xyPoints.size() < 100) 
			drawArrowHead(xyPoints.get(xyPoints.size()/2), thetas.get(xyPoints.size()/2), g);
		
	}
	
	// draws an arrow head using a position and angle
	public static void drawArrowHead(Point pos, double theta, Graphics2D g) {
		g.setColor(Color.BLACK);
		System.out.println(theta);
		Line2D seg1 = new Line2D.Double(pos.x, pos.y, pos.x- ARROW_HEAD_LENGTH * Math.cos(theta + ARROW_ANGLE), 
										pos.y-ARROW_HEAD_LENGTH * Math.sin(theta + ARROW_ANGLE));
		g.draw(seg1);
		Line2D seg2 = new Line2D.Double(pos.x, pos.y,  pos.x-ARROW_HEAD_LENGTH * Math.cos(theta - ARROW_ANGLE), 
				 pos.y-ARROW_HEAD_LENGTH * Math.sin(theta - ARROW_ANGLE));
		g.draw(seg2);
	}
	
	public static Vector findEAtPos(Point pos) {
		Vector sumE = new Vector(0,0);
		for(PointCharge c : Application.pointCharges) {
			sumE = sumE.add(findE(c.charge, pos.clone(), c.point)).clone();
		}

		return sumE;
	}
	
	// returns E from charge and r (in vector form)
	public static Vector findE(double charge, Vector r) {
		double theta=0;
		if(charge > 0) theta = r.theta;
		else if(charge < 0) {
			if(r.theta < Math.PI && r.theta >= 0) theta = r.theta + Math.PI;
			else if(r.theta >= Math.PI) theta = r.theta - Math.PI;
			//else System.out.println("something is big wrong");
		}				
		return new Vector(Math.abs(charge)/(r.mag*r.mag), theta);
	}
	
	public static Vector findE(double charge, Point ePos, Point chargePos) {
		Vector r = ePos.minus(chargePos);
		return findE(charge, r);
	}
}
