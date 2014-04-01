package edu.gatech.cx4230.projecttwo.sim.objects;

import java.util.ArrayList;
import java.util.List;

public class World {

	private ArrayList<Airport> airports;
	private int currentSimTime;
	
	public World() {
		airports = new ArrayList<Airport>();
		currentSimTime = 0;
	}
	
	public void addAirport(Airport airport) {
		airports.add(airport);
	}
	
	public ArrayList<Airport> getAirports() {
		return airports;
	}
	
	public void setAirports(ArrayList<Airport> airports) {
		this.airports = airports;
	}
	
	public int getCurrentSimTime() {
		return currentSimTime;
	}
	
	public void setCurrentSimTime(int time) {
		currentSimTime = time;
	}
}
