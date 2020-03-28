package main;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Application {
	static Display panel;
	
	static TextField enterCharge, enterX, enterY;
	
	public static int charge;
	public static List<PointCharge> pointCharges = new ArrayList<>();

	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                panel = new Display();
                frame.add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);
                frame.setVisible(true);
                
                // makes the menu
                Panel menuPanel = new Panel(new FlowLayout());
        	    Button addCharge = new Button("Add charge");
        	    enterCharge = new TextField();
        	    enterX = new TextField();
        	    enterY = new TextField();

        	    menuPanel.add(addCharge);
        	    addCharge.addActionListener(new ButtonClickListener());
        	    menuPanel.add(enterCharge);
        	    menuPanel.add(enterX);
        	    menuPanel.add(enterY);
        	    panel.add(menuPanel);
        	    
            }
        });

	}


}
