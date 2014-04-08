package edu.gatech.cx4230.projecttwo.database.operation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.cx4230.projecttwo.utilities.ListHelper;

/**
 * Class used to interact with the AirportDB.db file.  AirportDB.db contains information read from
 * the airports.csv and runways.csv files.  The database schema of AirportDB.db can be found in the res/data folder.
 * @author tbowling3
 *
 */
public class AirportsDBOperator extends DatabaseConnectionInterface {
	private static String dbFilePath = "data/AirportDB.db";
	
	public AirportsDBOperator() {
		dbOp = new DatabaseOperator(dbFilePath);
	}
	
	public List<AirportRunwayDataFromDB> getAirportsData() {
		return getAirportsData(null);
	} // close getAirportsData
	
	public List<AirportRunwayDataFromDB> getAirportsData(String airport) {
		ResultSet results = dbOp.executeSQLQuery(getDBQuery(airport));
		List<AirportRunwayDataFromDB> data = new ArrayList<AirportRunwayDataFromDB>();
		
		try {
			while(results.next()) {
				String ident = results.getString("ident");
				String airportName = results.getString("name");
				double lat = results.getDouble("latitude_degree");
				double lon = results.getDouble("longitude_degree");
				String munic = results.getString("municipality");
				int runwayLength = results.getInt("length_ft");
				
				data.add(new AirportRunwayDataFromDB(ident, airportName, lat, lon, munic, runwayLength));
				
				
			} // close while
		} catch (SQLException e) {
			System.out.println("Error with SQL Query");
			e.printStackTrace();
		} // close catch
		
		return data;
	} // close getAirportsData(...)
	
	private String getDBQuery(String a) {
		String out = "SELECT ident, name, latitude_degree, longitude_degree, municipality, length_ft ";
		out += "FROM 'Airports' JOIN 'Runways' ON 'Airports'.id = 'Runways'.airport_ref ";
		if(a != null && !a.isEmpty()) out += "WHERE ident = '" + a + "' ";
		out += "ORDER BY ident";
		out += ";";
		
		return out;
	} // close getDBQuery(...)
	
	public static void main(String[] args) {
		AirportsDBOperator op = new AirportsDBOperator();
		List<AirportRunwayDataFromDB> list = op.getAirportsData();
		System.out.println("Size of list: " + list.size());
		
		list = op.getAirportsData("KATL");
		System.out.println("Runways of KATL: " + list.size());
		System.out.println(ListHelper.listToString(list));
	}
}
