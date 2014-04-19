package edu.gatech.cx4230.projecttwo.sim.objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.Timetable;
import edu.gatech.cx4230.projecttwo.utilities.RandomNG;

public class World {
	private static RandomNG globalRand = new RandomNG(); // Seed as necessary
	private static HashMap<String,Airport> airports = new HashMap<String,Airport>();
	private static Timetable timetable;
	
	
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
		Collection<Airport> airportList = airports.values();
		
		for(Airport a: airportList) {
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
	
	public static double calculateDistance(double lat1, double lon1, double lat2, double lon2){
		double R = 6371; // km
		double dLat = (lat2 - lat1) * Math.PI / 180.0;
		double dLon = (lon2 - lon1) * Math.PI / 180.0;
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + 
				   Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double distance = R * c;
		return distance; // km
	}
	
	public static RandomNG chance(){
		return globalRand;
	}
}
