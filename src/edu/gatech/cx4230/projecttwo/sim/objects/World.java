package edu.gatech.cx4230.projecttwo.sim.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class World {

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
}
