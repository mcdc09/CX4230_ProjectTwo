package edu.gatech.cx4230.projecttwo.sim.objects;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cx4230.projecttwo.sim.event.Event;
import edu.gatech.cx4230.projecttwo.sim.event.EventPriorityQueue;


public class Airport {

	// properties
	private List<Runway> runways;
	private float latitude;
	private float longitude;
	private String city;
	private String state;
	private String icaoCode;
	private String timeZone;
	
	// state
	private int onTheGround;
	private int inTheAir;
	
	// events
	EventPriorityQueue<Event> pendingEvents;
	ArrayList<Event> processedEvents;
	
	public Airport() {
		
	}

	/* PROPERTIES */
	
	public List<Runway> getRunways() {
		return runways;
	}

	public void setRunways(List<Runway> runways) {
		this.runways = runways;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIcaoCode() {
		return icaoCode;
	}

	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
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

	
	/* EVENTS */
	 	
	public EventPriorityQueue<Event> getPendingEvents() {
		return pendingEvents;
	}
	
	public Event getNextPendingEvent() {
		return pendingEvents.removeMin();
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
	
	public void processNextEvent() {
		Event event = getNextPendingEvent();
		event.process();
		addProcessedEvent(event);
	}
}
