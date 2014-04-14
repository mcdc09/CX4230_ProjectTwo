package edu.gatech.cx4230.projecttwo.vis.markers;

import java.util.HashMap;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;

public class AirportMarker extends SimplePointMarker {
	private final String icaoCode;
	private static final float RAD = 5;
	
	public AirportMarker(Location location, String icaoCode) {
		super(location);
		this.icaoCode = icaoCode;
		setRadius(RAD);
		setColor(-12016464);
		if(properties == null) properties = new HashMap<String, Object>();
		properties.put("icaoCode", icaoCode);
	}

	/**
	 * @return the icaoCode
	 */
	public String getIcaoCode() {
		return icaoCode;
	}

}
