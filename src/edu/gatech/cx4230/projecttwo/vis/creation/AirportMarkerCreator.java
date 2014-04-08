package edu.gatech.cx4230.projecttwo.vis.creation;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.vis.markers.AirportMarker;

public class AirportMarkerCreator {
	private List<Marker> airportMarkers;
	
	public AirportMarkerCreator(List<Airport> airports) {
		airportMarkers = new ArrayList<Marker>();
		
		for(Airport a: airports) {
			Location loc = new Location(a.getLatitude(), a.getLongitude());
			airportMarkers.add(new AirportMarker(loc, a.getIcaoCode()));
		}
	}

	/**
	 * @return the airportMarkers
	 */
	public List<Marker> getAirportMarkers() {
		return airportMarkers;
	}

}
