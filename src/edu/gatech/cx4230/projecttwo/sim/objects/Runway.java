package edu.gatech.cx4230.projecttwo.sim.objects;

public class Runway {

	private final int length;
	private Aircraft aircraft;
	private int timeNextAvailable;
	
	public Runway(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public boolean isRunwayFree(int currSimTime) {
		return timeNextAvailable <= currSimTime;
	}

	public void setTimeNextAvailable(int time) {
		this.timeNextAvailable = time;
	}

}
