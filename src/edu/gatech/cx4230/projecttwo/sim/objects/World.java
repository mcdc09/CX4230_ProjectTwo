package edu.gatech.cx4230.projecttwo.sim.objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.Timetable;
import edu.gatech.cx4230.projecttwo.sim.testing.AirportSimulationLoggerMaster;
import edu.gatech.cx4230.projecttwo.utilities.RandomNG;

public class World {
	private static RandomNG globalRand = new RandomNG(); // Seed as necessary
	private static HashMap<String,Airport> airports = new HashMap<String,Airport>();
	private static Timetable timetable;
	private static double[] aircraftDistr = new double[4];	
	
	public World() {
		// Sorry for deleting the airport hash map instantiation.  I would like to be able to access it from elsewhere.
		// Yep.  currentSimTime, too
	}
	
	public static Timetable getTimetable() {
		return timetable;
	}
	
	public static void setTimetable(Timetable t) {
		timetable = t;
	}
	
	public void processTimeStep(int time) {
		for(Airport a: airports.values()) {
			a.processNextEvents(time);
		}
	}
	
	public static Collection<Airport> getAirports() {
		return airports.values();
	}
	
	public static Airport getAirport(String icaoCode) {
		return airports.get(icaoCode);
	}
	
	public void addAirport(Airport airport) {
		airports.put(airport.getIcaoCode(), airport);
	}
	
	public void setAirports(List<Airport> airportList) {
		for(Airport a : airportList) {
			addAirport(a);
		}
	}
	
	public static RandomNG chance(){
		return globalRand;
	}
	
	public static void populateACDistr(int rgl, int sml, int med, int lrg){
		double total = (double)rgl + sml + med + lrg;
		aircraftDistr[0] = (double)rgl / total;
		aircraftDistr[1] = (double)sml / total;
		aircraftDistr[2] = (double)med / total;
		aircraftDistr[3] = (double)lrg / total;
		AirportSimulationLoggerMaster.logLineSim("Total flights:  " + (int)total);
		AirportSimulationLoggerMaster.logLineSim((aircraftDistr[0] * 100) + "% regional");
		AirportSimulationLoggerMaster.logLineSim((aircraftDistr[1] * 100) + "% small");
		AirportSimulationLoggerMaster.logLineSim((aircraftDistr[2] * 100) + "% medium");
		AirportSimulationLoggerMaster.logLineSim((aircraftDistr[3] * 100) + "% large");
	}
	
	public static double[] getAircraftDistr(){
		// Don't forget to typecast your final aircraft count!
		return aircraftDistr;
	}
}