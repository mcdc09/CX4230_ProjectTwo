package edu.gatech.cx4230.projecttwo.vis.creation;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.vis.markers.FlightRouteMarker;

public class FlightMarkerCreator {
	private List<Marker> flightMarkers;
	
	public FlightMarkerCreator(List<Flight> flights) {
		flightMarkers = new ArrayList<Marker>();
		
		for(Flight f: flights) {
			int fn = f.getFlightNumber();
			Airport origin = f.getOrigin();
			Airport destination = f.getDestination();
			
			Location locO = new Location(origin.getLatitude(), origin.getLongitude());
			Location locD = new Location(destination.getLatitude(), origin.getLongitude());
			FlightRouteMarker m = new FlightRouteMarker(fn, locO, locD, origin.getIcaoCode(), destination.getIcaoCode());
			flightMarkers.add(m);
		}
	}

	/**
	 * @return the flightMarkers
	 */
	public List<Marker> getFlightMarkers() {
		return flightMarkers;
	}

}
