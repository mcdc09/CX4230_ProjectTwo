package edu.gatech.cx4230.projecttwo.vis.markers;

import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import edu.gatech.cx4230.projecttwo.utilities.FileHelper;

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
	private int heading; // degrees from North
	private static final double SCALING = 0.1;
	private static int width, height;
	
	public AircraftMarker(int fn, Location location, int type, int heading, PApplet p) {
		super(location);
		
		this.type = type;

		if(imgReg == null) {
			loadImages(p);
		}
		
		HashMap<String,Object> prop = new HashMap<String, Object>();
		prop.put("flightNumber", fn);
		setProperties(prop);
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
		pg.rotate((heading + 90)*(PConstants.TWO_PI/360));
		//pg.translate(x - width/2, y - height / 2);
		pg.ellipse(x-width/2, y-height/2, width, height);
		//pg.image(getImageForType(type), x - width/2, y - height / 2, width, height);
		if(selected) {
			pg.color(200, 200, 0, 255);
			pg.ellipse(x - width/2, y - height / 2, width, height);
		}
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

}
