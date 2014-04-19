package edu.gatech.cx4230.projecttwo.vis.creation;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.utilities.MathHelper;
import edu.gatech.cx4230.projecttwo.vis.markers.AircraftMarker;

public class AircraftMarkerCreator {
	private List<Marker> airplaneMarkers;
	
	public AircraftMarkerCreator(List<Flight> flights, PApplet p, int timeStep) {
		airplaneMarkers = new ArrayList<Marker>();
		
		for(Flight f: flights) {
			Location l = determineAirplaneLocation(f, timeStep);
			int type = determineAirplaneType(f.getAircraft());
			int heading = determineAirplaneHeading(f);
			AircraftMarker am = new AircraftMarker(f.getFlightNumber(), l, type, heading, p);
			airplaneMarkers.add(am);
		} // close for
	} // close constructor(...)
	
	public Location determineAirplaneLocation(Flight f, int timeStep) {
		Location originLoc = f.getOrigin().getLocation();
		Location destLoc = f.getDestination().getLocation();
		
		double aLat = MathHelper.linearInterp(timeStep, f.getTimeOfDeparture(), originLoc.getLat(), f.getEstimatedTimeArrival(), destLoc.getLat());
		double aLon = MathHelper.linearInterp(timeStep, f.getTimeOfDeparture(), originLoc.getLon(), f.getEstimatedTimeArrival(), destLoc.getLon());
		
		return new Location(aLat, aLon);
	}
	
	public int determineAirplaneHeading(Flight f) {
		Location originLoc = f.getOrigin().getLocation();
		Location destLoc = f.getDestination().getLocation();
		float lon1 = originLoc.getLon();
		float lat1 = originLoc.getLat();
		float lon2 = destLoc.getLon();
		float lat2 = destLoc.getLat();
		float dLon = lon2 - lon1;
		
		double y = Math.sin(dLon) * Math.cos(lat2);
		double x = Math.cos(lat1)*Math.sin(lat2) -
		        Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
		double brng = Math.atan2(y, x) * 180 / Math.PI;
		return (int) brng;
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
