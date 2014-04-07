package edu.gatech.cx4230.projecttwo.database.operation;

public class AirportRunwayDataFromDB {
	private String airportID;
	private String airportName;
	private double latitude;
	private double longitude;
	private String municipality;
	private int runwayLength;
	
	public AirportRunwayDataFromDB(String a_id, String a_name, double lat, double lon, String munic, int length) {
		this.airportID = a_id;
		this.airportName = a_name;
		this.latitude = lat;
		this.longitude = lon;
		this.municipality = munic;
		this.runwayLength = length;
	}

	/**
	 * @return the airportID
	 */
	public String getAirportID() {
		return airportID;
	}

	/**
	 * @param airportID the airportID to set
	 */
	public void setAirportID(String airportID) {
		this.airportID = airportID;
	}

	/**
	 * @return the airportName
	 */
	public String getAirportName() {
		return airportName;
	}

	/**
	 * @param airportName the airportName to set
	 */
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the municipality
	 */
	public String getMunicipality() {
		return municipality;
	}

	/**
	 * @param municipality the municipality to set
	 */
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	/**
	 * @return the runwayLength
	 */
	public int getRunwayLength() {
		return runwayLength;
	}

	/**
	 * @param runwayLength the runwayLength to set
	 */
	public void setRunwayLength(int runwayLength) {
		this.runwayLength = runwayLength;
	}

}
