package edu.gatech.cx4230.projecttwo.vis.map;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.utils.MapUtils;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.vis.creation.AirportMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.creation.FlightMarkerCreator;

public class SimMap {
	private UnfoldingMap map;
	private MarkerManager<Marker> manager;
	public static final int MAP_X = 25, MAP_Y = 25, MAP_WIDTH = 600, MAP_HEIGHT = 500;

	public SimMap(VisPApplet vis) {
		map = new UnfoldingMap(vis, MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);
		manager = map.getDefaultMarkerManager();

		List<Airport> airports = getAirportList(); 
		List<Flight> flights = getFlightList();

		AirportMarkerCreator amc = new AirportMarkerCreator(airports);
		map.addMarkers(amc.getAirportMarkers());

		FlightMarkerCreator fmc = new FlightMarkerCreator(flights);
		map.addMarkers(fmc.getFlightMarkers());


		MapUtils.createDefaultEventDispatcher(vis, map);
	}
	
	private List<Airport> getAirportList() {
		List<Airport> out = new ArrayList<Airport>();
		
		// TODO
		
		return out;
	}
	
	private List<Flight> getFlightList() {
		List<Flight> out = new ArrayList<Flight>();
		
		 // TODO
		
		return out;
	}

	public void draw() {
		map.draw();
	}

}
