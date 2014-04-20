package edu.gatech.cx4230.projecttwo.vis.markers;

import java.util.HashMap;
import java.util.List;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.MapPosition;

public class FlightRouteMarker extends SimpleLinesMarker {
	private static final int COLOR_RED = 73;
	private static final int COLOR_GREEN = 168;
	private static final int COLOR_BLUE = 69;
	private static final int COLOR_ALPHA = 150;
	private String icaoCodeA, icaoCodeB;
	
	public FlightRouteMarker(int fn, Location a, Location b, String codeA, String codeB) {
		addLocations(a);
		addLocations(b);
		this.icaoCodeA = codeA;
		this.icaoCodeB = codeB;
		
		if(properties == null) properties = new HashMap<String, Object>();
		properties.put("icaoCodeA", icaoCodeA);
		properties.put("icaoCodeB", icaoCodeB);
		properties.put("flightNumber", fn);
	}

	@Override
	public void draw(PGraphics pg, List<MapPosition> mapPositions) {
		pg.pushStyle();
		pg.color(COLOR_RED, COLOR_GREEN, COLOR_BLUE, COLOR_ALPHA);
		super.draw(pg, mapPositions);
		pg.popStyle();
	}

	/**
	 * @return the icaoCodeA
	 */
	public String getIcaoCodeA() {
		return icaoCodeA;
	}

	/**
	 * @return the icaoCodeB
	 */
	public String getIcaoCodeB() {
		return icaoCodeB;
	}
	

}
