package edu.gatech.cx4230.projecttwo.sim.objects;

public class Aircraft {

	private final String aircraftType;
	private final int passengerCapacity;
	private final int speed;
	private final int minRunwayLength;
	private final int runwayTime;
	private final int groundTime;
	
	public Aircraft(String aircraftType, int passengerCapacity, int speed,
			int minRunwayLength, int runwayTime, int groundTime) {
		super();
		this.aircraftType = aircraftType;
		this.passengerCapacity = passengerCapacity;
		this.speed = speed;
		this.minRunwayLength = minRunwayLength;
		this.runwayTime = runwayTime;
		this.groundTime = groundTime;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public int getSpeed() {
		return speed;
	}

	public int getMinRunwayLength() {
		return minRunwayLength;
	}

	public int getRunwayTime() {
		return runwayTime;
	}

	public int getGroundTime() {
		return groundTime;
	}
	
}
