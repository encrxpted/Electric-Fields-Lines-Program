package main;

public class ElectricFieldLine {
	double initialAngle;
	PointCharge initialCharge;
	
	public ElectricFieldLine(PointCharge c, double a) {
		initialCharge = c;
		initialAngle = a;
	}
	
	public boolean equals(ElectricFieldLine l) {
		if(initialAngle == l.initialAngle && initialCharge.equals(initialCharge)) 
			return true;
		return false;
	}
}
