package edu.gatech.cx4230.projecttwo.vis.markers;

import processing.core.PApplet;
import processing.core.PImage;
import edu.gatech.cx4230.projecttwo.utilities.FileHelper;

public class AirplaneMarker {
	public static final int REGIONAL = 76486;
	public static final int SHORT_RANGE = 12922;
	public static final int MEDIUM_RANGE = 394855;
	public static final int CROSS_COUNTRY = 912458;
	private static final String PLANE_BLUE = "drawable/air_plane_blue.png";
	private static final String PLANE_CYAN = "drawable/air_plane_cyan.png";
	private static final String PLANE_GREEN = "drawable/air_plane_green.png";
	private static final String PLANE_PURPLE = "drawable/air_plane_purple.png";
	private int type;
	private int heading; // from North
	private double x,y;
	private PImage img;
	
	public AirplaneMarker(PApplet p, int type, double x, double y, int heading) {
		this.type = type;
		this.x = x;
		this.y = y;
		String file = getFilenameForType(type);
		
		FileHelper fh = new FileHelper();
		String fullpath = fh.getPathToResource(file);
		img = p.loadImage(fullpath);
		
	}
	
	public void draw(PApplet p) {
		
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

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

}
