package edu.gatech.cx4230.projecttwo.sim.creation;

import java.util.ArrayList;

import edu.gatech.cx4230.projecttwo.sim.event.Timetable;
import edu.gatech.cx4230.projecttwo.utilities.CSVRow;

public class FlightGenerator {
	private Timetable t;
	private ArrayList<CSVRowInitialMatrix> flightList;
	
	public FlightGenerator(){
		t = new Timetable();
		
		flightList = (new CSVReaderInitialMatrix()).getRows();
		
		for(int i = 0; i < 0; i++){
			// t.addScheduledFlight(origin, destination, aircraftType, departureTime);
		}
	}
}