package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.gatech.cx4230.projecttwo.utilities.StatsHelper;

/**
 * Data Structure to hold a set of trial results
 * @author tbowling3
 *
 */
public class TrialResultList {
	private List<TrialResult> list;
	private int trials;
	private long totalTime;
	private Map<String, Double> airportDepartDelays;
	private Map<String, Double> airportArriveDelays;
	private Map<String, Double> passengerVolumes;
	private Map<String, Double> flightVolumes;
	private double averageDepart;
	private double averageArrival;
	private double averagePassengers;
	private double averageFlights;

	public TrialResultList() {
		list = new ArrayList<TrialResult>();

		airportDepartDelays = new HashMap<String, Double>();
		airportArriveDelays = new HashMap<String, Double>();
		passengerVolumes = new HashMap<String, Double>();
		flightVolumes = new HashMap<String, Double>();
	}

	/**
	 * Adds the given TrialResult to the list
	 * @param rt
	 */
	public void addTrialResult(TrialResult rt) {
		if(rt != null) {
			list.add(rt);
		}
	}

	public void calcAllAcrossTrials() {
		calcAverageArrivalDelays();
		calcAverageDepartureDelays();
		calcAveragePassengers();
		calcAverageFlights();
		averageDepart = calcAverage(airportDepartDelays);
		averageArrival = calcAverage(airportArriveDelays);
		averagePassengers = calcAverage(passengerVolumes);
		averageFlights = calcAverage(flightVolumes);
		
	}

	private void calcAverageArrivalDelays() {
		List<Map<String, Double>> allDelays = new ArrayList<Map<String, Double>>();

		for(TrialResult t: list) {
			allDelays.add(t.getAirportArriveDelays());
		}
		airportArriveDelays = calcAverageD(allDelays);
	}

	private void calcAverageDepartureDelays() {
		List<Map<String, Double>> allDelays = new ArrayList<Map<String, Double>>();

		for(TrialResult t: list) {
			allDelays.add(t.getAirportDepartDelays());
		}
		airportDepartDelays = calcAverageD(allDelays);
	}

	private void calcAveragePassengers() {
		List<Map<String, Integer>> allDelays = new ArrayList<Map<String, Integer>>();

		for(TrialResult t: list) {
			allDelays.add(t.getPassengerVolumes());
		}
		passengerVolumes = calcAverageI(allDelays);
	}

	private void calcAverageFlights() {
		List<Map<String, Integer>> allDelays = new ArrayList<Map<String, Integer>>();

		for(TrialResult t: list) {
			allDelays.add(t.getFlightVolumes());
		}
		flightVolumes = calcAverageI(allDelays);
	}

	private Map<String, Double> calcAverageD(List<Map<String, Double>> in) {
		Map<String, Double> out = new HashMap<String, Double>();

		Set<String> keys = in.get(0).keySet();
		for(String icao: keys) {
			double[] vals = new double[in.size()];

			for(int i = 0; i < in.size(); i++) {
				vals[i] = in.get(i).get(icao);
			}
			double ave = StatsHelper.findAverage(vals);
			out.put(icao, ave);
		}	
		return out;
	}

	private Map<String, Double> calcAverageI(List<Map<String, Integer>> in) {
		Map<String, Double> out = new HashMap<String, Double>();

		Set<String> keys = in.get(0).keySet();
		for(String icao: keys) {
			double[] vals = new double[in.size()];

			for(int i = 0; i < in.size(); i++) {
				vals[i] = in.get(i).get(icao);
			}
			double ave = StatsHelper.findAverage(vals);
			out.put(icao, ave);
		}	
		return out;
	}

	private double calcAverage(Map<String, Double> in) {
		double[] vals = new double[in.size()];
		
		int i = 0;
		for(String s: in.keySet()) {
			vals[i] = in.get(s);
			i++;
		}
		return StatsHelper.findAverage(vals);
	}



	// Getters and Setters
	/**
	 * @return the list
	 */
	public List<TrialResult> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<TrialResult> list) {
		this.list = list;
	}

	/**
	 * @return the trials
	 */
	public int getTrials() {
		return trials;
	}

	/**
	 * @param trials the trials to set
	 */
	public void setTrials(int trials) {
		this.trials = trials;
	}

	/**
	 * @return the totalTime
	 */
	public long getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
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
	public Map<String, Double> getPassengerVolumes() {
		return passengerVolumes;
	}

	/**
	 * @param passengerVolumes the passengerVolumes to set
	 */
	public void setPassengerVolumes(Map<String, Double> passengerVolumes) {
		this.passengerVolumes = passengerVolumes;
	}

	/**
	 * @return the flightVolumes
	 */
	public Map<String, Double> getFlightVolumes() {
		return flightVolumes;
	}

	/**
	 * @param flightVolumes the flightVolumes to set
	 */
	public void setFlightVolumes(Map<String, Double> flightVolumes) {
		this.flightVolumes = flightVolumes;
	}

	/**
	 * @return the averageDepart
	 */
	public double getAverageDepart() {
		return averageDepart;
	}

	/**
	 * @return the averageArrival
	 */
	public double getAverageArrival() {
		return averageArrival;
	}

	/**
	 * @return the averagePassengers
	 */
	public double getAveragePassengers() {
		return averagePassengers;
	}

	/**
	 * @return the averageFlights
	 */
	public double getAverageFlights() {
		return averageFlights;
	}

}
