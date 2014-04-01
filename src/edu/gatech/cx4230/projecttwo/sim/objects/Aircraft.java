package edu.gatech.cx4230.projecttwo.sim.objects;

public class Aircraft {

	private String aircraftType;
	private int passengerCapacity;
	private int speed;
	private int minRunwayLength;
	private int runwayTime;
	private int groundTime;
	
	public Aircraft() {
		
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMinRunwayLength() {
		return minRunwayLength;
	}

	public void setMinRunwayLength(int minRunwayLength) {
		this.minRunwayLength = minRunwayLength;
	}

	public int getRunwayTime() {
		return runwayTime;
	}

	public void setRunwayTime(int runwayTime) {
		this.runwayTime = runwayTime;
	}

	public int getGroundTime() {
		return groundTime;
	}

	public void setGroundTime(int groundTime) {
		this.groundTime = groundTime;
	}

	
}
