package edu.gatech.cx4230.projecttwo.vis.markers;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.utilities.FileHelper;
import edu.gatech.cx4230.projecttwo.utilities.GeoHelper;

public class AircraftMarker extends AbstractMarker {
	public static final int REGIONAL = 76486;
	public static final int SHORT_RANGE = 12922;
	public static final int MEDIUM_RANGE = 394855;
	public static final int CROSS_COUNTRY = 912458;
	private static final String PLANE_BLUE = "drawable/air_plane_blue.png";
	private static final String PLANE_CYAN = "drawable/air_plane_cyan.png";
	private static final String PLANE_GREEN = "drawable/air_plane_green.png";
	private static final String PLANE_PURPLE = "drawable/air_plane_purple.png";
	private static PImage imgReg, imgShort, imgMed, imgCross;
	private int type;
	private int flightNumber;
	private int heading; // degrees from North
	private static final double SCALING = 0.1;
	private static int width, height;
	private Flight flight;
	private boolean delayed = false;
	
	public AircraftMarker(Flight f, int time, PApplet p) {
		this.flight = f;
		this.flightNumber = f.getFlightNumber();
		this.type = determineAirplaneType(f.getAircraft());
		this.heading = (int) determineAirplaneHeading();
		setLocation(calculateLocation(time));
		
		if(imgReg == null) {
			loadImages(p);
		}
		
		HashMap<String,Object> prop = new HashMap<String, Object>();
		prop.put("flightNumber", flight.getFlightNumber());
		prop.put("flight", f);
		setProperties(prop);
	}
	
	private Location calculateLocation(int time) {
		Location originLoc = flight.getOrigin().getLocation();
		
		double speed = flight.getAircraft().getSpeed(); // km/hr
		double dtStep = time - flight.getTimeOfDeparture(); // steps
		double dt = dtStep * SimulationThread.TIME_PER_STEP; // sec
		dt = dt / 3600; // hr
		double dist = speed * dt; // km
		
		// Prevent flights from flying past the airport if they get
		// delayed and are unable to land
		if(dist > flight.getDistance()) {
			dist = flight.getDistance();
			delayed = true;
		}
		
		Location out = GeoHelper.calcDest(originLoc, heading, dist);
		return out;
	}
	
	public void updateLocation(int currTime) {
		setLocation(calculateLocation(currTime));
	}
	
	public double determineAirplaneHeading() {
		Location originLoc = flight.getOrigin().getLocation();
		Location destLoc = flight.getDestination().getLocation();
		return GeoHelper.calcHeading(originLoc, destLoc);
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
	
	private void loadImages(PApplet p) {
		imgReg = loadImageCust(PLANE_GREEN, p);
		imgShort = loadImageCust(PLANE_CYAN,p);
		imgMed = loadImageCust(PLANE_BLUE,p);
		imgCross = loadImageCust(PLANE_PURPLE,p);
		
		width = (int) (imgCross.width * SCALING);
		height = (int) (imgCross.height * SCALING);
	}
	
	private PImage loadImageCust(String file, PApplet p) {
		String fullpath = FileHelper.getPathToResource(file);
		return p.loadImage(fullpath);
	}
	
	@Override
	public void draw(PGraphics pg, float x, float y) {
		pg.pushStyle();
		//pg.imageMode(PConstants.CORNER);
		// The image is drawn in object coordinates, i.e. the marker's origin (0,0) is at its geo-location.
		
		// Drawing Rotated Image
		//pg.translate(x, y);
		//pg.rotate((float) GeoHelper.convertDegreeToRad(heading));
		//pg.translate(-width/2, -height/2);
		//pg.image(getImageForType(type), 0, 0, width, height);
		
		if(delayed) {
			pg.color(250, 20, 20, (int) (0.75*255));
			pg.fill(10, 40);
			pg.ellipse(x-width/2, y-height/2, width, height);
		}
		
		// Draw Vertical Image
		pg.image(getImageForType(type), x - width/2, y - height/2, width, height);
		
		pg.popStyle();
	}

	@Override
	protected boolean isInside(float checkX, float checkY, float x, float y) {
		return checkX > x && checkX < x + width && checkY > y && checkY < y + height;
	}
	
	private PImage getImageForType(int in) {
		PImage out = null;
		switch(in) {
		case REGIONAL:
			out = imgReg;
			break;
		case SHORT_RANGE:
			out = imgShort;
			break;
		case MEDIUM_RANGE:
			out = imgMed;
			break;
		case CROSS_COUNTRY:
			out = imgCross;
			break;
		}
		return out;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the heading
	 */
	public int getHeading() {
		return heading;
	}

	/**
	 * @param heading the heading to set
	 */
	public void setHeading(int heading) {
		this.heading = heading;
	}

	/**
	 * @return the flightNumber
	 */
	public int getFlightNumber() {
		return flightNumber;
	}
}
