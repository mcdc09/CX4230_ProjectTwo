package edu.gatech.cx4230.projecttwo.database.creation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import edu.gatech.cx4230.projecttwo.utilities.FileHelper;

/**
 * Class that reads in data from the airports.csv and runways.csv files and adds data to a database file
 * @author tbowling3
 *
 */
public class AirportRunwayDBCreator {
	private String dbPath;
	private String dbAirport = "data/AirportDB.db";

	public AirportRunwayDBCreator() {

		dbPath = FileHelper.getPathToResource(dbAirport);
		System.out.println(dbPath);
		Connection conOut = null;

		try {
			Class.forName("org.sqlite.JDBC").newInstance();

			conOut = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

			Statement state = conOut.createStatement();

			if (!conOut.isClosed())  System.out.println("Successfully connected to database");
			readAirportsCSV(state);
			readRunwaysCSV(state);

		} catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {

		} // close finally
	} // close constructor

	private void readAirportsCSV(Statement state) {
		String filename = "/Users/ducttapeboro/Desktop/airports.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		int lineCount = 0;
		String insertSQL = "";

		try {

			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] lineSplit = line.split(csvSplitBy);

				if(lineCount == 0) {
					// This is the header row

				} else {
					// This is the rest of the data
					if(lineSplit.length >= 15) {
						int ID = convertInt(lineSplit[0]);
						String ident = checkString(lineSplit[1]);
						String type = checkString(lineSplit[2]);
						String name = checkString(lineSplit[3]);
						double latitude_degree = convertDouble(lineSplit[4]);
						double longitude_degree = convertDouble(lineSplit[5]);
						int elevation_ft = convertInt(lineSplit[6]);
						String continent = checkString(lineSplit[7]);
						String iso_country = checkString(lineSplit[8]);
						String iso_region = checkString(lineSplit[9]);
						String municipality = checkString(lineSplit[10]);
						String iata_code = checkString(lineSplit[13]);
						String local_code = checkString(lineSplit[14]);

						insertSQL = createAirportUpdateSQL(ID, ident, type, name, latitude_degree, longitude_degree, elevation_ft, continent, iso_country, iso_region, municipality, iata_code, local_code);
						try {
							state.execute(insertSQL);
						} catch (SQLException e) {
							e.printStackTrace();
							System.err.println(insertSQL);
						}
					}
				} // close else

				if(lineCount%100 == 0) System.out.println("Airports: line " + lineCount);
				lineCount++;
			} // close while

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.err.println(insertSQL);
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	} // close readAirportsCSV

	private String createAirportUpdateSQL(int id, String ident, String type, String name, double lat, double lon, int elev, String continent, String iso_country, String iso_region, String munic, String iata, String local) {
		String out = "INSERT INTO Airports ";
		//out += "(ID, ident, type, name, latitude_degree, longitude_degree, elevation_ft, continent, iso_country, iso_region, municipality, iata_code, local_code) ";
		out += "VALUES (" + id + ", '" + ident + "', '" + type + "', '" + name + "', " + lat + ", " + lon + ", " + elev + ", '" + continent + "', '" + iso_country + "', '" + iso_region + "', '" + munic + "', '" + iata + "', '" + local + "');";
		return out;
	}

	private void readRunwaysCSV(Statement state) {
		String filename = "/Users/ducttapeboro/Desktop/runways.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		int lineCount = 0;
		String insertSQL = "";

		try {

			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] lineSplit = line.split(csvSplitBy);

				if(lineCount == 0) {
					// This is the header row

				} else {
					// This is the rest of the data
					if(lineSplit.length >= 15) {
						int id = convertInt(lineSplit[0]);
						int a_ref = convertInt(lineSplit[1]);
						String a_id = checkString(lineSplit[2]);
						int len = convertInt(lineSplit[3]);
						int wid = convertInt(lineSplit[4]);
						String surf = checkString(lineSplit[5]);
						int light = convertInt(lineSplit[6]);
						int closed = convertInt(lineSplit[7]);
						String le = checkString(lineSplit[8]);
						String he = checkString(lineSplit[14]);

						insertSQL = createRunwayUpdateSQL(id, a_ref, a_id, len, wid, surf, light, closed, le, he);

						boolean res = state.execute(insertSQL);
						if(res) {
							System.err.println("Res returned false");
						}
					}
				} // close else

				if(lineCount%100 == 0) System.out.println("Runways: line " + lineCount);
				lineCount++;
			} // close while

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(insertSQL);
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	} // close readRunwaysCSV

	private String createRunwayUpdateSQL(int id, int a_ref, String a_id, int len, int wid, String surf, int light, int closed, String le, String he) {
		String out = "INSERT INTO Runways ";
		//out += "(ID, airport_ref, airport_ident, length_ft, width_ft, surface, lighted, closed, le_ident, he_ident) ";
		out += "VALUES (" + id + ", " + a_ref + ", '" + a_id + "', " + len + ", " + wid + ", '" + surf + "', " + light + ", " + closed + ", '" + le + "', '" + he + "');";
		return out;
	}

	private int convertInt(String in) {
		int out = 0;
		if(!in.isEmpty()) {
			out = Integer.parseInt(in);
		}
		return out;
	}

	private String checkString(String in) {
		String out = "err";
		if(!in.isEmpty()) {
			out = in;
		}
		return out;
	}

	private double convertDouble(String in) {
		double out = 0;
		if(!in.isEmpty()) {
			out = Double.parseDouble(in);
		}
		return out;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AirportRunwayDBCreator creator = new AirportRunwayDBCreator();
	}
}
