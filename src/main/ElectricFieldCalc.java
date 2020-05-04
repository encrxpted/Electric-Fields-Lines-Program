package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ElectricFieldCalc implements Constants {
	
	private static boolean[] lines;
	private static HashMap<PointCharge, int[]> chargesToLineIDs = new HashMap<>();
	private static HashMap<Integer, PointCharge> lineIDsToCharges = new HashMap<>();
	
	public static void drawAllLines3(Graphics2D g) {
		for(PointCharge c : Application.pointCharges) {
			double numLines = Math.abs(c.charge);
			double angleIncrement = 2*Math.PI / numLines;
			
			for(int i = 0; i < numLines; i++) {
				calcAndDrawLine(c, c.thetaOfIntersection + i*angleIncrement, g) ;
			}
		}
	}
	
	/*public static void drawAllLines2(Graphics2D g) {
		int linesNeeded = 0;
		// assign IDs to each line that needs to be drawn
		for(PointCharge c : Application.pointCharges) {
			for(int i = 0; i < c.charge; i++) {
				c.lineIDs[i] = linesNeeded;
				lineIDsToCharges.put(linesNeeded, c);
				
				// make a new array and put it in the hashmap if needed
				if(!chargesToLineIDs.containsKey(c)) {
					int[] lineIDs = new int[(int) c.charge];
					lineIDs[0] = linesNeeded;
					chargesToLineIDs.put(c, lineIDs);
				}
				else { // else, just add the current line ID to the array already in the map
					int[] lineIDs = chargesToLineIDs.get(c);
					lineIDs[i] = linesNeeded;
					chargesToLineIDs.put(c, lineIDs);
				}
				
				linesNeeded++;
			}
		}
		
		lines = new boolean[linesNeeded];
		
		// for each line that needs to be drawn...
		for(boolean b : lines) {
			
		}
	}
	
	// use a dfs mechanic to draw the lines
	private static void dfsToDrawLines(int lineID, double initialAngle, Graphics2D g) {
		if(lines[lineID]) return;
		
		PointCharge intersect = calcAndDrawLine(lineIDsToCharges.get(lineID), initialAngle, g);
		lines[lineID] = true;
		
		// if the line drawn hits another point charge
		if(intersect != null) {
			int[] intersectLineIDs = chargesToLineIDs.get(intersect);
			
			int nextLineID = -1;
			// change 1 of the line IDs to true for the intersected charge
			for(int i : intersectLineIDs) {
				if(lines[i] == false) {
					lines[i] = true;
					break;
				}
			}
			
			double theta = intersect.thetaOfIntersection;
			// draw lines for the intersected point charges
			for(int i : intersectLineIDs) {
				if(lines[i] == false) {
					dfsToDrawLines(i, theta, g);
				}
			}
		
		}
		// if no other point charge is hit, we just go to the next line that needs to be drawn
	}*/
	
	// draws all electric field lines
	public static void drawAllLines(Graphics2D g) {
		for(PointCharge c : Application.pointCharges) {
			double numLines = Math.abs(c.charge);
			double angleIncrement = 2*Math.PI / numLines;
			
			for(int i = 0; i < numLines; i++) {
			calcAndDrawLine(c, i*angleIncrement, g) ;
			}
		}
	}
	
	// calculates and paints the line for one curve
	/*
	 * Returns:
	 * the intersected point charge if the line ended due to an intersection; null otherwise
	 */
	public static PointCharge calcAndDrawLine(PointCharge c, double theta1, Graphics2D g) {
		List<Point> xyPoints = new ArrayList<>(); // list of xy coords for the curve
		List<Double> thetas = new ArrayList<>();
		Vector step = new Vector(TICK, theta1);
		Point p = new Point(c.x, c.y);
		xyPoints.add(p);
		boolean end = false;
		PointCharge intersect = null;
		
		// check if there's already a line drawn at this angle for this point charge
		/*for(Double t : c.lineThetas) {
			System.out.println("line theta t: " +t);
			if(Math.abs(t - theta1) <= ANGLE_TOL) {
				System.out.println("line was skipped at " + theta1);
				return null;
			}
			System.out.println(Math.abs(t - theta1));
		}*/
		
		//c.lineThetas.add(theta1);
		
		while(xyPoints.size() < 4000 && !end) {
			
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
				if(!otherCharge.equals(c) && 
				segment.intersects(otherCharge.x - .5, otherCharge.y - .5, 1, 1)) {
					end = true;
					intersect = otherCharge;
					otherCharge.lineThetas.add(step.theta);
					otherCharge.thetaOfIntersection = step.theta;
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
		
		// if the loop was broken before it got to 200
		if(xyPoints.size() < 200) 
			drawArrowHead(xyPoints.get(xyPoints.size()/2), thetas.get(xyPoints.size()/2), g);
		
		return intersect;
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
