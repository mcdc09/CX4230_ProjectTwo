package edu.gatech.cx4230.projecttwo.sim.objects;

public class Flight {

	private final Aircraft aircraft;
	private final Airport origin;
	private final Airport destination;
	private final double distance;
	private final int timeOfDeparture;
	private final int estimatedTimeArrival;
	private int actualTimeArrival;
	
	public Flight(Aircraft aircraft, Airport origin, Airport destination,
			 double distance, int timeOfDeparture, int estimatedTimeArrival) {
		this.aircraft = aircraft;
		this.origin = origin;
		this.destination = destination;
		this.timeOfDeparture = timeOfDeparture;
		this.estimatedTimeArrival = estimatedTimeArrival;
		this.distance = distance;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public Airport getOrigin() {
		return origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public double getDistance() {
		return distance;
	}

	public int getTimeOfDeparture() {
		return timeOfDeparture;
	}

	public int getEstimatedTimeArrival() {
		return estimatedTimeArrival;
	}

	public int getActualTimeArrival() {
		return actualTimeArrival;
	}

	public void setActualTimeArrival(int actualTimeArrival) {
		this.actualTimeArrival = actualTimeArrival;
	}

}
