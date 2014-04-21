package edu.gatech.cx4230.projecttwo.utilities;

import de.fhpotsdam.unfolding.geo.Location;

/**
 * Provides some helpful classes for use with latitude and longitude
 * @author tbowling3
 *
 */
public class GeoHelper {
	/**
	 * Radius of the Earth in kilometers
	 */
	public static final int R = 6371; // km

	public static double calcHeading(Location origin, Location destination) {
		double lon1 = convertDegreeToRad(origin.getLon());
		double lat1 = convertDegreeToRad(origin.getLat());
		double lon2 = convertDegreeToRad(destination.getLon());
		double lat2 = convertDegreeToRad(destination.getLat());
		double dLon = lon2 - lon1;

		double y = Math.sin(dLon) * Math.cos(lat2);
		double x = Math.cos(lat1)*Math.sin(lat2) -
				Math.sin(lat1)*Math.cos(lat2)*Math.cos(dLon);
		return (convertRadToDegree(Math.atan2(y, x))+360) % 360.0;
	}

	public static double calcDistance(Location a, Location b) {
		double lat1 = a.getLat();
		double lon1 = a.getLon();
		double lat2 = b.getLat();
		double lon2 = b.getLon();

		double dLat = convertDegreeToRad(lat2-lat1);
		double dLon = convertDegreeToRad(lon2-lon1);
		double lat1R = convertDegreeToRad(lat1);
		double lat2R = convertDegreeToRad(lat2);

		double f = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1R) * Math.cos(lat2R); 
		double c = 2 * Math.atan2(Math.sqrt(f), Math.sqrt(1-f)); 
		return R * c;
	}

	public static Location calcDest(Location start, double bearing, double d) {
		double lat1 = start.getLat();
		double lon1 = start.getLon();

	    double rLat1 = convertDegreeToRad(lat1);
	    double rLon1 = convertDegreeToRad(lon1);
	    double rbearing = convertDegreeToRad(bearing);
	    double rdistance = d / R;

	    double rlat = Math.asin( Math.sin(rLat1) * Math.cos(rdistance) + Math.cos(rLat1) * Math.sin(rdistance) * Math.cos(rbearing) );
	    double rlon = rLon1 + Math.atan2(Math.sin(rbearing)*Math.sin(d/R)*Math.cos(rLat1), Math.cos(d/R)-Math.sin(rLat1)*Math.sin(rlat));

	    double lat = convertRadToDegree(rlat);
	    double lon = convertRadToDegree(rlon);
	    return new Location(lat,lon);
	}



	public static double convertDegreeToRad(double in) {
		return in * Math.PI / 180;
	}

	public static double convertRadToDegree(double in) {
		return in * 180 / Math.PI;
	}

}
