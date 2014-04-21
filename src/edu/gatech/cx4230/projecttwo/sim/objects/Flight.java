package edu.gatech.cx4230.projecttwo.sim.objects;

public class Flight {

	private final Aircraft aircraft;
	private final Airport origin;
	private final Airport destination;
	private final double distance;
	private final int timeOfDeparture;
	private final int estimatedTimeArrival;
	private int actualTimeArrival;
	private final int flightNumber;
	private static int flightCount = 0;
	
	public Flight(Aircraft aircraft, Airport origin, Airport destination,
			 double distance, int timeOfDeparture, int estimatedTimeArrival) {
		this.aircraft = aircraft;
		this.origin = origin;
		this.destination = destination;
		this.timeOfDeparture = timeOfDeparture;
		this.estimatedTimeArrival = estimatedTimeArrival;
		this.distance = distance;
		this.flightNumber = flightCount;
		flightCount++;
	}

	/**
	 * @return the flightNumber
	 */
	public int getFlightNumber() {
		return flightNumber;
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

	/**
	 * @return the flightCount
	 */
	public static int getFlightCount() {
		return flightCount;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Flight 
				&& ((Flight) o).getFlightNumber() == this.flightNumber;
	}
	
	public String toString() {
		String out = "Flight:  " + flightNumber + " ";
		out += origin.getIcaoCode() + " to " + destination.getIcaoCode();
		out += " ATD: " + timeOfDeparture + " ETA: " + estimatedTimeArrival;
		return out;
	}
}
