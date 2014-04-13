package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.Event;

public class SimulationScenario {
	private boolean useVis;
	private List<Event> events;
	
	public SimulationScenario(boolean vis, List<Event> failures) {
		this.useVis = vis;
		this.events = failures;
	}

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
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
