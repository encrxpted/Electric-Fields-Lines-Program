package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/*
 * This class represents the display of the electric field diagram
 */
public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	Graphics2D g2;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// draw point charges
		for(PointCharge c : Application.pointCharges) 
			c.drawCircle(g2);
		
		// Draw the field lines
		if(Application.pointCharges.size() > 0)
			ElectricFieldCalc.drawAllLines(g2);
		
		// redraw point charges so there's no lines "on top" of our ponit charges
		for(PointCharge c : Application.pointCharges) {
			c.drawCircle(g2);
		}
		
	}
	
}
