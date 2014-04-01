package edu.gatech.cx4230.projecttwo.sim.objects;

import java.util.ArrayList;

import edu.gatech.cx4230.projecttwo.sim.event.Event;
import edu.gatech.cx4230.projecttwo.sim.event.EventPriorityQueue;


public class Airport {

	// properties
	private final ArrayList<Runway> runways;
	private final float latitude;
	private final float longitude;
	private final String city;
	private final String state;
	private final String icaoCode;
	private final String timeZone;
	
	// state
	private int onTheGround;
	private int inTheAir;
	
	// events
	EventPriorityQueue<Event> pendingEvents;
	ArrayList<Event> processedEvents;
	
	public Airport(ArrayList<Runway> runways, float latitude, float longitude,
			String city, String state, String icaoCode, String timeZone) {
		this.runways = runways;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
		this.icaoCode = icaoCode;
		this.timeZone = timeZone;
	}

	/* PROPERTIES */
	
	public ArrayList<Runway> getRunways() {
		return runways;
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

	public String getTimeZone() {
		return timeZone;
	}

	
	/* STATE */
	
	public int getOnTheGround() {
		return onTheGround;
	}

	public void setOnTheGround(int onTheGround) {
		this.onTheGround = onTheGround;
	}

	public int getInTheAir() {
		return inTheAir;
	}

	public void setInTheAir(int inTheAir) {
		this.inTheAir = inTheAir;
	}

	public Runway getFreeRunway() {
		for(Runway r : runways) {
			if(r.isRunwayFree()) {
				return r;
			}
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
	
	public void processNextEvent() {
		Event event = pendingEvents.removeMin();
		event.process();
		addProcessedEvent(event);
	}
}
