package edu.gatech.cx4230.projecttwo.sim.objects;

public class Flight {

	private Aircraft aircraft;
	private Airport origin;
	private Airport destination;
	private int timeOfDeparture;
	private int estimatedTimeArrival;
	private int actualTimeArrival;
	private double distance;
	
	public Flight() {
		
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public int getTimeOfDeparture() {
		return timeOfDeparture;
	}

	public void setTimeOfDeparture(int timeOfDeparture) {
		this.timeOfDeparture = timeOfDeparture;
	}

	public int getEstimatedTimeArrival() {
		return estimatedTimeArrival;
	}

	public void setEstimatedTimeArrival(int estimatedTimeArrival) {
		this.estimatedTimeArrival = estimatedTimeArrival;
	}

	public int getActualTimeArrival() {
		return actualTimeArrival;
	}

	public void setActualTimeArrival(int actualTimeArrival) {
		this.actualTimeArrival = actualTimeArrival;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
