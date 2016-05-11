package Simulation;
import java.awt.Color;
import java.awt.Graphics;

public class Pendulum {
	private double angle;
	private double velocity;
	
	private double mass;
	private double length;
	
	private double outsideForce;
	
	private Base base;
	
	private boolean noise;
	
	public Pendulum(double angle, double velocity, double mass, double length, Base base, boolean noise) {
		this.angle = angle;
		this.velocity = velocity;
		this.mass = mass;
		this.length = length;
		this.base = base;
		this.noise = noise;
	}
	
	void update() {
		double acceleration = -Constants.gravity*Math.cos(angle)/length - 0.002 * outsideForce*Math.sin(angle)/mass;
		double forceOnBase = mass*Constants.gravity*Math.cos(angle)*Math.sin(angle);
		
		base.setForce(forceOnBase);
		base.addForce(outsideForce);
		base.update();
		
		velocity += acceleration/Constants.updateRate - (noise ? Constants.noiseFactor * (Math.random()-0.5)/(Constants.updateRate) : 0);
		angle += velocity/Constants.updateRate;
	}
	
	public void pushRight() {
		setOutsideForce(Constants.pushForce);
	}
	
	public void pushLeft() {
		setOutsideForce(-Constants.pushForce);
	}
	
	public void stop() {
		setOutsideForce(0.0);
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
