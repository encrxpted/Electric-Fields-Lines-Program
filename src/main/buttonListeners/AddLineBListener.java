package main.buttonListeners;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Application;
import main.components.ElectricFieldLine;
import main.components.PointCharge;

public class AddLineBListener implements ActionListener {
	PointCharge c;

	public AddLineBListener(PointCharge c) {
		this.c = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Dialog d = new Dialog(Application.frame, "Add lines");
		d.setLayout(new FlowLayout());
	    Button b = new Button("Add line");
	    Button b2 = new Button("Cancel");
	    TextField tf = new TextField();
	    tf.setColumns(30);

    	// Draw the line when the add line button is clicked
	    b.addActionListener(e1 -> {
	    	//TODO check if angle is between 0 and 360
	    	d.setVisible(false);
	    	Application.lines.add(new ElectricFieldLine(c,
	    			2*Math.PI - (Double.parseDouble(tf.getText()) * 2*Math.PI/360)));
	    	Application.panel.repaint();
	    });
	    
	    // Close the dialog if the cancel button is clicked
	    b2.addActionListener(e1 -> {
	    	d.setVisible(false);
	    });

	    d.add(new Label("Enter the initial angle of the next line (in degrees):"));
	    d.add(tf);
	    d.add(b);
	    d.add(b2);
	    d.setSize(300,200);    
	    d.setVisible(true);
		
	}

}
