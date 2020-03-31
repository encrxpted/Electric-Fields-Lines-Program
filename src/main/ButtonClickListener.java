package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Listens for the "add charge" button to be clicked
 * Adds a point charge if the button is clicked
 */
public class ButtonClickListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Application.pointCharges.add(new PointCharge(Double.parseDouble(Application.enterCharge.getText()), 
				Integer.parseInt(Application.enterX.getText()), Integer.parseInt(Application.enterY.getText())));
		Application.panel.repaint();
	}
}
