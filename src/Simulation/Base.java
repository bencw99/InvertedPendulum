package Simulation;
import java.awt.Color;
import java.awt.Graphics;

/**
 * The class representing the base of the inverted pendulum
 */
public class Base {

	/** The lateral x-position of the base along the rail in pixels **/
	private double position;
	/** The lateral velocity of the base along the rail in pixels/second **/
	private double velocity;
	
	/** The mass of the base in arbitrary units, the same units as the pendulum mass **/
	private double mass;
	
	/** The force being applied on the base this update cycle, including from both the pendulum arm and external pushes **/
	private double force;
	
	/**
	 * Parameterized constructor, initializes all variables through setters
	 * 
	 * @param position the lateral position in pixels
	 * @param velocity the lateral velocity in pixels/second
	 * @param mass the mass of the base
	 */
	public Base(double position, double velocity, double mass) {
		this.position = position;
		this.velocity = velocity;
		this.mass = mass;
	}
	
	/**
	 * Updates the base by calculating the acceleration from force and mass then updating position and velocity
	 */
	void update() {
		double acceleration = force/mass;
		
		velocity += acceleration/Constants.updateRate;
		position += velocity/Constants.updateRate;
	}
	
	void setForce(double force) {
		this.force = force;
	}
	
	void addForce(double force) {
		this.force += force;
	}
	
	public double getPosition() {
		return position;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	/**
	 * Draws the base based off of screen and base size constants found in the constants class
	 * 
	 * @param graphics the awt graphics object to be drawn on
	 */
	void draw(Graphics graphics) {
		int pixelPosition = (int) ((position + Constants.railWidth/2)*Constants.screenWidth/Constants.railWidth);
		int drawXPosition = pixelPosition - Constants.baseWidth/2;
		int drawYPosition = Constants.screenHeight*4/5-Constants.baseHeight/2;
		
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(0, Constants.screenHeight*4/5 - 5, Constants.screenWidth, 10);
		
		graphics.setColor(Color.GRAY);
		graphics.fillRect(drawXPosition, drawYPosition, Constants.baseWidth, Constants.baseHeight);
	}
}
