package edu.gatech.cx4230.projecttwo.vis.map;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.vis.creation.AircraftMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.creation.AirportMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.creation.FlightMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.markers.AircraftMarker;

/**
 * Map that shows the aircraft and airports of the visualization
 * @author tbowling3
 *
 */
public class SimMap {
	private UnfoldingMap map;
	private VisPApplet vis;
	public static final int MAP_X = 0, MAP_Y = 0, MAP_WIDTH = 600, MAP_HEIGHT = 500;
	private static final Location initialLoc = new Location(39.861667, -107);
	private MarkerManager<Marker> flightRouteManager;
	private MarkerManager<Marker> airportManager;
	private MarkerManager<Marker> aircraftManager;
	private List<Marker> airportMarkers;
	private List<Marker> flightMarkers;
	private List<AircraftMarker> aircraftMarkers;

	public SimMap(VisPApplet vis) {
		this.vis = vis;
		map = new UnfoldingMap(vis, MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);
		MapUtils.createDefaultEventDispatcher(vis, map);
		map.zoomAndPanTo(initialLoc, 3);
		
		flightRouteManager = new MarkerManager<Marker>();
		flightRouteManager.setMap(map);
		map.addMarkerManager(flightRouteManager);
		
		airportManager = new MarkerManager<Marker>();
		airportManager.setMap(map);
		map.addMarkerManager(airportManager);

		aircraftManager = new MarkerManager<Marker>();
		aircraftManager.setMap(map);
		map.addMarkerManager(aircraftManager);

		airportMarkers = new ArrayList<Marker>();
		flightMarkers = new ArrayList<Marker>();
		aircraftMarkers = new ArrayList<AircraftMarker>();
	}

	/**
	 * Creates the Airport Markers for the visualization
	 * @param airports
	 */
	public void createAirportMarkers(List<Airport> airports) {
		AirportMarkerCreator amc = new AirportMarkerCreator(airports);
		airportMarkers = amc.getAirportMarkers();
		airportManager.clearMarkers();
		airportManager.addMarkers(airportMarkers);
	}

	/**
	 * Creates the Aircraft Markers for the visualization
	 * @param flights
	 */
	public void createAirplaneAndFlightMarkers(List<Flight> flights) {
		createFlightMarkers(flights);
		createAircraftMarkers(flights);
	}

	private void createFlightMarkers(List<Flight> flights) {
		FlightMarkerCreator fmc = new FlightMarkerCreator(flights);
		flightMarkers = fmc.getFlightMarkers();
		flightRouteManager.addMarkers(flightMarkers);
	}

	/**
	 * Creates the Aircraft Markers for the visualization
	 * @param flights
	 */
	private void createAircraftMarkers(List<Flight> flights) {
		AircraftMarkerCreator amc = new AircraftMarkerCreator(flights, vis, vis.getTimeStep());
		aircraftMarkers = amc.getAirplaneMarkers();
		aircraftManager.clearMarkers();
		for(Marker m: aircraftMarkers) {
			aircraftManager.addMarker(m);
		}
	}

	/**
	 * Draws the map and markers
	 */
	public void draw() {	
		checkMarkers();
		map.draw();
	}

	/**
	 * Checks to make sure to only draw Markers that are on the map
	 */
	private void checkMarkers() {
		for(Marker a: airportMarkers) {
			if(isLocationOnMap(a.getLocation())) {
				airportManager.addMarker(a);
			} else {
				airportManager.removeMarker(a);
			}
		}
		for(Marker f: flightMarkers) {
			SimpleLinesMarker fS = (SimpleLinesMarker) f;
			boolean on = true;
			for(Location l: fS.getLocations()) {
				if(!isLocationOnMap(l)) {
					on = false;
					break;
				}
			}
			if(on) {
				flightRouteManager.addMarker(f);
			} else {
				flightRouteManager.removeMarker(f);
			}
		}
		for(Marker p: aircraftMarkers) {
			if(isLocationOnMap(p.getLocation())) {
				aircraftManager.addMarker(p);
			} else {
				aircraftManager.removeMarker(p);
			}
		}
	}

	/**
	 * Checks if a location is in the displayed bounds of the map
	 * @param loc
	 * @return
	 */
	private boolean isLocationOnMap(Location loc) {

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

			boolean found = false;
			for(Marker a: airportMarkers) {
				if(a.isInside(map, mouseX, mouseY) && !found) {
					a.setSelected(true);
					Airport airport = (Airport) a.getProperty("airport");
					vis.updateDisplayInfo(airport);
					found = true;
				} else {
					a.setSelected(false);
				}
			}
			if(!found) {
				for(Marker m: aircraftMarkers) {
					if(m.isInside(map, mouseX, mouseY) && !found) {
						m.setSelected(true);
						Object flightObject = m.getProperty("flightNumber");
						if(flightObject != null) {
							int flightNumber = Integer.parseInt(flightObject.toString());
							vis.updateDisplayInfo(flightNumber);
							found = true;
						}
					} else {
						m.setSelected(false);
					} // close else
				} // close for
			} // close if
		} // close if
	} // close mouseClicked(...)

	/**
	 * Checks if a screen position is in the bounds of the map area
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isInMapBounds(float x, float y) {
		return ((MAP_X < x) && (x < (MAP_X+MAP_WIDTH))) && ((MAP_Y < y) && (y < (MAP_Y + MAP_HEIGHT)));
	}

}
