package edu.gatech.cx4230.projecttwo.sim.creation;

import edu.gatech.cx4230.projecttwo.sim.event.Timetable;

public class FlightGenerator {
	private Timetable t;
	
	public FlightGenerator(){
		t = new Timetable();
		for(int i = 0; i < 0; i++){
			t.addScheduledFlight(origin, destination, aircraftType, departureTime);
		}
	}
}