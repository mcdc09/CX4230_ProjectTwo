package edu.gatech.cx4230.projecttwo.sim.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.creation.FlightGenerator;
import edu.gatech.cx4230.projecttwo.sim.creation.WorldBuilder;
import edu.gatech.cx4230.projecttwo.sim.event.AircraftCreationEvent;
import edu.gatech.cx4230.projecttwo.sim.event.Timetable;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.sim.testing.SimulationScenario;
import edu.gatech.cx4230.projecttwo.sim.testing.TrialResult;
import edu.gatech.cx4230.projecttwo.vis.map.VisPApplet;

public class AirportSimulation {
	private World world;
	boolean timeChanged;
	private static boolean flightCountChanged;
	private SimulationThread simThread;
	public static final int THREAD_WAIT_WITH_VIS = 25;
	private SimulationScenario scenario;
	private static List<Flight> flights = Collections.synchronizedList(new ArrayList<Flight>());
	
	public AirportSimulation(VisPApplet vis, SimulationScenario scenario) {
		this.scenario = scenario;
		
		WorldBuilder wb = new WorldBuilder();
		world = wb.getWorld();
		
		
		FlightGenerator fg = new FlightGenerator();
		Timetable timetable = fg.getTimetable();
		World.setTimetable(timetable);
		
		// Load simulation scenario events
		for(Airport a : World.getAirports()){
			int cap = a.getMaxAircraftCapacity();
			int rgl = (int)(cap * World.getAircraftDistr()[0]);
			int sml = (int)(cap * World.getAircraftDistr()[1]);
			int med = (int)(cap * World.getAircraftDistr()[2]);
			int lrg = (int)(cap * World.getAircraftDistr()[3]);
			a.addPendingEvent(new AircraftCreationEvent(a, rgl, sml, med, lrg));
		}
		
		int wait = 0;
		// Handle the visualization
		if(vis != null) {
			vis.sendAirports(World.getAirports());
			vis.sendFlights(flights);
			wait = THREAD_WAIT_WITH_VIS;
		}
		
		
		simThread = new SimulationThread(this, wait, "Sim Thread");
		simThread.start();
	} // close constructor
	
	public AirportSimulation(SimulationScenario scen) {
		this(null, scen);
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
	
	public TrialResult getSimulationResults() {
		TrialResult out = null;
		
		// TODO
		
		return out;
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
		return flights.size();
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
	public void setFlightCountChanged(boolean flightCount) {
		flightCountChanged = flightCount;
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
	public static synchronized List<Flight> getFlights() {
		flightCountChanged = false;
		return flights;
	}
	
	public static void addActiveFlight(Flight f) {
//		System.out.println("Flight added: " + f);
		flights.add(f);
		flightCountChanged = true;
	}
	
	public static void removeActiveFlight(Flight f) {
//		System.out.println("Flight removed: " + f);
		flights.remove(f);
		flightCountChanged = true;
	}
	
	public boolean isRunning() {
		return simThread.isRunning();
	}
}