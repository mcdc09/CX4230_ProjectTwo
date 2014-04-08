package edu.gatech.cx4230.projecttwo.sim.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class World {
	private static Random globalRand = new Random(); // Seed as necessary
	
	private HashMap<String,Airport> airports;
	private int currentSimTime;
	
	public World() {
		airports = new HashMap<String,Airport>();
		currentSimTime = 0;
	}
	
	public Collection<Airport> getAirports() {
		return airports.values();
	}
	
	public Airport getAirport(String icaoCode) {
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
	
	public int getCurrentSimTime() {
		return currentSimTime;
	}
	
	public void setCurrentSimTime(int time) {
		currentSimTime = time;
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
	
	public static Random chance(){
		return globalRand;
	}
}
