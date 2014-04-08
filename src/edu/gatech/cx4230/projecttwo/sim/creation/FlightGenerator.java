package edu.gatech.cx4230.projecttwo.sim.creation;

import java.util.ArrayList;

import sun.security.util.Length;
import edu.gatech.cx4230.projecttwo.sim.event.Timetable;
import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.utilities.CSVRow;

public class FlightGenerator {
	private Timetable t;
	private ArrayList<CSVRowInitialMatrix> flightList;
	
	public FlightGenerator(){
		t = new Timetable();
		
		flightList = (new CSVReaderInitialMatrix()).getRows();
		
		int[][] dm = distanceMatrix();
		int[][] fm = flightMatrix();
		int[][] tzm = timezoneMatrix();
		
		for(int i = 0; i < fm.length; i++){
			for(int j = 0; j < fm[i].length; j++){
				int lengthOfDay = 14 - tzm[i][j];
				int flightInterval = lengthOfDay * 3600 / fm[i][j];
				// t.addScheduledFlight(origin, destination, aircraftType, departureTime);
			}
		}
	}
	
	private int[][] distanceMatrix(){
		return null;
	}
	
	private int[][] flightMatrix(){
		return null;
	}
	
	private int[][] timezoneMatrix(){
		// Timezone of origin - Timezone of destination
		return null;
	}
}