package edu.gatech.cx4230.projecttwo.vis.markers;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractMarker;

public class AirportMarker extends AbstractMarker {
	private final String icaoCode;
	
	public AirportMarker(Location location, String icaoCode) {
		super(location);
		this.icaoCode = icaoCode;
		
	}

	/**
	 * @return the icaoCode
	 */
	public String getIcaoCode() {
		return icaoCode;
	}

	@Override
	public void draw(PGraphics arg0, float arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isInside(float arg0, float arg1, float arg2, float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
