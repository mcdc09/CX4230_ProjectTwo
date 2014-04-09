package edu.gatech.cx4230.projecttwo.sim.event;

import java.util.*;
import edu.gatech.cx4230.projecttwo.sim.objects.*;

public class Timetable {
	private Map<String, HashMap<String, PriorityQueue<ScheduledFlight>>> timetable;
	
	/**
	 * 
	 */
	public Timetable() {
		timetable = new HashMap<String, HashMap<String, PriorityQueue<ScheduledFlight>>>();
	}
	
	/**
	 * 
	 * @param origin
	 * @param destination
	 * @param aircraftType
	 * @param departureTime
	 */
	public void addScheduledFlight(String origin, String destination, String aircraftType, int departureTime){
		if(!timetable.containsKey(origin))
			timetable.put(origin, new HashMap<String, PriorityQueue<ScheduledFlight>>());
		if(!timetable.get(origin).containsKey(aircraftType)){
			timetable.get(origin).put(aircraftType, new PriorityQueue<ScheduledFlight>());
		}
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
	 * @param a Aircraft that is on the ground.  If there is no flight leaving the specified airport
	 * for the given type of aircraft, that aircraft will remain on the ground.
	 * @return
	 */
	public Flight scheduleFlight(Airport origin, Aircraft a){
		ScheduledFlight f = timetable.get(origin.getIcaoCode()).get(a.getAircraftType()).poll();
		Airport destination = World.getAirport(f.getDestination());
		int distance = (int)World.calculateDistance(origin.getLatitude(), origin.getLongitude(), 
						destination.getLatitude(), destination.getLongitude());
		int TOD = World.getCurrentSimTime() + 2400 / World.timeStep; // schedule take-off in 40 minutes
		int ETA = TOD + distance * a.getSpeed();
		return new Flight(a, origin, destination, distance, TOD, ETA);
	}
	
	public void timetableStatus(){
		
	}
}