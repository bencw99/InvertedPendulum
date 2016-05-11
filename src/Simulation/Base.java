package Simulation;
import java.awt.Color;
import java.awt.Graphics;

public class Base {
	private double position;
	private double velocity;
	
	private double mass;
	
	private double force;
	
	public Base(double position, double velocity, double mass) {
		this.position = position;
		this.velocity = velocity;
		this.mass = mass;
	}
	
	void update() {
		double acceleration = force/mass;
		
		position += velocity/Constants.updateRate;
		velocity += acceleration/Constants.updateRate;
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
	
	void draw(Graphics graphics) {
		int pixelPosition = (int) ((position + Constants.railWidth/2)*Constants.screenWidth/Constants.railWidth);
		int drawXPosition = pixelPosition - Constants.baseWidth/2;
		int drawYPosition = Constants.screenHeight*4/5-Constants.baseHeight/2;
		
		graphics.setColor(Color.BLACK);
		graphics.drawLine(0, Constants.screenHeight*4/5, Constants.screenWidth, Constants.screenHeight*4/5);
		graphics.drawLine(0, Constants.screenHeight*4/5 + 1, Constants.screenWidth, Constants.screenHeight*4/5 + 1);
		graphics.drawLine(0, Constants.screenHeight*4/5 - 1, Constants.screenWidth, Constants.screenHeight*4/5 - 1);
		
		graphics.setColor(Color.GRAY);
		graphics.fillRect(drawXPosition, drawYPosition, Constants.baseWidth, Constants.baseHeight);
	}
}
