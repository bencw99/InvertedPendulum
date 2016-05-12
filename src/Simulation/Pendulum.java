package Simulation;
import java.awt.Color;
import java.awt.Graphics;

/**
 * The class representing the entire inverted pendulum
 */
public class Pendulum {
	/** The angle of the inverted pendulum arm in radians **/
	private double angle;
	/** The angular velocity of the inverted pendulum arm in radians/second **/
	private double velocity;
	
	/** The mass of the pendulum weight in arbitrary units, the same units as the base mass **/
	private double mass;
	private double length;
	
	/** The outside push force exerted on the base this update cycle **/
	private double outsideForce;
	
	/** The base of this pendulum **/
	private Base base;
	
	/**
	 * Parameterized constructor, initializes all variables through setters
	 * 
	 * @param angle the angle in radians
	 * @param velocity the angular velocity in radians/second
	 * @param mass the mass of the weight
	 * @param length the length of the arm
	 * @param base the base of the pendulum
	 */
	public Pendulum(double angle, double velocity, double mass, double length, Base base) {
		this.angle = angle;
		this.velocity = velocity;
		this.mass = mass;
		this.length = length;
		this.base = base;
	}
	
	/**
	 * Updates the base by calculating the acceleration from force and mass then updating position and velocity
	 * Updates base as well
	 */
	void update() {
		double acceleration = -Constants.gravity * Math.cos(angle) / length - outsideForce * Math.sin(angle) / (length * mass);
		double forceOnBase = mass * Constants.gravity * Math.cos(angle) * Math.sin(angle);
		
		base.setForce(forceOnBase);
		base.addForce(outsideForce);
		base.update();
		
		velocity += acceleration / Constants.updateRate - Constants.noiseFactor * (Math.random() - 0.5) / (Constants.updateRate);
		angle += velocity / Constants.updateRate;
	}
	
	public void push(double speed) {
		setOutsideForce(Math.max(Math.min(speed * Constants.pushForce, Constants.pushForce), -Constants.pushForce));
	}
	
	private void setOutsideForce(double outsideForce) {
		this.outsideForce = outsideForce;
	}
	
	public Base getBase() {
		return base;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	/**
	 * Draws the pendulum based off of screen and base size constants found in the constants class
	 * 
	 * @param graphics the awt graphics object to be drawn on
	 */
	void draw(Graphics graphics) {
		int pixelArmBaseXPosition = (int) ((base.getPosition() + Constants.railWidth/2)*Constants.screenWidth/Constants.railWidth);
		int pixelArmBaseYPosition = Constants.screenHeight*4/5;
		
		int pixelArmTerminalXPosition = (int) (pixelArmBaseXPosition - length*Math.cos(angle));
		int pixelArmTerminalYPosition = (int) (pixelArmBaseYPosition - length*Math.sin(angle));
		
		graphics.setColor(Color.BLACK);
		graphics.drawLine(pixelArmBaseXPosition, pixelArmBaseYPosition, pixelArmTerminalXPosition, pixelArmTerminalYPosition);
		graphics.drawLine(pixelArmBaseXPosition + 1, pixelArmBaseYPosition, pixelArmTerminalXPosition + 1, pixelArmTerminalYPosition);
		graphics.drawLine(pixelArmBaseXPosition - 1, pixelArmBaseYPosition, pixelArmTerminalXPosition - 1, pixelArmTerminalYPosition);
		
		base.draw(graphics);
		
		int massXPosition = pixelArmTerminalXPosition - Constants.massWidth/2;
		int massYPosition = pixelArmTerminalYPosition - Constants.massWidth/2;
		
		graphics.setColor(Color.GRAY);
		graphics.fillOval(massXPosition, massYPosition, Constants.massWidth, Constants.massWidth);
	}
}
