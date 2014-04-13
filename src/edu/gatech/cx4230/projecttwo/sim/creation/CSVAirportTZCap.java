package edu.gatech.cx4230.projecttwo.sim.creation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.gatech.cx4230.projecttwo.utilities.FileHelper;


/**
 * Reads in the airports_tz_cap.csv file and stores the data
 * @author tbowling3
 *
 */
public class CSVAirportTZCap {
	private Map<String, Integer> timezones;
	private Map<String, Integer> capacities;
	private static final String filename = "data/airports_tz_cap.csv";
	private static int ROW_LIMIT = -1;
	private static final String csvSplitBy = ",";

	public CSVAirportTZCap() {
		timezones = new HashMap<String, Integer>();
		capacities = new HashMap<String, Integer>();
		
		String filepath = FileHelper.getPathToResource(filename);

		if(FileHelper.fileExists(filepath)) {
			BufferedReader br = null;
			String line = "";
			int lineCount = 0;

			try {
				br = new BufferedReader(new FileReader(filepath));
				while ((line = br.readLine()) != null) {
					if(ROW_LIMIT == -1 || lineCount < ROW_LIMIT) {
						// use comma as separator
						String[] lineSplit = line.split(csvSplitBy);

						if(lineCount == 0) {
							// This is the header row
						} else {
							// This is the rest of the data
							handleDataRow(lineSplit);
						} // close else

					} else {
						break;
					} // close else
					lineCount++;
				} // close while

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} // close finally
		}
	} // close constructor
	
	private void handleDataRow(String[] lineSplit) {
		String airport = lineSplit[0];
		int timeZone = Integer.parseInt(lineSplit[1]);
		int capacity = Integer.parseInt(lineSplit[2]);
		
		timezones.put(airport, timeZone);
		capacities.put(airport, capacity);
	} // close handleDataRow(...)
	
	/**
	 * Gets the timezone from Eastern Time for an airport given its identification code
	 * @param airportCode
	 * @return
	 */
	public int getTimezone(String airportCode) {
		int out = -1;
		if(timezones.containsKey(airportCode)) {
			out = timezones.get(airportCode);
		}
		
		return out;
	} // close getTimezone
	
	/**
	 * Gets the capacity for an airport given its identification code
	 * @param airportCode
	 * @return
	 */
	public int getCapacity(String airportCode) {
		int out = -1;
		if(capacities.containsKey(airportCode)) {
			out = capacities.get(airportCode);
		}
		return out;
	}

} // close class
