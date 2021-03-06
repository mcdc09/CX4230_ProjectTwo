package edu.gatech.cx4230.projecttwo.sim.event;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.LargeAircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.MediumAircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.RegionalAircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.SmallAircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.World;

/**
 * Object used to pass along initial distribution of aircraft to each airport
 * @author tbowling3
 *
 */
public class AircraftCreationEvent extends AirportEvent {
	private int regionalCount;
	private int smallCount;
	private int mediumCount;
	private int largeCount;

	/**
	 * 
	 * @param aICAO The airport in question
	 * @param regionalCount The number of regional aircraft at that airport
	 * @param smallCount The number of small aircraft aircraft at the airport
	 * @param medCount The number of medium aircraft aircraft at the airport
	 * @param largeCount The number of large aircraft aircraft at the airport
	 */
	public AircraftCreationEvent(String aICAO, int regionalCount, int smallCount, int medCount, int largeCount) {
		this.airportICAO = aICAO;
		this.regionalCount = regionalCount;
		this.smallCount = smallCount;
		this.mediumCount = medCount;
		this.largeCount = largeCount;
	}

	@Override
	public void process(int currTime) {
		List<Aircraft> aircrafts = new ArrayList<Aircraft>();
		for(int i = 0; i < regionalCount; i++) {
			aircrafts.add(new RegionalAircraft());
		}
		for(int i = 0; i < smallCount; i++) {
			aircrafts.add(new SmallAircraft());
		}
		for(int i = 0; i < mediumCount; i++) {
			aircrafts.add(new MediumAircraft());
		}
		for(int i = 0; i < largeCount; i++) {
			aircrafts.add(new LargeAircraft());
		}
		
		Airport airport = World.getAirport(airportICAO);
		for(Aircraft a: aircrafts) {
			// add aircraft to onTheGround at destination airport
			airport.addAircraftOnTheGround(a);
			
			try{
				// consult timetable to get new flight for this aircraft
				Flight newFlight = World.getTimetable().scheduleFlight(airport, a);
				// schedule DepartureEvent with destination airport as new origin airport
				DepartureEvent departEvent = new DepartureEvent(newFlight);
				airport.addPendingEvent(departEvent);
			}catch(NullPointerException e){
				continue;
			}
		}
	}
	
	public String toString() {
		return airportICAO + " Reg: " + regionalCount + " Sml: " + smallCount + " Med: " + mediumCount + " Lrg: " + largeCount;
	}
}