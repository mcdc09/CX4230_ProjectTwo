package edu.gatech.cx4230.projecttwo.vis.creation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;

public class AirportMarkerCreator {
	private List<Marker> airportMarkers;
	private static final float RAD = 5;
	
	public AirportMarkerCreator(List<Airport> airports) {
		airportMarkers = new ArrayList<Marker>();
		
		for(Airport a: airports) {
			HashMap<String,Object> properties = new HashMap<String,Object>();
			SimplePointMarker m = new SimplePointMarker(a.getLocation());
			m.setRadius(RAD);
			m.setColor(-12016464);
			properties.put("icaoCode", a.getIcaoCode());
			m.setProperties(properties);
			airportMarkers.add(m);
		}
	}

	/**
	 * @return the airportMarkers
	 */
	public List<Marker> getAirportMarkers() {
		return airportMarkers;
	}

}
