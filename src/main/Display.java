package main;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/*
 * This class represents the display of the electric field diagram
 */
public class Display extends JPanel implements Constants {

	private static final long serialVersionUID = 1L;
	Graphics2D g2;
	// All the currently displayed electric field lines
	public static ArrayList<ElectricFieldLine> lines = new ArrayList<>();
	
	public Display() {
		// When a point charge on the display is clicked, open a menu that allows the user to add a field line
		addMouseListener(new MouseListener() {
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
					        
					        addLine.addActionListener(e2 -> {
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
				        	    	lines.add(new ElectricFieldLine(c,
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
					        	
					        });
					        
					        deleteCharge.addActionListener(e2-> {
								Dialog d = new Dialog(Application.frame, "Delete Point Charge");
								d.setLayout(new FlowLayout());
				        	    Button b = new Button("Yes");
				        	    Button b2 = new Button("No");
				        	    
			        	    	// Draw the line when the add line button is clicked
				        	    b.addActionListener(e1 -> {
				        	    	d.setVisible(false);
				        	    	
				        	    	List<ElectricFieldLine> linesToRemove = new ArrayList<>();
				        	    	Application.pointCharges.remove(c); // remove point charge and its lines
				        	    	for(ElectricFieldLine l : lines)
				        	    		if(l.initialCharge.equals(c)) 
				        	    			linesToRemove.add(l);
				        	    	for(ElectricFieldLine l : linesToRemove) 
				        	    		lines.remove(l);
				        	    	
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
					        });
					        
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
			
		});
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
			//c.drawCircle(g2);
		}
		
	}
	
}
