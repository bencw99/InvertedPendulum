package Balancer;
import Simulation.*;

public class Balancer {
	public static boolean visual = true;
	public static Pendulum pendulum;
	
	public static void updatePendulum() {
		if(pendulum.getAngle() > Math.PI/2) {
			pendulum.push(1.0);
		}
		if(pendulum.getAngle() < Math.PI/2) {
			pendulum.push(-1.0);
		}
	}
}
