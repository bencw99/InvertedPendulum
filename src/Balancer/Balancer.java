package Balancer;
import Simulation.Pendulum;

public class Balancer {
	public static boolean visual = true;
	public static Pendulum pendulum;
	
	public static void updatePendulum() {
		if(pendulum.getAngle() + 0.3 * pendulum.getVelocity() > Math.PI/2) {
			pendulum.pushRight();
		}
		if(pendulum.getAngle() + 0.3 * pendulum.getVelocity() < Math.PI/2) {
			pendulum.pushLeft();
		}
	}
}
