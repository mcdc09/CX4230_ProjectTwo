package edu.gatech.cx4230.projecttwo.sim.creation;

import java.util.ArrayList;

import sun.security.util.Length;
import edu.gatech.cx4230.projecttwo.sim.event.Timetable;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
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
		
		String[] airports = {""};
		
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
					int departureTime = World.chance().nextInt(flightInterval) + flightInterval * k;
					t.addScheduledFlight(airports[i], airports[j], aircraftType, departureTime);
					fm[i][j]--;
					k++;
				}
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
		// Timezone of destination - Timezone of origin
		return null;
	}
}