package Simulation;

/**
 * The class containing constants relevant to the simulation
 */
public class Constants {
	
	/** Simulation Specifications **/
	
	public static final double updateRate = 50.0;
	public static final double speed = 1.0;
	public static final int simulationTrials = 10000;
	public static final boolean randomColor = false;
	public static final boolean jolts = true;
	
	
	/** World Specifications **/
	
	public static final double gravity = 5000.0;
	public static final double noiseFactor = 0.0;
	public static final double maximumNoiseForce = 0.0;
	public static final double pushForce = 20000.0;
	
	
	/** Pendulum Specifications **/
	
	public static final double ballMass = 1.0;
	public static final double baseMass = 1.0;
	
	public static final double armLength = 300;
	
	public static final double railWidth = 1000.0;
	
	
	/** Graphics Constants **/
	
	public static final int screenWidth = 1000;
	public static final int screenHeight = 500;
	
	public static final int baseWidth = 100;
	public static final int baseHeight = 50;
	
	public static final int massWidth = 50;
}
