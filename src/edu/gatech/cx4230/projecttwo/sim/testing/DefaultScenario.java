package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;

/**
 * Simulation scenario object that contains no failure events
 * @author tbowling3
 *
 */
public class DefaultScenario extends SimulationScenario {
	/**
	 * The total amount of time for the simulation to run in units of seconds
	 */
	public static final int TIME_RUN = 24*60*60;

	public DefaultScenario(boolean vis, List<AirportEvent> failures, int totalAircraft) {
		super(vis, failures, totalAircraft);
	}

	@Override
	public boolean continueSimulation(AirportSimulation sim) {
		int timeS = sim.getTimeStep() * SimulationThread.TIME_PER_STEP; // seconds
		return timeS <= TIME_RUN;
	}

	@Override
	public void quitSimulation(AirportSimulation sim) {}

}
