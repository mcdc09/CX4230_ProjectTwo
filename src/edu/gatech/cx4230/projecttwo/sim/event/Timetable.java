package edu.gatech.cx4230.projecttwo.sim.event;

import java.util.*;

import edu.gatech.cx4230.projecttwo.sim.objects.*;

public class Timetable {
	private Map<String, HashMap<String, LinkedList<Object>>> timetable;
	
	/**
	 * 
	 */
	public Timetable() {
		timetable = new HashMap<String, HashMap<String, LinkedList<Object>>>();
	}
	
	/**
	 * 
	 * @param origin
	 * @param destination
	 * @param aircraftType
	 * @param departureTime
	 */
	public void addScheduledFlight(String origin, String destination, String aircraftType, int departureTime){
		ScheduledFlight f = new ScheduledFlight(origin, destination, aircraftType, departureTime);
		timetable.get(origin).get(aircraftType).add(f);
	}
	
	/**
	 * Get the next scheduled flight departing the airport that uses a particular type of aircraft,
	 * presumably one that is already on the ground at that airport.  This allows the simulation to
	 * schedule the next possible flight from that airport instead of bogging down the system with
	 * flights that cannot be made due to lack of proper aircraft.  Airports should queue up their
	 * scheduled flights as aircraft become available at their airport.
	 * @param origin Airport that is the origin of a flight, presumably the airport calling this
	 * method.
	 * @param aircraftType String representation of the type of aircraft that is on the ground.  If
	 * there is no flight leaving the specified airport for the given type of aircraft, that aircraft
	 * will remain on the ground.
	 */
	public Object getScheduledFlight(String origin, String aircraftType){
		return timetable.get(origin).get(aircraftType).pop();
	}
	
	public void timetableStatus(){
		
	}
}