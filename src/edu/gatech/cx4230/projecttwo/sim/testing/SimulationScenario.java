package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;

public abstract class SimulationScenario {
	private boolean useVis;
	private List<AirportEvent> events;
	
	public SimulationScenario(boolean vis, List<AirportEvent> failures) {
		this.useVis = vis;
		if(failures == null) {
			failures = new ArrayList<AirportEvent>();
		}
		this.events = failures;
	}
	
	/**
	 * Uses the current state of the simulation to determine if the simulation
	 * should be ended.
	 * @param sim
	 * @return
	 */
	public abstract boolean continueSimulation(AirportSimulation sim);

	/**
	 * @return the useVis
	 */
	public boolean isUseVis() {
		return useVis;
	}

	/**
	 * @param useVis the useVis to set
	 */
	public void setUseVis(boolean useVis) {
		this.useVis = useVis;
	}

	/**
	 * @return the events
	 */
	public List<AirportEvent> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(List<AirportEvent> events) {
		this.events = events;
	}

}
