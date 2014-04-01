package edu.gatech.cx4230.projecttwo.vis.creation;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.geo.Location;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.vis.markers.FlightRouteMarker;

public class FlightMarkerCreator {
	private List<FlightRouteMarker> flightMarkers;
	
	public FlightMarkerCreator(List<Flight> flights) {
		flightMarkers = new ArrayList<FlightRouteMarker>();
		
		for(Flight f: flights) {
			Airport origin = f.getOrigin();
			Airport destination = f.getDestination();
			
			Location locO = new Location(origin.getLatitude(), origin.getLongitude());
			Location locD = new Location(destination.getLatitude(), origin.getLongitude());
			FlightRouteMarker m = new FlightRouteMarker(locO, locD, origin.getIcaoCode(), destination.getIcaoCode());
			flightMarkers.add(m);
		}
	}

	/**
	 * @return the flightMarkers
	 */
	public List<FlightRouteMarker> getFlightMarkers() {
		return flightMarkers;
	}

}