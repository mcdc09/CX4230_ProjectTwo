package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;

/**
 * An abstract scenario for the simulation.
 * @author tbowling3
 *
 */
public abstract class SimulationScenario {
	protected boolean useVis;
	protected int totalNumAircraft;
	protected List<AirportEvent> events;
	protected int trialCount;
	protected String name;
	
	public SimulationScenario(boolean vis, List<AirportEvent> failures, int totalAircraft) {
		this(vis, failures, totalAircraft, "default");
	}
	
	public SimulationScenario(boolean vis, List<AirportEvent> failures, int totalAircraft, String name) {
		this.useVis = vis;
		if(failures == null) {
			failures = new ArrayList<AirportEvent>();
		}
		this.events = failures;
		this.totalNumAircraft = totalAircraft;
		this.name = name;
	}
	
	/**
	 * Uses the current state of the simulation to determine if the simulation
	 * should be ended.
	 * @param sim
	 * @return
	 */
	public abstract boolean continueSimulation(AirportSimulation sim);
	
	/**
	 * Uses the simulation to perform post-processing on the simulation results (if chosen)
	 * @param sim
	 */
	public abstract void quitSimulation(AirportSimulation sim);

	
	// Getters and Setters
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

	/**
	 * @return the totalNumAircraft
	 */
	public int getTotalNumAircraft() {
		return totalNumAircraft;
	}

	/**
	 * @param totalNumAircraft the totalNumAircraft to set
	 */
	public void setTotalNumAircraft(int totalNumAircraft) {
		this.totalNumAircraft = totalNumAircraft;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
