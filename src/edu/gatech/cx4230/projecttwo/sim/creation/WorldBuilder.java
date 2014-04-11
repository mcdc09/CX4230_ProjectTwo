package edu.gatech.cx4230.projecttwo.sim.creation;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cx4230.projecttwo.database.operation.AirportCodeHelper;
import edu.gatech.cx4230.projecttwo.database.operation.AirportRunwayDataFromDB;
import edu.gatech.cx4230.projecttwo.database.operation.AirportsDBOperator;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;
import edu.gatech.cx4230.projecttwo.sim.objects.World;

/**
 * Creates the world.  Includes Airports with needed info and Runways
 * @author tbowling3
 *
 */
public class WorldBuilder {
	private World world;

	public WorldBuilder() {
		world = new World();

		String[] airportsIncluded = findIncludedAirports();

		AirportsDBOperator airportsDB = new AirportsDBOperator();

		for(String a: airportsIncluded) {
			ArrayList<Runway> runways = new ArrayList<Runway>();
			ArrayList<Aircraft> onTheGround = new ArrayList<Aircraft>();
			List<AirportRunwayDataFromDB> airportData = airportsDB.getAirportsData(AirportCodeHelper.getICAOcode(a));

			if(airportData.size() > 0) {
				// Builds List of runways
				for(AirportRunwayDataFromDB d: airportData) {
					Runway r = new Runway(d.getRunwayLength());
					runways.add(r);
				} // close for


				AirportRunwayDataFromDB d = airportData.get(0);

				int maxAircraftCap = 10; // TODO
				float lat = (float) d.getLatitude();
				float lon = (float) d.getLongitude();
				String city = d.getMunicipality();
				String state = ""; // TODO
				String icaoCode = d.getAirportID();
				String timeZone = ""; // TODO
				
				// TODO create list of initial Aircrafts onTheGround for this airport
				
				Airport airport = new Airport(runways, onTheGround, maxAircraftCap, lat, lon, city, state, icaoCode, timeZone);
				world.addAirport(airport);
			} // close if
		} // close for
	} // close constructor


	private String[] findIncludedAirports() {
		CSVReaderInitialMatrix reader = new CSVReaderInitialMatrix();
		List<CSVRowInitialMatrix> rows = reader.getRows();
		return rows.get(0).getAirports();
	}


	/**
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}
}
