package edu.gatech.cx4230.projecttwo.vis.markers;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractMarker;
import edu.gatech.cx4230.projecttwo.utilities.FileHelper;

public class AirplaneMarker extends AbstractMarker {
	public static final int REGIONAL = 76486;
	public static final int SHORT_RANGE = 12922;
	public static final int MEDIUM_RANGE = 394855;
	public static final int CROSS_COUNTRY = 912458;
	private static final String PLANE_BLUE = "drawable/air_plane_blue.png";
	private static final String PLANE_CYAN = "drawable/air_plane_cyan.png";
	private static final String PLANE_GREEN = "drawable/air_plane_green.png";
	private static final String PLANE_PURPLE = "drawable/air_plane_purple.png";
	private int type;
	private int heading; // degrees from North
	private PImage img;
	private static final double SCALING = 0.1;
	private int width, height;
	
	public AirplaneMarker(Location location, int type, int heading, PApplet p) {
		super(location);
		
		this.type = type;
		String file = getFilenameForType(type);
		
		FileHelper fh = new FileHelper();
		String fullpath = fh.getPathToResource(file);
		img = p.loadImage(fullpath);
		
		width = (int) (img.width * SCALING);
		height = (int) (img.height * SCALING);
		
	}
	
	@Override
	public void draw(PGraphics pg, float x, float y) {
		pg.pushStyle();
		pg.imageMode(PConstants.CORNER);
		// The image is drawn in object coordinates, i.e. the marker's origin (0,0) is at its geo-location.
		pg.rotate((heading + 90)*(PConstants.TWO_PI/180));
		pg.translate(x - width/2, y - height / 2);
		pg.image(img, 0, 0, width, height);
		pg.popStyle();
		
	}

	@Override
	protected boolean isInside(float checkX, float checkY, float x, float y) {
		return checkX > x && checkX < x + img.width && checkY > y && checkY < y + img.height;
	}
	
	private String getFilenameForType(int in) {
		String out = null;
		switch(in) {
		case REGIONAL:
			out = PLANE_GREEN;
			break;
		case SHORT_RANGE:
			out = PLANE_CYAN;
			break;
		case MEDIUM_RANGE:
			out = PLANE_BLUE;
			break;
		case CROSS_COUNTRY:
			out = PLANE_PURPLE;
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
