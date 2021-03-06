package Simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import Balancer.*;

public class SimulationScreen extends JPanel implements KeyListener {	
	public static Pendulum pendulum;
	public static double time;
	public static double averageTime;
	
	public static void main(String[]args) throws InterruptedException {
		JFrame frame = new JFrame();
		frame.setSize(Constants.screenWidth, Constants.screenHeight);
		frame.setTitle("Inverse Pendulum Simulation");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SimulationScreen panel = new SimulationScreen();
		frame.addKeyListener(panel);
		frame.add(panel);
		frame.setVisible(true);
		
		pendulum = new Pendulum(Math.PI/2, 0, Constants.ballMass, Constants.armLength, new Base(0, 0, Constants.baseMass));
		Balancer.pendulum = pendulum;
		
		time = 0.0;
		averageTime = 0.0;
		
		if(Balancer.visual) {
			while (pendulum.getAngle() < Math.PI && pendulum.getAngle() > 0 && pendulum.getBase().getPosition() > -Constants.railWidth/2 && pendulum.getBase().getPosition() < Constants.railWidth/2) {
				time += 1/Constants.updateRate;
				
				pendulum.update();
				
				frame.repaint();
				Thread.sleep((int) (1000/(Constants.speed*Constants.updateRate)));
				
				Balancer.updatePendulum();
			}
		}
		else {
			for(int i = 0; i < Constants.simulationTrials; i ++) {
				while (pendulum.getAngle() < Math.PI && pendulum.getAngle() > 0 && pendulum.getBase().getPosition() > -Constants.railWidth/2 && pendulum.getBase().getPosition() < Constants.railWidth/2) {
					time += 1/Constants.updateRate;
					
					pendulum.update();
					
					Balancer.updatePendulum();
				}
				
				averageTime += time;
				pendulum = new Pendulum(Math.PI/2, 0, Constants.ballMass, Constants.armLength, new Base(0, 0, Constants.baseMass));
				Balancer.pendulum = pendulum;
				
				time = 0.0;
			}
			
			averageTime /= Constants.simulationTrials;
			
			frame.repaint();
		}
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		if(Balancer.visual) {
			pendulum.draw(graphics);
			graphics.setColor(Color.BLACK);
			graphics.drawString("Time: " + ((double)(int)(time*100))/100, 50, 50);
		}
		else {
			graphics.drawString("Average Time: " + ((double)(int)(averageTime*100))/100, 50, 50);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(Constants.jolts) {
			if(code == KeyEvent.VK_1) {
				pendulum.changeVelocity(-3);
			}
			if(code == KeyEvent.VK_2) {
				pendulum.changeVelocity(-2);
			}
			if(code == KeyEvent.VK_3) {
				pendulum.changeVelocity(-1);
			}
			if(code == KeyEvent.VK_4) {
				pendulum.changeVelocity(1);
			}
			if(code == KeyEvent.VK_5) {
				pendulum.changeVelocity(2);
			}
			if(code == KeyEvent.VK_6) {
				pendulum.changeVelocity(3);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
