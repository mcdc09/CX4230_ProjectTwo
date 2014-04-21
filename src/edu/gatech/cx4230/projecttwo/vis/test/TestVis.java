package edu.gatech.cx4230.projecttwo.vis.test;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import edu.gatech.cx4230.projecttwo.sim.creation.WorldBuilder;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.LargeAircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.utilities.GeoHelper;
import edu.gatech.cx4230.projecttwo.vis.creation.AircraftMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.creation.AirportMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.markers.AircraftMarker;

public class TestVis extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6120940007184132500L;
	private static final int WIDTH = 500, HEIGHT = 400;
	private UnfoldingMap map;
	private int time = 0;
	private AircraftMarker am;
	
	public void setup() {
		size(WIDTH, HEIGHT);
		
		map = new UnfoldingMap(this, 10,10,WIDTH-20,HEIGHT-20);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		new WorldBuilder();
		
		Airport atl = World.getAirport("KATL");
		Airport ord = World.getAirport("KORD");
		List<Airport> airports = new ArrayList<Airport>();
		airports.add(atl); airports.add(ord);
		
		AirportMarkerCreator amc = new AirportMarkerCreator(airports);
		map.addMarkers(amc.getAirportMarkers());
		
		List<Aircraft> atlAircraft = new ArrayList<Aircraft>();
		atlAircraft.add(new LargeAircraft());
		
		double dist = GeoHelper.calcDistance(atl.getLocation(), ord.getLocation());
		Flight f = new Flight(atlAircraft.get(0), atl,ord, dist, 0, 50);
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(f);
		AircraftMarkerCreator fmc = new AircraftMarkerCreator(flights, this, (int) time);
		am = fmc.getAirplaneMarkers().get(0);
		
		map.addMarkers(fmc.getMarkers());
		
	}
	
	public void draw() {
		background(240);
		map.draw();
		
		color(255,255,0);
		text("Time: " + time, 25, 25);
		time++;
		
		if(time % 10 == 0) {
			am.updateLocation(time);
		}
	}

}
