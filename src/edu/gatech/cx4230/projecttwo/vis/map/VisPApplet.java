package edu.gatech.cx4230.projecttwo.vis.map;

import java.util.ArrayList;
import java.util.Collection;

import processing.core.PApplet;
import processing.core.PFont;
import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.testing.DefaultScenario;

public class VisPApplet extends PApplet {
	private AirportSimulation sim;
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
		sim = new AirportSimulation(this, new DefaultScenario(true, new ArrayList<AirportEvent>()));	
	}
	
	public void draw() {
		background(175);
		simMap.draw();
		
		fill(207, 14, 14);
		textFont(f);
		updateTime();
		checkFlightCount();
		text("Time: " + timeStep, 5,20);
		checkFlightCount();
		text("Flight Count: " + flightCount, 200, 20);
	}
	
	public void mouseMoved() {
		
	}
	
	public void mouseClicked() {
		simMap.mouseClicked(mouseX, mouseY);
	}
	
	private void updateTime() {
		if(sim.isTimeChanged()) {
			timeStep = sim.getTimeStep();
		}
	}
	
	private void checkFlightCount() {
		if(sim.isFlightCountChanged()) {
			flightCount = sim.getFlightCount();
		}
	}
	
	public void updateDisplayInfo(String airportCode) {
		
	}
	
	public void updateDisplayInfo(String aircraft, String origin, String destination) {
		
	}

	/**
	 * @return the simMap
	 */
	public SimMap getSimMap() {
		return simMap;
	}

	/**
	 * @param simMap the simMap to set
	 */
	public void setSimMap(SimMap simMap) {
		this.simMap = simMap;
	}
	
	public void sendAirports(Collection<Airport> airports) {
		simMap.createAirportMarkers(new ArrayList<Airport>(airports));
	}

}
