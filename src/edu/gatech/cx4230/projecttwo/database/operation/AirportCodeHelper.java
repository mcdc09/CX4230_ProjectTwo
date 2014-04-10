package edu.gatech.cx4230.projecttwo.database.operation;

public class AirportCodeHelper {

	/**
	 * Returns the 3-character IATA code
	 * @param in
	 * @return
	 */
	public static String getIATAcode(String in) {
		String out = "";

		if(in.length() == 4) {
			out = in.substring(1);
		} else {
			out = in;
		}
		return out;
	}

	/**
	 * Returns the 4-character ICAO code
	 * @param in
	 * @return
	 */
	public static String getICAOcode(String in) {
		String out = "";
		
		if("HNL".equals(in)) {
			out = "PHNL";
		} else {
			out = "K" + in;
		}
		
		return out;
	}

}
