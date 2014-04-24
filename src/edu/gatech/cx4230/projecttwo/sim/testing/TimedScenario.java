package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;

/**
 * A simulation scenario in which the simulation is run for a specific amount
 * of time (number of time steps).
 * 
 * @author tbowling3
 *
 */
public class TimedScenario extends SimulationScenario {
	/**
	 * The length of simulation time for the simulation to run.  After the
	 * given number of seconds has occurred in the simulation, it was stop.
	 * This shouldn't be confused with the wall time (real-world time).
	 */
	private int timeForSimulation; // seconds
	
	
	public TimedScenario(boolean vis, List<AirportEvent> failures, int totalAircraft, String name, int timeForSim) {
		super(vis, failures, totalAircraft, name);
		this.timeForSimulation = timeForSim;
	}


	@Override
	public boolean continueSimulation(AirportSimulation sim) {
		return (sim.getTimeStep() * SimulationThread.TIME_PER_STEP) < timeForSimulation;
	}

	@Override
	public void quitSimulation(AirportSimulation sim) {
		// TODO Auto-generated method stub
		
	}
	


}
