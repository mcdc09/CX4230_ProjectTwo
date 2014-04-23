package edu.gatech.cx4230.projecttwo.sim.event;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.ScheduledFlight;
import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.utilities.GeoHelper;

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
		Flight out = null;
		int ATA = 0;
		
		ScheduledFlight f = timetable.get(origin.getIcaoCode()).get(a.getAircraftType()).poll();
		// System.out.println(f.getOrigin() + " to " + f.getDestination() + " " + f.getDepartureTime());
		if(f!= null) {
			Airport destination = World.getAirport(f.getDestination());
			double distance = GeoHelper.calcDistance(origin.getLocation(), destination.getLocation()); // km
			int TOD = Math.max(SimulationThread.getCurrTimeStep(), f.getDepartureTime()) + 
						2400 / SimulationThread.TIME_PER_STEP; // (steps) schedule take-off in 40 minutes
			double t = (distance / a.getSpeed()) * 3600.0; // seconds
			int ETA = f.getDepartureTime() + 2400 / SimulationThread.TIME_PER_STEP + 
						(int) (t / SimulationThread.TIME_PER_STEP);
			ATA = TOD + (int) (t / SimulationThread.TIME_PER_STEP); // steps
			out =  new Flight(a, origin, destination, distance, TOD, ETA);
		}
		out.setActualTimeArrival(ATA);
		return out;
	}

	public void timetableStatus(){

	}
}