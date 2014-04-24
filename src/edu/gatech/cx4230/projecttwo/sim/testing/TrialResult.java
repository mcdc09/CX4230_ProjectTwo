package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.HashMap;
import java.util.Map;

public class TrialResult {
	private long simulationWallClockDuration;
	private Map<String, int[]> airportDepartDelaysArray;
	private Map<String, Double> airportDepartDelays;
	private Map<String, int[]> airportArriveDelaysArray;
	private Map<String, Double> airportArriveDelays;
	private Map<String, Integer> passengerVolumes;
	private Map<String, Integer> flightVolumes;
	
	public TrialResult() {
		airportDepartDelaysArray = new HashMap<String, int[]>();
		airportDepartDelays = new HashMap<String, Double>();
		airportArriveDelaysArray = new HashMap<String, int[]>();
		airportArriveDelays = new HashMap<String, Double>();
		passengerVolumes = new HashMap<String, Integer>();
		flightVolumes = new HashMap<String, Integer>();
	}
	
	public void addDepartDelays(String icao, int[] delays) {
		airportDepartDelaysArray.put(icao, delays);
	}
	
	public void addDepartDelay(String icao, double delay) {
		airportDepartDelays.put(icao, delay);
	}
	
	public void addArrivalDelays(String icao, int[] arrives) {
		airportArriveDelaysArray.put(icao, arrives);
	}
	
	public void addArrivalDelay(String icao, double arrive) {
		airportArriveDelays.put(icao, arrive);
	}
	
	public void addPassengerVol(String icao, int pass) {
		passengerVolumes.put(icao, pass);
	}
	
	public void addFlightVol(String icao, int vol) {
		flightVolumes.put(icao, vol);
	}
	

	// Getters and Setters
	/**
	 * @return the simulationWallClockDuration
	 */
	public long getSimulationWallClockDuration() {
		return simulationWallClockDuration;
	}

	/**
	 * @param simulationWallClockDuration the simulationWallClockDuration to set
	 */
	public void setSimulationWallClockDuration(long simulationWallClockDuration) {
		this.simulationWallClockDuration = simulationWallClockDuration;
	}

	/**
	 * @return the airportDepartDelaysArray
	 */
	public Map<String, int[]> getAirportDepartDelaysArray() {
		return airportDepartDelaysArray;
	}

	/**
	 * @param airportDepartDelaysArray the airportDepartDelaysArray to set
	 */
	public void setAirportDepartDelaysArray(
			Map<String, int[]> airportDepartDelaysArray) {
		this.airportDepartDelaysArray = airportDepartDelaysArray;
	}

	/**
	 * @return the airportDepartDelays
	 */
	public Map<String, Double> getAirportDepartDelays() {
		return airportDepartDelays;
	}

	/**
	 * @param airportDepartDelays the airportDepartDelays to set
	 */
	public void setAirportDepartDelays(Map<String, Double> airportDepartDelays) {
		this.airportDepartDelays = airportDepartDelays;
	}

	/**
	 * @return the airportArriveDelaysArray
	 */
	public Map<String, int[]> getAirportArriveDelaysArray() {
		return airportArriveDelaysArray;
	}

	/**
	 * @param airportArriveDelaysArray the airportArriveDelaysArray to set
	 */
	public void setAirportArriveDelaysArray(
			Map<String, int[]> airportArriveDelaysArray) {
		this.airportArriveDelaysArray = airportArriveDelaysArray;
	}

	/**
	 * @return the airportArriveDelays
	 */
	public Map<String, Double> getAirportArriveDelays() {
		return airportArriveDelays;
	}

	/**
	 * @param airportArriveDelays the airportArriveDelays to set
	 */
	public void setAirportArriveDelays(Map<String, Double> airportArriveDelays) {
		this.airportArriveDelays = airportArriveDelays;
	}

	/**
	 * @return the passengerVolumes
	 */
	public Map<String, Integer> getPassengerVolumes() {
		return passengerVolumes;
	}

	/**
	 * @param passengerVolumes the passengerVolumes to set
	 */
	public void setPassengerVolumes(Map<String, Integer> passengerVolumes) {
		this.passengerVolumes = passengerVolumes;
	}

	/**
	 * @return the flightVolumes
	 */
	public Map<String, Integer> getFlightVolumes() {
		return flightVolumes;
	}

	/**
	 * @param flightVolumes the flightVolumes to set
	 */
	public void setFlightVolumes(Map<String, Integer> flightVolumes) {
		this.flightVolumes = flightVolumes;
	}

}
