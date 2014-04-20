package edu.gatech.cx4230.projecttwo.sim.objects;

public class ScheduledFlight implements Comparable<ScheduledFlight> {
	private String origin;
	private String destination;
	private String aircraftType;
	private int departureTime;

	public ScheduledFlight(String origin, String destination, String aircraftType, int departureTime) {
		this.origin = origin;
		this.destination = destination;
		this.aircraftType = aircraftType;
		this.departureTime = departureTime;
	}

	public String getOrigin(){
		return origin;
	}

	public String getDestination(){
		return destination;
	}

	public String getAircraftType(){
		return aircraftType;
	}

	public int getDepartureTime(){
		return departureTime;
	}

	@Override
	public int compareTo(ScheduledFlight sf){
		return departureTime - sf.getDepartureTime();
	}
}