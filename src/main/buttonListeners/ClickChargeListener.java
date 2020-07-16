package main.buttonListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import main.Application;
import main.Constants;
import main.components.PointCharge;

/*
 * Represents the drop-down menu when a point charge is right clicked
 */

public class ClickChargeListener implements MouseListener, Constants {

	public ClickChargeListener() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(PointCharge c : Application.pointCharges) { 

			// if a point charge was right clicked
			if(e.getX() > c.x - CHARGE_DIAMETER/2 && e.getX() < c.x + CHARGE_DIAMETER/2 &&
						e.getY() > c.y - CHARGE_DIAMETER/2 && e.getY() < c.y+ CHARGE_DIAMETER/2) {
				if(e.getButton() >= 2) {
					JPopupMenu popupMenu = new JPopupMenu();
			        JMenuItem addLine = new JMenuItem("Add a line");
			        JMenuItem deleteCharge = new JMenuItem("Delete charge");
			        JMenuItem deleteLine = new JMenuItem("Delete a line");
			        
			        addLine.addActionListener(new AddLineBListener(c));
			        deleteCharge.addActionListener(new DeleteChargeBListener(c));
			        
			        popupMenu.add(addLine);
			        popupMenu.add(deleteCharge);
			        popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {				
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {				
	}
	
}
