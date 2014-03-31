package edu.gatech.cx4230.projecttwo.vis.map;

import processing.core.PApplet;
import processing.core.PFont;

public class VisPApplet extends PApplet {
	private static final int WIDTH = 1000, HEIGHT = 800;
	private SimMap simMap;
	private int timeStep = 0;
	private int flightCount;
	private PFont f;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5277061984985043214L;

	public void setup() {
		size(WIDTH, HEIGHT);
		
		f = createFont("Arial", 25, true);
		
		simMap = new SimMap(this);
	}
	
	public void draw() {
		background(175);
		simMap.draw();
		
		fill(207, 14, 14);
		textFont(f);
		timeChanged();
		checkFlightCount();
		text("Time: " + timeStep, 5,20);
		checkFlightCount();
		text("Flight Count: " + flightCount, 200, 20);
	}
	
	public void mouseMoved() {
		
	}
	
	public void mouseClicked() {
		
	}
	
	private void timeChanged() {
		// TODO check to see if the simulation time has changed and update if necessary
	}
	
	private void checkFlightCount() {
		// TODO check to see if the flight count has changed and update if necessary
	}

}
