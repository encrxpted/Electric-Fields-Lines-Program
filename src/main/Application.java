package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.stage.Stage;
import main.buttonListeners.AddChargeBListener;
import main.buttonListeners.ClearAllBListener;
import main.components.ElectricFieldLine;
import main.components.PointCharge;
import main.util.gui.HintTextField;

public class Application {
	public static Display panel;
	public static JFrame frame;
	
	public static JTextField enterCharge, enterX, enterY;
	
	public static int charge;
	public static List<PointCharge> pointCharges = new ArrayList<>();
	public static ArrayList<ElectricFieldLine> lines = new ArrayList<>();

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
                frame.setTitle("Electric Fields Program");
              
                // makes the menu
                JPanel menuPanel = new JPanel(new FlowLayout());
        	    JButton addCharge = new JButton("Add charge");
        	    enterCharge = new HintTextField("Charge");
        	    enterX = new HintTextField("X Coord");
        	    enterY = new HintTextField("Y Coord");
        	    JButton clearAll = new JButton("Clear All");

        	    addCharge.addActionListener(new AddChargeBListener());
        	    clearAll.addActionListener(new ClearAllBListener());
        	    menuPanel.add(enterCharge);
        	    menuPanel.add(enterX);
        	    menuPanel.add(enterY);
        	    menuPanel.add(addCharge);
        	    menuPanel.add(clearAll);

        	    menuPanel.setBackground(Color.DARK_GRAY);
        	    frame.add(menuPanel, BorderLayout.NORTH);
                frame.add(panel);

            }
        });

	}

    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Electric Fields Program");
    }

}
