package edu.gatech.cx4230.projecttwo.vis.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.vis.creation.AircraftMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.creation.AirportMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.markers.AircraftMarker;

/**
 * Map that shows the aircraft and airports of the visualization
 * @author tbowling3
 *
 */
public class SimMap {
	private UnfoldingMap map;
	private VisPApplet vis;
	public static final int MAP_X = 0, MAP_Y = 0, MAP_WIDTH = 650, MAP_HEIGHT = 500;
	private static final Location initialLoc = new Location(39.861667, -107);
	private MarkerManager<Marker> airportManager;
	private MarkerManager<Marker> aircraftManager;
	private List<Flight> flights;
	private Map<Flight, AircraftMarker> aircraftMap;
	private Marker lastHitMarker;

	public SimMap(VisPApplet vis) {
		this.vis = vis;
		map = new UnfoldingMap(vis, MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);
		MapUtils.createDefaultEventDispatcher(vis, map);
		map.zoomAndPanTo(initialLoc, 3);
		map.setZoomRange(3, 6);

		airportManager = new MarkerManager<Marker>();
		airportManager.setMap(map);
		map.addMarkerManager(airportManager);

		aircraftManager = new MarkerManager<Marker>();
		aircraftManager.setMap(map);
		map.addMarkerManager(aircraftManager);

		flights = AirportSimulation.getFlights();
		aircraftMap = new HashMap<Flight, AircraftMarker>();
	}

	/**
	 * Creates the Airport Markers for the visualization
	 * @param airports
	 */
	public void createAirportMarkers(List<Airport> airports) {
		AirportMarkerCreator amc = new AirportMarkerCreator(airports);
		airportManager.clearMarkers();
		airportManager.addMarkers(amc.getAirportMarkers());
		map.addMarkerManager(airportManager);
	}

	/**
	 * Creates the Aircraft Markers for the visualization
	 * @param f
	 */
	public void createAircraftMarkers(List<Flight> f) {
		this.flights = f;
		AircraftMarkerCreator amc = new AircraftMarkerCreator(flights, vis, vis.getTimeStep());
		aircraftMap = amc.getAircraftMarkerMap();
		List<AircraftMarker> aircraftMarkers = amc.getAirplaneMarkers();
		aircraftManager.clearMarkers();
		for(Marker m: aircraftMarkers) {
			aircraftManager.addMarker(m);
		}
		map.addMarkerManager(aircraftManager);
	}

	/**
	 * Adds aircraft to the visualization, updates their position, and removed flights
	 * that are over
	 * @param time The current simulation time
	 */
	public void updateAircraftMarkers(int time) {
		List<Flight> removeAM = new ArrayList<Flight>();

		for(Flight f: aircraftMap.keySet()) {
			if(flights.contains(f)) { // Update time
				aircraftMap.get(f).updateLocation(time);
			} else { // Add to Remove
				removeAM.add(f);
			}
		}
		for(Flight f: removeAM) { // Remove
			AircraftMarker am = aircraftMap.remove(f);
			aircraftManager.removeMarker(am);
		}

		// Create Aircraft Marker
		synchronized(flights) {
			Iterator<Flight> it = flights.iterator();
			while(it.hasNext()) {
				Flight f = it.next();
				if(!aircraftMap.containsKey(f)) {
					AircraftMarker am = new AircraftMarker(f, time, vis);
					aircraftMap.put(f, am);
					aircraftManager.addMarker(am);
				}
			}
		}
	}

	/**
	 * Draws the map and markers
	 */
	public void draw() {
		map.draw();
	}

	/**
	 * Checks if a location is in the displayed bounds of the map
	 * @param loc
	 * @return
	 */
	public boolean isLocationOnMap(Location loc) {
		ScreenPosition pos = map.getScreenPosition(loc);
		return isInMapBounds(pos.x, pos.y);
	}

	/**
	 * Detects if a mouse click has hit a Marker
	 * @param mouseX
	 * @param mouseY
	 */
	public void mouseClicked(int mouseX, int mouseY) {
		if(isInMapBounds(mouseX, mouseY)) {
			Marker hit = map.getFirstHitMarker(mouseX, mouseY);
			boolean found = false;

			if(hit != null) {
				if(lastHitMarker != null) lastHitMarker.setSelected(false);
				hit.setSelected(true);

				// Try Airport
				Airport airport = (Airport) hit.getProperty("airport");
				if(airport != null) {
					found = true;
					vis.updateDisplayInfo(airport);
				}



				lastHitMarker = hit;
			} else {
				// Try Aircraft
				if(!found) {
					Marker hit2 = aircraftManager.getFirstHitMarker(mouseX, mouseY);
					if(hit2 != null) {
						Flight flight = (Flight) hit2.getProperty("flight");
						if(flight != null) {
							vis.updateDisplayInfo(flight);
							found = true;
						}
					} // close hit2 null
				}

				if(lastHitMarker != null) lastHitMarker.setSelected(false);
			}
		} // close if
	} // close mouseClicked(...)

	/**
	 * Checks if a screen position is in the bounds of the map area
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInMapBounds(float x, float y) {
		return ((MAP_X < x) && (x < (MAP_X+MAP_WIDTH))) && ((MAP_Y < y) && (y < (MAP_Y + MAP_HEIGHT)));
	}

	public int getFlightMarkerCount() {
		return flights.size();
	}

}
