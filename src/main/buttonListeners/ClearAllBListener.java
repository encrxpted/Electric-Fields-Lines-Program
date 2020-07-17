package main.buttonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Application;

public class ClearAllBListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Application.pointCharges.clear();
		Application.lines.clear();
		Application.panel.repaint();
	}

}
