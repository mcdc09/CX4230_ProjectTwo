package edu.gatech.cx4230.projecttwo.sim.main;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.creation.FlightGenerator;
import edu.gatech.cx4230.projecttwo.sim.creation.WorldBuilder;
import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.event.Timetable;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.sim.testing.SimulationScenario;
import edu.gatech.cx4230.projecttwo.vis.map.VisPApplet;

public class AirportSimulation {
	private int flightCount;
	private World world;
	boolean timeChanged;
	private boolean flightCountChanged;
	private SimulationThread simThread;
	private SimulationScenario scenario;
	private static List<Flight> flights;
	
	public AirportSimulation(VisPApplet vis, SimulationScenario scenario) {
		this.scenario = scenario;
		
		WorldBuilder wb = new WorldBuilder();
		world = wb.getWorld();
		
		
		FlightGenerator fg = new FlightGenerator();
		Timetable timetable = fg.getTimetable();
		World.setTimetable(timetable);
		
		// TODO create list of initial Aircrafts onTheGround for each airport
		flights = new ArrayList<Flight>();
		
		// Load simulation scenario events
		List<AirportEvent> events = scenario.getEvents();
		for(AirportEvent e: events) {
			String id = e.getAirport().getIcaoCode();
			World.getAirport(id).addPendingEvent(e);
		}
		
		// Handle the visualization
		if(vis != null) {
			vis.sendAirports(World.getAirports());
			vis.sendFlights(flights);
		}
		
		
		simThread = new SimulationThread(this, 10, "Sim Thread");
		simThread.start();
	}
	
	public void processEventsForTimeStep(int timeStep) {
		world.processTimeStep(timeStep);
	}
	
	public boolean continueSimulation() {
		return scenario.continueSimulation(this);
	}
	
	public void quitSimulation() {
		// TODO handle closing of the simulation
	}
	
	public void triggerThread() {
		simThread.triggerRunning();
	}
	
	
	// Properties
	
	public int getTimeStep() {
		timeChanged = false;
		return SimulationThread.getCurrTimeStep();
	}

	/**
	 * @return the timeChanged
	 */
	public boolean isTimeChanged() {
		return timeChanged;
	}

	/**
	 * @param timeChanged the timeChanged to set
	 */
	public void setTimeChanged(boolean timeChanged) {
		this.timeChanged = timeChanged;
	}

	/**
	 * @return the flightCount
	 */
	public int getFlightCount() {
		flightCountChanged = false;
		return flightCount;
	}

	/**
	 * @param flightCount the flightCount to set
	 */
	public void setFlightCount(int flightCount) {
		this.flightCount = flightCount;
	}

	/**
	 * @return the flightCountChanged
	 */
	public boolean isFlightCountChanged() {
		return flightCountChanged;
	}

	/**
	 * @param flightCountChanged the flightCountChanged to set
	 */
	public void setFlightCountChanged(boolean flightCountChanged) {
		this.flightCountChanged = flightCountChanged;
	}

	/**
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @return the simThread
	 */
	public SimulationThread getSimThread() {
		return simThread;
	}

	/**
	 * @return the scenario
	 */
	public SimulationScenario getScenario() {
		return scenario;
	}

	/**
	 * @return the flights
	 */
	public List<Flight> getFlights() {
		return flights;
	}
	
	public static void addActiveFlight(Flight f) {
		flights.add(f);
	}
	
	public static void removeActiveFlight(Flight f) {
		flights.remove(f);
	}

}
