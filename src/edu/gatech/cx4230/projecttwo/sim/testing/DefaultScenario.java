package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;

/**
 * Simulation scenario object that contains no failure events
 * @author tbowling3
 *
 */
public class DefaultScenario extends SimulationScenario {

	public DefaultScenario(boolean vis, List<AirportEvent> failures, int totalAircraft) {
		super(vis, failures, totalAircraft);
	}

	@Override
	public boolean continueSimulation(AirportSimulation sim) {
		// TODO Auto-generated method stub
		return true;
	}

}
