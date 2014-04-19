package edu.gatech.cx4230.projecttwo.sim.creation;

import java.util.ArrayList;
import java.util.Collection;

import sun.security.util.Length;
import edu.gatech.cx4230.projecttwo.database.operation.AirportsDBOperator;
import edu.gatech.cx4230.projecttwo.sim.event.Timetable;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.utilities.CSVRow;

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
		
		System.out.println("Generating flight timetable.");
		for(int i = 0; i < fm.length; i++){
			for(int j = 0; j < fm[i].length; j++){
				String aircraftType;
				if(dm[i][j] < 850){
					aircraftType = Aircraft.TYPE_REGIONAL;
				}else if(dm[i][j] < 1500){
					aircraftType = Aircraft.TYPE_SMALL;
				}else if(dm[i][j] < 1500){
					aircraftType = Aircraft.TYPE_MEDIUM;
				}else{
					aircraftType = Aircraft.TYPE_LARGE;
				}
				
				int lengthOfDay = 14 - tzm[i][j]; // hours
				if(fm[i][j] == 0)
					continue;
				int flightInterval = lengthOfDay * 3600 / fm[i][j];
				
				int k = 0;
				while(fm[i][j] > 0){
					// Allow non-uniform spacing of flights to common destinations (multi airlines).
					// Also prevent flights from necessarily leaving at the same time (t=0).
					int tzOffset = tzm[i][j] * 60 * (60 / SimulationThread.timeStep);
					int departureTime = tzOffset + World.chance().nextInt(flightInterval) + flightInterval * k;
					t.addScheduledFlight(airports[i].getIcaoCode(), airports[j].getIcaoCode(),
							aircraftType, departureTime);
					fm[i][j]--;
					k++;
				}
			}
		}
	}
	
	public Timetable getTimetable(){
		return t;
	}
	
	private int[][] distanceMatrix(){
		System.out.println("Calculating distances.");
		int[][] dists = new int[airports.length][airports.length];
		for(int i = 0; i < airports.length; i++)
			for(int j = 0; j < airports.length; j++)
				dists[i][j] = (int)World.calculateDistance(airports[i].getLatitude(), airports[i].getLongitude(), 
								airports[j].getLatitude(), airports[j].getLongitude());
		return dists;
	}
	
	private int[][] flightMatrix(){
		System.out.println("Getting aircraft routs.");
		int[][] flights = new int[flightList.size()][airports.length];
		for(int i = 0; i < flightList.size(); i++){
			CSVRowInitialMatrix ap = flightList.get(i);
			for(int j = 0; j < airports.length; j++){
				flights[i][j] = ap.getWeightForAirport(airports[j].getIcaoCode());
			}
		}
		return flights;
	}
	
	private int[][] timezoneMatrix(){
		System.out.println("Initializing airport timezones.");
		int[][] matrix = new int[airports.length][airports.length];
		// Timezone of destination - Timezone of origin
		for(int i = 0; i < airports.length; i++)
			for(int j = 0; j < airports.length; j++){
				matrix[i][j] = airports[j].getTimeZone() - airports[i].getTimeZone();
			}
		return matrix;
	}
}