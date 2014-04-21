package edu.gatech.cx4230.projecttwo.vis.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	public static final int STEPS_FLIGHT_UPDATE = 60 / SimulationThread.timeStep; // How often the flight markers are updated
	private static final int WIDTH = 1000, HEIGHT = 500;
	public static int NUM_AIRCRAFT_FOR_VIS = 4000; // TODO change to be intelligent
	private SimMap simMap;
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
		size(WIDTH, HEIGHT, OPENGL);
		
		f = createFont("Arial", 25, true);
		
		simMap = new SimMap(this);
		cview = new ControlsView(2*SimMap.MAP_X + SimMap.MAP_WIDTH, SimMap.MAP_Y, 350,  SimMap.MAP_HEIGHT);
		sim = new AirportSimulation(this, new DefaultScenario(true, new ArrayList<AirportEvent>(), NUM_AIRCRAFT_FOR_VIS));	
	}
	
	public void draw() {
		background(175);
		updateFlightMarkers();
		simMap.draw();
		cview.draw(this);
		
		// Draw Time and Flight Count
		fill(207, 14, 14);
		textFont(f);
		updateTime();
		text("Time Step: " + timeStep, 5,20);
		text(timeString, 225, 20);
		checkFlightCount();
		text("Flight Count: " + flightCount, 350, 20);
	}
	
	public void keyPressed() {
		switch(key) {
		case ' ':
			sim.triggerThread();
			break;
		}
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
		if(timeStep % STEPS_FLIGHT_UPDATE == 0  && sim.isRunning()) {
			simMap.updateAircraftMarkers(timeStep);
		}
	}
	
	private void checkFlightCount() {
		flightCount = simMap.getFlightMarkerCount();
	}
	
	public void updateDisplayInfo(Airport a) {
		cview.updateForAirport(a);
	}
	
	public void updateDisplayInfo(Flight flight) {
		cview.updateForFlight(flight);
	}
	
	public void sendAirports(Collection<Airport> airportsC) {
		simMap.createAirportMarkers(new ArrayList<Airport>(airportsC));
	}
	
	public void sendFlights(List<Flight> flights) {
		simMap.createAircraftMarkers(flights);
	}

	/**
	 * @return the timeStep
	 */
	public int getTimeStep() {
		return timeStep;
	}
} // close VisPApplet
