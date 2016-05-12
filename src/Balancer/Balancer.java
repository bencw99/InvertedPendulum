package Balancer;
import Simulation.*;

public class Balancer {
	public static boolean visual = true;
	public static Pendulum pendulum;
	
	public static void updatePendulum() {
		
		double proportional = pendulum.getAngle() - Math.PI/2;
		double derivative = pendulum.getVelocity();
		
		double P = 100.0;
		double D = 1.0;
		
		double railProportional = -pendulum.getBase().getPosition();
		double railDerivative = -pendulum.getBase().getVelocity();
		
		double railP = 0.01;
		double railD = 0.0;
		
		pendulum.push(P*proportional + D*derivative + railP*railProportional + railD*railDerivative);
	}
}
