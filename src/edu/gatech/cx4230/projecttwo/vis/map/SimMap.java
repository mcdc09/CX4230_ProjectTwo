package edu.gatech.cx4230.projecttwo.vis.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import edu.gatech.cx4230.projecttwo.sim.creation.WorldBuilder;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.vis.creation.AirportMarkerCreator;
import edu.gatech.cx4230.projecttwo.vis.creation.FlightMarkerCreator;

public class SimMap {
	private UnfoldingMap map;
	public static final int MAP_X = 25, MAP_Y = 25, MAP_WIDTH = 600, MAP_HEIGHT = 500;
	private MarkerManager<Marker> manager;
	private List<Marker> airportMarkers;
	private List<Marker> flightMarkers;
	private List<Marker> planeMarkers;

	public SimMap(VisPApplet vis) {
		map = new UnfoldingMap(vis, MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);
		manager = map.getDefaultMarkerManager();

		AirportMarkerCreator amc = new AirportMarkerCreator(getAirportList());
		airportMarkers = amc.getAirportMarkers();
		manager.addMarkers(airportMarkers);

		FlightMarkerCreator fmc = new FlightMarkerCreator(getFlightList());
		flightMarkers = fmc.getFlightMarkers();
		manager.addMarkers(flightMarkers);

		planeMarkers = new ArrayList<Marker>(); // TODO Change

		MapUtils.createDefaultEventDispatcher(vis, map);
	}

	private List<Airport> getAirportList() {
		WorldBuilder wb = new WorldBuilder();
		Collection<Airport> outC = wb.getWorld().getAirports();
		List<Airport> out = new ArrayList<Airport>(outC);

		return out;
	}

	private List<Flight> getFlightList() {
		List<Flight> out = new ArrayList<Flight>();

		// TODO

		return out;
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
					found = true;
				} else {
					a.setSelected(false);
				}
			}
			if(!found) {
				for(Marker m: planeMarkers) {
					if(m.isInside(map, mouseX, mouseY) && !found) {
						m.setSelected(true);
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
