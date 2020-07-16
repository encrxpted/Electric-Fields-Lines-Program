package main.buttonListeners;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import main.Application;
import main.components.ElectricFieldLine;
import main.components.PointCharge;

public class DeleteChargeBListener implements ActionListener {
	PointCharge c;
	
	public DeleteChargeBListener(PointCharge c) {
		this.c =c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Dialog d = new Dialog(Application.frame, "Delete Point Charge");
		d.setLayout(new FlowLayout());
	    Button b = new Button("Yes");
	    Button b2 = new Button("No");
	    
    	// Draw the line when the add line button is clicked
	    b.addActionListener(e1 -> {
	    	d.setVisible(false);
	    	
	    	List<ElectricFieldLine> linesToRemove = new ArrayList<>();
	    	Application.pointCharges.remove(c); // remove point charge and its lines
	    	for(ElectricFieldLine l : Application.lines)
	    		if(l.initialCharge.equals(c)) 
	    			linesToRemove.add(l);
	    	for(ElectricFieldLine l : linesToRemove) 
	    		Application.lines.remove(l);
	    	
	    	Application.panel.repaint();
	    });
	    
	    // Close the dialog if the no button is clicked
	    b2.addActionListener(e1 -> {
	    	d.setVisible(false);
	    });
	    
	    d.add(new Label("Are you sure you want to delete this point charge?"));
	    d.add(b);
	    d.add(b2);
	    d.setSize(300,200);    
	    d.setVisible(true); 
		
	}

}
