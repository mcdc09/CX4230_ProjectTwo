package edu.gatech.cx4230.projecttwo.vis.map;

import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.utils.MapUtils;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.vis.creation.AirportMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.creation.FlightMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.markers.AirportMarker;
import edu.gatech.cx4230.projecttwo.vis.markers.FlightRouteMarker;

public class SimMap {
	private UnfoldingMap map;
	private MarkerManager<Marker> manager;
	public static final int MAP_X = 25, MAP_Y = 25, MAP_WIDTH = 600, MAP_HEIGHT = 500;
	
	public SimMap(VisPApplet vis) {
		map = new UnfoldingMap(vis, MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);
		manager = map.getDefaultMarkerManager();
		
		List<Airport> airports = null; // TODO
		List<Flight> flights = null; // TODO
		
		AirportMarkerCreator amc = new AirportMarkerCreator(airports);
		List<AirportMarker> airportMarkers = amc.getAirportMarkers();
		
		
		FlightMarkerCreator fmc = new FlightMarkerCreator(flights);
		List<FlightRouteMarker> flightRouteMarkers  = fmc.getFlightMarkers();
		
		
		MapUtils.createDefaultEventDispatcher(vis, map);
	}
	
	public void draw() {
		map.draw();
	}

}
