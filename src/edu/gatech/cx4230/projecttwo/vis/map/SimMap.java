package edu.gatech.cx4230.projecttwo.vis.map;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.vis.creation.AirportMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.creation.FlightMarkerCreator;

public class SimMap {
	private UnfoldingMap map;
	private VisPApplet vis;
	public static final int MAP_X = 25, MAP_Y = 25, MAP_WIDTH = 600, MAP_HEIGHT = 500;
	private static final Location initialLoc = new Location(39.861667, -107);
	private MarkerManager<Marker> manager;
	private List<Marker> airportMarkers;
	private List<Marker> flightMarkers;
	private List<Marker> planeMarkers;

	public SimMap(VisPApplet vis) {
		this.vis = vis;
		map = new UnfoldingMap(vis, MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);
		map.zoomAndPanTo(initialLoc, 3);
		manager = map.getDefaultMarkerManager();

		MapUtils.createDefaultEventDispatcher(vis, map);
		
		airportMarkers = new ArrayList<Marker>();
		flightMarkers = new ArrayList<Marker>();
		planeMarkers = new ArrayList<Marker>();
	}
	
	public void createAirportMarkers(List<Airport> airports) {
		AirportMarkerCreator amc = new AirportMarkerCreator(airports);
		airportMarkers = amc.getAirportMarkers();
		manager.addMarkers(airportMarkers);
	}
	
	public void createAirplaneAndFlightMarkers(List<Flight> flights) {
		createFlightMarkers(flights);
		createAircraftMarkers(flights);
	}
	
	private void createFlightMarkers(List<Flight> flights) {
		FlightMarkerCreator fmc = new FlightMarkerCreator(flights);
		flightMarkers = fmc.getFlightMarkers();
		manager.addMarkers(flightMarkers);
	}
	
	private void createAircraftMarkers(List<Flight> flights) {
		planeMarkers = new ArrayList<Marker>();
		// TODO Change
	}

	public void draw() {	
		checkMarkers();
		map.draw();
	}

	private void checkMarkers() {
		for(Marker a: airportMarkers) {
			if(isLocationOnMap(a.getLocation())) {
				manager.addMarker(a);
			} else {
				manager.removeMarker(a);
			}
		}
		for(Marker f: flightMarkers) {
			if(isLocationOnMap(f.getLocation())) {
				manager.addMarker(f);
			} else {
				manager.removeMarker(f);
			}
		}
		for(Marker p: planeMarkers) {
			if(isLocationOnMap(p.getLocation())) {
				manager.addMarker(p);
			} else {
				manager.removeMarker(p);
			}
		}
	}

	private boolean isLocationOnMap(Location loc) {

		ScreenPosition pos = map.getScreenPosition(loc);
		return isInMapBounds(pos.x, pos.y);
	}

	public void mouseClicked(int mouseX, int mouseY) {
		if(isInMapBounds(mouseX, mouseY)) {

			boolean found = false;
			for(Marker a: airportMarkers) {
				if(a.isInside(map, mouseX, mouseY) && !found) {
					a.setSelected(true);
					String code = a.getStringProperty("icaoCode");
					vis.updateDisplayInfo(code);
					found = true;
				} else {
					a.setSelected(false);
				}
			}
			if(!found) {
				for(Marker m: planeMarkers) {
					if(m.isInside(map, mouseX, mouseY) && !found) {
						m.setSelected(true);
						int flightNumber = Integer.parseInt(m.getStringProperty("flightNumber"));
						vis.updateDisplayInfo(flightNumber);
						found = true;
					} else {
						m.setSelected(false);
					} // close else
				} // close for
			} // close if
		} // close if
	} // close mouseClicked(...)

	private boolean isInMapBounds(float x, float y) {
		return ((MAP_X < x) && (x < (MAP_X+MAP_WIDTH))) && ((MAP_Y < y) && (y < (MAP_Y + MAP_HEIGHT)));
	}

}
