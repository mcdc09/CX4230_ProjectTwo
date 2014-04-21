package edu.gatech.cx4230.projecttwo.vis.creation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.marker.Marker;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.vis.markers.AircraftMarker;

public class AircraftMarkerCreator {
	private Map<Integer, AircraftMarker> amMap;
	
	public AircraftMarkerCreator(List<Flight> flights, PApplet p, int timeStep) {
		amMap = new HashMap<Integer, AircraftMarker>();
		
		for(Flight f: flights) {
			AircraftMarker am = new AircraftMarker(f, timeStep, p);
			amMap.put(f.getFlightNumber(), am);
		} // close for
	} // close constructor(...)

	/**
	 * @return the airplaneMarkers
	 */
	public List<AircraftMarker> getAirplaneMarkers() {
		return new ArrayList<AircraftMarker>(amMap.values());
	}
	
	public List<Marker> getMarkers() {
		return new ArrayList<Marker>(amMap.values());
	}
	
	public Map<Integer, AircraftMarker> getAircraftMarkerMap() {
		return amMap;
	}

}
