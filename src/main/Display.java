package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import main.buttonListeners.ClickChargeListener;
import main.components.PointCharge;

/*
 * This class represents the display of the electric field diagram
 */
public class Display extends JPanel implements Constants {

	private static final long serialVersionUID = 1L;
	Graphics2D g2;
	
	public Display() {
		// When a point charge on the display is clicked, open a menu that allows the user to add a field line
		addMouseListener(new ClickChargeListener());
	}

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
			ElectricFieldCalc.drawListedLines(g2);
		
		// redraw point charges so there's no lines "on top" of our ponit charges
		for(PointCharge c : Application.pointCharges) {
			c.drawCircle(g2);
		}
		
	}
	
}
