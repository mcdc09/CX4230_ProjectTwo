package edu.gatech.cx4230.projecttwo.sim.main;

import edu.gatech.cx4230.projecttwo.vis.map.VisPApplet;

public class AirportSimulation {
	private int flightCount;
	boolean timeChanged;
	private boolean flightCountChanged;
	private SimulationThread simThread;
	
	public AirportSimulation(VisPApplet vis) {
		
	}
	
	public boolean continueSimulation() {
		return true; // TODO
	}
	
	public int getTimeStep() {
		timeChanged = false;
		return simThread.getTimeStep();
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
	
	

}
