package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.event.AirportShutDownEvent;
import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;

/**
 * Simulation scenario object that shuts down a large airport like Atlanta
 * @author Joseph Mattingly
 *
 */
public class KATLShutdownScenario extends SimulationScenario {
	/**
	 * The total amount of time for the simulation to run in units of seconds
	 */
	public static final int TIME_RUN = 24*60*60;
	private static List<AirportEvent> failures = new ArrayList<AirportEvent>();
	private static boolean f = failures.add(new AirportShutDownEvent("KATL", 720, 1440));
	
	public KATLShutdownScenario(boolean vis, List<AirportEvent> failurez, int totalAircraft) {
		super(vis, failures, 100, "KATLShutdown");
	}

	@Override
	public boolean continueSimulation(AirportSimulation sim) {
		int timeS = sim.getTimeStep() * SimulationThread.TIME_PER_STEP; // seconds
		return timeS <= TIME_RUN;
	}

	@Override
	public void quitSimulation(AirportSimulation sim) {}
}