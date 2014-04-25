package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.gatech.cx4230.projecttwo.utilities.CSVWriter;
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
	private Map<String, Double> airportDepartDelaysSD;
	private Map<String, Double> airportArriveDelaysSD;
	private Map<String, Double> passengerVolumesSD;
	private Map<String, Double> flightVolumesSD;
	private double averageDepart;
	private double averageArrival;
	private double averagePassengers;
	private double averageFlights;

	public TrialResultList() {
		list = new ArrayList<TrialResult>();
		
		// Averages
		airportDepartDelays = new HashMap<String, Double>();
		airportArriveDelays = new HashMap<String, Double>();
		passengerVolumes = new HashMap<String, Double>();
		flightVolumes = new HashMap<String, Double>();
		
		// Standard Deviations
		airportDepartDelaysSD = new HashMap<String, Double>();
		airportArriveDelaysSD = new HashMap<String, Double>();
		passengerVolumesSD = new HashMap<String, Double>();
		flightVolumesSD = new HashMap<String, Double>();
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
		
		calcSDArrivalDelays();
		calcSDDepartureDelays();
		calcSDPassengers();
		calcSDFlights();
		
	}

	public void saveDataToCSV(String filename) {
		Object[][] arr = new Object[airportDepartDelays.keySet().size() + 1][];

		arr[0] = new String[]{"Airport", "Departure Delays Ave", "Departure Delays SD", "Arrival Delays Ave", "Arrival Delays SD", "Passengers Volume Ave", "Passengers Volume SD", "Flight Volume Ave", "Flight Volume SD"};

		int counter = 1;
		for(String s: airportDepartDelays.keySet()) {
			arr[counter] = new Object[9];
			arr[counter][0] = s;
			arr[counter][1] = airportDepartDelays.get(s);
			arr[counter][2] = airportDepartDelaysSD.get(s);
			arr[counter][3] = airportArriveDelays.get(s);
			arr[counter][4] = airportArriveDelaysSD.get(s);
			arr[counter][5] = passengerVolumes.get(s);
			arr[counter][6] = passengerVolumesSD.get(s);
			arr[counter][7] = flightVolumes.get(s);
			arr[counter][8] = flightVolumesSD.get(s);
			
			counter++;
		}
		CSVWriter.writeIntArrayToCSV(arr, filename);
	}

	// Calculate Averages Methods
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


	// Calculate Standard Deviations
	private void calcSDArrivalDelays() {
		List<Map<String, Double>> allDelays = new ArrayList<Map<String, Double>>();

		for(TrialResult t: list) {
			allDelays.add(t.getAirportArriveDelays());
		}
		airportArriveDelaysSD = calcSDD(allDelays);
	}

	private void calcSDDepartureDelays() {
		List<Map<String, Double>> allDelays = new ArrayList<Map<String, Double>>();

		for(TrialResult t: list) {
			allDelays.add(t.getAirportDepartDelays());
		}
		airportDepartDelaysSD = calcSDD(allDelays);
	}

	private void calcSDPassengers() {
		List<Map<String, Integer>> allDelays = new ArrayList<Map<String, Integer>>();

		for(TrialResult t: list) {
			allDelays.add(t.getPassengerVolumes());
		}
		passengerVolumesSD = calcSDI(allDelays);
	}

	private void calcSDFlights() {
		List<Map<String, Integer>> allDelays = new ArrayList<Map<String, Integer>>();

		for(TrialResult t: list) {
			allDelays.add(t.getFlightVolumes());
		}
		flightVolumesSD = calcSDI(allDelays);
	}
	
	private Map<String, Double> calcSDD(List<Map<String, Double>> in) {
		Map<String, Double> out = new HashMap<String, Double>();

		Set<String> keys = in.get(0).keySet();
		for(String icao: keys) {
			double[] vals = new double[in.size()];

			for(int i = 0; i < in.size(); i++) {
				vals[i] = in.get(i).get(icao);
			}
			double ave = StatsHelper.findStandardDeviation(vals);
			out.put(icao, ave);
		}	
		return out;
	}

	private Map<String, Double> calcSDI(List<Map<String, Integer>> in) {
		Map<String, Double> out = new HashMap<String, Double>();

		Set<String> keys = in.get(0).keySet();
		for(String icao: keys) {
			double[] vals = new double[in.size()];

			for(int i = 0; i < in.size(); i++) {
				vals[i] = in.get(i).get(icao);
			}
			double ave = StatsHelper.findStandardDeviation(vals);
			out.put(icao, ave);
		}	
		return out;
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

	/**
	 * @return the airportDepartDelaysSD
	 */
	public Map<String, Double> getAirportDepartDelaysSD() {
		return airportDepartDelaysSD;
	}

	/**
	 * @param airportDepartDelaysSD the airportDepartDelaysSD to set
	 */
	public void setAirportDepartDelaysSD(Map<String, Double> airportDepartDelaysSD) {
		this.airportDepartDelaysSD = airportDepartDelaysSD;
	}

	/**
	 * @return the airportArriveDelaysSD
	 */
	public Map<String, Double> getAirportArriveDelaysSD() {
		return airportArriveDelaysSD;
	}

	/**
	 * @param airportArriveDelaysSD the airportArriveDelaysSD to set
	 */
	public void setAirportArriveDelaysSD(Map<String, Double> airportArriveDelaysSD) {
		this.airportArriveDelaysSD = airportArriveDelaysSD;
	}

	/**
	 * @return the passengerVolumesSD
	 */
	public Map<String, Double> getPassengerVolumesSD() {
		return passengerVolumesSD;
	}

	/**
	 * @param passengerVolumesSD the passengerVolumesSD to set
	 */
	public void setPassengerVolumesSD(Map<String, Double> passengerVolumesSD) {
		this.passengerVolumesSD = passengerVolumesSD;
	}

	/**
	 * @return the flightVolumesSD
	 */
	public Map<String, Double> getFlightVolumesSD() {
		return flightVolumesSD;
	}

	/**
	 * @param flightVolumesSD the flightVolumesSD to set
	 */
	public void setFlightVolumesSD(Map<String, Double> flightVolumesSD) {
		this.flightVolumesSD = flightVolumesSD;
	}

}
