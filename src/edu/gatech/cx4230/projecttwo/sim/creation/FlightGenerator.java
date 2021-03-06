package edu.gatech.cx4230.projecttwo.sim.creation;

import java.util.ArrayList;

import edu.gatech.cx4230.projecttwo.sim.event.Timetable;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.utilities.GeoHelper;

public class FlightGenerator {
	private Timetable t;
	private ArrayList<CSVRowInitialMatrix> flightList;
	private Airport[] airports;
	
	public FlightGenerator(){
		t = new Timetable();
		
		airports = World.getAirports().toArray(new Airport[0]);
		
		flightList = (new CSVReaderInitialMatrix()).getRows();
		
		int[][] dm = distanceMatrix();
		int[][] fm = flightMatrix();
		int[][] tzm = timezoneMatrix();
		
		int rglCount = 0;
		int smlCount = 0;
		int medCount = 0;
		int lrgCount = 0;
		
		// System.out.println("Generating flight timetable.");
		for(int i = 0; i < fm.length; i++){
			for(int j = 0; j < fm[i].length; j++){
				String aircraftType;
				if(dm[i][j] < 850){
					aircraftType = Aircraft.TYPE_REGIONAL;
					rglCount += fm[i][j];
				}else if(dm[i][j] < 1500){
					aircraftType = Aircraft.TYPE_SMALL;
					smlCount += fm[i][j];
				}else if(dm[i][j] < 3000){
					aircraftType = Aircraft.TYPE_MEDIUM;
					medCount += fm[i][j];
				}else{
					aircraftType = Aircraft.TYPE_LARGE;
					lrgCount += fm[i][j];
				}
				
				int lengthOfDay = 18 + tzm[i][j]; // hours
				if(fm[i][j] == 0)
					continue;
				int flightInterval = lengthOfDay * 3600 / SimulationThread.TIME_PER_STEP / (fm[i][j] + 1);
				// System.out.println(airports[i].getIcaoCode() + "-" + airports[j].getIcaoCode() + " " + flightInterval);
				
				int k = 0;
				while(fm[i][j] > 0){
					// Allow non-uniform spacing of flights to common destinations (multi airlines).
					// Also prevent flights from necessarily leaving at the same time (t=0).
					int tzOffset = airports[i].getTimeZone() * 60 * (60 / SimulationThread.TIME_PER_STEP);
					int departureTime = tzOffset + World.chance().nextInt(flightInterval) + flightInterval * k;
					// System.out.println(airports[i].getIcaoCode() + "-" + airports[j].getIcaoCode() + " " + departureTime);
					t.addScheduledFlight(airports[i].getIcaoCode(), airports[j].getIcaoCode(),
							aircraftType, departureTime);
					fm[i][j]--;
					k++;
				}
			}
		}
		World.populateACDistr(rglCount, smlCount, medCount, lrgCount);
	}
	
	public Timetable getTimetable(){
		return t;
	}
	
	private int[][] distanceMatrix(){
		// System.out.println("Calculating distances.");
		int[][] dists = new int[airports.length][airports.length];
		for(int i = 0; i < airports.length; i++)
			for(int j = 0; j < airports.length; j++)
				dists[i][j] = (int)GeoHelper.calcDistance(airports[i].getLocation(), airports[j].getLocation());
		return dists;
	}
	
	private int[][] flightMatrix(){
		// System.out.println("Getting aircraft routes.");
		int[][] flights = new int[flightList.size()][airports.length];
		for(int i = 0; i < flightList.size(); i++){
			CSVRowInitialMatrix ap = flightList.get(i);
			for(int j = 0; j < airports.length; j++){
				// get correct row to place this airport in
				int k = -1;
				for (int z = 0; z < airports.length; z++)
					if(airports[z].getIcaoCode().substring(1).equals(ap.getOriginAirport())){
						k = z;
						break;
					}
				flights[k][j] = ap.getWeightForAirport(airports[j].getIcaoCode().substring(1)) / 10;
				//System.out.println(airports[k].getIcaoCode() + "-" + airports[j].getIcaoCode() + ": " + flights[k][j]);
			}
		}
		return flights;
	}
	
	private int[][] timezoneMatrix(){
		// System.out.println("Initializing airport timezones.");
		int[][] matrix = new int[airports.length][airports.length];
		// Timezone of destination - Timezone of origin
		for(int i = 0; i < airports.length; i++)
			for(int j = 0; j < airports.length; j++){
				matrix[i][j] = airports[j].getTimeZone() - airports[i].getTimeZone();
			}
		return matrix;
	}
}