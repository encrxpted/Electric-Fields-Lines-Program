package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Application {
	static Display panel;
	static JFrame frame;
	
	static JTextField enterCharge, enterX, enterY;
	
	public static int charge;
	public static List<PointCharge> pointCharges = new ArrayList<>();

	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame= new JFrame();
                panel = new Display();
                frame.add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 800);
                frame.setVisible(true);
              
                // makes the menu
                JPanel menuPanel = new JPanel(new FlowLayout());
        	    JButton addCharge = new JButton("Add charge");
        	    enterCharge = new HintTextField("Charge");
        	    enterX = new HintTextField("X Coord");
        	    enterY = new HintTextField("Y Coord");

        	    addCharge.addActionListener(new ButtonClickListener());
        	    menuPanel.add(enterCharge);
        	    menuPanel.add(enterX);
        	    menuPanel.add(enterY);
        	    menuPanel.add(addCharge);

        	    menuPanel.setBackground(Color.DARK_GRAY);
        	    frame.add(menuPanel, BorderLayout.NORTH);
                frame.add(panel);

            }
        });

	}


}
