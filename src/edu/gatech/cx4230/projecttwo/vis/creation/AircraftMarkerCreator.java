package edu.gatech.cx4230.projecttwo.vis.creation;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.utilities.GeoHelper;
import edu.gatech.cx4230.projecttwo.vis.markers.AircraftMarker;

public class AircraftMarkerCreator {
	private List<Marker> airplaneMarkers;
	
	public AircraftMarkerCreator(List<Flight> flights, PApplet p, int timeStep) {
		airplaneMarkers = new ArrayList<Marker>();
		
		for(Flight f: flights) {
			Location l = determineAirplaneLocation(f, timeStep);
			int type = determineAirplaneType(f.getAircraft());
			int heading = (int) determineAirplaneHeading(f);
			AircraftMarker am = new AircraftMarker(f.getFlightNumber(), l, type, heading, p);
			airplaneMarkers.add(am);
		} // close for
	} // close constructor(...)
	
	public Location determineAirplaneLocation(Flight f, int timeStep) {
		double heading = determineAirplaneHeading(f);
		Location originLoc = f.getOrigin().getLocation();
		double speed = f.getAircraft().getSpeed(); // km/hr
		double dtStep = timeStep - f.getTimeOfDeparture(); // steps
		double dt = dtStep * SimulationThread.timeStep; // sec
		dt = dt / 3600; // hr
		double dist = speed * dt; // km
		
		Location out = GeoHelper.calcDest(originLoc, heading, dist);
		return out;
	}
	
	public double determineAirplaneHeading(Flight f) {
		Location originLoc = f.getOrigin().getLocation();
		Location destLoc = f.getDestination().getLocation();
		return GeoHelper.calcHeading(originLoc, destLoc);
	}
	
	public int determineAirplaneType(Aircraft a) {
		String typeS = a.getAircraftType();
		int out = -1;
		if(Aircraft.TYPE_REGIONAL.equals(typeS)) {
			out = AircraftMarker.REGIONAL;
		} else if(Aircraft.TYPE_SMALL.equals(typeS)) {
			out = AircraftMarker.SHORT_RANGE;
		} else if(Aircraft.TYPE_MEDIUM.equals(typeS)) {
			out = AircraftMarker.MEDIUM_RANGE;
		} else if(Aircraft.TYPE_LARGE.equals(typeS)) {
			out = AircraftMarker.CROSS_COUNTRY;
		}
		return out;
	}

	/**
	 * @return the airplaneMarkers
	 */
	public List<Marker> getAirplaneMarkers() {
		return airplaneMarkers;
	}

}
