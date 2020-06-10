package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Listens for the "add charge" button to be clicked
 * Adds a point charge if the button is clicked
 */
public class ButtonClickListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		int x = Integer.parseInt(Application.enterX.getText());
		int y = Integer.parseInt(Application.enterY.getText());
		boolean valid = true;
		
		for(PointCharge c : Application.pointCharges) {
			if(c.x == x && c.y == y ) {
				valid = false;  
				return;
			}
		}
	
		if(valid) {
			Application.pointCharges.add(new PointCharge(Integer.parseInt(Application.enterCharge.getText()),x,y));
			Application.panel.repaint();
		}
	}
}
