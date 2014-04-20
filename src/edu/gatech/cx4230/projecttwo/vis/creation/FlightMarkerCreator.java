package edu.gatech.cx4230.projecttwo.vis.creation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

public class FlightMarkerCreator {
	private List<Marker> flightMarkers;
	
	public FlightMarkerCreator(List<Flight> flights) {
		flightMarkers = new ArrayList<Marker>();
		
		for(Flight f: flights) {
			int fn = f.getFlightNumber();
			Airport origin = f.getOrigin();
			Airport destination = f.getDestination();
			
			Location locO = origin.getLocation();
			Location locD = destination.getLocation();
			
			SimpleLinesMarker m = new SimpleLinesMarker(locO, locD);
			
			HashMap<String,Object> properties = new HashMap<String,Object>();
			properties.put("icaoCodeA", origin.getIcaoCode());
			properties.put("icaoCodeB", destination.getIcaoCode());
			properties.put("flightNumber", fn);
			m.setProperties(properties);
			
			m.setColor(-1773557691);
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
