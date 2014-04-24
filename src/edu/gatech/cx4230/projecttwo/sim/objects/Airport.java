package edu.gatech.cx4230.projecttwo.sim.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.fhpotsdam.unfolding.geo.Location;
import edu.gatech.cx4230.projecttwo.sim.event.AirportEvent;
import edu.gatech.cx4230.projecttwo.sim.event.ArrivalEvent;
import edu.gatech.cx4230.projecttwo.sim.event.DepartureEvent;
import edu.gatech.cx4230.projecttwo.sim.event.Event;
import edu.gatech.cx4230.projecttwo.sim.event.EventPriorityQueue;
import edu.gatech.cx4230.projecttwo.sim.event.FlightEvent;
import edu.gatech.cx4230.projecttwo.sim.event.LandedEvent;
import edu.gatech.cx4230.projecttwo.sim.testing.AirportSimulationLoggerMaster;


public class Airport {

	// properties
	private final ArrayList<Runway> runways;
	private final int maxAircraftCapacity;
	private final float latitude;
	private final float longitude;
	private final String city;
	private final String state;
	private final String icaoCode;
	private final String name;
	private final int timeZone;
	
	// state
	private boolean groundStop;
	private ArrayList<Aircraft> onTheGround;
	private int inTheAir;
	
	// events
	private EventPriorityQueue<Event> pendingEvents;
	private ArrayList<Event> processedEvents;
	private int currSimTime;
	
	public Airport(ArrayList<Runway> runways, ArrayList<Aircraft> onTheGround, int maxAircraftCapacity, 
			float latitude, float longitude, String city, String state, String icaoCode, int timeZone, String name) {
		// set airport properties
		this.runways = runways;
		this.maxAircraftCapacity = maxAircraftCapacity;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
		this.icaoCode = icaoCode;
		this.timeZone = timeZone;
		this.name = name;
		
		// initialize state
		this.groundStop = false;
		this.onTheGround = onTheGround; // initial aircrafts on the ground
		inTheAir = 0;
		
		// create new event queues
		pendingEvents = new EventPriorityQueue<Event>();
		processedEvents = new ArrayList<Event>();
	}
	
	public String toString() {
		return name;
	}

	/* PROPERTIES */
	
	public Location getLocation() {
		return new Location(latitude, longitude);
	}
	
	public ArrayList<Runway> getRunways() {
		return runways;
	}

	public int getMaxAircraftCapacity() {
		return maxAircraftCapacity;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getIcaoCode() {
		return icaoCode;
	}

	public int getTimeZone() {
		return timeZone;
	}
	
	public String getName() {
		return name;
	}

	
	/* STATE */
	
	public boolean isGroundStop() {
		return groundStop;
	}
	
	public void setGroundStop(boolean flag) {
		this.groundStop = flag;
	}
	
	public ArrayList<Aircraft> getAircraftsOnTheGround() {
		return onTheGround;
	}
	
	public int getNumOnTheGround() {
		return onTheGround.size();
	}

	public void addAircraftOnTheGround(Aircraft a) {
		onTheGround.add(a);
	}

	public void removeAircraftOnTheGround(Aircraft a) {
		onTheGround.remove(a);
	}
	
	public int getInTheAir() {
		return inTheAir;
	}

	public void setInTheAir(int inTheAir) {
		this.inTheAir = inTheAir;
	}

	public boolean hasFreeRunway(int minLength) {
		for(Runway r : runways) {
			if(r.isRunwayFree(currSimTime) && r.getLength() >= minLength) {
				return true;
			}
		}
		return false;
	}
	
	public Runway getFreeRunway(int minLength) {
		ArrayList<Runway> available = new ArrayList<Runway>();
		for(Runway r : runways) {
			if(r.isRunwayFree(currSimTime) && r.getLength() >= minLength) {
				//return r;
				available.add(r);
			}
		}
		int numAvailable = available.size();
		if(numAvailable > 0) {
			Random rand = new Random();
			int i = rand.nextInt(numAvailable);
			return available.get(i);
		}
		return null;
	}
	
	/* EVENTS */
	 	
	public EventPriorityQueue<Event> getPendingEvents() {
		return pendingEvents;
	}

	public void addPendingEvent(Event event) {
		pendingEvents.addInOrder(event);
	}

	public ArrayList<Event> getProcessedEvents() {
		return processedEvents;
	}

	public void addProcessedEvent(Event event) {
		processedEvents.add(event);
	}
	
	public int getTimeNextPendingEvent() {
		return pendingEvents.getMinValue();
	}
	
	public void processNextEvents(int currSimTime) {
		this.currSimTime = currSimTime;
		while(getTimeNextPendingEvent() <= currSimTime) {
			processNextEvent(currSimTime);
		}
	}
	
	public void processNextEvent(int currSimTime) {
		this.currSimTime = currSimTime;
		Event event = pendingEvents.removeMin();
		if(canProcessEvent(event)) {
			event.process(currSimTime);
			addProcessedEvent(event);
		}
		else {
			// if the event could not be processed, add it back to the pending event queue 
			// for next time step
			String line = "Airport<processNextEvent> " + currSimTime + " " + event.toString() + " not processed";
			AirportSimulationLoggerMaster.logLineEvent(line);
			event.setProcessTime(event.getProcessTime() + 1);
			addPendingEvent(event);
		}
	}
	
	public boolean canProcessEvent(Event e) {
		return (e instanceof DepartureEvent && canProcessDeparture((FlightEvent)e))
				|| (e instanceof ArrivalEvent && canProcessArrival((FlightEvent)e))
				|| (e instanceof LandedEvent && canProcessLanding((FlightEvent)e))
				|| (e instanceof AirportEvent);
	}
	
	public boolean canProcessDeparture(FlightEvent e) {
		if(onTheGround.size() > 0 
				&& hasFreeRunway(e.getFlight().getAircraft().getMinRunwayLength())
				&& !e.getFlight().getDestination().isGroundStop()) {
			return true;
		}
		return false;
	}
		
	public boolean canProcessArrival(FlightEvent e) {
		return true;
	}
	
	public boolean canProcessLanding(FlightEvent e) {
		return onTheGround.size() < maxAircraftCapacity && hasFreeRunway(e.getFlight().getAircraft().getMinRunwayLength());
	}
	
	public void addAircraftOnTheGround(List<Aircraft> aircrafts) {
		for(Aircraft a: aircrafts) {
			addAircraftOnTheGround(a);
		}
	}

	/**
	 * @return the currSimTime
	 */
	public int getCurrSimTime() {
		return currSimTime;
	}
}
