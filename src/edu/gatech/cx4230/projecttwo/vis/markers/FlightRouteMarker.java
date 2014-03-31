package edu.gatech.cx4230.projecttwo.vis.markers;

import java.util.List;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.utils.MapPosition;

public class FlightRouteMarker extends AbstractShapeMarker {
	private static final int COLOR_RED = 73;
	private static final int COLOR_GREEN = 168;
	private static final int COLOR_BLUE = 69;
	private static final int COLOR_ALPHA = 150;
	
	public FlightRouteMarker(Marker fromMark, Marker toMark) {
		addLocations(fromMark.getLocation());
		addLocations(toMark.getLocation());
	}

	@Override
	public void draw(PGraphics pg, List<MapPosition> mapPositions) {	
		MapPosition from = mapPositions.get(0);
		MapPosition to = mapPositions.get(1);
		
		pg.pushStyle();
		pg.color(COLOR_RED, COLOR_GREEN, COLOR_BLUE, COLOR_ALPHA);
		pg.line(from.x, from.y, to.x, to.y);
		pg.popStyle();
	}
	

}
