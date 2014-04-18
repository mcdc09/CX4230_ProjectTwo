package edu.gatech.cx4230.projecttwo.vis.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PFont;
import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.testing.DefaultScenario;

public class VisPApplet extends PApplet {
	private AirportSimulation sim;
	public static final int STEPS_FLIGHT_UPDATE = 10;
	private static final int WIDTH = 1100, HEIGHT = 550;
	private SimMap simMap;
	private Map<String, Airport> airports;
	private Map<Integer, Flight> flights;
	private int timeStep = 0;
	private String timeString = "";
	private int flightCount;
	private PFont f;
	private ControlsView cview;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5277061984985043214L;

	public void setup() {
		size(WIDTH, HEIGHT);
		
		f = createFont("Arial", 25, true);
		
		airports = new HashMap<String, Airport>();
		flights = new HashMap<Integer, Flight>();
		simMap = new SimMap(this);
		cview = new ControlsView(2*SimMap.MAP_X + SimMap.MAP_WIDTH, SimMap.MAP_Y, 350,  SimMap.MAP_HEIGHT);
		sim = new AirportSimulation(this, new DefaultScenario(true, new ArrayList<AirportEvent>()));	
	}
	
	public void draw() {
		background(175);
		
		// Draw Time and Flight Count
		fill(207, 14, 14);
		textFont(f);
		updateTime();
		checkFlightCount();
		text("Time Step: " + timeStep, 5,20);
		text(timeString, 225, 20);
		checkFlightCount();
		text("Flight Count: " + flightCount, 350, 20);
		
		updateFlightMarkers();
		simMap.draw();
		cview.draw(this);
	}
	
	public void keyPressed() {
		switch(key) {
		case ' ':
			sim.triggerThread();
			break;
		}
	}
	
	public void mouseMoved() {
		
	}
	
	public void mouseClicked() {
		simMap.mouseClicked(mouseX, mouseY);
	}
	
	private void updateTime() {
		if(sim.isTimeChanged()) {
			timeStep = sim.getTimeStep();
			timeString = SimulationThread.getRealTime(timeStep);
		}
	}
	
	/**
	 * Updates the flight markers in the visualization.
	 */
	private void updateFlightMarkers() {
		if(timeStep % STEPS_FLIGHT_UPDATE == 0) {
			sendFlights(sim.getFlights());
		}
	}
	
	private void checkFlightCount() {
		if(sim.isFlightCountChanged()) {
			flightCount = sim.getFlightCount();
		}
	}
	
	public void updateDisplayInfo(String airportCode) {
		cview.updateForAirport(airports.get(airportCode));
	}
	
	public void updateDisplayInfo(int flightNumber) {
		cview.updateForFlight(flights.get(flightNumber));
	}
	
	public void sendAirports(Collection<Airport> airportsC) {
		airports = new HashMap<String, Airport>();
		for(Airport a: airportsC) {
			airports.put(a.getIcaoCode(), a);
		}
		simMap.createAirportMarkers(new ArrayList<Airport>(airportsC));
	}
	
	public void sendFlights(List<Flight> flightsL) {
		flights = new HashMap<Integer, Flight>();
		for(Flight flight: flightsL) {
			flights.put(flight.getFlightNumber(), flight);
		}
		simMap.createAirplaneAndFlightMarkers(flightsL);
	}

	/**
	 * @return the timeStep
	 */
	public int getTimeStep() {
		return timeStep;
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
}
